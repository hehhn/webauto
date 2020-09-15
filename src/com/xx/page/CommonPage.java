package com.xx.page;
 
import org.openqa.selenium.WebDriver;

import com.xx.common.ElementAction;
import com.xx.common.LocatorInfo;



public class CommonPage {
	WebDriver driver;
	String[][] Locators;
	ElementAction elementaction;
	
	public CommonPage(WebDriver driver, String[][] Locators) throws Exception {
		this.driver = driver;
		this.Locators = Locators;
		elementaction = new ElementAction(driver,Locators);
	}
	
	//ͨ��_���ѿ�_����
	LocatorInfo common_message_text=new LocatorInfo("common_message_text","");
	
	//ͨ��_���ѿ�_ȷ����ť
	LocatorInfo common_message_ok=new LocatorInfo("common_message_ok","");
	
	//ͨ��_�������ʱ��tip
		LocatorInfo common_tip=new LocatorInfo("common_tip","");
	
	//ͨ���Ҳ���div�ı���
		LocatorInfo common_divname=new LocatorInfo("common_divname","");
		
	//ͨ��_���½��ϻ���ʾ_����
		LocatorInfo common_message1_text=new LocatorInfo("common_message1_text","");
		
		

		
	//�������ѿ������
	public String get_messgeText(){
		return elementaction.getTextValue(common_message_text);
	}
	 
	//����tip���Ѳ������
	public String get_TipText(){
			return elementaction.getTextValue(common_tip);
		}

	
	
	public void click_ok() throws Exception{
		elementaction.click(common_message_ok);
	}
	
	/*
	 * ��ȡ�Ҳ���div�ı��⣬���ռ��䡢�ݸ����
	 * ʷ�ֽ�
	 * 2017-08-16
	 */
	public String get_maindivname() throws Exception{
		return elementaction.getTextValue(common_divname);
	}
	
	
	/*
	 * ��ȡ���·��ϻ���ʾ������
	 * ʷ�ֽ�
	 * 2017-09-07
	 */
	public String get_messge1Text() throws Exception{
		return elementaction.getTextValue(common_message1_text);
	}
}
