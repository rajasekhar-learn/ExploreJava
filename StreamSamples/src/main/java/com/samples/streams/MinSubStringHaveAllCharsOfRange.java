package com.samples.streams;

import java.util.Scanner;

/**
 * prints minSubstring that includes all chars os given range.
 */
public class MinSubStringHaveAllCharsOfRange {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String S = in.next();
        int start = in.nextInt();
        int end = in.nextInt();
        String previous="";
        String result="";
        for(int i=start;i<end;i++){
            String index=""+S.charAt(i);
            if(!previous.equals(index)){
                previous=index;
                result=result+index;
            }else{
                previous=index;
            }
        }
        System.out.println(result);
    }
}
