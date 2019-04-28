package Visuals;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Application extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2517616123689799182L;

	public Application() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
        
        setSize(1406, 835);
        setResizable(false);
        
        setTitle("");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) 
    {
    	System.out.println("started");
        EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                
                Application ex = new Application();
                ex.setVisible(true);
             }
        });
    }
}