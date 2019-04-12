package org.ktrade.kline.kline.dao;


import org.apache.ibatis.annotations.Param;
import org.ktrade.kline.kline.bean.entity.KLineData;

import java.util.Date;
import java.util.List;

public interface KLineDataDao {
	List<KLineData> getMin1ByPrevTradeTimeAndLimit(@Param("prevTradeTime")Long prevTradeTime, @Param("limit")Integer limit);
	List<KLineData> getMin1BySinceAndPrevTradeTime(@Param("since")Long since,@Param("prevTradeTime")Long prevTradeTime);

}
