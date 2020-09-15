package com.xx.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


import jxl.Cell;
import jxl.CellType;
import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Excel {

    /*
     * ʵ�֣���ȡָ��·���£�ָ��sheet�е�excel�����ÿһ�У����ŵ������У�����
     * ������filePath���ļ�·��
     * ������sheetname:excel�е�sheet����
     * ����ֵ��cases��������
     * ���ߣ�slj
     * ʱ�䣺2017-06-21
     */

    public  String[][] readExcel(String filePath,String sheetname) throws IOException, JXLException{
		
    	/*��������������ȡ����is ���ڶ�ȡexcel���*/	
        InputStream is=null;    
        
        /*����Workbook����*/
        Workbook workbook=null;   
        
        /*����filepath��������·������ȡ��is��*/
        is=new FileInputStream(filePath);    
        
        /*��is�ж�ȡ����excel���ֵ��workbook*/
        workbook=Workbook.getWorkbook(is);    
        
       /*��������sheet����workbook�е�sheet��Ϊsheetname�ı���ȡ��sheet��*/
        Sheet sheet=workbook.getSheet(sheetname);    
        
        /*����һ�����飬���ڴ��sheet�е�ֵ�����ȸ���sheet�е�ʵ�ʳ��ȶ���*/
        String cases[][] = new String[sheet.getRows()][sheet.getColumns()];     
        
        /*ͨ������ѭ������sheet�е����ݶ�ȡ��������*/
		for(int i=0;i<sheet.getRows();i++){
			for(int j=0;j<sheet.getColumns();j++){
				
				/*����һ����Ԫ�������ͨ��getCell(j,i)�õ���Ԫ���е�ֵ����ȡÿ��ÿ�У�getCell(j,i)ǰ���j���У�i����*/
				Cell cellA1=sheet.getCell(j,i);
				
				/*�����ǰ��Ԫ��ĸ�ʽ��label(�ı�����)���͵ģ��Ͷ�ȡ�������У�ע�⣺excel������������Ҫ������ı����ͣ���������*/
				if (cellA1.getType().equals(CellType.LABEL)){
					
					/*��ȡstring���͵�Ԫ�������*/
					cases[i][j]=cellA1.getContents();
				}
			}
		}
		
		workbook.close();    //��excel��ر�
		is.close();    //���������ر�
		return cases;
   }

    
    /*
     * ʵ�֣��޸�excel�н���е�ֵ
     * ������filepath:���޸�excel�洢·��
     * ������sheetname:����е�sheet����
     * ����ֵ��result��������excel���������һ�µĶ�ά����
     * ���ߣ�slj
     * ʱ�䣺2017-08-09
     */

	public void UpdateCell( String filepath,String sheetname,String[][] result) throws BiffException, IOException, WriteException{
		
		CopyFile copyfile=new CopyFile();
		String dest=getFilename();
	    copyfile.copyFile(filepath, dest);
		//����Workbook���󣬱������б��ֻ��
		Workbook  workbook = null;
		
		//����WritableWorkbook���󣬽����б��ֵһ�ݣ���д����
		WritableWorkbook wworkbook = null;
		
		//��������������ȡ����is �û���ȡexcel���
		InputStream is=new FileInputStream(filepath);
		
		//���������ݱ�����ֻ����workbook��
		workbook=Workbook.getWorkbook(is);
		
		//��workbook�����ݣ�����һ�ݸ���д��wworkbook 
		wworkbook=Workbook.createWorkbook(new File(filepath),workbook);
		
		//��������wsheet����wworkbook�е�sheet��Ϊsheetname�ı���ȡ��wsheet��
		 WritableSheet wsheet =wworkbook.getSheet(sheetname);
		 

		 for(int i=1;i<result.length;i++)
		 {	
			 for(int j=1;j<wsheet.getRows();j++)
			 {
//				 String t1=result[i][result[0].length-3];
//				 String t2=wsheet.getWritableCell(wsheet.getColumns()-3, j).getContents();
			
				 //�ж����鵹�������е�i�У��Ƿ����дsheet�еĵ��������У���j����ȣ��������дԤ�ڽ����ʱ������
				 if(result[i][result[0].length-3].equals(wsheet.getWritableCell(wsheet.getColumns()-3, j).getContents())){
					//��ȡ��д�����е�Ԥ�ڽ��
					 WritableCell cell_expectancy =wsheet.getWritableCell(result[0].length-2, i);
					//��ȡ��д�����е�ʱ��
					 WritableCell cell_time =wsheet.getWritableCell(result[0].length-1, i);
			 
					 //����Ԥ�ڽ����Ԫ�����ʽ
					 CellFormat cf_expectancy = cell_expectancy.getCellFormat();
					//����ʱ�䵥Ԫ�����ʽ
					 CellFormat cf_time = cell_time.getCellFormat();
					 
					 //�޸�Ԥ�ڽ������һ���������У��ڶ����������У��������������޸ĵ�ֵ
					 Label lbl_expectancy = new Label(result[0].length-2, i, result[i][result[0].length-2]);
					//�޸�ʱ�䣬��һ���������У��ڶ����������У��������������޸ĵ�ֵ
					 Label lbl_time = new Label(result[0].length-1, i, result[i][result[0].length-1]);
			
					 //�ָ�Ԥ�ڽ����Ԫ����ʽ
					 lbl_expectancy.setCellFormat(cf_expectancy);
					//�ָ�ʱ�䵥Ԫ����ʽ
					 lbl_time.setCellFormat(cf_time);
			
					 //�����µ�Ԥ�ڽ��ֵ
					 wsheet.addCell(lbl_expectancy);
					//�����µ�ʱ��ֵ
					 wsheet.addCell(lbl_time);
					 break;
				 }
			 }	 
		}
		//���޸ı��浽wworkbook��
		wworkbook.write();
		System.out.println("д��excel�ɹ�");
		//�ͷ�wworkbook
		wworkbook.close();
	     	 
	}
	
	/*
	 * ʵ�֣����ڱ�������excel��ƴ��Ŀ���ļ����ݵ�·��������_������ʱ����
	 * ���أ�·��+ƴ�պõı���
	 * ���ߣ�slj
	 * ʱ�䣺2017-08-11
	 */
	public String getFilename(){
		
		DateString datestring=new DateString();
		
		return BaseData.copypath+datestring.getDateString()+".xls";

	}
}
