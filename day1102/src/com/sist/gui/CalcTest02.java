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
	
	int num1;  //������ ���� ù ��° ���� ���� ���� 
	int num2;  //2��° ��
	String op;    //
	
	
		public CalcTest02() {
		
		result= new JTextField();
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4,4));
		
		//16���� ��ư�� ǥ�õ� ���ڿ��� �迭�� ���
		String []arr = {"7","8","9","+",
						"4","5","6","-",
						"1","2","3","*",
						"0","C","=","/"};
		
		//��ư �迭�� ����
		JButton []btn = new JButton[arr.length];
		
		//��ư �迭�� ����ŭ �ݺ������ؼ� ��ư�� �����ؼ� �гο� ���
		for(int i=0; i<btn.length;i++) {
			btn[i]= new JButton(arr[i]);
			p.add(btn[i]);
			btn[i].addActionListener(this);
		}
	
		//Frame�� ���ʿ� �ؽ�Ʈ�ʵ带 ��� ��� ��ư�� ��� �г��� ���
		
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
		
		//������ ��ư�� ���ڸ� �о���� �޼ҵ�
		String cmd = e.getActionCommand();
		System.out.println(cmd);
		
		if(cmd.equals("*") || cmd.equals("+") ||  cmd.equals("-") || cmd.equals("/")  ) {
			
			//ȭ�鿡 ���̰� �ִ� ���ڸ� ����(number1)�� ����
			num1 = Integer.parseInt(result.getText());
			
			//� ������ �ؾ����� ����(op)�� ����
			op = cmd;
			
			//ȭ���� ������
			result.setText("");
			
		}else if(cmd.equals("=")) {
			//���� ���̴� ���ڸ� ����2�� ����
			num2 = Integer.parseInt(result.getText());
			
			//�׸��� op�� �������� ������ ���� 1�� 2�� �ش� ������ ������ reulst
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
			//���ڸ� ����
			result.setText("");
		}else {			
			//������ ��ư�� ���ڸ� �ؽ�Ʈ�ʵ忡 ��� �����Ͽ� ���
			result.setText( result.getText() +  cmd);	
		}
			
		
		
	}

}
