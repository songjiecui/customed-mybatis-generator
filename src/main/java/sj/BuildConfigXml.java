package sj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuildConfigXml {
    private String ip="";
    private String port="";
    private String user="";
    private String passWord="";
    private String dataBase="";
    private String tableName="";
    private String packAge="";
    
    private List<Table> selectTables;
    public List<Table> getSelectTables() {
        return selectTables;
    }

    public void setSelectTables(List<Table> selectTables) {
        this.selectTables = selectTables;
    }
    public BuildConfigXml(){
    	
    }
    public BuildConfigXml(String ip, String port, String user, String passWord, String dataBase, String tableName,String packAge) {
        super();
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.passWord = passWord;
        this.dataBase = dataBase;
        this.tableName = tableName;
        this.packAge = packAge;
    }

    private   Connection getConnection() throws Exception{ 
        Connection conn = null;
            Class<?> forName = Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://"+ip+":"+port+"/"+dataBase;
            conn = DriverManager.getConnection(url,user,passWord);
        return conn;
    }
    
    public List<Table> getDataBaseTable() throws Exception{
        int moduleID=1;
        List<Table> tables=new ArrayList<Table>();
        
        Connection conn=getConnection();
        String sql="SELECT TABLE_NAME,TABLE_COMMENT  from Information_schema.TABLES  where TABLE_SCHEMA='"+dataBase+"'";
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery(sql);
           
            while(rs.next()){
                Table t=new Table(moduleID+"",packAge, rs.getString(1), rs.getString(2),"");
                tables.add(t);
                moduleID=moduleID+1;
            }
            rs.close();
        } 
        catch (SQLException e) {
            System.out.println("数据库连接失败");
            throw new RuntimeException("数据库连接失败");
        } 
        return tables;
    }
    
    /*
    private String getAutoClassNameByTableName(String tableName){
        int index=tableName.indexOf("_");
        if(index<=0) {
            String res=tableName.substring(0,1).toUpperCase()+tableName.substring(1);
            return res;
        }
        String b=tableName.substring(0,index);
        b=b.substring(0,1).toUpperCase()+b.substring(1);
        String e=tableName.substring(index+1);
        e=e.substring(0,1).toUpperCase()+e.substring(1);
        return b+e;
    }
    
    private String getAutoPropertyByColumn(String columnName){
        int index=columnName.indexOf("_");
        if(index<=0) return columnName;
        String b=columnName.substring(0,index);
        String e=columnName.substring(index+1);
        e=e.substring(0,1).toUpperCase()+e.substring(1);
        return b+e;
    }
    */
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getDataBase() {
        return dataBase;
    }

    public void setDataBase(String dataBase) {
        this.dataBase = dataBase;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPackAge() {
        return packAge;
    }

    public void setPackAge(String packAge) {
        this.packAge = packAge;
    }
    
    
     
}
