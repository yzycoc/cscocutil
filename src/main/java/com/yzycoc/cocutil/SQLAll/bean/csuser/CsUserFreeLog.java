package com.yzycoc.cocutil.SQLAll.bean.csuser;

import com.yzycoc.cocutil.SQLAll.bean.BaseEntity;
import lombok.Data;

@Data
public class CsUserFreeLog extends BaseEntity<Integer> {

    private String groupNumber;

    private String userNumber;
}
