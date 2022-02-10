package com.runrab.gmall.mock.db.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.runrab.gmall.mock.db.model.OrderInfo;

import java.util.List;

/**
 * <p>
 * 订单表 订单表 服务类
 * </p>
 *
 * @author runrab
 * @since 2020-01-10
 */
public interface OrderInfoService extends IService<OrderInfo> {
    void genOrderInfos(boolean ifClear);

    void updateOrderStatus(List<OrderInfo> orderInfoList);

    List<OrderInfo> listWithDetail(Wrapper<OrderInfo> queryWrapper);

    List<OrderInfo> listWithDetail(Wrapper<OrderInfo> queryWrapper, Boolean withSkuInfo);


}
