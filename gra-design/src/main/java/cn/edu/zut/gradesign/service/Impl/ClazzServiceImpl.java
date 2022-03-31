package cn.edu.zut.gradesign.service.Impl;
import cn.edu.zut.gradesign.bean.user.Clazz;
import cn.edu.zut.gradesign.common.BaseDao;
import cn.edu.zut.gradesign.common.BaseServiceImpl;
import cn.edu.zut.gradesign.dao.user.ClazzDAO;
import cn.edu.zut.gradesign.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClazzServiceImpl extends BaseServiceImpl<Clazz> implements ClazzService {
    @Autowired
    private ClazzDAO dao;
    @Override
    public BaseDao<Clazz> getBaseDao() {
        return dao;
    }
}
