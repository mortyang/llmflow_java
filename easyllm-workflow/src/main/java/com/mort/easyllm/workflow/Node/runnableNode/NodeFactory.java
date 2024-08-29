package com.mort.easyllm.workflow.Node.runnableNode;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.workflow.annotation.node.Node;
import com.mort.easyllm.workflow.annotation.node.NodeProperties;
import com.mort.easyllm.workflow.annotation.node.PropertiesInject;
import com.mort.easyllm.common.utils.ValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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

    private NodeFactory() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }


    /**
     * 节点注册Map，利用函数式接口减少注册map使用
     *
     * @Author Mort
     * @Date 2024-07-22
     */
    private static final Map<String, Function<JSONObject, Object>> NODE_CREATORS = new ConcurrentHashMap<>();


//todo错误处理在此处
    public static void scanAndRegisterNodes() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("com.mort.easyllm.workFlow.Node"))
        );

        Set<Class<?>> nodeClasses = reflections.getTypesAnnotatedWith(Node.class);
        Set<Class<?>> nodePropertiesClasses = reflections.getTypesAnnotatedWith(NodeProperties.class);

        Map<String, Class<?>> nodePropertiesMap = new HashMap<>();
        for (Class<?> nodePropertiesClass : nodePropertiesClasses) {
            nodePropertiesMap.put(nodePropertiesClass.getAnnotation(NodeProperties.class).nodeType(),
                    nodePropertiesClass);
        }
        for (Class<?> nodeClass : nodeClasses) {
            String nodeName = nodeClass.getAnnotation(Node.class).nodeType();
            if (nodeName == null || nodeName.isEmpty()) {
                throw new IllegalArgumentException(nodeClass.getName() + "-载入失败，请为手动为该Node指定名称");
            }
            NODE_CREATORS.put(nodeName, createNodeCreator(nodeClass, nodePropertiesMap.get(nodeName)));
        }
        log.info("完成包的扫描与初始化，找到了如下包{}", nodeClasses);
    }


    // TODO：配置类变量和注入构造函数检查应该于启动构建时即完成
    private static Function<JSONObject, Object> createNodeCreator(Class<?> nodeClass, Class<?> nodePropertiesClass) {
        return (initParameter) -> {
            try {
                // 获取配置以及配置类
                if (nodePropertiesClass == null) {
                    throw new RuntimeException("未找到对应的配置类,node类名:" + nodeClass.getName());
                }
                Object properties = initParameter.to(nodePropertiesClass);
                ValidateUtil.validateAndThrow(properties); //校验

                Constructor<?>[] constructors = nodeClass.getDeclaredConstructors();
                for (Constructor<?> constructor : constructors) {
                    // 处理符合条件的构造函数
                    if (constructor.isAnnotationPresent(PropertiesInject.class)) {
                        if (constructor.getParameterCount() == 1 && constructor.getParameterTypes()[0].equals(nodePropertiesClass)) {
                            return constructor.newInstance(properties);
                        }
                    }
                }

                // 如果没有找到构造函数，尝试通过反射获取并设置标注了 @PropertiesInject 注解的字段
                Object instance = nodeClass.getDeclaredConstructor().newInstance(); // 创建一个默认实例

                boolean propertyInjected = false;
                Field[] fields = nodeClass.getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(PropertiesInject.class)) {
                        if (field.getType().equals(nodePropertiesClass)) {
                            field.setAccessible(true);
                            field.set(instance, properties);
                            propertyInjected = true;
                        } else {
                            throw new RuntimeException("错误的properties字段类型，注入失败");
                        }
                    }
                }

                if (!propertyInjected) {
                    throw new RuntimeException("配置注入失败，没有找到符合条件的构造函数或变量");
                }
                return instance;
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.getTargetException());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }


//    private static List<String> getPropertiesField(Class<?> clazz) {
//        List<String> fieldNames = new ArrayList<>();
//        while (clazz != null) {
//            fieldNames.addAll(Arrays.stream(clazz.getDeclaredFields())
//                    .map(Field::getName)
//                    .toList());
//            clazz = clazz.getSuperclass();
//        }
//        return fieldNames;
//    }


    public static <T> T getNodeByName(String nodeName, JSONObject initParameter, Class<T> clazz) {
        Function<JSONObject, Object> creator = NODE_CREATORS.get(nodeName);
        if (creator == null) {
            throw new IllegalArgumentException("没有找到对应的node: " + nodeName);
        }
        Object node = null;
        try {
            node = creator.apply(initParameter);
            return clazz.cast(node);
        } catch (ClassCastException e) {
            throw new ClassCastException("类型不匹配,需要的类型：" + clazz.getName() + "，实际类型：" + node.getClass());
        } catch (Exception e) {
            throw new RuntimeException(String.format("节点：%s注册失败，失败原因：%s",nodeName,e.getMessage()), e);
        }
    }

}
