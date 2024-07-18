package com.mort.easyllm.pojo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
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
    private JsonNode properties;

    private String fatherNodeName;
    private String nextNodeName;
    private List<String> nextNodesName;

    private InfoNode infoNode;

    @JsonCreator
    public PageNodeLinearDTO(
            @JsonProperty("nodeName") @NonNull String nodeName,
            @JsonProperty("nodeType") @NonNull String nodeType,
            @JsonProperty("isBranchNode") @NonNull Boolean isBranchNode,
            @JsonProperty("properties") JsonNode properties,
            @JsonProperty("fatherNodeName") String fatherNodeName,
            @JsonProperty("nextNodeName") String nextNodeName,
            @JsonProperty("nextNodesName") List<String> nextNodesName,
            @JsonProperty("infoNode") InfoNode infoNode) {
        this.nodeName = nodeName;
        this.nodeType = nodeType;
        this.isBranchNode = isBranchNode;
        this.properties = properties;
        this.fatherNodeName = fatherNodeName;
        this.nextNodeName = nextNodeName;
        this.nextNodesName = nextNodesName;
        this.infoNode = infoNode;
    }


}
