package com.mort.easyllm.workFlow.parameter;

import com.mort.easyllm.workFlow.context.GlobalVariables;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 用于支持含动态变量的文本
 * 传入的字符串会被拆分为文本和变量列表，获取时将变量列表的变量替换为对应全局变量中的值
 * 若出现无法找到的变量会按照字符串返回
 * @Author Mort
 * @Date 2024-08-13
 */
public class ConcatenatingString {


    private static class ExtractResult {
        List<String> textParts;
        List<String> variableParts;

        private ExtractResult() {
            this.textParts = new ArrayList<>();
            this.variableParts = new ArrayList<>();
        }
    }

    private final ExtractResult extractResult;


    public ConcatenatingString(String str) {
          this.extractResult = extractPatterns(str);
    }


    private String getString() {
        Iterator<String> iterator = extractResult.variableParts.iterator();
        StringBuilder str = new StringBuilder();
        for (String text : extractResult.textParts) {
            str.append(text);
            if (iterator.hasNext()) {
                String var = iterator.next();
                str.append(GlobalVariables.getGlobalVariables().getOrDefault(var, "/${" + var + "}"));
            }
        }
        if (iterator.hasNext()) {
            String var = iterator.next();
            str.append(GlobalVariables.getGlobalVariables().getOrDefault(var, "/${" + var + "}"));
        }
        return str.toString();
    }


    private static ExtractResult extractPatterns(String input) {
        ExtractResult result = new ExtractResult();
        String pattern = "/${";
        int[] lps = computeLPSArray(pattern);
        int i = 0, j = 0;

        StringBuilder current = new StringBuilder();
        StringBuilder outside = new StringBuilder();
        boolean expectingClosingBrace = false;

        //为了实现交叉，对/${为首进行特殊处理
        if (input.startsWith(pattern)) {
            result.textParts.add("");
        }

        while (i < input.length()) {
            //开始在括号内搜寻
            if (expectingClosingBrace) {
                if (i + 2 < input.length() && input.charAt(i) == '/' && input.charAt(i + 1) == '$' && input.charAt(i + 2) == '{') {
                    outside.append(current);
                    result.textParts.set(result.textParts.size() - 1, outside.toString());
                    current = new StringBuilder();
                    j = 0;
                    i = i + 3;
                    continue;
                }
                if (input.charAt(i) == '}') {
                    result.variableParts.add(current.toString());
                    expectingClosingBrace = false;
                    outside = new StringBuilder();
                    //处理紧接着/${的情况
                    if (i + 3 < input.length() && input.charAt(i + 1) == '/' && input.charAt(i + 2) == '$' && input.charAt(i + 3) == '{') {
                        result.textParts.add("");
                    }
                } else {
                    current.append(input.charAt(i));
                }
                i++;
            //单字符成功匹配的情况
            } else if (j < pattern.length() && input.charAt(i) == pattern.charAt(j)) {
                outside.append(input.charAt(i));
                i++;
                j++;
                //kmp匹配成功
                if (j == pattern.length()) {
                    if (outside.length() > pattern.length()) {
                        result.textParts.add(outside.substring(0, outside.length() - pattern.length()));
                    }
                    expectingClosingBrace = true;
                    current = new StringBuilder();
                    j = 0;
                }
            //kmp匹配失败的情况
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    outside.append(input.charAt(i));
                    i++;
                }
            }
        }

        if (!outside.isEmpty()) {
            result.textParts.add(outside.toString());
        }
        return result;
    }

    private static int[] computeLPSArray(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0;
        int i = 1;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }


//    public static void main(String[] args) {
//        String testString = "你好/${qwe}用/${户/${另一个{嵌套}/${期待}";
//        ConcatenatingString concatenatingString = new ConcatenatingString(testString);
//        System.out.println(concatenatingString.extractResult.textParts);
//        System.out.println(concatenatingString.extractResult.variableParts);
//        System.out.println(concatenatingString.getString());;
//    }

}
