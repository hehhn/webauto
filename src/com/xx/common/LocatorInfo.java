package com.xx.common;

public class LocatorInfo {
	
	private String locatorInfo;	//��λ�����ֻ�ʵ��value
//		private int waitSec;
		//by���� id��xpath�ȵ�
	private String byType;    //ͨ�����ַ�ʽ����
		
    public LocatorInfo(String name,String byType){
    	this.locatorInfo=name;
    	this.byType=byType;
    }
	
 
    /*
     * ʵ�֣���ȡ���嶨λ��Ϣ����//div[0]/��
     * ����ֵ��String���ͣ���//div[0]/
     * ���ߣ�slj
     * ʱ�䣺2017-06-21
     */
    public String getLocatorInfo(){
		return locatorInfo;
	}
    
    /*
     * ʵ�֣���ȡ���嶨λ��������xpath��id��
     * ����ֵ��String���ͣ���xpath��id��
     * ���ߣ�slj
     * ʱ�䣺2017-06-21
     */
    public String getByType(){
    	return byType;
    }

}
