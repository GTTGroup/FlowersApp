/**  company:   www.163.com
 *                      在线游戏事业部 
 *                      回声工作室
 *                      程序组
 *    工作编号:    G7525
 *    creator:      谷峰
 *    create on:  2014年8月15日   下午3:37:33
 *          坚持不懈,终会成功
 */
package com.do1.flowersapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.do1.flowersapp.R;
import com.do1.flowersapp.tools.BaseEffects;
import com.do1.flowersapp.tools.Effectstype;
import com.do1.flowersapp.tools.StringUtil;

/**
 *
 */
public class NiftyProgressDialogBuilder extends Dialog implements DialogInterface {

    private Effectstype type = Effectstype.SlideBottom;

    private RelativeLayout mRelativeLayoutView;

    private View mDialogView;

    private ProgressWheel mProgressBar;
    private TextView mMessage;

    private int mDuration = 250;

    public NiftyProgressDialogBuilder(Context context) {
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
        mDialogView = View.inflate(context, R.layout.layout_effect_progress_dialog, null);
        
        mRelativeLayoutView=(RelativeLayout)mDialogView.findViewById(R.id.layout_dialog);
        mProgressBar = (ProgressWheel) mDialogView.findViewById(R.id.pb_dialog);
        mProgressBar.setRimColor(Color.LTGRAY);
        mMessage = (TextView) mDialogView.findViewById(R.id.text_dialog_message);
        
        setContentView(mDialogView);
        this.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                start(type);
            }
        });
    }

    public NiftyProgressDialogBuilder withDuration(int duration) {
        this.mDuration=duration;
        return this;
    }

    public NiftyProgressDialogBuilder withEffect(Effectstype type) {
        this.type=type;
        return this;
    }
    
    public NiftyProgressDialogBuilder withMessage(String msg) {
        if(StringUtil.isNotEmpty(msg)) {
            mMessage.setVisibility(View.VISIBLE);
            mMessage.setText(msg);
        }
        return this;
    }
    
    public NiftyProgressDialogBuilder isCancelableOnTouchOutside(boolean cancelable) {
        this.setCanceledOnTouchOutside(cancelable);
        return this;
    }

    public NiftyProgressDialogBuilder isCancelable(boolean cancelable) {
        this.setCancelable(cancelable);
        return this;
    }

    private void start(Effectstype type){
        BaseEffects animator = type.getAnimator();
        animator.setDuration(Math.abs(mDuration));
        animator.start(mRelativeLayoutView);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        mProgressBar.stopSpinning();
    }

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
	}
}
