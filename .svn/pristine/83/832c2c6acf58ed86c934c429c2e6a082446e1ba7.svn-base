package org.ktrade.kline.kline.trade;

import java.math.BigDecimal;

public class Trade {

    private BigDecimal price;  //价格
    private BigDecimal amount; //数量
    private Long sortNum;     //交易ID，用于排序
    private Integer type;   //类型 1：buy 2：sell
    private String symbol;  //交易对名称

    public Trade() {

    }

    public Trade(BigDecimal price, BigDecimal amount, Long sortNum, Integer type, String symbol) {
        this.price = price;
        this.amount = amount;
        this.sortNum = sortNum;
        this.type = type;
        this.symbol = symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getSortNum() {
        return sortNum;
    }

    public void setSortNum(Long sortNum) {
        this.sortNum = sortNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "price=" + price +
                ", sortNum=" + sortNum +
                ", amount=" + amount +
                ", type=" + type +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
