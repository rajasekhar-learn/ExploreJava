package com.samples.streams;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

public class ArrayGame {
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
        //System.out.println(Arrays.deepToString(Arrays.stream(game).boxed().toArray()));
        List<Integer> visited=new ArrayList<>();
        if(game[0]==0 && game.length<leap){
            return true;
        }else if(Arrays.stream(game).filter(x->x==1).count()==game.length){
            return false;
        }else if(Arrays.stream(game).filter(x->x==0).count()==game.length){
            return true;
        }else if(game[0]==1){
            return false;
        }else {
            int i=0;
            int j=0;
            visited.add(i);
            while (i<game.length) {
                j=getIndex(game,i,leap);
                if(j<=0){
                    return false;
                }else if(visited.contains(j) || j==i){
                   i= getNonVisitedZeroNode(visited,j,game,false);
                   if(i<=0){
                       j=i;
                       i= getNonVisitedZeroNode(visited,j,game,true);
                       if(i==game.length || i==-1) {
                           return false;
                       }
                   }
                }else{
                    for(int a=0;a<visited.size();a++){
                        if(j<visited.get(a)){
                            visited.add(a,j);
                            break;
                        }
                    }
                   if(!visited.contains(j)){
                       visited.add(j);
                   }
                    i=j;
                }
            }
        }
        return true;
    }

    static int getNonVisitedZeroNode(List<Integer> visited,int indexPos,int[] game,boolean dirRght){
        int nearestVisitedIndex=0;
        while (visited.contains(indexPos)){
            if(dirRght){
                indexPos=++indexPos;
            }else {
                indexPos=--indexPos;
            }
            if(indexPos<=0){
                return indexPos<0?0:indexPos;
            }else if(indexPos>=game.length){
                return game.length;
            }else if(!visited.contains(indexPos)){
                if(game[indexPos]==0){
                    if(dirRght){
                        nearestVisitedIndex=visited.indexOf(indexPos - 1);
                        visited.add(nearestVisitedIndex+1,indexPos);
                    }else{
                        nearestVisitedIndex=visited.indexOf(indexPos + 1);
                        visited.add(nearestVisitedIndex,indexPos);
                    }
                    return indexPos;
                }else {
                    if(dirRght){
                        if((visited.indexOf(indexPos - 1) + 1) >= visited.size()){
                            return -1;
                        }else {
                            indexPos = visited.get(visited.indexOf(indexPos - 1) + 1);
                        }
                    }else {
                        indexPos = visited.get(visited.indexOf(indexPos + 1) - 1);
                    }
                }
            }
        }
        return indexPos;
    }


    static int getIndex(int[] game,int pos, int leap){
        int l=game.length;
        if(pos+leap>l-1 || game[pos+leap]!=1){
            return pos+leap;
        }else if(pos+1<l && game[pos+1]==0){
            return pos+1;
        }else if(pos-1>=0 && game[pos-1]==0){
                return pos-1;
        }
        return pos;
    }
}
