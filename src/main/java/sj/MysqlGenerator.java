package sj;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class MysqlGenerator {
    
    private JFrame frame;
    private JTextField txtIp;
    private JTextField txtPort;
    private JTextField txtUser;
    private JTextField txtPassword;
//    private JTextField txtTableName;
    private JTextField txtDataBase;
    private TextArea textArea;
    private JTextField txtVoPath;
    private JTextField configFilePath;
    private JTextField txtDaoPath;
    private JTextField txtMapperPath;
    private JTextField txtPackAge;
    private JList tableList;
    private JList selectTableList;
    
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MysqlGenerator window = new MysqlGenerator();
                    window.frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the application.
     */
    public MysqlGenerator() {
        initialize();
    }
    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("自动生成代码");
        frame.setBounds(100, 100, 1083, 774);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        txtIp = new JTextField();
        txtIp.setText("47.94.144.144");
        txtIp.setBounds(97, 20, 197, 21);
        frame.getContentPane().add(txtIp);
        txtIp.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("ip:");
        lblNewLabel.setBounds(23, 20, 54, 15);
        frame.getContentPane().add(lblNewLabel);
        
        txtPort = new JTextField();
        txtPort.setText("3306");
        txtPort.setColumns(10);
        txtPort.setBounds(97, 47, 197, 21);
        frame.getContentPane().add(txtPort);
        
        JLabel lblPort = new JLabel("port:");
        lblPort.setBounds(23, 51, 54, 15);
        frame.getContentPane().add(lblPort);
        
        JLabel lblUser = new JLabel("user:");
        lblUser.setBounds(23, 78, 54, 15);
        frame.getContentPane().add(lblUser);
        
        txtUser = new JTextField();
        txtUser.setText("appuser");
        txtUser.setColumns(10);
        txtUser.setBounds(97, 78, 197, 21);
        frame.getContentPane().add(txtUser);
        
        JLabel lblPassword = new JLabel("password:");
        lblPassword.setBounds(23, 106, 54, 15);
        frame.getContentPane().add(lblPassword);
        
        txtPassword = new JTextField();
        txtPassword.setText("appuser007");
        txtPassword.setColumns(10);
        txtPassword.setBounds(97, 106, 197, 21);
        frame.getContentPane().add(txtPassword);
        
//        JLabel lblTablename = new JLabel("tableName:");
//        lblTablename.setBounds(23, 160, 64, 15);
//        frame.getContentPane().add(lblTablename);
        
//        txtTableName = new JTextField();
//        txtTableName.setText("config");
//        txtTableName.setColumns(10);
//        txtTableName.setBounds(97, 160, 197, 21);
//        frame.getContentPane().add(txtTableName);
        
        
        
        JButton btnBuidCode = new JButton("[3]生成代码");
        btnBuidCode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				if (getSelectTables().size() == 0) {
					JOptionPane.showMessageDialog(null, "请选择要生成的表才能生成代码!");
					return;
				}
				if(GenerateCode()){
					JOptionPane.showMessageDialog(null, "代码生成成功!");
					return;
				}
				JOptionPane.showMessageDialog(null, "代码生成失败!");
				return;
            }
        });
        btnBuidCode.setBounds(890, 681, 138, 25);
        frame.getContentPane().add(btnBuidCode);
        
        JLabel lblDatabase = new JLabel("dataBase:");
        lblDatabase.setBounds(23, 131, 64, 15);
        frame.getContentPane().add(lblDatabase);
        
        txtDataBase = new JTextField();
        txtDataBase.setText("ttrisk_center");
        txtDataBase.setColumns(10);
        txtDataBase.setBounds(97, 132, 197, 21);
        frame.getContentPane().add(txtDataBase);
        
         textArea = new TextArea();
         textArea.setFont(new Font("Dialog", Font.PLAIN, 12));
        textArea.setBounds(330, 202, 513, 376);
        frame.getContentPane().add(textArea);
        
        JButton btnSaveXmlConfigToDisk = new JButton("[2]配置文件保存到本地");
        btnSaveXmlConfigToDisk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn_SaveXmlConfigToDisk();
            }
        });
        btnSaveXmlConfigToDisk.setBounds(699, 681, 167, 25);
        frame.getContentPane().add(btnSaveXmlConfigToDisk);
        JLabel configFilelabel = new JLabel("文件路径");
        configFilelabel.setBounds(353, 43, 70, 15);
        frame.getContentPane().add(configFilelabel);
        
        configFilePath = new JTextField();
        configFilePath.setText("D:\\config.xml");
        configFilePath.setColumns(10);
        configFilePath.setBounds(448, 43, 494, 21);
        frame.getContentPane().add(configFilePath);
        
        JLabel lblDao = new JLabel("代码存放目录");
        lblDao.setBounds(353, 70, 70, 15);
        frame.getContentPane().add(lblDao);
        
        txtDaoPath = new JTextField();
        txtDaoPath.setText("D:\\src");
        txtDaoPath.setColumns(10);
        txtDaoPath.setBounds(448, 70, 494, 21);
        frame.getContentPane().add(txtDaoPath);
        
        
//        JLabel lblService = new JLabel("mysql驱动路径");
//        lblService.setBounds(353, 98, 90, 15);
//        frame.getContentPane().add(lblService);
//        
//        txtMapperPath = new JTextField();
//        txtMapperPath.setText("");
//        txtMapperPath.setColumns(10);
//        txtMapperPath.setBounds(448, 98, 494, 21);
//        frame.getContentPane().add(txtMapperPath);
        
        JLabel label = new JLabel("包名");
        label.setBounds(353, 17, 70, 15);
        frame.getContentPane().add(label);
        
        txtPackAge = new JTextField();
        txtPackAge.setText("com.big.tiantian");
        txtPackAge.setColumns(10);
        txtPackAge.setBounds(448, 17, 494, 21);
        frame.getContentPane().add(txtPackAge);

        
        
        tableList = new JList();
        JScrollPane tableScroll = new JScrollPane(tableList); 
        tableScroll.setBorder(new LineBorder(new Color(0, 0, 0)));
        tableScroll.setBounds(5, 223, 120, 455);
        frame.getContentPane().add(tableScroll);
        
        JButton btnTestConnect = new JButton("[1]测试数据库连接");
        btnTestConnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    bindSelectTableList();
                }
                catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog( null,"数据库连接失败!");
	                return;
                }
            }
        });
        btnTestConnect.setBounds(160, 188, 149, 25);
        frame.getContentPane().add(btnTestConnect);
        
        selectTableList = new JList();
        JScrollPane selectTableScroll = new JScrollPane(selectTableList); 
        selectTableScroll.setBorder(new LineBorder(new Color(0, 0, 0)));
        selectTableScroll.setBounds(200, 223, 120, 455);
        frame.getContentPane().add(selectTableScroll);
        
        Button btnRight = new Button("-->");
        btnRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List selectedValuesList = tableList.getSelectedValuesList();
                if(selectedValuesList==null||selectedValuesList.size()==0) return;
                System.out.println(selectedValuesList);
                tableNames.removeAll(selectedValuesList);
                tableList.setListData(tableNames.toArray()); 
                tableSelectNames.addAll(selectedValuesList);
                selectTableList.setListData(tableSelectNames.toArray()); 
            }
        });
        btnRight.setBounds(134, 268, 60, 23);
        frame.getContentPane().add(btnRight);
        
        Button btnLeft = new Button("<--");
        btnLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List selectedValuesList = selectTableList.getSelectedValuesList();
                if(selectedValuesList==null || selectedValuesList.size()==0) return;
                tableSelectNames.removeAll(selectedValuesList);
                selectTableList.setListData(tableSelectNames.toArray()); 
                tableNames.addAll(selectedValuesList);
                tableList.setListData(tableNames.toArray()); 
            }
        });
        btnLeft.setBounds(134, 312, 60, 23);
        frame.getContentPane().add(btnLeft);
    }
    
    //================================================================================
    private String getPackageDatabase(){
        String packageDatabse=txtPackageDataBase.getText();
        if(!"".endsWith(packageDatabse)) packageDatabse=packageDatabse+".";
        return packageDatabse;
    }
    private String getPackageDatabasePath(){
        String packageDatabse=txtPackageDataBase.getText();
        if(!"".endsWith(packageDatabse)) packageDatabse=packageDatabse+"\\";
        return packageDatabse;
    }
    
    private void btn_SaveXmlConfigToDisk(){
        String ip=txtIp.getText();
        String port=txtPort.getText();
        String user=txtUser.getText();
        String password=txtPassword.getText();
        String dataBase=txtDataBase.getText();
//        String tableName=txtTableName.getText();
//        String voPath=txtVoPath.getText();
        String daoPath=txtDaoPath.getText();
//        String mapperPath=txtMapperPath.getText();
        String content=textArea.getText();
        List<Table> selectTables = getSelectTables();
        if(selectTables.size()==0){
        	  JOptionPane.showMessageDialog( null,"请选择要生成的表才能把文件保存到本地!");
              return;
        }
        String aa = buildDtoXml(getSelectTables());
        try {
			 boolean res=writeTxtFile(aa,new File(configFilePath.getText()));
	            if(!res){
	                JOptionPane.showMessageDialog( null,"生成配置文件失败!");
	                return;
	            }
	            JOptionPane.showMessageDialog( null,"生成配置文件成功!");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null,e.getMessage());
		}
    }
    

    
    private  boolean writeTxtFile(String content,File  fileName,String encoding)throws Exception{  
        RandomAccessFile mm=null;  
        boolean flag=false;  
        FileOutputStream o=null;  
        try {  
         o = new FileOutputStream(fileName);  
            o.write(content.getBytes(encoding));
            o.close();  
         flag=true;  
        } catch (Exception e) {  
         e.printStackTrace();  
        }finally{  
         if(mm!=null){  
          mm.close();  
         }  
        }  
        return flag;  
    }
    
    private boolean writeTxtFile(String content,File  fileName)throws Exception{ 
        return writeTxtFile(content,fileName,"utf-8");
    }
    
    private  ArrayList<String> tableNames=new ArrayList<String>();
    private  ArrayList<String> tableSelectNames=new ArrayList<String>();
    private java.util.List<Table> dataBaseTables;
    private JTextField txtPackageDataBase;
    private JTextField txtShardingKey;
    
    private void bindSelectTableList() throws Exception{
        String ip=txtIp.getText();
        String port=txtPort.getText();
        String user=txtUser.getText();
        String password=txtPassword.getText();
        String dataBase=txtDataBase.getText();
        String packAge=txtPackAge.getText();
        BuildConfigXml dll=new BuildConfigXml(ip,port,user,password,dataBase,"",packAge);
        dataBaseTables=dll.getDataBaseTable();
        tableNames.clear();
        for(Table t:dataBaseTables){
            tableNames.add(t.getName());
        }
        tableList.setListData(tableNames.toArray());
    }
    public String buildDtoXml(List<Table> tables){
    	
    	    StringBuilder sb=new StringBuilder();
    	    sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
    	    sb.append("<!DOCTYPE generatorConfiguration PUBLIC \"-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN\" \"http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd\">\n"); 
    	    sb.append("  <generatorConfiguration>\n"); 
    	    sb.append("     <classPathEntry location=\""+getRealPath()+"\" />\n");
    	    sb.append("  <context id=\"MySqlTables\" targetRuntime=\"MyBatis3\">\n"); 
    	    sb.append("  <property name=\"javaFileEncoding\" value=\"UTF-8\"/>\n");
    	    sb.append("  <property name=\"suppressTypeWarnings\" value=\"true\" />\n");
    	    sb.append("  <plugin type=\"org.mybatis.generator.plugins.SerializablePlugin\" />\n");
    	    sb.append("  <plugin type=\"org.mybatis.generator.OverIsMergeablePlugin\" />\n");
    	    sb.append("  <commentGenerator type=\"org.mybatis.generator.MyCommentGenerator\"></commentGenerator>\n");
    	    sb.append("      <jdbcConnection driverClass=\"com.mysql.jdbc.Driver\" connectionURL=\"jdbc:mysql://47.94.144.144:3306/ttrisk_center?characterEncoding=UTF-8&amp;tinyInt1isBit=false\" userId=\"appuser\" password=\"appuser007\"/>\n");
    	    sb.append("      <javaTypeResolver type=\"org.mybatis.generator.MyJavaTypeResolver\">\n");
    	    sb.append("      <property name=\"forceBigDecimals\" value=\"true\" />\n");
    	    sb.append("      </javaTypeResolver>\n");
    	    sb.append("        <javaModelGenerator targetPackage=\""+txtPackAge.getText()+".model\" targetProject=\""+txtDaoPath.getText()+"\">\n");
    	    sb.append("         <property name=\"enableSubPackages\" value=\"true\" />\n");
    	    sb.append("      <property name=\"trimStrings\" value=\"true\" />\n");
    	    sb.append("    </javaModelGenerator>\n");
    	    sb.append("  <sqlMapGenerator targetPackage=\""+txtPackAge.getText()+".mapper\" targetProject=\""+txtDaoPath.getText()+"\">\n");
    	    sb.append("<property name=\"enableSubPackages\" value=\"true\" />\n");
    	    sb.append("  <property name=\"trimStrings\" value=\"true\" />\n");
    	    sb.append(" </sqlMapGenerator>\n");
    	    sb.append("  <javaClientGenerator type=\"XMLMAPPER\" targetPackage=\""+txtPackAge.getText()+".mapper\" targetProject=\""+txtDaoPath.getText()+"\">\n");
    	    sb.append("<property name=\"enableSubPackages\" value=\"true\" />\n");
    	    sb.append("  <property name=\"trimStrings\" value=\"true\" />\n");
    	    sb.append("</javaClientGenerator>\n");
    	    for(int i=0;i<tables.size();i++){
    	    	sb.append("   <table tableName=\""+tables.get(i).getName()+"\" domainObjectName=\""+Common.camel(new StringBuffer(tables.get(i).getName()),true)+"\">\n");
        	    sb.append("<generatedKey column=\"id\" sqlStatement=\"MySql\" identity=\"true\"/>\n");
        	    sb.append("   </table>\n");
    	    }
    	    sb.append("    </context>  \n");
    	    sb.append("</generatorConfiguration> \n");
    	    System.out.println(sb.toString());
    	    return sb.toString();
    }
    private java.util.List<Table> getSelectTables(){
        java.util.List<Table> res=new ArrayList<Table>();
        for(String s:tableSelectNames){
            for(Table t:dataBaseTables){
                if(s.equals(t.getName())){
                    res.add(t);
                }
            }
        }
        return res;
    }


	private boolean GenerateCode() {
		boolean flag=true;
		try {
			String path = txtDaoPath.getText();
			File fileTxtDaoPath = new File(path);
			if(!fileTxtDaoPath.exists()){
				fileTxtDaoPath.mkdir();
			}
			List<String> warnings = new ArrayList<String>();
			boolean overwrite = true;
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream is = null;
			File file = new File(configFilePath.getText());
			is = new FileInputStream(file); // 通过对象多态性，进行实例化
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration config = cp.parseConfiguration(is);
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);
		} catch (Exception e) {
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}
	
	  public static String getRealPath() {
		String filePath = System.getProperty("java.class.path");
  	    String pathSplit = System.getProperty("path.separator");
  	    if(filePath.contains(pathSplit)){
  	        filePath = filePath.substring(0,filePath.indexOf(pathSplit));
  	    }else if (filePath.endsWith(".jar")) {//截取路径中的jar包名,可执行jar包运行的结果里包含".jar"
  	        filePath = filePath.substring(0, filePath.lastIndexOf(File.separator) + 1);
  	    }
  	   
  	    if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) {
  	    	 System.out.println("WINDOWS jar包所在目录："+filePath+"\\mysqlGenerator.jar");
  	    	return filePath+"\\mysqlGenerator.jar";
		} 
  	  System.out.println("LINUX jar包所在目录："+filePath+"\\mysqlGenerator.jar");
			return filePath+File.separator+"mysqlGenerator.jar";
	    }
  
  
}
