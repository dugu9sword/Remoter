package com.yag.remoter.messages;

/*
 * 鼠标移动、点击信息，需要传递哪些内容？张聪来实现吧。
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
