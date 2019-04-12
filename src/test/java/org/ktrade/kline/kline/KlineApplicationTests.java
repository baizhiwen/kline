package org.ktrade.kline.kline;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ktrade.kline.kline.bean.entity.BeginTime;
import org.ktrade.kline.kline.service.BeginTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KlineApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(KlineApplicationTests.class);
	@Autowired
	private BeginTimeService beginTimeService;
	@Test
	public void contextLoads() {
		BeginTime time = beginTimeService.getTime();
		logger.info(time.toString());
	}

}
