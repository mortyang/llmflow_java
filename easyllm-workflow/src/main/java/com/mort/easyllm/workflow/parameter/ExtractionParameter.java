package com.mort.easyllm.workflow.parameter;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;

@Data
public class ExtractionParameter {

    private String parameterName;

    private String description;

    private ParameterType parameterType;

    private Integer Required;


    @Getter
    public enum ParameterType {
        STRING(String.class, "String"),
        INTEGER(Integer.class, "Integer"),
        ARRAY(ArrayList.class, "Array");

        private final Class<?> type;
        private final String name;

        ParameterType(Class<?> type, String name) {
            this.type = type;
            this.name = name;
        }

    }


}
