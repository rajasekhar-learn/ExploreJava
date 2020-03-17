package com.samples.streams;

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        String A=sc.next();
        int len=A.length();
        int i=0;
        boolean isPalindrome=true;
        while (i<=len){
            if(A.charAt(len-1)!=A.charAt(i)){
                isPalindrome=false;
                System.out.println("No");
                break;
            }
            ++i;
            --len;
        }
        if(isPalindrome)
        System.out.println("Yes");
    }


}
