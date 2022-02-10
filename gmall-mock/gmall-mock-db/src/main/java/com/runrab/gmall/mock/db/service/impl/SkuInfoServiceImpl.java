package com.runrab.gmall.mock.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.runrab.gmall.mock.db.mapper.SkuInfoMapper;
import com.runrab.gmall.mock.db.model.SkuInfo;
import com.runrab.gmall.mock.db.service.SkuInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 库存单元表 服务实现类
 * </p>
 *
 * @author runrab
 * @since 2020-01-10
 */
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements SkuInfoService {

}
