package com.yag.remoter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/*
 * 设置传感器的界面
 */
public class SettingSensorActivity extends ListActivity {

	private RemoterApp ra;

	private String sensors[];

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_list);
		ra = (RemoterApp) getApplication();
	}

	@Override
	public void onResume() {
		super.onResume();
		sensors = new String[] { "左右翻动:" + ra.getLeftAndRight(),
				"前后翻动:" + ra.getUpAndDown(), "加速左甩:" + ra.getAccelLeft(),
				"加速右甩:" + ra.getAccelRight(), "加速前甩:" + ra.getAccelUp(),
				"加速后甩:" + ra.getAccelDown() };
		setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_item,
				sensors));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
		case 0:
			if (ra.getLeftAndRight().equals("OFF"))
				ra.setLeftAndRight("WASD模式");
			else if (ra.getLeftAndRight().equals("WASD模式"))
				ra.setLeftAndRight("方向键盘模式");
			else
				ra.setLeftAndRight("OFF");
			((TextView) (l.getChildAt(0))).setText("左右翻动:"
					+ ra.getLeftAndRight());
			break;
		case 1:
			if (ra.getUpAndDown().equals("OFF"))
				ra.setUpAndDown("WASD模式");
			else if (ra.getUpAndDown().equals("WASD模式"))
				ra.setUpAndDown("方向键盘模式");
			else
				ra.setUpAndDown("OFF");
			((TextView) (l.getChildAt(1))).setText("前后翻动:" + ra.getUpAndDown());
			break;
		default:
			String tmp = "";
			switch (position) {
			case 2:
				tmp = "accelLeft";
				break;
			case 3:
				tmp = "accelRight";
				break;
			case 4:
				tmp = "accelUp";
				break;
			case 5:
				tmp = "accelDown";
				break;
			}
			ra.setNameOfAction(tmp);
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			intent.setClass(SettingSensorActivity.this,
					SettingSensorActionActivity.class);
			startActivity(intent);
			break;
		}
	}
}
