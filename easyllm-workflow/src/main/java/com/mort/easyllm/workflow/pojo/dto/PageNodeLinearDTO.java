package com.mort.easyllm.workflow.pojo.dto;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.workflow.Node.chainNode.InfoNode;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class PageNodeLinearDTO {

    /**
     * 节点名称，用户定义不允许为空
     */
    @NonNull
    private String nodeName;

    /**
     * 节点类型，指定Node类型
     */
    @NonNull
    private String nodeType;

    /**
     * 配置文件，初始化运行节点时二次解析
     */
    private JSONObject properties;

    /**
     * 父亲节点类型，指定Node名
     */
    private List<String> fatherNodeNameList;

    /**
     * 后续节点类型，指定Node名，分支节点不指定
     */
    private String nextNodeName;

    /**
     * 指向WorkFlow链上对应节点
     */
    private InfoNode infoNode;

    /**
     * 分支节点标记
     */
    @NonNull
    private Boolean isBranchNode;

    /**
     * 默认节点名，分支节点必传
     */
    private String defaultNodeName;

    /**
     * 后续节点表（包含default节点），分支节点必传，用于节点构建
     */
    private List<String> nextNodeNameList;



}
