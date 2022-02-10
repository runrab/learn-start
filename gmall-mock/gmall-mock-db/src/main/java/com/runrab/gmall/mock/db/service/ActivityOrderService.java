package com.runrab.gmall.mock.db.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.runrab.gmall.mock.db.model.ActivityOrder;
import com.runrab.gmall.mock.db.model.OrderInfo;

import java.util.List;


/**
 * <p>
 * 活动与订单关联表 服务类
 * </p>
 *
 * @author runrab
 * @since 2020-01-10
 */
public interface ActivityOrderService extends IService<ActivityOrder> {



    public List<ActivityOrder> genActivityOrder(List<OrderInfo> orderInfoList, Boolean ifClear);

    public  void  saveActivityOrderList( List<ActivityOrder> activityOrderList);

}
