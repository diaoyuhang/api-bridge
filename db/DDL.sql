CREATE DATABASE api_bridge
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;

use api_bridge;
CREATE TABLE user
(
    id          INT AUTO_INCREMENT PRIMARY KEY comment '主键',
    name        varchar(50)  NOT NULL comment '用户名',
    email       varchar(100) NOT NULL comment '用户邮箱',
    password    varchar(100) NOT NULL comment '用户密码',
    create_time datetime     not null comment '创建时间',
    edit_time   datetime     not null comment '编辑时间',
    creator     varchar(50)  not null comment '创建人',
    editor      varchar(50)  not null comment '修改人',
    UNIQUE INDEX user_email (email)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;