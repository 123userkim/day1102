package com.sist.gui;

import javax.swing.JFrame;

public class JButtonTest extends JFrame {
	
	public JButtonTest() {
		//â�� ���̰� �Ϸ��� â�� ũ�� ���� �ʼ�
		
	}

	public static void main(String[] args) {
		
		//�츮�� ���� JBouttenTest�� �� JFream��
		JButtonTest  f =  new JButtonTest();
		
		//â�� ��������� â�� ȭ�鿡 ������ ����
		//���̰� �Ϸ��� â�� ���μ��� ���� ����, ȭ�鿡 ���̵��� ���� �ʼ�
		
		f.setSize(400,300);
		f.setVisible(true);
		
		//â�� ���� ���� ���α׷��� ���� ������
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
