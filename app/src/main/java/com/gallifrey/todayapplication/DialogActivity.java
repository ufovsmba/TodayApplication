package com.gallifrey.todayapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class DialogActivity extends AppCompatActivity {
    private Button mBtnNormal, mBtnList, mBtnSingle, mBtnMulti, mBtnEdit, mBtnCustom;
    private Button mBtnCircle,mBtnhori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        mBtnNormal = findViewById(R.id.btn_normal);
        mBtnList = findViewById(R.id.btn_list);
        mBtnSingle = findViewById(R.id.btn_single);
        mBtnMulti = findViewById(R.id.btn_multi);
        mBtnEdit = findViewById(R.id.btn_edit);
        mBtnCustom = findViewById(R.id.btn_custom);
        mBtnCircle =findViewById(R.id.btn_circle_progress);
        mBtnhori=findViewById(R.id.btn_hori_progress);


        MyDialogClickListener myDialogClickListener = new MyDialogClickListener();
        mBtnCustom.setOnClickListener(myDialogClickListener);
        mBtnNormal.setOnClickListener(myDialogClickListener);
        mBtnSingle.setOnClickListener(myDialogClickListener);
        mBtnEdit.setOnClickListener(myDialogClickListener);
        mBtnMulti.setOnClickListener(myDialogClickListener);
        mBtnList.setOnClickListener(myDialogClickListener);
        mBtnhori.setOnClickListener(myDialogClickListener);
        mBtnCircle.setOnClickListener(myDialogClickListener);
    }


    private class MyDialogClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_normal:
                    normalDialog();
                    break;
                case R.id.btn_list:
                    listDialog();
                    break;
                case R.id.btn_single:
                    singleDialog();
                    break;
                case R.id.btn_multi:
                    multiDialog();
                    break;
                case R.id.btn_edit:
                    editDialog();
                    break;
                case R.id.btn_custom:
                    customDialog();
                    break;
                case R.id.btn_hori_progress:
                    horiProgressDialog();
                    break;
                case R.id.btn_circle_progress:
                    circleProgressDialog();
            }
        }
        
    }

    private void circleProgressDialog() {
        ProgressDialog circleD=new ProgressDialog(this);
        circleD.setIcon(R.mipmap.icon_dialog);
        circleD.setTitle("???????????????");
        circleD.setMessage("???????????????.......");
        circleD.setProgressStyle(ProgressDialog.STYLE_SPINNER);//??????????????????????????????????????????
        circleD.show();
    }

    private void horiProgressDialog() {
        ProgressDialog horiProgress=new ProgressDialog(this);
        horiProgress.setIcon(R.mipmap.icon_dialog);
        horiProgress.setTitle("???????????????");
        horiProgress.setMessage("???????????????......");
        horiProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        horiProgress.setProgress(0);//???????????????
        horiProgress.setMax(100);//???????????????
        horiProgress.setSecondaryProgress(3);//???????????????
        horiProgress.setButton(DialogInterface.BUTTON_POSITIVE, "??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DialogActivity.this,"????????????",Toast.LENGTH_LONG);
                dialogInterface.cancel();
            }
        });
        horiProgress.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(horiProgress.getProgress()<horiProgress.getMax()){
                    try {
                        Thread.sleep(100);
                        horiProgress.incrementProgressBy(1);//???100????????????1
                        horiProgress.incrementSecondaryProgressBy(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void customDialog() {
        View view= LayoutInflater.from(this).inflate(R.layout.layout_custom,null,false);
        AlertDialog.Builder customD=new AlertDialog.Builder(this);
        EditText etUser=view.findViewById(R.id.et_user);
        Button mBtnCustomOk=view.findViewById(R.id.btn_custom_ok);
        customD.setView(view).show();
        mBtnCustomOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DialogActivity.this,etUser.getText(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void editDialog() {
        AlertDialog.Builder editD=new AlertDialog.Builder(this);
        EditText editText=new EditText(this);
        editD.setIcon(R.mipmap.icon_dialog)
                .setTitle("???????????????")
                .setView(editText)
                .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DialogActivity.this,"??????: "+editText.getText(),Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

    ArrayList<Integer> choices =new ArrayList<>();
    private void multiDialog() {

        String[] items={"??????","??????","??????","??????"};
        boolean[] checkedItems={true,false,false,true};
        choices.clear();
        choices.add(0);
        choices.add(3);
        AlertDialog.Builder multiD=new AlertDialog.Builder(this);
        multiD.setIcon(R.mipmap.icon_dialog)
                .setTitle("???????????????")
                .setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b){
                            choices.add(i);
                        }else{
                            choices.remove(Integer.valueOf(i));
                        }
                    }
                }).setPositiveButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StringBuilder str=new StringBuilder();
                for (int j = 0; j < choices.size(); j++) {
                    str.append(items[choices.get(j)]+" ");
                }
                Toast.makeText(DialogActivity.this,"???????????????"+str,Toast.LENGTH_LONG).show();
            }
        }).show();
    }

    private void singleDialog() {
        String[] items ={"??????","??????","??????","?????????"};
        AlertDialog.Builder singleD=new AlertDialog.Builder(this);
        final int[] choice = new int[1];
        singleD.setIcon(R.mipmap.icon_dialog)
                .setTitle("???????????????")
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        choice[0] =i;

                    }
                }).setPositiveButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DialogActivity.this,"??????????????? "+items[choice[0]],Toast.LENGTH_LONG).show();
            }
        }).show();
    }

    private void listDialog() {
        String[] items={"??????","??????","??????","??????","??????","??????"};
        AlertDialog.Builder listD=new AlertDialog.Builder(this);
        listD.setIcon(R.mipmap.icon_dialog2)
                .setTitle("???????????????")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DialogActivity.this,"???: "+items[i],Toast.LENGTH_LONG).show();
                    }
                }).show();

    }


    private void normalDialog() {
        AlertDialog.Builder normalD = new AlertDialog.Builder(this);
        normalD.setIcon(R.mipmap.icon_dialog)
                .setTitle("???????????????")
                .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DialogActivity.this, "Ok", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DialogActivity.this, "OK", Toast.LENGTH_LONG).show();
            }
        }).setNeutralButton("?????????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DialogActivity.this, "OK", Toast.LENGTH_LONG).show();
            }
        }).show();
    }
}