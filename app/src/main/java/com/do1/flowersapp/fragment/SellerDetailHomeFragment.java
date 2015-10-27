package com.do1.flowersapp.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.do1.flowersapp.R;
import com.do1.flowersapp.activity.SellerDetailAllActivity;
import com.do1.flowersapp.business.listener.HomeDetailClickListener;
import com.do1.flowersapp.business.model.SellerDetailHome;
import com.do1.flowersapp.tools.DeviceInfo;
import com.do1.flowersapp.tools.UITools;
import com.do1.flowersapp.widget.CirclePageIndicator;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bruce Too
 * On 10/21/15.
 * At 21:24
 * 商家详情页-商家首页tab
 */
public class SellerDetailHomeFragment extends Fragment implements HomeDetailClickListener {

    @Bind(R.id.recycler_detail_main)
    RecyclerView mRecyclerView;

    private List<SellerDetailHome> datas;
    private HomeRecyclerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seller_detail_home,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        datas = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            if(i == 0){
                SellerDetailHome data = new SellerDetailHome();
                data.viewType = HomeRecyclerAdapter.VIEW_TYPE_HEADER;
                data.adUrl = "http://pic7.nipic.com/20100424/4271569_235714000888_2.jpg";
                data.first = "花1";
                data.second = "花2";
                data.third = "花3";
                datas.add(0,data);
            }else {
                SellerDetailHome item = new SellerDetailHome();
                if(i % 2 == 0){
                    item.viewType = HomeRecyclerAdapter.VIEW_TYPE_ITEM_LEFT;
                }else {
                    item.viewType = HomeRecyclerAdapter.VIEW_TYPE_ITEM_RIGHT;
                }
                item.name = "草泥马"+i;
                item.money = "100"+i;
                item.sold = String.format(getResources().getString(R.string.seller_sold_num),"10"+i);
                item.coverUrl = "http://pic7.nipic.com/20100424/4271569_235714000888_2.jpg";
                datas.add(item);
            }
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new HomeRecyclerAdapter(datas,this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onHotFlowerClick(TextView view) {
        if(view.getText().equals("更多")){
            UITools.intent(getActivity(), SellerDetailAllActivity.class);
        }
    }

    @Override
    public void onItemClick() {
        Toast.makeText(getActivity(),"点我搞啥子",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTopAdClick() {
        Toast.makeText(getActivity(),"广告...",Toast.LENGTH_SHORT).show();
    }


    class HomeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        public final static int VIEW_TYPE_HEADER = 1;
        public final static int VIEW_TYPE_ITEM_LEFT = 2;
        public final static int VIEW_TYPE_ITEM_RIGHT = 3;

        private List<SellerDetailHome> datas;
        private HomeDetailClickListener listener;

        public HomeRecyclerAdapter(List<SellerDetailHome> datas,HomeDetailClickListener listener) {
            this.datas = datas;
            this.listener = listener;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            if(viewType == VIEW_TYPE_HEADER){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_seller_detail_home_header,parent,false);
                return new HeaderViewHolder(view);
            }else {
                if(viewType == VIEW_TYPE_ITEM_LEFT) {
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_seller_detail_home_item1, parent, false);
                }else {
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_seller_detail_home_item, parent, false);
                }
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = (int) ((DeviceInfo.getDeviceWidth(parent.getContext()) - getResources().getDimension(R.dimen.seller_detail_item_margin)*2)/2);
                view.setLayoutParams(layoutParams);
                return new ItemViewHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
              if(getItemViewType(position) == VIEW_TYPE_HEADER){
                 bindHeaderView((HeaderViewHolder)holder,datas.get(position));
              }else {
                 bindItemView((ItemViewHolder) holder, datas.get(position));
              }
        }

        private void bindItemView(ItemViewHolder holder,SellerDetailHome item) {
            Uri uri = Uri.parse(item.coverUrl);
            holder.draweeView.setImageURI(uri);
            holder.money.setText(item.money);
            holder.name.setText(item.name);
            holder.sold.setText(item.sold);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick();
                }
            });
        }

        private void bindHeaderView(final HeaderViewHolder holder, final SellerDetailHome item) {
            holder.viewPager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return 3;
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    SimpleDraweeView imageView = new SimpleDraweeView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    imageView.setImageURI(Uri.parse(item.adUrl));
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.onTopAdClick();
                        }
                    });
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                    container.addView(imageView, params);
                    return imageView;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((SimpleDraweeView) object);
                }
            });
            holder.indicator.setViewPager(holder.viewPager);
            holder.first.setText(item.first);
            holder.second.setText(item.second);
            holder.third.setText(item.third);

            holder.first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onHotFlowerClick(holder.first);
                }
            });

            holder.second.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onHotFlowerClick(holder.second);
                }
            });

            holder.third.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onHotFlowerClick(holder.third);
                }
            });

            holder.four.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onHotFlowerClick(holder.four);
                }
            });
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        @Override
        public int getItemViewType(int position) {
            return datas.get(position).viewType;
        }

        class HeaderViewHolder extends RecyclerView.ViewHolder{
            @Bind(R.id.view_pager)
            ViewPager viewPager;
            @Bind(R.id.indicator)
            CirclePageIndicator indicator;
            //分类1 - 4
            @Bind(R.id.text_first)
            TextView first;
            @Bind(R.id.text_second)
            TextView second;
            @Bind(R.id.text_third)
            TextView third;
            @Bind(R.id.text_four)
            TextView four;//更多
            public HeaderViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        class ItemViewHolder extends RecyclerView.ViewHolder{
            @Bind(R.id.drawee_cover)
            SimpleDraweeView draweeView;
            @Bind(R.id.text_name)
            TextView name;
            @Bind(R.id.text_money)
            TextView money;
            @Bind(R.id.text_sold)
            TextView sold;

            public ItemViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
