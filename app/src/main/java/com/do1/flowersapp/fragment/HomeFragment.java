package com.do1.flowersapp.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.do1.flowersapp.R;
import com.do1.flowersapp.activity.FloristsInformationActivity;
import com.do1.flowersapp.activity.SellerDetailActivity;
import com.do1.flowersapp.activity.ShopActivity;
import com.do1.flowersapp.business.model.HomeShop;
import com.do1.flowersapp.common.RecyclerArrayAdapter;
import com.do1.flowersapp.context.ModuleFragment;
import com.do1.flowersapp.tools.UITools;
import com.do1.flowersapp.widget.CirclePageIndicator;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gufeng
 * Created on 2015/10/14 00:44
 * 功能作用:
 */
public class HomeFragment extends ModuleFragment {

    private EditText inputCommodityName;
    private RecyclerView recyclerView;
    private ViewPager viewPager;

    private List<HomeShop> shopList;
    @Bind(R.id.btn_message)
    ImageView mTopMessage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inputCommodityName = (EditText) view.findViewById(R.id.input_commodity_name);
        inputCommodityName.setOnEditorActionListener(onEditorActionListener);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        createAdapter();
        mTopMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UITools.intent(getActivity(), SellerDetailActivity.class);
            }
        });
    }

    private void createAdapter() {

        shopList = new ArrayList<>();

        HomeShop homeShop = new HomeShop();
        homeShop.shopLogoResId = R.drawable.img_shop_logo_celebrity;
        homeShop.shopSlogan = getString(R.string.text_home_master_shop_slogan);
        homeShop.shopType = 0;
        shopList.add(homeShop);

        HomeShop homeShop1 = new HomeShop();
        homeShop1.shopLogoResId = R.drawable.img_shop_logo_gifts;
        homeShop1.shopSlogan = getString(R.string.text_home_gifts_shop_slogan);
        homeShop1.shopType = 1;
        shopList.add(homeShop1);

        HomeShop homeShop2 = new HomeShop();
        homeShop2.shopLogoResId = R.drawable.img_shop_logo_master;
        homeShop2.shopSlogan = getString(R.string.text_home_celebrity_shop_slogan);
        homeShop2.shopType = 2;
        shopList.add(homeShop2);

        List<String> logoList = new ArrayList<>();
        logoList.add("http://pic7.nipic.com/20100424/4271569_235714000888_2.jpg");
        logoList.add("http://pic14.nipic.com/20110603/6956730_144355861000_2.jpg");
        logoList.add("http://img5.imgtn.bdimg.com/it/u=815496641,3816560332&fm=21&gp=0.jpg");

        ShopRecyclerAdapter adapter = new ShopRecyclerAdapter(getActivity());
        adapter.setLogoList(logoList);
        recyclerView.setAdapter(adapter);
        adapter.addAll(shopList);
    }

    private  EditText.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

            }
            return false;
        }
    };

    class ShopRecyclerAdapter extends RecyclerArrayAdapter<HomeShop,RecyclerView.ViewHolder> {

        private final int TYPE_HEAD = 0;
        private final int TYPE_ITEM = 1;

        private Context context;
        private List<String> logoList;

        public ShopRecyclerAdapter(Context context) {
            super();
            this.context = context;
            logoList = new ArrayList<>();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_HEAD)
                return new HeaderViewHolder(LayoutInflater.from(context).inflate(R.layout.view_home_header,parent,false),logoList);
            return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item_home,parent,false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (position == 0) {
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            } else {
                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                final HomeShop shop = getItem(position-1);
                itemViewHolder.imgShopLogo.setImageResource(shop.shopLogoResId);
                itemViewHolder.textShopSlogan.setText(shop.shopSlogan);
                itemViewHolder.draweeFirstShopLogo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (shop.shopType == 0) {
                            UITools.intent(getActivity(), ShopActivity.class);
                        } else if(shop.shopType == 1) {

                        } else if(shop.shopType == 2) {
                            UITools.intent(getActivity(), FloristsInformationActivity.class);
                        }
                    }
                });
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0)
                return TYPE_HEAD;
            return TYPE_ITEM;
        }

        @Override
        public int getItemCount() {
            return getCount()+1;
        }

        public void setLogoList(List<String> logoList) {
            this.logoList = logoList;
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private ViewPager viewPager;
        private CirclePageIndicator indicator;
        private List<String> logoList;

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }

        public HeaderViewHolder(View itemView, final List<String> logoList) {
            super(itemView);
            viewPager = (ViewPager) itemView.findViewById(R.id.pager_content);
            indicator = (CirclePageIndicator) itemView.findViewById(R.id.indicator_guide);
            viewPager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return logoList.size();
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((SimpleDraweeView) object);
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    SimpleDraweeView imageView = new SimpleDraweeView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    String logo = logoList.get(position);
                    imageView.setImageURI(Uri.parse(logo));
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
                    container.addView(imageView, params);
                    return imageView;
                }
            });
            indicator.setViewPager(viewPager);
            this.logoList = logoList;
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgShopLogo;
        public TextView textShopSlogan;
        public SimpleDraweeView draweeFirstShopLogo;
        public TextView textFirstShopTitle;
        public TextView textFirstShopPirce;
        public SimpleDraweeView draweeSecondShopLogo;
        public TextView textSecondShopTitle;
        public TextView textSecondShopPirce;
        public SimpleDraweeView draweeThirdShopLogo;
        public TextView textThirdShopTitle;
        public TextView textThirdShopPirce;
        public SimpleDraweeView draweeFourShopLogo;
        public TextView textFourShopTitle;
        public TextView textFourShopPirce;
        public SimpleDraweeView draweeFiveShopLogo;
        public TextView textFiveShopTitle;
        public TextView textFiveShopPirce;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imgShopLogo = (ImageView) itemView.findViewById(R.id.img_shop_logo);
            textShopSlogan = (TextView) itemView.findViewById(R.id.text_shop_slogan);
            draweeFirstShopLogo = (SimpleDraweeView) itemView.findViewById(R.id.drawee_first_flower_logo);
            draweeSecondShopLogo = (SimpleDraweeView) itemView.findViewById(R.id.drawee_second_flower_logo);
            draweeThirdShopLogo = (SimpleDraweeView) itemView.findViewById(R.id.drawee_third_flower_logo);
            draweeFourShopLogo = (SimpleDraweeView) itemView.findViewById(R.id.drawee_four_flower_logo);
            draweeFiveShopLogo = (SimpleDraweeView) itemView.findViewById(R.id.drawee_five_flower_logo);
            textFirstShopTitle = (TextView) itemView.findViewById(R.id.text_first_flower_title);
            textFirstShopPirce = (TextView) itemView.findViewById(R.id.text_first_flower_price);
            textSecondShopTitle = (TextView) itemView.findViewById(R.id.text_second_flower_title);
            textSecondShopPirce = (TextView) itemView.findViewById(R.id.text_second_flower_price);
            textThirdShopTitle = (TextView) itemView.findViewById(R.id.text_second_flower_title);
            textThirdShopPirce = (TextView) itemView.findViewById(R.id.text_third_flower_price);
            textFourShopTitle = (TextView) itemView.findViewById(R.id.text_four_flower_title);
            textFourShopPirce = (TextView) itemView.findViewById(R.id.text_four_flower_price);
            textFiveShopTitle = (TextView) itemView.findViewById(R.id.text_five_flower_title);
            textFiveShopPirce = (TextView) itemView.findViewById(R.id.text_five_flower_price);
        }
    }
}
