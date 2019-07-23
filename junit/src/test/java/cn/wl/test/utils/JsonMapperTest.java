package cn.wl.test.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class JsonMapperTest {


    private static final Logger logger = LoggerFactory.getLogger(JsonMapper.class);
    private static final String jsonMapString = "{\n" +
            "  \"name\": \"Conan\",\n" +
            "  \"age\": 30,\n" +
            "  \"address\": \"shanghai\"\n" +
            "}";

    private JsonMapper jsonMapper = new JsonMapper();
    private Map<String, Object> objectMap = new HashMap<>();

    @Before
    public void beforeTest() {
        objectMap.put("name", "Conan");
        objectMap.put("age", 123);
        objectMap.put("address", "Shanghai");
    }


    @Test
    public void toJsonTest() {
        String jsonStr= jsonMapper.toJson(objectMap);
        logger.info(jsonStr);
    }

    @Test
    public void jsonToMapTest() {
        Map<String, Object> objectMap = jsonMapper.jsonToMap(jsonMapString);
        logger.info("name:" + objectMap.get("name"));
    }


}
