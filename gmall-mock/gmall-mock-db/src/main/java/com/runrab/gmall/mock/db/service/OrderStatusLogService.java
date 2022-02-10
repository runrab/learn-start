package com.runrab.gmall.mock.db.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.runrab.gmall.mock.db.model.OrderInfo;
import com.runrab.gmall.mock.db.model.OrderStatusLog;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author runrab
 * @since 2020-01-10
 */
public interface OrderStatusLogService extends IService<OrderStatusLog> {
    public void  genOrderStatusLog(List<OrderInfo> orderInfoList);

}
