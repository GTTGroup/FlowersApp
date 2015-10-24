package com.do1.flowersapp.activity;

import android.app.ActionBar;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.do1.flowersapp.R;
import com.do1.flowersapp.business.http.CommonResp;
import com.do1.flowersapp.business.http.ServerApiClient;
import com.do1.flowersapp.business.http.ServerApiClientCallback;
import com.do1.flowersapp.context.BaseActivity;
import com.do1.flowersapp.tools.ViewHolder;
import com.do1.flowersapp.widget.CirclePageIndicator;
import com.do1.flowersapp.widget.TBLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * Created by boluo on 2015/10/21.
 * 商品信息
 */
public class GoodsInfoActicity extends BaseActivity implements TBLayout.OnPullListener, TBLayout.OnPageChangedListener {
    @Bind(R.id.pager_container)
    ViewPager pagerContainer;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.indicator_guide)
    CirclePageIndicator indicatorGuide;
    @Bind(R.id.tv_goods_name)
    TextView tvGoodsName;
    @Bind(R.id.tv_type_name)
    TextView tvTypeName;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.tv_remain)
    TextView tvRemain;
    @Bind(R.id.tv_sell_num)
    TextView tvSellNum;
    @Bind(R.id.tv_server_area)
    TextView tvServerArea;
    @Bind(R.id.rg_level)
    RadioGroup rgLevel;
    @Bind(R.id.rg_count)
    RadioGroup rgCount;
    @Bind(R.id.rg_state)
    RadioGroup rgState;
    @Bind(R.id.btn_min)
    ImageView btnMin;
    @Bind(R.id.edt_count)
    EditText edtCount;
    @Bind(R.id.btn_plus)
    ImageView btnPlus;
    @Bind(R.id.tv_comment)
    TextView tvComment;
    @Bind(R.id.layout_comment)
    RelativeLayout layoutComment;
    @Bind(R.id.drawee_shop_img)
    SimpleDraweeView draweeShopImg;
    @Bind(R.id.tv_shop_name)
    TextView tvShopName;
    @Bind(R.id.tv_shop_desc)
    TextView tvShopDesc;
    @Bind(R.id.header)
    ScrollView header;
    @Bind(R.id.wv_info)
    WebView wvInfo;
    @Bind(R.id.footer)
    ScrollView footer;
    @Bind(R.id.tblayout)
    TBLayout tblayout;
    @Bind(R.id.btn_top_back)
    ImageView btnTopBack;
    @Bind(R.id.btn_message)
    ImageView btnMessage;
    @Bind(R.id.layout_top)
    RelativeLayout layoutTop;
    @Bind(R.id.tv_store)
    TextView tvStore;
    @Bind(R.id.tv_customer)
    TextView tvCustomer;
    @Bind(R.id.tv_keep)
    TextView tvKeep;
    @Bind(R.id.btn_add_shop_car)
    TextView btnAddShopCar;
    @Bind(R.id.btn_buy)
    TextView btnBuy;
    @Bind(R.id.layout_header)
    LinearLayout layoutHeader;
    @Bind(R.id.layout_footer)
    LinearLayout layoutFooter;
    private ListPopupWindow mPopupWindow;
    private List<String> logoList = new ArrayList<>();
    private boolean isLoadWebInfo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        tblayout.setOnPullListener(this);
        tblayout.setOnContentChangeListener(this);
        WebSettings webSettings = wvInfo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wvInfo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                handler.proceed();
            }
        });
        tblayout.post(new Runnable() {
            @Override
            public void run() {
                int height = tblayout.getHeight();
                wvInfo.setLayoutParams(new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, height));

            }
        });

        logoList.add("http://pic7.nipic.com/20100424/4271569_235714000888_2.jpg");
        logoList.add("http://pic14.nipic.com/20110603/6956730_144355861000_2.jpg");
        logoList.add("http://img5.imgtn.bdimg.com/it/u=815496641,3816560332&fm=21&gp=0.jpg");
        pagerContainer.setAdapter(new PagerAdapter() {
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
                SimpleDraweeView imageView = new SimpleDraweeView(GoodsInfoActicity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                String logo = logoList.get(position);
                imageView.setImageURI(Uri.parse(logo));
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                container.addView(imageView, params);
                return imageView;
            }
        });
        indicatorGuide.setViewPager(pagerContainer);
        ServerApiClient.getInstance().getGoodsInfo(GoodsInfoActicity.this, GoodsInfoActicity.class.getName(), "g01", new ServerApiClientCallback() {
            @Override
            public void onSuccess(CommonResp resp) {
                Log.d("onSuccess", resp.getData().toString());
            }

            @Override
            public void onFail(String serverRespCode, String severRespFail, JsonElement responseString) {
                Log.d("onFail", responseString.toString());
            }

            @Override
            public void onError(int statCode, Header[] headers, String responseString) {
                Log.d("onError", responseString);
            }
        });

        ServerApiClient.getInstance().getGoodsByShop(GoodsInfoActicity.this, GoodsInfoActicity.class.getName(), 10, 1, "s01", 2, "红", new ServerApiClientCallback() {
            @Override
            public void onSuccess(CommonResp resp) {
                Log.d("onSuccess", resp.getData().toString());
            }

            @Override
            public void onFail(String serverRespCode, String severRespFail, JsonElement responseString) {
                Log.d("onFail", responseString.toString());
            }

            @Override
            public void onError(int statCode, Header[] headers, String responseString) {
                Log.d("onError", responseString);
            }
        });
    }

    @Override
    public boolean headerFootReached(MotionEvent event) {
        if (header.getScrollY() + header.getHeight() >= layoutHeader.getHeight()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean footerHeadReached(MotionEvent event) {
        if (wvInfo.getScrollY() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void onPageChanged(int stub) {
        switch (stub) {
            case TBLayout.SCREEN_HEADER:
                Log.d("tag", "SCREEN_HEADER");
                layoutTop.setVisibility(View.VISIBLE);
                break;
            case TBLayout.SCREEN_FOOTER:
                Log.d("tag", "SCREEN_FOOTER");
                layoutTop.setVisibility(View.GONE);
                if (!isLoadWebInfo) {
                    isLoadWebInfo = true;
                    loadWebInfo();
                }
                break;
        }
    }

    private void loadWebInfo() {
        wvInfo.loadUrl("http://www.baidu.com/");
    }

    @OnClick(R.id.btn_message)
    public void messageClick() {
        if (mPopupWindow == null) {
            mPopupWindow = new ListPopupWindow(this);
            mPopupWindow.setAdapter(new BaseAdapter() {
                private String[] items = new String[]{"消息", "首页", "分享"};
                private int[] icons = new int[]{R.drawable.icon_message, R.drawable.icon_home_page, R.drawable.icon_share};

                @Override
                public int getCount() {
                    return items.length;
                }

                @Override
                public Object getItem(int position) {
                    return items[position];
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    ViewHolder holder = ViewHolder.newInstance(parent.getContext(), convertView, parent, R.layout.layout_message_item);
                    holder.setText(R.id.text_desc, items[position]);
                    holder.setImageResource(R.id.image_icon, icons[position]);
                    return holder.getView();
                }
            });
            mPopupWindow.setWidth((int) getResources().getDimension(R.dimen.message_pop_window_width));
            mPopupWindow.setHeight(ListPopupWindow.WRAP_CONTENT);
            mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_msg_bg));
            mPopupWindow.setModal(false);
            mPopupWindow.setAnchorView(btnMessage);
            mPopupWindow.setVerticalOffset((int) getResources().getDimension(R.dimen.top_height) - btnMessage.getBottom());
            mPopupWindow.setHorizontalOffset(-20);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                mPopupWindow.setDropDownGravity(Gravity.END);
            }
            mPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mPopupWindow.dismiss();
                    Toast.makeText(GoodsInfoActicity.this, "点击了第" + position + "个item", Toast.LENGTH_SHORT).show();
                }
            });
            mPopupWindow.show();
        } else {
            if (!mPopupWindow.isShowing()) {
                mPopupWindow.show();
            }
        }
    }
}
