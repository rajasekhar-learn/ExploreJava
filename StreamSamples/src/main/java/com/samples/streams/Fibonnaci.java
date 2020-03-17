package com.samples.streams;

import java.math.BigInteger;

public class Fibonnaci {
    public static void main(String[] args) {
        int n=100000;
        System.out.println(nthfib(n));
    }

    public static BigInteger nthfib(int n){
        BigInteger[] table=new BigInteger[n+1];
        table[0]=new BigInteger("1");
        table[1]=new BigInteger("1");
        for(int i=2;i<=n;i++)
        table[i]=table[i-1].add(table[i-2]);
        return table[n];
    }
    public static BigInteger nthNonDPfib(int n){
        if(n==0|| n==1){
            return new BigInteger("1");
        }
        return nthNonDPfib(n-1).add(nthNonDPfib(n-2));
    }

}
