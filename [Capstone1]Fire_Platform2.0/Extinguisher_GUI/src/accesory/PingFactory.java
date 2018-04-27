package accesory;
import java.net.InetAddress;
import java.util.ArrayList;

public class PingFactory {

	protected PingFactory(){}
	public static PingFactory newInstance(){return new PingFactory();}

	
	public static long getRTT(String ipAdrr){
		try {
			  String ipAddress = ipAdrr;
			  InetAddress inet = InetAddress.getByName(ipAddress);

			  StopWatch stopWatch = StopWatch.newInstance();
			  long timeDif =0;
			
			  stopWatch.resetTime();
		      if (inet.isReachable(5000)){
		        timeDif = stopWatch.checkTime_nano();
		        //System.out.println("Ping RTT: " + (timeDif+ "ms"));
		      } else {
		      	timeDif = -1;
		        System.out.println(ipAddress + " NOT reachable.");
		      }
		      	      
		      
		      return timeDif;
			      

			} catch ( Exception e ) {
			  System.out.println("Exception:" + e.getMessage());
			  return -1;
			}
		
	}
	
	public ArrayList<Long> calRTT(String hostIP,int sampleNumber) {
	
		ArrayList<Long> sampleTemp = new ArrayList<Long>();
		long rttTemp=0;
		//long minTemp=0;
		long sumTemp=0;
		//long maxTemp=0;
		long getRttTimeLapse=0;
		
		
		
		/*///////////////////////////////

		 *////////////////////////////////

		getRTT(hostIP);
		sumTemp += getRTT(hostIP);
		sumTemp += getRTT(hostIP);
		sumTemp += getRTT(hostIP);
		sumTemp /= 3;
		
		sampleTemp.add(sumTemp);
		

		getRttTimeLapse =  sumTemp*2/1000000;
		if(getRttTimeLapse>200){getRttTimeLapse = 200;}
		
		////////////////////////////////////////
		
		
		
		
		
		/*////////////////////////////////////////////////

		 *////////////////////////////////////////////////

		if(sampleNumber<=0){System.out.println("");}
		else{
			for(int i=0; i<sampleNumber-1; i++){
				rttTemp = getRTT(hostIP);
				if(rttTemp>0){	
					//if(rttTemp<minTemp){minTemp = rttTemp;}
					//if(rttTemp>maxTemp){maxTemp = rttTemp;}
					sumTemp += rttTemp;
					sampleTemp.add(rttTemp);
				}
				
		      try {Thread.sleep(getRttTimeLapse);} catch (InterruptedException e) { }
			}
		}
		
		
	
		/////////////////////////////////////////////////////
		
		
		
		
		
		/*/////////////////////////////////////////////////////

		 *////////////////////////////////////////////////////// 
		//long[0] = sample number
		//long[1] = min
		//long[2] = avg
		//long[3] = max
		//long[] calTemp = new long[5];
		//calTemp[0] = sampleTemp.size();
		//calTemp[1] = minTemp;
		//calTemp[2] = sumTemp/sampleTemp.size();
		//calTemp[3] = maxTemp;
		//calTemp[4] =  (long) MyMath.standardDeviation(sampleTemp);
		return sampleTemp;
		
		///////////////////////////////////////////////////////////
	}
	
	public void calRTTTest(String hostIP,int sampleNumber){
		ArrayList<Long> rttResult = PingFactory.newInstance().calRTT(hostIP,sampleNumber);
		System.out.println("sample numbers are : " + rttResult.size());
		System.out.println("min is : " + MyMath.min(rttResult)+"ns");
		System.out.println("avg is : " + MyMath.mean(rttResult)+"ns");
		System.out.println("max is : " + MyMath.max(rttResult)+"ns");
		System.out.println("medv is : " + MyMath.standardDeviation(rttResult)+"ns");
		

	}
}








/*
try {
  String ipAddress = "182.168.0.28";
  InetAddress inet = InetAddress.getByName(ipAddress);

  System.out.println("Sending Ping Request to " + ipAddress);
  while(true){
      long finish = 0;
      long start = new GregorianCalendar().getTimeInMillis();
 
      if (inet.isReachable(5000)){
        finish = new GregorianCalendar().getTimeInMillis();
        System.out.println("Ping RTT: " + (finish - start + "ms"));
      } else {
        System.out.println(ipAddress + " NOT reachable.");
      }
      try {
          Thread.sleep(50);
        } catch (InterruptedException e) { }
   }
} catch ( Exception e ) {
  System.out.println("Exception:" + e.getMessage());
}
*/