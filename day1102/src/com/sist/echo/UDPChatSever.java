package com.sist.echo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

import javax.sound.midi.SysexMessage;

public class UDPChatSever {
	
	
	//data�� UDT������� �ְ� �ޱ����� datagrampacket�� ��Ŷ�� ��������� ������ ��
	
	DatagramSocket socket;
	DatagramPacket packet;
	
	
	
	public UDPChatSever() {
		try {
			socket = new DatagramSocket(9005);
			
			//Ŭ���̾�ũ�κ��� ���ŵǴ� �����͸� ��� ���� byte�� �迭�� ��Ŷ�� �����
			byte []data= new byte[100];
			packet = new DatagramPacket(data, data.length);
			
			//���Ѵ�� ���·� Ŭ���̾�Ʈ�� ������ �����͸� �״�� �޾Ƹ��ϵ��� ��
			while(true) {
				
				//��� �󸶸�ŭ�� ��Ŷ�� �޾ƿ� ������
				//Ŭ���̾�Ʈ�κ��� �����͸� ������ : �޾ƿ� �� �������� inetAddress�� ����� ����
				socket.receive(packet);
				
				//Ŭ���̾�Ʈ�� ������ �����͸� ����ϱ�
				String msg = new String(data);
				System.out.println("���ŵ� ������ : "+msg.trim());
				
				//packtet�ȿ� ��� ������ ���� InetAddress�� Ȯ���ϱ�
				InetAddress addr= packet.getAddress();
				System.out.println("������ ���� �ּ� : "+addr);
				
				//�� ��Ŷ�� �״�� �޾Ƹ�
				socket.send(packet);
				
				//���� �����͸� �����ϵ��� �迭�� �����
				Arrays.fill(data, (byte)0);
				
			}
			
			
		}catch(Exception e) {
			System.out.println("���ܹ߻� : "+e.getMessage());
		}
		
		
	
		
		
		
		
		
		
	}//������ end

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UDPChatSever();
	}

}
