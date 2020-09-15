package com.xx.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadDB {

	
		 String driverName;    //�������ݿ���������
		 
		 String dbURL;    //�������ݿ����ӵ�ַ
		 
		 String userName;    //�������ݿ������û���
		
		 String userPwd;     //�������ݿ���������
		 
		 Connection dbConn;    //���ڲ������ݿ�
		 
	
		 
		 /*
			 * slj
			 * �������ݿ�
			 * 2017-06-21
			  
			 public void connectDB(){
				 
				 driverName = BaseData.DatabaseDriver;
				 
				// driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";    //�������ݿ�driver���������ݿ���SQL server	
				 
				 //dbURL="jdbc:sqlserver://192.168.50.166;DatabaseName=selenium";    //�������ݿ����ӵ�ַ
				 dbURL = BaseData.dbURL;
				 
				  
				 
				 // userName="sa";  
				 // userPwd="root";	
				userName = BaseData.userName;
				userPwd = BaseData.userPwd;
				 
				 try
				 	{
					 
					 Class.forName(driverName);    //��������
					 
					 dbConn=DriverManager.getConnection(dbURL,userName,userPwd);    //�������ݿ�
					 
					 System.out.println("mysql�������ݿ�ɹ�");

				 	}
				 catch(Exception e)
				 	{
				 		e.printStackTrace();
				 		System.out.print("mysql����ʧ�ܣ��������ݿ�����Ƿ���������");
				 	}   
			 }*/
		 
		 /*
		 * slj
		 * �������ݿ�
		 * 2017-06-21
		 */
		 public void connectDB(){
			 
			 driverName = BaseData.DatabaseDriver;
			 
			// driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";    //�������ݿ�driver���������ݿ���SQL server	
			 
			 //dbURL="jdbc:sqlserver://192.168.50.166;DatabaseName=selenium";    //�������ݿ����ӵ�ַ
			 dbURL = BaseData.dbURL;
			 
			  
			 
			 // userName="sa";  
			 // userPwd="root";	
			userName = BaseData.userName;
			userPwd = BaseData.userPwd;
			 
			 try
			 	{
				 
				 Class.forName(driverName);    //��������
				 
				 dbConn=DriverManager.getConnection(dbURL,userName,userPwd);    //�������ݿ�
				 
				 System.out.println("�������ݿ�ɹ�");

			 	}
			 catch(Exception e)
			 	{
			 		e.printStackTrace();
			 		System.out.print("����ʧ�ܣ��������ݿ�����Ƿ���������");
			 	}   
		 }

		 /*
		  * slj
		  * �ر����ݿ�����
		  * 2017-06-21
		  */
		 
		 public void closeConn()  
		    {  
		        try {  
		        	dbConn.close();  
		        	dbConn = null;  
		        } catch (Exception ex) {  
		            System.out.println(ex.getMessage());  
		            dbConn=null;   
		        }
		    }
		 
		 /*
		  * slj
		  * �����ݿ�����Ӧ��Ŀ�µ�����ҳ��Ԫ�ض�λ��Ϣ��ȡ����ά�����з���
		  * ����ֵ��elements�Ƕ�ά���飬����Ԫ��ʹ��
		  * 2017-06-21
		  */
		 
		 public String[][] readLocators() throws SQLException{
				
		     this.connectDB();
		     String projectname = BaseData.projectname;
			
		     /*���������һ�����������ƶ��α��ResultSet�У��ٴ���st����ִ�����ݿ�*/
		     
			Statement st =dbConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			/*ִ�в�ѯ��䣬�������resset��*/
			//sqlserver�汾
			//ResultSet resset = st.executeQuery("select  cast(l.id as nvarchar)id,l.name,value,[type],[desc],cast(status as nvarchar) status,p.name as project,l.createtime from tb_locator as l  left join  tb_project as p on p.id=l.projectid where l.status=0 order by l.createtime desc");
			String querystr="select  cast(l.id as char)id,lname,lvalue,ltype,ldesc,cast(lstatus as char) lstatus,pname as project,l.createtime from locators as l  left join  projects as p on p.id=l.projectid where lstatus=0 and projectid=(select id from projects where pname=\'"+projectname+"\')order by l.createtime desc";
			ResultSet resset = st.executeQuery(querystr);
			int rowCount = 0;    //�������� 
			while(resset.next()){
			    rowCount++;
			   }
			
			
			int i=0;    //���ڱ��������У����ڸ�ֵ
			/*��ҳ��Ԫ�ش洢��elements�У���������ţ����ƣ�ֵ�����ͣ������ĸ�ֵ*/
			
			String  elements[][]=new String[rowCount][5];
			
	 		resset.beforeFirst();    //��ָ����ڵ�һ������֮ǰ
	 		
	 		/*����resset�����������Ӧ���е�ֵ������Ԫ��������*/
	 		
	 		while(resset.next()) {
	            elements[i][0]=resset.getString("id");
	 			elements[i][1]=resset.getString("lname");
	 			elements[i][2]=resset.getString("lvalue");
	 			elements[i][3]=resset.getString("ltype");
	 			elements[i][4]=resset.getString("ldesc");
	 			elements[i][4]=resset.getString("project");
	 			i++;
	 		}
	 		this.closeConn();
	 		return elements;
	 	}
}
