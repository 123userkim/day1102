package com.sist.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;

public class GridLayoutTest03 extends JFrame {
	
	
	public GridLayoutTest03() {
		//5행 3열
		setLayout(new GridLayout(5,4));
		
		//반복문을 이용하여 15개의 버튼을 생성하여 프레임에 담기
		for(int i=1 ; i<=9 ;i++) {
			add(new JButton("버튼"+i));
		}
		
		setSize(400,300);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GridLayoutTest03();
	}

}
