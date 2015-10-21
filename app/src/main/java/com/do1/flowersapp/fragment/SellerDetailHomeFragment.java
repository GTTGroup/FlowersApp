package com.do1.flowersapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Bruce Too
 * On 10/21/15.
 * At 21:24
 */
public class SellerDetailHomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView view = new TextView(container.getContext());
        view.setText("SellerDetailHomeFragment");
        return view;
    }
}
