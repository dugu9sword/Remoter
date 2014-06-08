package com.yag.remoter;

import com.yag.remoter.messages.Message;
import com.yag.remoter.messages.MotionMessage;
import com.yag.remoter.messages.MouseClickMessage;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;

public class MouseActivity extends Activity implements OnTouchListener {

	final static int NONE = 0;
	final static int DRAG = 1;
	int touchState = NONE;
	final static float MIN_DIST = 50;
	static float eventDistance = 0;
	static float centerX = 0;
	static float centerY = 0;
	static float pastX = 0;
	static float pastY = 0;

	/*
	 * 初始化界面
	 */
	private void init() {
		ImageView view = (ImageView) findViewById(R.id.imageView);
		Button button1 = (Button) findViewById(R.id.button1);
		Button button3 = (Button) findViewById(R.id.button3);
		button1.setOnClickListener(new MyOnClickListener());
		button3.setOnClickListener(new MyOnClickListener());
		view.setOnTouchListener(this);
	}

	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button1:
				Message mouseClickMessage1 = new MouseClickMessage(1);
				NetWriter.write(mouseClickMessage1);
				break;
			case R.id.button3:
				Message mouseClickMessage3 = new MouseClickMessage(3);
				NetWriter.write(mouseClickMessage3);
				break;
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mouse);
		init();

	}

	@SuppressLint("NewApi")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			touchState = DRAG;
			centerX = event.getX(0);
			centerY = event.getY(0);
			break;
		case MotionEvent.ACTION_MOVE:
			if (touchState == DRAG) {
				Message mouseMessage = new MotionMessage(
						event.getX() - centerX, event.getY() - centerY);
				centerX=event.getX(0);
				centerY=event.getY(0);
				NetWriter.write(mouseMessage);
			}
			break;
		case MotionEvent.ACTION_UP:
			touchState = NONE;
			break;
		default:
			break;
		}
		return true;
	}

}