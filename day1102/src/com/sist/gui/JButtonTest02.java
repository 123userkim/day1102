package com.sist.gui;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JButtonTest02 extends JFrame {
	
	public JButtonTest02() {  //나의 모양을 정해줘
		
		
		//버튼을 생성하여 "창"에 담기
		JButton btn01 = new JButton("버튼1");
		JButton btn02 = new JButton("버튼2");
		
		add(btn01);
		add(btn02);
		
		//창이 보이게 하려면 창의 크기 설정 필수
		setSize(400,300);
		setVisible(true);
		
		//창을 닫을 때에 프로그램도 같이 종료함
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		
		//우리가 만든 JBouttenTest가 곧 JFream임
		//변수 이름은 굳이임, 그래서 객체를 생성하기만 해도 좋음
		new JButtonTest02();
		
	}

}
