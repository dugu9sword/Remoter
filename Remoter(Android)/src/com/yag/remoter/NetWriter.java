package com.yag.remoter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import android.util.Log;

import com.yag.remoter.messages.KeyMessage;
import com.yag.remoter.messages.Message;

/*
 * 用于信息发送
 */
public class NetWriter {
	private static DatagramSocket socket;

	private NetWriter() {
	}

	public static void write(Message message) {
		try {
			byte[] buf = new byte[2560];
			socket = RemoterApp.socket;
			DatagramPacket sendPacket = RemoterApp.sendPacket;
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream;
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(message);
			sendPacket.setData(byteArrayOutputStream.toByteArray());
			socket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}