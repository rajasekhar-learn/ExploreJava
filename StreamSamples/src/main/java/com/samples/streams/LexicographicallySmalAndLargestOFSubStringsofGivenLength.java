package com.samples.streams;

import java.util.Scanner;

public class LexicographicallySmalAndLargestOFSubStringsofGivenLength {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        String s=scanner.next();
        int k=scanner.nextInt();
        System.out.println(getSmallestAndLargest(s,k));
    }

    public static String getSmallestAndLargest(String s, int k) {
        String smallest = "";
        String largest = "";
        for(int i=0;i<=s.length()-k;i++){
            String subString=s.substring(i,i+k);
            if(largest.compareTo(subString)<=0){
                largest=subString;
                if(i==0)
                smallest=subString;
            }else if(smallest.compareTo(subString)>=0) {
                smallest=subString;
            }
        }
        return smallest + "\n" + largest;
    }

}

