package com.runrab.gmall.mock.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.runrab.gmall.mock.db.model.PaymentInfo;

/**
 * <p>
 * 支付流水表 服务类
 * </p>
 *
 * @author runrab
 * @since 2020-01-10
 */
public interface PaymentInfoService extends IService<PaymentInfo> {
    void  genPayments(Boolean ifClear);
}
