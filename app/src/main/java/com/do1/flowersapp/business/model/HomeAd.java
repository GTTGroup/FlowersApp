package com.do1.flowersapp.business.model;

import java.util.List;

/**
 * Created by gufeng
 * Created on 2015/10/25 22:17
 * 功能作用: 首页广告轮播图
 */
public class HomeAd {

    private List<Ad> list;

    public List<Ad> getList() {
        return list;
    }

    public void setList(List<Ad> list) {
        this.list = list;
    }

    public static class Ad {

        private String adDescription;
        private String adOrder;
        private String adType;
        private String adUrl;
        private String contentPath;
        private String createTime;
        private String createUser;
        private String imgPath;
        private String title;
        private String status;

        public String getAdDescription() {
            return adDescription;
        }

        public void setAdDescription(String adDescription) {
            this.adDescription = adDescription;
        }

        public String getAdOrder() {
            return adOrder;
        }

        public void setAdOrder(String adOrder) {
            this.adOrder = adOrder;
        }

        public String getAdType() {
            return adType;
        }

        public void setAdType(String adType) {
            this.adType = adType;
        }

        public String getAdUrl() {
            return adUrl;
        }

        public void setAdUrl(String adUrl) {
            this.adUrl = adUrl;
        }

        public String getContentPath() {
            return contentPath;
        }

        public void setContentPath(String contentPath) {
            this.contentPath = contentPath;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
