package com.test.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadServerLogServlet extends HttpServlet {

	private static final long serialVersionUID = 7503953988166684851L; 

	  public ReadServerLogServlet() { 
	    super(); 
	  } 

	  
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	      throws ServletException, IOException { 
	   
		  
	    StringBuilder logContent = new StringBuilder(); 
	    logContent.append("<pre>"); 
	    BufferedReader br = null;
		FileReader fr = null;
		String sCurrentLine="";
		int i=0;
		try{
	    
	    	fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			
			
			while ((sCurrentLine = br.readLine()) != null) {
				i++;
				logContent.append(String.valueOf(i)+"-").append(sCurrentLine).append("<br>");
				//System.out.println(sCurrentLine+"<br>");
				
			}
		//	System.out.println(i);
	    } catch (IOException x) { 
	       System.out.println("exception is...."+x.getMessage());
	    } 
	    
	    finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	    logContent.append("</pre>").append(",data="+String.valueOf(i));
	    //logContent.append("</pre>"); 
	  //  resp.setContentType("application/json");
	    resp.getWriter().print(logContent.toString()); 
	    
	  } 

	
	  public void init(ServletConfig servletConfig) throws ServletException { 
	    super.init(servletConfig); 
	  } 
	private static final String FILENAME = "D:\\TestDataRavi\\apache-tomcat-8.5.20-windows-x64\\apache-tomcat-8.5.20\\logs\\catalina.2017-09-06.log";
	public static void main(String[] args) {
		File ff = new File(FILENAME);
    System.out.println(ff.canRead());
    System.out.println(ff.canExecute());
    System.out.println(ff.canWrite());
	
	
    BufferedReader br = null;
	FileReader fr = null;

	try {

		//br = new BufferedReader(new FileReader(FILENAME));
		fr = new FileReader(FILENAME);
		br = new BufferedReader(fr);
		StringBuilder logContent = new StringBuilder(); 
		logContent.append("<pre>");
		String sCurrentLine="";
		int i=0;
		//sCurrentLine=sCurrentLine.concat("<pre>");
		while ((sCurrentLine = br.readLine()) != null) {
			i++;
			String ss= String.valueOf(i);
			logContent.append(sCurrentLine).append("<br>").append(ss).toString();
			
		}
		
		logContent.append("<pre>");
		System.out.println(logContent.toString());
		System.out.println(i);
	} catch (IOException e) {

		e.printStackTrace();

	} finally {

		try {

			if (br != null)
				br.close();

			if (fr != null)
				fr.close();

		} catch (IOException ex) {

			ex.printStackTrace();

		}

	}
	
	
	
	
	}
	
	
	
}
