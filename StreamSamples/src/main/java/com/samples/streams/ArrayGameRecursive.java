package com.samples.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayGameRecursive {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        List<String> results=new ArrayList<>();
        List<String> testResults=new ArrayList<>();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();
            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }
            if(canWin(leap, game)) {
                System.out.println( "YES");
                results.add("YES");
            }else {
                results.add("NO");
                System.out.println( "NO");
            }
        }
        System.out.println("enter actual results to compare!!");
        scan.nextLine();
        for(int i=0;i<results.size();i++){
            testResults.add(scan.nextLine());
        }
        System.out.println(results.size() +" ::  "+testResults.size());
        for (int i=0;i<results.size();i++){
            if(!results.get(i).equalsIgnoreCase(testResults.get(i))){
                System.out.println("test cases failed :: "+i);
            }
        }
        scan.close();
    }
    public static boolean canWin(int leap, int[] game) {
        return isSolvable(leap,game,0);
    }
    private static boolean isSolvable(int m, int[] arr, int i) {
        if (i < 0 || arr[i] == 1) return false;
        if ((i == arr.length - 1) || i + m > arr.length - 1) return true;

        arr[i] = 1;
        return isSolvable(m, arr, i + 1) || isSolvable(m, arr, i - 1) || isSolvable(m, arr, i + m);
    }
}
