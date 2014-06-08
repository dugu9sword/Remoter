package com.yag.remoter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.yag.remoter.messages.KeyMessage;
import com.yag.remoter.messages.Message;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsoluteLayout;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class GameActivity extends Activity implements SensorEventListener {

	private SensorManager sensorManager;
	private Sensor sensor;

	/*
	 * 由于设置、鼠标、方向键都不能使用字符，所以用空格代替 空格代表内容，仅用于作为按钮识别的特征
	 */
	private static final String LOGO_SETTINGS = "设";
	private static final String LOGO_MOUSE = "鼠";

	private Rutton btn_jmpToMouse;
	private Rutton btn_settings;
	private AbsoluteLayout absoluteLayout;
	private HashMap<Rutton, LayoutParams> bg = new HashMap<Rutton, LayoutParams>();
	private LayoutParams lp_jmpToMouse;
	private LayoutParams lp_settings;

	private RemoterApp ra;

	// 防止体感动作出现。。。额，问题
	private Calendar preCalendar = new GregorianCalendar();
	private Calendar tmpCalendar;
	// 0，左；1，右；2，上；3，下
	private int[] orientation = new int[] { 0, 0, 0, 0 };
	private Rutton btn_move;
	private LayoutParams lp_move;
	private TextView test;
	public static Vibrator v;

	private static final int Rutton_LENGTH = RemoterApp.width / 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		init();
	}

	/*
	 * 初始化 1、设置初始按钮，包括设置键、键鼠切换键、方向键 2、连接socket
	 */
	private void init() {
		v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		ra = (RemoterApp) getApplication();

		// 取得连接
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		absoluteLayout = (AbsoluteLayout) findViewById(R.id.layout);

		// 测试用TextView
		test = new TextView(getApplicationContext());
		test.setText("TEST");
		absoluteLayout.addView(test, new LayoutParams(200, 30, 200, 0));

		// 设置键
		btn_settings = new Rutton(getApplicationContext());
		btn_settings.setName(LOGO_SETTINGS);
		btn_settings.setBackgroundResource(R.drawable.settings);
		lp_settings = new LayoutParams(Rutton_LENGTH / 3 * 2,
				Rutton_LENGTH / 3 * 2, 0, 0);
		absoluteLayout.addView(btn_settings, lp_settings);
		btn_settings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setClass(getApplicationContext(), SettingActivity.class);
				startActivity(intent);
			}
		});

		// 切换到鼠标状态
		btn_jmpToMouse = new Rutton(getApplicationContext());
		btn_jmpToMouse.setName(LOGO_MOUSE);
		btn_jmpToMouse.setBackgroundResource(R.drawable.mouse);
		lp_jmpToMouse = new LayoutParams(Rutton_LENGTH / 3 * 2,
				Rutton_LENGTH / 3 * 2, Rutton_LENGTH / 3 * 2, 0);
		absoluteLayout.addView(btn_jmpToMouse, lp_jmpToMouse);
		btn_jmpToMouse.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setClass(getApplicationContext(), MouseActivity.class);
				startActivity(intent);
			}
		});

		bg.put(btn_settings, lp_settings);
		bg.put(btn_jmpToMouse, lp_jmpToMouse);

		// 初始化方向键
		int x_up = ra.getHeight() - Rutton_LENGTH * 3 / 2;
		int y_up = ra.getWidth() - Rutton_LENGTH * 3 / 2;
		Rutton btn_up = new Rutton(getApplicationContext());
		btn_up.setName(KeyMessage.UP);
		IB.setImage(btn_up);
		LayoutParams lp_up = new LayoutParams(Rutton_LENGTH, Rutton_LENGTH,
				x_up - Rutton_LENGTH / 2, y_up - Rutton_LENGTH / 2);
		absoluteLayout.addView(btn_up, lp_up);
		bg.put(btn_up, lp_up);

		Rutton btn_down = new Rutton(getApplicationContext());
		btn_down.setName(KeyMessage.DOWN);
		IB.setImage(btn_down);
		LayoutParams lp_down = new LayoutParams(Rutton_LENGTH, Rutton_LENGTH,
				x_up - Rutton_LENGTH / 2, y_up - Rutton_LENGTH / 2
						+ Rutton_LENGTH);
		absoluteLayout.addView(btn_down, lp_down);
		bg.put(btn_down, lp_down);

		Rutton btn_left = new Rutton(getApplicationContext());
		btn_left.setName(KeyMessage.LEFT);
		IB.setImage(btn_left);
		LayoutParams lp_left = new LayoutParams(Rutton_LENGTH, Rutton_LENGTH,
				x_up - Rutton_LENGTH / 2 - Rutton_LENGTH, y_up - Rutton_LENGTH
						/ 2 + Rutton_LENGTH);
		absoluteLayout.addView(btn_left, lp_left);
		bg.put(btn_left, lp_left);

		Rutton btn_right = new Rutton(getApplicationContext());
		btn_right.setName(KeyMessage.RIGHT);
		IB.setImage(btn_right);
		LayoutParams lp_right = new LayoutParams(Rutton_LENGTH, Rutton_LENGTH,
				x_up - Rutton_LENGTH / 2 + Rutton_LENGTH, y_up - Rutton_LENGTH
						/ 2 + Rutton_LENGTH);
		absoluteLayout.addView(btn_right, lp_right);
		bg.put(btn_right, lp_right);

		// 刷新界面
		refresh();
	}

	/*
	 * 1、注册传感器 2、用于界面布局相关功能
	 */
	@Override
	public void onResume() {
		super.onResume();
		// 注册传感器
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_FASTEST);
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_FASTEST);
		// 保存布局
		if (ra.isSavingLayout()) {
			ra.setSavingLayout(false);
			String fileName = (ra.getNameOfLayout());
			try {
				File path_layout = new File(
						"/data/data/com.yag.remoter/layouts");
				if (!path_layout.exists()) {
					path_layout.mkdir();
				}
				ObjectOutputStream oos = new ObjectOutputStream(
						new FileOutputStream(
								"/data/data/com.yag.remoter/layouts/"
										+ fileName));

				/*
				 * Rutton不能被序列化，只能保存getText的值,int[]中，前四个表 示LayoutParams中的值
				 */
				HashMap<String, int[]> tmpbg = new HashMap<String, int[]>();
				Iterator iterator = bg.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry entry = (Map.Entry) iterator.next();
					Rutton tmpRutton = (Rutton) entry.getKey();
					LayoutParams lp = (LayoutParams) entry.getValue();
					if ((!tmpRutton.getName().toString().equals(LOGO_MOUSE))
							&& !tmpRutton.getName().toString()
									.equals(LOGO_SETTINGS))
						tmpbg.put(tmpRutton.getName().toString(), new int[] {
								lp.width, lp.height, lp.x, lp.y });
				}

				Layout layout = new Layout();
				layout.hashMap = tmpbg;
				layout.upAndDown = ra.getUpAndDown();
				layout.leftAndRight = ra.getLeftAndRight();
				layout.accelLeft = ra.getAccelLeft();
				layout.accelRight = ra.getAccelRight();
				layout.accelUp = ra.getAccelUp();
				layout.accelDown = ra.getAccelDown();

				oos.writeObject(layout);
				oos.close();
				Toast.makeText(GameActivity.this, "成功保存布局", Toast.LENGTH_SHORT)
						.show();
			} catch (Exception e) {
//				Log.e("123", e.toString());
				Toast.makeText(GameActivity.this, "保存失败", Toast.LENGTH_SHORT)
						.show();
			}
		}
		// 导入布局
		if (ra.isImportingLayout()) {
			ra.setImportingLayout(false);
			String filename = ra.getNameOfLayout();
			try {
				ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream(
								"/data/data/com.yag.remoter/layouts/"
										+ filename));
				Layout layout = (Layout) ois.readObject();
				HashMap<String, int[]> tmpbg = (HashMap<String, int[]>) (layout.hashMap);
				ois.close();

				// 清空布局按钮
				bg.clear();

				// 载入布局
				Iterator iterator = tmpbg.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry entry = (Map.Entry) iterator.next();
					Rutton tmpRutton = new Rutton(getApplicationContext());
					tmpRutton.setName((String) entry.getKey());
					IB.setImage(tmpRutton);
					int[] tmpInt = (int[]) entry.getValue();
					LayoutParams tmpLayoutParams = new LayoutParams(tmpInt[0],
							tmpInt[1], tmpInt[2], tmpInt[3]);
					bg.put(tmpRutton, tmpLayoutParams);
				}

				// 放置基本按钮
				bg.put(btn_settings, lp_settings);
				bg.put(btn_jmpToMouse, lp_jmpToMouse);

				// 刷新按钮
				refresh();

				// 设置传感器
				ra.setUpAndDown(layout.upAndDown);
				ra.setLeftAndRight(layout.leftAndRight);
				ra.setAccelLeft(layout.accelLeft);
				ra.setAccelRight(layout.accelRight);
				ra.setAccelUp(layout.accelUp);
				ra.setAccelDown(layout.accelDown);

				Toast.makeText(GameActivity.this, "成功导入布局", Toast.LENGTH_SHORT)
						.show();
			} catch (Exception e) {
				Toast.makeText(GameActivity.this, "导入失败", Toast.LENGTH_SHORT)
						.show();
			}
		}
		// 移动按钮
		if (ra.isMovingButton()) {
			btn_settings.setEnabled(false);
			btn_jmpToMouse.setEnabled(false);
			btn_move = new Rutton(getApplicationContext());
			btn_move.setBackgroundResource(R.drawable.ok);
			lp_move = new LayoutParams(Rutton_LENGTH, Rutton_LENGTH,
					ra.getHeight() - Rutton_LENGTH, 0);
			absoluteLayout.addView(btn_move, lp_move);
			btn_move.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					ra.setMovingButton(false);
					btn_settings.setEnabled(true);
					btn_jmpToMouse.setEnabled(true);
					absoluteLayout.removeView(arg0);
				}
			});
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent me) {
		// 若当前为载入按钮状态则添加按钮
		if (ra.isAddingButton()) {
			LayoutParams lp = new LayoutParams(Rutton_LENGTH, Rutton_LENGTH,
					(int) me.getX() - Rutton_LENGTH / 2, (int) me.getY()
							- Rutton_LENGTH / 2);
			Rutton rutton = new Rutton(getApplicationContext());
			rutton.setName(ra.getNameOfButton());
			bg.put(rutton, lp);
			ra.setAddingButton(false);
			refresh();
		}
		return true;
	}

	/*
	 * 根据HashMap中的按钮刷新界面
	 */
	private void refresh() {
		Iterator iterator = bg.entrySet().iterator();
		absoluteLayout.removeAllViews();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			Rutton tmpRutton = (Rutton) entry.getKey();
			absoluteLayout.addView(tmpRutton, (LayoutParams) entry.getValue());
			// 对于基本按钮不添加普通的按键监听器
			if ((!tmpRutton.getName().equals(LOGO_SETTINGS))
					&& (!tmpRutton.getName().equals(LOGO_MOUSE))) {
				tmpRutton.setOnTouchListener(new MyOnTouchListener(tmpRutton
						.getName().toString()));
				IB.setImage(tmpRutton);
			}
		}
	}

	private class MyOnTouchListener implements OnTouchListener {
		private int value;
		private boolean working = true;
		private int lastX;
		private int lastY;
		private int l = 0, t = 0;// l,t对应x,y坐标

		public MyOnTouchListener(String key) {
			int i = 0;
			s: while (i < KeyMessage.KEYS.length) {
				if (key.equals(KeyMessage.KEYS[i]))
					break s;
				i++;
			}
//			Log.e("key", key);
			value = KeyMessage.VALUES[i];
		}

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (ra.isMovingButton()) {
				int ea = event.getAction();
				switch (ea) {
				case MotionEvent.ACTION_DOWN:
					lastX = (int) event.getRawX();
					lastY = (int) event.getRawY();
					break;
				case MotionEvent.ACTION_MOVE:
					int dx = (int) event.getRawX() - lastX;
					int dy = (int) event.getRawY() - lastY;

					l = v.getLeft() + dx;
					int b = v.getBottom() + dy;
					int r = v.getRight() + dx;
					t = v.getTop() + dy;
					if (l < 0) {
						l = 0;
						r = l + v.getWidth();
					}

					if (t < 0) {
						t = 0;
						b = t + v.getHeight();
					}

					if (r > ra.getHeight()) {
						r = ra.getHeight();
						l = r - v.getWidth();
					}

					if (b > ra.getWidth()) {
						b = ra.getWidth();
						t = b - v.getHeight();
					}
					v.layout(l, t, r, b);

					lastX = (int) event.getRawX();
					lastY = (int) event.getRawY();

					v.postInvalidate();
//					Log.e("MOVE", l + " " + t);
					break;
				case MotionEvent.ACTION_UP:
					// (l,t)代表(x,y)
					absoluteLayout.removeView(v);
					bg.remove(((Rutton) v).getText());
					bg.put((Rutton) v, new LayoutParams(Rutton_LENGTH,
							Rutton_LENGTH, l, t));
					absoluteLayout.addView((Rutton) v, new LayoutParams(
							Rutton_LENGTH, Rutton_LENGTH, l, t));
					break;
				}
				return false;
			} else if (working)
				if (ra.isDeletingButton()) {
					working = false;
					ra.setDeletingButton(false);
					bg.remove(((Rutton) v));
					refresh();
				} else {
					switch (event.getAction() & MotionEvent.ACTION_MASK) {
					case MotionEvent.ACTION_DOWN:
						Message keyMessage_Down = new KeyMessage(value,
								KeyMessage.KEY_PRESSED);
						NetWriter.write(keyMessage_Down);

						// 振动
						if (ra.getShake().equals("ON"))
							GameActivity.v.vibrate(70);

						break;
					case MotionEvent.ACTION_UP:
						Message keyMessage_Up = new KeyMessage(value,
								KeyMessage.KEY_RELEASED);
						NetWriter.write(keyMessage_Up);
						break;
					}
				}
			return true;
		}
	}

	/*
	 * 注销传感器
	 */
	@Override
	protected void onStop() {
		super.onStop();
		sensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}

	/*
	 * 获得传感器信息
	 */
	@Override
	public void onSensorChanged(SensorEvent arg0) {
		sensor = arg0.sensor;
		switch (sensor.getType()) {
		case Sensor.TYPE_ORIENTATION:
			test.setText("" + arg0.values[1]);
			try {
				// 控制左右，如果方向传感器打开，且左右变化范围大于限定范围，则进行方向设置
				if (!ra.getLeftAndRight().equals("OFF")
						&& (Math.abs(arg0.values[1]) > ra.getDegree())) {
					// if内是控制向左（如果左键未按下则按下左键，如果右键按下则松开右键），else内控制向右，同理
					if (arg0.values[1] > 0) {
						if (orientation[0] == 0) {
							// Log.e("LOG",
							// "send "
							// + ra.getKey(ra.getLeftAndRight(), 2));
							NetWriter.write(new KeyMessage(ra.getKey(
									ra.getLeftAndRight(), 2),
									KeyMessage.KEY_PRESSED));
							orientation[0] = 1;
						}
						if (orientation[1] == 1) {
//							Log.e("LOG",
//									"send "
//											+ ra.getKey(ra.getLeftAndRight(), 3));
							NetWriter.write(new KeyMessage(ra.getKey(
									ra.getLeftAndRight(), 3),
									KeyMessage.KEY_RELEASED));
							orientation[1] = 0;
						}
					} else {
						if (orientation[0] == 1) {
//							Log.e("LOG",
//									"send "
//											+ ra.getKey(ra.getLeftAndRight(), 2));
							NetWriter.write(new KeyMessage(ra.getKey(
									ra.getLeftAndRight(), 2),
									KeyMessage.KEY_RELEASED));
							orientation[0] = 0;
						}
						if (orientation[1] == 0) {
//							Log.e("LOG",
//									"send "
//											+ ra.getKey(ra.getLeftAndRight(), 3));
							NetWriter.write(new KeyMessage(ra.getKey(
									ra.getLeftAndRight(), 3),
									KeyMessage.KEY_PRESSED));
							orientation[1] = 1;
						}
					}
				}

				// 控制前后，与左右同aad理
				if (!ra.getUpAndDown().equals("OFF")
						&& (Math.abs(arg0.values[2]) > ra.getDegree())) {
					if (arg0.values[2] < 0) {
						if (orientation[2] == 0) {
//							Log.e("LOG",
//									"send " + ra.getKey(ra.getUpAndDown(), 0));
							NetWriter.write(new KeyMessage(ra.getKey(
									ra.getUpAndDown(), 0),
									KeyMessage.KEY_PRESSED));
							orientation[2] = 1;
						}
						if (orientation[3] == 1) {
//							Log.e("LOG",
//									"send " + ra.getKey(ra.getUpAndDown(), 1));
							NetWriter.write(new KeyMessage(ra.getKey(
									ra.getUpAndDown(), 1),
									KeyMessage.KEY_RELEASED));
							orientation[3] = 0;
						}
					} else {
						if (orientation[2] == 1) {
//							Log.e("LOG",
//									"send " + ra.getKey(ra.getUpAndDown(), 0));
							NetWriter.write(new KeyMessage(ra.getKey(
									ra.getUpAndDown(), 0),
									KeyMessage.KEY_RELEASED));
							orientation[2] = 0;
						}
						if (orientation[3] == 0) {
//							Log.e("LOG",
//									"send " + ra.getKey(ra.getUpAndDown(), 1));
							NetWriter.write(new KeyMessage(ra.getKey(
									ra.getUpAndDown(), 1),
									KeyMessage.KEY_PRESSED));
							orientation[3] = 1;
						}
					}
				}

				// 如果左右或前后在变化范围之内，那么恢复左右前后的状态
				if (Math.abs(arg0.values[1]) < ra.getDegree()) {
					if (orientation[0] == 1) {
//						Log.e("LOG",
//								"send " + ra.getKey(ra.getLeftAndRight(), 2));
						NetWriter.write(new KeyMessage(ra.getKey(
								ra.getLeftAndRight(), 2),
								KeyMessage.KEY_RELEASED));
						orientation[0] = 0;
					}
					if (orientation[1] == 1) {
//						Log.e("LOG",
//								"send " + ra.getKey(ra.getLeftAndRight(), 3));
						NetWriter.write(new KeyMessage(ra.getKey(
								ra.getLeftAndRight(), 3),
								KeyMessage.KEY_RELEASED));
						orientation[1] = 0;
					}
				}
				if (Math.abs(arg0.values[2]) < ra.getDegree()) {
					if (orientation[2] == 1) {
//						Log.e("LOG",
//								"send " + ra.getKey(ra.getUpAndDown(), 0));
						NetWriter
								.write(new KeyMessage(ra.getKey(
										ra.getUpAndDown(), 0),
										KeyMessage.KEY_RELEASED));
						orientation[2] = 0;
					}
					if (orientation[3] == 1) {
//						Log.e("LOG",
//								"send " + ra.getKey(ra.getUpAndDown(), 1));
						NetWriter
								.write(new KeyMessage(ra.getKey(
										ra.getUpAndDown(), 1),
										KeyMessage.KEY_RELEASED));
						orientation[3] = 0;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		/*
		 * 仅仅试用于不需要频繁甩动手机的情况
		 */
		case Sensor.TYPE_ACCELEROMETER:
			try {
				tmpCalendar = new GregorianCalendar();
				int TIME_DELAY = 500;

				if (arg0.values[1] < -ra.getAccel()) {
					if (tmpCalendar.getTimeInMillis()
							- preCalendar.getTimeInMillis() >= TIME_DELAY) {
						preCalendar = tmpCalendar;
						new ShortClicker(
								KeyMessage.getValue(ra.getAccelRight()))
								.start();
						// NetWriter.write(new MotionMessage(10000, 0));
					}
					Log.e("LEFT", "OHNO");
				}

				if (arg0.values[1] > ra.getAccel()) {
					if (tmpCalendar.getTimeInMillis()
							- preCalendar.getTimeInMillis() >= TIME_DELAY) {
						preCalendar = tmpCalendar;
						new ShortClicker(KeyMessage.getValue(ra.getAccelLeft()))
								.start();
						// NetWriter.write(new MotionMessage(-10000, 0));
					}
				}

				if (arg0.values[0] < -ra.getAccel()) {
					if (tmpCalendar.getTimeInMillis()
							- preCalendar.getTimeInMillis() >= TIME_DELAY) {
						preCalendar = tmpCalendar;
						new ShortClicker(KeyMessage.getValue(ra.getAccelDown()))
								.start();
					}
				}

				if (arg0.values[0] > ra.getAccel()) {
					if (tmpCalendar.getTimeInMillis()
							- preCalendar.getTimeInMillis() >= TIME_DELAY) {
						preCalendar = tmpCalendar;
						new ShortClicker(KeyMessage.getValue(ra.getAccelUp()))
								.start();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}

	class ShortClicker extends Thread {
		int key;

		public ShortClicker(int key) {
			this.key = key;
		}

		public void run() {
			try {
				NetWriter.write(new KeyMessage(key, KeyMessage.KEY_PRESSED));
				Thread.sleep(ra.getSensorTime());
				NetWriter.write(new KeyMessage(key, KeyMessage.KEY_RELEASED));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
