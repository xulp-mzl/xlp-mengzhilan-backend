package com.mengzhilan.entity;

import com.mengzhilan.base.MZBaseEntity;
import com.mengzhilan.base.Version;
import org.xlp.db.ddl.annotation.XLPIndex;
import org.xlp.db.ddl.type.IndexType;
import org.xlp.db.tableoption.annotation.XLPColumn;
import org.xlp.db.tableoption.annotation.XLPEntity;
import org.xlp.db.tableoption.xlpenum.DataType;
import org.xlp.javabean.annotation.Bean;
import org.xlp.javabean.annotation.FieldName;

/**
 * Create by xlp on 2021/3/28
 *
 * 用户信息表
 */
@Bean
@XLPEntity(tableName = "mz_user", descriptor = "用户信息表")
public class User extends MZBaseEntity implements Version {
    private static final long serialVersionUID = -1663355112060933818L;

    @FieldName
    @XLPColumn(columnName = "user_type", dataType = DataType.VARCHAR,
            length = 50, descriptor = "用户类型", maxLength = 50)
    private String userType;

    @FieldName
    @XLPColumn(columnName = "profile_url", dataType = DataType.VARCHAR,
            length = 500, descriptor = "用户头像url", maxLength = 500)
    private String profileUrl;

    @FieldName
    @XLPIndex(indexType = IndexType.UNIQUE)
    @XLPColumn(columnName = "user_no", dataType = DataType.VARCHAR,
            length = 255, descriptor = "用户登入id", maxLength = 255, isNull = false)
    private String userNo;

    @FieldName
    @XLPColumn(columnName = "nickname", dataType = DataType.VARCHAR,
            length = 255, descriptor = "用户昵称", maxLength = 255)
    private String nickname;

    @FieldName
    @XLPIndex
    @XLPColumn(columnName = "password", dataType = DataType.VARCHAR,
            length = 1024, descriptor = "用户密码", isNull = false)
    private String password;

    @FieldName
    @XLPColumn(columnName = "secretKey", dataType = DataType.VARCHAR,
            length = 1024, descriptor = "加密串")
    private String secretKey;

    @FieldName
    @XLPIndex
    @XLPColumn(columnName = "version", dataType = DataType.INT,
            descriptor = "版本信息", isNull = false, zeroFill = true)
    private int version;

    /**
     * 获取用户类型
     *
     * @return
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 设置用户类型
     *
     * @param userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * 获取用户头像url
     *
     * @return
     */
    public String getProfileUrl() {
        return profileUrl;
    }

    /**
     * 设置用户头像url
     *
     * @param profileUrl
     */
    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    /**
     * 获取用户登入账号
     *
     * @return
     */
    public String getUserNo() {
        return userNo;
    }

    /**
     * 设置用户登入账号
     *
     * @param userNo
     */
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    /**
     * 获取用户昵称
     *
     * @return
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置用户昵称
     *
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取用户密码（加密后的密码）
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码（加密后的密码）
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取加密串
     *
     * @return
     */
    public String getSecretKey() {
        return secretKey;
    }

    /**
     * 设置加密串
     *
     * @param secretKey
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public int getVersion() {
        return version;
    }

    /**
     * 设置版本信息
     *
     * @param version
     */
    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "User{" +
                "classId='" + getClassId() + '\'' +
                ", id='" + getId() + '\'' +
                ", userType='" + userType + '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                ", userNo='" + userNo + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", secretKey='" + secretKey + '\'' +
                "} ";
    }
}
