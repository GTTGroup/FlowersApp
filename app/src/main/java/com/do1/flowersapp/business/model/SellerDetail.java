package com.do1.flowersapp.business.model;

/**
 * Created by TuYong N1007
 * On 2015/10/22
 * At 14:32
 * 商家详情信息
 */
public class SellerDetail {
//    code	返回结果	String
//    desc	结果描述	String
//    data	返回实体
//    id	商铺ID	String
//    shopName	商铺名称	String
//    shopInfoUrl	商家简介地址	String
//    addr	商家介绍地址	String
//    linkService	联系方式	String

    private DataEntity data;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * createTime : Mon Oct 19 14:20:47 CST 2015
         * linkService : 073188854321
         * updateTime : null
         * status : 1
         * addr : 长沙市韶山南路123号
         * downTime : Wed Nov 25 14:20:39 CST 2015
         * city : 长沙
         * shopInfoUrl : /upload/s01.html
         * shopContactUrl : /upload/s01-1.html
         * id : s01
         * orgId : 0001
         * createUser : admin
         * upTime : Mon Oct 19 14:20:34 CST 2015
         * shopIntro : null
         * shopName : 欣欣花语
         * imgPath : /upload/s01.jpg
         * shopDesc : 面朝阳光的迷你店铺
         * updateUser : null
         */

        private String createTime;
        private String linkService;
        private Object updateTime;
        private String status;
        private String addr;
        private String downTime;
        private String city;
        private String shopInfoUrl;
        private String shopContactUrl;
        private String id;
        private String orgId;
        private String createUser;
        private String upTime;
        private Object shopIntro;
        private String shopName;
        private String imgPath;
        private String shopDesc;
        private Object updateUser;

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public void setLinkService(String linkService) {
            this.linkService = linkService;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public void setDownTime(String downTime) {
            this.downTime = downTime;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setShopInfoUrl(String shopInfoUrl) {
            this.shopInfoUrl = shopInfoUrl;
        }

        public void setShopContactUrl(String shopContactUrl) {
            this.shopContactUrl = shopContactUrl;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public void setUpTime(String upTime) {
            this.upTime = upTime;
        }

        public void setShopIntro(Object shopIntro) {
            this.shopIntro = shopIntro;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public void setShopDesc(String shopDesc) {
            this.shopDesc = shopDesc;
        }

        public void setUpdateUser(Object updateUser) {
            this.updateUser = updateUser;
        }

        public String getCreateTime() {
            return createTime;
        }

        public String getLinkService() {
            return linkService;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public String getStatus() {
            return status;
        }

        public String getAddr() {
            return addr;
        }

        public String getDownTime() {
            return downTime;
        }

        public String getCity() {
            return city;
        }

        public String getShopInfoUrl() {
            return shopInfoUrl;
        }

        public String getShopContactUrl() {
            return shopContactUrl;
        }

        public String getId() {
            return id;
        }

        public String getOrgId() {
            return orgId;
        }

        public String getCreateUser() {
            return createUser;
        }

        public String getUpTime() {
            return upTime;
        }

        public Object getShopIntro() {
            return shopIntro;
        }

        public String getShopName() {
            return shopName;
        }

        public String getImgPath() {
            return imgPath;
        }

        public String getShopDesc() {
            return shopDesc;
        }

        public Object getUpdateUser() {
            return updateUser;
        }
    }

//     public String id;
//     public String shopName;
//     public String shopInfoUrl;
//     public String addr;
//     public String linkService;
//     public JsonElement data;

}
