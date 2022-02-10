package com.runrab.gmall.mock.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.runrab.gmall.mock.db.model.UserInfo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author runrab
 * @since 2020-01-10
 */
public interface UserInfoService extends IService<UserInfo> {

    void  genUserInfos(Boolean ifClear);

}
