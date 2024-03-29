package com.mengzhilan.view;

import org.xlp.javabean.annotation.Bean;
import org.xlp.javabean.annotation.FieldName;

import java.io.Serializable;
import java.util.List;

/**
 * Create by xlp on 2022/7/24
 *
 * 之来源接口
 */
@FunctionalInterface
public interface IValueFrom {
    /**
     * 获取可选值
     *
     * @return
     */
    List<OptionValue> getOptionValue();

    /**
     * 可选值类
     */
    @Bean
    class OptionValue implements Serializable {
        private static final long serialVersionUID = 2662360657603152181L;
        /**
         * 值
         */
        @FieldName
        private Object value;

        /**
         * 显示值
         */
        @FieldName
        private String label;

        /**
         * 描述
         */
        @FieldName
        private String description;

        public OptionValue(){}

        public OptionValue(Object value, String label) {
            this.value = value;
            this.label = label;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "OptionValue{" +
                    "value='" + value + '\'' +
                    ", label='" + label + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}
