package sj;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.LineBorder;

import java.awt.Color;

public class Jmain extends JFrame {
    
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Jmain frame = new Jmain();
                    frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
   
   
    
    /**
     * Create the frame.
     */
    public Jmain() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
         contentPane.setLayout(null);
        setContentPane(contentPane);
        
        JList list = new JList(new String[]{"北京" , "天津" , "上海" , "大连" , "青岛" , "武汉" , "西安"});
        
        JScrollPane jsp = new JScrollPane(list);
        jsp.setBorder(new LineBorder(new Color(0, 0, 0)));
        jsp.setBounds(85, 54, 100, 50);
        contentPane.add(jsp);
        
        
       
         
    }
    
}
