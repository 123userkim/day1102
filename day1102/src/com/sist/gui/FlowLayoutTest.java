package com.sist.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.FlowLayout;

public class FlowLayoutTest extends JFrame{
	
	public FlowLayoutTest() {
		
		//프레임에 flowLayout을 설정함
		setLayout(new FlowLayout());
				
		//반복문을 이용해서 버튼을 15개를 생성해서 프레임에 담기
		for(int i =1 ; i<=15; i++) {
			add(new JButton("버튼"+i));
			
		}
		
		//프레임의 크기를 설정하고 화면에 보이도록 설정함
		setSize(400,300);
		setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FlowLayoutTest();

	}

}
