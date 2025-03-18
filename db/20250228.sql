DROP TABLE IF EXISTS "tb_order";
CREATE TABLE "tb_order"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "serial_number" varchar(30) NOT NULL UNIQUE,
    "name" varchar(50) NOT NULL,
    "label" varchar(100) NOT NULL,
    "cycle" int4 NOT NULL DEFAULT 1,
    "status" smallint NOT NULL DEFAULT 1,
    "type" smallint NOT NULL DEFAULT 1,
    "bonus" decimal(20, 2) NOT NULL DEFAULT 0.00,
    "bond" decimal(20, 2) NOT NULL DEFAULT 0.00,
    "publish_time" timestamp(6) DEFAULT now(),
    "develop" varchar(255) NOT NULL,
    "contact" varchar(255) NOT NULL,
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
COMMENT ON COLUMN "tb_order"."label" IS '标签';
COMMENT ON COLUMN "tb_order"."cycle" IS '周期';
COMMENT ON COLUMN "tb_order"."status" IS '后台状态0:审核失败 1:待审核 2:审核成功 3:待发布 4:已发布 5:进行中 6:已结束';
COMMENT ON COLUMN "tb_order"."type" IS '类型(1:需求 2:BUG)';
COMMENT ON COLUMN "tb_order"."bonus" IS '奖励';
COMMENT ON COLUMN "tb_order"."bond" IS '保证金(保证金最大是预算的双倍)';
COMMENT ON COLUMN "tb_order"."publish_time" IS '发布时间';
COMMENT ON COLUMN "tb_order"."develop" IS '开发语言';
COMMENT ON COLUMN "tb_order"."contact" IS '联系方式';
COMMENT ON COLUMN "tb_order"."url" IS '图片链接';
COMMENT ON COLUMN "tb_order"."description" IS '文案';
COMMENT ON COLUMN "tb_order"."creator" IS '创建人';
COMMENT ON COLUMN "tb_order"."create_name" IS '创建名称';
COMMENT ON COLUMN "tb_order"."created" IS '创建时间';
COMMENT ON COLUMN "tb_order"."updater" IS '更新人';
COMMENT ON COLUMN "tb_order"."update_name" IS '更新人名称';
COMMENT ON COLUMN "tb_order"."updated" IS '更新时间';
COMMENT ON TABLE "tb_order" IS '任务订单表';

DROP TABLE IF EXISTS "tb_order_work";
CREATE TABLE "tb_order_work"(
    "id" int8 NOT NULL PRIMARY KEY,
    "serial_number" varchar(30) NOT NULL,
    "user_id" int8 NOT NULL,
    "username" varchar(50),
    "user_pic" varchar(255),
    "status" smallint NOT NULL DEFAULT 1,
    "bonus" decimal(20, 2) NOT NULL DEFAULT 0.00,
    "bond" decimal(20, 2) NOT NULL DEFAULT 0.00,
    "remark" varchar(255),
    "proof_url" text,
    "creator" int8 NOT NULL,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "tb_order_work"."id" IS '主键id';
COMMENT ON COLUMN "tb_order_work"."serial_number" IS '任务编号';
COMMENT ON COLUMN "tb_order_work"."user_id" IS '用户ID';
COMMENT ON COLUMN "tb_order_work"."username" IS '用户名称';
COMMENT ON COLUMN "tb_order_work"."user_pic" IS '用户头像';
COMMENT ON COLUMN "tb_order_work"."status" IS '状态0:无责取消 1:有责取消 2:进行中 3:待验收 4:已验收 5:已完成';
COMMENT ON COLUMN "tb_order_work"."bonus" IS '奖励(有可能多人同时进行一个订单)';
COMMENT ON COLUMN "tb_order_work"."bond" IS '接单用户保证金';
COMMENT ON COLUMN "tb_order_work"."remark" IS '备注(取消时添加备注)';
COMMENT ON COLUMN "tb_order_work"."proof_url" IS '证明(取消时添加证明)';
COMMENT ON COLUMN "tb_order_work"."creator" IS '创建人(前端在新增记录时候直接取tb_order中值)';
COMMENT ON COLUMN "tb_order_work"."created" IS '创建时间';
COMMENT ON COLUMN "tb_order_work"."updated" IS '更新时间';
COMMENT ON TABLE "tb_order_work" IS '订单任务表';

DROP TABLE IF EXISTS "tb_user_work";
CREATE TABLE "tb_user_work"(
    "id" int8 NOT NULL PRIMARY KEY,
    "username" varchar(50),
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
COMMENT ON COLUMN "tb_user_work"."username" IS '用户名';
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

DROP TABLE IF EXISTS "tb_balance";
CREATE TABLE "tb_balance"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "user_id" int8 NOT NULL,
    "username" varchar(50),
    "amount" decimal(20, 2) NOT NULL DEFAULT 0.00,
    "freeze" decimal(20, 2) NOT NULL DEFAULT 0.00,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);
COMMENT ON COLUMN "tb_balance"."id" IS '主键id';
COMMENT ON COLUMN "tb_balance"."user_id" IS '用户ID';
COMMENT ON COLUMN "tb_balance"."username" IS '用户名称';
COMMENT ON COLUMN "tb_balance"."amount" IS '保证金余额';
COMMENT ON COLUMN "tb_balance"."freeze" IS '冻结金额';
COMMENT ON COLUMN "tb_balance"."created" IS '创建时间';
COMMENT ON COLUMN "tb_balance"."updated" IS '更新时间';
COMMENT ON TABLE "tb_balance" IS '保证金表';

DROP TABLE IF EXISTS "tb_balance_trx";
CREATE TABLE "tb_balance_trx"(
    "id" int8 NOT NULL PRIMARY KEY,
    "order_work_id" int8 NOT NULL,
    "serial_number" varchar(30) NOT NULL,
    "user_id" int8 NOT NULL,
    "username" varchar(50),
    "amount" decimal(20, 2) NOT NULL DEFAULT 0.00,
    "status" smallint NOT NULL DEFAULT 1,
    "cycle" int4 NOT NULL DEFAULT 7,
    "url" varchar(255),
    "video" varchar(255),
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "tb_balance_trx"."id" IS '主键id';
COMMENT ON COLUMN "tb_balance_trx"."order_work_id" IS '订单任务ID';
COMMENT ON COLUMN "tb_balance_trx"."serial_number" IS '订单编号(冗余用于前端显示)';
COMMENT ON COLUMN "tb_balance_trx"."user_id" IS '用户ID';
COMMENT ON COLUMN "tb_balance_trx"."username" IS '用户名称(冗余字段)';
COMMENT ON COLUMN "tb_balance_trx"."amount" IS '扣除保证金';
COMMENT ON COLUMN "tb_balance_trx"."status" IS '状态1:待申诉 2:申诉成功 3:申诉失败';
COMMENT ON COLUMN "tb_balance_trx"."cycle" IS '申诉周期';
COMMENT ON COLUMN "tb_balance_trx"."url" IS '申诉图片';
COMMENT ON COLUMN "tb_balance_trx"."video" IS '申诉视屏';
COMMENT ON COLUMN "tb_balance_trx"."created" IS '创建时间';
COMMENT ON COLUMN "tb_balance_trx"."updated" IS '更新时间';
COMMENT ON TABLE "tb_balance_trx" IS '保证金划扣表';

DROP TABLE IF EXISTS "tb_activity";
CREATE TABLE "tb_activity"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "name" varchar(50) NOT NULL,
    "url" varchar(255) NOT NULL,
    "background_image" varchar(255) NOT NULL,
    "poster_image" varchar(255),
    "total" int4 NOT NULL DEFAULT 0,
    "surplus" int4 NOT NULL DEFAULT 0,
    "start_time" timestamp(6) DEFAULT now(),
    "end_time" timestamp(6) DEFAULT now(),
    "fixed" varchar(32) NOT NULL,
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
COMMENT ON COLUMN "tb_activity"."background_image" IS '活动图片地址';
COMMENT ON COLUMN "tb_activity"."poster_image" IS '分享海报';
COMMENT ON COLUMN "tb_activity"."total" IS '总库存';
COMMENT ON COLUMN "tb_activity"."surplus" IS '剩余库存';
COMMENT ON COLUMN "tb_activity"."start_time" IS '活动开始时间';
COMMENT ON COLUMN "tb_activity"."end_time" IS '活动结束时间';
COMMENT ON COLUMN "tb_activity"."fixed" IS '固定时间';
COMMENT ON COLUMN "tb_activity"."status" IS '状态0:暂停 1:下架 2:上架';
COMMENT ON COLUMN "tb_activity"."description" IS '活动描述';
COMMENT ON COLUMN "tb_activity"."creator" IS '创建人';
COMMENT ON COLUMN "tb_activity"."create_name" IS '创建名称';
COMMENT ON COLUMN "tb_activity"."created" IS '创建时间';
COMMENT ON COLUMN "tb_activity"."updater" IS '更新人';
COMMENT ON COLUMN "tb_activity"."update_name" IS '更新人名称';
COMMENT ON COLUMN "tb_activity"."updated" IS '更新时间';
COMMENT ON TABLE "tb_activity" IS '活动表';

DROP TABLE IF EXISTS "tb_activity_rule";
CREATE TABLE "tb_activity_rule"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "activity_id" int8 NOT NULL,
    "rule_id" int8 NOT NULL,
    "rule_type" smallint NOT NULL DEFAULT 1,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "tb_activity_rule"."id" IS '主键id';
COMMENT ON COLUMN "tb_activity_rule"."activity_id" IS '活动ID';
COMMENT ON COLUMN "tb_activity_rule"."rule_id" IS '规则ID';
COMMENT ON COLUMN "tb_activity_rule"."rule_type" IS '规则类别(1:新用户规则2:周期规则3:星级规则)';
COMMENT ON COLUMN "tb_activity_rule"."created" IS '创建时间';
COMMENT ON COLUMN "tb_activity_rule"."updated" IS '更新时间';
COMMENT ON TABLE "tb_activity_rule" IS '活动规则关联表';

DROP TABLE IF EXISTS "tb_star_rule";
CREATE TABLE "tb_star_rule"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "name" varchar(50) NOT NULL,
    "star_ids" varchar(32) NOT NULL,
    "star_names" varchar(64),
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);
COMMENT ON COLUMN "tb_star_rule"."id" IS '主键id';
COMMENT ON COLUMN "tb_star_rule"."name" IS '星级规则名称';
COMMENT ON COLUMN "tb_star_rule"."star_ids" IS '星级级别(字符串数组存储)';
COMMENT ON COLUMN "tb_star_rule"."star_names" IS '星级名称(前端显示用)';
COMMENT ON COLUMN "tb_star_rule"."created" IS '创建时间';
COMMENT ON COLUMN "tb_star_rule"."updated" IS '更新时间';
COMMENT ON TABLE "tb_star_rule" IS '星级规则表';

DROP TABLE IF EXISTS "tb_user_rule";
CREATE TABLE "tb_user_rule"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "name" varchar(50) NOT NULL,
    "day" int4 NOT NULL DEFAULT 30,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);
COMMENT ON COLUMN "tb_user_rule"."id" IS '主键id(即活动ID)';
COMMENT ON COLUMN "tb_user_rule"."name" IS '新用户规则名称';
COMMENT ON COLUMN "tb_user_rule"."day" IS '新用户注册多少天内';
COMMENT ON COLUMN "tb_user_rule"."created" IS '创建时间';
COMMENT ON COLUMN "tb_user_rule"."updated" IS '更新时间';
COMMENT ON TABLE "tb_user_rule" IS '用户规则表';

DROP TABLE IF EXISTS "tb_cycle_rule";
CREATE TABLE "tb_cycle_rule"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "name" varchar(50) NOT NULL,
    "month" int2 NOT NULL DEFAULT 1,
    "week" int2 NOT NULL DEFAULT 1,
    "day" int2 NOT NULL DEFAULT 1,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "tb_cycle_rule"."id" IS '主键id(即活动ID)';
COMMENT ON COLUMN "tb_cycle_rule"."name" IS '周期规则名称';
COMMENT ON COLUMN "tb_cycle_rule"."month" IS '每月参与次数';
COMMENT ON COLUMN "tb_cycle_rule"."week" IS '每周参与次数';
COMMENT ON COLUMN "tb_cycle_rule"."day" IS '每天参与次数';
COMMENT ON COLUMN "tb_cycle_rule"."created" IS '创建时间';
COMMENT ON COLUMN "tb_cycle_rule"."updated" IS '更新时间';
COMMENT ON TABLE "tb_cycle_rule" IS '周期规则表';

DROP TABLE IF EXISTS "tb_item";
CREATE TABLE "tb_item"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "name" varchar(50) NOT NULL,
    "url" varchar(255) NOT NULL,
    "amount" decimal(20, 2) NOT NULL DEFAULT 0.00,
    "status" smallint NOT NULL DEFAULT 1,
    "expire" int4 NOT NULL DEFAULT 7,
    "range" int4 NOT NULL DEFAULT 1,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now(),
    "creator" int8,
    "updater" int8,
    "create_name" varchar(20),
    "update_name" varchar(20)
);

COMMENT ON COLUMN "tb_item"."id" IS '主键id';
COMMENT ON COLUMN "tb_item"."name" IS '奖励名称';
COMMENT ON COLUMN "tb_item"."url" IS '图片';
COMMENT ON COLUMN "tb_item"."amount" IS '奖励金额';
COMMENT ON COLUMN "tb_item"."status" IS '状态0:暂停 1:上架 2:下架';
COMMENT ON COLUMN "tb_item"."expire" IS '过期天数';
COMMENT ON COLUMN "tb_item"."range" IS '排名范围';
COMMENT ON COLUMN "tb_item"."creator" IS '创建人';
COMMENT ON COLUMN "tb_item"."create_name" IS '创建名称';
COMMENT ON COLUMN "tb_item"."created" IS '创建时间';
COMMENT ON COLUMN "tb_item"."updater" IS '更新人';
COMMENT ON COLUMN "tb_item"."update_name" IS '更新人名称';
COMMENT ON COLUMN "tb_item"."updated" IS '更新时间';
COMMENT ON TABLE "tb_item" IS '奖品表';

DROP TABLE IF EXISTS "tb_activity_item";
CREATE TABLE "tb_activity_item"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "activity_id" int8 NOT NULL,
    "item_id" int8 NOT NULL,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "tb_activity_item"."id" IS '主键id';
COMMENT ON COLUMN "tb_activity_item"."activity_id" IS '活动ID';
COMMENT ON COLUMN "tb_activity_item"."item_id" IS '奖品ID';
COMMENT ON COLUMN "tb_activity_item"."created" IS '创建时间';
COMMENT ON COLUMN "tb_activity_item"."updated" IS '更新时间';
COMMENT ON TABLE "tb_activity_item" IS '活动奖励表';

DROP TABLE IF EXISTS "tb_activity_order";
CREATE TABLE "tb_activity_order"(
    "id" int8 NOT NULL PRIMARY KEY,
    "ass_order_id" varchar(32) NOT NULL DEFAULT '-1',
    "activity_id" int8 NOT NULL,
    "activity_name" varchar(50),
    "item_id" int8 NOT NULL,
    "item_name" int8 NOT NULL,
    "amount" decimal(20, 2) NOT NULL DEFAULT 0.00,
    "user_id" int8 NOT NULL,
    "username" varchar(50),
    "user_pic" varchar(255),
    "status" smallint NOT NULL DEFAULT 1,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "tb_activity_order"."id" IS '主键id';
COMMENT ON COLUMN "tb_activity_order"."ass_order_id" IS '关联订单号';
COMMENT ON COLUMN "tb_activity_order"."activity_id" IS '活动ID';
COMMENT ON COLUMN "tb_activity_order"."activity_name" IS '活动名称';
COMMENT ON COLUMN "tb_activity_order"."item_id" IS '奖品ID';
COMMENT ON COLUMN "tb_activity_order"."item_name" IS '奖品名称';
COMMENT ON COLUMN "tb_activity_order"."amount" IS '订单金额';
COMMENT ON COLUMN "tb_activity_order"."user_id" IS '用户ID';
COMMENT ON COLUMN "tb_activity_order"."username" IS '用户名称';
COMMENT ON COLUMN "tb_activity_order"."user_pic" IS '用户头像';
COMMENT ON COLUMN "tb_activity_order"."status" IS '状态(1:待支付,2:已支付,3:已取消,4:支付失败)';
COMMENT ON COLUMN "tb_activity_order"."created" IS '创建时间';
COMMENT ON COLUMN "tb_activity_order"."updated" IS '更新时间';
COMMENT ON TABLE "tb_activity_order" IS '活动订单表';

DROP TABLE IF EXISTS "tb_user_kabaw";
CREATE TABLE "tb_user_kabaw"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "activity_order_id" int8 NOT NULL,
    "user_id" int8 NOT NULL,
    "username" varchar(50),
    "user_pic" varchar(255),
    "status" smallint NOT NULL DEFAULT 1,
    "item_name" int8 NOT NULL,
    "item_url" varchar(255) NOT NULL,
    "item_expire" int4 NOT NULL,
    "item_amount" decimal(20, 2) NOT NULL,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "tb_user_kabaw"."id" IS '主键id';
COMMENT ON COLUMN "tb_user_kabaw"."activity_order_id" IS '关联活动订单号tb_activity_order';
COMMENT ON COLUMN "tb_user_kabaw"."item_name" IS '奖品名称';
COMMENT ON COLUMN "tb_user_kabaw"."item_url" IS '奖品图片';
COMMENT ON COLUMN "tb_user_kabaw"."item_expire" IS '奖品有效期';
COMMENT ON COLUMN "tb_user_kabaw"."item_amount" IS '奖品金额';
COMMENT ON COLUMN "tb_user_kabaw"."user_id" IS '用户ID';
COMMENT ON COLUMN "tb_user_kabaw"."username" IS '用户名称';
COMMENT ON COLUMN "tb_user_kabaw"."user_pic" IS '用户头像';
COMMENT ON COLUMN "tb_user_kabaw"."status" IS '状态(1:待使用,2:已使用,3:已过期)';
COMMENT ON COLUMN "tb_user_kabaw"."created" IS '创建时间';
COMMENT ON COLUMN "tb_user_kabaw"."updated" IS '更新时间';
COMMENT ON TABLE "tb_user_kabaw" IS '用户卡包表';

DROP TABLE IF EXISTS "tb_star";
CREATE TABLE "tb_star"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "name" varchar(30) NOT NULL,
    "status" int2 NOT NULL DEFAULT 1,
    "icon" varchar(255) NOT NULL,
    "disclaimer" int2 NOT NULL DEFAULT 0,
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
COMMENT ON COLUMN "tb_star"."status" IS '状态 0:禁用 1:启用';
COMMENT ON COLUMN "tb_star"."icon" IS '图标';
COMMENT ON COLUMN "tb_star"."disclaimer" IS '免责次数';
COMMENT ON COLUMN "tb_star"."start_value" IS '起始值';
COMMENT ON COLUMN "tb_star"."end_value" IS '结束值';
COMMENT ON COLUMN "tb_star"."creator" IS '创建人';
COMMENT ON COLUMN "tb_star"."create_name" IS '创建名称';
COMMENT ON COLUMN "tb_star"."created" IS '创建时间';
COMMENT ON COLUMN "tb_star"."updater" IS '更新人';
COMMENT ON COLUMN "tb_star"."update_name" IS '更新人名称';
COMMENT ON COLUMN "tb_star"."updated" IS '更新时间';
COMMENT ON TABLE "tb_star" IS '星级表';

DROP TABLE IF EXISTS "tb_label";
CREATE TABLE "tb_label"(
    "id" serial NOT NULL PRIMARY KEY,
    "name" varchar(20) NOT NULL,
    "status" int2 NOT NULL DEFAULT 1,
    "bean_name" varchar(100) NOT NULL,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "tb_label"."id" IS '主键id';
COMMENT ON COLUMN "tb_label"."name" IS '标签名称';
COMMENT ON COLUMN "tb_label"."status" IS '状态 0:禁用 1:启用';
COMMENT ON COLUMN "tb_label"."bean_name" IS 'Spring bean名称';
COMMENT ON COLUMN "tb_label"."created" IS '创建时间';
COMMENT ON COLUMN "tb_label"."updated" IS '更新时间';
COMMENT ON TABLE "tb_label" IS '标签表';
