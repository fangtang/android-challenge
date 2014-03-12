package com.example.AnswerFromLXL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyListAdapt extends BaseAdapter {
    private List<Commit> commits = null;
    private Context mContext = null;
    private TextView txtPerson,txtCommit,txtMessage;

    public MyListAdapt(Context context,List<Commit> com){
        this.mContext = context;
        this.commits = com;
    }
    @Override
    public int getCount() {
        return commits.size();
    }

    @Override
    public Object getItem(int position) {
        return commits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.row,null);
        }
        if (convertView != null) {
            //将数据更新到list
            txtPerson = (TextView) convertView.findViewById(R.id.person_name);
            txtPerson.setText(commits.get(position).getPerson());
            txtCommit = (TextView) convertView.findViewById(R.id.commit_context);
            txtCommit.setText(commits.get(position).getCommit());
            txtMessage = (TextView) convertView.findViewById(R.id.message);
            txtMessage.setText(commits.get(position).getMessage());
        }
        return convertView;
    }
}
