package com.example.dbdemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class SearchListAdapter extends ArrayAdapter<LoginModel> {

    private final Activity context;
    private final LoginModel[] user_id;
    private final LoginModel[] user_name_array;

    public SearchListAdapter(Activity context, LoginModel[] user_id, LoginModel[] user_name_array) {
        super(context, R.layout.list_item, user_name_array);

        this.context=context;
        this.user_id=user_id;
        this.user_name_array=user_name_array;
    }
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_item, null, true);
        TextView id=(TextView)rowView.findViewById(R.id.tv_id);
        TextView name=(TextView)rowView.findViewById(R.id.tv_user);
        id.setText(user_id[position].toString());
        name.setText(user_name_array[position].toString());
        return rowView;
    }
}
