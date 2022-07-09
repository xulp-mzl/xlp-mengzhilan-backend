package com.mengzhilan.form.value;

import org.xlp.javabean.annotation.Bean;
import org.xlp.javabean.annotation.FieldName;

import java.util.List;

/**
 * Create by xlp on 2022/7/9
 *
 * 表单值来源接口
 */
@FunctionalInterface
public interface Value {

    /**
     * 获取表单值
     *
     * @param modelId
     * @param fieldId
     * @return
     */
    List<ValueItem> getValueItems(String modelId, String fieldId);

    @Bean
    class ValueItem {
        /**
         * 显示值
         */
        @FieldName
        private String label;
        /**
         * 真正值
         */
        @FieldName
        private Object value;

        public ValueItem() {}

        public ValueItem(String label, Object value) {
            this.label = label;
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
