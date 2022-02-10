package com.runrab.gmall.mock.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.runrab.gmall.mock.db.model.CommentInfo;

/**
 * <p>
 * 商品评论表 服务类
 * </p>
 *
 * @author runrab
 * @since 2020-01-10
 */
public interface CommentInfoService extends IService<CommentInfo> {

    public  void genComments(Boolean ifClear);

}
