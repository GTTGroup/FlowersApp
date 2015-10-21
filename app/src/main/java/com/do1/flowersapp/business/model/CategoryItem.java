package com.do1.flowersapp.business.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gufeng
 * Created on 2015/10/19 02:26
 * 功能作用: 分类列表选项明细
 */
public class CategoryItem {

    private ArrayList<Category> list;

    public ArrayList<Category> getList() {
        return list;
    }

    public void setList(ArrayList<Category> list) {
        this.list = list;
    }

    public static class Category {
        private String id;
        private String typeName;
        private String status;
        private String typeId;
        private String typeCode;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }
    }
}
