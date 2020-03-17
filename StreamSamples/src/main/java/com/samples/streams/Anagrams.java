package com.samples.streams;

import java.util.Scanner;

public class Anagrams {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        String b = scan.next();
        scan.close();
        boolean ret = isAnagram(a, b);
        System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
    }
    static boolean isAnagram(String a, String b) {
        a=a.toLowerCase();
        b=b.toLowerCase();
        if(a.length()!=b.length()) {
            return false;
        }else if(a.equalsIgnoreCase(b)) {
            return true;
        }else if(a.chars().distinct().count()!=b.chars().distinct().count()){
            return false;
        }
        else {
            //check frequency of each char
            for(int c:a.toCharArray()){
                if(a.chars().filter(i->i==c).count()!=b.chars().filter(j->j==c).count()){
                    return false;
                }
            }
        }
        return true;
    }
}
