package com.samples.streams;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test1 {
    static volatile int count=0;
    public static void main(String[] args) {
        List<Integer> numbers=new ArrayList<>();
        numbers= IntStream.rangeClosed(1,1000000).boxed().collect(Collectors.toList());
       // romanizer(numbers).forEach(System.out::println);
         List<Integer> parrellList=getPairs(numbers,100000);
         List<Integer> seqList=getSeqPairs(numbers,100000);
         parrellList.stream().forEach(val->{
             if(!seqList.contains(val)) {
                 System.out.println(val);
             }
         });
    }

    static List<Integer> getPairs(List<Integer> stocksProfit,int target){
        List<Integer> seenList=new CopyOnWriteArrayList<>();
        stocksProfit.stream().forEach(integer -> {
            if(integer<=target) {
                Integer pair = target - integer;
                if (stocksProfit.contains(pair) && !seenList.contains(pair) && !seenList.contains(integer)) {
                    seenList.add(integer);
                }
            }
        });
        //System.out.println(seenList.stream().map(val->val.toString()).collect(Collectors.joining(",")));
        return seenList;
    }

    static List<Integer> getSeqPairs(List<Integer> stocksProfit,int target){

        List<Integer> seenList=new CopyOnWriteArrayList<>();
        stocksProfit.stream().forEach(integer -> {
            if(integer<=target) {
                Integer pair = target - integer;
                if (stocksProfit.contains(pair) && !seenList.contains(pair) && !seenList.contains(integer)) {
                    seenList.add(integer);
                }
            }
        });
        //System.out.println(seenList.stream().map(val->val.toString()).collect(Collectors.joining(",")));
        return seenList;
    }

    public static List<String> romanizer(List<Integer> numbers) {
        return numbers.stream().map(Test1::getRomanNumber).collect(Collectors.toList());
    }

    static public String getRomanNumber(int number) {
        int[] decimalArray = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanArray = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < decimalArray.length; i++) {
            while (number >= decimalArray[i]) {
                number = number - decimalArray[i];
                result.append(romanArray[i]);
            }
        }
        return result.toString();
    }
}
