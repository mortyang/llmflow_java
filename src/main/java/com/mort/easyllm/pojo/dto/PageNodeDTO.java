package com.mort.easyllm.pojo.dto;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPObject;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class PageNodeDTO {

    @NonNull
    private String nodeName;
    @NonNull
    private String nodeType;
    @NonNull
    private Boolean isBranchNode;

    private JSONObject properties;

    private PageNodeDTO nextNode;

    private List<PageNodeDTO> nextNodes;

}