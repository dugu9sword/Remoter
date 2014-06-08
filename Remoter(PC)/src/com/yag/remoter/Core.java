package com.yag.remoter;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import com.yag.remoter.messages.*;

public class Core {

	static DatagramSocket socket;
	static Robot robot;
	static ObjectInputStream ois;
	private static byte[] buf;

	/*
	 * 获得IP地址
	 */
	public static String getIP() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String ip99 = addr.getHostAddress().toString();// 获取本机ip
		return ip99;
	}

	/*
	 * 电脑端打开连接并接受手机连接
	 */
	public static void link(int port) throws IOException {
		buf = new byte[256];
		socket = new DatagramSocket(port);
	}

	public static Message readMessage() throws ClassNotFoundException,
			IOException {
		DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
		socket.receive(receivePacket);
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				buf);
		ObjectInputStream objectInputStream = new ObjectInputStream(
				byteArrayInputStream);
		Message message = (Message) (objectInputStream.readObject());
		return message;
	}

	/*
	 * 运行内核
	 */
	public static void run() {
		try {
			robot = new Robot();
			s: while (true) {
				Message message = (Message) readMessage();

				// 如果Message属于按键类信息
				if (message instanceof KeyMessage) {
					KeyMessage keyMessage = (KeyMessage) message;
					if (keyMessage.key != -1) {
						if (keyMessage.condition == KeyMessage.KEY_PRESSED)
							robot.keyPress(keyMessage.key);
						if (keyMessage.condition == KeyMessage.KEY_RELEASED)
							robot.keyRelease(keyMessage.key);
					}
				}

				// 如果Message属于鼠标点击信息
				if (message instanceof MouseClickMessage) {
					MouseClickMessage mouseClickMessage = (MouseClickMessage) message;
					if (mouseClickMessage.key == mouseClickMessage.click) {
						robot.mousePress(InputEvent.BUTTON1_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_MASK);
					}
					if (mouseClickMessage.key == mouseClickMessage.rightclick) {
						robot.mousePress(InputEvent.BUTTON3_MASK);
						robot.mouseRelease(InputEvent.BUTTON3_MASK);
					}
				}

				// 如果Message属于鼠标移动信息
				if (message instanceof MotionMessage) {
					MotionMessage motionMessage = (MotionMessage) message;
					Point mousePoint = MouseInfo.getPointerInfo().getLocation();
					robot.mouseMove(mousePoint.x
							+ (int) (motionMessage.dx), mousePoint.y
							+ (int) (motionMessage.dy));
				}

				// 如果Message属于关闭信息
				if (message instanceof EndingMessage) {
					socket.close();
					break s;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
