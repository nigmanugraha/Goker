package com.android.nigma.goker.Model;

import java.util.HashMap;
import java.util.Map;

public class MemberModel {
    private String address;
    private String nama;
    private String skill;
    private int age;

    public MemberModel(String address, String nama, String skill, int age) {
        this.address = address;
        this.nama = nama;
        this.skill = skill;
        this.age = age;
    }

    public MemberModel(String nama) {
        this.nama = nama;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<String,Object> toMap(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("nama",nama);
        map.put("skill",skill);
        map.put("age",age);
        map.put("address",address);
        return map;
    }
}
