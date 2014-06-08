package com.yag.remoter;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LinkActivity extends Activity {
	private EditText ip, port;
	private Button connect, help, exit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link);

		/*
		 * 防止版本不兼容
		 */
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build());

		// 获取手机屏幕信息
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		RemoterApp ra = (RemoterApp) getApplication();
		ra.setWidth(dm.widthPixels);
		ra.setHeight(dm.heightPixels);

		connect = (Button) findViewById(R.id.btn_connect);
		help = (Button) findViewById(R.id.btn_help);
		exit = (Button) findViewById(R.id.btn_exit);
		ip = (EditText) findViewById(R.id.ip);
		port = (EditText) findViewById(R.id.port);

		connect.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				try {
					// 新建UDP协议的Socket
					byte[] buf = new byte[256];
					DatagramSocket socket = new DatagramSocket();
					InetAddress inetAddress = InetAddress.getByName(ip
							.getText().toString());
					DatagramPacket sendPacket = new DatagramPacket(buf,
							buf.length, inetAddress, Integer.parseInt(port
									.getText().toString()));

					// 保存Socket
					RemoterApp ra = (RemoterApp) getApplication();
					ra.setSocket(socket);
					ra.sendPacket = sendPacket;

					Intent intent = new Intent();
					intent.setClass(LinkActivity.this, GameActivity.class);
					intent.setAction(Intent.ACTION_VIEW);
					startActivity(intent);
				} catch (Exception e) {
					Log.e("LINK", e.toString());
					Toast.makeText(getApplicationContext(), "连接失败",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		help.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(LinkActivity.this, HelpActivity.class);
				intent.setAction(Intent.ACTION_VIEW);
				startActivity(intent);
			}
		});

		exit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				System.exit(0);
			}
		});
	}

}
