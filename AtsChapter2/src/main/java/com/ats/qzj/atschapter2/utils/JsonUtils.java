package com.ats.qzj.atschapter2.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /**
     * 从 JSON 字符串中提取指定字段的值（支持嵌套字段）
     *
     * @param jsonString JSON 字符串
     * @param fieldPath  字段路径（支持嵌套，以 '.' 分隔）
     * @return 字段值的列表
     */
    public List<String> extractFieldValues(String jsonString, String fieldPath) {
        List<String> values = new ArrayList<>();
        try {
            Object object = JSON.parse(jsonString);
            String[] fields = fieldPath.split("\\.");
            extractFieldValues(object, fields, 0, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return values;
    }

    /**
     * 从 JSON 对象中递归提取字段的值（支持嵌套字段）
     *
     * @param object     当前 JSON 对象
     * @param fields     字段路径数组
     * @param index      当前字段路径的索引
     * @param values     存储字段值的列表
     */
    private  void extractFieldValues(Object object, String[] fields, int index, List<String> values) {
        if (object instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) object;
            if (index < fields.length) {
                String fieldName = fields[index];
                if (jsonObject.containsKey(fieldName)) {
                    Object childNode = jsonObject.get(fieldName);
                    if (index == fields.length - 1) {
                        values.add(childNode.toString());
                    } else {
                        extractFieldValues(childNode, fields, index + 1, values);
                    }
                }
            }
        } else if (object instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) object;
            for (Object element : jsonArray) {
                extractFieldValues(element, fields, index, values);
            }
        }
    }
}