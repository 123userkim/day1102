package com.sist.chat;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

//서버가 한번에 여러명의 클라이언트와 대화가 가능하도록(메아리) 하기 위하여 
//클라이언트와 계속하여 대화를 주고 받는 기능은 쓰레드로 구현합니다.
class ServerThread extends Thread{
	
	//요청한 클라이언트와 연결된 소켓을 맴버변수로 선언합니다.
	Socket socket;
	
	//연결된 클라언트와 데이터를 주고받을 스트림을 선언합니다.
	InputStream is;
	OutputStream os;
	
	//실제로 데이터가 담길 배열을 선언합니다.
	byte[]data = new byte[100];

	//객체생성시 클라이언트와 연결된 소켓을 매개변수로 전달받아 맴버변수에 담고
	//스트림을 생성합니다.
	public ServerThread(Socket socket) {
		this.socket = socket;
		try {
			is = this.socket.getInputStream();
			os = this.socket.getOutputStream();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	
	
	public void run() {
		//연결이 된 클라이언트와 계속하여 데이터를 주고 받기 위하여 반복문을 이용합니다.
		while(true) {
			try {
				//클라이언트로 부터 데이터를 수신합니다.
				is.read(data);
				String msg = new String(data);
				System.out.println("수신된 데이터:" + msg.trim());
				
				//클라이언트로 부터 수신된 데이터를 그대로 메아리 합니다.
				//os.write(data);
				
				//접속한 모든 클라이언트들에게 데이터를 방송하고자 합니다.
				sendAll(data);
				
				
				//다음에 수신될 데이터를 위하여 배열을 깨끗이 비워줍니다.
				Arrays.fill(data, (byte)0);
			}catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}//end catch
		}//end while
	}//end run
	
	
	public void sendAll(byte []data) throws Exception{
		
		//모든 클라이언트를 담아둔 list로 부터 데이터를 하나씩 꺼집어 내어 오면 그것은 ServerThrea자료형입니다.
		for(ServerThread t  :ChatServer.list) {
			
			//각각의 SeverThread에는 연결한 클라이언트에게 데이터를 출력을 위한 스트림이 있어요
			//그 스트림을 통하여 데이터를 출력합니다.
			t.os.write(data);
		}		
	}
	
	
}//end class


public class ChatServer {

	// 요청한 클라이언트들을 모두 담아두기 위한 ArrayList를 만들어요.
	// ArrayList에 담는 자료형은 통신을 요청한 한명의 클라이언트와 계속하여 대화를 주고 받는 일읆 담당하는 쓰레드인 ServerThread를 담아요.
	public static ArrayList<ServerThread> list = new ArrayList<ServerThread>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//클라이언트로 부터 수신된 데이터를 담기 위한 byte의 배열을 만들어요
		byte []data = new byte[100];
		
		try {
			//1. SeverSocket을 통해 서버를 가동한다.
			ServerSocket server = new ServerSocket(9003);
			
			while(true) { //무한대기 상태로 클라이언트의 접속을 기다린다.
				
				//클라이언트의 요청을 수락한다.
				Socket socket = server.accept();
				
				//연결된 클라이언트와 대화을 계속주고 받기 위한 쓰레드객체를 생성하고 쓰레드를 가동한다.
				ServerThread thread = new ServerThread(socket);
				thread.start();
				
				//접속한 모든클라이트와 통신을 담당하는 객체를 담아 두기 위한 리스트에 담아요.
				list.add(thread);
			}
			
		}catch (Exception e) {
			
		}
	}
}
