package com.yag.remoter.messages;

/*
 * 触屏事件
 */
public class MotionMessage implements Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = -369678594832139259L;

	float dx;
	float dy;

	public MotionMessage(float _dx, float _dy) {
		dx = _dx;
		dy = _dy;
	}

}
