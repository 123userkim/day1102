package com.sist.gui;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcTest02 extends JFrame implements ActionListener {
		
	JTextField result;
	
	int num1;  //연산을 위한 첫 번째 수를 담을 변수 
	int num2;  //2번째 수
	String op;    //
	
	
		public CalcTest02() {
		
		result= new JTextField();
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4,4));
		
		//16개의 버튼에 표시될 문자열을 배열에 담기
		String []arr = {"7","8","9","+",
						"4","5","6","-",
						"1","2","3","*",
						"0","C","=","/"};
		
		//버튼 배열을 선언
		JButton []btn = new JButton[arr.length];
		
		//버튼 배열의 수만큼 반복실행해서 버튼을 생성해서 패널에 담기
		for(int i=0; i<btn.length;i++) {
			btn[i]= new JButton(arr[i]);
			p.add(btn[i]);
			btn[i].addActionListener(this);
		}
	
		//Frame의 위쪽에 텍스트필드를 담고 가운데 버튼이 담긴 패널을 담기
		
		add(result,BorderLayout.NORTH);
		add(p,BorderLayout.CENTER);
		
		setVisible(true);
		setSize(400,300);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 new CalcTest02();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//눌러진 버튼의 글자를 읽어오는 메소드
		String cmd = e.getActionCommand();
		System.out.println(cmd);
		
		if(cmd.equals("*") || cmd.equals("+") ||  cmd.equals("-") || cmd.equals("/")  ) {
			
			//화면에 보이고 있는 숫자를 변수(number1)에 저장
			num1 = Integer.parseInt(result.getText());
			
			//어떤 연산을 해야할지 변수(op)에 저장
			op = cmd;
			
			//화면을 지워줌
			result.setText("");
			
		}else if(cmd.equals("=")) {
			//지금 보이는 숫자를 변수2에 저장
			num2 = Integer.parseInt(result.getText());
			
			//그리고 op에 연산자의 종류에 따라 1과 2를 해당 연산을 수행해 reulst
			if(op.equals("+")) {
				String sum= (num1+num2)+"";
				result.setText(sum);
			}else if(op.equals("-")) {
				String sub = (num1-num2)+"";
				result.setText(sub);
			}else if(op.equals("*")) {
				String multi= (num1*num2)+"";
				result.setText(multi);
				 
			}else if(op.equals("/")) {
				String div= (num1/num2)+"";
				result.setText(div);
			}
			
			
			
		}else if(cmd.equals("C")) {
			//숫자를 지움
			result.setText("");
		}else {			
			//눌러진 버튼의 글자를 텍스트필드에 계속 누적하여 출력
			result.setText( result.getText() +  cmd);	
		}
			
		
		
	}

}
