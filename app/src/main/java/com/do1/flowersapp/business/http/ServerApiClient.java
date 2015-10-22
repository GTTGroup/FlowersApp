package com.do1.flowersapp.business.http;

import android.content.Context;
import android.util.Log;

import com.do1.flowersapp.tools.SecurityDes3Util;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by gufeng
 * Created on 2015/10/21 20:56
 * 功能作用: 服务端接口请求
 */
public class ServerApiClient {

    //首页广告
    private static final String APIURL_INDEXADACTION = "/indexAdAction!indexAd.action";
    //商家详情
    private static final String APIURL_SHOP = "/shopAction!shopDtl.action";
    //商家所有商品分页数据(包含新品、商铺商品搜索)
    private static final String APIURL_GOODS_BY_SHOP = "/goodsAction!goodsByShop.action";
    //商品详情
    private static final String APIURL_GOODS = "/goodsAction!goodsInfo.action";
    //首页分类
    private static final String APIURL_TYPELIST = "/indexTypeAction!typeList.action";
    //首页分类-子类别
    private static final String APIURL_SUBTYPELIST = "/indexTypeAction!subTypeList.action";
    //类别品种
    private static final String APIURL_GOODSLIST = "/indexTypeAction!goodsListByType.action";
    //商品搜索
    private static final String APIURL_SEARCHCOMMODITY = "/goodsAction!goodsByIndex.action";
    //商家所有商品分页数据(包含新品、商铺商品搜索)
    private static final String APIURL_SHOPGOODS = "/goodsAction!goodsByShop.action";
    //商品详情
    private static final String APIURL_COMMODITY = "//goodsAction!goodsInfo.action";
    //商品规格列表
    private static final String APIURL_GOODSSKULIST = "/goodsAction!goodsSkuList.action";
    //商品规格对应的属性
    private static final String APIURL_GOODSATTRBYSKU = "/goodsAction!goodsAttrBySku.action";
    //商家商品总分类
    private static final String APIURL_GOODSTYPEBYSHOP = "/shopAction!goodsTypeByShop.action";
    //商家分类对应子分类
    private static final String APIURL_GOODSSUBTYPEBYSHOP = "/shopAction!goodsSubTypeByShop.action";
    //商家子分类对应商品
    private static final String APIURL_GOODSINFOBYSUBTYPE = "/goodsAction!goodsInfoBySubType.action";


    private AsyncHttpClient asyncHttpClient;
    private static ServerApiClient apiClient;

    private ServerApiClient() {
        if (null == apiClient) {
            asyncHttpClient = new AsyncHttpClient();
            asyncHttpClient.setTimeout(10000);
            asyncHttpClient.setMaxRetriesAndTimeout(0,0);
        }
    }

    public static ServerApiClient getInstance() {
        if (null == apiClient) {
            apiClient = new ServerApiClient();
        }
        return apiClient;
    }

    /**
     * 加密参数
     * @param context
     * @param map      传入的参数
     * @param url      接口地址
     * @param tag      请求标识，每个请求关联一个tag.最好以当前Activity或fragment命名。
     *                 在Activity或Fragment中Ondestory的时候cancal该请求用到。
     *                 防止空指针
     * @param ifCache  是否缓存。缓存数据都通用在一个表中。存入接口返回的数据。后续在各业务模块中处理
     * @param callback 接口返回回调方法
     */
    private void postSecurity(Context context,Map<String,Object> map,String url,String tag,boolean ifCache,ServerApiClientCallback callback) {
        RequestParams params = new RequestParams();
        if(null != map && map.size() > 0) {
            Map<String,String> securityMap = SecurityDes3Util.decode(map);
            JSONObject jsonObject = new JSONObject(securityMap);
            params.put("requestJson",jsonObject.toString());
        }
        post(context, ifCache, url, tag, params, callback);
    }

    private void post(Context context, final boolean ifCache,String url,String tag,RequestParams params, final ServerApiClientCallback callback) {
        asyncHttpClient.post(context, url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                callback.onError(statusCode, headers, responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("ApiClient", statusCode + "==" + responseString);
                if (statusCode == HttpURLConnection.HTTP_OK) {
                    Gson gson = new Gson();
                    CommonResp commonResp = gson.fromJson(responseString, CommonResp.class);
                    if (ServerConstant.API_RESPONSE_CODE_SUCC.equals(commonResp.getCode())) {
                        if (ifCache) {

                        }
                        callback.onSuccess(commonResp);
                    } else {
                        callback.onFail(commonResp.getCode(), commonResp.getDesc(), commonResp.getData());
                    }
                } else {
                    callback.onError(statusCode, headers, responseString);
                }
            }
        });
    }

    public void cancelRequest(String tag) {
        asyncHttpClient.cancelRequestsByTAG(tag, true);
    }

    /**
     * 获取首页广告轮播
     */
    public void getHomeAdList(Context context,String tag,ServerApiClientCallback callback) {
        String url = ServerConstant.API_URL + ServerConstant.API_URL_PATH + APIURL_INDEXADACTION;
        Map<String,Object> params = new HashMap<>();
        params.put("adType",1);
        postSecurity(context, params, url, tag, true, callback);
    }

    /**
     * 获取首页种类
     */
    public void getTypeList(Context context,String tag,ServerApiClientCallback callback) {
        String url = ServerConstant.API_URL + ServerConstant.API_URL_PATH + APIURL_TYPELIST;
        postSecurity(context,null,url,tag,true,callback);
    }

    /**
     * 获取首页子列表种类
     */
    public void getSubTypeList(Context context,String tag,String parentId,ServerApiClientCallback callback) {
        String url = ServerConstant.API_URL + ServerConstant.API_URL_PATH + APIURL_SUBTYPELIST;
        Map<String,Object> params = new HashMap<>();
        params.put("parentId",parentId);
        postSecurity(context, params, url, tag, true, callback);
    }

    /**
     * 商家详情信息
     * @param context
     * @param tag
     * @param sellerId
     * @param callback
     */
    public void getSellerDetailMessage(Context context, String tag, String sellerId, ServerApiClientCallback callback){
        String url = ServerConstant.API_URL + ServerConstant.API_URL_PATH + APIURL_SHOP;
        Map<String,Object> params = new HashMap<>();
        params.put("id",sellerId);
        postSecurity(context,params,url,tag,true,callback);
    }

    /**
     * 商品详情(基础信息)
     * @param context
     * @param tag
     * @param goodsId
     * @param callback
     */
    public void getGoodsInfo(Context context, String tag, String goodsId, ServerApiClientCallback callback){
        String url = ServerConstant.API_URL + ServerConstant.API_URL_PATH + APIURL_GOODS;
        Map<String,Object> params = new HashMap<>();
        params.put("id",goodsId);
        postSecurity(context,params,url,tag,true,callback);
    }

    public void getGoodsByShop(Context context, String tag, int pageNum, int pageIndex, String shopId, int type, String keyword, ServerApiClientCallback callback) {
        String url = ServerConstant.API_URL + ServerConstant.API_URL_PATH + APIURL_GOODS_BY_SHOP;
        Map<String, Object> params = new HashMap<>();
        params.put("pageNum", pageNum);
        params.put("pageIndex", pageIndex);
        params.put("shopId", shopId);
        params.put("type", type);
        params.put("keyword", keyword);
        postSecurity(context, params, url, tag, true, callback);
    }


}
