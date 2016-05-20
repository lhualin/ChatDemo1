package com.findu.chatdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        init();
    }

    private void init() {
        addImgBtn=(ImageButton) findViewById(R.id.add_btom_btn);
        sendBtn=(Button)findViewById(R.id.send_btom_btn);
        listView = (ListView) findViewById(R.id.listview_chat);
        editText=(EditText)findViewById(R.id.chat_btom_editext);
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
        MsgData msgData=new MsgData();
        chatMsgs=msgData.setData();
        chatAdapter=new ChatAdapter(this,chatMsgs);
        listView.setAdapter(chatAdapter);
        listView.setSelection(chatAdapter.getCount()-1);
    }
    public void sendMsg(){
        String contString =editText.getText().toString();
        ChatMsg chatMsg=new ChatMsg("me",getDate(),contString,true,R.mipmap.me);
        chatMsgs.add(chatMsg);
        chatAdapter.notifyDataSetChanged();
        editText.setText("");
        listView.setSelection(chatAdapter.getCount()-1);
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    private String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(new Date());
    }
}
