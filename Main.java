package com.ncedu.mustakhimov;

import java.io.IOException;
import org.apache.commons.cli.*;
import com.ncedu.mustakhimov.Search;

public class Main {

	public static void main(String[] args) {
		//Программа запускалась на eclipse 
		Search sch = new Search();
		byte bKbd[] = new byte[256];
		int iCnt = 0;
		String nameOfFile = "";
		String path;
		System.out.print("You can enter:\n");
		System.out.print("- name of file or part of name\n");
		System.out.print("- regular expression\n");
		System.out.print("- '*' show all files of directory\n");
        System.out.print("Enter:");
        try {
			iCnt = System.in.read(bKbd);
		} catch (IOException e) {
			e.printStackTrace();
		}
        nameOfFile = new String(bKbd, 0, iCnt);
        nameOfFile = nameOfFile.trim();
        Options options = new Options();
        
        //Аргументы командной строки -s -d
        //Пример -s true -d true
        Option size = new Option("s", "size", true, "file size (true/false)");
        size.setRequired(true);
        options.addOption(size);

        Option date = new Option("d", "date", true, "date changes (true/false)");
        date.setRequired(true);
        options.addOption(date);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
            return;
        }
        path = System.getProperty("user.dir");
        String sizeOp = cmd.getOptionValue("size");
        String dateOp = cmd.getOptionValue("date");

        sch.SearchFile(path, nameOfFile, sizeOp, dateOp);
        
	}

}
