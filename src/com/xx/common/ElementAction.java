package com.xx.common;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ElementAction {
	
	WebDriver driver;
	String[][] Locators;
	FindWebElement findwebelement;
	public ElementAction(WebDriver driver,String[][] Locators) throws Exception{
		this.driver= driver;
		this.Locators = Locators;
		findwebelement = new FindWebElement(driver,Locators);
	}
	 /*****************�޲��裬�����������************************************************************************* 
    /*
     * ʵ�֣���Ԫ�ظ�ֵ��һ��ָ������ϴ���ť��
     * ������locatorInfo����λ��Ϣ
     * 		value����ֵ����
     * ���ߣ�slj
     * ʱ�䣺2017-06-21
     */
    
	public void sendValue(LocatorInfo locatorInfo, String value) throws Exception {
		WebElement e = findwebelement.findElement(driver, locatorInfo);
		e.sendKeys(value);
	}
    
    /*
     * ʵ�֣����Ԫ��
     * ������locatorInfo����λ��Ϣ
     * ���ߣ�slj
     * ʱ�䣺2017-06-21
     */
    
	public void click(LocatorInfo locatorInfo) throws Exception {
 		WebElement e = findwebelement.findElement(driver, locatorInfo);
 		e.click();
 	}
    
    /*
     * ʵ�֣���ȡԪ��text��ֵ
     * ������locatorInfo����λ��Ϣ
     * ����ֵ��String����Ԫ��text��ֵ����
     * ���ߣ�slj
     * ʱ�䣺2017-06-21
     */
    
	public String getTextValue(LocatorInfo locatorInfo){
    	WebElement e=findwebelement.findElement(driver,locatorInfo);
    	return e.getText();
    } 
    
    /*
     * ʵ�֣����Ԫ��ֵ��һ��ָ�����
     * ������locatorInfo����λ��Ϣ
     * ���ߣ�slj
     * ʱ�䣺2017-06-21
     */
    
	public void cleanText(LocatorInfo locatorInfo){
    	WebElement e=findwebelement.findElement(driver,locatorInfo);
    	e.clear();
    }
   
	/********************�������Ԫ�ز�������*******************
	 * 
	 * @param locator
	 * @param type  
	 * @param value
	 * @throws Exception
	 */
	public void excstep(LocatorInfo locator,String type,String value) throws Exception{
		switch(type){
		case "input":
    		sendValue(locator,value);
    		break;
		case "click":
    		click(locator);
    		break;
		case "gettext":
    		getTextValue(locator);
    		break;
		}
		
	}
	

	
	public void setps(String[][] cases,LocatorInfo[] locMap) throws Exception{
		StrToLoc strtoloc=new StrToLoc();
		LocatorInfo[] loc_real  = new LocatorInfo[cases.length];
		for(int i=0;i<cases.length ;i++){
			loc_real=strtoloc.strtoloc(locMap, cases);
		}
		
		for(int i=0;i<cases.length ;i++){
			excstep(loc_real[i],cases[i][1],cases[i][3]);
		}
	}
	
	
	
    
    /*
     * ʵ�֣� ���մ�������ԣ�����Ԫ�ص�����ֵ������value��maxlength���ͷ���locatorInfo����󳤶�
     * ������locatorInfo��Ԫ�ض�λ��Ϣ
     * 		value��Ԫ������
     * ����ֵ��String�����Ծ���ֵ
     * ���ߣ�slj
     * ʱ�䣺2017-06-21
     */
	public String getElementValue(LocatorInfo locatorInfo,String value){
    	WebElement e = findwebelement.findElement(driver,locatorInfo);
    	return  e.getAttribute(value);
    }
    
//    /*
//     * ����div�ı���ɫ
//     * locatorInfo��Ԫ�ض�λ��Ϣ
//     * ����ֵ��String�����ƣ�rgba(250, 116, 116, 0.1)
//     * slj
//     * 2017-06-21
//     */
//    public String getDivBgc(Locator locatorInfo){
//    	WebElement e=findElement(driver,locatorInfo);
//		return e.getCssValue("background-color");	
//    }
    
    /*
     * ʵ�֣����ش����css���Ե�ֵ
     * ������locatorInfo��Ԫ�ض�λ��Ϣ
     * ����ֵ��String�����ƣ�rgba(250, 116, 116, 0.1)����ʽ��ֵ
     * ���ߣ�slj
     * ʱ�䣺2017-06-21
     */
	protected String getCssValue(LocatorInfo locatorInfo,String str){
    	WebElement e = findwebelement.findElement(driver,locatorInfo);
		return e.getCssValue(str);	
    }
    
    
    /*
     * ʵ�֣��ж�Ԫ���Ƿ�ɼ�
     * ������locatorInfo��Ԫ�ض�λ��Ϣ
     * ����ֵ���������ͣ�true
     * ���ߣ�slj
     * ʱ�䣺2017-06-21
     */
	public boolean isExist(LocatorInfo locatorInfo){
    	WebElement e = findwebelement.findElement(driver,locatorInfo);
    	return e.isDisplayed();
    }
    
    
    /*
     * ʵ�֣��ж�Ԫ���Ƿ������html��
     * ������locatorInfo��Ԫ�ض�λ��Ϣ
     * ����ֵ���������ͣ�true���ڣ�false������
     * ���ߣ�slj
     * ʱ�䣺2017-06-21
     */
	public boolean isExistInHtml(LocatorInfo locatorInfo) throws IOException{
 	   locatorInfo = findwebelement.getLocator(locatorInfo.getLocatorInfo());
 	   WebElement e;
     	switch(locatorInfo.getByType()){
     		case "xpath":
     			try{	

     				e=driver.findElement(By.xpath(locatorInfo.getLocatorInfo()));
     				return true; 
     			}
     			catch(org.openqa.selenium.NoSuchElementException ex)  
     	        {  
     	             return false;  
     	        }
     		case "id":
     			try{

     				e=driver.findElement(By.id(locatorInfo.getLocatorInfo()));
     				return true;  

     			}
     			catch(org.openqa.selenium.NoSuchElementException ex)  
     	        {  
     	             return false;  
     	        }	
     		
     		case "name":
     			try{
//     				locatorInfo=getLocator(locatorInfo.getElement());
     				e=driver.findElement(By.name(locatorInfo.getLocatorInfo()));
     				return true;  

     			}
     			catch(org.openqa.selenium.NoSuchElementException ex)  
     	        {  
     	             return false;  
     	        }	
     			
     		case "cssSelector":
     			try{
//     				locatorInfo=getLocator(locatorInfo.getElement());
     				e=driver.findElement(By.cssSelector(locatorInfo.getLocatorInfo()));
     				return true;  

     			}
     			catch(org.openqa.selenium.NoSuchElementException ex)  
     	        {  
     	             return false;  
     	        }
     			
     		case "className":
     			try{
//     				locatorInfo=getLocator(locatorInfo.getElement());
     				e=driver.findElement(By.className(locatorInfo.getLocatorInfo()));
     				return true;  

     			}
     			catch(org.openqa.selenium.NoSuchElementException ex)  
     	        {  
     	             return false;  
     	        }
     			
     		case "tagName":
     			try{
//     				locatorInfo=getLocator(locatorInfo.getElement());
     				e=driver.findElement(By.tagName(locatorInfo.getLocatorInfo()));
     				return true;  

     			}
     			catch(org.openqa.selenium.NoSuchElementException ex)  
     	        {  
     	             return false;  
     	        }
     			
     		case "linkText":
     			try{
//     				locatorInfo=getLocator(locatorInfo.getElement());
     				e=driver.findElement(By.linkText(locatorInfo.getLocatorInfo()));
     				return true;  

     			}
     			catch(org.openqa.selenium.NoSuchElementException ex)  
     	        {  
     	             return false;  
     	        }
     			
     		case "partialLinkText":
     			try{
//     				locatorInfo=getLocator(locatorInfo.getElement());
     				e=driver.findElement(By.partialLinkText(locatorInfo.getLocatorInfo()));
     				return true;  

     			}
     			catch(org.openqa.selenium.NoSuchElementException ex)  
     	        {  
     	             return false;  
     	        }
     			
     		default:
     			try{
//     				locatorInfo=getLocator(locatorInfo.getElement());
     				e=driver.findElement(By.xpath(locatorInfo.getLocatorInfo()));
     				return true;  

     			}
     			catch(org.openqa.selenium.NoSuchElementException ex)  
     	        {  
     	             return false;  
     	        }
     	}  	 
           
     }
    
    
    /*
     * ʵ�֣������ͣ
     * ������locatorInfo��Ԫ�ض�λ��Ϣ
     * ���ߣ�slj
     * ʱ�䣺2017-06-21
     */
	protected void mouseOver(LocatorInfo locatorInfo) throws IOException {
 		WebElement e = findwebelement.findElement(driver, locatorInfo);
 		Actions actions = new Actions(driver);
 		actions.moveToElement(e).perform();
 	}
     
     
    /*
     * ʵ�֣�ҳ�������������locatorInfoԪ�س���
     * ������locatorInfo��Ԫ�ض�λ��Ϣ
     * ���ߣ�slj
     * ʱ�䣺2017-06-21
     */
	protected  void scrollTo(WebDriver driver, LocatorInfo locatorInfo) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement e = findwebelement.findElement(driver,locatorInfo);
        js.executeScript("var q=document.documentElement.scrollTop=0", e);
    }
    

    /*
     * ʵ�֣��໬���еĴ�ֱ��������������locatorInfoԪ�س���
     * ������locatorInfo��Ԫ�ض�λ��Ϣ
     * ���ߣ�slj
     * ʱ�䣺2017-06-21
     */
	protected  void divScrollTo(WebDriver driver, LocatorInfo locatorInfo) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement e = findwebelement.findElement(driver,locatorInfo);
        js.executeScript("arguments[0].scrollIntoView(true);", e);
    }
    
    
    /*
	public WebDriver getDriver() {
		return driver;
	}
    
	public void waitForPageLoad() {
		 getDriver().manage().timeouts()
				.pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	*/

}
