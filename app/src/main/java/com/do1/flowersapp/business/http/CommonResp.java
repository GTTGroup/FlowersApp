package com.do1.flowersapp.business.http;

import com.google.gson.JsonElement;

/**
 * 接口返回响应数据
 */
public class CommonResp {
    private String desc;
    private JsonElement data;
    private String code;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
