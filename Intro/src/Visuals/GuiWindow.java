package Visuals;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class GuiWindow extends JPanel 
{
	private static final long serialVersionUID = 2L;
	
	JLabel[] labels;
	JTextField[] fields;
	
	public GuiWindow(String name, int x, int y, int width, int height,String[] entries)
	{
		initUI(name,x,y,width,height,entries);
		
	}
	
	private void initUI(String name, int x, int y, int width, int height,String[] entries)
	{
		setLayout(new GridLayout(0,2));
		labels = new JLabel[entries.length];
		fields = new JTextField[entries.length];
		for(int i=0; i<entries.length;i++)
		{
			labels[i] = new JLabel(entries[i]);
			labels[i].setFont(new Font("Consolas",0,25));
			fields[i] = new JTextField(10);
			fields[i].setFont(new Font("Consolas",0,20));
			add(labels[i]);
			add(fields[i]);
		}
		setSize(100,100);
		setLayout(null);
		add(new JButton("help"));
		//validate();
		System.out.println("getting here part 2");
	}
	/*
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Toolkit.getDefaultToolkit().sync();
    }*/
	
}
