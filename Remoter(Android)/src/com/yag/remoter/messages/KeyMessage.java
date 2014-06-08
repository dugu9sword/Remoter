package com.yag.remoter.messages;

import java.util.HashMap;

/*
 * ������Ϣ��key��ֵʹ��KeyEvent�ﶨ���KEYֵ
 */
public class KeyMessage implements Message {

	/**
 * 
 */
	private static final long serialVersionUID = 3522439152288397451L;
	public int key;
	public int condition;

	public KeyMessage(int _key, int _condition) {
		key = _key;
		condition = _condition;
	}

	public static final HashMap KV = new HashMap<String, Integer>() {

		/**
 * 
 */
		private static final long serialVersionUID = 2870269739164796635L;

	};

	/**
	 * The first number in the range of ids used for key events.
	 */
	public static final int KEY_FIRST = 400;

	/**
	 * The last number in the range of ids used for key events.
	 */
	public static final int KEY_LAST = 402;

	/**
	 * The "key typed" event. This event is generated when a character is
	 * entered. In the simplest case, it is produced by a single key press.
	 * Often, however, characters are produced by series of key presses, and the
	 * mapping from key pressed events to key typed events may be many-to-one or
	 * many-to-many.
	 */
	public static final int KEY_TYPED = KEY_FIRST;

	/**
	 * The "key pressed" event. This event is generated when a key is pushed
	 * down.
	 */
	public static final int KEY_PRESSED = 1 + KEY_FIRST; // Event.KEY_PRESS

	/**
	 * The "key released" event. This event is generated when a key is let up.
	 */
	public static final int KEY_RELEASED = 2 + KEY_FIRST; // Event.KEY_RELEASE

	/* Virtual key codes. */

	public static final int VK_ENTER = '\n';
	public static final int VK_BACK_SPACE = '\b';
	public static final int VK_TAB = '\t';
	public static final int VK_CANCEL = 0x03;
	public static final int VK_CLEAR = 0x0C;
	public static final int VK_SHIFT = 0x10;
	public static final int VK_CONTROL = 0x11;
	public static final int VK_ALT = 0x12;
	public static final int VK_PAUSE = 0x13;
	public static final int VK_CAPS_LOCK = 0x14;
	public static final int VK_ESCAPE = 0x1B;
	public static final int VK_SPACE = 0x20;
	public static final int VK_PAGE_UP = 0x21;
	public static final int VK_PAGE_DOWN = 0x22;
	public static final int VK_END = 0x23;
	public static final int VK_HOME = 0x24;

	/**
	 * Constant for the non-numpad <b>left</b> arrow key.
	 * 
	 * @see #VK_KP_LEFT
	 */
	public static final int VK_LEFT = 0x25;

	/**
	 * Constant for the non-numpad <b>up</b> arrow key.
	 * 
	 * @see #VK_KP_UP
	 */
	public static final int VK_UP = 0x26;

	/**
	 * Constant for the non-numpad <b>right</b> arrow key.
	 * 
	 * @see #VK_KP_RIGHT
	 */
	public static final int VK_RIGHT = 0x27;

	/**
	 * Constant for the non-numpad <b>down</b> arrow key.
	 * 
	 * @see #VK_KP_DOWN
	 */
	public static final int VK_DOWN = 0x28;

	/**
	 * Constant for the comma key, ","
	 */
	public static final int VK_COMMA = 0x2C;

	/**
	 * Constant for the minus key, "-"
	 * 
	 * @since 1.2
	 */
	public static final int VK_MINUS = 0x2D;

	/**
	 * Constant for the period key, "."
	 */
	public static final int VK_PERIOD = 0x2E;

	/**
	 * Constant for the forward slash key, "/"
	 */
	public static final int VK_SLASH = 0x2F;

	/** VK_0 thru VK_9 are the same as ASCII '0' thru '9' (0x30 - 0x39) */
	public static final int VK_0 = 0x30;
	public static final int VK_1 = 0x31;
	public static final int VK_2 = 0x32;
	public static final int VK_3 = 0x33;
	public static final int VK_4 = 0x34;
	public static final int VK_5 = 0x35;
	public static final int VK_6 = 0x36;
	public static final int VK_7 = 0x37;
	public static final int VK_8 = 0x38;
	public static final int VK_9 = 0x39;

	/**
	 * Constant for the semicolon key, ";"
	 */
	public static final int VK_SEMICOLON = 0x3B;

	/**
	 * Constant for the equals key, "="
	 */
	public static final int VK_EQUALS = 0x3D;

	/** VK_A thru VK_Z are the same as ASCII 'A' thru 'Z' (0x41 - 0x5A) */
	public static final int VK_A = 0x41;
	public static final int VK_B = 0x42;
	public static final int VK_C = 0x43;
	public static final int VK_D = 0x44;
	public static final int VK_E = 0x45;
	public static final int VK_F = 0x46;
	public static final int VK_G = 0x47;
	public static final int VK_H = 0x48;
	public static final int VK_I = 0x49;
	public static final int VK_J = 0x4A;
	public static final int VK_K = 0x4B;
	public static final int VK_L = 0x4C;
	public static final int VK_M = 0x4D;
	public static final int VK_N = 0x4E;
	public static final int VK_O = 0x4F;
	public static final int VK_P = 0x50;
	public static final int VK_Q = 0x51;
	public static final int VK_R = 0x52;
	public static final int VK_S = 0x53;
	public static final int VK_T = 0x54;
	public static final int VK_U = 0x55;
	public static final int VK_V = 0x56;
	public static final int VK_W = 0x57;
	public static final int VK_X = 0x58;
	public static final int VK_Y = 0x59;
	public static final int VK_Z = 0x5A;

	/**
	 * Constant for the open bracket key, "["
	 */
	public static final int VK_OPEN_BRACKET = 0x5B;

	/**
	 * Constant for the back slash key, "\"
	 */
	public static final int VK_BACK_SLASH = 0x5C;

	/**
	 * Constant for the close bracket key, "]"
	 */
	public static final int VK_CLOSE_BRACKET = 0x5D;

	public static final int VK_NUMPAD0 = 0x60;
	public static final int VK_NUMPAD1 = 0x61;
	public static final int VK_NUMPAD2 = 0x62;
	public static final int VK_NUMPAD3 = 0x63;
	public static final int VK_NUMPAD4 = 0x64;
	public static final int VK_NUMPAD5 = 0x65;
	public static final int VK_NUMPAD6 = 0x66;
	public static final int VK_NUMPAD7 = 0x67;
	public static final int VK_NUMPAD8 = 0x68;
	public static final int VK_NUMPAD9 = 0x69;

	public static final int VK_MULTIPLY = 0x6A;
	public static final int VK_ADD = 0x6B;
	public static final int VK_SUBTRACT = 0x6D;
	public static final int VK_DECIMAL = 0x6E;
	public static final int VK_DIVIDE = 0x6F;
	public static final int VK_DELETE = 0x7F; /* ASCII DEL */

	/** Constant for the function key. */
	public static final int VK_F1 = 0x70;
	public static final int VK_F2 = 0x71;
	public static final int VK_F3 = 0x72;
	public static final int VK_F4 = 0x73;
	public static final int VK_F5 = 0x74;
	public static final int VK_F6 = 0x75;
	public static final int VK_F7 = 0x76;
	public static final int VK_F8 = 0x77;
	public static final int VK_F9 = 0x78;
	public static final int VK_F10 = 0x79;
	public static final int VK_F11 = 0x7A;
	public static final int VK_F12 = 0x7B;

	public static final int VK_INSERT = 0x9B;
	public static final int VK_META = 0x9D;

	public static final int VK_BACK_QUOTE = 0xC0;
	public static final int VK_QUOTE = 0xDE;

	/**
	 * Constant for the "#" key.
	 * 
	 * @since 1.2
	 */
	public static final int VK_NUMBER_SIGN = 0x0208;

	/**
	 * Constant for the "+" key.
	 * 
	 * @since 1.2
	 */
	public static final int VK_PLUS = 0x0209;

	/**
	 * Constant for the Microsoft Windows "Windows" key. It is used for both the
	 * left and right version of the key.
	 * 
	 * @see #getKeyLocation()
	 * @since 1.5
	 */
	public static final int VK_WINDOWS = 0x020C;

	/*
	 * 添加按钮用的键值对，上下左右键仅用于特征识别。。。
	 */
	public static final String UP = "↑";
	public static final String DOWN = "↓";
	public static final String LEFT = "←";
	public static final String RIGHT = "→";

	public static final String[] KEYS = { "A", "B", "C", "D", "E", "F", "G",
			"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z", "ENTER", "SPACE", "ESC", "CONTROL",
			"ALT", "HOME", "END", "INSERT", "DELETE", "SHIFT", UP, RIGHT, DOWN,
			LEFT, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	public static final int[] VALUES = { VK_A, VK_B, VK_C, VK_D, VK_E, VK_F,
			VK_G, VK_H, VK_I, VK_J, VK_K, VK_L, VK_M, VK_N, VK_O, VK_P, VK_Q,
			VK_R, VK_S, VK_T, VK_U, VK_V, VK_W, VK_X, VK_Y, VK_Z, VK_ENTER,
			VK_SPACE, VK_ESCAPE, VK_CONTROL, VK_ALT, VK_HOME, VK_END,
			VK_INSERT, VK_DELETE, VK_SHIFT, VK_UP, VK_RIGHT, VK_DOWN, VK_LEFT,
			VK_0, VK_1, VK_2, VK_3, VK_4, VK_5, VK_6, VK_7, VK_8, VK_9 };

	/*
	 * 设置传感器用的键值对
	 */

	public static final String[] KEYS2 = { "OFF", "A", "B", "C", "D", "E", "F",
			"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
			"T", "U", "V", "W", "X", "Y", "Z", "ENTER", "SPACE", "ESC",
			"CONTROL", "ALT", "HOME", "END", "INSERT", "DELETE", "SHIFT", UP,
			RIGHT, DOWN, LEFT, "0", "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10",
			"F11", "F12", };
	public static final int[] VALUES2 = { -1, VK_A, VK_B, VK_C, VK_D, VK_E,
			VK_F, VK_G, VK_H, VK_I, VK_J, VK_K, VK_L, VK_M, VK_N, VK_O, VK_P,
			VK_Q, VK_R, VK_S, VK_T, VK_U, VK_V, VK_W, VK_X, VK_Y, VK_Z,
			VK_ENTER, VK_SPACE, VK_ESCAPE, VK_CONTROL, VK_ALT, VK_HOME, VK_END,
			VK_INSERT, VK_DELETE, VK_SHIFT, VK_UP, VK_RIGHT, VK_DOWN, VK_LEFT,
			VK_0, VK_1, VK_2, VK_3, VK_4, VK_5, VK_6, VK_7, VK_8, VK_9, VK_F1,
			VK_F2, VK_F3, VK_F4, VK_F5, VK_F6, VK_F7, VK_F8, VK_F9, VK_F10,
			VK_F11, VK_F12 };

	public static int getValue(String key) {
		int i = 0;
		s: while (i < KEYS2.length) {
			if (key.equals(KEYS2[i]))
				break s;
			i++;
		}
		return VALUES2[i];
	}
}
