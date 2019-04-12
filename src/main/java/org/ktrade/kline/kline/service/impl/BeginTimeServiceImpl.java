package org.ktrade.kline.kline.service.impl;


import org.ktrade.kline.kline.bean.entity.BeginTime;
import org.ktrade.kline.kline.dao.BeginTimeDao;
import org.ktrade.kline.kline.service.BeginTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeginTimeServiceImpl implements BeginTimeService {

	@Autowired
	private BeginTimeDao beginTimeDao;
	
	@Override
	public BeginTime getTime() {
		return beginTimeDao.getTime();
	}



}
