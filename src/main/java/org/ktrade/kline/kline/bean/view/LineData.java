package org.ktrade.kline.kline.bean.view;

import java.util.List;

public class LineData {
    List<Object[]> lines;
    Trades[] trades;
    Depths depths;

    public List<Object[]> getLines() {
        return lines;
    }

    public void setLines(List<Object[]> lines) {
        this.lines = lines;
    }

    public Trades[] getTrades() {
        return trades;
    }

    public void setTrades(Trades[] trades) {
        this.trades = trades;
    }

    public Depths getDepths() {
        return depths;
    }

    public void setDepths(Depths depths) {
        this.depths = depths;
    }
}
