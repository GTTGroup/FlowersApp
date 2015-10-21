package com.do1.flowersapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.do1.flowersapp.R;
import com.do1.flowersapp.business.model.CategoryItem;
import com.do1.flowersapp.common.RecyclerArrayAdapter;
import com.do1.flowersapp.common.RecyclerItemClickListener;
import com.do1.flowersapp.context.ModuleFragment;
import com.do1.flowersapp.tools.DeviceInfo;
import com.do1.flowersapp.widget.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gufeng
 * Created on 2015/10/14 00:44
 * 功能作用: 首页--分类
 */
public class CategroyFragment extends ModuleFragment {

    private RecyclerView recyclerCategory;
    private RecyclerView recyclerContent;

    private List<String> categoryList;

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

                List<CategoryItem> list = new ArrayList<CategoryItem>();
                CategoryItem detailItem = new CategoryItem();
                detailItem.categoryItems.add("红色");
                detailItem.categoryItems.add("白色");
                detailItem.categoryItems.add("黄色");
                detailItem.categoryItems.add("绿色");
                detailItem.categoryItems.add("天空蓝色");
                detailItem.categoryItems.add("蓝色");
                detailItem.categoryItems.add("紫色");
                detailItem.categoryItems.add("大红色");
                detailItem.detailTitle = "分类";
                list.add(detailItem);

                CategoryItem detailItem1 = new CategoryItem();
                detailItem1.detailTitle = "种类";
                detailItem1.categoryItems.add("大衣");
                detailItem1.categoryItems.add("大衣");
                detailItem1.categoryItems.add("大衣");
                detailItem1.categoryItems.add("大衣");
                detailItem1.categoryItems.add("大衣");
                detailItem1.categoryItems.add("大衣");
                detailItem1.categoryItems.add("大衣");
                detailItem1.categoryItems.add("大衣");
                detailItem1.categoryItems.add("大衣");
                detailItem1.categoryItems.add("大衣");
                detailItem1.categoryItems.add("大衣");
                detailItem1.categoryItems.add("大衣");
                detailItem1.categoryItems.add("大衣");
                list.add(detailItem1);

                CategoryItem detailItem2 = new CategoryItem();
                detailItem2.detailTitle = "品牌";
                detailItem2.categoryItems.add("NICK");
                detailItem2.categoryItems.add("NICK");
                detailItem2.categoryItems.add("NICK");
                detailItem2.categoryItems.add("NICK");
                detailItem2.categoryItems.add("NICK");
                detailItem2.categoryItems.add("NICK");
                detailItem2.categoryItems.add("NICK");
                detailItem2.categoryItems.add("NICK");
                detailItem2.categoryItems.add("NICK");
                detailItem2.categoryItems.add("NICK");
                detailItem2.categoryItems.add("NICK");
                detailItem2.categoryItems.add("NICK");
                detailItem2.categoryItems.add("NICK");
                list.add(detailItem2);

                CategoryItem detailItem3 = new CategoryItem();
                detailItem3.detailTitle = "品牌";
                detailItem3.categoryItems.add("NICK");
                detailItem3.categoryItems.add("NICK");
                detailItem3.categoryItems.add("NICK");
                detailItem3.categoryItems.add("NICK");
                detailItem3.categoryItems.add("NICK");
                detailItem3.categoryItems.add("NICK");
                detailItem3.categoryItems.add("NICK");
                detailItem3.categoryItems.add("NICK");
                detailItem3.categoryItems.add("NICK");
                detailItem3.categoryItems.add("NICK");
                detailItem3.categoryItems.add("NICK");
                detailItem3.categoryItems.add("NICK");
                detailItem3.categoryItems.add("NICK");
                list.add(detailItem3);

                createCatrgoryAdapter(list);
            }
        }));
        createListAdapter();
        recyclerContent = (RecyclerView) view.findViewById(R.id.recycler_content);
        recyclerContent.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category,null);
    }

    private void createListAdapter() {
        categoryList = new ArrayList<>();
        categoryList.add("玫瑰");
        categoryList.add("百合");
        categoryList.add("康乃馨");
        categoryList.add("康乃馨1");
        categoryList.add("康乃馨2");
        categoryList.add("康乃馨3");
        categoryList.add("康乃馨4");
        categoryList.add("康乃馨5");
        categoryList.add("康乃馨6");
        categoryList.add("康乃馨7");
        categoryList.add("康乃馨8");
        categoryList.add("康乃馨9");
        categoryList.add("康乃馨10");
        categoryList.add("康乃馨11");
        categoryList.add("康乃馨12");
        categoryList.add("康乃馨13");
        categoryList.add("康乃馨14");

        adapter = new ListRecyclerAdapter(getActivity());
        recyclerCategory.setAdapter(adapter);
        adapter.addAll(categoryList);
    }

    private void createCatrgoryAdapter(List<CategoryItem> items) {

        ItemRecyclerAdapter adapter = new ItemRecyclerAdapter(getActivity());
        adapter.addAll(items);
        recyclerContent.setAdapter(adapter);
    }

    class ItemRecyclerAdapter extends RecyclerArrayAdapter<CategoryItem,CategoryViewHolder> {

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
            CategoryItem item = getItem(position);
            holder.textTitle.setText(item.detailTitle);
            int nButtonWidth = (deviceWidth-listRecyclerViewWidth-(holder.flowCategory.getSelfHorSpacing()*3) - holder.flowCategory.getPaddingLeft() - holder.flowCategory.getPaddingRight()) / 4;
            for (final String tag : item.categoryItems) {
                final RadioButton rbTag = (RadioButton) LayoutInflater.from(getActivity()).inflate(R.layout.view_category_item, null);
                rbTag.setLayoutParams(new FlowLayout.LayoutParams(nButtonWidth, radioButtonHeight));
                rbTag.setText(tag);
                rbTag.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                holder.flowCategory.addView(rbTag);
            }
        }
    }

    class ListRecyclerAdapter extends RecyclerArrayAdapter<String,ListViewHolder> {

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
            String category = getItem(position);
            holder.textCategory.setText(category);
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
