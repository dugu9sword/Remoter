package com.yag.remoter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/*
 * 保存布局的界面
 */
public class SavingLayoutActivity extends Activity {
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_savinglayout);
		Button btn_back = (Button) findViewById(R.id.btn_back);
		Button btn_save = (Button) findViewById(R.id.btn_save);
		btn_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((RemoterApp) getApplication()).setSavingLayout(false);
				Intent intent = new Intent();
				intent.setClass(SavingLayoutActivity.this, GameActivity.class);
				startActivity(intent);
			}
		});
		btn_save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((RemoterApp) getApplication()).setSavingLayout(true);
				((RemoterApp) getApplication())
						.setNameOfLayout(((EditText) findViewById(R.id.text_nameoflayout))
								.getText().toString());
				Intent intent = new Intent();
				intent.setClass(SavingLayoutActivity.this, GameActivity.class);
				startActivity(intent);
			}
		});
	}
}
