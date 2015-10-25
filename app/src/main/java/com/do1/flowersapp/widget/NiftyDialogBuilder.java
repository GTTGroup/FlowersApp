package com.do1.flowersapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.do1.flowersapp.R;
import com.do1.flowersapp.tools.BaseEffects;
import com.do1.flowersapp.tools.DisplayUtil;
import com.do1.flowersapp.tools.Effectstype;


public class NiftyDialogBuilder extends Dialog implements DialogInterface {

    private Effectstype type = Effectstype.SlideBottom;

    private RelativeLayout mRelativeLayoutView;

    private LinearLayout mLinearLayoutMsgView;

    private LinearLayout mLinearLayoutTopView;

    private FrameLayout mFrameLayoutCustomView;

    private View mDialogView;

    private TextView mTitle;

    private TextView mMessage;

    private Button mBtnPositive;

    private Button mBtnNegative;

    private boolean isCancelable=true;

    private int mDuration = 250;

    public NiftyDialogBuilder(Context context) {
        super(context, R.style.dialog_tran);
        init(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width  = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes((WindowManager.LayoutParams) params);
    }

    private void init(Context context) {
        mDialogView = View.inflate(context, R.layout.layout_effect_dialog, null);
        
        mRelativeLayoutView=(RelativeLayout)mDialogView.findViewById(R.id.layout_dialog);
        mLinearLayoutTopView=(LinearLayout)mDialogView.findViewById(R.id.layout_dialog_title);
        mLinearLayoutMsgView=(LinearLayout)mDialogView.findViewById(R.id.layout_dialog_content);
        mFrameLayoutCustomView=(FrameLayout)mDialogView.findViewById(R.id.layout_dialog_custom);
        
        mTitle = (TextView) mDialogView.findViewById(R.id.text_dialog_title);
        mMessage = (TextView) mDialogView.findViewById(R.id.text_dialog_message);
        mBtnPositive=(Button)mDialogView.findViewById(R.id.btn_dialog_positive);
        mBtnNegative=(Button)mDialogView.findViewById(R.id.btn_dialog_negative);

        setContentView(mDialogView);
        
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)mRelativeLayoutView.getLayoutParams();
        params.width = Math.min(DisplayUtil.getDeviceWidth(getContext()), DisplayUtil.getDeviceHeight(getContext()));
        params.gravity = Gravity.CENTER;
        mRelativeLayoutView.setLayoutParams(params);
        
        this.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                start(type);
            }
        });

        mRelativeLayoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCancelable) {
                    dismiss();
                }
            }
        });
    }
    
    public TextView getMessageTextView() {
    	return mMessage;
    }

    public NiftyDialogBuilder withTitle(CharSequence title) {
        toggleView(mLinearLayoutTopView, title);
        mTitle.setText(title);
        return this;
    }

    public NiftyDialogBuilder withTitleColor(String colorString) {
        mTitle.setTextColor(Color.parseColor(colorString));
        return this;
    }

    public NiftyDialogBuilder withMessage(int textResId) {
        toggleView(mLinearLayoutMsgView,textResId);
        mMessage.setText(textResId);
        return this;
    }

    public NiftyDialogBuilder withMessage(CharSequence msg) {
        toggleView(mLinearLayoutMsgView,msg);
        mMessage.setText(msg);
        return this;
    }
    
    public NiftyDialogBuilder withMessageColor(int color) {
    	if (color != -1) {
    		mMessage.setTextColor(color);
		}
        return this;
    }
    
    public NiftyDialogBuilder withMessageColor(String colorString) {
        mMessage.setTextColor(Color.parseColor(colorString));
        return this;
    }

    public NiftyDialogBuilder withDuration(int duration) {
        this.mDuration=duration;
        return this;
    }

    public NiftyDialogBuilder withEffect(Effectstype type) {
        this.type=type;
        return this;
    }
    
    public NiftyDialogBuilder withButtonDrawable(int resid) {
        mBtnPositive.setBackgroundResource(resid);
        mBtnNegative.setBackgroundResource(resid);
        return this;
    }
    
    public NiftyDialogBuilder withPositiveText(CharSequence text) {
    	mBtnPositive.setVisibility(View.VISIBLE);
    	mBtnPositive.setText(text);

        return this;
    }

    public NiftyDialogBuilder withNegativeText(CharSequence text) {
        mBtnNegative.setVisibility(View.VISIBLE);
        mBtnNegative.setText(text);
        return this;
    }
    
    public NiftyDialogBuilder setPositiveButtonClick(View.OnClickListener click) {
    	mBtnPositive.setOnClickListener(click);
        return this;
    }

    public NiftyDialogBuilder setNegativeButtonClick(View.OnClickListener click) {
        mBtnNegative.setOnClickListener(click);
        return this;
    }


    public NiftyDialogBuilder setCustomView(int resId, Context context) {
        mFrameLayoutCustomView.setVisibility(View.VISIBLE);
    	mLinearLayoutMsgView.setVisibility(View.GONE);
        View customView = View.inflate(context, resId, null);
        if (mFrameLayoutCustomView.getChildCount()>0){
            mFrameLayoutCustomView.removeAllViews();
        }
        mFrameLayoutCustomView.addView(customView);
        return this;
    }

    public NiftyDialogBuilder setCustomView(View view) {
        mFrameLayoutCustomView.setVisibility(View.VISIBLE);
    	mLinearLayoutMsgView.setVisibility(View.GONE);
        if (mFrameLayoutCustomView.getChildCount()>0){
            mFrameLayoutCustomView.removeAllViews();
        }
        mFrameLayoutCustomView.addView(view);

        return this;
    }

    public NiftyDialogBuilder isCancelableOnTouchOutside(boolean cancelable) {
        this.isCancelable=cancelable;
        this.setCanceledOnTouchOutside(cancelable);
        return this;
    }

    public NiftyDialogBuilder isCancelable(boolean cancelable) {
        this.isCancelable=cancelable;
        this.setCancelable(cancelable);
        return this;
    }

    private void toggleView(View view,Object obj){
        if (obj==null){
            view.setVisibility(View.GONE);
        }else {
            view.setVisibility(View.VISIBLE);
        }
    }

    private void start(Effectstype type){
        BaseEffects animator = type.getAnimator();
        animator.setDuration(Math.abs(mDuration));
        animator.start(mRelativeLayoutView);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        mBtnPositive.setVisibility(View.GONE);
        mBtnNegative.setVisibility(View.GONE);
    }
}
