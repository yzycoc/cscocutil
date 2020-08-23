package com.yzycoc.cocutil;

import com.yzycoc.cocutil.service.ClanApiService;
import com.yzycoc.cocutil.service.JarvisApiService;
import com.yzycoc.cocutil.service.result.ClanResult;
import com.yzycoc.cocutil.util.enums.JarvisEnum;
import com.yzycoc.config.ConfigParameter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class CscocutilApplicationTests {
    @Autowired
    private ClanApiService service;

    @Autowired
    private JarvisApiService jarvisService;
    @Test
    void contextLoads() {
        //ClanResult imageClan = service.getImageClan("QJ9C0J9C");
        //ClanResult imageClan = service.getImagePlayer("9UJJPU2P");
        //ClanResult imageClan = service.getImageYq();
        //ClanResult imageClan = service.getImageClanAllCollectImage("PQP8UJCQ");
        for (int i = 0; i < ConfigParameter.filePath.length; i++) {
            File file = new File(ConfigParameter.filePath[i]);
            if(!file.exists()){//如果文件夹不存在
                file.mkdirs();//创建文件夹
            }
        }

        ClanResult imageClan = jarvisService.getClanwar("QLUQVLPJ", JarvisEnum.clanwar);
        System.out.println(imageClan.toString());
    }
}
