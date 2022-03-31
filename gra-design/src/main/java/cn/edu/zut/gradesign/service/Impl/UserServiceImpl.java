package cn.edu.zut.gradesign.service.Impl;
import cn.edu.zut.gradesign.bean.common.Pager;
import cn.edu.zut.gradesign.bean.user.User;
import cn.edu.zut.gradesign.common.BaseDao;
import cn.edu.zut.gradesign.common.BaseServiceImpl;
import cn.edu.zut.gradesign.dao.user.UserDAO;
import cn.edu.zut.gradesign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    private UserDAO dao;
    @Override
    public BaseDao<User> getBaseDao() {
        return dao;
    }
}
