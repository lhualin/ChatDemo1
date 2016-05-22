package com.findu.chatdemo.tools;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.findu.chatdemo.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2016/5/18.
 */
public class ChatAdapter extends BaseAdapter {
    Context context;
    List<ChatMsg> chatMsgs;

    public ChatAdapter(Context context, List<ChatMsg> chatMsgs) {
        this.chatMsgs = chatMsgs;
        this.context = context;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return chatMsgs.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return chatMsgs.get(position);
    }


    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        Boolean msgtype;
        msgtype = chatMsgs.get(position).getMsgType();
        if (msgtype) {
            Log.i("true", "_____" + position);
        } else {
            Log.i("false", "_____" + position);
        }

        if (msgtype) {
            convertView = LayoutInflater.from(context).inflate(R.layout.chat_listview_item_me, null);
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.chat_listview_item_other, null);
        }
        viewHolder = new ViewHolder();
        viewHolder.imgview = (ImageView) convertView.findViewById(R.id.img_chat_listview_item_user);
        viewHolder.msg = (TextView) convertView.findViewById(R.id.txview_chat_listview_item_content);
        viewHolder.msgtype = msgtype;

        viewHolder.imgview.setBackgroundResource(chatMsgs.get(position).getImgid());

        viewHolder.msg.setText(chatMsgs.get(position).getMessage());
        Log.i("ChatAdapter","___________________________________"+chatMsgs.get(position).getMessage());
        return convertView;
    }

    static class ViewHolder {
        public ImageView imgview;
        public TextView msg;
        public boolean msgtype;
    }

}
