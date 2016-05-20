package com.findu.chatdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        findview();
        innit();
    }
    public void findview(){
        recyclerView=(RecyclerView)findViewById(R.id.chat_recycle);
        editText=(EditText)findViewById(R.id.chat_btom_editext);
        sendBtn=(Button)findViewById(R.id.send_btom_btn);
        addImgBtn=(ImageButton)findViewById(R.id.add_btom_btn);
    }
    public void innit(){
        MsgData msgData=new MsgData();
        chatMsgs=msgData.setData();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        chatRecyclerAdapter=new ChatRecyclerAdapter(this,chatMsgs);
        recyclerView.setAdapter(chatRecyclerAdapter);
        recyclerView.scrollToPosition(chatRecyclerAdapter.getItemCount()-1);

        editText.addTextChangedListener(new TextWatcher() {
            int l=0;////////记录字符串被删除字符之前，字符串的长度
            int location=0;//记录光标的位置
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                l=s.length();
                location=editText.getSelectionStart();
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(editText.getText().length()!=0){
                    addImgBtn.setVisibility(View.INVISIBLE);
                    sendBtn.setVisibility(View.VISIBLE);
                }else{
                    addImgBtn.setVisibility(View.VISIBLE);
                    sendBtn.setVisibility(View.INVISIBLE);
                }
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsg();
            }
        });
    }
    public void sendMsg(){
        String contString =editText.getText().toString();
        ChatMsg chatMsg=new ChatMsg("me",getDate(),contString,true,R.mipmap.me);
        chatMsgs.add(chatMsg);
        chatRecyclerAdapter.notifyDataSetChanged();
        editText.setText("");
        recyclerView.scrollToPosition(chatRecyclerAdapter.getItemCount()-1);
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    private String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(new Date());
    }

}
