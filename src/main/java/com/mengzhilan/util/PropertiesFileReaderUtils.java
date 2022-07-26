package com.mengzhilan.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xlp.utils.XLPCharsetUtil;
import org.xlp.utils.io.XLPIOUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

/**
 * Create by xlp on 2022/7/25
 *
 * Properties 配置文件读取工具类
 */
public class PropertiesFileReaderUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesFileReaderUtils.class);

    /**
     * 读取配置文件类容
     *
     * @param fileName 文件名称，相对于classes路径
     */
    public static Properties load(String fileName){
        Properties properties = new Properties();
        InputStream inputStream = null;
        Reader reader = null;
        try {
            inputStream = PropertiesFileReaderUtils.class.getClassLoader()
                    .getResourceAsStream(fileName);
            byte[] bytes = XLPIOUtil.IOToByteArray(inputStream);
            inputStream = new ByteArrayInputStream(bytes);
            //获取文件编码
            String charsetName = XLPCharsetUtil.gainCharsetName(bytes);

            //处理中文乱码问题
            reader = XLPIOUtil.getReader(inputStream, charsetName);
            if (LOGGER.isDebugEnabled())
                LOGGER.debug("加载bean映射配置文件开始： 配置文件名为：" + fileName);
            properties.load(reader);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("加载bean映射配置文件完成");
                LOGGER.debug("加载的数据为：" + properties);
            }
        } catch (IOException e) {
            if (LOGGER.isErrorEnabled())
                LOGGER.error("加载bean映射配置文件失败！", e);
        }
        XLPIOUtil.closeInputStream(inputStream);
        XLPIOUtil.closeReader(reader);
        return properties;
    }
}
