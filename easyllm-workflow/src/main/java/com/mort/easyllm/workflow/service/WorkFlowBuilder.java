package com.mort.easyllm.workflow.service;

import com.mort.easyllm.common.exception.PropertiesParseException;
import com.mort.easyllm.workflow.pojo.dto.PageNodeLinearDTO;
import com.mort.easyllm.workflow.Node.chainNode.BranchInfoNode;
import com.mort.easyllm.workflow.Node.chainNode.InfoNode;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class WorkFlowBuilder {

    private void initBranchInfoNode(PageNodeLinearDTO currentPageNode, LinkedHashMap<String, PageNodeLinearDTO> pageNodeMap) {
        if (currentPageNode.getDefaultNodeName() == null
                || currentPageNode.getDefaultNodeName().isEmpty()
                || !pageNodeMap.containsKey(currentPageNode.getDefaultNodeName())) {
            throw new PropertiesParseException("分支节点必须有兜底", currentPageNode.getNodeName());
        }
        //初始化本节点
        BranchInfoNode branchInfoNode = BranchInfoNode.branchInfoNodeBuilder()
                .nodeName(currentPageNode.getNodeName())
                .nodeType(currentPageNode.getNodeType())
                .isBranchNode(currentPageNode.getIsBranchNode())
                .properties(currentPageNode.getProperties())
                .defaultNodeName(currentPageNode.getDefaultNodeName())
                .build();
        currentPageNode.setInfoNode(branchInfoNode);
        // 父节点配置
        for (String fatherNodeName : currentPageNode.getFatherNodeNameList()) {
            PageNodeLinearDTO fatherPageNode = pageNodeMap.get(fatherNodeName);
            if (fatherPageNode.getInfoNode() instanceof BranchInfoNode) {
                //注册到父节点
                ((BranchInfoNode) fatherPageNode.getInfoNode()).getNextNodeMap().put(currentPageNode.getNodeName(), branchInfoNode);
                return;
            }
            fatherPageNode.getInfoNode().setNextNode(branchInfoNode);
        }
    }

    private void initInfoNode(PageNodeLinearDTO currentPageNode, LinkedHashMap<String, PageNodeLinearDTO> pageNodeMap) {
        InfoNode infoNode = InfoNode.builder()
                .nodeName(currentPageNode.getNodeName())
                .nodeType(currentPageNode.getNodeType())
                .isBranchNode(currentPageNode.getIsBranchNode())
                .properties(currentPageNode.getProperties())
                .build();
        currentPageNode.setInfoNode(infoNode);

        for (String fatherNodeName : currentPageNode.getFatherNodeNameList()) {
            PageNodeLinearDTO fatherPageNode = pageNodeMap.get(fatherNodeName);
            if (fatherPageNode.getInfoNode() instanceof BranchInfoNode) {
                ((BranchInfoNode) fatherPageNode.getInfoNode()).getNextNodeMap().put(currentPageNode.getNodeName(), infoNode);
                return;
            }
            fatherPageNode.getInfoNode().setNextNode(infoNode);
        }
    }

    private LinkedHashMap<String, PageNodeLinearDTO> listToMap(List<PageNodeLinearDTO> frontNodeLinear) {
        LinkedHashMap<String, PageNodeLinearDTO> nodeMap = new LinkedHashMap<>();
        for (PageNodeLinearDTO frontNode : frontNodeLinear) {
            nodeMap.put(frontNode.getNodeName(), frontNode);
        }
        return nodeMap;
    }

    public InfoNode buildWorkFlow(List<PageNodeLinearDTO> pageNodeList) {
        LinkedHashMap<String, PageNodeLinearDTO> pageNodeMap = listToMap(pageNodeList);
        Iterator<Map.Entry<String, PageNodeLinearDTO>> mapIterator = pageNodeMap.entrySet().iterator();
        //首节点处理
        PageNodeLinearDTO firstFrontNode = mapIterator.next().getValue();
        InfoNode startNode = InfoNode.builder()
                .nodeName(firstFrontNode.getNodeName())
                .nodeType(firstFrontNode.getNodeType())
                .isBranchNode(firstFrontNode.getIsBranchNode())
                .properties(firstFrontNode.getProperties())
                .build();
        firstFrontNode.setInfoNode(startNode);

        while (mapIterator.hasNext()) {
            PageNodeLinearDTO tempPageNode = mapIterator.next().getValue();
            if (tempPageNode.getIsBranchNode()) {
                initBranchInfoNode(tempPageNode, pageNodeMap);
                continue;
            }
            initInfoNode(tempPageNode, pageNodeMap);
        }
        return startNode;
    }

//    public void dfsTraversal(@NotNull PageNodeDTO frontNode, InfoNode infoNode) {
//        if (frontNode.getNextNode() == null) {
//            return;
//        }
//        //选择分支
//        if (frontNode.getIsBranchNode()) {
//            for (PageNodeDTO nextNode : frontNode.getNextNodes()) {
//
//            }
//            return;
//        }
//        //常规分支
////        if (frontNode.getNextNode().getIsBranchNode()) {
////            infoNode.setNextNode(initInfoNode());
////        } else {
////            infoNode.setNextNode();
////        }
////        dfsTraversal(frontNode.getNextNode(), infoNode.getNextNode());
//    }

//    public BranchInfoNode initBranchInfoNode(FrontNodeDTO frontNode) {
//        return BranchInfoNode.branchInfoNodeBuilder()
//                .nodeName(frontNode.getNextNode().getNodeName())
//                .nodeType(frontNode.getNextNode().getNodeType())
//                .isBranchNode(frontNode.getNextNode().getIsBranchNode())
//                .properties(frontNode.getNextNode().getProperties())
//                .build();
//    }
//
//    public InfoNode initInfoNode(FrontNodeDTO frontNode) {
//        return InfoNode.builder()
//                .nodeName(frontNode.getNextNode().getNodeName())
//                .nodeType(frontNode.getNextNode().getNodeType())
//                .isBranchNode(frontNode.getNextNode().getIsBranchNode())
//                .properties(frontNode.getNextNode().getProperties())
//                .build();
//    }


}
