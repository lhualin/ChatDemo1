package com.findu.chattest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button btn,btn1,btn2,btn3;
    final  Context context=this;
    EditText editText;
    Boolean aBoolean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv=(TextView)findViewById(R.id.tv);
        btn=(Button)findViewById(R.id.btn);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        editText=(EditText)findViewById(R.id.editTxt) ;
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.jiepin);
//根据Bitmap对象创建ImageSpan对象
                ImageSpan imageSpan=new ImageSpan(context,bitmap);
//创建一个SpinnableString对象，以便插入ImageSpan对象封装的图像
                SpannableString spannableString=new SpannableString("replace");
//用ImageSpan对象替换replace字符串
                spannableString.setSpan(imageSpan,0,7, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                tv.setText(spannableString);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String html="<img src='"+R.mipmap.msg_img+"'/>";
                CharSequence reslut= Html.fromHtml(html, new Html.ImageGetter() {
                    @Override
                    public Drawable getDrawable(String s) {
                        //获取资源ID
                        int resId=Integer.parseInt(s);
                        //装载图像资源
                        Drawable drawable=getResources().getDrawable(resId);
                        //设置图像按原始大小显示
                        drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
                        return drawable;
                    }
                }, null);
                tv.setText(reslut);

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("文字文字文字学");

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("chattest","___________getWindow().getAttributes().softInputMode="+getWindow().getAttributes().softInputMode);
                Log.d("chattest","___________ WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN="+ WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
               if(getWindow().getAttributes().softInputMode== WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN){
                   getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

                    //Log.d("chattest","___________soft key hide");
                    //((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).showSoftInputFromInputMethod(MainActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.SHOW_IMPLICIT);
                }else{
                    //Log.d("chattest","___________soft key visible");
                    //((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).showSoftInputFromInputMethod(MainActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.SHOW_FORCED);
                    //((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
