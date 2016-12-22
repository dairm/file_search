package com.ncedu.mustakhimov;

import java.io.File;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
Используется для поиска документов в директории
@author Мустахимов Даир
@version 1.0
*/

public class Search {
	
	/**
	Проверяет сооветствует ли шаблон тестовой строке
	@param testString - тестовая строка
	@param pattern - регулярное выражение или часть строки
	@return возвращает true, если найдено хотя бы одно соответствие,
	иначе false 
	*/
	public boolean testRegExp(String testString, String pattern){  
        Pattern p = Pattern.compile(pattern);  
        Matcher m = p.matcher(testString);  
        int count = 0;
        //выбираем файлы где находим хотя бы одно соответствие с шаблоном 
        while(m.find()) {
            count++;
        }
        if(count != 0){
        	return true;
        	}
        else return false;
	}
	
    /**
	Возвращает длину файла 
	*/
	private String getFileSizeBytes(File file) {
        return file.length() + " bytes";
    }
	
    /**
	Используется для поиска файла или каталога в директории 
	по имени или регулярному выражению
	@param path путь к родителю файла
	@param input имя файла или шаблон
	@param size настройка для отображения размера файлов
	@param date настройка для отображения даты изменения файла
	*/
	void SearchFile(String path, String input, String size, String date){
		File file = new File(path);
		String[] ls = file.list();
		String fileName;
		for(String f : ls){
			if(input.equals("*") || testRegExp(f, input) == true ){
				File temp = new File(path,f);
				// выводим на экран только 13 символов от названия файла
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
