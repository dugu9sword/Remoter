package com.yag.remoter;

import com.yag.remoter.messages.EndingMessage;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/*
 * 设置界面
 */
public class SettingActivity extends ListActivity {
	private RemoterApp ra;

	private String[] settings;

	private ArrayAdapter<String> aa;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_list);
		ra = (RemoterApp) getApplication();
	}

	@Override
	public void onResume() {
		super.onResume();
		settings = new String[] { "添加按钮", "移动按钮", "删除按钮", "保存布局", "导入布局",
				"设置传感器", "按键振动反馈:" + ra.getShake(), "断开服务器连接" };
		aa = new ArrayAdapter<String>(this, R.layout.activity_item, settings);
		setListAdapter(aa);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		switch (position) {
		case 0:
			ra.setAddingButton(true);
			Intent intent1 = new Intent();
			intent1.setClass(SettingActivity.this, AddingButtonActivity.class);
			startActivity(intent1);
			break;
		case 1:
			ra.setMovingButton(true);
			Intent intent0 = new Intent();
			intent0.setClass(SettingActivity.this, GameActivity.class);
			startActivity(intent0);
			break;
		case 2:
			ra.setDeletingButton(true);
			Intent intent2 = new Intent();
			intent2.setClass(SettingActivity.this, GameActivity.class);
			startActivity(intent2);
			break;
		case 3:
			ra.setSavingLayout(true);
			Intent intent3 = new Intent();
			intent3.setClass(SettingActivity.this, SavingLayoutActivity.class);
			startActivity(intent3);
			break;
		case 4:
			Intent intent4 = new Intent();
			intent4.setClass(SettingActivity.this,
					ImportingLayoutActivity.class);
			startActivity(intent4);
			break;
		case 5:
			Intent intent6 = new Intent();
			intent6.setClass(SettingActivity.this, SettingSensorActivity.class);
			startActivity(intent6);
			break;
		case 6:
			ra.setShake(ra.getShake().equals("ON") ? "OFF" : "ON");
			settings[6] = "按键振动反馈:" + ra.getShake();
			aa.notifyDataSetChanged();
			break;
		case 7:
			NetWriter.write(new EndingMessage());
			ra.setSocket(null);
			Intent intent5 = new Intent();
			intent5.setClass(SettingActivity.this, LinkActivity.class);
			startActivity(intent5);
			break;
		}
	}

}
