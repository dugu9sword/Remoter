package com.yag.remoter.messages;

/*
 * 鼠标移动、点击信息，需要传递哪些内容？张聪来实现吧。
 */
public class MotionMessage implements Message{
	/**
	 * 
	 */
	private static final long serialVersionUID = -369678594832139259L;
	public float dx;
	public float dy;

	public MotionMessage(float _dx,float _dy)
	{
		dx=_dx;
		dy=_dy;
	}
}
