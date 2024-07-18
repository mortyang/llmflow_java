package com.mort.easyllm.pojo.dto;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPObject;
import com.mort.easyllm.Node.InfoNode.InfoNode;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class PageNodeLinearDTO {

    @NonNull
    private String nodeName;
    @NonNull
    private String nodeType;
    @NonNull
    private Boolean isBranchNode;
    private JSONObject properties;

    private String fatherNodeName;
    private String nextNodeName;
    private List<String> nextNodesName;

    private InfoNode infoNode;

}
