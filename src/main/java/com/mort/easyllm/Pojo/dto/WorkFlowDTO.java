package com.mort.easyllm.Pojo.dto;


import lombok.Data;

import java.util.List;

@Data
public class WorkFlowDTO {

    private String workFlowName;

    private PageNodeDTO workFlow;

    private List<PageNodeLinearDTO> workFLowList;

}
