package org.ktrade.kline.kline.trade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.collections.list.TreeList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 0.后期抽象出一个撮合接口 TODO
 * <p>
 * 1.接收挂单接口      ( 远程方法 )
 * 2.吐出成交订单接口  ( 远程方法 )
 * 3.最新成交价接口
 * 4.快照接口
 * 5.取消挂单接口
 */

@Service
public class MatchingTrade {
    private static final Logger logger = LoggerFactory.getLogger(MatchingTrade.class);

    private TreeList buyList = new TreeList();   //买单队列
    private TreeList sellList = new TreeList();  //卖单队列
    private List matchHistory =new LinkedList<TreeList>();  //匹配历史


    private BigDecimal lastPrice = BigDecimal.valueOf(0);   //最新成交价,默认最后一次成交价为0
    @Autowired
    private WebSocketH5Session webSocketH5Session;


    public List<Trade> getHistoryList() {
        List<Trade> list= new ArrayList<Trade>();
        int size= (matchHistory.size()<20)?matchHistory.size():20;
        for(int i=0;i<size;i++){
            list.add((Trade)matchHistory.get(i));
        }
        return list;
    }

    public List<Trade> getBuyListAll() {
        List<Trade> list= new ArrayList<Trade>();
        int size= buyList.size();
        for(int i=0;i<size;i++){
            list.add((Trade)buyList.get(i));
        }
        return list;
    }

    public  List<Trade> getSellListAll() {
        List<Trade> list= new ArrayList<Trade>();
        int size= sellList.size();
        for(int i=0;i<size;i++){
            list.add((Trade)sellList.get(i));
        }
        return list;
    }

    public List<Trade> getBuyList() {
        List<Trade> list= new ArrayList<Trade>();
        int size= (buyList.size()<5)?buyList.size():5;
        for(int i=0;i<size;i++){
            list.add((Trade)buyList.get(i));
        }
        return list;
    }

    public  List<Trade> getSellList() {
        List<Trade> list= new ArrayList<Trade>();
        int size= (sellList.size()<5)?sellList.size():5;
        for(int i=0;i<size;i++){
            list.add((Trade)sellList.get(i));
        }
        return list;
    }

    /**
     * 插入购买撮合队列
     *
     * @param trade
     * @return
     */
    public int insertBuyList(Trade trade){
        return  insertBuyList( trade,false);
    }
    private int insertBuyList(Trade trade,boolean onlyPrice) {
        int idx = -1;
        if (trade == null) {
            return -1;
        }
        int size = buyList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                Trade trade2 = (Trade) buyList.get(i);
                if (compareBuyList(trade, trade2,onlyPrice) > 0) {
                    idx = i;
                    break;
                }
            }
            if (idx == -1) {
                idx = size;
            }
        } else {
            idx = 0;
        }

        buyList.add(idx, trade);
        /*//推送
        if(idx<5){
            sendLast5Massage();
        }*/
        return idx;
    }



    /**
     * 插入卖出撮合队列
     *
     * @param trade
     * @return
     */
    public int insertSellList(Trade trade){
        return  insertSellList(trade,false);
    }

    //private List<Trade> list=new LinkedList<Trade>();
    private int insertSellList(Trade trade,boolean onlyPrice) {
        int idx = -1;
        if (trade == null) {
            return -1;
        }

        int size = sellList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                Trade trade2 = (Trade) sellList.get(i);
                if (compareSellList(trade, trade2,onlyPrice) > 0) {
                    idx = i;
                    break;
                }
            }
            if (idx == -1) {
                idx = size;
            }
        } else {
            idx = 0;
        }

        sellList.add(idx, trade);
        //sellList.add(size, trade);
        //int x=list.size();
        // list.add(x,trade);
        /* //推送
        if(idx<5){
            sendLast5Massage();
        }*/
        return idx;
    }

    /**
     * 购买队列比较规则
     *
     * @param t1
     * @param t2
     * @return
     */
    private int compareBuyList(Trade t1, Trade t2,boolean onlyPrice) {
        if (t1.getPrice().compareTo(t2.getPrice()) > 0) {
            return 1;
        } else if (t1.getPrice().compareTo(t2.getPrice()) == 0) {
            if(onlyPrice) {
                //如果onlyPrice为真，表示只判断价格，不判断序号，价格一样的情况下返回t1>t2
                return 1;
            }else if (t1.getSortNum() < t2.getSortNum()) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    /**
     * 购买队列比较规则
     *
     * @param t1
     * @param t2
     * @return
     */
    private int compareSellList(Trade t1, Trade t2,boolean onlyPrice) {
        if (t1.getPrice().compareTo(t2.getPrice()) < 0) {
            return 1;
        } else if (t1.getPrice().compareTo(t2.getPrice()) == 0) {
            if(onlyPrice) {
                //如果onlyPrice为真，表示只判断价格，不判断序号，价格一样的情况下返回t1>t2
                return 1;
            }else if (t1.getSortNum() < t2.getSortNum()) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    /**
     * 撮合方法
     * 1.取出买单第一个与卖单第一个，判断买单价格是否大于卖单
     * 2.停止条件：
     *  1）如果没有买单 或者 没有卖单 则停止
     *  2）如果买单价小于卖单价 则停止
     * 3.撮合 (
     *  0) 买单、卖单先出队列
     *  1）买单数量 > 卖单数量：调用吐出撮合完成方法sendMatchResult，接到返回值后，减少买单数量、买单入队列，
     *  2）买单数量 == 卖单数量：调用吐出撮合完成方法sendMatchResult，接到返回值，
     *  3）买单数量 < 卖单数量：调用吐出撮合完成方法sendMatchResult，接到返回值后，减少卖单数量、卖单入队列，
     */
    private boolean matching() {
        boolean r = false;
        //1）如果没有买单 或者 没有卖单 则停止
        if (buyList.size() == 0 || sellList.size() == 0) {
            return r;
        }


        Trade buy = (Trade) buyList.remove(0);
        Trade sell = (Trade) sellList.remove(0);
        //2）如果买单价小于卖单价 则停止
        if (buy.getPrice().compareTo(sell.getPrice()) < 0) {
            insertBuyList(buy,true);
            insertSellList(sell,true);
            return r;
        }
        //判断价格
        BigDecimal tmpLastPrice=lastPrice;
        if(tmpLastPrice.compareTo(buy.getPrice())>=0){
            tmpLastPrice=buy.getPrice();
        }else if(tmpLastPrice.compareTo(sell.getPrice())<=0){
            tmpLastPrice=sell.getPrice();
        }

        //1）买单数量 > 卖单数量：调用吐出撮合完成方法sendMatchResult，接到返回值后，减少买单数量、买单入队列
        if (buy.getAmount().compareTo(sell.getAmount()) > 0) {

            //发送，接到返回值后
            Trade buyTemp=new Trade(tmpLastPrice,sell.getAmount(),buy.getSortNum(),buy.getType(),buy.getSymbol());
            Trade sellTemp=new Trade(tmpLastPrice,sell.getAmount(),sell.getSortNum(),sell.getType(),sell.getSymbol());
            boolean sendResult=sendMatchResult(buyTemp,sellTemp);
            if(sendResult){
                buy.setAmount(buy.getAmount().subtract(sell.getAmount())); //减少买单数量
                insertBuyList(buy,true);
                lastPrice=tmpLastPrice;
            }else{
                //失败后重新插入队列
                insertBuyList(buy,true);
                insertSellList(sell,true);

            }
        }else if(buy.getAmount().compareTo(sell.getAmount()) ==0){
            //2）买单数量 == 卖单数量：调用吐出撮合完成方法sendMatchResult，接到返回值
            Trade buyTemp=new Trade(tmpLastPrice,buy.getAmount(),buy.getSortNum(),buy.getType(),buy.getSymbol());
            Trade sellTemp=new Trade(tmpLastPrice,sell.getAmount(),sell.getSortNum(),sell.getType(),sell.getSymbol());
            boolean sendResult=sendMatchResult(buyTemp,sellTemp);
            if(sendResult){
                lastPrice=tmpLastPrice;
            }else{
                //失败后重新插入队列
                insertBuyList(buy,true);
                insertSellList(sell,true);
            }
        }else {
            //3）买单数量 < 卖单数量：调用吐出撮合完成方法sendMatchResult，接到返回值后，减少卖单数量、卖单入队列，
            Trade buyTemp=new Trade(tmpLastPrice,buy.getAmount(),buy.getSortNum(),buy.getType(),buy.getSymbol());
            Trade sellTemp=new Trade(tmpLastPrice,buy.getAmount(),sell.getSortNum(),sell.getType(),sell.getSymbol());
            boolean sendResult=sendMatchResult(buyTemp,sellTemp);
            if(sendResult){
                sell.setAmount(sell.getAmount().subtract(buy.getAmount())); //减少卖单数量
                insertSellList(sell,true);
                lastPrice=tmpLastPrice;
            }else{
                //失败后重新插入队列
                insertBuyList(buy,true);
                insertSellList(sell,true);
            }
        }
        r=true;

        return r;
    }

    /**
     * 循环多次调用撮合方法，如果撮合成功（返回true),则继续下一次撮合
     */
    public void loopMatching() {
        boolean r = true;
        while (r) {
            r = matching();
        }

    }
    public int x=0;
    /**
     * 撮合成功后，调用此方法对外公布撮合结果
     */
    private boolean sendMatchResult(Trade buy,Trade sell) {
        Trade lastTrade = buy.getSortNum()>=sell.getSortNum()?buy:sell;
        logger.info("撮合成功：" + buy + sell);

        matchHistory.add(0,lastTrade);
        int hsize=100;
        if(matchHistory.size()>hsize){
            //删掉一半
            matchHistory=matchHistory.subList(0,hsize/2);
        }

        try {
            //推送历史
            webSocketH5Session.sendHistoryMessage(lastTrade);
            //推送最新5条
            sendLast5Massage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        x++;
        return true;
    }

    /**
     * 推送最新5条
     */
    public void sendLast5Massage(){
        try {
            webSocketH5Session.sendLast5Message(getBuyList(),getSellList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //------------------------------------------------------------------------------------------//
    private long timeIdx=1;
    /**
     * 测试购买插入撮合列表
     */
    public void testInsertBuyList(String symbol) {

        for (int i = 1; i <= 10; i++) {
            BigDecimal price = BigDecimal.valueOf(123 + i);
            BigDecimal amount = BigDecimal.valueOf(10);
            Long sortNum =SortTool.getSortNum();
            Integer type = 1;
            Trade t = new Trade(price, amount, sortNum,type,symbol);
            this.insertBuyList(t);
        }

        BigDecimal price = BigDecimal.valueOf(130.1);
        BigDecimal amount = BigDecimal.valueOf(10);
        Long sortNum =SortTool.getSortNum();
        Integer type = 1;
        Trade t = new Trade(price, amount, sortNum, type,symbol);
        this.insertBuyList(t);

        price = BigDecimal.valueOf(130);

        sortNum =SortTool.getSortNum();
        Trade t2 = new Trade(price, amount, sortNum, type,symbol);
        this.insertBuyList(t2);

        price = BigDecimal.valueOf(130);
        sortNum =SortTool.getSortNum();
        amount = BigDecimal.valueOf(11);

        Trade t3 = new Trade(price, amount, sortNum,  type,symbol);
        this.insertBuyList(t3);

        price = BigDecimal.valueOf(130);
        sortNum =SortTool.getSortNum();
        amount = BigDecimal.valueOf(11);

        Trade t4 = new Trade(price, amount, sortNum, type,symbol);
        this.insertBuyList(t4);

        price = BigDecimal.valueOf(129.1);
        sortNum =SortTool.getSortNum();
        amount = BigDecimal.valueOf(11);

        Trade t5 = new Trade(price, amount, sortNum, type,symbol);
        this.insertBuyList(t5);

        price = BigDecimal.valueOf(150.1);
        sortNum =SortTool.getSortNum();
        amount = BigDecimal.valueOf(11000);

        Trade t6 = new Trade(price, amount, sortNum, type,symbol);
        //this.insertBuyList(t6);
        //printBuyList();
    }
    public void printBuyList(){
        logger.info("买家列表：---------------------------------");
        for (int i = 0; i < buyList.size(); i++) {
            logger.info(i + ":" + buyList.get(i));
        }
    }

    /**
     * 测试购卖插入撮合列表
     */
    public void testInsertSellList( String symbol) {

        for (int i = 10; i > 0; i--) {
            BigDecimal price = BigDecimal.valueOf(123 + i);
            BigDecimal amount = BigDecimal.valueOf(10);
            Long sortNum =SortTool.getSortNum();
            Integer type = 2;
            Trade t = new Trade(price, amount, sortNum,  type,symbol);
            this.insertSellList(t);
        }

        BigDecimal price = BigDecimal.valueOf(130.1);
        BigDecimal amount = BigDecimal.valueOf(10);
        Long sortNum =SortTool.getSortNum();
        Integer type = 2;
        Trade t = new Trade(price, amount, sortNum,  type,symbol);
        this.insertSellList(t);

        price = BigDecimal.valueOf(130);
        sortNum =SortTool.getSortNum();
        amount = BigDecimal.valueOf(11);

        Trade t2 = new Trade(price, amount, sortNum,type,symbol);
        this.insertSellList(t2);

        price = BigDecimal.valueOf(130);
        sortNum =SortTool.getSortNum();
        amount = BigDecimal.valueOf(11);

        Trade t3 = new Trade(price, amount, sortNum,  type,symbol);
        this.insertSellList(t3);

        price = BigDecimal.valueOf(130);
        sortNum =SortTool.getSortNum();
        amount = BigDecimal.valueOf(11);

        Trade t4 = new Trade(price, amount, sortNum, type,symbol);
        this.insertSellList(t4);

        price = BigDecimal.valueOf(129.1);
        sortNum =SortTool.getSortNum();
        amount = BigDecimal.valueOf(11);

        Trade t5 = new Trade(price, amount, sortNum, type,symbol);
        this.insertSellList(t5);
        //printSellList();
    }
    public void printSellList(){
        logger.info("卖家列表：---------------------------------");
        for (int i = 0; i < sellList.size(); i++) {
            logger.info(i + ":" + sellList.get(i));
        }
    }
    public static void main(String[] args){
        String symbol="eth_btc";
        MatchingTrade mt = new MatchingTrade();

        Long t1= Calendar.getInstance().getTimeInMillis();
        for (int i = 1000000; i > 0; i--) {
            BigDecimal price = BigDecimal.valueOf(123 + i);
            BigDecimal amount = BigDecimal.valueOf(10);
            Long sortNum =SortTool.getSortNum();
            Integer type = 2;
            Trade t = new Trade(price, amount, sortNum,  type,symbol);
            mt.insertSellList(t);
        }
        Long t2= Calendar.getInstance().getTimeInMillis()-t1;
        System.out.println(t2);
        //System.out.println(mt.list.size());
    }
    public static void main2(String[] args) {
        String symbol1="eth_btc";
        String symbol2="kcoin_btc";

        String symbol3="eth_btc";

        Map<String,MatchingTrade> symbolMap= new HashMap<String, MatchingTrade>();    //交易对数组 TODO  每个交易对一组买单和卖单队列
        MatchingTrade ethBtc = new MatchingTrade();
        MatchingTrade kcoinBtc = new MatchingTrade();
        symbolMap.put(symbol1,ethBtc);
        symbolMap.put(symbol2,kcoinBtc);

        if(!symbolMap.containsKey(symbol3)){
            MatchingTrade mt = new MatchingTrade();
            symbolMap.put(symbol3,mt);
        }
        MatchingTrade mt = symbolMap.get(symbol3);

        Long t0= Calendar.getInstance().getTimeInMillis();
        //测试插入
        mt.testInsertBuyList(symbol3);
        mt.testInsertSellList(symbol3);
        mt.testInsertBuyList(symbol3);
        mt.testInsertSellList(symbol3);
        mt.testInsertBuyList(symbol3);
        mt.testInsertSellList(symbol3);
        mt.testInsertBuyList(symbol3);
        mt.testInsertSellList(symbol3);
        mt.testInsertBuyList(symbol3);
        mt.testInsertSellList(symbol3);
        mt.testInsertBuyList(symbol3);
        mt.testInsertSellList(symbol3);

        //Long t2= Calendar.getInstance().getTimeInMillis()-t0;
        //logger.info("插入"+mt.x+"笔，用时:"+t2+"毫秒");
       // 交易对撮合
        //测试撮合
        Long t1= Calendar.getInstance().getTimeInMillis();
        mt.loopMatching();
        Long t3= Calendar.getInstance().getTimeInMillis()-t0;
        logger.info("撮合"+mt.x+"笔，用时:"+t3+"毫秒");
       // mt.printBuyList();
       // mt.printSellList();


    }
}
