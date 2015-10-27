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
import com.do1.flowersapp.activity.GoodsInfoActicity;
import com.do1.flowersapp.activity.SellerDetailActivity;
import com.do1.flowersapp.business.http.CommonResp;
import com.do1.flowersapp.business.http.ServerApiClient;
import com.do1.flowersapp.business.http.ServerApiClientCallback;
import com.do1.flowersapp.business.model.HomeAd;
import com.do1.flowersapp.business.model.HomeShop;
import com.do1.flowersapp.common.RecyclerArrayAdapter;
import com.do1.flowersapp.constants.ServerConstant;
import com.do1.flowersapp.context.ModuleFragment;
import com.do1.flowersapp.tools.UITools;
import com.do1.flowersapp.widget.CirclePageIndicator;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * Created by gufeng
 * Created on 2015/10/14 00:44
 * 功能作用:
 */
public class HomeFragment extends ModuleFragment {

    @Bind(R.id.input_commodity_name) EditText inputCommodityName;
    @Bind(R.id.recycler_content) RecyclerView recyclerView;

    private ShopRecyclerAdapter adapter;

    private List<HomeShop> shopList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shopList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inputCommodityName.setOnEditorActionListener(onEditorActionListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        createAdapter();

        //首页轮播广告
        ServerApiClient.getInstance().getHomeAdList(getActivity(), getClass().getName(), new ServerApiClientCallback() {
            @Override
            public void onSuccess(CommonResp resp) {
                HomeAd homeAd = new Gson().fromJson(resp.getData(), HomeAd.class);
                if (null != homeAd && homeAd.getList().size() > 0) {
                    adapter = new ShopRecyclerAdapter(getActivity());
                    adapter.setAdList(homeAd.getList());
                    recyclerView.setAdapter(adapter);
                    adapter.addAll(shopList);
                }
            }

            @Override
            public void onError(int statCode, Header[] headers, String responseString) {

            }

            @Override
            public void onFail(String serverRespCode, String severRespFail, JsonElement responseString) {
            }
        });

        //首页店铺
        ServerApiClient.getInstance().getHomeShop(getActivity(), getClass().getName(), "5", "1", new ServerApiClientCallback() {
            @Override
            public void onSuccess(CommonResp resp) {

            }

            @Override
            public void onFail(String serverRespCode, String severRespFail, JsonElement responseString) {

            }

            @Override
            public void onError(int statCode, Header[] headers, String responseString) {

            }
        });
    }

    private void createAdapter() {
        adapter = new ShopRecyclerAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        adapter.addAll(shopList);
    }

    @OnClick(R.id.btn_message) void openMessageList() {
        UITools.intent(getActivity(),SellerDetailActivity.class);
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
        private List<HomeAd.Ad> adList;

        public ShopRecyclerAdapter(Context context) {
            super();
            this.context = context;
            adList = new ArrayList<>();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_HEAD)
                return new HeaderViewHolder(LayoutInflater.from(context).inflate(R.layout.view_home_header,parent,false),adList);
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
                            UITools.intent(getActivity(), SellerDetailActivity.class);
                        } else if(shop.shopType == 1) {
                            UITools.intent(getActivity(), GoodsInfoActicity.class);
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

        public void setAdList(List<HomeAd.Ad> adList) {
            this.adList = adList;
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private ViewPager viewPager;
        private CirclePageIndicator indicator;

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }

        public HeaderViewHolder(View itemView, final List<HomeAd.Ad> adList) {
            super(itemView);
            viewPager = (ViewPager) itemView.findViewById(R.id.pager_content);
            indicator = (CirclePageIndicator) itemView.findViewById(R.id.indicator_guide);
            viewPager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return adList.size();
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
                    HomeAd.Ad ad = adList.get(position);
                    imageView.setImageURI(Uri.parse(ServerConstant.API_URL + ServerConstant.SCHEME + ad.getImgPath()));
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                    container.addView(imageView, params);
                    return imageView;
                }
            });
            indicator.setViewPager(viewPager);
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
