CREATE DATABASE api_bridge
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;

use api_bridge;
CREATE TABLE user
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY comment '主键',
    name        varchar(50)  NOT NULL comment '用户名',
    email       varchar(100) NOT NULL comment '用户邮箱',
    password    varchar(100) NOT NULL comment '用户密码',
    create_time datetime     not null comment '创建时间',
    edit_time   datetime     not null comment '编辑时间',
    creator     varchar(50)  not null comment '创建人',
    editor      varchar(50)  not null comment '修改人',
    UNIQUE INDEX user_email (email)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='用户';

CREATE TABLE project
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY comment '主键',
    name        varchar(100) NOT NULL comment '项目名',
    description varchar(255) NOT NULL comment '描述',
    create_time datetime     not null comment '创建时间',
    edit_time   datetime     not null comment '编辑时间',
    creator     varchar(50)  not null comment '创建人',
    editor      varchar(50)  not null comment '修改人',
    INDEX project_edit_time (edit_time)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='项目';

CREATE TABLE tag_group
(
    id          varchar(100) PRIMARY KEY comment '主键',
    project_id  BIGINT       not null comment '项目id|project.id',
    name        varchar(255) NOT NULL comment '标签名',
    create_time datetime     not null comment '创建时间',
    edit_time   datetime     not null comment '编辑时间',
    creator     varchar(50)  not null comment '创建人',
    editor      varchar(50)  not null comment '修改人',
    INDEX tag_group_project_id (project_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='接口标签组';


CREATE TABLE api_meta_date
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY comment '主键',
    tag_id      varchar(100) NOT NULL comment '组id|tag_group.id',
    meta_date   MEDIUMTEXT   NOT NULL comment 'api元数据',
    create_time datetime     not null comment '创建时间',
    edit_time   datetime     not null comment '编辑时间',
    creator     varchar(50)  not null comment '创建人',
    editor      varchar(50)  not null comment '修改人',
    INDEX api_meta_date_uuid (tag_id),
    INDEX api_meta_date_edit_time (edit_time)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='接口信息';