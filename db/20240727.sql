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
    "path" varchar(100) NOT NULL DEFAULT '0',
    "remark" varchar(255),
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "tb_tenant"."id" IS '主键';
COMMENT ON COLUMN "tb_tenant"."name" IS '租户名称';
COMMENT ON COLUMN "tb_tenant"."parent_id" IS '父租户ID,0为主租户';
COMMENT ON COLUMN "tb_tenant"."parent_name" IS '父租户名称';
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
COMMENT ON COLUMN "tb_tenant"."path" IS '树路径0/1/2';
COMMENT ON COLUMN "tb_tenant"."remark" IS '机构简称';
COMMENT ON COLUMN "tb_tenant"."created" IS '创建时间';
COMMENT ON COLUMN "tb_tenant"."updated" IS '更新时间';
COMMENT ON TABLE "tb_tenant" IS '机构信息';

DROP TABLE IF EXISTS "tb_data_center";
CREATE TABLE "tb_data_center" (
    "id" bigserial NOT NULL PRIMARY KEY,
    "file_name" varchar(100) NOT NULL,
    "path" varchar(255) NOT NULL,
    "status" int2 NOT NULL DEFAULT 1,
    "sign" varchar(32) NOT NULL,
    "remark" varchar(100),
    "tenant_id" int8,
    "tenant_name" varchar(20),
    "creator" int8,
    "create_name" varchar(20),
    "created" TIMESTAMP (6),
    "updater" int8,
    "update_name" varchar(20),
    "updated" TIMESTAMP (6)
);

COMMENT ON COLUMN "tb_data_center"."id" IS '主键id';
COMMENT ON COLUMN "tb_data_center"."file_name" IS '下载文件名';
COMMENT ON COLUMN "tb_data_center"."path" IS '下载路径';
COMMENT ON COLUMN "tb_data_center"."status" IS '1待审核 2审核中 3审核成功 4审核驳回';
COMMENT ON COLUMN "tb_data_center"."sign" IS '文件签名保证唯一';
COMMENT ON COLUMN "tb_data_center"."remark" IS '审核驳回详细备注';
COMMENT ON COLUMN "tb_data_center"."tenant_id" IS '租户ID(冗余字段)';
COMMENT ON COLUMN "tb_data_center"."tenant_name" IS '租户名称(冗余字段)';
COMMENT ON COLUMN "tb_data_center"."creator" IS '创建人id';
COMMENT ON COLUMN "tb_data_center"."create_name" IS '创建名称';
COMMENT ON COLUMN "tb_data_center"."created" IS '创建时间';
COMMENT ON COLUMN "tb_data_center"."updater" IS '更新人id';
COMMENT ON COLUMN "tb_data_center"."update_name" IS '更新人名称';
COMMENT ON COLUMN "tb_data_center"."updated" IS '更新时间';
COMMENT ON TABLE "tb_data_center" IS '数据中心表';

DROP TABLE IF EXISTS "tb_data_activiti";
CREATE TABLE "tb_data_activiti"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "parent_id" int8 NOT NULL DEFAULT 0,
    "name" varchar(100) NOT NULL,
    "data_id" int8 NOT NULL,
    "status" int2 NOT NULL DEFAULT 1,
    "type" int2 NOT NULL,
    "type_name" varchar(30) NOT NULL,
    "audit_id" int8 NOT NULL DEFAULT 0,
    "audit_name" varchar(30),
    "audit_time" TIMESTAMP (6),
    "remark" varchar(255),
    "tenant_id" int8,
    "tenant_name" varchar(20),
    "creator" int8,
    "create_name" varchar(20),
    "created" TIMESTAMP (6),
    "updater" int8,
    "update_name" varchar(20),
    "updated" TIMESTAMP (6)
);

COMMENT ON COLUMN "tb_data_activiti".id IS '主键id';
COMMENT ON COLUMN "tb_data_activiti".parent_id IS '父级审批流ID';
COMMENT ON COLUMN "tb_data_activiti"."name" IS '审批流名称';
COMMENT ON COLUMN "tb_data_activiti"."data_id" IS '数据主键ID';
COMMENT ON COLUMN "tb_data_activiti"."status" IS '1待审核 2审核中 3审核成功 4审核驳回';
COMMENT ON COLUMN "tb_data_activiti"."type" IS '类型详情见JAVA代码activiti枚举';
COMMENT ON COLUMN "tb_data_activiti"."type_name" IS '类型详情见JAVA代码activiti枚举';
COMMENT ON COLUMN "tb_data_activiti"."audit_id" IS '审核人ID';
COMMENT ON COLUMN "tb_data_activiti"."audit_name" IS '审核人名称';
COMMENT ON COLUMN "tb_data_activiti"."audit_time" IS '审核时间';
COMMENT ON COLUMN "tb_data_activiti"."remark" IS '审核驳回必须填写的备注信息';
COMMENT ON COLUMN "tb_data_activiti"."tenant_id" IS '租户ID';
COMMENT ON COLUMN "tb_data_activiti"."tenant_name" IS '租户名称(冗余字段)';
COMMENT ON COLUMN "tb_data_activiti"."creator" IS '创建人id';
COMMENT ON COLUMN "tb_data_activiti"."create_name" IS '创建名称';
COMMENT ON COLUMN "tb_data_activiti"."created" IS '创建时间';
COMMENT ON COLUMN "tb_data_activiti"."updater" IS '更新人id';
COMMENT ON COLUMN "tb_data_activiti"."update_name" IS '更新人名称';
COMMENT ON COLUMN "tb_data_activiti"."updated" IS '更新时间';
COMMENT ON TABLE "tb_data_activiti" IS '数据审批流表';