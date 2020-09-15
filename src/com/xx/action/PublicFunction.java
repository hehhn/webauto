package com.xx.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import com.xx.common.BaseData;
import com.xx.common.DateString;
import com.xx.common.Excel;
import com.xx.common.ReadDB;

import jxl.JXLException;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

public class PublicFunction {

	String mainfile_txt=BaseData.mainfile_txt;
	String mainfile_sql=BaseData.mainfile_sql;
	String mainfile_docx=BaseData.mainfile_docx;
	String casefile=BaseData.casefile;
	Excel excel=new Excel();
	File casefilepath;
	String [][] casemap;
	WebDriver driver;
	
	
	/*
	 * ʵ�֣���ȡdocx�ļ��ľ���·��
	 * ����ֵ��String�;���·��
	 * ���ߣ�slj
	 * ʱ�䣺2017-11-22
	 */
	public String getDocxFilePath() throws IOException, JXLException{
		
		File file=new File(mainfile_docx);	
		return file.getAbsolutePath();
	}
	
	
	
	/*
	 * ʵ�֣���ȡsql�ļ��ľ���·��
	 * ����ֵ��String�;���·��
	 * ���ߣ�slj
	 * ʱ�䣺2017-11-21
	 */
	public String getSqlFilePath() throws IOException, JXLException{
		
		File file=new File(mainfile_sql);	
		return file.getAbsolutePath();
	}
	
	
	/*
	 * ʵ�֣���ȡtxt�ļ��ľ���·��
	 * ����ֵ��String�;���·��
	 * ���ߣ�slj
	 * ʱ�䣺2017-11-21
	 */
	public String getTxtFilePath() throws IOException, JXLException{
		
		File file=new File(mainfile_txt);	
		return file.getAbsolutePath();
	}
	
	/*
	 * ʵ�֣�ʱ���
	 * ����ֵ��Sting�͵�ʱ�䴮
	 * ���ߣ�slj
	 * ʱ�䣺2017-11-21
	 */
	public String getDate() throws IOException, JXLException{
		
		DateString datestring=new DateString();
		return datestring.getDateString();
	}
	
	
	

	/*
	 * ʵ�֣���ȡexcel��������������ȡ��������
	 * ����ֵ��String����������
	 * ���ߣ�slj
	 * ʱ�䣺2017-11-21
	 */
	public String[][] getCaseMap(String sheetname) throws IOException, JXLException{
		
		 
		 casefilepath=new File(casefile);
		return casemap=excel.readExcel(casefilepath.getAbsolutePath(),sheetname);
	}
	
	/*
	 * ʵ�֣���ȡ���ݿ��е�Ԫ�أ���������
	 * ����ֵ��String��Ԫ������
	 * ���ߣ�slj
	 * ʱ�䣺2017-11-21
	 */
	public String[][] getLocatorMap() throws SQLException{
		
		ReadDB readLocator=new ReadDB();
		return readLocator.readLocators();
	}	
	
	/*
	 * ʵ�֣������������ƣ����㲽�����
	 * ������casesʵ���������ϣ�casename������
	 * ����ֵ���������
	 * 
	 */
	public int getstepcount(String[][] cases,String casename){
		int count=0;
		while(count<cases.length-1&cases[count][cases[0].length-1].equals(casename)){
			count++;
			}
		return count+1;
	}
	
	
	/*
	 * ʵ�֣������������ƣ���ʵ�ʲ��Թ����е��������Ϸ���
	 * ������casesʵ���������ϣ�casename������
	 * ����ֵ��ʵ��ʹ����������
	 * 
	 */
	public String[][] getstepcases(String[][] cases,String casename){
		int row=0,i=0;
		//ʵ�ʲ���
		int stepcount=this.getstepcount(cases,casename);
		String  cases_real[][]=new String[stepcount][cases[0].length];
		 for(int j=0;j<cases.length;j++,row++){
			 for(int col=0;col<cases[0].length;col++){
				 if(cases[i][cases[0].length-1].equals(casename)&row<stepcount)
					 cases_real[row][col]=cases[j][col];
			 }
		  }
		 return cases_real;
		
	}
	 
	
	/*
	 * ʵ�֣��򿪸߰汾�����
	 * ���ߣ�slj
	 * ʱ�䣺2019-8-12
	 */
	public  WebDriver openBrowser(String  url) throws SQLException, BiffException, WriteException, IOException{
		System.setProperty("webdriver.gecko.driver","file/geckodriver.exe");
		System.setProperty("webdriver.firefox.bin", BaseData.firefoxstr);
		ProfilesIni pi=new ProfilesIni();
		FirefoxProfile profile = pi.getProfile("Selenium");
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(profile);
		driver = new FirefoxDriver(options); 
		driver.get(url);
		return driver;
		 
	}	
	
	/*
	 * ʵ�֣��ر�������������������Դ�������д��excel
	 * ���ߣ�slj
	 * ʱ�䣺2017-11-21
	 */
	public void end(WebDriver driver,String casesheetname) throws SQLException, BiffException, WriteException, IOException{
		
		driver.close();
		driver.quit();
		excel.UpdateCell(casefilepath.getAbsolutePath(), casesheetname, casemap);
		 
	}	
	

	
	
	
}
