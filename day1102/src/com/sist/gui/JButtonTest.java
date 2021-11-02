package com.sist.gui;

import javax.swing.JFrame;

public class JButtonTest extends JFrame {
	
	public JButtonTest() {
		//창이 보이게 하려면 창의 크기 설정 필수
		
	}

	public static void main(String[] args) {
		
		//우리가 만든 JBouttenTest가 곧 JFream임
		JButtonTest  f =  new JButtonTest();
		
		//창을 만들었지만 창이 화면에 보이지 않음
		//보이게 하려면 창의 가로세로 길이 설정, 화면에 보이도록 설정 필수
		
		f.setSize(400,300);
		f.setVisible(true);
		
		//창을 닫을 때에 프로그램도 같이 종료함
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
