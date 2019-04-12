package org.ktrade.kline.kline.trade;


import org.springframework.stereotype.Service;

@Service
public class SortTool {
    private static long sortNum=1;
    public static long getSortNum(){
        return sortNum++;
    }


}
