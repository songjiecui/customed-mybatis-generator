package sj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Common {
    /** 
    * 首字母大写 
    *  
    * @param srcStr 
    * @return 
    */  
    private static String firstCharacterToUpper(String srcStr) {  
       return srcStr.substring(0, 1).toUpperCase() + srcStr.substring(1);  
    }  
    /** 
    * 替换字符串并让它的下一个字母为大写 
    * @param srcStr 
    * @param org 
    * @param ob 
    * @return 
    */  
    public static String replaceUnderlineAndfirstToUpper(String srcStr,String org,String ob)  
    {  
       String newString = "";  
       int first=0;  
       while(srcStr.indexOf(org)!=-1)  
       {  
        first=srcStr.indexOf(org);  
        if(first!=srcStr.length())  
        {  
         newString=newString+srcStr.substring(0,first)+ob;  
         srcStr=srcStr.substring(first+org.length(),srcStr.length());  
         srcStr=firstCharacterToUpper(srcStr);  
        }  
       }  
       newString=newString+srcStr;  
       return newString;  
    }  
    
    public static String replaceUnderlineAndAllfirstToUpper(String srcStr,String org,String ob){
        String res=replaceUnderlineAndfirstToUpper(srcStr,org,ob);
        res=firstCharacterToUpper(res);
        return res;
    }
    
    private static String underline2Camel(String line, boolean ... firstIsUpperCase) {
		String str = "";

		if(StringUtils.isBlank(line)){
			return str;
		} else {
			StringBuilder sb = new StringBuilder();
			String [] strArr;
			// 不包含下划线，且第二个参数是空的
			if(!line.contains("_") && firstIsUpperCase.length == 0){
				sb.append(line.substring(0, 1).toLowerCase()).append(line.substring(1));
				str = sb.toString();
			} else if (!line.contains("_") && firstIsUpperCase.length != 0){
				if (!firstIsUpperCase[0]) {
					sb.append(line.substring(0, 1).toLowerCase()).append(line.substring(1));
					str = sb.toString();
				} else {
					sb.append(line.substring(0, 1).toUpperCase()).append(line.substring(1));
					str = sb.toString();
				}
			} else if (line.contains("_") && firstIsUpperCase.length == 0) {
				strArr = line.split("_");
				for (String s : strArr) {
					sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
				}
				str = sb.toString();
				str = str.substring(0, 1).toLowerCase() + str.substring(1);
			} else if (line.contains("_") && firstIsUpperCase.length != 0) {
				strArr = line.split("_");
				for (String s : strArr) {
					sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
				}
				if (!firstIsUpperCase[0]) {
					str = sb.toString();
					str = str.substring(0, 1).toLowerCase() + str.substring(1);
				} else {
					str = sb.toString();
				}
			}
		}
		return str;
	}
    public static StringBuffer camel(StringBuffer str,boolean ... firstIsUpperCase ) {
		//利用正则删除下划线，把下划线后一位改成大写
		Pattern pattern = Pattern.compile("_(\\w)");
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer(str);
		if(matcher.find()) {
			sb = new StringBuffer();
			//将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里。
			//正则之前的字符和被替换的字符
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
			//把之后的也添加到StringBuffer对象里
			matcher.appendTail(sb);			
		}else {
			if(firstIsUpperCase.length!=0){
				return new StringBuffer().append(sb.substring(0, 1).toUpperCase()).append(sb.substring(1));
			}else{
				return sb;
			}
			
		}	
		return camel(sb,firstIsUpperCase);
	}
    
    public static void main(String[] args){
        String res=replaceUnderlineAndAllfirstToUpper("aa_bb_cc_dd","_","");
        System.out.println(res);
    }
    
    
}
