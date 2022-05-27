package com.mengzhilan.listener;

import com.alibaba.druid.pool.DruidDataSource;
import com.mengzhilan.constant.inner.InnerConstants;
import com.mengzhilan.mapping.RequestMappingMap;
import com.mengzhilan.sysout.ReportUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xlp.db.utils.XLPDBUtil;
import org.xlp.utils.XLPStringUtil;
import org.xlp.utils.io.XLPIOUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;
import java.util.Properties;

/**
 * Create by xlp on 2020/11/26
 * <p>
 * Tomcat 启动监听
 */
public class MengzhilanApplicationListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(MengzhilanApplicationListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        String isReport = sc.getInitParameter(InnerConstants.CONTEXT_PARAM_KEY_IS_REPORT);
        if (LOGGER.isInfoEnabled())
          LOGGER.info("获配置信息isReport=" + isReport);
        if (isReport != null && !"true".equals(isReport)) {
            ReportUtils.setIsReport(false);
        }
        String druidConfigFile = sc.getInitParameter(InnerConstants.CONTEXT_PARAM_KEY_DRUID_PROPERTIES);
        if (LOGGER.isInfoEnabled())
            LOGGER.info("获取配置信息druid.properties=" + druidConfigFile);
        //创建数据库连接池
        DruidDataSource druidDataSource = new DruidDataSource();
        druidConfigFile = XLPStringUtil.emptyTrim(druidConfigFile);

        //获取druid数据库连接池配置文件的输入流
        InputStream inputStream = null;
        if (XLPStringUtil.isEmpty(druidConfigFile)) {
            inputStream = MengzhilanApplicationListener.class.getClassLoader()
                    .getResourceAsStream(InnerConstants.DEFALUT_DRUID_PROPERTIES_FILE_NAME);
        } else if (druidConfigFile.startsWith(InnerConstants.CLASS_PATH_PRE)) {
            String druidFileName = druidConfigFile.substring(InnerConstants.CLASS_PATH_PRE.length());
            inputStream = MengzhilanApplicationListener.class.getClassLoader()
                    .getResourceAsStream(druidFileName);
        } else {
            String path = sc.getRealPath(druidConfigFile);
            File file = new File(path);
            if (!file.exists()){
                if (LOGGER.isWarnEnabled())
                    LOGGER.warn(path + "不存在，设置druid数据库连接池配置文件！");
            }else {
                try {
                    inputStream = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    if (LOGGER.isWarnEnabled())
                        LOGGER.warn("读取druid数据库连接池配置文件失败！", e);
                }
            }
        }
        //初始化数据库连接池
        if (inputStream != null){
            Properties properties = new Properties();
            try {
                properties.load(inputStream);
                druidDataSource.configFromPropety(properties);
                if (LOGGER.isDebugEnabled())
                    LOGGER.debug("druid数据库连接池配置信息：" + properties);
            } catch (IOException e) {
                if (LOGGER.isWarnEnabled())
                  LOGGER.warn("读取druid数据库连接池配置文件失败！", e);
            }
        }
        XLPIOUtil.closeInputStream(inputStream);
        XLPDBUtil.initDataSource(druidDataSource);
        if (LOGGER.isInfoEnabled())
          LOGGER.info("数据初始化完成。");

        String pkgPaths = sc.getInitParameter(InnerConstants.CONTEXT_PARAM_KEY_SCANNER_CONTROLLE_RPACKAGE);
        try {
            RequestMappingMap.loadAllController(this.getClass().getClassLoader(), pkgPaths);
        } catch (IOException e) {
            if (LOGGER.isErrorEnabled())
                LOGGER.error("controller注解类信息加载失败！", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
