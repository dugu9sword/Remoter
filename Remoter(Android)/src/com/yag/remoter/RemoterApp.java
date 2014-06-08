package com.yag.remoter;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.yag.remoter.messages.KeyMessage;

import android.app.Application;

/*
 * 应用程序共享的信息，方便Activity间的交流
 */
public class RemoterApp extends Application {
	// 网络连接
	public static DatagramSocket socket;
	public static DatagramPacket sendPacket;

	// 布局相关
	private String nameOfButton;
	private boolean addingButton = false;
	private boolean deletingButton = false;
	private boolean savingLayout = false;
	private String nameOfLayout;
	private boolean importingLayout = false;
	private boolean movingButton = false;

	// 手机屏幕
	public static int width;
	public static int height;

	// 传感器相关
	private String upAndDown = "OFF";// OFF,WASD模式，方向键盘模式
	private String leftAndRight = "OFF"; // OFF,WASD模式，方向键盘模式
	private String accelLeft = "OFF";
	private String accelRight = "OFF";
	private String accelUp = "OFF";
	private String accelDown = "OFF";
	private String nameOfAction = "";

	// 振动反馈
	private String shake = "OFF";

	private int degree = 15;
	private int accel = 15;
	private int sensorTime = 850;

	// 方向传感器根据当前模式，取得键位,key,0,1,2,3对应上下左右
	public int getKey(String mode, int key) {
		int keyCode = 0;
		if (mode.equals("WASD模式")) {
			switch (key) {
			case 0:
				keyCode = KeyMessage.VK_W;
				break;
			case 1:
				keyCode = KeyMessage.VK_S;
				break;
			case 2:
				keyCode = KeyMessage.VK_A;
				break;
			case 3:
				keyCode = KeyMessage.VK_D;
				break;
			}
		} else {
			switch (key) {
			case 0:
				keyCode = KeyMessage.VK_UP;
				break;
			case 1:
				keyCode = KeyMessage.VK_DOWN;
				break;
			case 2:
				keyCode = KeyMessage.VK_LEFT;
				break;
			case 3:
				keyCode = KeyMessage.VK_RIGHT;
				break;
			}
		}
		return keyCode;
	}

	public DatagramSocket getSocket() {
		return socket;
	}

	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}

	public String getNameOfButton() {
		return nameOfButton;
	}

	public void setNameOfButton(String nameOfButton) {
		this.nameOfButton = nameOfButton;
	}

	public boolean isAddingButton() {
		return addingButton;
	}

	public void setAddingButton(boolean addingButton) {
		this.addingButton = addingButton;
	}

	public boolean isDeletingButton() {
		return deletingButton;
	}

	public void setDeletingButton(boolean deletingButton) {
		this.deletingButton = deletingButton;
	}

	public boolean isSavingLayout() {
		return savingLayout;
	}

	public void setSavingLayout(boolean savingLayout) {
		this.savingLayout = savingLayout;
	}

	public String getNameOfLayout() {
		return nameOfLayout;
	}

	public void setNameOfLayout(String nameOfLayout) {
		this.nameOfLayout = nameOfLayout;
	}

	public boolean isImportingLayout() {
		return importingLayout;
	}

	public void setImportingLayout(boolean importingLayout) {
		this.importingLayout = importingLayout;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		RemoterApp.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		RemoterApp.height = height;
	}

	public String getUpAndDown() {
		return upAndDown;
	}

	public void setUpAndDown(String upAndDown) {
		this.upAndDown = upAndDown;
	}

	public String getLeftAndRight() {
		return leftAndRight;
	}

	public void setLeftAndRight(String leftAndRight) {
		this.leftAndRight = leftAndRight;
	}

	public String getAccelLeft() {
		return accelLeft;
	}

	public void setAccelLeft(String accelLeft) {
		this.accelLeft = accelLeft;
	}

	public String getAccelRight() {
		return accelRight;
	}

	public void setAccelRight(String accelRight) {
		this.accelRight = accelRight;
	}

	public String getAccelUp() {
		return accelUp;
	}

	public void setAccelUp(String accelUp) {
		this.accelUp = accelUp;
	}

	public String getAccelDown() {
		return accelDown;
	}

	public void setAccelDown(String accelDown) {
		this.accelDown = accelDown;
	}

	public String getNameOfAction() {
		return nameOfAction;
	}

	public void setNameOfAction(String nameOfAction) {
		this.nameOfAction = nameOfAction;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public int getAccel() {
		return accel;
	}

	public void setAccel(int accel) {
		this.accel = accel;
	}

	public int getSensorTime() {
		return sensorTime;
	}

	public void setSensorTime(int sensorTime) {
		this.sensorTime = sensorTime;
	}

	public boolean isMovingButton() {
		return movingButton;
	}

	public void setMovingButton(boolean movingButton) {
		this.movingButton = movingButton;
	}

	public String getShake() {
		return shake;
	}

	public void setShake(String shake) {
		this.shake = shake;
	}

}
