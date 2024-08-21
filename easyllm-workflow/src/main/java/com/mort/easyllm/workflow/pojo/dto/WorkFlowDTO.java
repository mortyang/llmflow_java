package com.mort.easyllm.workflow.pojo.dto;

import com.mort.easyllm.common.parameter.Message;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class WorkFlowDTO {

    private Integer id;

    private String workFlowName;

    private List<PageNodeLinearDTO> workFlowList;

    private String frontJson;

    private List<Message> messages;

    private HashMap<String, String> sessionVariables;

}
