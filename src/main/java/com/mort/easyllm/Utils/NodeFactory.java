package com.mort.easyllm.Utils;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.Annotation.Node.Node;
import com.mort.easyllm.Annotation.Node.NodePropertiesField;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;


/**
 * @author Mort
 */
@Slf4j
public class NodeFactory {

//    public static final Map<String, Class<?>> NODEMAP = new HashMap<>();
//
//    // 注册类到工厂
//    static {
//        //RunnableNode
//        NODEMAP.put("HttpNode", HttpNodeImpl.class);
//
//        //JudgeNode
//        NODEMAP.put("NormalJudgeNode", NormalJudgeNodeImpl.class);
//    }
//
//    public static Object getRunableNodeByName(String className,Object initParameter) {
//        try {
//            Class<?> clazz = NODEMAP.get(className);
//            if (clazz != null) {
//                return clazz.getConstructor(Object.class).newInstance(initParameter);
//            } else {
//                throw new IllegalArgumentException("No class registered with name: " + className);
//            }
//        }catch (NoSuchMethodException  e){
//            throw new RuntimeException();
//        }
//        catch (Exception e) {
//            log.error("获取类出错",e);
//            throw new RuntimeException("Failed to create instance of: " + className, e);
//        }
//    }

    /**
     * 节点注册Map，利用函数式接口快速创建对象
     *
     * @Author Mort
     * @Date 2024-07-22
     */
    private static final Map<String, Function<Object, Object>> NODE_CREATORS = new ConcurrentHashMap<>();

    /**
     * 前端配置Map，负责存储对应node的配置信息返回给前端
     *
     * @Author Mort
     * @Date 2024-07-22
     */
    public static final Map<String, List<String>> FRONT_PAGE_CONFIG = new HashMap<>();

//    static {
//        scanAndRegisterNodes();
//    }

    public static void scanAndRegisterNodes() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("com.mort.easyllm.Node"))
        );

        Set<Class<?>> nodeClasses = reflections.getTypesAnnotatedWith(Node.class);
        Set<Class<?>> nodePropertiesClasses = reflections.getTypesAnnotatedWith(NodePropertiesField.class);

        Map<String, List<String>> nodePropertiesMap = new HashMap<>();
        for (Class<?> nodePropertiesClass : nodePropertiesClasses) {
            String nodePropertiesName = nodePropertiesClass.getAnnotation(NodePropertiesField.class).nodeName();
            List<String> nodeProperties = getPropertiesField(nodePropertiesClass);
            nodePropertiesMap.put(nodePropertiesName, nodeProperties);
        }

        for (Class<?> nodeClass : nodeClasses) {
            String nodeName = nodeClass.getAnnotation(Node.class).nodeName();
            if (nodeName == null || nodeName.isEmpty()) {
                throw new IllegalArgumentException(nodeClass.getName() + "-载入失败，请为手动为该Node指定名称");
            }
            NODE_CREATORS.put(nodeName, createNodeCreator(nodeClass));
            FRONT_PAGE_CONFIG.put(nodeName, nodePropertiesMap.get(nodeName));
        }
        log.info("完成包的扫描与初始化，找到了如下包{}", nodeClasses);
    }


    private static Function<Object, Object> createNodeCreator(Class<?> nodeClass) {
        return initParameter -> {
            try {
                //预注册，避免运行时使用反射,提高构建效率
                Constructor<?> constructor = nodeClass.getConstructor(JSONObject.class);
                return constructor.newInstance(initParameter);
            } catch (ClassCastException e) {
                throw new RuntimeException("initParameter 必须是 JSONObject 类型");
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("类" + nodeClass.getName() + "必须有一个以JSONObject为参数的构造方法,注册失败");
            } catch (Exception e) {
                if (e.getCause() instanceof NullPointerException) {
                    throw new RuntimeException(nodeClass.getSimpleName() + "的properties中@NonNull的参数未配置");
                }
                throw new RuntimeException("Node注册失败: " + nodeClass.getSimpleName() + ",错误原因：" + e.getMessage(), e);
            }
        };
    }


    private static List<String> getPropertiesField(Class<?> clazz) {
        List<String> fieldNames = new ArrayList<>();
        while (clazz != null) {
            fieldNames.addAll(Arrays.stream(clazz.getDeclaredFields())
                    .map(java.lang.reflect.Field::getName)
                    .toList());
            clazz = clazz.getSuperclass();
        }
        return fieldNames;
    }


    public static <T> T getNodeByName(String nodeName, JSONObject initParameter, Class<T> clazz) {
        Function<Object, Object> creator = NODE_CREATORS.get(nodeName);
        if (creator == null) {
            throw new IllegalArgumentException("没有找到对应的node: " + nodeName);
        }
        Object node = creator.apply(initParameter);
        try {
            return clazz.cast(node);
        } catch (ClassCastException e) {
            throw new ClassCastException("类型不匹配,需要的类型：" + clazz.getName() + "，实际类型：" + node.getClass());
        } catch (Exception e) {
            throw new RuntimeException("Node注册失败: " + clazz.getSimpleName() + ",错误原因：" + e.getMessage(), e);
        }
    }

}
