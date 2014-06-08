package com.yag.remoter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ActSplashScreen extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
		/*
		 * 闪屏的核心代码
		 */
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				//跳转到连接界面
				Intent intent = new Intent(ActSplashScreen.this,
						LinkActivity.class); 
				startActivity(intent);
				ActSplashScreen.this.finish(); // 结束启动动画界面
			}
		}, 1000); // 启动动画持续1秒钟
	}
}
