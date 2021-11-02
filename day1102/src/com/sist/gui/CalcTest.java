package com.sist.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcTest extends JFrame implements ActionListener{
	
	//�� ���� �Է¹ޱ� ���� ����� ����ϱ� ���� txtFile�� ��������� �����ϱ�
	JTextField jtf01;
	JTextField jtf02;
	JTextField jtf03;
	
	public CalcTest() {
		
		//�ؽ�Ʈ �ʵ� ��ü�� ������, �Ű������� ���� �� ���� �����ִ� ������ ��
		jtf01 = new JTextField(10);
		jtf02 = new JTextField(10);
		jtf03 = new JTextField(10);
		
		//�������� ���̾ƿ������ FlowLayout���� ����
		setLayout(new FlowLayout());
		
		//�󺧰�, ��ư�� �����Ͽ� �����ӿ� �ֱ�
		add(new JLabel("����1:")); //���� ȭ�鿡 ���̱⸸ �ϸ� ��->�̸� �ʿ� ��
		add(jtf01);
		
		add(new JLabel("����2:"));
		add(jtf02);
		
		JButton btnAdd= new JButton("���ϱ�");
		add(btnAdd);
		
		add(new JLabel("���:"));
		add(jtf03);
		
		setSize(600,200);
		setVisible(true);
	
		//��ư�� �̺�Ʈ�� �����
		//�Ű������� this�� ��ư�� ������ �� ��ó���ϴ� ��簴ü�� �ٷ� calcTest�ڽ��̶�� ���
		btnAdd.addActionListener(this); 
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new CalcTest();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//textfile jtf01, jtf02�� ���� �о����
		int number1= Integer.parseInt(jtf01.getText()); //�о�� �� String���� �ͼ� ���ڷ� ����
		int number2= Integer.parseInt(jtf02.getText()); //�о�� �� String���� �ͼ� ���ڷ� ����
		
		int result = number1+number2;
		jtf03.setText(result +"");
		 
	}


}
