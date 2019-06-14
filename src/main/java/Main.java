import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mysql.jdbc.Connection;

public class Main extends JFrame {
	    private JFrame frame;
	    private JTextArea textarea = new JTextArea(5, 5);
	    private JScrollPane scrollPane;
	    private JPanel controlPanelcenter;
	    private JPanel controlPanelleft;
	    private JPanel controlPanelright;
	    private JTextArea arealeft;
	    private JLabel namelab = new JLabel("用户名");
	    private JLabel passlab = new JLabel("密码");
	    private JLabel portlab = new JLabel("端口");
	    private JLabel iplab = new JLabel("ip");
	    private JLabel databaselab = new JLabel("数据库名称");
		private final JTextField userNameText = new JTextField(10);
		private final JTextField userPasswordText = new JTextField(10);
		private final JTextField ipText = new JTextField(10);
		private final JTextField databaseText = new JTextField(10);
		private final JTextField portText = new JTextField(10);
	    private Connection conn;
	    private final JList<String> list = new JList<String>();
	    private Vector<String> dataModel = new Vector<String>();
       

	    public static void main(String[] args) throws ClassNotFoundException, SQLException {
	        Main frame = new Main("生成代码");
	        frame.showTextFieldDemo();
	        
	    }

	    //构造函数，负责创建用户界面
	    public Main(String title) {
	        super(title);
	         frame = new JFrame(title);
//	        frame.setBounds(300, 100, 400, 400);    //设置窗口大小和位置
	        frame.setSize(1204,768) ;
	        frame.addWindowListener(new WindowAdapter() {
		         public void windowClosing(WindowEvent windowEvent){
		            System.exit(0);
		         }        
		      });    
	        // 设置一下首选大小
	        list.setPreferredSize(new Dimension(200, 100));

	        // 允许可间断的多选
	        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	        controlPanelcenter = new JPanel(new GridLayout(6, 2));
	        controlPanelcenter.setSize(200,300);
	        namelab.setHorizontalAlignment(SwingConstants.RIGHT);
	        controlPanelcenter.add(namelab);
	        controlPanelcenter.add(userNameText);
	        passlab.setHorizontalAlignment(SwingConstants.RIGHT);
	        controlPanelcenter.add(passlab);
	        controlPanelcenter.add(userPasswordText);
	        iplab.setHorizontalAlignment(SwingConstants.RIGHT);
	        controlPanelcenter.add(iplab);
	        controlPanelcenter.add(ipText);
	        databaselab.setHorizontalAlignment(SwingConstants.RIGHT);
	        controlPanelcenter.add(databaselab);
	        controlPanelcenter.add(databaseText);
	        portlab.setHorizontalAlignment(SwingConstants.RIGHT);
	        controlPanelcenter.add(portlab);
	        controlPanelcenter.add(portText);
	        controlPanelleft=new JPanel(new CardLayout());
	        controlPanelleft.setBackground(Color.blue);
	        controlPanelleft.add(controlPanelcenter);
	        frame.add(controlPanelcenter);
	        frame.add(controlPanelleft);
	        frame.getContentPane().add(controlPanelcenter, BorderLayout.NORTH);
	        frame.getContentPane().add(controlPanelleft, BorderLayout.WEST);
	        setLayout(new FlowLayout());//流式布局
	        frame.setVisible(true) ;
	        
	    }
		   private void showTextFieldDemo(){
			      JButton loginButton = new JButton("连接数据库");
			      loginButton.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent e) {
						try {
							connectToDB(userNameText.getText(),userPasswordText.getText(),portText.getText(),ipText.getText(),databaseText.getText());
							controlPanelleftData();
							dataList();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			         }
			      });
			      controlPanelcenter.add(loginButton);
		   }
	    //程序启动时，需调用该方法连接到数据库
	    //之所以不放在构造函数中，是因为这些操作可能抛出异常，需要单独处理
	    public boolean connectToDB(String userName,String password,String port,String ip,String database) throws SQLException, ClassNotFoundException {
	    	boolean flag=false;
	        Class.forName("com.mysql.jdbc.Driver");
	        final String URL = "jdbc:mysql://"+ip+":"+port+"/"+database+"?characterEncoding=UTF-8";
	        conn = (Connection) DriverManager.getConnection(URL, userName, password);
	        if(null!=conn)flag=true;
	        return flag;
	    }
	    
	    private Vector<String> controlPanelleftData(){
	    	StringBuilder sb = new StringBuilder();
            sb.append("SELECT table_name FROM information_schema. TABLES WHERE table_comment <> '' AND table_schema = (SELECT DATABASE()) ORDER BY create_time DESC");
            try {
            	Statement  stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sb.toString());
				while (rs.next()) {
					                String name = rs.getString(1);
					                dataModel.add(name);
                    
                    }
				return dataModel;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return null;
	    }
	    private void dataList(){
	    	   list.setListData(dataModel);
		        list.addListSelectionListener(new ListSelectionListener() {
		            @Override
		            public void valueChanged(ListSelectionEvent e) {
		                // 获取所有被选中的选项索引
		                int[] indices = list.getSelectedIndices();
		                // 获取选项数据的 ListModel
		                ListModel<String> listModel = list.getModel();
		                // 输出选中的选项
		                for (int index : indices) {
		                    System.out.println("选中: " + index + " = " + listModel.getElementAt(index));
		                }
		            }
		        });
		        controlPanelleft.add(list);
		         scrollPane = new JScrollPane(list);
		         controlPanelleft.add(scrollPane);
		        frame.add(controlPanelcenter);
		        frame.add(controlPanelleft);
		        frame.getContentPane().add(controlPanelcenter, BorderLayout.NORTH);
		        frame.getContentPane().add(controlPanelleft, BorderLayout.WEST);
		        setLayout(new FlowLayout());//流式布局
		        frame.setVisible(true) ;
	    }

}
