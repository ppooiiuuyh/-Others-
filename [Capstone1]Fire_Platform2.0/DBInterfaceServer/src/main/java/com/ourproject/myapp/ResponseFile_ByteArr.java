package com.ourproject.myapp;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseFile_ByteArr {
	String path;
	HttpServletRequest request;
	HttpServletResponse response;
	
	public ResponseFile_ByteArr(HttpServletRequest request,HttpServletResponse response){
		this.request = request;
		this.response = response;
		path = makePath();
	}
	
	
	
	public void process(){

	     ServletOutputStream  svrOut = null ;
	     BufferedOutputStream outStream = null ; 
	     
	     try {                  
	
		     svrOut = response.getOutputStream();  
	         outStream =  new BufferedOutputStream( svrOut );
	         
	         byte[] imag = readImage(path);
	         outStream.write(  imag, 0, imag.length );      
	         outStream.flush();                       
	
	       } catch( Exception writeException ) { 
	
	         writeException.printStackTrace();
	
	       } finally { 
	
	          try {             
	
	             if ( outStream != null ) outStream.close();  
	
	           } catch( Exception closeException ) { 
	
	            closeException.printStackTrace();
	
	           }    
	
	       }
	}
	
	
	public String makePath(){
		String mac = request.getParameter("mac");
        System.out.println(mac);
        
        String filename = request.getParameter("filename");
        System.out.println(filename);
        
		
  	  //if windows
      //  String rootPath = "C:\\Users\\ppooi\\Desktop\\";
      //  String path = rootPath + mac+ File.separator + filename;
      //  System.out.println(path);

       //if linux
        String rootPath = "/home/dohyeon/workspace/cst_sohwagiproject/images/";
        String path = rootPath + mac+ File.separator + filename;
      
		return path;
	}
	
	
    public byte[] readImage(String path) throws Exception 

     {    

       String filePath = path;
       int BUF_SIZE  ;

       byte[] buf = null ;    

       DataInputStream in =  null ;

       try {     

       

       File imgFile= new File(filePath) ;

       BUF_SIZE = (int)imgFile.length() ;    

       buf = new byte[BUF_SIZE] ;   

       in = new DataInputStream(new FileInputStream(imgFile));       

       in.readFully(buf);        

       } finally { 

         in.close();

       }

       return   buf;

     }
}

