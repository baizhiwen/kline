package org.ktrade.kline.kline;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ktrade.kline.kline.bean.entity.BeginTime;
import org.ktrade.kline.kline.bean.entity.KLineData;
import org.ktrade.kline.kline.dao.KLineDataDao;
import org.ktrade.kline.kline.service.BeginTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KlineDataTests {
	private static final Logger logger = LoggerFactory.getLogger(KlineDataTests.class);
	@Autowired
	private KLineDataDao kLineDataDao;
	@Test
	public void getMin1ByPrevTradeTimeAndLimit() {
		List<KLineData> klineDataList = kLineDataDao.getMin1ByPrevTradeTimeAndLimit(1510924620000L, 100);
		for(KLineData data:klineDataList) {
			logger.info(data.toString());
		}
	}

}
