package com.samples.streams;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Exam {

    private static final Pattern urlPattern = Pattern.compile(
            "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    static Map<Integer,String> days=new HashMap<>();
    static {
        days.put(Calendar.SUNDAY,"SUNDAY");
        days.put(Calendar.MONDAY,"MONDAY");
        days.put(Calendar.TUESDAY,"TUESDAY");
        days.put(Calendar.WEDNESDAY,"WEDNESDAY");
        days.put(Calendar.THURSDAY,"THURSDAY");
        days.put(Calendar.FRIDAY,"FRIDAY");
        days.put(Calendar.SATURDAY,"SATURDAY");
    }
    public static void main(String[] args) {
      /*  List<String> domains=new ArrayList<>();
        domains.add("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        domains.add("<HEML >  http://abcd.com/123");
        domains.add("https://abcd.com/123");
        domains.add("http://www.abcd.com/123");
        domains=domains.parallelStream().map(Exam::getUrl).collect(Collectors.toList());
        System.out.println(findNumber(domains));*/
        //Scanner sc=new Scanner(System.in);

        System.out.println(findDay(7,5,2015));

    }
    public static int findDay(int month, int day, int year) {

        Calendar calendar=Calendar.getInstance();
        calendar.set(year,month,day);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
    public static String getUrl(String line){
        Matcher matcher = urlPattern.matcher(line);
        while (matcher.find()) {
            int matchStart = matcher.start(1);
            int matchEnd = matcher.end();
            return line.substring(matchStart,matchEnd);
        }
        return "";
    }
    public static String findNumber( List<String> lines) {
        // Write your code here
        try {
           lines= lines.parallelStream().map(domain-> {
                try {
                    return new URL(domain);
                } catch (MalformedURLException e) {

                }
                return null;
            }).filter(url->url!=null).map(url ->url.getHost()).map(host->{
                if(host.contains("www.")||host.contains("ww2.")|| host.contains("web.")){
                    return host.substring(4,host.length());
                }
                return host;
           }).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());
           lines.sort(Comparator.naturalOrder());
           return lines.stream().collect(Collectors.joining(";"));

        }catch (Exception e){

        }
        return null;
    }
}
