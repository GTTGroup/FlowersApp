package com.do1.flowersapp.tools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.do1.flowersapp.widget.NiftyDialogBuilder;
import com.do1.flowersapp.widget.NiftyProgressDialogBuilder;

/**
 * Created by gufeng
 * Created on 2015/10/16 01:05
 * 功能作用:
 */
public class UITools {

    public static void intent(Context context,Class activityClass) {
        Intent intent = new Intent();
        intent.setClass(context, activityClass);
        context.startActivity(intent);
    }

    public static void intent(Context context,Class activityClass,Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context,activityClass);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static NiftyDialogBuilder showNiftyDialog(NiftyDialogBuilder niftyDialog,String title,String message,int messageColor,CharSequence positiveButtontxt, View.OnClickListener positiveListener, boolean cancelable) {
        niftyDialog.isCancelable(cancelable)
                .isCancelableOnTouchOutside(cancelable)
                .withTitle(title)
                .withMessage(message)
                .withMessageColor(messageColor)
                .withPositiveText(positiveButtontxt)
                .setPositiveButtonClick(positiveListener);
        niftyDialog.show();
        return niftyDialog;
    }

    public static NiftyDialogBuilder showNiftyDialog(NiftyDialogBuilder niftyDialog,String title,String message,CharSequence positiveButtontxt, View.OnClickListener positiveListener, boolean cancelable) {
        niftyDialog.isCancelable(cancelable)
                .isCancelableOnTouchOutside(cancelable)
                .withTitle(title)
                .withMessage(message)
                .withPositiveText(positiveButtontxt)
                .setPositiveButtonClick(positiveListener);
        niftyDialog.show();
        return niftyDialog;
    }

    public static NiftyDialogBuilder showNiftyDialog(NiftyDialogBuilder niftyDialog,String title,CharSequence message,CharSequence positiveButtontxt, View.OnClickListener positiveListener, CharSequence negativeButtontxt,
                                                     View.OnClickListener negativeListener, boolean cancelable) {
        niftyDialog.isCancelable(cancelable)
                .isCancelableOnTouchOutside(cancelable)
                .withTitle(title)
                .withMessage(message)
                .withPositiveText(positiveButtontxt)
                .setPositiveButtonClick(positiveListener)
                .withNegativeText(negativeButtontxt)
                .setNegativeButtonClick(negativeListener);
        niftyDialog.show();
        return niftyDialog;
    }

    public static NiftyDialogBuilder showCustomNiftyDialog(NiftyDialogBuilder niftyDialog,String title,String message,View view,boolean cancelable) {
        niftyDialog.isCancelable(cancelable)
                .isCancelableOnTouchOutside(cancelable)
                .withTitle(title)
                .withMessage(message)
                .setCustomView(view);
        niftyDialog.show();
        return niftyDialog;
    }

    public static NiftyDialogBuilder showCustomNiftyDialog(NiftyDialogBuilder niftyDialog,String title,String message,View view,CharSequence positiveButtontxt, View.OnClickListener positiveListener, boolean cancelable) {
        niftyDialog.isCancelable(cancelable)
                .isCancelableOnTouchOutside(cancelable)
                .withTitle(title)
                .withMessage(null)
                .setCustomView(view)
                .withPositiveText(positiveButtontxt)
                .setPositiveButtonClick(positiveListener);
        niftyDialog.show();
        return niftyDialog;
    }

    public static NiftyDialogBuilder showCustomNiftyDialog(NiftyDialogBuilder niftyDialog,String title,String message,View view,CharSequence positiveButtontxt, View.OnClickListener positiveListener, CharSequence negativeButtontxt,
                                                           View.OnClickListener negativeListener, boolean cancelable) {
        niftyDialog.isCancelable(cancelable)
                .isCancelableOnTouchOutside(cancelable)
                .withTitle(title)
                .withMessage(null)
                .setCustomView(view)
                .withPositiveText(positiveButtontxt)
                .setPositiveButtonClick(positiveListener)
                .withNegativeText(negativeButtontxt)
                .	setNegativeButtonClick(negativeListener);
        niftyDialog.show();
        return niftyDialog;
    }

    public static void createNiftyProgressDialog(NiftyProgressDialogBuilder niftyDialog,String message,boolean cancelable) {
        niftyDialog.isCancelable(cancelable)
                .isCancelableOnTouchOutside(cancelable)
                .withMessage(message);
        niftyDialog.show();
    }

}
