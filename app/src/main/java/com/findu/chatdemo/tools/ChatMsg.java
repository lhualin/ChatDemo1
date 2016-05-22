package com.findu.chatdemo.tools;

import android.text.SpannableString;

/**
 * Created by Administrator on 2016/5/18.
 */
public class ChatMsg {
    private String name;//消息来自
    private String date;//消息日期
    private SpannableString message;//消息内容
    private boolean msgtype;// 是否为收到的消息
    private int imgid;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public SpannableString getMessage() {
        return message;
    }

    public void setMessage(SpannableString message) {
        this.message = message;
    }

    public void setMsgType(Boolean msgtype){
        this.msgtype=msgtype;
    }
    public boolean getMsgType() {
        return msgtype;
    }
    public void setImgid(int imgid){
        this.imgid=imgid;
    }
    public int getImgid(){
        return imgid;
    }
    public ChatMsg(){
    }
    public ChatMsg(String name, String date, SpannableString msg, boolean msgtype, int imgid) {
        super();
        this.name = name;
        this.date = date;
        this.message = msg;
        this.msgtype = msgtype;
        this.imgid=imgid;
    }
}
