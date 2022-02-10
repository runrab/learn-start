package com.runrab.gmall.mock.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.runrab.gmall.mock.db.model.OrderRefundInfo;

/**
 * <p>
 * 退单表 服务类
 * </p>
 *
 * @author runrab
 * @since 2020-01-10
 */
public interface OrderRefundInfoService extends IService<OrderRefundInfo> {

    void  genRefundsOrFinish(Boolean ifClear);
}
