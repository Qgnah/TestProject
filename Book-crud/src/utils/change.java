package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class change {
	static SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
	//ʱ��ת��
	public static Date chagetime(String t){
		Date d=null;
		try {
			 d=sf.parse(t);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return d;
		
	}
	
	/*
	 * Date ת  String
	 */
	public static String changetime2(Date d){
		return sf.format(d);
	}

}
