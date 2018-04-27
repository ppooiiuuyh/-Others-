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
			 * 1. request ��Ʈ�����κ��� �����͸� �о�鿩�� finalRecvBytes�� �ϼ���Ų��
			 * 		1.1 ��ǲ��Ʈ���� ���� ��������� ���������� ��� //1�����ִ��� �ѹ��б�������0�̴����ϴ� ������ �־ �ϴ� �ּ�ó���ص�
			 * 		1.2 ������� �о�鿩�� finalRecvBytes�� �߰��Ѵ�
			 *		1.3 ������� blank�κ� ( blank �κ��� �����ͻ����������� ����ϱ�� �Ͽ���) �� ���� ���� �������� ����� ���Ѵ�.
			 *		1.4 ������ �������� �����ŭ �߰��� �д´�.
			 *		1.5 finalRecvBytes �˻�
			 *
			 * 2. �ϼ��� finalRecvBytes�� ������ ó���� �Ѵ�.
			 * 		2.1 ��� ������ ���Ѵ�.
			 * 		2.2 �ϼ��� finalRecvBytes�� ������ ó���� �Ͽ� queryList�� �ϼ��Ѵ�. 
			 * 		
			 * 3. 
			 * ****************************************************************************************************************/
			
			
			
			
			
	
	        byte[] finalRecvBytes = new byte[0];
			/******************************************************************************************************
			 * 1. request ��Ʈ�����κ��� �����͸� �о���δ�.
			 * ****************************************************************************************************/
			{
				ServletInputStream sis =  request.getInputStream();
				DataInputStream dis = new DataInputStream(sis);
				
				
			    /********************************************************
	             *	1.1 ��ǲ��Ʈ���� ���� ��������� ���������� ��� //1�����ִ��� �ѹ��б�������0�̴����ϴ� ������ �־ �ϴ� �ּ�ó���ص�
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
	             *	1.2 ������� �о�鿩�� finalRecvBytes�� �߰��Ѵ�
	             ************************************************/
	            {
		            byte[] headerTemp = new byte[1284];
		            int headerLen = dis.read(headerTemp);
		            if(headerLen < 1284) throw new Exception("header len is less than expected!");
		            finalRecvBytes = addByteArr(finalRecvBytes,headerTemp);
	            }
		
	
	            
	            int dataSize =0;
	            /**********************************************
	             *	1.3 ������� blank�κ� ( blank �κ��� �����ͻ����������� ����ϱ�� �Ͽ���) �� ���� ���� �������� ����� ���Ѵ�.
	             ************************************************/
	            {
		            byte[] blank = new byte[1024];
		            for( int i=0; i<1024; i++ ) blank[i] = finalRecvBytes[i+4+128+128];
		            String dataSizeStr = new String(blank);
		
		            dataSize = Integer.parseInt(dataSizeStr.trim());
		            System.out.println("[wanted pure data size ] : "+dataSize);
	            }
		
					
	            /**********************************************
	             *	1.4 ������ �������� �����ŭ �߰��� �д´�.
	             ************************************************/
	            {
		            byte[] getbytes = new byte[1024];
		            int len =0 ;
		            while(dis.available() > 0 || finalRecvBytes.length<(dataSize+1284)){
		                if(System.currentTimeMillis() - starttime > timeout){throw new Exception("time out");}//timeout
		         
		                
		            	len = dis.read(getbytes);  	
		            	if(len < 0 ) continue;  //Ȥ�ó�
		            	
		                byte[] temp = new byte[len];
				        for(int i=0; i<temp.length; i++) temp[i] = getbytes[i];
				        
		                finalRecvBytes = addByteArr(finalRecvBytes,temp);
		                	
		            }
		            System.out.println("[read total data size(maybe wanted size + header size ] : "+finalRecvBytes.length);
	            }
	
		      
	            /**********************************************
	             *	1.5.finalRecvBytes �˻�.
	             *  - ����1 : total data size �� ��� + pure data size���� ������ ����
	             ************************************************/
	            {
			        if(finalRecvBytes.length<dataSize+1284 ){
			            throw new IOException("[error in process img message : less than wanted total data size]");
			        }
	            }	
					
	               
			}// 1. request ��Ʈ�����κ��� �����͸� �о�鿩�� finalRecvBytes�� �ϼ���Ų��  end
			
			
			
			

	        String[] queryList; 
			/******************************************************************************************************
			 * 2. �ϼ��� finalRecvBytes�� ������ ó���� �Ͽ� queryList�� �ϼ��Ѵ�. 
			 * ****************************************************************************************************/
			{
				
				
			
		        
		        byte[] typeBytes = new byte[4];
		        /**********************************************
		         * 2.1 ��� ������ ���Ѵ�.
		         ************************************************/
		        {
		        	for(int i=0; i<4; i++) typeBytes[i] = finalRecvBytes[i];
		        }
		        
		        
		        

		         /**********************************************
		         * 2.1 ������ ��� ���뿡���� ������ ó���Լ�(���ڷ� finalRecvBytes�� �ѱ��) �� �����ؼ� ó���ϰ� queryList�� ���Ϲ޴´�.
		         ************************************************/
		        {
		            if(hasSameContents_byteArr(typeBytes,"imag".getBytes())){
		            	queryList = processForImg_ReturnQueryList(finalRecvBytes);
		            }
		            else{
		               throw new IOException("ó�������� Ÿ���� �ƴմϴ�");
		            }
		        }
				
			}//2. �ϼ��� finalRecvBytes�� ������ ó���� �Ͽ� queryList�� �ϼ��Ѵ�. end
		            
		            
			
			
			/******************************************************************************************************
			 * 3. queryList�� ������� ó���Ѵ�.
			 * ****************************************************************************************************/
			{ 
	            /**********************************************
		         * 3.1 queryList�� null�� �ƴҰ�� ó��
		         ************************************************/
				{
			      if(queryList != null){
			    	  MariaDBManager maria = new MariaDBManager();
			    	  for(String query : queryList){
			    		  System.out.println("query execute result : "+maria.execute(query));
			    	  }
			      }
				}
			}//3. queryList�� ������� ó���Ѵ�. end
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	            
	}
	
	
    

	
	
	
	
	/*****************************************************************************************************************
     * fianlRecvBytes ó�� �Լ�
     *
     *
    * ***************************************************************************************************************/
	
		/*******************************************************************************
	     * imagŸ�� finalBytes�� �޾Ƽ� ������ ������ ��ȯ
	    * ******************************************************************************/
		    public String[] processForImg_ReturnQueryList(byte[] finalRecvBytes) throws IOException {
		
		        /***************************************************************
		         * header Ÿ���� imag ������ ó���Լ�.
		         * �����Ұ� �����ϰ� ���� ������ ������
		         * ���� �߻��ÿ� ������ ���ܸ� throw �Ѵ�.
		         *
		         * -----------------------------------
		         * ������ ����
		         * ó�� Ÿ�� [4]
		         * ��¥ [128]
		         * �����Ͱ��� [128]
		         * ���� [1024]
		         * ������ [����������]
		         *------------------------------------
		         * 1. finalRecvBbytes �κ��� ��¥ �д´�
		         * 2. finalRecvBbytes �κ��� �����Ͱ��� �д´�.
		         * 3. finalRecvBbytes �κ��� ���� �д´�.
		         * 4. finalRecvBbytes �κ��� ������(������ ����) �д´�.
		         * 5. ������ ����Ʈ ����
		         * 6. ������ ����Ʈ ����
		        * ***************************************************************/
		
		
		
		
		        byte[] type = new byte[4];
		        byte[] date = new byte[128];
		        byte[] dataoverview = new byte[128];
		        byte[] blank = new byte[1024];
		        byte[] data = new byte[finalRecvBytes.length - type.length - date.length - dataoverview.length - blank.length];
		        /***************************************
		         * 1~ 4. finalRecvBbytes �κ��� ������ �д´�.
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
		         * 1.2~4.2 ���Ϸ� ���
		         * -----------------------------------
		         * ������ ����
		         * ó�� Ÿ�� [4]
		         * ��¥ [128]
		         * �����Ͱ��� [128]
		         * ���� [1024]
		         * ������ [����������]
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
		         * 5. ������ ����Ʈ ����
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
     * ��Ÿ ��ƿ �ż���
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
		     * �� byteŸ�� �迭�� ��ġ�� �޼���
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
		     * �� byteŸ�� �迭�� ���� ���� ������ �ִ��� ���ϴ� �ż���
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
