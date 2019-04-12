package org.ktrade.kline.kline.TradingView;

import java.math.BigDecimal;
import java.util.List;

public class TradingViewHistory {
    private String s;       //ok | error | no_data
    private String errmsg;  //error message. Should be present just if s = 'error'
    private List<Long> t;   //bar time. unix timestamp (UTC)
    private List<BigDecimal> c;  //close price
    private List<BigDecimal> o;  //open price (optional)
    private List<BigDecimal> h;  // high price (optional)
    private List<BigDecimal> l;  //low price (optional)
    private List<BigDecimal> v;  // volume (optional)
    private Long nextTime;      //time of the next bar if there is no data (status code is no_data) in the requested period (optional)

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<Long> getT() {
        return t;
    }

    public void setT(List<Long> t) {
        this.t = t;
    }

    public List<BigDecimal> getC() {
        return c;
    }

    public void setC(List<BigDecimal> c) {
        this.c = c;
    }

    public List<BigDecimal> getO() {
        return o;
    }

    public void setO(List<BigDecimal> o) {
        this.o = o;
    }

    public List<BigDecimal> getH() {
        return h;
    }

    public void setH(List<BigDecimal> h) {
        this.h = h;
    }

    public List<BigDecimal> getL() {
        return l;
    }

    public void setL(List<BigDecimal> l) {
        this.l = l;
    }

    public List<BigDecimal> getV() {
        return v;
    }

    public void setV(List<BigDecimal> v) {
        this.v = v;
    }

    public Long getNextTime() {
        return nextTime;
    }

    public void setNextTime(Long nextTime) {
        this.nextTime = nextTime;
    }
}
