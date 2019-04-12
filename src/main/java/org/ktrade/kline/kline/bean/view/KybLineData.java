package org.ktrade.kline.kline.bean.view;

public class KybLineData {
    Boolean success;
    LineData data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public LineData getData() {
        return data;
    }

    public void setData(LineData data) {
        this.data = data;
    }
}
