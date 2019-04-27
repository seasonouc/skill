DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS kill_product;
DROP TABLE IF EXISTS kill_oder_list;

CREATE TABLE user
(
    `user_id`   VARCHAR(35) PRIMARY KEY COMMENT '用户id',
    `user_name` VARCHAR(100) NOT NULL COMMENT '用户名'
) engine = innodb,
  charset = 'utf8';

CREATE TABLE kill_product
(
    `request_id`     VARCHAR(64) PRIMARY KEY COMMENT 'request id',
    `kill_id`        BIGINT(64) AUTO_INCREMENT COMMENT '秒杀id',
    `product_id`     BIGINT(64)    NOT NULL COMMENT '商品id',
    `product_name`   VARCHAR(60)   NOT NULL COMMENT '商品名称',
    `product_price`  DECIMAL(8, 2) NOT NULL COMMENT '商品价格',
    `product_number` INT           NOT NULL COMMENT '商品数量',
    `start_time`     TIMESTAMP COMMENT '开始时间',
    `end_time`       TIMESTAMP COMMENT '结束时间',
    unique index `killid`(`kill_id`)
) engine = innodb,charset = 'utf8';

CREATE TABLE kill_oder_list
(
    `oder_id`    BIGINT(64) AUTO_INCREMENT PRIMARY KEY COMMENT '订单id',
    `user_id`    VARCHAR(30) COMMENT '订单用户id',
    `product_id` BIGINT(64) NOT NULL COMMENT '订单商品id',
    `number`     INT        NOT NULL COMMENT '商品数量',
    `pay_status` BOOLEAN DEFAULT FALSE COMMENT '支付状态',
    `oder_date`  DATE       NOT NULL COMMENT '下单时间'
) engine = innodb,
  charset = 'utf8';

