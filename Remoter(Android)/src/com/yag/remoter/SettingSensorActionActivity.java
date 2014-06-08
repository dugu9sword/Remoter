package com.yag.remoter;

import com.yag.remoter.messages.KeyMessage;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*
 * 设置传感器动作的界面
 */
public class SettingSensorActionActivity extends ListActivity{
	
	private RemoterApp ra;
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_list);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.activity_item,
				KeyMessage.KEYS2);
		setListAdapter(aa);
		ra=(RemoterApp)getApplication();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		String name = (ra.getNameOfAction());
		if (name.equals("accelLeft"))
			ra.setAccelLeft(KeyMessage.KEYS2[position]);
		if (name.equals("accelRight"))
			ra.setAccelRight(KeyMessage.KEYS2[position]);
		if (name.equals("accelUp"))
			ra.setAccelUp(KeyMessage.KEYS2[position]);
		if (name.equals("accelDown"))
			ra.setAccelDown(KeyMessage.KEYS2[position]);
		ra.setNameOfAction("");
		
		Intent intent = new Intent();
		intent.setClass(SettingSensorActionActivity.this, SettingSensorActivity.class);
		startActivity(intent);
	}
}
