package com.mengzhilan.form;

import com.mengzhilan.entity.model.ModelInfo;
import com.mengzhilan.entity.model.form.ModelFormDetailConfig;
import com.mengzhilan.enumeration.FormFieldType;
import com.mengzhilan.enumeration.attribute.AttributeType;
import com.mengzhilan.form.annotation.LoadBeanForm;
import com.mengzhilan.helper.CommonServiceHelper;
import com.mengzhilan.service.model.ModelAttributeService;
import com.mengzhilan.service.model.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xlp.db.tableoption.annotation.XLPColumn;
import org.xlp.db.tableoption.annotation.XLPEntity;
import org.xlp.db.tableoption.annotation.XLPId;
import org.xlp.javabean.JavaBeanPropertiesDescriptor;
import org.xlp.javabean.PropertyDescriptor;
import org.xlp.scanner.pkg.ClassPathPkgScanner;
import org.xlp.scanner.pkg.ScannerPkg;
import org.xlp.scanner.util.ClassUtils;
import org.xlp.utils.XLPBooleanUtil;
import org.xlp.utils.XLPDateUtil;
import org.xlp.utils.XLPStringUtil;

import java.io.IOException;
import java.util.*;

/**
 * Create by xlp on 2022/6/9
 *
 * 表单配置
 */
@LoadBeanForm(packageName = "com.mengzhilan.entity")
public class FormConfig {
    private final static Logger LOGGER = LoggerFactory.getLogger(FormConfig.class);

    /**
     * 存储所有的类名称
     */
    private final static Set<String> CLASS_NAMES = new HashSet<>();

    /**
     * 存储所有的类
     */
    private final static Set<Class<?>> CLASS_SET = new HashSet<>();

    /**
     * 存储bean form 信息
     */
    private final static List<FormInfoBean> FORM_INFO_BEANS = new LinkedList<>();

    private final static ModelService MODEL_SERVICE = CommonServiceHelper.getModelService();

    private final static ModelAttributeService MODEL_ATTRIBUTE_SERVICE = CommonServiceHelper.getModelAttributeService();

    static {
        reload();
    }

    /**
     * 加载符合要求class并解析出form或表格配置
     */
    public static void reload(){
        CLASS_NAMES.clear();
        CLASS_SET.clear();
        LoadBeanForm loadBeanForm = FormConfig.class.getAnnotation(LoadBeanForm.class);
        // 读取指定包名下的类
        ScannerPkg scannerPkg = new ClassPathPkgScanner();
        // 获取要加载的包名称
        String[] packageName = loadBeanForm.packageName();
        try {
            for (String pn : packageName) {
                if (!XLPStringUtil.isEmpty(pn)){
                    CLASS_NAMES.addAll(scannerPkg.scanner(pn));
                }
            }
        } catch (IOException e) {
            if (LOGGER.isErrorEnabled()){
                LOGGER.error("加载bean转换成表单信息失败！", e);
            }
        }
        // 处理类名
        String[] className = loadBeanForm.className();
        for (String cn : className) {
            if (!XLPStringUtil.isEmpty(cn)){
                CLASS_NAMES.add(cn);
            }
        }

        //去除不需要的类名
        String[] excludeClassName = loadBeanForm.excludeClassName();
        for (String ecn : excludeClassName) {
            if (!XLPStringUtil.isEmpty(ecn)){
                CLASS_NAMES.remove(ecn);
            }
        }

        // 查找出符合要求的类
        findSuitClasses();

        // 解析成form信息
        parseToFormInfoBeans();

        // 从数据库中读取最新信息
        updateFormInfoBeans();
    }

    private static void orderFormInfoBeans() {
       FORM_INFO_BEANS.sort((o1, o2) -> {
           int orderNo1 = o1.getOrderNo();
           int orderNo2 = o2.getOrderNo();
           return Integer.compare(orderNo1, orderNo2);
        });
    }

    private static void updateFormInfoBeans() {
        List<ModelInfo> modelInfos = MODEL_SERVICE.list(ModelInfo.class);
        for (ModelInfo modelInfo : modelInfos) {
            for (FormInfoBean formInfoBean : FORM_INFO_BEANS) {
                if (formInfoBean.getBeanId().equals(modelInfo.getModelId())){
                    formInfoBean.setBeanName(modelInfo.getModelName());
                    formInfoBean.setOrderNo(modelInfo.getWeight());
                    formInfoBean.setHidden(modelInfo.isDisabled());
                    break;
                }
            }
        }
    }

    private static void parseToFormInfoBeans() {
        XLPEntity entity;
        FormInfoBean form;
        PropertyDescriptor<?>[] pds;
        for (Class<?> cs : CLASS_SET) {
            entity = cs.getAnnotation(XLPEntity.class);
            form = new FormInfoBean();
            form.setSourceBeanClass(cs);
            form.setBeanId(cs.getSimpleName());
            String beanName = entity.descriptor();
            form.setBeanName(XLPStringUtil.isEmpty(beanName) ? cs.getSimpleName() : beanName);
            form.setTableName(entity.tableName());
            pds = new JavaBeanPropertiesDescriptor<>(cs).getPds();
            FormFieldInfo formFieldInfo;
            String fieldDescription = XLPStringUtil.EMPTY, columnName = XLPStringUtil.EMPTY;
            Class<?> fieldClass;
            XLPColumn xlpColumn;
            XLPId xlpId = null;
            for (PropertyDescriptor<?> pd : pds) {
                formFieldInfo = new FormFieldInfo();
                formFieldInfo.setFormFieldId(pd.getFieldName());
                xlpColumn =  pd.getFieldAnnotation(XLPColumn.class);
                if (xlpColumn != null){
                    fieldDescription = xlpColumn.descriptor();
                    columnName = xlpColumn.columnName();
                } else if ((xlpId = pd.getFieldAnnotation(XLPId.class)) != null){
                    fieldDescription = xlpId.descriptor();
                    columnName = xlpId.columnName();
                }
                if (xlpColumn != null || xlpId != null){
                    formFieldInfo.setFormFieldName(XLPStringUtil.isEmpty(fieldDescription)
                            ? pd.getFieldName() : fieldDescription);
                    fieldClass = pd.getFiledClassType();
                    if (XLPBooleanUtil.isBoolean(fieldClass)){
                        formFieldInfo.setFormFieldType(FormFieldType.BOOLEAN);
                    } else if (XLPDateUtil.isDate(fieldClass)){
                        formFieldInfo.setFormFieldType(FormFieldType.DATE);
                    } else {
                        formFieldInfo.setFormFieldType(FormFieldType.INPUT);
                    }
                    formFieldInfo.setAttributeType(AttributeType.HARD_ATTR);
                    formFieldInfo.setCanDelete(false);
                    formFieldInfo.setColumnName(XLPStringUtil.isEmpty(columnName) ? pd.getFieldName()
                            : columnName);
                    form.addFormFieldInfo(formFieldInfo);
                }
            }
            FORM_INFO_BEANS.add(form);
            updateFormFieldInfoFromDb(form);
        }
    }

    /**
     * 用数据中的数据更新该模型属性信息
     *
     * @param form
     */
    private static void updateFormFieldInfoFromDb(FormInfoBean form) {
        List<ModelFormDetailConfig> modelFormDetailConfigs =
                MODEL_ATTRIBUTE_SERVICE.getModelFormDetailConfigByModelId(form.getBeanId());
        modelFormDetailConfigs.forEach((item) -> {
            FormFieldInfo formFieldInfo = findFormFieldInfo(form, item.getFieldId());
            if (formFieldInfo != null){
                formFieldInfo.setFormFieldName(item.getFieldName());
                formFieldInfo.setCanDelete(item.getCanDelete());
                formFieldInfo.setOrderNo(item.getOrderNo());
                formFieldInfo.setAttributeType(item.getAttributeType());
            }
        });
    }

    /**
     * 根据模型id和属性id获取FormFieldInfo对象
     *
     * @param beanId
     * @param formFieldId
     * @return
     */
    public static FormFieldInfo findFormFieldInfo(String beanId, String formFieldId){
        FormInfoBean formInfoBean = findFormInfoBean(beanId);
        return findFormFieldInfo(formInfoBean, formFieldId);
    }

    /**
     * 根据模型表单对象和属性id获取FormFieldInfo对象
     *
     * @param formInfoBean
     * @param formFieldId
     * @return
     */
    public static FormFieldInfo findFormFieldInfo(FormInfoBean formInfoBean, String formFieldId){
        if (formInfoBean == null) return null;
        if (XLPStringUtil.isEmpty(formFieldId)) return null;
        List<FormFieldInfo> formFieldInfos = formInfoBean.getFormFieldInfos();
        for (FormFieldInfo formFieldInfo : formFieldInfos) {
            if (formFieldInfo.getFormFieldId().equals(formFieldId)) return formFieldInfo;
        }
        return null;
    }

    private static void findSuitClasses() {
        Set<Class<?>> classes = new HashSet<>();
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        for (String className : CLASS_NAMES) {
            //这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
            try {
                classes.add(classLoader.loadClass(className));
            } catch (ClassNotFoundException e) {
                if (LOGGER.isErrorEnabled())
                    LOGGER.error("【" + className + "】该class加载失败", e);
            }
        }
        for (Class<?> cs : classes) {
            if (cs.getAnnotation(XLPEntity.class) != null){
                CLASS_SET.add(cs);
            }
        }
    }

    /**
     * 获取form 信息
     *
     * @return
     */
    public static List<FormInfoBean> getFormInfoBeans(){
        // 排序
        orderFormInfoBeans();
        return FORM_INFO_BEANS;
    }

    /**
     * 通过beanId 获取FormInfoBean对象
     *
     * @param beanId
     * @return
     */
    public static FormInfoBean findFormInfoBean(String beanId){
        for (FormInfoBean infoBean : FORM_INFO_BEANS) {
            if (infoBean.getBeanId().equals(beanId)) return infoBean;
        }
        return null;
    }
}
