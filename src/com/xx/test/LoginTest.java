package com.xx.test;
 
//��ʱ���ã�����
import java.io.IOException;
import java.sql.SQLException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.net.Urls;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.xx.action.PublicFunction;
import com.xx.action.UrlInfo;
import com.xx.page.LoginPage;

public class LoginTest {
	
	

	String[][] caseMap;
	String[][] locatorMap;
	WebDriver driver;
	String url;
	String caseFilePath;
	String caseSheetName;
	String mainfile_txt;
	String mainfile_sql;
	String mainfile_docx;
	String datestr;
	PublicFunction publicfunction;
	UrlInfo urls;
		
	/*
	 * ʵ�֣���ʼ������
	 * 		1. ��excel���ļ���ȡ������caseMap�д���
	 * 		2. �����ݿ���Ԫ�ض�ȡ������locatorMap�д���
	 * 		3. ��ȡ����sql��txt�ļ��ľ���·������Ϊ�ϴ��Ĳ���
	 * 		4. �������������¼
	 * ���ߣ�slj
	 * ʱ�䣺2017-11-21
	 */
		@BeforeClass
		public void init() throws Exception{
		  publicfunction=new PublicFunction();
		  
		    urls=new UrlInfo();
			url=urls.get_url();
			
			//ͨ���ؼ��֡���¼������ȡexcle�С���¼��sheetҳ�е�����������������
			caseSheetName="��¼";
			caseMap=publicfunction.getCaseMap(caseSheetName);
			
			//��ȡԪ������
			locatorMap=publicfunction.getLocatorMap();
			
			//��ȡsql�ļ��ľ���·��			
			mainfile_sql=publicfunction.getSqlFilePath();
			
			//��ȡtxt�ļ��ľ���·��			
			mainfile_txt=publicfunction.getTxtFilePath();
			
			//��ȡdocx�ļ��ľ���·��	
			mainfile_docx=publicfunction.getDocxFilePath();
			
			//����ʱ���
			datestr=publicfunction.getDate();
			
			driver = publicfunction.openBrowser(url);			

		}
		
		@AfterClass
		public void closeFirefox() throws BiffException, WriteException, IOException{
			try {
				publicfunction.end(driver, caseSheetName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}

	
	/*
	 * ��ţ�AUTO-1
	 * ���裺����������¼
	 * ���ߣ�slj
	 * ʱ�䣺2017-12-07
	 */
	@Test(priority = 1,enabled = true)
	public void login() throws Exception{
		Thread.sleep(10000);
		int flag=0;
		for(int i=0;i<caseMap.length;i++,flag=i){
			
			if("������¼".equals(caseMap[i][caseMap[0].length-3])){
				if("true".equals(caseMap[i][caseMap[0].length-5])){
					LoginPage loginPage=new LoginPage(driver,locatorMap); 
					loginPage.setLoginName(caseMap[i][1]);
					loginPage.setLoginPwd(caseMap[i][2]);
					loginPage.clickCommitBtn();
					Thread.sleep(1000);
					 
					if(caseMap[i][caseMap[0].length-4].equals("")){
						caseMap[i][caseMap[0].length-2]="ͨ��"; 
						caseMap[i][caseMap[0].length-1]=datestr;
						
					}
					else{
						//Assert.assertEquals("δͨ����ʵ��ֵ��"+loginPage.getErrorMsg()+"Ԥ��ֵ��",caseMap[i][caseMap[0].length-4]);
						caseMap[i][caseMap[0].length-2]="δͨ��"; 
						caseMap[i][caseMap[0].length-1]=datestr;
					}
					break;
				}
				else{
					caseMap[i][caseMap[0].length-2]="���ڲ��Է�Χ"; 
					return;
				}
			}
				
		}
		if(flag==caseMap.length){
			System.out.print("û���ҵ�������¼����");
				}
			
	}
	
	

}
