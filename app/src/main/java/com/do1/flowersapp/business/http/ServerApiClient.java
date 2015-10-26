package com.do1.flowersapp.business.http;

import android.content.Context;
import android.util.Log;

import com.do1.flowersapp.constants.ServerConstant;
import com.do1.flowersapp.tools.SecurityDes3Util;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.LinkedHashMap;

import cz.msebera.android.httpclient.Header;

/**
 * Created by gufeng
 * Created on 2015/10/21 20:56
 * 功能作用: 服务端接口请求
 */
public class ServerApiClient {

    //首页广告
    private static final String APIURL_INDEXADACTION = "/indexAdAction!indexAd.action";
    //首页展柜
    private static final String APIURL_INDEXDISPLAYACTION = "/indexDisplayAction!displayInfo.action";
    //商家详情
    private static final String APIURL_SHOP = "/shopAction!shopDtl.action";
    //商品详情
    private static final String APIURL_GOODS = "/goodsAction!goodsInfo.action";
    //商品规格列表
    private static final String APIURL_GOODS_SKULIST = "/goodsAction!goodsSkuList.action";
    //商品规格对应的属性
    private static final String APIURL_GOODS_ATTRBYSKU = "/goodsAction!goodsAttrBySku.action";
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
    //用户登录
    private static final String APIURL_USERLOGIN = "/memberAction!login.action";
    //用户注册
    private static final String APIURL_USERREGISTER = "/memberAction!register.action";
    //获取用户基础信息
    private static final String APIURL_USERINFO = "/memberAction!userinfo.action";
    //获取用户支付信息
    private static final String APIURL_USERPAYINFO = "/memberAction!getUserPayInfo.action";
    //查询用户收货地址
    private static final String APIURL_USERADDRESSLIST = "/memberAction!searchAddr.action";
    //新增用户收货地址
    private static final String APIURL_ADDUSERADDRESS = "/memberAction!addAddr.action";
    //删除用户收货地址
    private static final String APIURL_DELUSERADDRESS = "/memberAction!delAddr.action";
    //订单列表
    private static final String APIURL_ORDERLIST = "/buycarAction!orderList.action";
    //订单详情
    private static final String APIURL_ORDERDETAIL = "/buycarAction!orderDetail.action";


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
    private void postSecurity(Context context,LinkedHashMap<String,Object> map,String url,String tag,boolean ifCache,ServerApiClientCallback callback) {
        RequestParams params = new RequestParams();
        if(null != map && map.size() > 0) {
            LinkedHashMap<String,String> securityMap = SecurityDes3Util.decode(map);
            JSONObject jsonObject = new JSONObject(securityMap);
            Log.e("post-requestJson:",jsonObject.toString());
            params.put("requestJson",jsonObject.toString());
        }
        post(context, ifCache, url, tag, params, callback);
    }

    private void post(Context context, final boolean ifCache,String url,String tag,RequestParams params, final ServerApiClientCallback callback) {
        Log.d("ApiClient","params:" + params);
        asyncHttpClient.post(context, url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("ApiClient", "onFailure:" + statusCode + "--" + responseString);
                callback.onError(statusCode, headers, responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("ApiClient", "OnSuccess:" + statusCode + "--" + responseString);
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
        LinkedHashMap<String,Object> params = new LinkedHashMap<>();
        params.put("adType", 1);
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
        LinkedHashMap<String,Object> params = new LinkedHashMap<>();
        params.put("parentId",parentId);
        postSecurity(context, params, url, tag, true, callback);
    }

    /**
     * 获取首页类别品种
     */
    public void getGoodsTypeList(Context context,String tag,String goodsType,ServerApiClientCallback callback) {
        String url = ServerConstant.API_URL + ServerConstant.API_URL_PATH + APIURL_GOODSLIST;
        LinkedHashMap<String,Object> params = new LinkedHashMap<>();
        params.put("goodsType",goodsType);
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
        LinkedHashMap<String,Object> params = new LinkedHashMap<>();
        params.put("id",sellerId);
        postSecurity(context,params,url,tag,true,callback);
    }

    /**
     * 商家所有商品分类的list(包含新品,商铺商品搜索) 对应 SellerDetailAllFragment
     * @param context
     * @param tag
     * @param shopId
     * @param pageNum
     * @param pageIndex
     * @param type 1、全部（时间）2、销量 3、价格
     * @param keyword 模糊查询条件
     * @param callback
     */
    public void getSellerDetailList(Context context, String tag, String shopId,String pageNum,String pageIndex,String type,String keyword,ServerApiClientCallback callback){
        String url = ServerConstant.API_URL + ServerConstant.API_URL_PATH + APIURL_SHOPGOODS;
        LinkedHashMap<String,Object> params = new LinkedHashMap<>();
        params.put("pageNum",pageNum);
        params.put("pageIndex",pageIndex);
        params.put("shopId",shopId);
        params.put("type",type);
        params.put("keyword","");
//        params.put("pageNum","10");
//        params.put("pageIndex","1");
//        params.put("shopId","s01");
//        params.put("type","2");
//        params.put("keyword","红");
        postSecurity(context, params, url, tag, true, callback);
    }

    /**
     * 商家商品总分类
     * @param context
     * @param tag
     * @param shopId
     */
    public void getSellerAllList(Context context,String tag,String shopId,ServerApiClientCallback callback){
        String url = ServerConstant.API_URL + ServerConstant.API_URL_PATH + APIURL_GOODSTYPEBYSHOP;
        LinkedHashMap<String,Object> params = new LinkedHashMap<>();
        params.put("shopId",shopId);
        postSecurity(context, params, url, tag, true, callback);
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
        LinkedHashMap<String,Object> params = new LinkedHashMap<>();
        params.put("id",goodsId);
        postSecurity(context,params,url,tag,true,callback);
    }

    /**
     * 商品规格列表
     * @param context
     * @param tag
     * @param goodsId
     * @param callback
     */
    public void getGoodsSkuList(Context context, String tag, String goodsId, ServerApiClientCallback callback) {
        String url = ServerConstant.API_URL + ServerConstant.API_URL_PATH + APIURL_GOODS_SKULIST;
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("id",goodsId);
        postSecurity(context, params, url, tag, true, callback);
    }

    /**
     * 首页展柜
     */
    public void getHomeShop(Context context,String tag,String pageNum,String displayType,ServerApiClientCallback callback) {
        String url = ServerConstant.API_URL + ServerConstant.API_URL_PATH + APIURL_INDEXDISPLAYACTION;
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("pageNum", pageNum);
        params.put("pageIndex", "1");
        params.put("displayType", displayType);
        params.put("orgId", "0001");
        postSecurity(context, params, url, tag, true, callback);
    }

    /**
     * 用户登录
     */
    public void login(Context context,String tag,String userAccount,String password,ServerApiClientCallback callback) {
        String url = ServerConstant.API_URL + ServerConstant.API_URL_PATH + APIURL_USERLOGIN;
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("userName", userAccount);
        params.put("userPwd", password);
        postSecurity(context, params, url, tag, true, callback);
    }

    /**
     * 用户注册
     */
    public void register(Context context,String tag,String userAccount,String password,String userType,String nickName,String mobile,String address,ServerApiClientCallback callback) {
        String url = ServerConstant.API_URL + ServerConstant.API_URL_PATH + APIURL_USERREGISTER;
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("userName", userAccount);
        params.put("userPwd", password);
        params.put("userType", userType);
        params.put("nickName",nickName);
        params.put("mobile", mobile);
        params.put("addr", address);
        postSecurity(context, params, url, tag, true, callback);
    }

    /**
     * 获取用户基础信息
     */
    public void getUserInfo(Context context,String tag,String userId,ServerApiClientCallback callback) {
        String url = ServerConstant.API_URL + ServerConstant.API_URL_PATH + APIURL_USERINFO;
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("userId", userId);
        postSecurity(context, params, url, tag, true, callback);
    }

    /**
     * 获取用户支付信息
     */
    public void getUserPayInfo(Context context,String tag,String userId,ServerApiClientCallback callback) {
        String url = ServerConstant.API_URL + ServerConstant.API_URL_PATH + APIURL_USERPAYINFO;
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("userId", userId);
        postSecurity(context, params, url, tag, true, callback);
    }

    /**
     * 查询用户收货地址
     */
    public void getUserAddressList(Context context,String tag,String memberId,ServerApiClientCallback callback) {
        String url = ServerConstant.API_URL + ServerConstant.API_URL_PATH + APIURL_USERADDRESSLIST;
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("memberId", memberId);
        postSecurity(context, params, url, tag, true, callback);
    }

    /**
     * 删除用户收货地址
     */
    public void delUserAddress(Context context,String tag,String id,ServerApiClientCallback callback) {
        String url = ServerConstant.API_URL + ServerConstant.API_URL_PATH + APIURL_DELUSERADDRESS;
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("id", id);
        postSecurity(context, params, url, tag, true, callback);
    }

    /**
     * 新增用户收货地址
     */
    public void addUserAddress(Context context,String tag,String memberId,String realName,String mobile,String postCode,String areaAddr,String roadAddr,String isDefault,ServerApiClientCallback callback) {
        String url = ServerConstant.API_URL + ServerConstant.API_URL_PATH + APIURL_ADDUSERADDRESS;
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("memberId", memberId);
        params.put("realname", realName);
        params.put("mobile", mobile);
        params.put("postcode", postCode);
        params.put("areaAddr", areaAddr);
        params.put("roadAddr", roadAddr);
        params.put("isDefault", isDefault);
        postSecurity(context, params, url, tag, true, callback);
    }

    /**
     * 商品规格对应的属性
     * @param context
     * @param tag
     * @param goodsId
     * @param skuIds
     * @param callback
     */
    public void getGoodsAttrbySku(Context context, String tag, String goodsId, String skuIds, ServerApiClientCallback callback) {
        String url = ServerConstant.API_URL + ServerConstant.API_URL_PATH + APIURL_GOODS_ATTRBYSKU;
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("id",goodsId);
        params.put("skuIds",skuIds);
        postSecurity(context, params, url, tag, true, callback);
    }

    /**
     * 订单列表
     */
    public void getOrderList(Context context, String tag, String userId,String pageNum,String pageIndex, ServerApiClientCallback callback) {
        String url = ServerConstant.API_URL + ServerConstant.API_URL_PATH + APIURL_ORDERLIST;
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("pageNum",pageNum);
        params.put("pageIndex",pageIndex);
        params.put("userId",userId);
        postSecurity(context, params, url, tag, true, callback);
    }
}
