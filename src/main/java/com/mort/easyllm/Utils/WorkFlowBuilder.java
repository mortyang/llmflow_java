package com.mort.easyllm.Utils;

import com.mort.easyllm.Node.InfoNode.BranchInfoNode;
import com.mort.easyllm.Node.InfoNode.InfoNode;
import com.mort.easyllm.pojo.dto.PageNodeLinearDTO;
import com.mort.easyllm.pojo.dto.WorkFlowDTO;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class WorkFlowBuilder {

    public InfoNode buildWorkFlow(List<PageNodeLinearDTO> pageNodeList) {
        LinkedHashMap<String, PageNodeLinearDTO> pageNodeMap  = mapConverter(pageNodeList);
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


    private void initBranchInfoNode(PageNodeLinearDTO pageNode, LinkedHashMap<String, PageNodeLinearDTO> frontNode) {
        BranchInfoNode branchInfoNode = BranchInfoNode.branchInfoNodeBuilder()
                .nodeName(pageNode.getNodeName())
                .nodeType(pageNode.getNodeType())
                .isBranchNode(pageNode.getIsBranchNode())
                .properties(pageNode.getProperties())
                .build();
        pageNode.setInfoNode(branchInfoNode);
        PageNodeLinearDTO fatherNode = frontNode.get(pageNode.getFatherNodeName());
        if (fatherNode.getInfoNode() instanceof BranchInfoNode) {
            ((BranchInfoNode) fatherNode.getInfoNode()).getNextNodeMap().put(pageNode.getNodeName(), branchInfoNode);
            return;
        }
        fatherNode.getInfoNode().setNextNode(branchInfoNode);
    }

    private void initInfoNode(PageNodeLinearDTO pageNode, LinkedHashMap<String, PageNodeLinearDTO> frontNode) {
        InfoNode infoNode = InfoNode.builder()
                .nodeName(pageNode.getNodeName())
                .nodeType(pageNode.getNodeType())
                .isBranchNode(pageNode.getIsBranchNode())
                .properties(pageNode.getProperties())
                .build();
        pageNode.setInfoNode(infoNode);

        PageNodeLinearDTO fatherNode = frontNode.get(pageNode.getFatherNodeName());
        if (fatherNode.getInfoNode() instanceof BranchInfoNode) {
            ((BranchInfoNode) fatherNode.getInfoNode()).getNextNodeMap().put(pageNode.getNodeName(), infoNode);
            return;
        }
        fatherNode.getInfoNode().setNextNode(infoNode);
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

    public LinkedHashMap<String, PageNodeLinearDTO> mapConverter (List<PageNodeLinearDTO> frontNodeLinear){
        LinkedHashMap<String, PageNodeLinearDTO> nodeMap = new LinkedHashMap<>();
        for(PageNodeLinearDTO frontNode : frontNodeLinear){
            nodeMap.put(frontNode.getNodeName(),frontNode);
        }
        return nodeMap;
    }

}
