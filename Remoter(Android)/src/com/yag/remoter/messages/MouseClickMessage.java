package com.yag.remoter.messages;

/*
 * ����ƶ��������Ϣ����Ҫ������Щ���ݣ��Ŵ���ʵ�ְɡ�
 */
public class MouseClickMessage implements Message {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4986200638649337505L;

	int key;

	int click = 1;
	int doubleclick = 2;
	int rightclick = 3;

	public MouseClickMessage(int _key) {
		key = _key;
	}
}
