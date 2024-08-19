package com.mort.easyllm.workflow.Node.runnableNode.variableHandlerNode.properties;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.workflow.parameter.ConcatenatingString;
import com.mort.easyllm.workflow.parameter.ExtractionParameter;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ParameterExtractionNodeProperties {

    private List<ExtractionParameter> extractionParameterList;

    private ConcatenatingString sysMsg;

    private ConcatenatingString input;

    private Integer useContext;

    private Integer contextLength;


    public static ParameterExtractionNodeProperties jsonObjectConvert(JSONObject properties) {
        return ParameterExtractionNodeProperties.builder()
                .extractionParameterList(properties.getList("extractionParameterList", ExtractionParameter.class))
                .useContext(properties.getInteger("useContext"))
                .sysMsg(new ConcatenatingString(properties.getString("sysMsg")))
                .input(new ConcatenatingString(properties.getString("input")))
                .build();
    }


}
