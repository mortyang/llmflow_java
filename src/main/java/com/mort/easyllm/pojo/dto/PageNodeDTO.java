package com.mort.easyllm.pojo.dto;

import com.fasterxml.jackson.databind.JsonNode;
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

    private JsonNode properties;

    private PageNodeDTO nextNode;

    private List<PageNodeDTO> nextNodes;

}
