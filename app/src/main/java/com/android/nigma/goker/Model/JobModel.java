package com.android.nigma.goker.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class JobModel implements Serializable {
    private String id;
    private String idCreator;
    private String title;
    private String spesification;
    private String desc;
    private int price;

    public JobModel() {
    }

    public JobModel(String idCreator, String title, String spesification, String desc, int price) {
        this.idCreator = idCreator;
        this.title = title;
        this.spesification = spesification;
        this.desc = desc;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(String idCreator) {
        this.idCreator = idCreator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpesification() {
        return spesification;
    }

    public void setSpesification(String spesification) {
        this.spesification = spesification;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Map<String,Object> toMap(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("idCreator",idCreator);
        map.put("title",title);
        map.put("spesification",spesification);
        map.put("desc",desc);
        map.put("price",price);
        return map;
    }

}
