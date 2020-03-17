package com.samples.streams;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

public class TokensPrinterFromStringMatchingGivenPattern {
    public static String patterStr="[A-Za-z\\s\\!\\,\\?\\.\\_\\'\\@]+";
    static Map<String,String> regexMap=new HashMap<>();
    public static void main(String[] args) {
        regexMap.put("!","!");
        regexMap.put(",","\\,");
        regexMap.put(".","\\.");
        regexMap.put("_","\\_");
        regexMap.put("@","\\@");
        regexMap.put("?","\\?");
        regexMap.put(" ","\\s");
        regexMap.put("","\\s");
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        if(Pattern.compile(patterStr).matcher(s).find()){
            List<String> result=new ArrayList<>();
            result.add(s);
            System.out.println(s.split(""));
            Set<String> splitChars=Arrays.stream(s.split("")).filter(op-> !Pattern.compile("[A-Za-z]+").matcher(op).find()).collect(Collectors.toSet());
            for (String splitChar:splitChars) {
                result=applySplit(result,splitChar.trim());
            }
            result=result.stream().filter(data->data!=null && !data.isEmpty()).collect(Collectors.toList());
            System.out.println(result.size());
            result.forEach(System.out::println);
        }
        scan.close();
    }
    private static List<String> applySplit(List<String> s, String pattern){
        List<String>result=new ArrayList<>();
        for (String s1:s) {
            try {
                result.addAll(Arrays.asList(s1.split(regexMap.get(pattern)!=null?regexMap.get(pattern):pattern)));
            }catch (PatternSyntaxException e){
                result.addAll(Arrays.asList(s1.split(regexMap.get(pattern))));
            }
        }
        return result;
    }

}
