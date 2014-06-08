package com.yag.remoter;

import java.io.File;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*
 * 导入布局的界面
 */
public class ImportingLayoutActivity extends ListActivity {
	private String[] names;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_list);
		File file = new File("/data/data/com.yag.remoter/layouts");
		if (!file.exists())
			file.mkdirs();
		File[] files = file.listFiles();
		names = new String[files.length];
		for (int i = 0; i < files.length; i++)
			names[i] = files[i].getName();
		if (names.length != 0) {
			ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
					R.layout.activity_item, names);
			setListAdapter(aa);
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		((RemoterApp) getApplication()).setImportingLayout(true);
		((RemoterApp) getApplication()).setNameOfLayout(names[position]);
		Intent intent = new Intent();
		intent.setClass(ImportingLayoutActivity.this, GameActivity.class);
		startActivity(intent);
	}
}
