CREATE TABLE `cs_user_vip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `end_time` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `eternity` bit(1) DEFAULT NULL,
  `number` decimal(19,2) DEFAULT NULL,
  `qqnumber` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `vip_member` int(11) DEFAULT NULL,
  `group_eternity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;