import {cloneDeep} from 'lodash';

const propertiesStorage = {
    http: {
        url: '',
        method: 'get',
        body: '',
        headers: []
    },
    judge: {
        isBranchNode: true,
        defaultNodeName: "",
        nextNodeNameList: [],
        conditions: [
            {
                label: '默认标题',
                condition: '',
                nextNode: '',
                handle: "source_0",
                closeable: false
            },
        ],
    },
    llmJudge: {
        isLLMNode: true,
        intentions: [{
            intention: ""
        }],
    },
    llm: {
        isLLMNode: true,
        sysMsg: concatenatingStringGetter()
    },
    parameterExtraction: {
        isLLMNode: true,
        useSequentialCheck: true,
        extractionParameterList: [{
            parameterName: "demo",
            description: "",
            parameterType: "",
            required: false,
        }],
    }
}

export const newNodePropertiesGetter = {
    httpGetter: () => {
        return cloneDeep(propertiesStorage.http)
    },
    llmJudgeGetter: () => {
        return cloneDeep(propertiesStorage.llmJudge)
    },
    llmGetter: () => {
        return cloneDeep(propertiesStorage.llm)
    },
    judge: {
        judgeGetter: () => {
            return cloneDeep(propertiesStorage.judge)
        },
        conditionsGetter: () => {
            return cloneDeep(propertiesStorage.judge.conditions[0])
        }
    }
}

export function concatenatingStringGetter() {
    return {
        text: ""
    }
}

export function getPropertiesTemplate(nodeType) {
    let ret = cloneDeep(propertiesStorage[nodeType]);
    //公共
    ret.hasProperties = false
    ret.input = concatenatingStringGetter()
    ret.hasProperties = false
    ret.fatherNodeNameList = []
    ret.nextNodeName = ""
    ret.defaultNodeName = ""
    ret.nextNodeNameList = []

    //特殊节点配置处理
    if (!ret.isBranchNode) {
        ret.isBranchNode = false
    }
    if (ret.isLLMNode) {
        ret.llmProperties = {
            modelName: "qwen-turbo",
            apiKey: "sk-40a4dab15f134fa0b2c5a4c7a7a44a1b",
            temperature: 0.7,
            maxTokens: 1700,
            topP: 0.8,
            enableSearch: false,
            useContext: false,
            contextLength: 20
        }
    }

    return ret;
}

