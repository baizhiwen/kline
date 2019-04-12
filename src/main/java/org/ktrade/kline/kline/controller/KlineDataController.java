package org.ktrade.kline.kline.controller;

import org.apache.commons.lang3.StringUtils;
import org.ktrade.kline.kline.bean.entity.KLineData;
import org.ktrade.kline.kline.bean.view.Depths;
import org.ktrade.kline.kline.bean.view.KybLineData;
import org.ktrade.kline.kline.bean.view.LineData;
import org.ktrade.kline.kline.bean.view.Trades;
import org.ktrade.kline.kline.service.KLineDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//@RestController
//@RequestMapping("kline")
public class KlineDataController {
    //http://localhost:8080/mock.json?symbol=coin5/coin3&range=60000&since=1508076960000&prevTradeTime=1510805549690
    private static final Logger logger = LoggerFactory.getLogger(KlineDataController.class);

    @Autowired
    private KLineDataService kLineDataService;

    @RequestMapping("/jsondata")
    public KybLineData jsondata(String symbol, Integer limit, Integer range, Long since, Long prevTradeTime){
        if(StringUtils.isBlank(symbol)){
            logger.error("symbol is null");
            return  null ;
        }
        //相同为假
        if(!((limit==null || limit<=0 ) ^ (since==null || since<=0)) ){
            logger.error("limit since 同时传参或同时没传参");
            return  null ;
        }
        if(prevTradeTime==null){

            logger.info("prevTradeTime is null");

            Calendar calendar=Calendar.getInstance();
            //默认为当前时间
            prevTradeTime = calendar.getTime().getTime();
        }

        //根据不同range查询不同的表

        List<KLineData> kLineDataList=null;

        if(limit!=null && limit>0) {
            limit=100;
            kLineDataList= kLineDataService.getMin1ByPrevTradeTimeAndLimit(prevTradeTime, limit);
        }else if(since!=null && since>0){
            kLineDataList= kLineDataService.getMin1BySinceAndPrevTradeTime(since,prevTradeTime);
        }

        if(kLineDataList==null){
            return null;
        }
        KybLineData kybLineData = new KybLineData();
        kybLineData.setSuccess(true);
        LineData lineData = new LineData();
        List<Object[]> list = new ArrayList<Object[]>();
        for(KLineData data:kLineDataList) {
            Object[] linesStr = data.toArray();
            list.add(linesStr);
        }

        lineData.setLines(list);
        kybLineData.setData(lineData);
        return kybLineData;
    }



/*
    private int x=0;
    @RequestMapping("/jsondata")
    public KLineData jsondata(String symbol, Integer range, Long since, Long prevTradeTime){
        KLineData kline=new KLineData();
        kline.setSuccess(true);
        LineData lineData = new LineData();

        Double[][] lines= new Double[10][6];
        for(int i=0;i<10;i++) {
            lines[0][0] = 1.50790476E12+i*10000;
            lines[0][1] = 299.30597249871;
            lines[0][2] = 299.30597249871;
            lines[0][3] = 199.30597249871;
            lines[0][4] = 199.30597249871;
            lines[0][5] = 66.9905449283;
        }
        lineData.setLines(lines);



        Trades t=new Trades();
        t.setAmount(0.02);
        t.setPrice(5798.79+x);
        t.setTid(373015085L);
        t.setTime(1508136949000L+x*1000);
        t.setType("buy");

        Trades[] trades=new Trades[1];
        trades[0]=t;

        lineData.setTrades(trades);

        Depths depths=new Depths();
        Double[][] asks=new Double[1][2];
        asks[0][0]=500654.27+x;
        asks[0][1]=0.5;
        Double[][] bids=new Double[1][2];
        bids[0][0]=5798.79+x;
        bids[0][1]=0.013;
        depths.setAsks(asks);
        depths.setBids(bids);

        lineData.setDepths(depths);
        kline.setData(lineData);
        x++;
        return kline;
    }*/
    /*
    {
  "success": true,
  "data": {
    "lines": [
      [
        1.50790476E12,
        99.30597249871,
        99.30597249871,
        99.30597249871,
        99.30597249871,
        66.9905449283
      ]
    ],
    "trades": [
      {
        "amount": 0.02,
        "price": 5798.79,
        "tid": 373015085,
        "time": 1508136949000,
        "type": "buy"
      }
    ],
    "depths": {
      "asks": [
        [
          500654.27,
          0.5
        ]
      ],
      "bids": [
        [
          5798.79,
          0.013
        ]
      ]
    }
  }
}
     */
}
