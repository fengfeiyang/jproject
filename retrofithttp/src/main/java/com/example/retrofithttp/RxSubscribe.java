package com.example.retrofithttp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Display;
import android.view.WindowManager;

import com.example.retrofithttp.dialog.DialogClick2;
import com.example.retrofithttp.dialog.NetDialog;
import com.example.retrofithttp.dialog.NonNetworkDialog;
import com.example.retrofithttp.utils.PhoneUtil;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * 作者：junj
 * 时间：2016/8/12
 * 描述：TOTO
 */
public abstract class RxSubscribe<T> implements Observer<T> {
    private boolean showDialog = true;
    private Context   mContext;
    private NetDialog dialog;
    private String    msg;


    /**
     * @param context context
     * @param msg     dialog message
     */
    public RxSubscribe (Context context, String msg) {
        this.mContext = context;
        this.msg = msg;
    }

    /**
     * @param context context
     */
    public RxSubscribe (Context context) {
        this(context, "加载中");
    }

    public RxSubscribe (Context context, boolean showDailog) {
        this(context, "加载中");
        this.showDialog = showDailog;
    }


    @Override
    public void onComplete () {
        if (showDialog)
            dialog.dismiss();
    }

    @Override
    public void onSubscribe (@NonNull final Disposable d) {
        if (showDialog) {
            dialog = new NetDialog(mContext);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel (DialogInterface dialog) {
                    if (!d.isDisposed()) {
                        d.dispose();
                    }
                }
            });
            dialog.show();
        }
    }


    @Override
    public void onNext (T t) {
        _onNext(t);
    }

    @Override
    public void onError (Throwable e) {
        e.printStackTrace();
        if (PhoneUtil.getNetworkType(mContext) == 0) {
//            _onError(e, "网络不可用");
            NonNetworkDialog nonNetworkDialog = new NonNetworkDialog(mContext, new DialogClick2() {
                @Override
                public void dialogOk () {
                    if (showDialog) {
                        ((Activity) mContext).finish();
                    }
                }

                @Override
                public void dialogCancel () {
                    if (showDialog) {
                        ((Activity) mContext).finish();
                    }
                }
            });
            nonNetworkDialog.show();
            WindowManager windowManager = ((Activity) mContext).getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            WindowManager.LayoutParams lp = nonNetworkDialog.getWindow().getAttributes();
            lp.width = (int) (display.getWidth()); //设置宽度
            lp.height = (int) (display.getHeight()); //设置高度
            nonNetworkDialog.getWindow().setAttributes(lp);

        } else if (e instanceof ServerException) {
            if ("1001".equals(((ServerException) e).getCode())) {//token错误
                Intent intent = new Intent();
                intent.setAction("action:com.baotianqi.valpromise.LoginActivity");
                mContext.startActivity(intent);
            } else {
                _onError(e, e.getMessage());
            }
        } else if (e instanceof NullPointerException) {
            String message = e.getMessage();
            if ("data为空".equals(message)) {
                _onNext(null);
            }
        } else {
            _onError(e, "噢噢，网络连接失败，请稍后再试");
        }
        if (showDialog)
            dialog.dismiss();
    }

    protected abstract void _onNext (T t);

    protected abstract void _onError (Throwable e, String message);

}
