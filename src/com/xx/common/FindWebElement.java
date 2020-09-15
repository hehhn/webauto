package com.xx.common;

import java.io.IOException;
 








import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;











 


public class FindWebElement {

	protected WebDriver driver;
    protected String[][] locatorMap;    //���ڱ����ȡ������Ԫ��
    ReadDB dbLocators=new ReadDB();
    
  /*
   * Ϊ������driver��locatorMap��ֵ
   */
	public  FindWebElement(WebDriver driver,String[][] Locators) throws Exception{
    	
		this.driver=driver;
    	this.locatorMap=Locators;
    }
	
	/*
	 * ʵ�֣����մ��ݹ��������ƣ�ȥԪ�������в�����Ӧ�������������еĵ�3��value�͵�����type��ʼ��һ����λʵ������ʱ��һ����������������value����by�����ֵ
	 * ������locatorName����λ������
	 * ���ߣ�slj
	 * ʱ�䣺2016-06-21
	 */
	protected LocatorInfo getLocator(String locatorName){
		LocatorInfo locatorinfo=null;
		for(int i=0;i<locatorMap.length;i++){
			//if(locatorMap[i][1].endsWith(locatorName)){
			if(locatorMap[i][1].equals(locatorName)){
				locatorinfo=new LocatorInfo(locatorMap[i][2],locatorMap[i][3]);
				break;
			}				
		}
		return locatorinfo;
	}
	
	/*
	 * ʵ�֣����ݴ������Ķ�λ��Ϣ�� bytype��ֵ����ҳ���ϲ��ң�������
	 * ������driver�������
	 * 	   locatorInfo����λ��Ϣ��������λ��ʶ��bytype��
	 * ����ֵ��return e��WebElement ���ͣ����ݶ�λ��Ϣ��ȷ�����ҷ�ʽ����ֵ��e����
	 * ���ߣ�slj
	 * ʱ�䣺2017-06-21
	 */
	protected WebElement getElement(WebDriver driver,LocatorInfo locatorInfo){
		WebElement e;
		/*���ն�λ��Ϣ�еĶ�λ��ʶ���ҵ������value���ٸ�ֵ��locatorInfo����ʱ��locatorInfo����ʵ�ʵ�value��bytype*/
		locatorInfo=getLocator(locatorInfo.getLocatorInfo());
		
		switch(locatorInfo.getByType()){
		case "xpath":
    		e=driver.findElement(By.xpath(locatorInfo.getLocatorInfo()));
    		break;
    	
		case "id":
    		e=driver.findElement(By.id(locatorInfo.getLocatorInfo()));
    		break;
    		
		case "name":
    		e=driver.findElement(By.name(locatorInfo.getLocatorInfo()));
    		break;
    		
		case "cssSelector":
    		e=driver.findElement(By.cssSelector(locatorInfo.getLocatorInfo()));
    		break;	
    		
		case "className":
    		e=driver.findElement(By.className(locatorInfo.getLocatorInfo()));
    		break;	
    		
		case "tagName":
    		e=driver.findElement(By.tagName(locatorInfo.getLocatorInfo()));
    		break;	
    		
		case "linkText":
    		e=driver.findElement(By.linkText(locatorInfo.getLocatorInfo()));
    		break;	
    		
		case "partialLinkText":
    		e=driver.findElement(By.partialLinkText(locatorInfo.getLocatorInfo()));
    		break;	
    	
		default:
            e = driver.findElement(By.id(locatorInfo.getLocatorInfo()));
		}
		return e;
	}
	
	/*
	 * ʵ�֣����մ���Ķ�λ��Ϣ��locatorInfo����ȥ������в���Ԫ�أ�10��֮���ҵ�����ֵ��element���أ�û�оͷ��ؿ�
	 * ������driver�������
	 * 		locatorInfo����λ��Ϣ
	 * ����ֵ��element������������ҵ���Ԫ�أ���û�ҵ��Ŀ�Ԫ��
	 * ���ߣ� slj
	 * ʱ�䣺2017-06-21
	 */
	
	protected WebElement findElement(WebDriver driver, final LocatorInfo locatorInfo) {
//        WebElement element = (new WebDriverWait(driver, 10))
//                .until(new ExpectedCondition<WebElement>() {
//
//                    public WebElement apply(WebDriver driver) {
//                        return getElement(driver, locatorInfo);
//                    }
//		              });
//        return element=null;
    	try{
    		return getElement(driver,locatorInfo);	
    	}
    	 catch(Exception e){
    		 e.printStackTrace();
    		 System.out.println("û�ҵ�Ԫ��");
    		 return  null;
    	 }
    	
    }
	
 
}
