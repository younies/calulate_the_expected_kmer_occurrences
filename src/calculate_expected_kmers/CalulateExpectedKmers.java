package calculate_expected_kmers;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CalulateExpectedKmers {
	
	public final static BigDecimal kmersNumbers = new BigDecimal("1000000");
	public final static BigDecimal allGenomesCount = new BigDecimal("9689453908");
	
	
	public static long[] hittsResults = {1073163l ,228507l ,132203l ,96709l ,78364l ,94369l ,200352l ,478417l ,1010184l ,1866129l ,3230771l ,6267568l ,15127271l ,40751412l ,103713948l ,226839806l ,402445487l ,548925818l ,558002786l ,442708943l ,309275372l ,213289466l ,149481068l ,102529444l ,64613859l ,35239867l ,15640038l ,5201655l ,1148779l ,124056l ,0l ,3153184189l} ;
	public static BigDecimal expectedHitts[] = new BigDecimal[32];
	
	public static BigInteger binomial(final int N, final int K)
	{
	    BigInteger ret = BigInteger.ONE;
	    for (int k = 0; k < K; k++) {
	        ret = ret.multiply(BigInteger.valueOf(N-k))
	                 .divide(BigInteger.valueOf(k+1));
	    }
	    return ret;
	}
	
	
	public static void main(String[] args)
	{
		 BigDecimal kmersProbability[] = new BigDecimal[32];
		 
		 for(int i = 0 ; i < kmersProbability.length ; ++i)
		 {
			 
			 BigDecimal temp =  new BigDecimal(binomial(31, i));
			 BigDecimal factors = new BigDecimal(  Math.pow(0.25, 31- i) * Math.pow(0.75, i));
			 temp = temp.multiply( factors);
			 
			 kmersProbability[i] = temp.multiply(kmersNumbers);
			 expectedHitts[i] = kmersProbability[i].multiply(allGenomesCount);
			 System.out.print(i +": kmer_probability " + kmersProbability[i].divide(kmersNumbers) );
			 System.out.print("  Expected_number_of_hits:  " + expectedHitts[i].toBigInteger() );
			 System.out.print(" Actual_number_of_hitts: " + hittsResults[i]);
			 BigDecimal tempoo = new BigDecimal(hittsResults[i]);
			 System.out.println(" Differences:  " + expectedHitts[i].subtract(tempoo).toBigInteger().abs() );
		 }	 
	}	
}
