package org.ktrade.kline.kline.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.ktrade.kline.kline.TradingView.TradingViewDB;
import org.ktrade.kline.kline.TradingView.TradingViewDataBean;
import org.ktrade.kline.kline.TradingView.TradingViewHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ktrade.kline.kline.TradingView.TradingViewConfig;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("tradingView")
public class TradingViewDataController {
    private static final Logger logger = LoggerFactory.getLogger(TradingViewDataController.class);
    private Gson gson = new GsonBuilder().create();

   /* @RequestMapping("/")
    public String index() {
        return "datafeed is ok";
    }*/



    @RequestMapping("/config")
    public String config() {
        return "{\"supports_search\":true,\"supports_group_request\":false,\"supports_marks\":false,\"supports_timescale_marks\":false,\"supports_time\":true}";
    }

    @RequestMapping("/symbol_info")
    public String symbol_info(String group) {
        return "";
    }

    @RequestMapping("/symbols")
    public String symbols(String symbol) {
        //{"name":"AAPL","exchange-traded":"NasdaqNM","exchange-listed":"NasdaqNM","timezone":"America/New_York","minmov":1,"minmov2":0,"pointvalue":1,"session":"0930-1630","has_intraday":false,"has_no_volume":false,"description":"Apple Inc.","type":"stock","supported_resolutions":["D","2D","3D","W","3W","M","6M"],"pricescale":100,"ticker":"AAPL"}
        //{"name":"AAPL","exchange-traded":"NasdaqNM","exchange-listed":"NasdaqNM","timezone":"Asia/Shanghai","minmov":1,"minmov2":0,"pointvalue":1,"session":"0930-1630","has_intraday":false,"has_no_volume":false,"description":"Apple Inc.","type":"stock","supported_resolutions":["D","2D","3D","W","3W","M","6M"],"pricescale":100,"ticker":"AAPL"}
        //return "{\"has_empty_bars\":true,\"has_intraday\":true,\"timezone\":\"Asia/Shanghai\",\"minmov\":1,\"minmov2\":0,\"pointvalue\":1,\"session\":\"24x7\",\"description\":\""+symbol+"\",\"type\":\"stock\",\"supported_resolutions\":[\"1\", \"15\", \"30\", \"60\", \"D\", \"2D\", \"3D\", \"W\"],\"pricescale\":100,\"ticker\":\""+symbol+"\"}";
        return "{\"supported_resolutions\":[\"1\", \"5\", \"15\", \"30\", \"60\",\"240\",\"D\", \"W\",\"M\"],\"volume_precision\":2,\"ticker\":\""+symbol+"\",\"description\":\""+symbol+"\",\"pricescale\":100,\"has_empty_bars\":true,\"has_intraday\":true,\"timezone\":\"Asia/Taipei\",\"minmov\":1,\"minmov2\":0,\"pointvalue\":1,\"session\":\"24x7\",\"type\":\"stock\"}";
    }

    @RequestMapping("/search")
    public String search(String query,String type,String exchange,Integer limit) {
        ///search?query=AA&type=stock&exchange=NYSE&limit=15
        return "";
    }

    @RequestMapping("/history")
    public TradingViewHistory history(String symbol,String resolution,Long from,Long to) {

        List<TradingViewDataBean> search = TradingViewDB.search(from, to);
        TradingViewHistory convert = TradingViewDB.convert(search);
        return convert;
    }

    @RequestMapping("/marks")
    public String marks(String symbol,String resolution,Long from,Long to) {
        //?symbol=<ticker_name>&from=<unix_timestamp>&to=<unix_timestamp>&resolution=<resolution>

        return "";
    }

    @RequestMapping("/timescale_marks")
    public String timescale_marks(String symbol,String resolution,Long from,Long to) {
        //?symbol=<ticker_name>&from=<unix_timestamp>&to=<unix_timestamp>&resolution=<resolution>

        return "";
    }

    @RequestMapping("/time")
    public Long time() {
        Long cur= Calendar.getInstance().getTimeInMillis()/1000;
        return cur;
    }

    @RequestMapping("/quotes")
    public String quotes(String symbol) {
        //GET /quotes?symbols=NYSE%3AAA%2CNYSE%3AF%2CNasdaqNM%3AAAPL
        return "";
    }

}
