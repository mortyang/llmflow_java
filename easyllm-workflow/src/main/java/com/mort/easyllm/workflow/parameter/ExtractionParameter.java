package com.mort.easyllm.workflow.parameter;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Data
public class ExtractionParameter {

    private String parameterName;

    private String description;

    private ParameterType parameterType;

    private Boolean Required;


    @JSONCreator
    public ExtractionParameter(@JSONField(name = "parameterName") String parameterName,
                               @JSONField(name = "description") String description,
                               @JSONField(name = "parameterType") String parameterType,
                               @JSONField(name = "required") Boolean required) {
        this.parameterName = parameterName;
        this.description = description;
        this.parameterType = ParameterType.fromValue(parameterType);
        Required = required;
    }


    @Getter
    public enum ParameterType {

        STRING(String.class, "String") {
            @Override
            public boolean validate(String value) {
                return true;
            }
        },
        INTEGER(Integer.class, "Integer") {
            @Override
            public boolean validate(String value) {
                if (value == null) {
                    return false;
                }
                try {
                    Integer.parseInt(value);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        };

        private final Class<?> type;
        private final String typeName;

        ParameterType(Class<?> type, String typeName) {
            this.type = type;
            this.typeName = typeName;
        }

        private static final Map<String, ParameterType> VALUE_MAP = new HashMap<>();

        static {
            for (ParameterType type : values()) {
                VALUE_MAP.put(type.typeName, type);
            }
        }


        public static ParameterType fromValue(String value) {
            ParameterType type = VALUE_MAP.get(value);
            if (type == null) {
                throw new IllegalArgumentException("No enum constant for value:" + value);
            }
            return type;
        }

        public abstract boolean validate(String value);

    }


}
