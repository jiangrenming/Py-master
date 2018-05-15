package com.yuqi.admin.py.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yuqi.admin.py.R;

import java.io.File;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Admin on 2017/12/1.
 */

public class DialogUtil {

    private static ProgressDialog pd;
    public static void start(Context context){
        pd = new ProgressDialog(context);
        pd.setMessage("请求数据中，请稍后。。");
        pd.show();;
    }
    public static void finish(){
        pd.cancel();
    }

}
