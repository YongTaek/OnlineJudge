package kr.jadekim.oj.mainserver.util;

import java.util.ArrayList;

/**
 * Created by ohyongtaek on 2016. 8. 31..
 */
public class Pagenation {

    public static ArrayList<Integer> generatePagenation(int totalPages, int pageSize) {
        ArrayList<Integer> pages = new ArrayList<>();
        if(totalPages%pageSize==0){
            for(int i=0;i<=totalPages/pageSize;i++){
                pages.add(i+1);
            }
        }else{
            for(int i=0;i<(totalPages/pageSize)+1;i++){
                pages.add(i+1);
            }

        }
        return pages;
    }
}
