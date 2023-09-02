package com.tc.gen.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import java.beans.PropertyEditorSupport;

/**
 * 实现spring PropertyEditorRegistrar接口，处理action入参
 */
public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {

        registry.registerCustomEditor(String.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                if (StringUtils.isBlank(value)) {
                    setValue(null);                      //空白则转null，并且不进行转义
                    return;
                }
                setValue(value);
            }
        });
    }
}
