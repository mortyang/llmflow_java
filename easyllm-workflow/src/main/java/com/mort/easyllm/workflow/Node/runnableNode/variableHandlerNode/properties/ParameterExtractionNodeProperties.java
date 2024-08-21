package com.mort.easyllm.workflow.Node.runnableNode.variableHandlerNode.properties;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;
import com.mort.easyllm.common.annotation.node.NodeProperties;
import com.mort.easyllm.workflow.parameter.ConcatenatingString;
import com.mort.easyllm.workflow.parameter.ExtractionParameter;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@NodeProperties(nodeType = "ParameterExtractionNode")
public class ParameterExtractionNodeProperties {

    @JSONField(name = "extractionParameterList")
    private List<ExtractionParameter> extractionParameterList;

    @JSONField(name = "sysMsg")
    private ConcatenatingString sysMsg;

    @JSONField(name = "input")
    private ConcatenatingString input;

    @JSONField(name = "useContext")
    private Integer useContext;

    @JSONField(name = "contextLength")
    private Integer contextLength;


}
