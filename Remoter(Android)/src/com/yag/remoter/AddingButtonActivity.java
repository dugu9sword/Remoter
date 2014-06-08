package com.yag.remoter;

import com.yag.remoter.messages.KeyMessage;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*
 * 添加新按钮的界面
 */
public class AddingButtonActivity extends ListActivity{

	/*
	 * 使用KeyMessage中的KEYS常量
	 */
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_list);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.activity_item,
				KeyMessage.KEYS);
		setListAdapter(aa);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		((RemoterApp)getApplication()).setNameOfButton(KeyMessage.KEYS[position]);
		Intent intent = new Intent();
		intent.setClass(AddingButtonActivity.this, GameActivity.class);
		startActivity(intent);
	}
}
