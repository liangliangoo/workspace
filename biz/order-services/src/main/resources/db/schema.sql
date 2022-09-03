create table trade_order_2c
(
    id               bigint auto_increment
        primary key,
    union_id         varchar(32)   not null,
    app_id           bigint        not null comment 'appId',
    user_id          bigint        not null comment '买家 uid',
    order_type       int           not null comment '订单类型',
    pay_type         int           not null comment '支付类型',
    order_price      bigint        not null comment '订单价格（原价）',
    order_pay_price  bigint        null comment '订单支付金额',
    coupon_id        bigint        null comment '优惠券ID',
    coupon_price     bigint        null comment '优惠金额',
    product_id       bigint        null comment '商品ID',
    status           varchar(20)   null comment '订单状态',
    create_time      datetime      null comment '创建时间',
    update_time      datetime      null comment '更新时间',
    product_snapshot varchar(1024) null comment '商品快照'
)
    comment '2C订单表' charset = utf8mb4;


-- auto-generated definition
create table trade_order_detail
(
    id               bigint auto_increment
        primary key,
    union_id         varchar(32)   not null,
    app_id           bigint        not null comment 'appId',
    user_id          bigint        not null comment '买家ID',
    order_type       int           not null comment '订单类型',
    order_id         bigint        not null comment '订单号',
    product_id       bigint        not null comment '商品ID',
    product_price    bigint        not null comment '商品价格',
    order_status     varchar(20)   null comment '订单状态',
    create_time      datetime      null comment '创建时间',
    update_time      datetime      null comment '更新时间',
    product_snapshot varchar(1024) not null comment '商品快照',
    num              int           not null comment '数量'
)
    comment '订单详情' charset = utf8mb4;