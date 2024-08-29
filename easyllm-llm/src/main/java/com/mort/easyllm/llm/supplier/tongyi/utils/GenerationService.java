package com.mort.easyllm.llm.supplier.tongyi.utils;

import com.alibaba.dashscope.aigc.generation.Generation;

public class GenerationService {

    private static final ThreadLocal<Generation> generationThreadLocal = ThreadLocal.withInitial(Generation::new);

    public static Generation getGeneration() {
        return generationThreadLocal.get();
    }

    public static void removeGeneration() {
        generationThreadLocal.remove();
    }

}
