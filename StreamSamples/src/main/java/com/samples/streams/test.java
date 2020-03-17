package com.samples.streams;

import java.util.Arrays;
import java.util.stream.IntStream;

public class test {
    static long count;
    public static void main(String[] args) {
        int[] d={2,4};
        int sum=4;

        System.out.println("count :: "+numWays(d,d.length,sum));
        //System.out.println(count);
    }

    public static int numWays(int[] denoms, int m,int sum){
        int table[] = new int[sum+1];
        table[0]=1;

        for(int i=0;i<m;i++) {
            for (int j = denoms[i]; j <= sum; j++) {
                table[j]=table[j] + table[j - denoms[i]];
                IntStream.of(table).forEach(x -> {
                    System.out.print(x + " ");
                });
            }
            System.out.println();
        }
        return table[sum];
    }
}

