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
	
	//두 수를 입력받기 위한 결과를 출력하기 위한 txtFile를 멤버변수로 선언하기
	JTextField jtf01;
	JTextField jtf02;
	JTextField jtf03;
	
	public CalcTest() {
		
		//텍스트 필드 객체를 생성함, 매개변수의 값은 한 번에 보여주는 글자의 수
		jtf01 = new JTextField(10);
		jtf02 = new JTextField(10);
		jtf03 = new JTextField(10);
		
		//프레임의 레이아웃방식을 FlowLayout으로 설정
		setLayout(new FlowLayout());
		
		//라벨과, 버튼을 생성하여 프레임에 넣기
		add(new JLabel("숫자1:")); //라벨은 화면에 보이기만 하면 됨->이름 필요 ㄴ
		add(jtf01);
		
		add(new JLabel("숫자2:"));
		add(jtf02);
		
		JButton btnAdd= new JButton("더하기");
		add(btnAdd);
		
		add(new JLabel("결과:"));
		add(jtf03);
		
		setSize(600,200);
		setVisible(true);
	
		//버튼의 이벤트를 등록함
		//매개변수인 this는 버튼이 눌렸을 때 일처리하는 담당객체가 바로 calcTest자신이라는 얘기
		btnAdd.addActionListener(this); 
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new CalcTest();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//textfile jtf01, jtf02의 값을 읽어오기
		int number1= Integer.parseInt(jtf01.getText()); //읽어올 때 String으로 와서 숫자로 변형
		int number2= Integer.parseInt(jtf02.getText()); //읽어올 때 String으로 와서 숫자로 변형
		
		int result = number1+number2;
		jtf03.setText(result +"");
		 
	}


}
