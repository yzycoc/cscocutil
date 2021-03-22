//调取自己的配置文件数据酷
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


CREATE TABLE `cs_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `group_number` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `perpetual` bit(1) DEFAULT NULL,
  `robot_number` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=651 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `lexicon` (
  `id` int(11) NOT NULL auto_increment,
  `create_date` varchar(255) collate utf8_esperanto_ci default NULL,
  `create_name` varchar(255) collate utf8_esperanto_ci default NULL,
  `antistop` varchar(255) collate utf8_esperanto_ci default NULL,
  `code` varchar(255) collate utf8_esperanto_ci default NULL,
  `number` varchar(255) collate utf8_esperanto_ci default NULL,
  `response` varchar(20000) collate utf8_esperanto_ci default NULL,
  `type` varchar(255) collate utf8_esperanto_ci default NULL,
  `isuser` bit(1) default NULL,
  `qunnumber` varchar(255) collate utf8_esperanto_ci default NULL,
  `sum` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_esperanto_ci;

CREATE TABLE `open_layout` (
  `id` int(11) NOT NULL auto_increment,
  `create_date` varchar(255) collate utf8_bin default NULL,
  `create_name` varchar(255) collate utf8_bin default NULL,
  `image_url` varchar(255) collate utf8_bin default NULL,
  `number` varchar(255) collate utf8_bin default NULL,
  `remark` varchar(255) collate utf8_bin default NULL,
  `type` varchar(255) collate utf8_bin default NULL,
  `url` varchar(255) collate utf8_bin default NULL,
  `dldate` varchar(255) collate utf8_bin default NULL,
  `dlurl` varchar(255) collate utf8_bin default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `qq_code` (
  `id` int(11) NOT NULL auto_increment,
  `qqcode` varchar(255) default NULL,
  `sum` int(255) default NULL,
  `date` varchar(255) default NULL,
  `stringdate` varchar(255) default NULL,
  `jysum` int(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `qq_robot_set` (
  `id` int(11) NOT NULL auto_increment,
  `create_date` varchar(255) default NULL,
  `create_name` varchar(255) default NULL,
  `cq_path` varchar(255) default NULL,
  `encode` varchar(255) default NULL,
  `link_ip` varchar(255) default NULL,
  `localqqcode` varchar(255) default NULL,
  `localqqnick` varchar(255) default NULL,
  `java_port` varchar(255) default NULL,
  `server_path` varchar(255) default NULL,
  `server_port` varchar(255) default NULL,
  `backlog` int(11) default NULL,
  `api` varchar(2000) default NULL,
  `administrator` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;