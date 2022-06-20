package com.mengzhilan.base.service;

import com.mengzhilan.base.CreatedInfo;
import com.mengzhilan.base.Version;
import org.xlp.db.sql.UpdateSQL;
import org.xlp.mv.BaseService;

import java.util.Date;

/**
 * Create by xlp on 2022/6/12
 */
public abstract class ApplicationBaseServiceAbstract extends BaseService {
    @Override
    public <T> boolean update(T bean) {
        if (bean instanceof CreatedInfo && ((CreatedInfo) bean).getUpdateTime() == null){
            ((CreatedInfo) bean).setUpdateTime(new Date());
        }
        UpdateSQL<T> updateSQL = new UpdateSQL<>(bean, false);
        if (bean instanceof Version){
            Version version = (Version) bean;
            updateSQL.andEq(version.getVersionFiledName(), version.getVersion());
        }
        return update(updateSQL);
    }

    @Override
    public <T> Object save(T bean) {
        if (bean instanceof CreatedInfo){
            Date date = new Date();
            ((CreatedInfo) bean).setUpdateTime(date);
            ((CreatedInfo) bean).setCreateTime(date);
        }
        return super.save(bean);
    }
}
