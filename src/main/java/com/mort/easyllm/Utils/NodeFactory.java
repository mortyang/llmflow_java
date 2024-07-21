package com.mort.easyllm.Utils;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.Annotation.Node;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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

    private static final Map<String, Function<Object, Object>> NODE_CREATORS = new ConcurrentHashMap<>();

    static {
        scanAndRegisterNodes();
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
            throw new RuntimeException("Node创建失败,nodeName:" + nodeName, e);
        }
    }


    private static void scanAndRegisterNodes() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("com.mort.easyllm.Node"))
        );
        Set<Class<?>> nodeClasses = reflections.getTypesAnnotatedWith(Node.class);
        for (Class<?> nodeClass : nodeClasses) {
            Node nodeAnnotation = nodeClass.getAnnotation(Node.class);
            String nodeName = nodeAnnotation.NodeName();
            if (nodeName == null || nodeName.isEmpty()) {
                throw new IllegalArgumentException(nodeClass.getName() + "-载入失败，请为手动为该Node指定名称");
            }
            NODE_CREATORS.put(nodeName, createNodeCreator(nodeClass));
        }
        log.info("完成包的扫描与初始化，找到了如下包{}", nodeClasses);
    }


    private static Function<Object, Object> createNodeCreator(Class<?> nodeClass) {
        return initParameter -> {
            try {
                //预注册，避免运行时使用反射,提高构建效率
                Constructor<?> constructor = nodeClass.getConstructor(JSONObject.class);
                return constructor.newInstance((JSONObject) initParameter);
            } catch (ClassCastException e) {
                throw new RuntimeException("initParameter 必须是 JSONObject 类型", e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("类" + nodeClass.getName() + "必须有一个以Object为参数的构造方法,注册失败", e);
            } catch (Exception e) {
                throw new RuntimeException("Node注册失败: " + nodeClass.getSimpleName(), e);
            }
        };
    }

}
