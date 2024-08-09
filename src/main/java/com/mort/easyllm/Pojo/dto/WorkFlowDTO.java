package com.mort.easyllm.Pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class WorkFlowDTO {

    private Integer id;

    private String workFlowName;

    private List<PageNodeLinearDTO> workFlowList;

    private String frontJson;

}
