package com.runrab.gmall.mock.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.runrab.gmall.mock.db.mapper.CouponInfoMapper;
import com.runrab.gmall.mock.db.model.CouponInfo;
import com.runrab.gmall.mock.db.service.CouponInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠券表 服务实现类
 * </p>
 *
 * @author runrab
 * @since 2020-01-10
 */
@Service
public class CouponInfoServiceImpl extends ServiceImpl<CouponInfoMapper, CouponInfo> implements CouponInfoService {

}
