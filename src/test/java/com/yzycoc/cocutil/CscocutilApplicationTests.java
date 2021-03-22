package com.yzycoc.cocutil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzycoc.cocutil.SQLAll.bean.PublicLexicon;
import com.yzycoc.cocutil.SQLAll.bean.xjpublic.CocBinding;
import com.yzycoc.cocutil.SQLAll.service.CocBindingService;
import com.yzycoc.cocutil.SQLAll.service.PublicLexiconService;
import com.yzycoc.cocutil.SQLMy.bean.MyLexicon;
import com.yzycoc.cocutil.SQLMy.service.MyLexiconService;
import com.yzycoc.cocutil.service.ClanApiService;
import com.yzycoc.cocutil.service.JarvisApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CscocutilApplicationTests {
    @Autowired
    private ClanApiService service;
    @Autowired
    private PublicLexiconService publicLexicon;
    @Autowired
    private JarvisApiService jarvisService;
    @Autowired
    private CocBindingService cocBindingService;

    @Test
    void contextLoads() {


        //ClanResult imageClan = service.getImageClan("QJ9C0J9C");
        //ClanResult imageClan = service.getImagePlayer("9UJJPU2P");
        //ClanResult imageClan = service.getImageYq();
        //ClanResult imageClan = service.getImageClanAllCollectImage("PQP8UJCQ");
       /* for (int i = 0; i < ConfigParameter.filePath.length; i++) {
            File file = new File(ConfigParameter.filePath[i]);
            if(!file.exists()){//如果文件夹不存在
                file.mkdirs();//创建文件夹
            }
        }*/

       /* ClanResult imageClan = jarvisService.getClanwar("QLUQVLPJ", JarvisEnum.clanwar);
        System.out.println(imageClan.toString());*/
    }
}
