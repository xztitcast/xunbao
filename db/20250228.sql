CREATE TABLE "tb_order"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "serial_number" varchar(30) NOT NULL UNIQUE,
    "name" varchar(50) NOT NULL,
    "cycle" int4 NOT NULL DEFAULT 1,
    "status" smallint NOT NULL DEFAULT 1,
    "bonus" decimal(20, 2) NOT NULL DEFAULT 0.00,
    "has_bond" smallint NOT NULL DEFAULT 0,
    "bond" decimal(20, 2) NOT NULL DEFAULT 0.00,
    "publish_time" timestamp(6) DEFAULT now(),
    "develop" varchar(255) NOT NULL,
    "url" varchar(500) NOT NULL,
    "description" text,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now(),
    "creator" int8,
    "updater" int8,
    "create_name" varchar(20),
    "update_name" varchar(20)
);

COMMENT ON COLUMN "tb_order"."id" IS '主键id';
COMMENT ON COLUMN "tb_order"."serial_number" IS '编号';
COMMENT ON COLUMN "tb_order"."name" IS '任务名称';
COMMENT ON COLUMN "tb_order"."cycle" IS '周期';
COMMENT ON COLUMN "tb_order"."status" IS '后台状态0:审核失败 1:待审核 2:审核成功';
COMMENT ON COLUMN "tb_order"."bonus" IS '奖励';
COMMENT ON COLUMN "tb_order"."has_bond" IS '是否缴保证金0:否 1:是';
COMMENT ON COLUMN "tb_order"."bond" IS '保证金(保证金最大是预算的双倍)';
COMMENT ON COLUMN "tb_order"."publish_time" IS '发布时间';
COMMENT ON COLUMN "tb_order"."develop" IS '开发语言';
COMMENT ON COLUMN "tb_order"."url" IS '图片链接';
COMMENT ON COLUMN "tb_order"."description" IS '文案';
COMMENT ON COLUMN "tb_order"."creator" IS '创建人';
COMMENT ON COLUMN "tb_order"."create_name" IS '创建名称';
COMMENT ON COLUMN "tb_order"."created" IS '创建时间';
COMMENT ON COLUMN "tb_order"."updater" IS '更新人';
COMMENT ON COLUMN "tb_order"."update_name" IS '更新人名称';
COMMENT ON COLUMN "tb_order"."updated" IS '更新时间';
COMMENT ON TABLE "tb_order" IS '任务订单表';

CREATE TABLE "tb_order_work"(
    "id" int8 NOT NULL PRIMARY KEY,
    "serial_number" varchar(30) NOT NULL,
    "user_id" int8 NOT NULL,
    "username" varchar(50),
    "user_pic" varchar(255),
    "state" smallint NOT NULL DEFAULT 1,
    "bonus" decimal(20, 2) NOT NULL DEFAULT 0.00,
    "bond" decimal(20, 2) NOT NULL DEFAULT 0.00,
    "remark" varchar(255),
    "proof_url" text,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "tb_order_work"."id" IS '主键id';
COMMENT ON COLUMN "tb_order_work"."serial_number" IS '任务编号';
COMMENT ON COLUMN "tb_order_work"."user_id" IS '用户ID';
COMMENT ON COLUMN "tb_order_work"."username" IS '用户名称';
COMMENT ON COLUMN "tb_order_work"."user_pic" IS '用户头像';
COMMENT ON COLUMN "tb_order_work"."state" IS '状态0:无责取消 1:有责取消 2:进行中 3:待验收 4:已验收 5:已完成';
COMMENT ON COLUMN "tb_order_work"."bonus" IS '奖励(有可能多人同时进行一个订单)';
COMMENT ON COLUMN "tb_order_work"."bond" IS '接单用户保证金';
COMMENT ON COLUMN "tb_order_work"."remark" IS '备注(取消时添加备注)';
COMMENT ON COLUMN "tb_order_work"."proof_url" IS '证明(取消时添加证明)';
COMMENT ON COLUMN "tb_order_work"."created" IS '创建时间';
COMMENT ON COLUMN "tb_order_work"."updated" IS '更新时间';
COMMENT ON TABLE "tb_order_work" IS '订单任务表';

CREATE TABLE "tb_user_work"(
    "id" int8 NOT NULL PRIMARY KEY,
    "rate" decimal(5, 2) NOT NULL DEFAULT 0.7,
    "trade_amount" decimal(20, 2) NOT NULL DEFAULT 0.00,
    "score" int4 NOT NULL DEFAULT 0,
    "num0" int4 NOT NULL DEFAULT 0,
    "num1" int4 NOT NULL DEFAULT 0,
    "num2" int4 NOT NULL DEFAULT 0,
    "num3" int4 NOT NULL DEFAULT 0,
    "num4" int4 NOT NULL DEFAULT 0,
    "num5" int4 NOT NULL DEFAULT 0,
    "num6" int4 NOT NULL DEFAULT 0,
    "num7" int4 NOT NULL DEFAULT 0,
    "num8" int4 NOT NULL DEFAULT 0,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "tb_user_work"."id" IS '主键id(即用户ID)';
COMMENT ON COLUMN "tb_user_work"."rate" IS '分成比例';
COMMENT ON COLUMN "tb_user_work"."trade_amount" IS '交易量';
COMMENT ON COLUMN "tb_user_work"."score" IS '积分';
COMMENT ON COLUMN "tb_user_work"."num0" IS '信用分';
COMMENT ON COLUMN "tb_user_work"."num1" IS '进行中统计数';
COMMENT ON COLUMN "tb_user_work"."num2" IS '待验收统计数';
COMMENT ON COLUMN "tb_user_work"."num3" IS '已验收统计数';
COMMENT ON COLUMN "tb_user_work"."num4" IS '已完成统计数';
COMMENT ON COLUMN "tb_user_work"."num5" IS '有责取消统计数';
COMMENT ON COLUMN "tb_user_work"."num6" IS '无责取消统计数';
COMMENT ON COLUMN "tb_user_work"."num7" IS '购买源码统计数';
COMMENT ON COLUMN "tb_user_work"."num8" IS '销售源码统计数';
COMMENT ON COLUMN "tb_user_work"."created" IS '创建时间';
COMMENT ON COLUMN "tb_user_work"."updated" IS '更新时间';
COMMENT ON TABLE "tb_user_work" IS '用户工作台';

CREATE TABLE "tb_balance"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "user_id" int8 NOT NULL,
    "amount" decimal(20, 2) NOT NULL DEFAULT 0.00,
    "freeze" decimal(20, 2) NOT NULL DEFAULT 0.00,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);
COMMENT ON COLUMN "tb_balance"."id" IS '主键id';
COMMENT ON COLUMN "tb_balance"."user_id" IS '用户ID';
COMMENT ON COLUMN "tb_balance"."amount" IS '保证金余额';
COMMENT ON COLUMN "tb_balance"."freeze" IS '冻结金额';
COMMENT ON COLUMN "tb_balance"."created" IS '创建时间';
COMMENT ON COLUMN "tb_balance"."updated" IS '更新时间';
COMMENT ON TABLE "tb_balance" IS '保证金表';

CREATE TABLE "tb_balance_trx"(
    "id" int8 NOT NULL PRIMARY KEY,
    "order_flow_id" int8 NOT NULL,
    "user_id" int8 NOT NULL,
    "amount" decimal(20, 2) NOT NULL DEFAULT 0.00,
    "status" smallint NOT NULL DEFAULT 1,
    "cycle" int4 NOT NULL DEFAULT 7,
    "url" varchar(255),
    "video" varchar(255),
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "tb_balance_trx"."id" IS '主键id';
COMMENT ON COLUMN "tb_balance_trx"."order_flow_id" IS '订单流ID';
COMMENT ON COLUMN "tb_balance_trx"."user_id" IS '用户ID';
COMMENT ON COLUMN "tb_balance_trx"."amount" IS '扣除保证金';
COMMENT ON COLUMN "tb_balance_trx"."status" IS '状态1:待申诉 2:申诉成功 3:申诉失败';
COMMENT ON COLUMN "tb_balance_trx"."cycle" IS '申诉周期';
COMMENT ON COLUMN "tb_balance_trx"."url" IS '申诉图片';
COMMENT ON COLUMN "tb_balance_trx"."video" IS '申诉视屏';
COMMENT ON COLUMN "tb_balance_trx"."created" IS '创建时间';
COMMENT ON COLUMN "tb_balance_trx"."updated" IS '更新时间';
COMMENT ON TABLE "tb_balance_trx" IS '保证金划扣表';

CREATE TABLE "tb_activity"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "name" varchar(50) NOT NULL,
    "url" varchar(255) NOT NULL,
    "total" int4 NOT NULL DEFAULT 0,
    "surplus" int4 NOT NULL DEFAULT 0,
    "start_time" timestamp(6) DEFAULT now(),
    "end_time" timestamp(6) DEFAULT now(),
    "status" smallint NOT NULL DEFAULT 1,
    "description" text,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now(),
    "creator" int8,
    "updater" int8,
    "create_name" varchar(20),
    "update_name" varchar(20)
);

COMMENT ON COLUMN "tb_activity"."id" IS '主键id';
COMMENT ON COLUMN "tb_activity"."name" IS '活动名称';
COMMENT ON COLUMN "tb_activity"."url" IS '活动图片地址';
COMMENT ON COLUMN "tb_activity"."total" IS '总库存';
COMMENT ON COLUMN "tb_activity"."surplus" IS '剩余库存';
COMMENT ON COLUMN "tb_activity"."start_time" IS '活动开始时间';
COMMENT ON COLUMN "tb_activity"."end_time" IS '活动结束时间';
COMMENT ON COLUMN "tb_activity"."status" IS '状态0:暂停 1:下架 2:上架';
COMMENT ON COLUMN "tb_activity"."description" IS '活动描述';
COMMENT ON COLUMN "tb_activity"."creator" IS '创建人';
COMMENT ON COLUMN "tb_activity"."create_name" IS '创建名称';
COMMENT ON COLUMN "tb_activity"."created" IS '创建时间';
COMMENT ON COLUMN "tb_activity"."updater" IS '更新人';
COMMENT ON COLUMN "tb_activity"."update_name" IS '更新人名称';
COMMENT ON COLUMN "tb_activity"."updated" IS '更新时间';
COMMENT ON TABLE "tb_activity" IS '活动表';

CREATE TABLE "tb_activity_star_rule"(
    "id" int8 NOT NULL PRIMARY KEY,
    "star_id" varchar(50) NOT NULL,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);
COMMENT ON COLUMN "tb_activity_star_rule"."id" IS '主键id(即活动ID)';
COMMENT ON COLUMN "tb_activity_star_rule"."star_id" IS '星级级别(字符串数组存储)';
COMMENT ON COLUMN "tb_activity_star_rule"."created" IS '创建时间';
COMMENT ON COLUMN "tb_activity_star_rule"."updated" IS '更新时间';
COMMENT ON TABLE "tb_activity_star_rule" IS '活动星级规则表';

CREATE TABLE "tb_activity_user_rule"(
    "id" int8 NOT NULL PRIMARY KEY,
    "day" int4 NOT NULL DEFAULT 30,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);
COMMENT ON COLUMN "tb_activity_user_rule"."id" IS '主键id(即活动ID)';
COMMENT ON COLUMN "tb_activity_user_rule"."day" IS '新用户注册多少天内';
COMMENT ON COLUMN "tb_activity_user_rule"."created" IS '创建时间';
COMMENT ON COLUMN "tb_activity_user_rule"."updated" IS '更新时间';
COMMENT ON TABLE "tb_activity_user_rule" IS '活动用户规则表';

CREATE TABLE "tb_activity_cycle_rule"(
    "id" int8 NOT NULL PRIMARY KEY,
    "month" int2 NOT NULL DEFAULT 1,
    "week" int2 NOT NULL DEFAULT 1,
    "day" int2 NOT NULL DEFAULT 1,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "tb_activity_cycle_rule"."id" IS '主键id(即活动ID)';
COMMENT ON COLUMN "tb_activity_cycle_rule"."month" IS '每月参与次数';
COMMENT ON COLUMN "tb_activity_cycle_rule"."week" IS '每周参与次数';
COMMENT ON COLUMN "tb_activity_cycle_rule"."day" IS '每天参与次数';
COMMENT ON COLUMN "tb_activity_cycle_rule"."created" IS '创建时间';
COMMENT ON COLUMN "tb_activity_cycle_rule"."updated" IS '更新时间';
COMMENT ON TABLE "tb_activity_cycle_rule" IS '活动周期规则表';

CREATE TABLE "tb_reward"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "name" varchar(50) NOT NULL,
    "amount" decimal(20, 2) NOT NULL DEFAULT 0.00,
    "status" smallint NOT NULL DEFAULT 1,
    "expire" int4 NOT NULL DEFAULT 7,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now(),
    "creator" int8,
    "updater" int8,
    "create_name" varchar(20),
    "update_name" varchar(20)
);

COMMENT ON COLUMN "tb_reward"."id" IS '主键id';
COMMENT ON COLUMN "tb_reward"."name" IS '奖励名称';
COMMENT ON COLUMN "tb_reward"."amount" IS '奖励金额';
COMMENT ON COLUMN "tb_reward"."status" IS '状态0:暂停 1:上架 2:下架';
COMMENT ON COLUMN "tb_reward"."expire" IS '过期天数';
COMMENT ON COLUMN "tb_reward"."creator" IS '创建人';
COMMENT ON COLUMN "tb_reward"."create_name" IS '创建名称';
COMMENT ON COLUMN "tb_reward"."created" IS '创建时间';
COMMENT ON COLUMN "tb_reward"."updater" IS '更新人';
COMMENT ON COLUMN "tb_reward"."update_name" IS '更新人名称';
COMMENT ON COLUMN "tb_reward"."updated" IS '更新时间';
COMMENT ON TABLE "tb_reward" IS '奖励表';

CREATE TABLE "tb_activity_reward"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "activity_id" int8 NOT NULL,
    "reward_id" int8 NOT NULL,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "tb_activity_reward"."id" IS '主键id';
COMMENT ON COLUMN "tb_activity_reward"."activity_id" IS '活动ID';
COMMENT ON COLUMN "tb_activity_reward"."reward_id" IS '奖励ID';
COMMENT ON COLUMN "tb_activity_reward"."created" IS '创建时间';
COMMENT ON COLUMN "tb_activity_reward"."updated" IS '更新时间';
COMMENT ON TABLE "tb_activity_reward" IS '活动奖励表';

CREATE TABLE "tb_star"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "name" varchar(30) NOT NULL,
    "start_value" int4 NOT NULL DEFAULT 0,
    "end_value" int4 NOT NULL DEFAULT 0,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now(),
    "creator" int8,
    "updater" int8,
    "create_name" varchar(20),
    "update_name" varchar(20)
);

COMMENT ON COLUMN "tb_star"."id" IS '主键id';
COMMENT ON COLUMN "tb_star"."name" IS '星级名称 武林新秀、小有名气、渐入佳境、名动一方、武林豪侠、一代宗师、绝世高手';
COMMENT ON COLUMN "tb_star"."start_value" IS '起始值';
COMMENT ON COLUMN "tb_star"."end_value" IS '结束值';
COMMENT ON COLUMN "tb_star"."creator" IS '创建人';
COMMENT ON COLUMN "tb_star"."create_name" IS '创建名称';
COMMENT ON COLUMN "tb_star"."created" IS '创建时间';
COMMENT ON COLUMN "tb_star"."updater" IS '更新人';
COMMENT ON COLUMN "tb_star"."update_name" IS '更新人名称';
COMMENT ON COLUMN "tb_star"."updated" IS '更新时间';
COMMENT ON TABLE "tb_star" IS '星级表';