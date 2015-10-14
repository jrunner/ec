/*创建数据库语句*/
CREATE Database If Not Exists mybatis Character Set UTF8;
use mybatis;

/*创建数据表语句*/
DROP TABLE IF EXISTS `mybatis_user`;
CREATE TABLE `mybatis_user` (
  `id` VARCHAR(128) NOT NULL UNIQUE,
  `name` VARCHAR(128) NOT NULL,
  `password` VARCHAR(1280) NOT NULL,
  `sex` TINYINT(1) NOT NULL,
  `birthday` DATETIME NOT NULL,
  `create_time` DATETIME NOT NULL,
  `user_type` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `mybatis_user_say`;
CREATE TABLE `mybatis_user_say` (
  `id` VARCHAR(128) NOT NULL UNIQUE,
  `user` VARCHAR(128) NOT NULL,
  `say` VARCHAR(1280) NOT NULL,
  `create_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

