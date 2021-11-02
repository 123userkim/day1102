package com.sist.echo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

import javax.sound.midi.SysexMessage;

public class UDPChatSever {
	
	
	//data를 UDT방식으로 주고 받기위한 datagrampacket과 소킷을 멤버변수로 만들어야 함
	
	DatagramSocket socket;
	DatagramPacket packet;
	
	
	
	public UDPChatSever() {
		try {
			socket = new DatagramSocket(9005);
			
			//클라이언크로부터 수신되는 데이터를 담기 위한 byte의 배열과 패킷을 만들기
			byte []data= new byte[100];
			packet = new DatagramPacket(data, data.length);
			
			//무한대기 상태로 클라이언트가 보내온 데이터를 그대로 메아리하도록 함
			while(true) {
				
				//어디에 얼마만큼씩 패킷을 받아올 것인지
				//클라이언트로부터 데이터를 수신함 : 받아올 땐 보낸측의 inetAddress가 담겨져 있음
				socket.receive(packet);
				
				//클라이언트가 보내온 데이터를 출력하기
				String msg = new String(data);
				System.out.println("수신된 데이터 : "+msg.trim());
				
				//packtet안에 담긴 보내온 측의 InetAddress를 확인하기
				InetAddress addr= packet.getAddress();
				System.out.println("보내온 측의 주소 : "+addr);
				
				//그 패킷을 그대로 메아리
				socket.send(packet);
				
				//다음 데이터를 수신하도록 배열을 비워둠
				Arrays.fill(data, (byte)0);
				
			}
			
			
		}catch(Exception e) {
			System.out.println("예외발생 : "+e.getMessage());
		}
		
		
	
		
		
		
		
		
		
	}//생성자 end

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UDPChatSever();
	}

}
