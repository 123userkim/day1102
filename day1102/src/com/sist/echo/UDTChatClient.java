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
	
	//data를 UDT방식으로 주고 받기위한 DatagramSocket과 소킷을 멤버변수로 만들어야 함
	
		DatagramSocket socket;
		DatagramPacket packet;

	
	
	//대화 내용을 표시하기 위한 txtArea를 멤버변수로 선언함
			JTextArea jta;
			
	//대화내용을 입력하기 위한 txtFiled 선언
			JTextField jtf;
	
	public UDTChatClient() {
		
		jta = new JTextArea();
		jtf = new JTextField(50); 
		
		//전송을위한 버튼을 생성함
		JButton btn = new JButton("전송");
		
		
		
		//버튼과 필드를 담을 패널을 만듦
		JPanel p = new JPanel();
		p.add(jtf);
		p.add(btn);
		
		
		//텍스트에리어에 스트롤을 표현하는 스크롤펜 객체를 생성함
		JScrollPane jsp = new JScrollPane(jta);
		
		//프레임 가운데에 텍스트에리어를 감싸고 있는 스크롤 펜
		add(jsp, BorderLayout.CENTER);
		
		//프레임의 아래에 필드와 버튼을 담고있는 패널 넣기
		add(p,BorderLayout.SOUTH);
		
		setSize(700,500);
		setVisible(true);
		
		try {
			//데이터를 주고 받기위한 메소드를 갖고 있는 DatagramSocket생성
			socket = new DatagramSocket();
			
		}catch(Exception e) {
			System.out.println("예외발생 :"+e.getMessage());
		}
		
		btn.addActionListener(this);
		
		
		
				//서버로부터 데이터를 계속 수신하기 위한 쓰레듴
				class ClientThread implements Runnable{
					
					//데이터를 수신하기 위한 패킷을 따로 생성함
					DatagramPacket packet;
					byte []data = new byte[100];
					
		
					@Override
					public void run() {
						
						//data수신을 위한 패킷을 따로 생성함
						packet= new DatagramPacket(data, data.length);
										
						//계속하여 서버로부터 데이터를 수신하도록
						while(true) {
							//데이터를 수신하기 위해서 socket클래스의 receive메소드 사용
							
							try {							
								
								socket.receive(packet);
								
								//서버로부터 수신된 데이터의 배열을 문자열로
								String msg = new String(data);
								
								//수신된 문자열을 텍스트에리어에 추가
								jta.append(msg.trim()+"\n");
								
								Arrays.fill(data, (byte)0);
								
							}catch(Exception e) {
								System.out.println("예외처리:"+e.getMessage());
							}
						}
					}
					
				}//end이너클래스
				
				//thread객체를 생성하고, thread를 가동
				new Thread(new ClientThread()).start();
				
				
				
	}//end생성자

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UDTChatClient();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		try {
			//사용자입력한 대화내용이 있는 텍스트 필드는 문자열을 갖고옴
			String msg = jtf.getText();
			
			//UDT방식으로 데이터를 보내려면 패킷을 생성
			//그래서 이문자열을 byte[]배열로 만들어야만 함
			byte []data = msg.getBytes();
			
			
			//ip목적지 주소를 갖는 InetAddress객체를 생성함
			InetAddress addr= InetAddress.getByName("192.168.0.13"); 
			
			
			int port = 9005;
			
			//데이터를 보낼 패킷을 생성
			packet = new DatagramPacket(data, data.length, addr, port);
			
			//서버로 데이터를 전송함
			socket.send(packet);
			
		}catch(Exception ex) {
			System.out.println("예외발생 :"+ex.getMessage());
		}
		
		
		
		
	}

}
