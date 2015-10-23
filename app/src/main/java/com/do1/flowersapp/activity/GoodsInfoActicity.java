package com.do1.flowersapp.activity;

import android.app.ActionBar;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.do1.flowersapp.R;
import com.do1.flowersapp.business.http.CommonResp;
import com.do1.flowersapp.business.http.ServerApiClient;
import com.do1.flowersapp.business.http.ServerApiClientCallback;
import com.do1.flowersapp.context.BaseActivity;
import com.do1.flowersapp.widget.CirclePageIndicator;
import com.do1.flowersapp.widget.TBLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by boluo on 2015/10/21.
 * 商品信息
 */
public class GoodsInfoActicity extends BaseActivity implements TBLayout.OnPullListener, TBLayout.OnPageChangedListener {
    private TBLayout mLayout;
    private ScrollView mHeader;
    private ScrollView mFooter;
    private LinearLayout mHeaderContent;
    private LinearLayout mFooterContent;
    private ViewPager viewPager;
    private CirclePageIndicator indicator;
    private WebView wvInfo;
    private List<String> logoList = new ArrayList<>();
    private boolean isLoadWebInfo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        initView();
    }

    private void initView() {
        mLayout = (TBLayout) findViewById(R.id.tblayout);
        mLayout.setOnPullListener(this);
        mLayout.setOnContentChangeListener(this);
        mHeader = (ScrollView) findViewById(R.id.header);
        mFooter = (ScrollView) findViewById(R.id.footer);
        mHeaderContent = (LinearLayout) mHeader.getChildAt(0);
        mFooterContent = (LinearLayout) mFooter.getChildAt(0);
        viewPager = (ViewPager) findViewById(R.id.pager_container);
        indicator = (CirclePageIndicator) findViewById(R.id.indicator_guide);
        wvInfo = (WebView) findViewById(R.id.wv_info);
        WebSettings webSettings = wvInfo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wvInfo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, android.net.http.SslError error) {
//                handler.proceed();
            }
        });
        mLayout.post(new Runnable() {
            @Override
            public void run() {
                int height = mLayout.getHeight();
                wvInfo.setLayoutParams(new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, height));

            }
        });

        logoList.add("http://pic7.nipic.com/20100424/4271569_235714000888_2.jpg");
        logoList.add("http://pic14.nipic.com/20110603/6956730_144355861000_2.jpg");
        logoList.add("http://img5.imgtn.bdimg.com/it/u=815496641,3816560332&fm=21&gp=0.jpg");
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
                SimpleDraweeView imageView = new SimpleDraweeView(GoodsInfoActicity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                String logo = logoList.get(position);
                imageView.setImageURI(Uri.parse(logo));
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                container.addView(imageView, params);
                return imageView;
            }
        });
        indicator.setViewPager(viewPager);
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
        if (mHeader.getScrollY() + mHeader.getHeight() >= mHeaderContent
                .getHeight()) {
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
                break;
            case TBLayout.SCREEN_FOOTER:
                Log.d("tag", "SCREEN_FOOTER");
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

}
