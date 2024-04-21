package com.bfox.xunbao.common.storage.entity;

/**
 * 存储对象
 * @author eden
 * @date 2024/4/21 18:49:49
 */
public class Storage {

    private String name;

    private String url;

    public Storage() {

    }

    public Storage(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
