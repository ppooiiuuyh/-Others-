package com.ourproject.myapp;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ourproject.myapp.dbinterfaces.MariaDBManager;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public String home(Locale locale, Model model) {
			logger.info("Welcome home! The client locale is {}.", locale);
			
			Date date = new Date();
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
			
			String formattedDate = dateFormat.format(date);
			
			model.addAttribute("serverTime", formattedDate );
			
			return "home";
		}
		
	
	
	/************************************************************************************
	 * Gate로 부터 데이터를받아 데이터의 타입에따라 적절하게 처리
	 * *********************************************************************************/
		@RequestMapping(value = "/todbserver.do", method = RequestMethod.POST)
		public void todbserver(HttpServletRequest request){
	          ToDBServer_DO toDBServer_do = new ToDBServer_DO(request);
	          toDBServer_do.process();	
		}

	/************************************************************************************
	 *서버의 이미지에대한 url로서 역할
	 * *********************************************************************************/
		@RequestMapping(value = "/responseImg_byteArr", method = RequestMethod.GET)
		public void responseFile_byteArr(HttpServletRequest request,HttpServletResponse response){
	       ResponseFile_ByteArr responseFile_byteArr = new ResponseFile_ByteArr(request,response);
	       responseFile_byteArr.process();
		}
	/************************************************************************************
	 *Browser로 부터 요청을받아 적절한 데이터를 응답
	 * *********************************************************************************/
		@RequestMapping(value = "/picture", method = RequestMethod.GET)
		public String img(HttpServletRequest request,Model model){
			model.addAttribute("mac", request.getParameter("mac"));
			model.addAttribute("filename", request.getParameter("filename"));
			return "picture";
		}

		
	    
}	
	
	
	
	
	
	
	
	
	
	

	/*
	@RequestMapping(value = "/test.do", method = RequestMethod.POST)
	public void test(HttpServletRequest reader){
		byte[] temp = new byte[1024];
		byte[] finalbytes = new byte[0];
		int len;
		try {
			while(reader.getInputStream().available()<=0){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			while(reader.getInputStream().available()>0){
			     len = reader.getInputStream().read(temp);
                 byte[] gets = new byte[len];
                 for(int i=0; i<gets.length; i++) gets[i] = temp[i];
				finalbytes = addByteArr(finalbytes, gets);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//sout(Integer.toString(finalbytes.length));		
		soutforwindow(Integer.toString(finalbytes.length));
	}


    public void soutforwindow(String str){
		   FileWriter fw;
		try {
			fw = new FileWriter("C:\\Users\\ppooi\\Desktop\\A\\"+System.currentTimeMillis()+"len"+str+".txt");
	
	       fw.write(str);
	    		fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
	public void sout(String str){
		   FileWriter fw;
		try {
			fw = new FileWriter(str+".txt");
	
	       fw.write(str);
	    		fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
*/

