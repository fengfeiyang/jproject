package com.example.retrofithttp.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.retrofithttp.R;


/**
 * 作者：junj
 * 时间：2016/8/12
 * 描述：TOTO
 */
public class NetDialog extends AlertDialog {
    private Context context;
    private LayoutInflater inflater;
    private String mMes;

    public NetDialog(Context context, String mes) {
        super(context, R.style.NetDialog);
        this.context = context;
        mMes = mes;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public NetDialog(Context context) {
        super(context, R.style.NetDialog);
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.proccess, null);
        TextView tvInfo = (TextView) view.findViewById(R.id.tv_info);
        setContentView(view);
        setCanceledOnTouchOutside(false);
        if (mMes != null && !TextUtils.isEmpty(mMes))
            tvInfo.setText(mMes +"...");
    }
}
