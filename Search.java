package com.ncedu.mustakhimov;

import java.io.File;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
������������ ��� ������ ���������� � ����������
@author ���������� ����
@version 1.0
*/

public class Search {
	
	/**
	��������� ������������ �� ������ �������� ������
	@param testString - �������� ������
	@param pattern - ���������� ��������� ��� ����� ������
	@return ���������� true, ���� ������� ���� �� ���� ������������,
	����� false 
	*/
	public boolean testRegExp(String testString, String pattern){  
        Pattern p = Pattern.compile(pattern);  
        Matcher m = p.matcher(testString);  
        int count = 0;
        //�������� ����� ��� ������� ���� �� ���� ������������ � �������� 
        while(m.find()) {
            count++;
        }
        if(count != 0){
        	return true;
        	}
        else return false;
	}
	
    /**
	���������� ����� ����� 
	*/
	private String getFileSizeBytes(File file) {
        return file.length() + " bytes";
    }
	
    /**
	������������ ��� ������ ����� ��� �������� � ���������� 
	�� ����� ��� ����������� ���������
	@param path ���� � �������� �����
	@param input ��� ����� ��� ������
	@param size ��������� ��� ����������� ������� ������
	@param date ��������� ��� ����������� ���� ��������� �����
	*/
	void SearchFile(String path, String input, String size, String date){
		File file = new File(path);
		String[] ls = file.list();
		String fileName;
		for(String f : ls){
			if(input.equals("*") || testRegExp(f, input) == true ){
				File temp = new File(path,f);
				// ������� �� ����� ������ 13 �������� �� �������� �����
				if(f.length() < 16){
				    fileName = f;
				}
				else fileName = f.substring(0, 13).concat("...");
				System.out.format("%16s | %-49s "
						,fileName, path );
				
				if(size.compareTo("true")==0){
					System.out.format("%8s ",  getFileSizeBytes(temp));
				}
				if(date.compareTo("true")==0){
					System.out.format("%32s",  new Date(temp.lastModified()));
				}
				if(temp.isDirectory()==true){
					System.out.print("  | catalog");
					System.out.println("  |");
					SearchFile(path+"\\"+f, input, size, date);
				}
				else System.out.println();
			}
		}
	}
}
