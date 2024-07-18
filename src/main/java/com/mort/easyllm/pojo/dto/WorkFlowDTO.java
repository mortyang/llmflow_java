package com.mort.easyllm.pojo.dto;


import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Data
public class WorkFlowDTO {

    private String workFlowName;

    private PageNodeDTO workFlow;

    private List<PageNodeLinearDTO> workFLowList;

}
