package com.findu.chatdemo.tools;

import android.text.SpannableString;

import com.findu.chatdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/18.
 */
public class MsgData {
    List<ChatMsg> chatMsgs=new ArrayList<>();
    public MsgData(){
    }
    private String[] msgArray = new String[] { "你在哪？", "在家呢", "在家干嘛？出来嗨！", "去哪？",
            "朝天门", "朝天门没得啥子意思", "那你说去哪", "困一觉",
            "你可以狗带了", "why not？", "无语", "哈哈！" };

    private String[] dataArray = new String[] { "2012-09-22 18:00:02",
            "2012-09-22 18:10:22", "2012-09-22 18:11:24",
            "2012-09-22 18:20:23", "2012-09-22 18:30:31",
            "2012-09-22 18:35:37", "2012-09-22 18:40:13",
            "2012-09-22 18:50:26", "2012-09-22 18:52:57",
            "2012-09-22 18:55:11", "2012-09-22 18:56:45",
            "2012-09-22 18:57:33", };
    //实际可以从数据库中给chatMsgs添加数据
    public List<ChatMsg> setData(){
        //是否自己发送
        Boolean msgtype;
        String name;
        //头像
        int imgid;
        for (int i=0;i<12;i++){
            if(i%2==0){
                msgtype=true;
                name="me";
                imgid= R.mipmap.me;
            }else {
                msgtype=false;
                name="other";
                imgid=R.mipmap.other;
            }
            SpannableString spannableString=new SpannableString(msgArray[i]);
            ChatMsg chatMsg=new ChatMsg(name,dataArray[i],spannableString,msgtype,imgid);
            chatMsgs.add(chatMsg);
        }
        return chatMsgs;
    }
}
