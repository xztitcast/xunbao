CREATE TABLE "tb_tenant"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "name" varchar(30) NOT NULL,
    "parent_id" int8 NOT NULL DEFAULT 0,
    "parent_name" varchar(30),
    "status" int2 NOT NULL DEFAULT 1,
    "phones" varchar(30),
    "tname" varchar(20),
    "tphone" varchar(20),
    "pid" varchar(20),
    "pname" varchar(20),
    "cid" varchar(20),
    "cname" varchar(20),
    "aid" varchar(20),
    "aname" varchar(20),
    "address" varchar(100),
    "signtime" TIMESTAMP(6) DEFAULT NOW(),
    "expiretime" TIMESTAMP(6) DEFAULT NOW(),
    "path" varchar(100),
    "remark" varchar(255),
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "tb_tenant"."id" IS '主键';
COMMENT ON COLUMN "tb_tenant"."name" IS '机构全名';
COMMENT ON COLUMN "tb_tenant"."parent_id" IS '父机构ID,0为总机构';
COMMENT ON COLUMN "tb_tenant"."parent_name" IS '父机构名称';
COMMENT ON COLUMN "tb_tenant"."status" IS '状态是否有效0:无效 1:有效';
COMMENT ON COLUMN "tb_tenant"."phones" IS '机构服务电话(类似400)';
COMMENT ON COLUMN "tb_tenant"."tname" IS '负责人姓名';
COMMENT ON COLUMN "tb_tenant"."tphone" IS '负责人电话';
COMMENT ON COLUMN "tb_tenant"."pid" IS '省编码';
COMMENT ON COLUMN "tb_tenant"."pname" IS '省名称';
COMMENT ON COLUMN "tb_tenant"."cid" IS '市编码';
COMMENT ON COLUMN "tb_tenant"."cname" IS '市名称';
COMMENT ON COLUMN "tb_tenant"."aid" IS '区编码';
COMMENT ON COLUMN "tb_tenant"."aname" IS '区名称';
COMMENT ON COLUMN "tb_tenant"."address" IS '机构详细地址';
COMMENT ON COLUMN "tb_tenant"."signtime" IS '签约时间';
COMMENT ON COLUMN "tb_tenant"."expiretime" IS '到期时间';
COMMENT ON COLUMN "tb_tenant"."path" IS '0-总机构 1-子机构';
COMMENT ON COLUMN "tb_tenant"."remark" IS '机构简称';
COMMENT ON COLUMN "tb_tenant"."created" IS '创建时间';
COMMENT ON COLUMN "tb_tenant"."updated" IS '更新时间';
COMMENT ON TABLE "tb_tenant" IS '机构信息';