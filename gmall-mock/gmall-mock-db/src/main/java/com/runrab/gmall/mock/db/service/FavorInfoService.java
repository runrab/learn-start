package com.runrab.gmall.mock.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.runrab.gmall.mock.db.model.FavorInfo;

/**
 * <p>
 * 商品收藏表 服务类
 * </p>
 *
 * @author runrab
 * @since 2020-01-10
 */
public interface FavorInfoService extends IService<FavorInfo> {

    public void  genFavors(Boolean ifClear);

}
