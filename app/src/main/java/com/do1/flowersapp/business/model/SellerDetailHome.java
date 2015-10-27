package com.do1.flowersapp.business.model;

import java.util.List;

/**
 * Created by TuYong N1007
 * On 2015/10/22
 * At 14:47
 */
public class SellerDetailHome {
    //显示的type
    public int viewType;
    //头部数据
    public String adUrl;
    public List<String> adUrls;//头部banner数据
    public String first;//第一个分类
    public String second;//第二个分类
    public String third;//第三个分类
//    public String four;//更多分类

    //普通item
    public String coverUrl;//花图片地址
    public String name;//花名
    public String sold;//花已售
    public String money;//售价


}
