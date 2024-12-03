const serverNodeMap = {
    judge: {
        serverNodeType: "NormalJudgeNode",
        propertiesConverter: judgePropertiesConvert
    },
    llm: {
        serverNodeType: "TongyiNode",
        propertiesConverter: llmPropertiesConvert
    },
    llmJudge: {
        serverNodeType: "IntentionJudgeNode",
        propertiesConverter: llmJudgePropertiesConvert
    },
    http: {
        serverNodeType: "HttpNode",
        propertiesConverter: httpPropertiesConvert
    },
    parameterExtraction: {
        serverNodeType: "ParameterExtractionNode",
        propertiesConverter: parameterExtractionConvert
    },
}


const serverNodeList = Object.keys(serverNodeMap).map(key => ({
    type: key,
    serverNodeName: serverNodeMap[key].serverNodeName,
    propertiesConverter: serverNodeMap[key].propertiesConverter
}));

function convertServerNode(nodeInfo) {
    if (serverNodeMap[nodeInfo.type] == null || serverNodeMap[nodeInfo.type].propertiesConverter == null) {
        console.error("error", serverNodeMap[nodeInfo.type])
        return null
    }
    return {
        nodeName: nodeInfo.id,
        nodeType: serverNodeMap[nodeInfo.type].serverNodeType,
        properties: serverNodeMap[nodeInfo.type].propertiesConverter(nodeInfo.data),
        fatherNodeNameList: nodeInfo.data.fatherNodeNameList,
        nextNodeName: nodeInfo.data.nextNodeName,
        isBranchNode: nodeInfo.data.isBranchNode,
        defaultNodeName: nodeInfo.data.defaultNodeName,
        nextNodeNameList: nodeInfo.data.nextNodeNameList
    }
}


function llmPropertiesConvert(obj) {
    return obj
}

function parameterExtractionConvert(obj) {
    return obj
}

function httpPropertiesConvert(obj) {
    const {headers, ...obj2} = obj
    obj2.headers = {}
    headers.forEach(header => {
        obj2.headers[header.key] = header.value
    })
    return obj2
}

function judgePropertiesConvert(obj) {
    const {conditions, ...obj2} = obj
    obj2.nodeNameToConditionMap = {}
    conditions.forEach(header => {
        obj2.nodeNameToConditionMap[header.nextNode] = header.condition
    })
    return obj2
}

function llmJudgePropertiesConvert(obj) {
    const {intentions, ...obj2} = obj
    obj2.intentions = []
    intentions.forEach(intention => {
        obj2.intentions.push(intention.intention)
    })
    return obj2
}


export default {
    serverNodeMap,
    serverNodeList,
    convertServerNode,
}