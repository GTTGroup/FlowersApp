package com.do1.flowersapp.business.http;

import com.google.gson.JsonElement;

import cz.msebera.android.httpclient.Header;

/**
 * Created by gufeng
 * Created on 2015/10/21 20:56
 * 功能作用: 服务端接口请求
 */
public interface ServerApiClientCallback {

    /**
     * 接口返回成功200并且服务端接口返回0成功状态，必调用此方法
     * @param resp
     */
    public void onSuccess(CommonResp resp);

    /**
     * 接口返回成功200并且服务端接口返回1失败状态，必调用此方法
     * @param serverRespCode   服务端返回的状态。非HTTP协议的返回码
     * @param severRespFail    服务端返回的失败原因
     * @param responseString   服务端返回的失败数据
     */
    public void onFail(String serverRespCode,String severRespFail,JsonElement responseString);

    /**
     * HTTP协议的错误方法回调
     * @param statCode      HTTP协议的返回码
     * @param headers
     * @param responseString
     */
    public void onError(int statCode,Header[] headers,String responseString);
}
