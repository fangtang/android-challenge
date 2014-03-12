package com.example.AnswerFromLXL;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.List;

public class MyActivity extends Activity {
    final static String TAG = "MyActivity";

    private ListView listView =null;
    private ListAdapter listAdapter = null;
    private List<Commit> commits = null;
    private Context mContext =null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initData();
        loadView();
        Intent intent = new Intent(this,DataService.class);
        startService(intent);
    }

    private MsgReceiver msgReceiver;
    /**
     * �㲥������
     * @author lxl
     */
    public class MsgReceiver extends BroadcastReceiver {
        public MsgReceiver(){
        }
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG,intent.getAction());
            if(intent.getAction()==null){
                return;
            }
            if (intent.getAction().equals(Constant.DATA_GIT)) {
                Commits com = (Commits)intent.getSerializableExtra("data");
                if(com != null){
                    List<Commit> commits = com.getCommits();
                    //Create a ListView that groups the recent commits by author in a custom view.
                    listAdapter = new MyListAdapt(mContext,commits);
                    listView.setAdapter(listAdapter);
                }
            }
        }
    }

    /**
     * ���ݳ�ʼ��
     */
    private void initData() {
        mContext =this;
        msgReceiver = new MsgReceiver();
        registReceiver();
    }

    /**
     * ��̬ע��㲥������ ע������ע��Ĺ㲥������  ��Ҫע��
     * */
    private void registReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        //���û�ȡ����֮�󷵻ع㲥��action
        intentFilter.addAction(Constant.DATA_GIT);
        registerReceiver(msgReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        if(msgReceiver!=null){
            unregisterReceiver(msgReceiver);
        }
        super.onDestroy();
    }

    /**
     * Ԥ���ؿؼ�
     */
    private void loadView() {
        listView = (ListView)findViewById(R.id.list);
    }
}
