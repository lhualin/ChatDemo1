package com.findu.chatdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.findu.chatdemo.tools.ChatMsg;
import com.findu.chatdemo.tools.ChatRecyclerAdapter;
import com.findu.chatdemo.tools.MsgData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Chat2Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    Context context = this;
    List<ChatMsg> chatMsgs;
    EditText editText;
    ChatRecyclerAdapter chatRecyclerAdapter;
    Button sendBtn;
    ImageButton addImgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        findview();
        innit();
    }

    public void findview() {
        recyclerView = (RecyclerView) findViewById(R.id.chat_recycle);
        editText = (EditText) findViewById(R.id.chat_btom_editext);
        sendBtn = (Button) findViewById(R.id.send_btom_btn);
        addImgBtn = (ImageButton) findViewById(R.id.add_btom_btn);
    }

    public void innit() {
        //得到数据
        MsgData msgData = new MsgData();
        chatMsgs = msgData.setData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        chatRecyclerAdapter = new ChatRecyclerAdapter(this, chatMsgs);
        recyclerView.setAdapter(chatRecyclerAdapter);
        //滚动到最后一条
        recyclerView.scrollToPosition(chatRecyclerAdapter.getItemCount() - 1);

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

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送信息
                sendTextMsg(v);
            }
        });
        //发送图片，视频
        addImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTextMsg(v);
            }
        });
    }

    public void sendTextMsg(View v) {
        SpannableString spannableString = null;
        switch (v.getId()) {
            case R.id.add_btom_btn:
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.msg_img);
                //根据Bitmap对象创建ImageSpan对象
                ImageSpan imageSpan = new ImageSpan(context, bitmap);
                //创建一个SpinnableString对象，以便插入ImageSpan对象封装的图像
                spannableString = new SpannableString("replace");
                //用ImageSpan对象替换replace字符串
                spannableString.setSpan(imageSpan, 0, 7, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            case R.id.send_btom_btn:
                String contString = editText.getText().toString();
                spannableString = new SpannableString(contString);
        }
        ChatMsg chatMsg = new ChatMsg("me", getDate(), spannableString, true, R.mipmap.me);
        chatMsgs.add(chatMsg);
        //通知adapter数据改变
        chatRecyclerAdapter.notifyDataSetChanged();
        //清空EditText
        editText.setText("");
        //滚动到最后一条
        recyclerView.scrollToPosition(chatRecyclerAdapter.getItemCount() - 1);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    //得到发送信息的时间
    private String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(new Date());
    }
}
