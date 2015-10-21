package com.do1.flowersapp.context;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.do1.flowersapp.R;

/**
 * Created by gufeng
 * Created on 2015/10/14 00:44
 * 功能作用: Fragment基类
 */
public class ModuleFragment extends Fragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        adjustTopHeight(view);
    }

    protected void adjustTopHeight(View view) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int topHeight = getResources().getDimensionPixelSize(R.dimen.top_height_systembartint);
            int topMargin = getResources().getDimensionPixelSize(R.dimen.top_margin);
            LinearLayout flTop = (LinearLayout) view.findViewById(R.id.ll_top);
            flTop.getLayoutParams().height = topHeight;
            int childCount = flTop.getChildCount();
            if(childCount > 0) {
                for(int index = 0;index<childCount;index++) {
                    View childView = flTop.getChildAt(index);
                    ((LinearLayout.LayoutParams)childView.getLayoutParams()).topMargin = topMargin;
                }
            }
        }
    }
}
