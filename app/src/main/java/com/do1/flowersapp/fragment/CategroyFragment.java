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
import android.widget.LinearLayout;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * Created by gufeng
 * Created on 2015/10/14 00:44
 * 功能作用: 首页--分类
 */
public class CategroyFragment extends ModuleFragment {

    @Bind(R.id.recycler_category) RecyclerView recyclerCategory;
    @Bind(R.id.ll_category) LinearLayout llCategoryItem;

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
                llCategoryItem.removeAllViews();
                getSubCategory(adapter.getItem(selectedCategoryPosition).getId());
            }
        }));
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
        View view = inflater.inflate(R.layout.fragment_category, null);
        ButterKnife.bind(this, view);
        return view;
    }

    private void createListAdapter(List<CategoryList.Category> categoryList) {
        adapter = new ListRecyclerAdapter(getActivity());
        recyclerCategory.setAdapter(adapter);
        adapter.addAll(categoryList);
        selectedCategoryPosition = 0;
        adapter.setSelectedPosition(selectedCategoryPosition);
        getSubCategory(categoryList.get(selectedCategoryPosition).getId());
    }

    private void addCategoryItemView(String title,List<CategoryItem.Category> categoryList) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.recycler_item_category_item,null);
        TextView textTitle = (TextView) view.findViewById(R.id.text_category_item);
        FlowLayout flowCategory = (FlowLayout) view.findViewById(R.id.layout_category_item);
        llCategoryItem.addView(view);
        textTitle.setText(title);
        int deviceWidth = DeviceInfo.getDeviceWidth(getActivity());
        int listRecyclerViewWidth = getResources().getDimensionPixelSize(R.dimen.category_recyclerview_list_width);
        int radioButtonHeight = getResources().getDimensionPixelSize(R.dimen.category_item_height);
        int nButtonWidth = (deviceWidth-listRecyclerViewWidth-(flowCategory.getSelfHorSpacing()*3) - flowCategory.getPaddingLeft() - flowCategory.getPaddingRight()) / 4;
        for (final CategoryItem.Category item : categoryList) {
            final RadioButton rbTag = (RadioButton) LayoutInflater.from(getActivity()).inflate(R.layout.view_category_item, null);
            rbTag.setLayoutParams(new FlowLayout.LayoutParams(nButtonWidth, radioButtonHeight));
            rbTag.setText(item.getTypeName());
            rbTag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ServerApiClient.getInstance().getSubTypeList(getActivity(), getClass().getName(), item.getId(), new ServerApiClientCallback() {
                        @Override
                        public void onSuccess(CommonResp resp) {
                            CategoryItem categoryList = new Gson().fromJson(resp.getData(), CategoryItem.class);
                            if (null != categoryList && categoryList.getList().size() > 0) {
                                addCategoryItemView("种类",categoryList.getList());
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
            });
            flowCategory.addView(rbTag);
        }
    }

    private void getSubCategory(String parentId) {
        ServerApiClient.getInstance().getSubTypeList(getActivity(), getClass().getName(), parentId, new ServerApiClientCallback() {
            @Override
            public void onSuccess(CommonResp resp) {
                CategoryItem categoryList = new Gson().fromJson(resp.getData(), CategoryItem.class);
                if (null != categoryList && categoryList.getList().size() > 0) {
                    addCategoryItemView("分类",categoryList.getList());
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
}
