package com.example.chase.automaticwall;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chase on 16-01-2017.
 */

public class RoomListAdapter extends BaseAdapter {

    private Context mContext;
    private List<ROOM> mRoomList;

    //constructor

    public RoomListAdapter(Context mContext, List<ROOM> mRoomList) {
        this.mContext = mContext;
        this.mRoomList = mRoomList;
    }

    @Override
    public int getCount() {
        return mRoomList.size();
    }

//    @Override
//    public boolean isEnabled(int position) {
//        return false;
//    }

    @Override
    public Object getItem(int position) {
        return mRoomList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v1 = View.inflate(mContext, R.layout.roomlist, null);
        TextView tvname = (TextView) v1.findViewById(R.id.room_name);
        TextView tvipaddress = (TextView) v1.findViewById(R.id.room_ip);

        //Set Text for Text View

        tvname.setText(mRoomList.get(position).getRoom_Name());
        tvipaddress.setText(mRoomList.get(position).getRoom_IP_Address());

        v1.setTag(mRoomList.get(position).getRoom_Name());
        return v1;
    }
}
