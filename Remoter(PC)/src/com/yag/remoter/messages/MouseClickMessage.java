package com.yag.remoter.messages;

/*
 * ����ƶ��������Ϣ����Ҫ������Щ���ݣ��Ŵ���ʵ�ְɡ�
 */
public class MouseClickMessage implements Message{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4986200638649337505L;

	public int key;
	
	public int click=1;
	public int doubleclick=2;
	public int rightclick=3;

	public MouseClickMessage(int _key)
	{
		key=_key;
	}
}
