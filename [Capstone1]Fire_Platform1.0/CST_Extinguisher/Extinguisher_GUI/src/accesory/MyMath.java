package accesory;

import java.util.ArrayList;

public class MyMath {
	
	public static long sum(ArrayList<Long> array){
		long sum =0;
		for(Long data :array)
			sum += data;
		
		return sum;
	}
	public static long min(ArrayList<Long> array){
		long min =array.get(0);
		for(Long data :array){
			if(data<min)
				min= data;
		}
		
		return min;
	}
	
	public static long max(ArrayList<Long> array){
		long max =array.get(0);
		for(Long data :array){
			if(data>max)
				max=data;
		}
		
		return max;
	}
	
	
	
	public static double mean(ArrayList<Long> array) {
	    double sum = 0.0;

	    for (Long data : array)
	      sum += data;

	    return sum / array.size();
	  }


	  public static double standardDeviation(ArrayList<Long> array) {
	  
	    double sum = 0.0;
	    double sd = 0.0;
	    double diff;
	    double meanValue = mean(array);

	    for (Long data : array) {
	      diff = data - meanValue;
	      sum += diff * diff;
	    }
	    sd = Math.sqrt(sum / (array.size()));

	    return sd;
	  }
}
