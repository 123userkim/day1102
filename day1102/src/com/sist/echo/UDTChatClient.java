package com.sist.echo;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.BorderLayout;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class UDTChatClient extends JFrame implements ActionListener{
	
	//data�� UDT������� �ְ� �ޱ����� DatagramSocket�� ��Ŷ�� ��������� ������ ��
	
		DatagramSocket socket;
		DatagramPacket packet;

	
	
	//��ȭ ������ ǥ���ϱ� ���� txtArea�� ��������� ������
			JTextArea jta;
			
	//��ȭ������ �Է��ϱ� ���� txtFiled ����
			JTextField jtf;
	
	public UDTChatClient() {
		
		jta = new JTextArea();
		jtf = new JTextField(50); 
		
		//���������� ��ư�� ������
		JButton btn = new JButton("����");
		
		
		
		//��ư�� �ʵ带 ���� �г��� ����
		JPanel p = new JPanel();
		p.add(jtf);
		p.add(btn);
		
		
		//�ؽ�Ʈ����� ��Ʈ���� ǥ���ϴ� ��ũ���� ��ü�� ������
		JScrollPane jsp = new JScrollPane(jta);
		
		//������ ����� �ؽ�Ʈ����� ���ΰ� �ִ� ��ũ�� ��
		add(jsp, BorderLayout.CENTER);
		
		//�������� �Ʒ��� �ʵ�� ��ư�� ����ִ� �г� �ֱ�
		add(p,BorderLayout.SOUTH);
		
		setSize(700,500);
		setVisible(true);
		
		try {
			//�����͸� �ְ� �ޱ����� �޼ҵ带 ���� �ִ� DatagramSocket����
			socket = new DatagramSocket();
			
		}catch(Exception e) {
			System.out.println("���ܹ߻� :"+e.getMessage());
		}
		
		btn.addActionListener(this);
		
		
		
				//�����κ��� �����͸� ��� �����ϱ� ���� ������
				class ClientThread implements Runnable{
					
					//�����͸� �����ϱ� ���� ��Ŷ�� ���� ������
					DatagramPacket packet;
					byte []data = new byte[100];
					
		
					@Override
					public void run() {
						
						//data������ ���� ��Ŷ�� ���� ������
						packet= new DatagramPacket(data, data.length);
										
						//����Ͽ� �����κ��� �����͸� �����ϵ���
						while(true) {
							//�����͸� �����ϱ� ���ؼ� socketŬ������ receive�޼ҵ� ���
							
							try {							
								
								socket.receive(packet);
								
								//�����κ��� ���ŵ� �������� �迭�� ���ڿ���
								String msg = new String(data);
								
								//���ŵ� ���ڿ��� �ؽ�Ʈ����� �߰�
								jta.append(msg.trim()+"\n");
								
								Arrays.fill(data, (byte)0);
								
							}catch(Exception e) {
								System.out.println("����ó��:"+e.getMessage());
							}
						}
					}
					
				}//end�̳�Ŭ����
				
				//thread��ü�� �����ϰ�, thread�� ����
				new Thread(new ClientThread()).start();
				
				
				
	}//end������

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UDTChatClient();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		try {
			//������Է��� ��ȭ������ �ִ� �ؽ�Ʈ �ʵ�� ���ڿ��� �����
			String msg = jtf.getText();
			
			//UDT������� �����͸� �������� ��Ŷ�� ����
			//�׷��� �̹��ڿ��� byte[]�迭�� �����߸� ��
			byte []data = msg.getBytes();
			
			
			//ip������ �ּҸ� ���� InetAddress��ü�� ������
			InetAddress addr= InetAddress.getByName("192.168.0.13"); 
			
			
			int port = 9005;
			
			//�����͸� ���� ��Ŷ�� ����
			packet = new DatagramPacket(data, data.length, addr, port);
			
			//������ �����͸� ������
			socket.send(packet);
			
		}catch(Exception ex) {
			System.out.println("���ܹ߻� :"+ex.getMessage());
		}
		
		
		
		
	}

}
