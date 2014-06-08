package com.yag.remoter;

import com.yag.remoter.messages.KeyMessage;

/*
 * 用于存储按键所对应的图片信息
 */
public class IB {

	public static final String BUTTON[] = { "0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
			"X", "Y", "Z", "ENTER", "SPACE", "ESC", "HOME", "INSERT", "DELETE",
			"CONTROL", "SHIFT", "ALT", "END", KeyMessage.UP, KeyMessage.DOWN,
			KeyMessage.LEFT, KeyMessage.RIGHT };

	public static final int IMAGE[] = { R.drawable.num0, R.drawable.num1,
			R.drawable.num2, R.drawable.num3, R.drawable.num4, R.drawable.num5,
			R.drawable.num6, R.drawable.num7, R.drawable.num8, R.drawable.num9,
			R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,
			R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h,
			R.drawable.i, R.drawable.j, R.drawable.k, R.drawable.l,
			R.drawable.m, R.drawable.n, R.drawable.o, R.drawable.p,
			R.drawable.q, R.drawable.r, R.drawable.s, R.drawable.t,
			R.drawable.u, R.drawable.v, R.drawable.w, R.drawable.x,
			R.drawable.y, R.drawable.z, R.drawable.enter, R.drawable.space,
			R.drawable.esc, R.drawable.home, R.drawable.insert,
			R.drawable.delete, R.drawable.control, R.drawable.shift,
			R.drawable.alt, R.drawable.end, R.drawable.up, R.drawable.down,
			R.drawable.left, R.drawable.right };

	public static int getImage(String button) {
		int i = 0;
		s: while (i < BUTTON.length){
			if (BUTTON[i].equals(button))
				break s;
			i++;
		}
		return IMAGE[i];
	}

	public static void setImage(Rutton rutton) {
		rutton.setBackgroundResource(getImage(rutton.getName()));
	}
	
}
