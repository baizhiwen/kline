package org.ktrade.kline.kline.controller;


import org.apache.commons.lang3.StringUtils;
import org.ktrade.kline.kline.trade.MatchingTrade;
import org.ktrade.kline.kline.trade.SortTool;
import org.ktrade.kline.kline.trade.Trade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("matching")
public class MatchingTradeController {

    private static final Logger logger = LoggerFactory.getLogger(MatchingTradeController.class);

    @Autowired
    private MatchingTrade matchingTrade;



    /*
     * 买入BTC_ETH
     * 卖出BTC
     * 买挂单 BUY
     * 卖挂单 SELL
     * 最新成交
     */

    @RequestMapping(value="/init", method = RequestMethod.GET )
    public boolean init(  String symbol) {
        if(StringUtils.isEmpty(symbol)){
            return false;
        }
        matchingTrade.testInsertBuyList(symbol);
        matchingTrade.testInsertSellList(symbol);
        matchingTrade.loopMatching();
        return true;
    }

    @RequestMapping(value="/buy", method = RequestMethod.POST )
    public int buy( BigDecimal price,BigDecimal amount, String symbol) {
        if(price==null || amount ==null || StringUtils.isEmpty(symbol)){
            return -1;
        }

        Trade trade = new Trade();
        trade.setAmount(amount);
        trade.setPrice(price);
        trade.setSortNum(SortTool.getSortNum());
        trade.setType(1);
        trade.setSymbol(symbol);
        int idx=matchingTrade.insertBuyList(trade);
        matchingTrade.loopMatching();
        return idx;
    }

    @RequestMapping(value="/sell", method = RequestMethod.POST)
    public int sell( BigDecimal price,BigDecimal amount, String symbol) {
        if(price==null || amount ==null || StringUtils.isEmpty(symbol)){
            return -1;
        }

        Trade trade = new Trade();
        trade.setAmount(amount);
        trade.setPrice(price);
        trade.setSortNum(SortTool.getSortNum());
        trade.setType(2);
        trade.setSymbol(symbol);
        int idx=matchingTrade.insertSellList(trade);
        matchingTrade.loopMatching();
        return idx;
    }

    @RequestMapping(value="/buyList", method = RequestMethod.GET)
    public List<Trade> buyList(String symbol) {
        if(StringUtils.isEmpty(symbol)){
            return null;
        }

        List<Trade> list=matchingTrade.getBuyList();
        return list;
    }

    @RequestMapping(value="/sellList", method = RequestMethod.GET)
    public List<Trade> sellList(String symbol) {
        if(StringUtils.isEmpty(symbol)){
            return null;
        }

        List<Trade> list=matchingTrade.getSellList();
        return list;
    }

    @RequestMapping(value="/buyListAll", method = RequestMethod.GET)
    public List<Trade> buyListAll(String symbol) {
        if(StringUtils.isEmpty(symbol)){
            return null;
        }

        List<Trade> list=matchingTrade.getBuyListAll();
        return list;
    }

    @RequestMapping(value="/sellListAll", method = RequestMethod.GET)
    public List<Trade> sellListAll(String symbol) {
        if(StringUtils.isEmpty(symbol)){
            return null;
        }

        List<Trade> list=matchingTrade.getSellListAll();
        return list;
    }



    @RequestMapping(value="/getHistoryList", method = RequestMethod.GET)
    public List<Trade> getHistoryList(String symbol) {
        if(StringUtils.isEmpty(symbol)){
            return null;
        }

        List<Trade> list=matchingTrade.getHistoryList();
        return list;
    }






}
