package com.sist.tcp;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TCPChatClient extends JFrame implements ActionListener{
	
	//대화내용을 출력할 텍스트에리어를 멤버변수로 만들기
	JTextArea jta;
	
	//내가 입력할 대화내용을 입력할 텍스트 필드를 멤버변수로
	JTextField jtf;
	
	//입출력 스트림을 멤버변수로 만들기
	InputStream is;
	OutputStream os;
	
	public TCPChatClient() {
		
		
		
		
		jta = new JTextArea();
		jtf = new JTextField(50);
		
		//전송을 위한 버튼을 생성함
		JButton btn = new JButton("전송");
		//버튼의 이벤트를 등록함
		btn.addActionListener(this);
		
		//텍스트필드와 버튼을 담기 위한 패널을 생성함
		JPanel p = new JPanel();
		p.add(jtf);
		p.add(btn);
		
		//스크롤펜으로 감싸기
		JScrollPane jsp = new JScrollPane(jta);
		
		//프레임의 가운데에 텍스트에리어를 감싸는 스크롤펜을 담음
		add(jsp,BorderLayout.CENTER);
		
		//텍스트 필드와 버튼을 담고있는 패널을 아랫쪽에
		add(p,BorderLayout.SOUTH);
		
		//프레임의 크리를 설정
		setSize(800,600);
		setVisible(true);
		
		try {
			//통신을 위해 서버에 접속을 요청함
			Socket socket = new Socket("192.168.0.13",9003);
			is = socket.getInputStream();
			os=socket.getOutputStream();
			
		}catch(Exception e) {
			
		}
		
		//서버가 보내오는 데이터를 계속해서 받기위한 쓰레드 클래스를 생성해보자
		//클래스 안에 있는 클래스를 inner클래스 -> 마치 outter클래스의 멤버처럼 작동함
		//바깥에 있는 클래스는 outter 클래스
		class ClinetThread extends Thread{
			byte []data =new byte[100];

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						//서버가 보내온 데이터를 수신함
					 is.read(data);
					 
					 //수신한 데이터를 문자열로 만듦
					 String msg = new String (data);
					 
					 //공백을 제거해서 수신한 문자열을 텍스트에리어에 추가함
					 jta.append(msg.trim()+"\n");
					 
					}catch(Exception e) {
						System.out.println("예외발생 : "+e.getMessage());
					}
				}//while
				
			}//run
			
		}//이너 클래스
		
		//서버로부터 계속하여 수신된 메시지를 받기 위한 thread 객체를 생성후 가동
		ClinetThread ct= new ClinetThread();
		ct.start();
		
		
	}//생성자

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new TCPChatClient ();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		try {
			
			//사용자가 입력한 텍스트필드의 내용을 byte형의 배열로 만들어 서버로 보내기
			//outputStream void	write(byte[] b) 메소드를 이용함
			byte []data = jtf.getText().getBytes();		
			os.write(data);
			
			//메시지를 전성하고 다음메시지를 위해 텍스트 필드 깨끗이
			jtf.setText("");
			
			
			
		}catch(Exception ex) {
			System.out.println("예외발생 : "+ex.getMessage());
		}
	}

}
