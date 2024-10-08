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
    status      int          not null default 0 comment '状态|0-未激活，1-激活，2-停用',
    create_time datetime     not null comment '创建时间',
    edit_time   datetime     not null comment '编辑时间',
    creator     varchar(50)  not null comment '创建人',
    editor      varchar(50)  not null comment '修改人',
    rec_time    timestamp    not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '时间戳',
    UNIQUE INDEX user_email (email)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='用户';

CREATE TABLE project
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY comment '主键',
    name        varchar(100) NOT NULL comment '项目名',
    description varchar(255) NOT NULL comment '描述',
    status      int          not null default 1 comment '状态|1-正常，0-删除状态',
    create_time datetime     not null comment '创建时间',
    edit_time   datetime     not null comment '编辑时间',
    creator     varchar(50)  not null comment '创建人',
    editor      varchar(50)  not null comment '修改人',
    rec_time    timestamp    not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '时间戳',
    INDEX       project_edit_time (edit_time),
    INDEX       project_name ( name)
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
    rec_time    timestamp    not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '时间戳',
    INDEX tag_group_project_id (project_id),
    INDEX tag_group_create_time (create_time)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='接口标签组';


CREATE TABLE api_meta_date
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY comment '主键',
    tag_id      varchar(100) NOT NULL comment '组id|tag_group.id',
    path        varchar(255) not null comment '接口请求路径',
    method      varchar(20)  not null comment '请求方法|get post put delete',
    summary     varchar(255)          default '' comment '接口名',
    meta_date   MEDIUMTEXT   NOT NULL comment 'api元数据',
    create_time datetime     not null comment '创建时间',
    edit_time   datetime     not null comment '编辑时间',
    creator     varchar(50)  not null comment '创建人',
    editor      varchar(50)  not null comment '修改人',
    rec_time    timestamp    not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '时间戳',
    INDEX api_meta_date_edit_time (edit_time),
    INDEX api_meta_date_path (path),
    INDEX api_meta_date_summary (summary),
    unique INDEX api_meta_date_tag_id_path_method (tag_id, path, method)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='接口信息';


CREATE TABLE api_meta_date_history
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY comment '历史记录主键',
    api_id         BIGINT       not null comment '接口id|api_meta_date.id',
    tag_id         varchar(100) NOT NULL comment '组id|tag_group.id',
    path           varchar(255) not null comment '接口请求路径',
    method         varchar(20)  not null comment '请求方法|GET POST PUT DELETE',
    summary        varchar(255)          default '' comment '接口名',
    meta_date      MEDIUMTEXT   NOT NULL comment 'api元数据',
    operation_type int          not null comment '操作类型|1-新增，2-更新，3-删除',
    create_time    datetime     not null comment '创建时间',
    edit_time      datetime     not null comment '编辑时间',
    creator        varchar(50)  not null comment '创建人',
    editor         varchar(50)  not null comment '修改人',
    rec_time       timestamp    not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '时间戳',
    INDEX aapi_meta_date_history_uuid (tag_id),
    INDEX api_meta_date_history_edit_time (edit_time),
    INDEX api_meta_date_history_path (path)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='接口信息操作历史';

CREATE TABLE permission_path
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY comment '主键',
    project_id  BIGINT      NOT NULL comment '项目id',
    path_type   int         NOT NULL COMMENT '权限路径类型|0-权限修改,1-项目查看，2-项目编辑，3-项目删除，4-上传接口，5-接口删除',
    create_time datetime    not null comment '创建时间',
    edit_time   datetime    not null comment '编辑时间',
    creator     varchar(50) not null comment '创建人',
    editor      varchar(50) not null comment '修改人',
    rec_time    timestamp   not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '时间戳',
    INDEX project_edit_time (edit_time),
    INDEX project_project_id (project_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='项目权限路径';

CREATE TABLE user_permission
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY comment '主键',
    user_id       BIGINT      NOT NULL comment '用户id|user.id',
    permission_id BIGINT      NOT NULL COMMENT '权限id|permission_path.id',
    create_time   datetime    not null comment '创建时间',
    edit_time     datetime    not null comment '编辑时间',
    creator       varchar(50) not null comment '创建人',
    editor        varchar(50) not null comment '修改人',
    rec_time      timestamp   not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '时间戳',
    INDEX user_permission_edit_time (edit_time),
    INDEX user_permission_permission_id (permission_id),
    UNIQUE INDEX user_permission_userId_permissionId (user_id,permission_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='用户权限';

create table project_request_param
(
    id          bigint auto_increment comment '主键'
        primary key,
    project_id  bigint      not null comment '项目id|project.id',
    type        int         not null comment '参数类型|0-请求头Header参数，1-cookie参数',
    name  varchar(50) not null comment '参数名',
    required    tinyint(1)  not null default 0 comment '参数是否必填',
    create_time datetime    not null comment '创建时间',
    edit_time   datetime    not null comment '编辑时间',
    creator     varchar(50) not null comment '创建人',
    editor      varchar(50) not null comment '修改人',
    rec_time    timestamp            default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '时间戳'
)
    comment '项目请求参数配置' charset = utf8mb4;

create index project_request_param_project_id
    on project_request_param (project_id);

create table user_env_config
(
    id          bigint auto_increment comment '主键'
        primary key,
    user_id     bigint       not null comment '用户id|user.id',
    url         varchar(100) not null comment '请求url域名前缀',
    description varchar(50)  not null default '' comment '描述',
    create_time datetime     not null comment '创建时间',
    edit_time   datetime     not null comment '编辑时间',
    creator     varchar(50)  not null comment '创建人',
    editor      varchar(50)  not null comment '修改人',
    rec_time    timestamp             default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '时间戳'
)
    comment '用户环境配置' charset = utf8mb4;


create index user_env_config_user_id
    on user_env_config (user_id);