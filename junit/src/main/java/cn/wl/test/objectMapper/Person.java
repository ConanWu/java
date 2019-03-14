package cn.wl.test.objectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Person {
    private String name;
    private String address;
    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAbc() {
        return "AAABBBCCC";
    }

    public void setAbc(String abc) {

    }

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = new Person();
        person.setName("conan");
        person.setAddress("");
        System.out.println(objectMapper.writeValueAsString(person));
    }
}
