package com.yzycoc.cocutil.SQLAll.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzycoc.cocutil.SQLAll.bean.vip.CsUserVip;
import com.yzycoc.from.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @program: cscocutil
 * @description:
 * @author: yzy
 * @create: 2021-01-21 11:41
 * @Version 1.0
 **/
public interface CsUserVipService extends IService<CsUserVip> {


    VipApplyForResult applyFor(VipApplyFor vip);

    void resultOkImage(String uuid, HttpServletRequest request, HttpServletResponse response);

    List<Dom4jResult> dom4jXml(String xml, String robotNumber, String money);

    void resultMyScoreOkImage(String userNumber, HttpServletRequest request, HttpServletResponse response);

    StringBuffer getMyScore(String userNumber);
}
