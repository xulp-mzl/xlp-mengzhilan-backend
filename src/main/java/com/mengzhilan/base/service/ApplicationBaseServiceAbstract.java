package com.mengzhilan.base.service;

import com.mengzhilan.base.Version;
import org.xlp.db.sql.UpdateSQL;
import org.xlp.mv.BaseService;

/**
 * Create by xlp on 2022/6/12
 */
public abstract class ApplicationBaseServiceAbstract extends BaseService {
    @Override
    public <T> boolean update(T bean) {
        UpdateSQL<T> updateSQL = new UpdateSQL<>(bean, false);
        if (bean instanceof Version){
            Version version = (Version) bean;
            updateSQL.andEq(version.getVersionFiledName(), version.getVersion());
        }
        return update(updateSQL);
    }
}
