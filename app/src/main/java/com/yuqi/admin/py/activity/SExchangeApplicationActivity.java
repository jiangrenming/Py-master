package com.yuqi.admin.py.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;

/**
 * Created by Administrator on 2017/12/19.
 * 兑换申请
 */
public class SExchangeApplicationActivity extends BaseActivity{
    private TextView ljlxpykf;
    private Dialog msgDialog;
    private TextView dh_fuzhi, dh_hujiao;//复制   呼叫
    Intent intent = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_jfsc_duihuanshenqing);
    }

    @Override
    public String title_text() {
        return "兑换申请";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ljlxpykf:

                msgDialog = new Dialog(SExchangeApplicationActivity.this, R.style.MyDialogStyle);
                // 加载对话框布局
                msgDialog.setContentView(R.layout.item_jf_duihuanshenqing);
                msgDialog.setCanceledOnTouchOutside(true);
                msgDialog.show();

                dh_fuzhi = (TextView) msgDialog.getWindow().findViewById(R.id.dh_fuzhi);
                dh_hujiao = (TextView) msgDialog.getWindow().findViewById(R.id.dh_hujiao);
                /**复制*/
                dh_fuzhi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                    }
                });
                /**呼叫*/
                dh_hujiao.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:18019208990"));
                        startActivity(intent);
                    }
                });

                break;

            case R.id.ljlxpysy:
                Intent intent = new Intent(SExchangeApplicationActivity.this,ShouyActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
