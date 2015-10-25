package com.do1.flowersapp.business.model;

import java.util.List;

/**
 * Created by gufeng
 * Created on 2015/10/26 03:58
 * 功能作用: 分类品种
 */
public class CategoryVariety {

    private List<Variety> list;

    public List<Variety> getList() {
        return list;
    }

    public void setList(List<Variety> list) {
        this.list = list;
    }

    public static class Variety {

        private String colorId;
        private String goodsCode;
        private String goodsName;
        private String goodsType;
        private String id;
        private String imgPath;
        private String status;

        public String getColorId() {
            return colorId;
        }

        public void setColorId(String colorId) {
            this.colorId = colorId;
        }

        public String getGoodsCode() {
            return goodsCode;
        }

        public void setGoodsCode(String goodsCode) {
            this.goodsCode = goodsCode;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(String goodsType) {
            this.goodsType = goodsType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
