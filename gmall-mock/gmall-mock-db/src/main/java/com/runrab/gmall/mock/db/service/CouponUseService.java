package com.runrab.gmall.mock.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.runrab.gmall.mock.db.model.CouponUse;
import com.runrab.gmall.mock.db.model.OrderInfo;

import java.util.List;

/**
 * <p>
 * 优惠券领用表 服务类
 * </p>
 *
 * @author runrab
 * @since 2020-01-10
 */
public interface CouponUseService extends IService<CouponUse> {

    public void genCoupon(Boolean ifClear);

    public  void  usedCoupon(List<OrderInfo> orderInfoList);

    public List<CouponUse> usingCoupon(List<OrderInfo> orderInfoList);

    public  void  saveCouponUseList( List<CouponUse> couponUseList);


}
