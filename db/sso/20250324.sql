DROP TABLE IF EXISTS "tb_user";
CREATE TABLE "tb_user"(
    "id" varchar(20) NOT NULL PRIMARY KEY,
    "username" varchar(50) NOT NULL DEFAULT '0',
    "password" varchar(64) NOT NULL DEFAULT '0',
    "nickname" varchar(50),
    "avatar" varchar(255),
    "union_id" varchar(32) NOT NULL DEFAULT '0',
    "openid" varchar(32) NOT NULL DEFAULT '0',
    "uuid" varchar(32) NOT NULL UNIQUE,
    "type" smallint NOT NULL DEFAULT 1,
    "ip" varchar(32),
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "tb_user"."id" IS '主键id';
COMMENT ON COLUMN "tb_user"."username" IS '用户名';
COMMENT ON COLUMN "tb_user"."password" IS '密码';
COMMENT ON COLUMN "tb_user"."nickname" IS '昵称';
COMMENT ON COLUMN "tb_user"."avatar" IS '用户头像';
COMMENT ON COLUMN "tb_user"."union_id" IS '微信union_id';
COMMENT ON COLUMN "tb_user"."openid" IS '小程序openid';
COMMENT ON COLUMN "tb_user"."uuid" IS '用户唯一标识';
COMMENT ON COLUMN "tb_user"."type" IS '类型1:真实用户 2:虚拟用户';
COMMENT ON COLUMN "tb_user"."ip" IS 'IP地址';
COMMENT ON COLUMN "tb_user"."created" IS '创建时间';
COMMENT ON COLUMN "tb_user"."updated" IS '更新时间';
COMMENT ON TABLE "tb_user" IS '用户表';

DROP TABLE IF EXISTS "tb_user_info";
CREATE TABLE "tb_user_info"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "user_id" varchar(20) NOT NULL,
    "acc_name" varchar(30) NOT NULL,
    "acc_no" varchar(64) NOT NULL,
    "acc_code" varchar(255) NOT NULL,
    "type" smallint NOT NULL DEFAULT 1,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);

CREATE INDEX tb_user_info_idx_001 ON "tb_user_info"("user_id");

COMMENT ON COLUMN "tb_user_info"."id" IS '主键id';
COMMENT ON COLUMN "tb_user_info"."user_id" IS '用户ID';
COMMENT ON COLUMN "tb_user_info"."acc_name" IS '真实姓名';
COMMENT ON COLUMN "tb_user_info"."acc_no" IS '账号';
COMMENT ON COLUMN "tb_user_info"."acc_code" IS '收款码';
COMMENT ON COLUMN "tb_user_info"."type" IS '1:微信 2:支付宝 3:银行卡';
COMMENT ON COLUMN "tb_user_info"."created" IS '创建时间';
COMMENT ON COLUMN "tb_user_info"."updated" IS '更新时间';
COMMENT ON TABLE "tb_user_info" IS '用户信息表';