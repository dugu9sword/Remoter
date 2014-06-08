package com.yag.remoter;

import java.io.Serializable;
import java.util.HashMap;

import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.Button;

/*
 * 布局类
 */
public class Layout implements Serializable{

	public HashMap<String, int[]> hashMap;
	public String upAndDown;
	public String leftAndRight;
	public String accelLeft;
	public String accelRight;
	public String accelUp;
	public String accelDown;

}
