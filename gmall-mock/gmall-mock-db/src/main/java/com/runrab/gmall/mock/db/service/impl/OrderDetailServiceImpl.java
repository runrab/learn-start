package com.runrab.gmall.mock.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.runrab.gmall.mock.db.mapper.OrderDetailMapper;
import com.runrab.gmall.mock.db.model.OrderDetail;
import com.runrab.gmall.mock.db.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单明细表 服务实现类
 * </p>
 *
 * @author runrab
 * @since 2020-01-10
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}
