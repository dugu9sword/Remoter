package com.yag.remoter.messages;

/*
 * ����ƶ��������Ϣ����Ҫ������Щ���ݣ��Ŵ���ʵ�ְɡ�
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
