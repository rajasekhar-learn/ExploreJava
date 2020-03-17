package com.samples.streams;

import java.util.Arrays;

public class ChangeProblem {
static long count;
    public static void main(String[] args) {
        int[] d={1,2,3};
        long sum=6;
        System.out.println(numWays(d,d.length,sum));
        System.out.println(numWays(d,d.length,1)+numWays(d,d.length,sum-1));
        System.out.println(numWays(d,d.length,2)+numWays(d,d.length,sum-2));
        System.out.println(numWays(d,d.length,3)+numWays(d,d.length,sum-3));
        System.out.println(count);
    }

    public static int numWays(int[] denoms, int m,long sum){
        count++;
     //   System.out.println(Arrays.deepToString(Arrays.stream(denoms).boxed().toArray())+ " m: "+m +" sum: "+sum);
        if(sum<0) {
     //       System.out.println(" stack END ==> return 0");
            return 0;
        }
        if(sum==0) {
      //      System.out.println("found way ..");
            return 1;
        }
        if(m<=0 && sum>0){
       //     System.out.println(" stack END ==> return 0");
            return 0;
        }
        return numWays( denoms, m - 1, sum ) +
                numWays( denoms, m, sum-denoms[m-1]);
    }
}

