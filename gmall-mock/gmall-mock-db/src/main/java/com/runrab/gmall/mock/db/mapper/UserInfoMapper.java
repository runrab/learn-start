package com.runrab.gmall.mock.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.runrab.gmall.mock.db.model.UserInfo;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author runrab
 * @since 2020-01-10
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Update("truncate table user_info")
    public  void truncateUserInfo();

}
