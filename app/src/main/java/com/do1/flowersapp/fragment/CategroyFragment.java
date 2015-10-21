package com.do1.flowersapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.do1.flowersapp.R;
import com.do1.flowersapp.business.http.CommonResp;
import com.do1.flowersapp.business.http.ServerApiClient;
import com.do1.flowersapp.business.http.ServerApiClientCallback;
import com.do1.flowersapp.business.model.CategoryItem;
import com.do1.flowersapp.business.model.CategoryList;
import com.do1.flowersapp.common.RecyclerArrayAdapter;
import com.do1.flowersapp.common.RecyclerItemClickListener;
import com.do1.flowersapp.context.ModuleFragment;
import com.do1.flowersapp.tools.DeviceInfo;
import com.do1.flowersapp.widget.FlowLayout;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by gufeng
 * Created on 2015/10/14 00:44
 * 功能作用: 首页--分类
 */
public class CategroyFragment extends ModuleFragment {

    private RecyclerView recyclerCategory;
    private RecyclerView recyclerContent;

    private ListRecyclerAdapter adapter;

    private int selectedCategoryPosition = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerCategory = (RecyclerView) view.findViewById(R.id.recycler_category);
        recyclerCategory.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerCategory.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                selectedCategoryPosition = position;
                adapter.setSelectedPosition(selectedCategoryPosition);
                getSubCategory(adapter.getItem(selectedCategoryPosition).getId());
            }
        }));
        recyclerContent = (RecyclerView) view.findViewById(R.id.recycler_content);
        recyclerContent.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        ServerApiClient.getInstance().getTypeList(getActivity(), getClass().getName(), new ServerApiClientCallback() {
            @Override
            public void onSuccess(CommonResp resp) {
                CategoryList categoryList = new Gson().fromJson(resp.getData(), CategoryList.class);
                if (null != categoryList && categoryList.getList().size() > 0) {
                    createListAdapter(categoryList.getList());
                }
            }

            @Override
            public void onError(int statCode, Header[] headers, String responseString) {

            }

            @Override
            public void onFail(String serverRespCode, String severRespFail, JsonElement responseString) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category,null);
    }

    private void createListAdapter(List<CategoryList.Category> categoryList) {
        adapter = new ListRecyclerAdapter(getActivity());
        recyclerCategory.setAdapter(adapter);
        adapter.addAll(categoryList);
        selectedCategoryPosition = 0;
        adapter.setSelectedPosition(selectedCategoryPosition);
        getSubCategory(categoryList.get(selectedCategoryPosition).getId());
    }

    private void createCatrgoryAdapter(List<CategoryItem.Category> items) {

        ItemRecyclerAdapter adapter = new ItemRecyclerAdapter(getActivity());
        adapter.addAll(items);
        recyclerContent.setAdapter(adapter);
    }

    private void getSubCategory(String parentId) {
        ServerApiClient.getInstance().getSubTypeList(getActivity(), getClass().getName(), parentId, new ServerApiClientCallback() {
            @Override
            public void onSuccess(CommonResp resp) {
                CategoryItem categoryList = new Gson().fromJson(resp.getData(), CategoryItem.class);
                if (null != categoryList && categoryList.getList().size() > 0) {
//                    createCatrgoryAdapter(categoryList.getList());
                }
            }

            @Override
            public void onFail(String serverRespCode, String severRespFail, JsonElement responseString) {

            }

            @Override
            public void onError(int statCode, Header[] headers, String responseString) {

            }
        });
    }

    class ItemRecyclerAdapter extends RecyclerArrayAdapter<CategoryItem.Category,CategoryViewHolder> {

        private Context context;
        private int deviceWidth;
        private int listRecyclerViewWidth;
        private int radioButtonHeight;

        public ItemRecyclerAdapter(Context ctx) {
            this.context = ctx;
            deviceWidth = DeviceInfo.getDeviceWidth(context);
            listRecyclerViewWidth = context.getResources().getDimensionPixelSize(R.dimen.category_recyclerview_list_width);
            radioButtonHeight = context.getResources().getDimensionPixelSize(R.dimen.category_item_height);
        }

        @Override
        public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CategoryViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.recycler_item_category_item,null));
        }

        @Override
        public void onBindViewHolder(CategoryViewHolder holder, int position) {
            CategoryItem.Category item = getItem(position);
//            holder.textTitle.setText(item.detailTitle);
//            int nButtonWidth = (deviceWidth-listRecyclerViewWidth-(holder.flowCategory.getSelfHorSpacing()*3) - holder.flowCategory.getPaddingLeft() - holder.flowCategory.getPaddingRight()) / 4;
//            for (final String tag : item.categoryItems) {
//                final RadioButton rbTag = (RadioButton) LayoutInflater.from(getActivity()).inflate(R.layout.view_category_item, null);
//                rbTag.setLayoutParams(new FlowLayout.LayoutParams(nButtonWidth, radioButtonHeight));
//                rbTag.setText(tag);
//                rbTag.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//                holder.flowCategory.addView(rbTag);
//            }
        }
    }

    class ListRecyclerAdapter extends RecyclerArrayAdapter<CategoryList.Category,ListViewHolder> {

        private Context context;
        private int selectedPosition = -1;

        public ListRecyclerAdapter(Context ctx) {
            super();
            this.context = ctx;
        }

        @Override
        public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ListViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item_category_list,null));
        }

        @Override
        public void onBindViewHolder(ListViewHolder holder, int position) {
            CategoryList.Category category = getItem(position);
            holder.textCategory.setText(category.getTypeName());
            if (position == selectedPosition) {
                holder.textCategory.setSelected(true);
            } else {
                holder.textCategory.setSelected(false);
            }
        }

        public void setSelectedPosition(int selectedPosition) {
            this.selectedPosition = selectedPosition;
            notifyDataSetChanged();
        }
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        public TextView textCategory;

        public ListViewHolder(View itemView) {
            super(itemView);
            textCategory = (TextView) itemView.findViewById(R.id.text_category);
        }
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        public TextView textTitle;
        public FlowLayout flowCategory;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            textTitle = (TextView) itemView.findViewById(R.id.text_category_item);
            flowCategory = (FlowLayout) itemView.findViewById(R.id.layout_category_item);
        }
    }
}
