CREATE TABLE `coc_binding` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `msg` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `qqcode` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `tag` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=993 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


--
CREATE TABLE `configuration` (
  `id` int(11) NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `con` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;



CREATE TABLE `illicitdrug` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `msg` varchar(5000) COLLATE utf8mb4_bin DEFAULT NULL,
  `reply` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


CREATE TABLE `yq` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `msg` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `yuqing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cn_bh` varchar(255) DEFAULT NULL,
  `cn_kgj` varchar(255) DEFAULT NULL,
  `cn_kgj_bh` varchar(255) DEFAULT NULL,
  `cn_l_x_bh` varchar(255) DEFAULT NULL,
  `cn_lx` varchar(255) DEFAULT NULL,
  `cn_yhd` varchar(255) DEFAULT NULL,
  `cn_yhd_bh` varchar(255) DEFAULT NULL,
  `cn_zlp` varchar(255) DEFAULT NULL,
  `cn_zx` varchar(255) DEFAULT NULL,
  `cn_zxbh` varchar(255) DEFAULT NULL,
  `jy` varchar(255) DEFAULT NULL,
  `pf` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `ts1` varchar(255) DEFAULT NULL,
  `ts2` varchar(255) DEFAULT NULL,
  `ts3` varchar(255) DEFAULT NULL,
  `word_bh` varchar(255) DEFAULT NULL,
  `word_hd` varchar(255) DEFAULT NULL,
  `word_hd_bh` varchar(255) DEFAULT NULL,
  `word_kgj` varchar(255) DEFAULT NULL,
  `word_kgj_bh` varchar(255) DEFAULT NULL,
  `word_lx` varchar(255) DEFAULT NULL,
  `word_lx_b_h` varchar(255) DEFAULT NULL,
  `word_zlp` varchar(255) DEFAULT NULL,
  `word_zx` varchar(255) DEFAULT NULL,
  `word_zx_bh` varchar(255) DEFAULT NULL,
  `yj` varchar(255) DEFAULT NULL,
  `html` mediumtext,
  `htmltime` varbinary(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;