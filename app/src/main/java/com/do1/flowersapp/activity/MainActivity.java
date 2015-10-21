package com.do1.flowersapp.activity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.do1.flowersapp.R;
import com.do1.flowersapp.context.BaseActivity;
import com.do1.flowersapp.context.ModuleFragment;
import com.do1.flowersapp.fragment.CategroyFragment;
import com.do1.flowersapp.fragment.HomeFragment;
import com.do1.flowersapp.fragment.PurchaseBillFragment;
import com.do1.flowersapp.fragment.PersonalFragment;

/**
 * Created by gufeng
 * Created on 2015/10/14 00:01
 * 功能作用: 首页
 */
public class MainActivity extends BaseActivity {

    private final String TAB_HOME = "home";
    private final String TAB_CATEGORY = "category";
    private final String TAB_ORDER = "order";
    private final String TAB_PERSONAL = "personal";

    private final int positon_home = 0;
    private final int position_categroy = 1;
    private final int position_live = 2;
    private final int position_personal = 3;

    private RelativeLayout tabHome;
    private RelativeLayout tabCategory;
    private RelativeLayout tabOrder;
    private RelativeLayout tabPersonal;

    private TextView textHome;
    private TextView textCategory;
    private TextView textOrder;
    private TextView textPersonal;

    private long mExitTime = 0;
    private int tabPosition = -1;
    private SparseArray<ModuleFragment> tabs;
    private Fragment currentFragment;

    private IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, getString(R.string.exit_main), Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }

            return false;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tabposition", tabPosition);
    }

    private void init(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        initView();
        tabs = new SparseArray<>();
        if(null == savedInstanceState) {
            instantiateFragment(positon_home);
        } else {
            restoreFragment(savedInstanceState);
        }
    }

    private void initView() {
        tabHome = (RelativeLayout) findViewById(R.id.tab_home);
        tabCategory = (RelativeLayout) findViewById(R.id.tab_category);
        tabOrder = (RelativeLayout) findViewById(R.id.tab_order);
        tabPersonal = (RelativeLayout) findViewById(R.id.tab_personal);

        tabHome.setOnClickListener(bottomClickListener);
        tabCategory.setOnClickListener(bottomClickListener);
        tabOrder.setOnClickListener(bottomClickListener);
        tabPersonal.setOnClickListener(bottomClickListener);

        textHome = (TextView) findViewById(R.id.text_tab_home);
        textCategory = (TextView) findViewById(R.id.text_tab_category);
        textOrder = (TextView) findViewById(R.id.text_tab_order);
        textPersonal = (TextView) findViewById(R.id.text_tab_personal);
    }

    private void restoreFragment(Bundle savedInstanceState) {
        tabPosition = savedInstanceState.getInt("tabposition",positon_home);
        setSelectedTab(tabPosition);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = (HomeFragment) fragmentManager.findFragmentByTag(TAB_HOME);
        CategroyFragment columnFragment = (CategroyFragment) fragmentManager.findFragmentByTag(TAB_CATEGORY);
        PurchaseBillFragment liveFragment = (PurchaseBillFragment) fragmentManager.findFragmentByTag(TAB_ORDER);
        PersonalFragment personalFragment = (PersonalFragment) fragmentManager.findFragmentByTag(TAB_PERSONAL);
        if(null != homeFragment) {
            restoreFragment(positon_home,homeFragment,fragmentTransaction);
        }
        if (null != columnFragment) {
            restoreFragment(position_categroy,columnFragment,fragmentTransaction);
        }
        if (null != liveFragment) {
            restoreFragment(position_live,liveFragment,fragmentTransaction);
        }
        if (null != personalFragment) {
            restoreFragment(position_personal,personalFragment,fragmentTransaction);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void restoreFragment(int position,ModuleFragment fragment,FragmentTransaction fragmentTransaction) {
        tabs.put(position, fragment);
        if(tabPosition == position_personal) {
            currentFragment = fragment;
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.hide(fragment);
        }
    }

    private void instantiateFragment(int position) {
        if (tabPosition != position) {
            tabPosition = position;
            setSelectedTab(tabPosition);
            ModuleFragment fragment = tabs.get(position);
            String tag = TAB_HOME;
            if (null == fragment) {
                switch (position) {
                    case positon_home:
                        tag = TAB_HOME;
                        fragment = new HomeFragment();
                        break;
                    case position_categroy:
                        tag = TAB_CATEGORY;
                        fragment = new CategroyFragment();
                        break;
                    case position_live:
                        tag = TAB_ORDER;
                        fragment = new PurchaseBillFragment();
                        break;
                    case position_personal:
                        tag = TAB_PERSONAL;
                        fragment = new PersonalFragment();
                        break;
                }
                tabs.put(position, fragment);
            }
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if(null != currentFragment) {
                fragmentTransaction.hide(currentFragment);
            }
            if(fragment.isAdded()) {
                fragmentTransaction.show(fragment);
            } else {
                fragmentTransaction.add(R.id.container,fragment,tag);
            }
            fragmentTransaction.commitAllowingStateLoss();
            currentFragment = fragment;
        }
    }

    private void setSelectedTab(int position) {
        textHome.setSelected(false);
        textCategory.setSelected(false);
        textOrder.setSelected(false);
        textPersonal.setSelected(false);

        switch (position) {
            case 0:
                textHome.setSelected(true);
                break;
            case 1:
                textCategory.setSelected(true);
                break;
            case 2:
                textOrder.setSelected(true);
                break;
            case 3:
                textPersonal.setSelected(true);
                break;
        }
    }

    private View.OnClickListener bottomClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            if (v == tabHome) {
                instantiateFragment(positon_home);
            } else if( v == tabCategory) {
                instantiateFragment(position_categroy);
            } else if(v == tabOrder) {
                instantiateFragment(position_live);
            } else if(v == tabPersonal) {
                instantiateFragment(position_personal);
            }
        }
    };
}
