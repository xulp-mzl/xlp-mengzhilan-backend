package com.mengzhilan.service.model;

import com.mengzhilan.form.FormInfoBean;
import org.xlp.mv.IBaseService;

/**
 * Create by xlp on 2022/6/12
 */
public interface ModelService extends IBaseService {
    /**
     * 隐藏不需要操作的模型
     */
    void hideModelByModelIds(String[] modelIds);

    /**
     * 修改模型信息
     *
     * @param formInfoBean
     */
    void updateModelByFormInfoBean(FormInfoBean formInfoBean);
}
