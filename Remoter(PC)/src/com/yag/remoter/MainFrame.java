package com.yag.remoter;

import javax.swing.JFrame;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class MainFrame extends JFrame {
	private JTextField port;
	private JLabel linkedDevice;
	private JButton link;

	/*
	 * 内部类，使用线程操作网络连接，并控制界面。
	 */
	class Linker extends Thread {
		public void run() {
			try {
				int portNum = Integer.parseInt(port.getText());
				link.setEnabled(false);
				port.setEditable(false);
				Core.link(portNum);
				linkedDevice.setText("ON");
				Core.run();
				link.setEnabled(true);
				port.setEditable(true);
				linkedDevice.setText("OFF");
			} catch (Exception e) {
				linkedDevice.setText("ERROR");
				link.setEnabled(true);
				port.setEditable(true);
			}
		}
	}

	public MainFrame() {
		getContentPane().setLayout(null);

		JPanel panel = new ImagePanel();
		getContentPane().add(panel);
		panel.setLayout(null);

		ImageIcon wifi = new ImageIcon("pic\\wifi.gif");
		JLabel wifilabel = new JLabel(wifi);
		wifilabel.setBounds(0, 0, 500, 135);
		panel.add(wifilabel);

		// 获取电脑IP
		JLabel pcip = new JLabel(Core.getIP());
		pcip.setFont(new Font("微软雅黑", Font.PLAIN, 29));
		pcip.setForeground(Color.white);
		pcip.setBounds(120, 236, 220, 34);
		panel.add(pcip);

		// 显示服务开启状态
		linkedDevice = new JLabel("OFF");
		linkedDevice.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		linkedDevice.setForeground(Color.white);
		linkedDevice.setBounds(230, 415, 205, 34);
		panel.add(linkedDevice);

		// 端口输入框
		port = new JTextField();
		port.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		port.setText("12345");
		port.setBounds(160, 323, 155, 37);
		panel.add(port);
		port.setColumns(10);

		// 连接按钮
		link = new JButton(new ImageIcon("pic\\link.jpg"));
		link.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Linker().start();
			}
		});
		link.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		link.setBounds(72, 500, 240, 80);
		panel.add(link);

		setSize(390, 703);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Remoter");
		setResizable(false);
	}

	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
	}

	class ImagePanel extends JPanel {

		ImagePanel() {
			setBounds(0, 0, 400, 703);
			setLayout(null);
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			ImageIcon icon = new ImageIcon("pic\\Cover.jpg");
			g.drawImage(icon.getImage(), 0, 0, null);
		}
	}
}