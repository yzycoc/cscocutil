package com.yzycoc.cocutil.SQLMy.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzycoc.cocutil.SQLAll.bean.csuser.CsUser;
import com.yzycoc.cocutil.SQLAll.service.CsUserService;
import com.yzycoc.cocutil.SQLMy.bean.MyCsUser;
import com.yzycoc.cocutil.SQLMy.mapper.MyCsUserMapper;
import com.yzycoc.cocutil.SQLMy.service.MyCsUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "MyCsUserService")
@Primary
public class IMyCsUserImpl extends ServiceImpl<MyCsUserMapper, MyCsUser> implements MyCsUserService {
    @Autowired
    private CsUserService csUserService;

    @Transactional
    @Override
    public Boolean Synchronization() {
        try {
            List<CsUser> list = csUserService.list();
            if(list.size() > 0){
                QueryWrapper qw = new QueryWrapper();
                qw.eq("1","1");
                this.remove(qw);
                for (CsUser csUserVip : list) {
                    MyCsUser myCsUserVip = new MyCsUser();
                    BeanUtils.copyProperties(csUserVip, myCsUserVip);
                    myCsUserVip.setId(csUserVip.getId());
                    this.saveOrUpdate(myCsUserVip);
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
