import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Test {
public static void main(String[] args) throws  Exception {
	//这是加密方式
//    String hashed = BCrypt.hashpw("sanwenqian123", BCrypt.gensalt());
//    System.out.println(hashed);
////$2a$10$u/XJC7yLA4558sRhjWMKweJqsdmfugzergbyKT5lQoE5lCD7PvxvK
////$2a$10$g2XYo.Mmb1efYiulAVCybuxsQta9td5po2/HqVJF8JEp00MGZtq22
//    //
//    //这是解密方式
//    if (BCrypt.checkpw("sanwenqian123", "$2a$10$u/XJC7yLA4558sRhjWMKweJqsdmfugzergbyKT5lQoE5lCD7PvxvK"))
//        System.out.println("It matches");
//    else
//        System.out.println("It does not match");
	String filePath = Build();
	File file = new File("E:\\aa.xml");
	writeTxtFile(filePath,file,"utf-8");

}
private  static boolean writeTxtFile(String content,File  fileName,String encoding)throws Exception{  
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
public static String Build(){
    File file=new File("");
    String path=file.getAbsolutePath(); 
    StringBuilder sb=new StringBuilder();
    sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
    sb.append("<!DOCTYPE generatorConfiguration PUBLIC \"-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN\" \"http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd\">\n"); 
    sb.append("  <generatorConfiguration>\n"); 
    sb.append("     <classPathEntry location=\"C:/Users/xs_sj/.m2/repository/mysql/mysql-connector-java/5.1.34/mysql-connector-java-5.1.34-sources.jar\" />\n");
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
    sb.append("        <javaModelGenerator targetPackage=\"com.big.dao.model\" targetProject=\"src\">\n");
    sb.append("         <property name=\"enableSubPackages\" value=\"true\" />\n");
    sb.append("      <property name=\"trimStrings\" value=\"true\" />\n");
    sb.append("    </javaModelGenerator>\n");
    sb.append("  <sqlMapGenerator targetPackage=\"com.big.dao.mapper\" targetProject=\"src\">\n");
    sb.append("<property name=\"enableSubPackages\" value=\"true\" />\n");
    sb.append("  <property name=\"trimStrings\" value=\"true\" />\n");
    sb.append(" </sqlMapGenerator>\n");
    sb.append("  <javaClientGenerator type=\"XMLMAPPER\" targetPackage=\"com.big.dao.mapper\" targetProject=\"src\">\n");
    sb.append("<property name=\"enableSubPackages\" value=\"true\" />\n");
    sb.append("  <property name=\"trimStrings\" value=\"true\" />\n");
    sb.append("</javaClientGenerator>\n");
    
    sb.append("   <table tableName=\"risk_dto_user_huace\" domainObjectName=\"RiskDtoUserHuace\">\n");
    sb.append("<generatedKey column=\"id\" sqlStatement=\"MySql\" identity=\"true\"/>\n");
    sb.append("   </table>\n");
    sb.append("    </context>  \n");
    sb.append("</generatorConfiguration> \n");
    return sb.toString();
    
}
}
