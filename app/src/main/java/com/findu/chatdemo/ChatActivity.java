package com.findu.chatdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.findu.chatdemo.tools.ChatAdapter;
import com.findu.chatdemo.tools.ChatMsg;
import com.findu.chatdemo.tools.MsgData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    ListView listView;
    ChatAdapter chatAdapter;
    ImageButton addImgBtn;
    Button sendBtn;
    EditText editText;
    List<ChatMsg> chatMsgs;
    final Context context = this;
    TextView tvTitle;
    ImageButton emojiBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        init();
    }

    private void init() {
        addImgBtn = (ImageButton) findViewById(R.id.add_btom_btn);
        sendBtn = (Button) findViewById(R.id.send_btom_btn);
        listView = (ListView) findViewById(R.id.listview_chat);
        editText = (EditText) findViewById(R.id.chat_btom_editext);
        tvTitle = (TextView) findViewById(R.id.name_title_txview);
        emojiBtn=(ImageButton)findViewById(R.id.emoji_btn) ;
        editText.addTextChangedListener(new TextWatcher() {
            int l = 0;////////记录字符串被删除字符之前，字符串的长度
            int location = 0;//记录光标的位置

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                l = s.length();
                location = editText.getSelectionStart();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //根据是否有输入内容决定显示添加按钮还是发送按钮
                if (editText.getText().length() != 0) {
                    addImgBtn.setVisibility(View.INVISIBLE);
                    sendBtn.setVisibility(View.VISIBLE);
                } else {
                    addImgBtn.setVisibility(View.VISIBLE);
                    sendBtn.setVisibility(View.INVISIBLE);
                }
            }
        });
        //发送图片，视频
        addImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTextMsg(v);
            }
        });
        //发送文字信息
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTextMsg(v);
            }
        });
        MsgData msgData = new MsgData();
        chatMsgs = msgData.setData();
        chatAdapter = new ChatAdapter(this, chatMsgs);
        listView.setAdapter(chatAdapter);
        listView.setSelection(chatAdapter.getCount() - 1);
        emojiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(ChatActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
    }

    public void sendTextMsg(View v) {
        SpannableString spannableString=null;
        switch (v.getId()) {
            case R.id.add_btom_btn:
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.jiepin);
                //根据Bitmap对象创建ImageSpan对象
                ImageSpan imageSpan = new ImageSpan(context, bitmap);
                //创建一个SpinnableString对象，以便插入ImageSpan对象封装的图像
                spannableString = new SpannableString("replace");
                //用ImageSpan对象替换replace字符串
                spannableString.setSpan(imageSpan, 0, 7, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                //若是html用下一种方法
                /* String html="<img src='"+R.mipmap.ic_launcher+"'/>";
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
                textViewImg.setText(reslut);*/
                break;
            case R.id.send_btom_btn:
                String contString = editText.getText().toString();
                spannableString = new SpannableString(contString);
                break;
            default:
                break;
        }
        ChatMsg chatMsg = new ChatMsg("me", getDate(), spannableString, true, R.mipmap.me);
        //tvTitle.setText(spannableString);
        chatMsgs.add(chatMsg);
        chatAdapter.notifyDataSetChanged();
        editText.setText("");
        listView.setSelection(chatAdapter.getCount() - 1);;

    }

    private String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(new Date());
    }
}
