package com.sist.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

//server�� �ѹ��� ���� ���� Ŭ���̾�Ʈ�� ��ȭ�� �����ϵ���(�޾Ƹ�)�ϱ� ���Ͽ� 
//Ŭ���̾�Ʈ�� ����Ͽ� ��ȭ�� �ְ� �޴� ����� ������� ǥ��

class SeverThread extends Thread{
	
	//��û�� Ŭ���̾�Ʈ�� ����� ������ ��������� ������
	Socket socket;
		
	//����� Ŭ���̾�Ʈ�� �����͸� �ְ���� ��Ʈ���� ������
	InputStream is;
	OutputStream os;
	
	byte []data = new byte[100];

	
	//��ü������ Ŭ���̾�Ʈ�� ����� ��Ŷ�� �Ű������� ���޹޾� ��������� ���
		public SeverThread(Socket socket) {
			this.socket = socket;
			try {
				is = this.socket.getInputStream();
				os = this.socket.getOutputStream();
				
			}catch(Exception e) {
				System.out.println("���ܹ߻� : "+e.getMessage());
			}
		}
	
	public void run() {
		//������ �� Ŭ���̾�Ʈ�� ����Ͽ� �����͸� �ְ� �ޱ� ���ؼ� �ݺ����� �̿���
			while(true) {
				//Ŭ���̾�Ʈ�κ��� �����͸� ������
				
				try {
				is.read(data);
				String msg = new String(data);
				System.out.println("���ŵ� ������: " + msg.trim());
				
				//Ŭ���̾�Ʈ�κ��� ���ŵ� �����͸� �״�� �޾Ƹ�
				os.write(data);
				
				//���� �����͸� ���ؼ� �迭�� ������ �����
				Arrays.fill(data, (byte)0);
				}catch(Exception e) {
					System.out.println("���ܹ߻� : "+e.getMessage());
				}//end catch
			}//end while
		}//end run
	}//end class


public class TCPChatServer {
	
	public static void main(String[] args) {
		
		//Ŭ���̾�Ʈ�κ��� ���ŵ� �����͸� ��� ���� byte�� �迭�� ����
		byte []data =new byte[100];
		
		try {
			
			//severSocket(Ŭ���̾�Ʈ�� ��û�� �ޱ� ���� �غ� �ϴ� ��)�� ���� ������ ������
			ServerSocket sever = new ServerSocket(9003);
			
			while(true) { 
				//���� ��� ���·� Ŭ���̾�Ʈ�� ������ ��ٸ�
				//Ŭ���̾�Ʈ�� ��û�� �����Ѵ�
				Socket socket = sever.accept();
				
				SeverThread thread= new SeverThread(socket);
				thread.start();
			}
			
		}catch(Exception e) {
			
		}
	}
}
