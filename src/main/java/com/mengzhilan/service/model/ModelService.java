package com.mengzhilan.service.model;

import org.xlp.mv.IBaseService;

/**
 * Create by xlp on 2022/6/12
 */
public interface ModelService extends IBaseService {
    /**
     * 隐藏不需要操作的模型
     */
    void hideModelByModelIds(String[] modelIds);
}
