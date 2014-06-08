package com.yag.remoter;

import android.content.Context;
import android.widget.Button;

/*
 * 考虑到Button使用图片而不是字符，将本该setText的文本设置为Name
 */
public class Rutton extends Button{
	
	public Rutton(Context context) {
		super(context);
	}

	private String name;

	

	public void setName(String s){
		this.name=s;
	}
	
	public String getName(){
		return name;
	}


}
