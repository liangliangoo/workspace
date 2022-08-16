-- 在用H2 做测试案例的时候，一定要注意SQL文件规范，不然会执行失败
drop table if exists t_user;
create table if not exists t_user
(
    id          int auto_increment,
    name        varchar(64)  null comment '用户命名',
    age         int          null comment '年龄',
    phone       varchar(32)  null comment '手机号',
    address     varchar(320) null comment '用户地址',
    create_time datetime     null comment '创建时间',
    update_time datetime     not null comment '用户更新数据时间',
    constraint user_pk
        primary key (id)
);