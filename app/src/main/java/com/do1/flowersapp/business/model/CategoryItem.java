package com.do1.flowersapp.business.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gufeng
 * Created on 2015/10/19 02:26
 * 功能作用: 分类列表选项明细
 */
public class CategoryItem {

    public String detailTitle;
    public long groupId;
    public List<String> categoryItems = new ArrayList<>();
}
