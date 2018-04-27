package com.ourproject.myapp;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ourproject.myapp.dbinterfaces.MariaDBManager;

public class ToDBServer_DO {
	
	HttpServletRequest request;
	
	public ToDBServer_DO(HttpServletRequest request){
		this.request = request;
	}
	
	
	public void process(){

		long starttime = System.currentTimeMillis();
		long timeout = 10000;
		try {
				
			/******************************************************************************************************************
			 * 1. request 스트림으로부터 데이터를 읽어들여서 finalRecvBytes를 완성시킨다
			 * 		1.1 인풋스트림에 읽을 헤더내용이 있을때까지 대기 //1만차있던가 한번읽기전까진0이던가하는 문제가 있어서 일단 주석처리해둠
			 * 		1.2 헤더내용 읽어들여서 finalRecvBytes에 추가한다
			 *		1.3 헤더에서 blank부분 ( blank 부분을 데이터사이즈정보로 사용하기로 하였다) 을 통해 순수 데이터의 사이즈를 구한다.
			 *		1.4 구해진 데이터의 사이즈만큼 추가로 읽는다.
			 *		1.5 finalRecvBytes 검사
			 *
			 * 2. 완성된 finalRecvBytes로 적절한 처리를 한다.
			 * 		2.1 헤버 내용을 구한다.
			 * 		2.2 완성된 finalRecvBytes로 적절한 처리를 하여 queryList를 완성한다. 
			 * 		
			 * 3. 
			 * ****************************************************************************************************************/
			
			
			
			
			
	
	        byte[] finalRecvBytes = new byte[0];
			/******************************************************************************************************
			 * 1. request 스트림으로부터 데이터를 읽어들인다.
			 * ****************************************************************************************************/
			{
				ServletInputStream sis =  request.getInputStream();
				DataInputStream dis = new DataInputStream(sis);
				
				
			    /********************************************************
	             *	1.1 인풋스트림에 읽을 헤더내용이 있을때까지 대기 //1만차있던가 한번읽기전까진0이던가하는 문제가 있어서 일단 주석처리해둠
	             * *******************************************************/
				{
					/*	while(dis.available()<1){
				                if(System.currentTimeMillis() - starttime > timeout){throw new Exception("time out");}//timeout
				                
				                try {
				                    Thread.sleep(10);
				                } catch (InterruptedException e) {
				                    e.printStackTrace();
				                }
				
				            }*/
				}
	            
	            
	            
				
	            /**********************************************
	             *	1.2 헤더내용 읽어들여서 finalRecvBytes에 추가한다
	             ************************************************/
	            {
		            byte[] headerTemp = new byte[1284];
		            int headerLen = dis.read(headerTemp);
		            if(headerLen < 1284) throw new Exception("header len is less than expected!");
		            finalRecvBytes = addByteArr(finalRecvBytes,headerTemp);
	            }
		
	
	            
	            int dataSize =0;
	            /**********************************************
	             *	1.3 헤더에서 blank부분 ( blank 부분을 데이터사이즈정보로 사용하기로 하였다) 을 통해 순수 데이터의 사이즈를 구한다.
	             ************************************************/
	            {
		            byte[] blank = new byte[1024];
		            for( int i=0; i<1024; i++ ) blank[i] = finalRecvBytes[i+4+128+128];
		            String dataSizeStr = new String(blank);
		
		            dataSize = Integer.parseInt(dataSizeStr.trim());
		            System.out.println("[wanted pure data size ] : "+dataSize);
	            }
		
					
	            /**********************************************
	             *	1.4 구해진 데이터의 사이즈만큼 추가로 읽는다.
	             ************************************************/
	            {
		            byte[] getbytes = new byte[1024];
		            int len =0 ;
		            while(dis.available() > 0 || finalRecvBytes.length<(dataSize+1284)){
		                if(System.currentTimeMillis() - starttime > timeout){throw new Exception("time out");}//timeout
		         
		                
		            	len = dis.read(getbytes);  	
		            	if(len < 0 ) continue;  //혹시나
		            	
		                byte[] temp = new byte[len];
				        for(int i=0; i<temp.length; i++) temp[i] = getbytes[i];
				        
		                finalRecvBytes = addByteArr(finalRecvBytes,temp);
		                	
		            }
		            System.out.println("[read total data size(maybe wanted size + header size ] : "+finalRecvBytes.length);
	            }
	
		      
	            /**********************************************
	             *	1.5.finalRecvBytes 검사.
	             *  - 조건1 : total data size 가 헤더 + pure data size보다 작으면 에러
	             ************************************************/
	            {
			        if(finalRecvBytes.length<dataSize+1284 ){
			            throw new IOException("[error in process img message : less than wanted total data size]");
			        }
	            }	
					
	               
			}// 1. request 스트림으로부터 데이터를 읽어들여서 finalRecvBytes를 완성시킨다  end
			
			
			
			

	        String[] queryList; 
			/******************************************************************************************************
			 * 2. 완성된 finalRecvBytes로 적절한 처리를 하여 queryList를 완성한다. 
			 * ****************************************************************************************************/
			{
				
				
			
		        
		        byte[] typeBytes = new byte[4];
		        /**********************************************
		         * 2.1 헤버 내용을 구한다.
		         ************************************************/
		        {
		        	for(int i=0; i<4; i++) typeBytes[i] = finalRecvBytes[i];
		        }
		        
		        
		        

		         /**********************************************
		         * 2.1 구해진 헤버 내용에따라 적절한 처리함수(인자로 finalRecvBytes를 넘긴다) 를 선택해서 처리하고 queryList를 리턴받는다.
		         ************************************************/
		        {
		            if(hasSameContents_byteArr(typeBytes,"imag".getBytes())){
		            	queryList = processForImg_ReturnQueryList(finalRecvBytes);
		            }
		            else{
		               throw new IOException("처리가능한 타입이 아닙니다");
		            }
		        }
				
			}//2. 완성된 finalRecvBytes로 적절한 처리를 하여 queryList를 완성한다. end
		            
		            
			
			
			/******************************************************************************************************
			 * 3. queryList가 있을경우 처리한다.
			 * ****************************************************************************************************/
			{ 
	            /**********************************************
		         * 3.1 queryList가 null이 아닐경우 처리
		         ************************************************/
				{
			      if(queryList != null){
			    	  MariaDBManager maria = new MariaDBManager();
			    	  for(String query : queryList){
			    		  System.out.println("query execute result : "+maria.execute(query));
			    	  }
			      }
				}
			}//3. queryList가 있을경우 처리한다. end
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	            
	}
	
	
    

	
	
	
	
	/*****************************************************************************************************************
     * fianlRecvBytes 처리 함수
     *
     *
    * ***************************************************************************************************************/
	
		/*******************************************************************************
	     * imag타입 finalBytes를 받아서 적절한 쿼리문 반환
	    * ******************************************************************************/
		    public String[] processForImg_ReturnQueryList(byte[] finalRecvBytes) throws IOException {
		
		        /***************************************************************
		         * header 타입이 imag 였을때 처리함수.
		         * 저장할건 저장하고 디비로 보낼건 보낸다
		         * 문제 발생시에 상위로 예외를 throw 한다.
		         *
		         * -----------------------------------
		         * 데이터 구조
		         * 처리 타입 [4]
		         * 날짜 [128]
		         * 데이터개요 [128]
		         * 공백 [1024]
		         * 데이터 [나머지전부]
		         *------------------------------------
		         * 1. finalRecvBbytes 로부터 날짜 읽는다
		         * 2. finalRecvBbytes 로부터 데이터개요 읽는다.
		         * 3. finalRecvBbytes 로부터 공백 읽는다.
		         * 4. finalRecvBbytes 로부터 데이터(나머지 전부) 읽는다.
		         * 5. 쿼리문 리스트 생성
		         * 6. 쿼리문 리스트 리턴
		        * ***************************************************************/
		
		
		
		
		        byte[] type = new byte[4];
		        byte[] date = new byte[128];
		        byte[] dataoverview = new byte[128];
		        byte[] blank = new byte[1024];
		        byte[] data = new byte[finalRecvBytes.length - type.length - date.length - dataoverview.length - blank.length];
		        /***************************************
		         * 1~ 4. finalRecvBbytes 로부터 데이터 읽는다.
		         * *************************************/
		        {
		            for( int i=0; i<4 ; i++) type[i] = finalRecvBytes[i];
		            for( int i=0; i<128; i++ ) date[i] = finalRecvBytes[i+4];
		            for( int i=0; i<128; i++ ) dataoverview[i] = finalRecvBytes[i+4+128];
		            for( int i=0; i<1024; i++ ) blank[i] = finalRecvBytes[i+4+128+128];
		            for( int i=0; i< finalRecvBytes.length - (4+128+128+1024) ; i++) data[i] = finalRecvBytes[i + 4+128+128+1024];
		
		            
		            System.out.println("=========================processForImg==========================");
		            
		            for(int i=0; i<type.length ; i++) System.out.print((char)type[i]);
		            System.out.println();
		            for(int i=0; i<date.length ; i++) System.out.print((char)date[i]);
		            System.out.println();
		            for(int i=0; i<dataoverview.length ; i++) System.out.print((char)dataoverview[i]);
		            System.out.println();
		            for(int i=0; i<blank.length ; i++) System.out.print((char)blank[i]);
		            System.out.println();
		           // for(int i=0; i<data.length ; i++) System.out.print((char)data[i]);
		            
		        }
		
		

		       // String rootpath = new String("C:\\Users\\ppooi\\Desktop\\A");
		        String rootpath = new String("/home/dohyeon/workspace/cst_sohwagiproject/images");
		        String fileFullPath;
		        String lastFileName_forSave;
		        /***************************************
		         * 1.2~4.2 파일로 출력
		         * -----------------------------------
		         * 데이터 구조
		         * 처리 타입 [4]
		         * 날짜 [128]
		         * 데이터개요 [128]
		         * 공백 [1024]
		         * 데이터 [나머지전부]
		         *------------------------------------
		         * *************************************/
		            FileOutputStream fos;
		
		            File file = new File(rootpath+File.separator + new String(dataoverview).trim());
		            
		            System.out.println(new String(dataoverview));
		            System.out.println(file.getAbsolutePath());
		            
		            if(!file.exists()){
		            	file.mkdirs();
		            }
		            
		            lastFileName_forSave = new String(System.currentTimeMillis()+".jpg");
		            fileFullPath = file.getAbsolutePath() + File.separator + lastFileName_forSave;
		            

		            
		            fos = new FileOutputStream(fileFullPath);
		            fos.write(data);
		            fos.close();
		            
		         
		           
		        String[] queryList = new String[]{new String()};
	            /***************************************
		         * 5. 쿼리문 리스트 생성
		         * -----------------------------------
		         * 1. insert to myDB.test filepath, id, type, datetime, lastupdatetime 
		         *------------------------------------
		         * *************************************/
		         {
		           
		            
		            String idtemp = new String(dataoverview).trim();
		            String typetemp = new String(type).trim();
		            String datetime = new String("now()");
		            String lastupdatetime = new String("now()");
		            String fileURL = new String("http://13.112.225.91:8080/DBInterfaceServer/responseImg_byteArr?mac=" + idtemp + "&filename=" + lastFileName_forSave );
		           // http://13.112.225.91:8080/DBInterfaceServer/responseImg_byteArr?mac=B827EB166DA0&filename=1494352445323.jpg

		            
		            System.out.println("path : " + fileFullPath);
		            System.out.println("id : " + idtemp);
		            System.out.println("type : " + typetemp);
		            System.out.println("datetime : " + datetime);
		            System.out.println("lastupdatetime : " + lastupdatetime);
		            
		            String query1 = "insert into myDB.test values("+'\"'+lastFileName_forSave+'\"'+"," + '\"'+idtemp+'\"' +"," +'\"'+typetemp+'\"' +","+datetime+","+lastupdatetime+","+'\"'+fileURL+'\"'+");";
			      //  String query2 = "select * from test";
			      //  String query3 = "insert into myDB.test values(\"/home/pi/12412/fireimg109851\",12412,\"jpg\",now(),now());";

		            
		            queryList = new String[]{query1};
		                

		        }
		         
		         
	            System.out.println("=========================processForImg end==========================");
	            System.out.println();
	            
	            return queryList;
		    }
		    
    
    
    
    /*****************************************************************************************************************
     * 기타 유틸 매서드
     *
     *
    * ***************************************************************************************************************/
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
		
			/*******************************************************************************
		     * 두 byte타입 배열을 합치는 메서드
		    * ******************************************************************************/
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
			
			
			/*******************************************************************************
		     * 두 byte타입 배열이 같은 값을 가지고 있는지 비교하는 매서드
		    * ******************************************************************************/
		        public boolean hasSameContents_byteArr(byte[] b1, byte[] b2){
		            boolean result = true;
		
		            if(b1.length != b2.length) result = false;
		            for(int i=0; i<b1.length; i++) {
		                if (b1[i] != b2[i]) result = false;
		            }
		
		            return result;
		        }
}
