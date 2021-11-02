package com.sist.chat;

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


public class ChatClient extends JFrame implements ActionListener {
	
	//대화내용을 출력할 텍스트에리어를 맴버변수로 만들어요
	JTextArea jta;
	
	//내가 대화내용을 입력할 텍스트필드를 맴버변수로 만들어요
	JTextField jtf;
	
	//입출력스트림을 맴버변수로 만들어요
	InputStream is;
	OutputStream os;
	
	public ChatClient() {
		
		
		//맴버변수 텍스트에리어와 텍스트필드를 생성합니다.
		jta = new JTextArea();
		jtf = new JTextField(50);
		
		
		//"전송"을 위한 버튼을 생성합니다.
		JButton btn = new JButton("전송");
		
		//버튼에 이벤트를 등록합니다.
		btn.addActionListener(this);
		
		//텍스트필드와 버튼을 담기위한 패널을 생성합니다.
		JPanel p = new JPanel();
		
		//패널의 레이아웃방식 ->borderLayout
		p.setLayout(new BorderLayout());
		p.add(jtf,BorderLayout.CENTER);
		p.add(btn,BorderLayout.SOUTH);
		
		//텍스트에리어를 스크롤팬으로 감싸요
		JScrollPane jsp = new JScrollPane(jta);
		
		//프레임의 가운데에 텍스트에리어를 감싸고 있는 스크롤팬을 담아요
		add(jsp,BorderLayout.CENTER);
		
		//텍스트필드와 버튼을 담고 있는 패널을 프레임의 아래에 담아요
		add(p,BorderLayout.EAST);
		
		//프레임의 크기를 설정합니다.
		setSize(100,200);
		
		//프레임이 화면에 보이도록 설정합니다.
		setVisible(true);
		
		try {
			
			//통신을 위하여 서버에 접속을 요청한다.
			
			
			Socket socket = new Socket("192.168.0.13", 9003);
			
			//입출력을 위한 스트림을 생성합니다.
			is = socket.getInputStream();
			os = socket.getOutputStream();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		//서버가 보내오는 데이터를 계속하여 받기 위한 쓰레드 클래스를 만들어 봅시다.
		//클래스 안에 있는 클래스를 inner클래스라 하고 
		//바깥에 있는 클래스는 outter클래스라고 합니다.
		//inner클래스는 마치 outter클래스 맴버처럼 동작해요
		//outter클래스의 맴버에 자유롭게 접근할 수 있어요.
		class ClientThread extends Thread{
			byte []data = new byte[100];
			public void run() {
				while(true) {
					try {
						//서버가 보내온 데이터를 수신합니다.
						is.read(data);
						
						//수신한 데이터을 문자열로 만들어요
						String msg = new String(data);
						
						//수신한 문자열을 텍스트에리어에 추가합니다.
						jta.append(msg.trim()+"\n");
						
					}catch (Exception e) {
						System.out.println("예외발생:"+e.getMessage());
					}
					
				}//end run
			}
		}//end innerClass
		
		//서버로부터 계속하여 수신된 메세지를 받기위한 쓰레드 객체를 생성하고 가동한다.
		ClientThread ct = new ClientThread();
		ct.start();
		
	}//end  생성자
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChatClient();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		try {
			//사용자가 입력한 텍스트필드의 내용을 서버로 보내도록 합니다.
			//OutputStream void	write(byte[] b) 메소드를 이용합니다.
			byte []data = jtf.getText().getBytes();
			os.write(data);
			
			//메세지를 전송하고 다음 메세지 입력을 위하여 텍스트필드를 깨끗이 지워요
			jtf.setText("");
			
		}catch (Exception ex) {
			System.out.println("예외발생:"+ex.getMessage());
		}
		
	}
}










