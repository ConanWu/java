package cn.wl.test.objectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


public class PersonTest {

    private ObjectMapper objectMapper;
    private Person person;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void objectMapperTest1() throws JsonProcessingException {
        person = new Person();
        person.setName("conan");
        person.setAddress("");
        System.out.println(objectMapper.writeValueAsString(person));
    }

    @Test
    public void objectMapperTest2() {
        try {
            person = objectMapper.readValue("{\"name\":\"conan\",\"address\":\"\",\"mobile\":null,\"abc\":\"aabbcc\"}", Person.class);

            System.out.println("name:" + person.getName());
            System.out.println("address:" + person.getAddress());
            System.out.println("mobile:" + person.getMobile());
            System.out.println(person.getAbc());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
