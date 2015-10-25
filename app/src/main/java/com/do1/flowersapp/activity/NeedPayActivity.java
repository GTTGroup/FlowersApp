package com.do1.flowersapp.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.do1.flowersapp.R;
import com.do1.flowersapp.business.model.NeedPayItem;
import com.do1.flowersapp.common.RVAapter;
import com.do1.flowersapp.common.RViewHolder;
import com.do1.flowersapp.context.BaseActivity;
import com.do1.flowersapp.tools.UITools;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by boluo on 2015/10/24.
 * 应付账款
 */
public class NeedPayActivity extends BaseActivity {
    @Bind(R.id.btn_top_back)
    ImageView btnTopBack;
    @Bind(R.id.text_top_title)
    TextView textTopTitle;
    @Bind(R.id.rv_data)
    RecyclerView rvData;
    private List<NeedPayItem> list = new ArrayList<NeedPayItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_pay);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        setTextTitle("应付款");
        list.add(new NeedPayItem("广州辉煌花卉门市部", 10000.8f));
        list.add(new NeedPayItem("南京辉煌花卉门市部", 5000.55f));
        list.add(new NeedPayItem("广州春天花卉门市部", 6666));
        list.add(new NeedPayItem("香港春天花卉门市部", 564));
        rvData.setLayoutManager(new LinearLayoutManager(this));
        rvData.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 1;
            }
        });
        rvData.setAdapter(new RVAapter<NeedPayItem>(list, R.layout.layout_need_pay_list_item) {
            @Override
            public void onBindViewHolder(RViewHolder holder, int position) {
                holder.setText(R.id.tv_cust_nane, getItem(position).custName);
                holder.setText(R.id.tv_need_pay, "￥" + new DecimalFormat(".00").format(getItem(position).needPay));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UITools.intent(NeedPayActivity.this, NeedPayDetailActivity.class);
                    }
                });
            }
        });
    }
}
