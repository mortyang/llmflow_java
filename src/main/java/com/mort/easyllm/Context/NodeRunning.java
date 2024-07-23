package com.mort.easyllm.Context;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mort
 */
@Getter
public class NodeRunning{

    private final Map<String,String> nodeOutput = new HashMap<>();

    private final List<String> historyMessages = new ArrayList<>();

}
