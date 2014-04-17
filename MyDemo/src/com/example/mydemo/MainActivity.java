package com.example.mydemo;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	private SimpleAdapter simpleAdapter;
	private ListView curListView;
	private MyTask myTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		myTask = new MyTask();
		myTask.execute(MainActivity.this);
	}
 
	private class MyTask extends AsyncTask<Context, Integer, List<HashMap<String, String>>>{
		private Context context;

		@Override
		protected List<HashMap<String, String>> doInBackground(Context...params) {
			// TODO 自动生成的方法存根
			this.context = params[0];
			GetRepoAttri gr = new GetRepoAttri();
			return gr.getMsgList("rails", "rails");
		}
		@Override
		protected void onPostExecute(List<HashMap<String, String>> list){
			curListView = (ListView) findViewById(R.id.msglist);
			simpleAdapter = new SimpleAdapter(context,list,R.layout.item, 
					new String[]{"name","date","content"}, new int[]{R.id.name,R.id.date,R.id.content});
			curListView.setAdapter(simpleAdapter);
		}
	}
}
