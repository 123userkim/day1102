package com.sist.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.FlowLayout;

public class FlowLayoutTest extends JFrame{
	
	public FlowLayoutTest() {
		
		//�����ӿ� flowLayout�� ������
		setLayout(new FlowLayout());
				
		//�ݺ����� �̿��ؼ� ��ư�� 15���� �����ؼ� �����ӿ� ���
		for(int i =1 ; i<=15; i++) {
			add(new JButton("��ư"+i));
			
		}
		
		//�������� ũ�⸦ �����ϰ� ȭ�鿡 ���̵��� ������
		setSize(400,300);
		setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FlowLayoutTest();

	}

}