package com.mort.easyllm.main.mapper.po;

import lombok.Builder;
import lombok.Data;

@Data
public class WorkFlowPo {

    private Integer id;

    private String workFlowName;

    private String permissionGroup;

    private byte[] runnableObjectByte;

    private String frontJson;

    private Boolean enabled;

    public WorkFlowPo(int id, String workFlowName, Boolean enabled) {
        this.id = id;
        this.workFlowName = workFlowName;
        this.enabled = enabled;
    }

    @Builder
    public WorkFlowPo(int id, String workFlowName, String permissionGroup, byte[] runnableObjectByte, String frontJson, Boolean enabled) {
        this.id = id;
        this.workFlowName = workFlowName;
        this.permissionGroup = permissionGroup;
        this.runnableObjectByte = runnableObjectByte;
        this.frontJson = frontJson;
        this.enabled = enabled;
    }

    public WorkFlowPo(Integer id, String workFlowName, String permissionGroup, byte[] runnableObjectByte) {
        this.id = id;
        this.workFlowName = workFlowName;
        this.permissionGroup = permissionGroup;
        this.runnableObjectByte = runnableObjectByte;
    }


}
