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
	 * ���IP��ַ
	 */
	public static String getIP() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String ip99 = addr.getHostAddress().toString();// ��ȡ����ip
		return ip99;
	}

	/*
	 * ���Զ˴����Ӳ������ֻ�����
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
	 * �����ں�
	 */
	public static void run() {
		try {
			robot = new Robot();
			s: while (true) {
				Message message = (Message) readMessage();

				// ���Message���ڰ�������Ϣ
				if (message instanceof KeyMessage) {
					KeyMessage keyMessage = (KeyMessage) message;
					if (keyMessage.key != -1) {
						if (keyMessage.condition == KeyMessage.KEY_PRESSED)
							robot.keyPress(keyMessage.key);
						if (keyMessage.condition == KeyMessage.KEY_RELEASED)
							robot.keyRelease(keyMessage.key);
					}
				}

				// ���Message�����������Ϣ
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

				// ���Message��������ƶ���Ϣ
				if (message instanceof MotionMessage) {
					MotionMessage motionMessage = (MotionMessage) message;
					Point mousePoint = MouseInfo.getPointerInfo().getLocation();
					robot.mouseMove(mousePoint.x
							+ (int) (motionMessage.dx), mousePoint.y
							+ (int) (motionMessage.dy));
				}

				// ���Message���ڹر���Ϣ
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
