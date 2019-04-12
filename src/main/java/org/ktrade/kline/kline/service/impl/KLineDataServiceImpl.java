package org.ktrade.kline.kline.service.impl;

import org.ktrade.kline.kline.bean.entity.KLineData;
import org.ktrade.kline.kline.dao.KLineDataDao;
import org.ktrade.kline.kline.service.KLineDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class KLineDataServiceImpl implements KLineDataService {
    @Autowired
    private KLineDataDao kLineDataDao;
    @Override
    public List<KLineData> getMin1ByPrevTradeTimeAndLimit(Long prevTradeTime, Integer limit) {
        return kLineDataDao.getMin1ByPrevTradeTimeAndLimit(prevTradeTime,limit);
    }

    @Override
    public List<KLineData> getMin1BySinceAndPrevTradeTime(Long since, Long prevTradeTime) {
        return kLineDataDao.getMin1BySinceAndPrevTradeTime(since,prevTradeTime);
    }


}
