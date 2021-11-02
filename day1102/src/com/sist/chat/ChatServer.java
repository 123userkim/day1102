package com.sist.chat;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

//������ �ѹ��� �������� Ŭ���̾�Ʈ�� ��ȭ�� �����ϵ���(�޾Ƹ�) �ϱ� ���Ͽ� 
//Ŭ���̾�Ʈ�� ����Ͽ� ��ȭ�� �ְ� �޴� ����� ������� �����մϴ�.
class ServerThread extends Thread{
	
	//��û�� Ŭ���̾�Ʈ�� ����� ������ �ɹ������� �����մϴ�.
	Socket socket;
	
	//����� Ŭ���Ʈ�� �����͸� �ְ���� ��Ʈ���� �����մϴ�.
	InputStream is;
	OutputStream os;
	
	//������ �����Ͱ� ��� �迭�� �����մϴ�.
	byte[]data = new byte[100];

	//��ü������ Ŭ���̾�Ʈ�� ����� ������ �Ű������� ���޹޾� �ɹ������� ���
	//��Ʈ���� �����մϴ�.
	public ServerThread(Socket socket) {
		this.socket = socket;
		try {
			is = this.socket.getInputStream();
			os = this.socket.getOutputStream();
		}catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
		}
	}
	
	
	
	public void run() {
		//������ �� Ŭ���̾�Ʈ�� ����Ͽ� �����͸� �ְ� �ޱ� ���Ͽ� �ݺ����� �̿��մϴ�.
		while(true) {
			try {
				//Ŭ���̾�Ʈ�� ���� �����͸� �����մϴ�.
				is.read(data);
				String msg = new String(data);
				System.out.println("���ŵ� ������:" + msg.trim());
				
				//Ŭ���̾�Ʈ�� ���� ���ŵ� �����͸� �״�� �޾Ƹ� �մϴ�.
				//os.write(data);
				
				//������ ��� Ŭ���̾�Ʈ�鿡�� �����͸� ����ϰ��� �մϴ�.
				sendAll(data);
				
				
				//������ ���ŵ� �����͸� ���Ͽ� �迭�� ������ ����ݴϴ�.
				Arrays.fill(data, (byte)0);
			}catch (Exception e) {
				System.out.println("���ܹ߻�:"+e.getMessage());
			}//end catch
		}//end while
	}//end run
	
	
	public void sendAll(byte []data) throws Exception{
		
		//��� Ŭ���̾�Ʈ�� ��Ƶ� list�� ���� �����͸� �ϳ��� ������ ���� ���� �װ��� ServerThrea�ڷ����Դϴ�.
		for(ServerThread t  :ChatServer.list) {
			
			//������ SeverThread���� ������ Ŭ���̾�Ʈ���� �����͸� ����� ���� ��Ʈ���� �־��
			//�� ��Ʈ���� ���Ͽ� �����͸� ����մϴ�.
			t.os.write(data);
		}		
	}
	
	
}//end class


public class ChatServer {

	// ��û�� Ŭ���̾�Ʈ���� ��� ��Ƶα� ���� ArrayList�� ������.
	// ArrayList�� ��� �ڷ����� ����� ��û�� �Ѹ��� Ŭ���̾�Ʈ�� ����Ͽ� ��ȭ�� �ְ� �޴� �ϟ� ����ϴ� �������� ServerThread�� ��ƿ�.
	public static ArrayList<ServerThread> list = new ArrayList<ServerThread>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Ŭ���̾�Ʈ�� ���� ���ŵ� �����͸� ��� ���� byte�� �迭�� ������
		byte []data = new byte[100];
		
		try {
			//1. SeverSocket�� ���� ������ �����Ѵ�.
			ServerSocket server = new ServerSocket(9003);
			
			while(true) { //���Ѵ�� ���·� Ŭ���̾�Ʈ�� ������ ��ٸ���.
				
				//Ŭ���̾�Ʈ�� ��û�� �����Ѵ�.
				Socket socket = server.accept();
				
				//����� Ŭ���̾�Ʈ�� ��ȭ�� ����ְ� �ޱ� ���� �����尴ü�� �����ϰ� �����带 �����Ѵ�.
				ServerThread thread = new ServerThread(socket);
				thread.start();
				
				//������ ���Ŭ����Ʈ�� ����� ����ϴ� ��ü�� ��� �α� ���� ����Ʈ�� ��ƿ�.
				list.add(thread);
			}
			
		}catch (Exception e) {
			
		}
	}
}
