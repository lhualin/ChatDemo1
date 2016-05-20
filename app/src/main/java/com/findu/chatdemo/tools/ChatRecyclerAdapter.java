package com.findu.chatdemo.tools;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.findu.chatdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/20.
 */
public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.ViewHolder>{

    List<ChatMsg> chatMsgs;
    Context context;
    public ChatRecyclerAdapter(Context context,List<ChatMsg> chatMsgs) {
        this.chatMsgs=chatMsgs;
        this.context=context;
    }

    public int getItemViewType(int position) {
        //根据是否自己发送设定viewtype
        if(chatMsgs.get(position).getMsgType()){
            return  0;
        }else{
            return 1;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        //根据不同的viewtype加载不同item布局
        if(viewType==0){
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_listview_item_me, parent, false);
            return new ViewHolder(view);
        }else {
            view=LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_listview_item_other,parent,false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatRecyclerAdapter.ViewHolder holder, int position) {
        holder.imgview.setBackgroundResource(chatMsgs.get(position).getImgid());
        holder.msg.setText(chatMsgs.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return chatMsgs.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgview;
        public TextView msg;

        public ViewHolder(View view) {
            super(view);
            imgview = (ImageView) view.findViewById(R.id.img_chat_listview_item_user);
            msg = (TextView) view.findViewById(R.id.txview_chat_listview_item_content);
        }
    }
}
