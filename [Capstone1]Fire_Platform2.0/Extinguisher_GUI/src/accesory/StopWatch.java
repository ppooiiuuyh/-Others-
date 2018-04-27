package accesory;

public class StopWatch {
	long startTime;
	long startTime_nano;
	
	public StopWatch(){
		startTime = System.currentTimeMillis();
		startTime_nano = System.nanoTime();
	}
	public static StopWatch newInstance(){
		return new StopWatch();
	}
	
	public void resetTime(){
		startTime = System.currentTimeMillis();
	}
	public void resetTime_nano(){
		startTime_nano = System.nanoTime();
	}
	
	public long checkTime(){
		return  System.currentTimeMillis() - startTime;
	}
	public long checkTime_nano(){
		return System.nanoTime() -startTime_nano;
	}
	
	public long currentTime(){
		return System.currentTimeMillis();
	}
	public long currentTime_nano(){
		return System.nanoTime();
	}
	
	
}
