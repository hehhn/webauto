package com.xx.common;

public class BaseData {
	
	//**************************���ݿ�����*******************//
	
	/*
	//�������ݿ�driver���������ݿ���SQL server	
	public static final String DatabaseDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	//�������ݿ����ӵ�ַ
	public static final String dbURL="jdbc:sqlserver://192.168.50.166;DatabaseName=selenium";  
	
	//�����������ݿ��û���������
	public static final String userName = "sa";  
	public static final String userPwd = "root";
*/
	
	//�������ݿ�driver���������ݿ���MySQL	
		public static final String DatabaseDriver = "com.mysql.jdbc.Driver";
		
		//�������ݿ����ӵ�ַ
		public static final String dbURL="jdbc:mysql://localhost:3308/elements?useSSL=false";  
		
		//�����������ݿ��û���������
		public static final String userName = "root";  
		public static final String userPwd = "root";
	
		
//*************************��������Ŀ�����ݿ���project��nameֵ*******************//
		public static final String projectname="��Ŀ1";

//*************************�����ŵ�ַ*******************//
		public static final String firefoxstr="C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
	
	
//*************************���ݵ�ַ*******************//

	public static final String copypath = "E:\\Auto_eg\\file\\temp\\cases_";
	
	
	
//*************************���������������ַ����Ե�ַ*******************//
	
	public static final String mainfile_txt="file/sendmail_mainfile.txt";
	public static final String mainfile_sql="file/sendmail_mainfile.sql";
	public static final String mainfile_docx="file/sendmail_mainfile.docx";
	
	//������ַ
	public static final String casefile="file/cases.xls";
}
