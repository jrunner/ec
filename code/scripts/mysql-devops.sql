/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/11/23 15:22:11                          */
/*==============================================================*/


drop table if exists devops_account;

drop table if exists devops_issue;

drop table if exists devops_menu;

drop table if exists devops_pub_history;

/*==============================================================*/
/* Table: devops_account                                        */
/*==============================================================*/
create table devops_account
(
   id                   int not null auto_increment comment 'ID',
   name                 varchar(20) not null comment '用户名',
   password             varchar(40) not null comment '密码',
   email                varchar(40) not null comment '邮箱',
   qq                   varchar(20) not null comment 'QQ',
   mobile               varchar(11) not null comment '手机号',
   real_name            varchar(64) not null comment '姓名',
   can_use              tinyint(4) not null default "2" comment '1:未启用,2:已启用',
   create_time          datetime not null comment '创建时间',
   update_time          timestamp not null comment '更新时间',
   salt                 varchar(10) not null comment '盐',
   primary key (id)
);

alter table devops_account comment '用户表';

/*==============================================================*/
/* Table: devops_issue                                          */
/*==============================================================*/
create table devops_issue
(
   id                   int not null auto_increment comment 'ID',
   title                varchar(64) not null comment '标题',
   bug_level            tinyint not null comment 'Bug级别',
   deal_time            datetime not null comment '问题处理时间',
   deal_owner           varchar(64) not null comment '处理人',
   description          varchar(1024) not null comment '功能描述',
   solution             varchar(1024) not null comment '解决方案',
   can_use              tinyint(4) not null default "2" comment '1:未启用,2:已启用',
   create_time          datetime not null comment '创建时间',
   update_time          timestamp not null comment '更新时间',
   primary key (id)
);

alter table devops_issue comment '问题情况表';

/*==============================================================*/
/* Table: devops_menu                                           */
/*==============================================================*/
create table devops_menu
(
   id                   int not null auto_increment comment 'ID',
   pid                  int not null comment '父ID',
   name                 varchar(64) not null comment '名称',
   url                  varchar(255) not null default "" comment 'URL',
   description          varchar(255) not null comment '描述',
   type                 int not null comment '1: 菜单, 2:url',
   order_nun            int not null comment '数组越大排序靠前',
   can_use              tinyint(4) not null default "2" comment '1:未启用,2:已启用',
   create_time          datetime not null comment '创建时间',
   update_time          timestamp not null comment '修改时间',
   primary key (id)
);

alter table devops_menu comment '菜单表';

/*==============================================================*/
/* Table: devops_pub_history                                    */
/*==============================================================*/
create table devops_pub_history
(
   id                   int not null auto_increment comment 'ID',
   pub_time             datetime not null comment '发布时间',
   description          varchar(1024) not null comment '功能描述',
   status               tinyint(4) not null comment '1:未发布,2:已发布',
   app                  varchar(30) not null comment '应用（如hotel_main、hotel_job）',
   can_use              tinyint(4) not null default "2" comment '1:未启用,2:已启用',
   create_time          datetime not null comment '创建时间',
   update_time          timestamp not null comment '更新时间',
   primary key (id)
);

alter table devops_pub_history comment '发版情况表';

