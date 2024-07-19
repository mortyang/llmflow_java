package com.mort.easyllm.Config;

import com.mort.easyllm.Node.BranchNode.NormalJudgeNodeImpl;
import com.mort.easyllm.Node.RunableNode.RequestNode.HttpNodeImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mort
 */
@Slf4j
public class NodeFactory {

    public static final Map<String, Class<?>> NODEMAP = new HashMap<>();

    // 注册类到工厂
    static {
        //RunnableNode
        NODEMAP.put("HttpNode", HttpNodeImpl.class);

        //JudgeNode
        NODEMAP.put("NormalJudgeNode", NormalJudgeNodeImpl.class);
    }

    //todo:增加对每个runnablenode和judgenode存在构造方法的校验
    public static Object getRunableNodeByName(String className,Object initParameter) {
        try {
            Class<?> clazz = NODEMAP.get(className);
            if (clazz != null) {
                return clazz.getConstructor(Object.class).newInstance(initParameter);
            } else {
                throw new IllegalArgumentException("No class registered with name: " + className);
            }
        } catch (Exception e) {
            log.error("获取类出错",e);
            throw new RuntimeException("Failed to create instance of: " + className, e);
        }
    }


}
