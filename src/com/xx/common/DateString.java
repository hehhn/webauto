package com.xx.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

public class DateString {


	DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
	Date date=new Date();
	
	
	/*
     * ʵ�֣�����ǰ������ʱ����ƴ�ӳ��ַ���
     * ����ֵ��ƴ�Ӻõ�ʱ��YYYYMMDDHHMMSS
     * ���ߣ�slj
     * ʱ�䣺2017-09-05
     */
	public String  getDateString(){
		//return year+""+mouth+""+date+""+hour+""+minute+""+second;
		return df.format(date);

		 
	}
}
