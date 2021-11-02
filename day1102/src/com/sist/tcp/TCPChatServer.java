package com.sist.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

//server가 한번에 여러 명의 클라이언트와 대화가 가능하도록(메아리)하기 위하여 
//클라이언트와 계속하여 대화를 주고 받는 기능은 쓰레드로 표현

class SeverThread extends Thread{
	
	//요청한 클라이언트와 연결된 소켓을 멤버변수로 선언함
	Socket socket;
		
	//연결된 클라이언트와 데이터를 주고받을 스트림을 생성함
	InputStream is;
	OutputStream os;
	
	byte []data = new byte[100];

	
	//객체생성시 클라이언트와 연결된 소킷을 매개변수로 전달받아 멤버변수에 담기
		public SeverThread(Socket socket) {
			this.socket = socket;
			try {
				is = this.socket.getInputStream();
				os = this.socket.getOutputStream();
				
			}catch(Exception e) {
				System.out.println("예외발생 : "+e.getMessage());
			}
		}
	
	public void run() {
		//연결이 된 클라이언트와 계속하여 데이터를 주고 받기 위해서 반복문을 이용함
			while(true) {
				//클라이언트로부터 데이터를 수신함
				
				try {
				is.read(data);
				String msg = new String(data);
				System.out.println("수신된 데이터: " + msg.trim());
				
				//클라이언트로부터 수신된 데이터를 그대로 메아리
				os.write(data);
				
				//다음 데이터를 위해서 배열을 깨끗이 비워줌
				Arrays.fill(data, (byte)0);
				}catch(Exception e) {
					System.out.println("예외발생 : "+e.getMessage());
				}//end catch
			}//end while
		}//end run
	}//end class


public class TCPChatServer {
	
	public static void main(String[] args) {
		
		//클라이언트로부터 수신된 데이터를 담기 위한 byte의 배열을 만듦
		byte []data =new byte[100];
		
		try {
			
			//severSocket(클라이언트의 요청을 받기 위한 준비를 하는 것)을 통해 서버를 가동함
			ServerSocket sever = new ServerSocket(9003);
			
			while(true) { 
				//무한 대기 상태로 클라이언트의 접속을 기다림
				//클라이언트의 요청을 수락한다
				Socket socket = sever.accept();
				
				SeverThread thread= new SeverThread(socket);
				thread.start();
			}
			
		}catch(Exception e) {
			
		}
	}
}
