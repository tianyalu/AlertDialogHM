package com.sty.alert.dialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnNormalDialog;
    private Button btnSingleChooseDialog;
    private Button btnMultiChooseDialog;
    private Button btnProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setListeners();
    }

    private void initViews(){
        btnNormalDialog = findViewById(R.id.btn_normal_dialog);
        btnSingleChooseDialog = findViewById(R.id.btn_single_choose_dialog);
        btnMultiChooseDialog = findViewById(R.id.btn_multi_choose_dialog);
        btnProgressDialog = findViewById(R.id.btn_progress_dialog);
    }

    private void setListeners(){
        btnNormalDialog.setOnClickListener(this);
        btnSingleChooseDialog.setOnClickListener(this);
        btnMultiChooseDialog.setOnClickListener(this);
        btnProgressDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_normal_dialog:
                showNormalDialog();
                break;
            case R.id.btn_single_choose_dialog:
                showSingleChooseDialog();
                break;
            case R.id.btn_multi_choose_dialog:
                showMultiChooseDialog();
                break;
            case R.id.btn_progress_dialog:
                showProgressDialog();
                break;
            default:
                break;
        }
    }

    //点击按钮，弹出一个正常对话框
    private void showNormalDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("警告");
        builder.setMessage("世界上最遥远的距离就是没有网络");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("Tag", "确定按钮被点击了");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("Tag", "取消按钮被点击了");
            }
        });
        builder.show();
    }

    //点击按钮，弹出一个单选对话框
    private void showSingleChooseDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择您喜欢的课");
        final String items[] = {"Android", "IOS", "php", "C", "C++", "C#", "HTML"};
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "您选中了：" + items[which], Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.show();
    }

    //点击按钮，弹出一个多选对话框
    private void showMultiChooseDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择您喜欢吃的水果");
        final String items[] = {"榴莲", "苹果", "葡萄", "香蕉", "黄瓜", "桃子", "梨"};
        final boolean[] checkedItems = { true, false, false, false, false, false, true};
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                //checkedItems[which] = !checkedItems[which];
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuffer sb = new StringBuffer();
                for(int i = 0; i < checkedItems.length; i++){
                    if(checkedItems[i]){
                        String fruit = items[i];
                        sb.append(fruit + " ");
                    }
                }
                Toast.makeText(MainActivity.this, "您选择的水果为：" + sb, Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    //点击按钮，弹出Progress对话框
    private void showProgressDialog(){
        //与进度相关的控件都可以直接在子线程中更新UI
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("正在玩命加载中...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //设置进度的最大值
        dialog.setMax(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <= 100; i ++){
                    //设置当前的进度
                    SystemClock.sleep(50); //睡眠50ms
                    dialog.setProgress(i);
                }
            }
        }).start();
        dialog.show();
    }
}
