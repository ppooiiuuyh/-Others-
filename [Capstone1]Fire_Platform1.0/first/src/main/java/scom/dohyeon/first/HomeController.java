package scom.dohyeon.first;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String  home() {
		sout("testget");

		return "home";
	}
	
	@RequestMapping(value = "/test.do", method = RequestMethod.POST)
	public void test(HttpServletRequest reader){
		byte[] temp = new byte[1024];
		byte[] finalbytes = new byte[0];
		int len;
		try {
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
		
		
		
		sout(Integer.toString(finalbytes.length));		

	}

	
    byte[] addByteArr(byte[] barr1, byte[] barr2){
        byte[] result = new byte[barr1.length + barr2.length];

        for(int i=0; i<barr1.length; i++){
            result[i] = barr1[i];
        }
        for(int i=barr1.length; i<barr1.length+ barr2.length ; i++){
            result[i] = barr2[i-barr1.length];
        }

        return result;
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
	
	public void sout(String str,int num){
		   FileWriter fw;
		try {
			fw = new FileWriter("C:\\Users\\ppooi\\Desktop\\A\\"+str+".txt");
	
	       fw.write(str + num);
	    		fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
