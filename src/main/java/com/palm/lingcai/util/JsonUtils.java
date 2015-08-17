package com.palm.lingcai.util;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * json转化工具类
 *
 * @author Administrator
 */
public class JsonUtils {

    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * Object字符串转化为 json
     *
     * @param Object obj
     * @return String json
     */
    public static String objectToJsonString(Object obj) {
        ObjectMapper objectMapper = null;
        String resultJson = null;
        try {
            objectMapper = new ObjectMapper();
            resultJson = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("Object to Json error", e);
        }
        return resultJson;
    }

    /**
     * 将string json 转化为Object
     *
     * @param String json
     * @param Class  cls
     * @return Class obj
     */
    public static <T> T getBeanFromJsonString(String json, Class<T> cls) {
        ObjectMapper objectMapper = null;
        T obj = null;
        try {
            objectMapper = new ObjectMapper();
            obj = (T) objectMapper.readValue(json, cls);
        } catch (Exception e) {
            return null;
        }
        return obj;
    }

    /**
     * 解析json字符串方法
     *
     * @param jsonText json字符串
     * @param key
     * @return
     */
    public static String parseJson(String jsonText, String key) {
        JsonFactory jsonFactory = new MappingJsonFactory();
        JsonParser jsonParser = null;// Json解析器
        HashMap<String, String> map = null;
        try {
            jsonParser = jsonFactory.createJsonParser(jsonText);
            jsonParser.nextToken();// 跳到结果集的开始
            map = new HashMap<String, String>();// 结果集HashMap
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                jsonParser.nextToken();    // 跳转到Value
                map.put(jsonParser.getCurrentName(), jsonParser.getText());    // 将Json中的值装入Map中
            }
        } catch (JsonParseException e) {
            logger.error("--json parser error--", e);
        } catch (IOException e) {
            logger.error("--json parser error--", e);
        }
        return map.get(key) == null ? null : map.get(key);
    }

    /**
     * 解析json字符串方法
     *
     * @param jsonText json字符串
     * @param key
     * @return
     */
    public static Map<String, Object> parseJson(String jsonText) {
        if (jsonText == null || jsonText.equals("")) {
            return null;
        }

        JsonFactory jsonFactory = new MappingJsonFactory();
        JsonParser jsonParser = null;// Json解析器
        HashMap<String, Object> map = null;
        try {
            jsonParser = jsonFactory.createJsonParser(jsonText);
            jsonParser.nextToken();// 跳到结果集的开始
            map = new HashMap<String, Object>();// 结果集HashMap
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                jsonParser.nextToken();    // 跳转到Value
                map.put(jsonParser.getCurrentName(), jsonParser.getText());    // 将Json中的值装入Map中
            }
        } catch (Exception e) {

        }
        return map == null ? null : map;
    }


    /**
     * 返回 json串"{\"resultCode\":\"resultCode\",\"retultMsg\":\"resultMsg\"}";
     *
     * @return
     */
    public static String getJsonResult(String resultCode, Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"resultCode\":\"");
        sb.append(resultCode);
        sb.append("\",\"retultMsg\":");
        sb.append(objectToJsonString(obj));
        sb.append("}");
        return sb.toString();
    }


    public static void main(String[] args) {
        String answerJson = "{\"id\":10,\"questionId\":10,\"userId\":10,\"answer\":\"ssss\",\"isRight\":1,\"createTime\":\"2013-12-17\"}";
        // Answer answer = getBeanFromJsonString(answerJson, Answer.class);
        // String objectToJsonString = objectToJsonString(answer);
        String parseJson = parseJson(answerJson, "questionId");
        System.out.println(Math.random());
    }

}
