--postgresql 数据库纯数字字符串和字符串之间隐式转换
CREATE CAST (INTEGER AS VARCHAR) WITH INOUT AS IMPLICIT;
CREATE CAST (VARCHAR AS INTEGER) WITH INOUT AS IMPLICIT;
CREATE CAST (BIGINT AS VARCHAR) WITH INOUT AS IMPLICIT;
CREATE CAST (VARCHAR AS BIGINT) WITH INOUT AS IMPLICIT;

--删掉隐式转换
-- DROP CAST (varchar as bigint);
-- DROP CAST (bigint as varchar);

-- 菜单
CREATE TABLE tb_sys_menu (
     id bigserial NOT NULL,
     parent_id int8 DEFAULT 0 ,
     name varchar(30),
     url varchar(100),
     perms varchar(255),
     type smallint,
     status int2 NOT NULL DEFAULT 0,
     icon varchar(50),
     sorted smallint DEFAULT 0,
     PRIMARY KEY (id)
);

COMMENT ON TABLE tb_sys_menu IS '菜单管理';
COMMENT ON COLUMN tb_sys_menu.id IS '主键ID';
COMMENT ON COLUMN tb_sys_menu.parent_id IS '上级ID，一级菜单为0';
COMMENT ON COLUMN tb_sys_menu.name IS '名称';
COMMENT ON COLUMN tb_sys_menu.url IS '菜单URL';
COMMENT ON COLUMN tb_sys_menu.perms IS '授权(多个用逗号分隔，如：sys:user:list,sys:user:save)';
COMMENT ON COLUMN tb_sys_menu.type IS '类型 0：菜单 1：按钮';
COMMENT ON COLUMN tb_sys_menu.status IS '状态 是否能被删除 0：否 1：是';
COMMENT ON COLUMN tb_sys_menu.icon IS '菜单图标';
COMMENT ON COLUMN tb_sys_menu.sorted IS '排序';

CREATE TABLE tb_sys_user_tenant(
    id int8 NOT NULL PRIMARY KEY ,
    tenant_id int8 NOT NULL,
    tenant_name varchar(30) NOT NULL,
    created timestamp default now(),
    updated timestamp default now()
);

COMMENT ON TABLE tb_sys_user_tenant IS '用户租户关联表';
COMMENT ON COLUMN tb_sys_user_tenant.id IS '主键ID（即用户id）';
COMMENT ON COLUMN tb_sys_user_tenant.tenant_id IS '租户ID';
COMMENT ON COLUMN tb_sys_user_tenant.tenant_name IS '租户名称';
COMMENT ON COLUMN tb_sys_user_tenant.created IS '创建时间';
COMMENT ON COLUMN tb_sys_user_tenant.updated IS '更新时间';

-- 系统用户
CREATE TABLE tb_sys_user (
     id bigserial,
     username varchar(50) NOT NULL,
     password varchar(100),
     salt varchar(20),
     status smallint,
     locked timestamp(6) DEFAULT NOW(),
     avatar varchar(255),
     created timestamp(6),
     updated timestamp(6),
     creator int8,
     PRIMARY KEY (id),
     UNIQUE (username)
);

COMMENT ON TABLE tb_sys_user IS '用户管理';
COMMENT ON COLUMN tb_sys_user.id IS '主键ID';
COMMENT ON COLUMN tb_sys_user.username IS '用户名';
COMMENT ON COLUMN tb_sys_user.password IS '密码';
COMMENT ON COLUMN tb_sys_user.status IS '状态  0：停用 1：正常';
COMMENT ON COLUMN tb_sys_user.locked IS '锁定时间';
COMMENT ON COLUMN tb_sys_user.avatar IS '头像';
COMMENT ON COLUMN tb_sys_user.created IS '创建时间';
COMMENT ON COLUMN tb_sys_user.updated IS '更新时间';
COMMENT ON COLUMN tb_sys_user.creator IS '创建者ID';

-- 角色
CREATE TABLE tb_sys_role (
     id bigserial NOT NULL,
     name varchar(50),
     remark varchar(100),
     created timestamp(6),
     updated timestamp(6),
     creator int8,
     PRIMARY KEY (id)
);

COMMENT ON TABLE tb_sys_role IS '角色管理';
COMMENT ON COLUMN tb_sys_role.id IS '主键ID';
COMMENT ON COLUMN tb_sys_role.name IS '角色名称';
COMMENT ON COLUMN tb_sys_role.remark IS '备注';
COMMENT ON COLUMN tb_sys_role.created IS '创建时间';
COMMENT ON COLUMN tb_sys_role.updated IS '更新时间';
COMMENT ON COLUMN tb_sys_role.creator IS '创建者ID';

-- 用户与角色对应关系
CREATE TABLE tb_sys_user_role (
    id bigserial NOT NULL,
    user_id int8,
    role_id int8,
    PRIMARY KEY (id)
);

COMMENT ON TABLE tb_sys_user_role IS '角色用户关系';
COMMENT ON COLUMN tb_sys_user_role.role_id IS '角色ID';
COMMENT ON COLUMN tb_sys_user_role.user_id IS '用户ID';

-- 角色与菜单对应关系
CREATE TABLE tb_sys_role_menu (
    id bigserial NOT NULL,
    role_id int8,
    menu_id int8,
    PRIMARY KEY (id)
);

COMMENT ON TABLE tb_sys_role_menu IS '角色菜单关系';
COMMENT ON COLUMN tb_sys_role_menu.role_id IS '角色ID';
COMMENT ON COLUMN tb_sys_role_menu.menu_id IS '菜单ID';

-- 系统日志
CREATE TABLE tb_sys_log (
    id bigserial NOT NULL,
    username varchar(50),
    operation varchar(50),
    method varchar(255),
    params text,
    time int8 NOT NULL,
    ip varchar(64),
    created timestamp(6),
    PRIMARY KEY (id)
);

COMMENT ON TABLE tb_sys_log IS '系统日志表';
COMMENT ON COLUMN tb_sys_log.id IS '主键ID';
COMMENT ON COLUMN tb_sys_log.username IS '用户名';
COMMENT ON COLUMN tb_sys_log.operation IS '用户操作';
COMMENT ON COLUMN tb_sys_log.method IS '请求方法';
COMMENT ON COLUMN tb_sys_log.params IS '请求参数';
COMMENT ON COLUMN tb_sys_log.ip IS 'IP地址';
COMMENT ON COLUMN tb_sys_log.created IS '创建时间';

CREATE TABLE tb_sys_dict (
     id serial NOT NULL,
     key varchar(50) NOT NULL UNIQUE,
     value varchar(255) NOT NULL,
     remark varchar(50),
     created timestamp(6) DEFAULT NULL,
     updated timestamp(6) DEFAULT NULL,
     PRIMARY KEY (id)
);

COMMENT ON TABLE tb_sys_dict IS '字典信息表';
COMMENT ON COLUMN tb_sys_dict.id IS '主键ID';
COMMENT ON COLUMN tb_sys_dict.key IS '字典KEY';
COMMENT ON COLUMN tb_sys_dict.value IS '字典KEY -> VALUE';
COMMENT ON COLUMN tb_sys_dict.remark IS '备注';
COMMENT ON COLUMN tb_sys_dict.created IS '创建时间';
COMMENT ON COLUMN tb_sys_dict.updated IS '更新时间';

INSERT INTO tb_sys_user (id, username, password, salt, status, avatar, created, updated, creator) VALUES ('1', 'admin', '$2a$10$8IWXJhBdygy/g5ab7BJvIupMGKbVnvONd1Mh0ThHT.62n8x8W7gjy', 'YzcmCZNvbXocrsz9dm8e', '0', NULL, NOW(), NOW(), '0');

INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (1, 0, '系统管理', '', '', 0, 0, 'icon-desktop', 0);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (3, 1, '角色管理', 'sys/role', '', 1, 0, 'icon-team', 2);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (2, 1, '管理员列表', 'sys/user', '', 1, 0, 'icon-user', 1);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (4, 1, '菜单管理', 'sys/menu', '', 1, 0, 'icon-menu', 3);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (5, 1, '数据字典', 'sys/dict', '', 1, 0, 'icon-folder-open', 4);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (7, 1, '日志管理', 'sys/log', '', 1, 0, 'icon-file-text', 5);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (8, 2, '查看', '', 'sys:user:list,sys:user:info,sys:role:select', 2, 0, 'icon-search', 0);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (9, 2, '新增', '', 'sys:user:save', 2, 0, 'icon-plus-circle', 1);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (10, 2, '修改', '', 'sys:user:update', 2, 0, 'icon-edit-square', 2);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (11, 2, '删除', '', 'sys:user:delete', 2, 0, 'icon-delete', 3);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (12, 3, '查看', '', 'sys:role:list,sys:role:info', 2, 0, 'icon-search', 0);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (13, 3, '新增', '', 'sys:role:save', 2, 0, 'icon-plus-circle', 1);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (14, 3, '修改', '', 'sys:role:update', 2, 0, 'icon-edit-square', 2);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (15, 3, '删除', '', 'sys:role:delete', 2, 0, 'icon-delete', 3);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (16, 4, '查看', '', 'sys:menu:list,sys:menu:info,sys:menu:select', 2, 0, 'icon-search', 0);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (17, 4, '新增', '', 'sys:menu:save', 2, 0, 'icon-plus-circle', 1);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (18, 4, '修改', '', 'sys:menu:update', 2, 0, 'icon-edit-square', 2);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (19, 4, '删除', '', 'sys:menu:delete', 2, 0, 'icon-delete', 3);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (20, 5, '查看', '', 'sys:dict:list,sys:dict:info', 2, 0, 'icon-search', 0);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (21, 5, '新增', '', 'sys:dict:save', 2, 0, 'icon-plus-circle', 1);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (22, 5, '修改', '', 'sys:dict:update', 2, 0, 'icon-edit-square', 2);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (23, 5, '删除', '', 'sys:dict:delete', 2, 0, 'icon-delete', 3);
INSERT INTO "public"."tb_sys_menu" ("id", "parent_id", "name", "url", "perms", "type", "status", "icon", "sorted") VALUES (24, 7, '查看', '', 'sys:log:list', 2, 0, 'icon-search', 0);

alter sequence tb_sys_menu_id_seq restart with 25;
alter sequence tb_sys_user_id_seq restart with 2;

commit;

--framework模块
CREATE TABLE tb_tenant(
    id bigserial NOT NULL PRIMARY KEY ,
    name varchar(30) NOT NULL ,
    logo varchar(255) DEFAULT NULL ,
    mobile varchar(20) NOT NULL ,
    contacts varchar(30) NOT NULL ,
    contacts_mobile varchar(20) NOT NULL ,
    pid varchar(20) NOT NULL ,
    pname varchar(30) NOT NULL ,
    cid varchar(20) NOT NULL ,
    cname varchar(30) NOT NULL ,
    aid varchar(20) NOT NULL ,
    aname varchar(30) NOT NULL ,
    address varchar(100) NOT NULL ,
    remark varchar(50) NOT NULL ,
    sign_time timestamp NOT NULL ,
    expire_time timestamp NOT NULL ,
    created timestamp DEFAULT NOW(),
    updated timestamp DEFAULT NOW()
);

COMMENT ON TABLE tb_tenant IS '租户表';
COMMENT ON COLUMN tb_tenant.id IS '主键ID';
COMMENT ON COLUMN tb_tenant.name IS '租户名称';
COMMENT ON COLUMN tb_tenant.logo IS '备注';
COMMENT ON COLUMN tb_tenant.mobile IS '服务电话';
COMMENT ON COLUMN tb_tenant.contacts IS '联系人';
COMMENT ON COLUMN tb_tenant.contacts_mobile IS '联系人电话';
COMMENT ON COLUMN tb_tenant.pid IS '省编号';
COMMENT ON COLUMN tb_tenant.pname IS '省名称';
COMMENT ON COLUMN tb_tenant.cid IS '城市编号';
COMMENT ON COLUMN tb_tenant.cname IS '城市名称';
COMMENT ON COLUMN tb_tenant.aid IS '区编号';
COMMENT ON COLUMN tb_tenant.aname IS '区名称';
COMMENT ON COLUMN tb_tenant.address IS '详细底子';
COMMENT ON COLUMN tb_tenant.remark IS '备注';
COMMENT ON COLUMN tb_tenant.sign_time IS '签约时间';
COMMENT ON COLUMN tb_tenant.expire_time IS '到期时间';
COMMENT ON COLUMN tb_tenant.created IS '创建时间';
COMMENT ON COLUMN tb_tenant.updated IS '更新时间';

--setup模块
DROP TABLE IF EXISTS "tb_layout";
CREATE TABLE "tb_layout" (
    "id" serial NOT NULL PRIMARY KEY,
    "color" varchar(20) NOT NULL,
    "selected_color" varchar(20) NOT NULL,
    "background_color" varchar(20) NOT NULL,
    "border_style" varchar(20) NOT NULL,
    "type" smallint NOT NULL DEFAULT 1,
    "status" smallint NOT NULL DEFAULT 1,
    "tisid" int8 NOT NULL,
    "tisname" varchar(20) NOT NULL,
    "creator" int8,
    "create_name" varchar(20),
    "created" timestamp(6) DEFAULT now(),
    "updater" int8,
    "update_name" varchar(20),
    "updated" timestamp(6) DEFAULT now()
);

CREATE INDEX tb_layout_tts_idx ON "public"."tb_layout" ("tisid","type","status");

COMMENT ON COLUMN "public"."tb_layout"."id" IS '主键';
COMMENT ON COLUMN "public"."tb_layout"."color" IS 'tab 上的文字默认颜色，仅支持十六进制颜色';
COMMENT ON COLUMN "public"."tb_layout"."selected_color" IS 'tab 上的文字选中时的颜色，仅支持十六进制颜色';
COMMENT ON COLUMN "public"."tb_layout"."background_color" IS 'tab 的背景色，仅支持十六进制颜色';
COMMENT ON COLUMN "public"."tb_layout"."border_style" IS 'tabbar 上边框的颜色， 仅支持 black / white';
COMMENT ON COLUMN "public"."tb_layout"."type" IS '1:小程序, 2:安卓, 3:IOS';
COMMENT ON COLUMN "public"."tb_layout"."status" IS '0:无效, 1:有效';
COMMENT ON COLUMN "public"."tb_layout"."tisid" IS '机构号';
COMMENT ON COLUMN "public"."tb_layout"."tisname" IS '机构名称';
COMMENT ON COLUMN "public"."tb_layout"."creator" IS '创建人id';
COMMENT ON COLUMN "public"."tb_layout"."create_name" IS '创建名称';
COMMENT ON COLUMN "public"."tb_layout"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_layout"."updater" IS '更新人id';
COMMENT ON COLUMN "public"."tb_layout"."update_name" IS '更新人名称';
COMMENT ON COLUMN "public"."tb_layout"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_layout" IS '布局(适用小程序/安卓/IOS)';

DROP TABLE IF EXISTS "public"."tb_layout_navigate";
CREATE TABLE "public"."tb_layout_navigate" (
    "id" bigserial NOT NULL PRIMARY KEY,
    "navigate_name" varchar(20) NOT NULL,
    "select_icon" varchar(20) NOT NULL,
    "default_icon" varchar(20) NOT NULL,
    "page_path" varchar(100) NOT NULL,
    "special" smallint NOT NULL DEFAULT 1,
    "page_parameters" varchar(50),
    "sorted" smallint NOT NULL DEFAULT 0,
    "layout_id" int4 NOT NULL,
    "created" timestamp(6) NOT NULL DEFAULT now(),
    "updated" timestamp(6) NOT NULL DEFAULT now()
);

CREATE INDEX tb_layout_navigate_layout_id_idx ON "public"."tb_layout_navigate" ("layout_id");

COMMENT ON COLUMN "public"."tb_layout_navigate"."id" IS '主键';
COMMENT ON COLUMN "public"."tb_layout_navigate"."navigate_name" IS '导航名';
COMMENT ON COLUMN "public"."tb_layout_navigate"."select_icon" IS '导航选中图片';
COMMENT ON COLUMN "public"."tb_layout_navigate"."default_icon" IS '导航未选中默认图片';
COMMENT ON COLUMN "public"."tb_layout_navigate"."page_path" IS '页面路径';
COMMENT ON COLUMN "public"."tb_layout_navigate"."special" IS '是否特殊:1:非特殊 2:特殊(扫一扫)';
COMMENT ON COLUMN "public"."tb_layout_navigate"."page_parameters" IS '页面参数';
COMMENT ON COLUMN "public"."tb_layout_navigate"."sorted" IS '升序规则';
COMMENT ON COLUMN "public"."tb_layout_navigate"."layout_id" IS '关联tb_layout表id';
COMMENT ON COLUMN "public"."tb_layout_navigate"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_layout_navigate"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_layout_navigate" IS '导航';

DROP TABLE IF EXISTS "public"."tb_layout_navigate_path";
CREATE TABLE "public"."tb_layout_navigate_path"(
    "id" serial NOT NULL PRIMARY KEY,
    "navigate_name" varchar(20) NOT NULL,
    "navigate_path" varchar(100) NOT NULL
);

COMMENT ON COLUMN "public"."tb_layout_navigate_path"."id" IS '主键';
COMMENT ON COLUMN "public"."tb_layout_navigate_path"."navigate_name" IS '导航名';
COMMENT ON COLUMN "public"."tb_layout_navigate_path"."navigate_path" IS '导航路径';
COMMENT ON TABLE "public"."tb_layout_navigate_path" IS '导航路径(手动跑脚本)';

INSERT INTO "tb_layout_navigate_path" ("navigate_name", "navigate_path") VALUES ('首页', '/pages/index/index');
INSERT INTO "tb_layout_navigate_path" ("navigate_name", "navigate_path") VALUES ('配送商品分类', '/pages/class/class');
INSERT INTO "tb_layout_navigate_path" ("navigate_name", "navigate_path") VALUES ('礼享罗湖首页', '/twentyOne/luohuact/index');
INSERT INTO "tb_layout_navigate_path" ("navigate_name", "navigate_path") VALUES ('好店列表', '/pages/discount/discount');
INSERT INTO "tb_layout_navigate_path" ("navigate_name", "navigate_path") VALUES ('爆品福利', '/pages/explosives/explosives');
INSERT INTO "tb_layout_navigate_path" ("navigate_name", "navigate_path") VALUES ('个人中心', '/pages/mine/mine');
INSERT INTO "tb_layout_navigate_path" ("navigate_name", "navigate_path") VALUES ('电商样式首页', '/activity/index/index');
INSERT INTO "tb_layout_navigate_path" ("navigate_name", "navigate_path") VALUES ('邮惠生活样式首页', '/activity/index/index1');
INSERT INTO "tb_layout_navigate_path" ("navigate_name", "navigate_path") VALUES ('礼享罗湖样式首页', '/twentyOne/luohuact/index ');
INSERT INTO "tb_layout_navigate_path" ("navigate_name", "navigate_path") VALUES ('配送商品页面', '/buycar/goHome/goHome');
INSERT INTO "tb_layout_navigate_path" ("navigate_name", "navigate_path") VALUES ('购物车页面', '/buycar/carlist/carlist');
INSERT INTO "tb_layout_navigate_path" ("navigate_name", "navigate_path") VALUES ('自定义专区', '/activity/pages/activeconfig/activeconfig');
INSERT INTO "tb_layout_navigate_path" ("navigate_name", "navigate_path") VALUES ('领券中心', '/voucher/pages/voucher/voucher');
INSERT INTO "tb_layout_navigate_path" ("navigate_name", "navigate_path") VALUES ('商街版首页', '/twentyTwo/youindex/youindex');

DROP TABLE IF EXISTS "public"."tb_ad";
CREATE TABLE "public"."tb_ad" (
    "id" bigserial NOT NULL PRIMARY KEY,
    "navigate_name" varchar(20) NOT NULL,
    "adtype" varchar(11) NOT NULL,
    "change" smallint NOT NULL DEFAULT 0,
    "nums" smallint NOT NULL DEFAULT 1,
    "sorted" smallint NOT NULL DEFAULT 0,
    "status" smallint DEFAULT 1,
    "position" smallint NOT NULL DEFAULT 1,
    "tisid" int8 NOT NULL,
    "tisname" varchar(20) NOT NULL,
    "creator" int8,
    "create_name" varchar(20),
    "created" timestamp(6) DEFAULT now(),
    "updater" int8,
    "update_name" varchar(20),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "public"."tb_ad"."id" IS '主键';
COMMENT ON COLUMN "public"."tb_ad"."navigate_name" IS '模块页名称';
COMMENT ON COLUMN "public"."tb_ad"."adtype" IS '广告类型(实际是固定编号与前端商量固定)';
COMMENT ON COLUMN "public"."tb_ad"."change" IS '是否切屏0否 1是';
COMMENT ON COLUMN "public"."tb_ad"."nums" IS '广告条数';
COMMENT ON COLUMN "public"."tb_ad"."status" IS '0:无效 1:有效';
COMMENT ON COLUMN "public"."tb_ad"."position" IS '位置(1横栏banner,2底部,3首页中部抢优惠)';
COMMENT ON COLUMN "public"."tb_ad"."sorted" IS '排序(升序)';
COMMENT ON COLUMN "public"."tb_ad"."tisid" IS '机构号';
COMMENT ON COLUMN "public"."tb_ad"."tisname" IS '机构名称';
COMMENT ON COLUMN "public"."tb_ad"."creator" IS '创建人id';
COMMENT ON COLUMN "public"."tb_ad"."create_name" IS '创建名称';
COMMENT ON COLUMN "public"."tb_ad"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_ad"."updater" IS '更新人id';
COMMENT ON COLUMN "public"."tb_ad"."update_name" IS '更新人名称';
COMMENT ON COLUMN "public"."tb_ad"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_ad" IS '广告位';

INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('首页', '1', 1, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('积分商城', '6', 1, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('首页专区栏1(不规则栏)', '73', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('首页专区栏2(横栏)', '72', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('今日红包Banner', '71', 1, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('直播专题Banner', '21', 1, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('首页分类下面左', '74', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('首页分类下面右', '75', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('首页分类下面下', '76', 1, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('首页中部', '77', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('首页底部', '78', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('代金券banner', '79', 1, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('配送到家banner', '80', 1, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('买单有折banner', '81', 1, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('大牌优惠banner', '82', 1, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('优惠套餐banner', '83', 1, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('爆品福利banner', '84', 1, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('发现banner', '85', 1, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('超值推荐', '86', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('新品推荐', '87', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('大品牌，一起惠', '88', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('礼享罗湖', '26', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('礼享罗湖-美食', '1001', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('礼享罗湖-家居', '1002', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('礼享罗湖-珠宝', '1003', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('礼享罗湖-酒店', '1004', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('会员专区', '90', 1, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('领券中心banner', '2001', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('玩转积分banner', '2002', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('首页大屏广告顶部轮播', '3001', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('首页大屏广告底部专区', '3002', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('首页活动轮播banner', '2005', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('4.0首页分类上方广告位', '2007', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('支付认证结果页广告', '2006', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('二类户开户', '2008', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('我的数币红包页', '3003', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('任务中心', '3301', 0, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('养老专区大字版一级功能入口', '952713', 1, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('养老专区banner', '952711', 1, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('养老专区金刚区icon', '952712', 1, 1, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('个人养老金-首页左侧', '952714', 0, 11, 85, '冀优邮', '1');
INSERT INTO "tb_ad" ("navigate_name", "adtype", "change", "sorted", "tisid", "tisname", "status") VALUES ('个人养老金-首页底部', '952715', 0, 11, 85, '冀优邮', '1');

DROP TABLE IF EXISTS "public"."tb_ad_content";
CREATE TABLE "public"."tb_ad_content" (
    "id" bigserial NOT NULL PRIMARY KEY,
    "name" varchar(100) NOT NULL,
    "category_id" int8 NOT NULL,
    "category_name" varchar(30),
    "category_param" varchar(125),
    "urls" varchar(100),
    "pics" varchar(100),
    "shared" smallint NOT NULL DEFAULT 0,
    "jump_type" smallint DEFAULT 1,
    "jump_appid" varchar(64),
    "jump_url" varchar(255),
    "start_time" timestamp(6) DEFAULT NOW(),
    "end_time" timestamp(6) DEFAULT NOW(),
    "tisid" int8 NOT NULL,
    "tisname" varchar(20) NOT NULL,
    "creator" int8,
    "create_name" varchar(20),
    "created" timestamp(6) DEFAULT now(),
    "updater" int8,
    "update_name" varchar(20),
    "updated" timestamp(6) DEFAULT now()
);

CREATE INDEX tb_ad_content_category_id_idx ON "public"."tb_ad_content" ("category_id");

COMMENT ON COLUMN "public"."tb_ad_content"."id" IS '主键';
COMMENT ON COLUMN "public"."tb_ad_content"."name" IS '广告名字';
COMMENT ON COLUMN "public"."tb_ad_content"."category_id" IS '广告类目ID(关联tb_ad_category主键ID)';
COMMENT ON COLUMN "public"."tb_ad_content"."category_name" IS '广告类目名称(关联tb_ad_category名称,冗余字段)';
COMMENT ON COLUMN "public"."tb_ad_content"."category_param" IS '广告类目标签参数';
COMMENT ON COLUMN "public"."tb_ad_content"."pics" IS '图片地址';
COMMENT ON COLUMN "public"."tb_ad_content"."urls" IS '链接地址';
COMMENT ON COLUMN "public"."tb_ad_content"."shared" IS '是否可分享(0否 1是)';
COMMENT ON COLUMN "public"."tb_ad_content"."jump_type" IS '跳转类型 1:内部小程序  2:外部小程序 3:H5';
COMMENT ON COLUMN "public"."tb_ad_content"."jump_appid" IS '跳转外部小程序appid';
COMMENT ON COLUMN "public"."tb_ad_content"."jump_url" IS '跳转链接';
COMMENT ON COLUMN "public"."tb_ad_content"."start_time" IS '开始时间';
COMMENT ON COLUMN "public"."tb_ad_content"."end_time" IS '结束时间';
COMMENT ON COLUMN "public"."tb_ad_content"."tisid" IS '机构号';
COMMENT ON COLUMN "public"."tb_ad_content"."tisname" IS '机构名称';
COMMENT ON COLUMN "public"."tb_ad_content"."creator" IS '创建人id';
COMMENT ON COLUMN "public"."tb_ad_content"."create_name" IS '创建名称';
COMMENT ON COLUMN "public"."tb_ad_content"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_ad_content"."updater" IS '更新人id';
COMMENT ON COLUMN "public"."tb_ad_content"."update_name" IS '更新人名称';
COMMENT ON COLUMN "public"."tb_ad_content"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_ad_content" IS '广告内容信息';

DROP TABLE IF EXISTS "public"."tb_ad_join_content";
CREATE TABLE "public"."tb_ad_join_content"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "ad_id" int8 NOT NULL,
    "content_id" int8 NOT NULL,
    "sorted" smallint NOT NULL DEFAULT 0,
    "created" timestamp(6) DEFAULT NOW(),
    "updated" timestamp(6) DEFAULT NOW(),
    constraint tb_ad_join_content_aid_cid_unique UNIQUE("ad_id", "content_id")
);

COMMENT ON COLUMN "public"."tb_ad_join_content"."id" IS '主键';
COMMENT ON COLUMN "public"."tb_ad_join_content"."ad_id" IS '广告id(关联tb_ad表)';
COMMENT ON COLUMN "public"."tb_ad_join_content"."content_id" IS '广告内容ID(关联tb_ad_content表)';
COMMENT ON COLUMN "public"."tb_ad_join_content"."sorted" IS '排序(升序)';
COMMENT ON COLUMN "public"."tb_ad_join_content"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_ad_join_content"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_ad_join_content" IS '广告与广告内容关联表';

DROP TABLE IF EXISTS "public"."tb_ad_category";
CREATE TABLE "public"."tb_ad_category" (
    "id" serial NOT NULL PRIMARY KEY,
    "name" varchar(20) NOT NULL,
    "label" varchar(10) NOT NULL,
    "tisid" int8 NOT NULL,
    "tisname" varchar(20) NOT NULL,
    "creator" int8,
    "create_name" varchar(20),
    "created" timestamp(6) DEFAULT now(),
    "updater" int8,
    "update_name" varchar(20),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "public"."tb_ad_category"."id" IS '记录id';
COMMENT ON COLUMN "public"."tb_ad_category"."name" IS '广告类目名称';
COMMENT ON COLUMN "public"."tb_ad_category"."label" IS '广告类目标签';
COMMENT ON COLUMN "public"."tb_ad_category"."tisid" IS '机构号';
COMMENT ON COLUMN "public"."tb_ad_category"."tisname" IS '机构名称';
COMMENT ON COLUMN "public"."tb_ad_category"."creator" IS '创建人id';
COMMENT ON COLUMN "public"."tb_ad_category"."create_name" IS '创建名称';
COMMENT ON COLUMN "public"."tb_ad_category"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_ad_category"."updater" IS '更新人id';
COMMENT ON COLUMN "public"."tb_ad_category"."update_name" IS '更新人名称';
COMMENT ON COLUMN "public"."tb_ad_category"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_ad_category" IS '广告类目';


DROP TABLE IF EXISTS "public"."tb_agreement";
CREATE TABLE "public"."tb_agreement" (
    "id" serial NOT NULL PRIMARY KEY,
    "sphone" varchar(15) ,
    "oasuncodepic" varchar(300),
    "csprotool" text,
    "enterprotocl" text,
    "stimestart" time(6) DEFAULT NOW(),
    "stimeend" time(6) DEFAULT NOW(),
    "privacyprotocol" text,
    "memberprotocol" text,
    "psbcelectronicprotocol" text,
    "citylimitlist" varchar(500),
    "aboutpf" text,
    "auth_protocl" text,
    "psbc_privacy_protocol" text,
    "number_wallet_protocol" text ,
    "merchant_privacy_protocol" text,
    "tisid" int8 NOT NULL UNIQUE,
    "tisname" varchar(20),
    "creator" int8,
    "create_name" varchar(20),
    "created" timestamp(6) DEFAULT now(),
    "updater" int8,
    "update_name" varchar(20),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "public"."tb_agreement"."id" IS '自增id';
COMMENT ON COLUMN "public"."tb_agreement"."sphone" IS '客服电话';
COMMENT ON COLUMN "public"."tb_agreement"."oasuncodepic" IS '公众号太阳码';
COMMENT ON COLUMN "public"."tb_agreement"."csprotool" IS '客户服务协议';
COMMENT ON COLUMN "public"."tb_agreement"."enterprotocl" IS '入驻协议';
COMMENT ON COLUMN "public"."tb_agreement"."stimestart" IS '客服上班时间';
COMMENT ON COLUMN "public"."tb_agreement"."stimeend" IS '客服下班时间';
COMMENT ON COLUMN "public"."tb_agreement"."privacyprotocol" IS '个人隐私协议';
COMMENT ON COLUMN "public"."tb_agreement"."memberprotocol" IS '会员服务协议';
COMMENT ON COLUMN "public"."tb_agreement"."psbcelectronicprotocol" IS '邮政储蓄银行电子服务协议';
COMMENT ON COLUMN "public"."tb_agreement"."citylimitlist" IS '区域限制列表';
COMMENT ON COLUMN "public"."tb_agreement"."aboutpf" IS '平台简介';
COMMENT ON COLUMN "public"."tb_agreement"."auth_protocl" IS '中国邮政储蓄银行YOU商街授权协议';
COMMENT ON COLUMN "public"."tb_agreement"."psbc_privacy_protocol" IS '中国邮政储蓄银行开户隐私协议';
COMMENT ON COLUMN "public"."tb_agreement"."number_wallet_protocol" IS '数字人民币服务协议';
COMMENT ON COLUMN "public"."tb_agreement"."merchant_privacy_protocol" IS '商户隐私协议';
COMMENT ON COLUMN "public"."tb_agreement"."tisid" IS '机构号';
COMMENT ON COLUMN "public"."tb_agreement"."tisname" IS '机构名称';
COMMENT ON COLUMN "public"."tb_agreement"."creator" IS '创建人id';
COMMENT ON COLUMN "public"."tb_agreement"."create_name" IS '创建名称';
COMMENT ON COLUMN "public"."tb_agreement"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_agreement"."updater" IS '更新人id';
COMMENT ON COLUMN "public"."tb_agreement"."update_name" IS '更新人名称';
COMMENT ON COLUMN "public"."tb_agreement"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_agreement" IS '协议表';

DROP TABLE IF EXISTS "public"."tb_module";
CREATE TABLE "public"."tb_module" (
    "id" bigserial NOT NULL PRIMARY KEY,
    "name" varchar(50) NOT NULL,
    "remark" text,
    "icon" varchar(255) NOT NULL,
    "linkurl" varchar(255) ,
    "status" smallint NOT NULL DEFAULT 0,
    "jumptype" smallint NOT NULL DEFAULT 1,
    "jumpappid" varchar(64),
    "sorted" smallint NOT NULL DEFAULT 0,
    "shared" smallint NOT NULL DEFAULT 0,
    "tisid" int8 NOT NULL,
    "tisname" varchar(20) NOT NULL,
    "creator" int8,
    "create_name" varchar(20),
    "created" timestamp(6) DEFAULT now(),
    "updater" int8,
    "update_name" varchar(20),
    "updated" timestamp(6) DEFAULT now()
);

CREATE INDEX tb_module_tisid_status_idx ON "public"."tb_module" ("tisid", "status");

COMMENT ON COLUMN "public"."tb_module"."id" IS '主键ID';
COMMENT ON COLUMN "public"."tb_module"."name" IS '模块名称';
COMMENT ON COLUMN "public"."tb_module"."remark" IS '描述';
COMMENT ON COLUMN "public"."tb_module"."icon" IS 'icon图片地址';
COMMENT ON COLUMN "public"."tb_module"."linkurl" IS '链接地址';
COMMENT ON COLUMN "public"."tb_module"."status" IS '状态是否无效 0:有效 1:无效';
COMMENT ON COLUMN "public"."tb_module"."jumptype" IS '跳转类型 1:内部链接 2:外部小程序';
COMMENT ON COLUMN "public"."tb_module"."jumpappid" IS '跳转外部小程序appid';
COMMENT ON COLUMN "public"."tb_module"."sorted" IS '排序(升序规则 开始0)';
COMMENT ON COLUMN "public"."tb_module"."shared" IS '是否可分享(0:是 1:否)';
COMMENT ON COLUMN "public"."tb_module"."tisid" IS '机构号';
COMMENT ON COLUMN "public"."tb_module"."tisname" IS '机构名称';
COMMENT ON COLUMN "public"."tb_module"."creator" IS '创建人id';
COMMENT ON COLUMN "public"."tb_module"."create_name" IS '创建名称';
COMMENT ON COLUMN "public"."tb_module"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_module"."updater" IS '更新人id';
COMMENT ON COLUMN "public"."tb_module"."update_name" IS '更新人名称';
COMMENT ON COLUMN "public"."tb_module"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_module" IS '模块配置(兼容小程序/APP)';

DROP TABLE IF EXISTS "public"."tb_article_category";
CREATE TABLE "public"."tb_article_category"(
    "id" serial NOT NULL PRIMARY KEY,
    "name" varchar(20) NOT NULL UNIQUE,
    "tisid" int8 NOT NULL,
    "tisname" varchar(50) NOT NULL,
    "creator" int8,
    "create_name" varchar(20),
    "created" timestamp(6) DEFAULT now(),
    "updater" int8,
    "update_name" varchar(20),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "public"."tb_article_category"."id" IS '主键ID';
COMMENT ON COLUMN "public"."tb_article_category"."name" IS '类目名称';
COMMENT ON COLUMN "public"."tb_article_category"."tisid" IS '机构号';
COMMENT ON COLUMN "public"."tb_article_category"."tisname" IS '机构名称';
COMMENT ON COLUMN "public"."tb_article_category"."creator" IS '创建人id';
COMMENT ON COLUMN "public"."tb_article_category"."create_name" IS '创建名称';
COMMENT ON COLUMN "public"."tb_article_category"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_article_category"."updater" IS '更新人id';
COMMENT ON COLUMN "public"."tb_article_category"."update_name" IS '更新人名称';
COMMENT ON COLUMN "public"."tb_article_category"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_article_category" IS '文章类目表';

DROP TABLE IF EXISTS "public"."tb_article";
CREATE TABLE "public"."tb_article"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "author" varchar(10) NOT NULL,
    "title" varchar(30) NOT NULL,
    "pic" varchar(100) NOT NULL,
    "top" smallint NOT NULL DEFAULT 0,
    "hot" smallint NOT NULL DEFAULT 0,
    "status" smallint NOT NULL DEFAULT 1,
    "category_id" int4 NOT NULL,
    "push_time" timestamp(6),
    "tisid" int8 NOT NULL,
    "tisname" varchar(20) NOT NULL,
    "creator" int8,
    "create_name" varchar(20),
    "created" timestamp(6) DEFAULT now(),
    "updater" int8,
    "update_name" varchar(20),
    "updated" timestamp(6) DEFAULT now()
);

CREATE INDEX tb_article_category_id_idx ON "public"."tb_article" ("category_id");

COMMENT ON COLUMN "public"."tb_article"."id" IS '主键ID';
COMMENT ON COLUMN "public"."tb_article"."author" IS '作者';
COMMENT ON COLUMN "public"."tb_article"."title" IS '标题';
COMMENT ON COLUMN "public"."tb_article"."pic" IS '封面图';
COMMENT ON COLUMN "public"."tb_article"."top" IS '是否置顶0:否 1:是';
COMMENT ON COLUMN "public"."tb_article"."hot" IS '是否热门0:否 1:是';
COMMENT ON COLUMN "public"."tb_article"."status" IS '状态1:待发布 2:已发布 3:已下架';
COMMENT ON COLUMN "public"."tb_article"."category_id" IS '关联类目ID';
COMMENT ON COLUMN "public"."tb_article"."push_time" IS '发布时间';
COMMENT ON COLUMN "public"."tb_article"."tisid" IS '机构号';
COMMENT ON COLUMN "public"."tb_article"."tisname" IS '机构名称';
COMMENT ON COLUMN "public"."tb_article"."creator" IS '创建人id';
COMMENT ON COLUMN "public"."tb_article"."create_name" IS '创建名称';
COMMENT ON COLUMN "public"."tb_article"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_article"."updater" IS '更新人id';
COMMENT ON COLUMN "public"."tb_article"."update_name" IS '更新人名称';
COMMENT ON COLUMN "public"."tb_article"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_article" IS '文章表';

DROP TABLE IF EXISTS "public"."tb_article_content";
CREATE TABLE "public"."tb_article_content"(
    "id" int8 NOT NULL PRIMARY KEY,
    "content" text NOT NULL,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "public"."tb_article_content"."id" IS '主键ID';
COMMENT ON COLUMN "public"."tb_article_content"."content" IS '内容';
COMMENT ON COLUMN "public"."tb_article_content"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_article_content"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_article_content" IS '文章内容表';

DROP TABLE IF EXISTS "public"."tb_classroom_category";
CREATE TABLE "public"."tb_classroom_category"(
    "id" serial NOT NULL PRIMARY KEY,
    "name" varchar(20) NOT NULL UNIQUE,
    "tisid" int8 NOT NULL,
    "tisname" varchar(20) NOT NULL,
    "creator" int8,
    "create_name" varchar(20),
    "created" timestamp(6) DEFAULT now(),
    "updater" int8,
    "update_name" varchar(20),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "public"."tb_classroom_category"."id" IS '主键ID';
COMMENT ON COLUMN "public"."tb_classroom_category"."name" IS '类目名称';
COMMENT ON COLUMN "public"."tb_classroom_category"."tisid" IS '机构号';
COMMENT ON COLUMN "public"."tb_classroom_category"."tisname" IS '机构名称';
COMMENT ON COLUMN "public"."tb_classroom_category"."creator" IS '创建人id';
COMMENT ON COLUMN "public"."tb_classroom_category"."create_name" IS '创建名称';
COMMENT ON COLUMN "public"."tb_classroom_category"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_classroom_category"."updater" IS '更新人id';
COMMENT ON COLUMN "public"."tb_classroom_category"."update_name" IS '更新人名称';
COMMENT ON COLUMN "public"."tb_classroom_category"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_classroom_category" IS '数币课堂类目表';

DROP TABLE IF EXISTS "public"."tb_classroom";
CREATE TABLE "public"."tb_classroom"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "publisher" varchar(10) NOT NULL,
    "title" varchar(30) NOT NULL,
    "pic" varchar(100) NOT NULL,
    "avatar" varchar(100) NOT NULL,
    "top" smallint NOT NULL DEFAULT 0,
    "status" smallint NOT NULL DEFAULT 1,
    "category_id" int4 NOT NULL,
    "push_time" timestamp(6),
    "tisid" int8 NOT NULL,
    "tisname" varchar(20) NOT NULL,
    "creator" int8,
    "create_name" varchar(20),
    "created" timestamp(6) DEFAULT now(),
    "updater" int8,
    "update_name" varchar(20),
    "updated" timestamp(6) DEFAULT now()
);

CREATE INDEX tb_classroom_category_id_idx ON "public"."tb_classroom" ("category_id");

COMMENT ON COLUMN "public"."tb_classroom"."id" IS '主键ID';
COMMENT ON COLUMN "public"."tb_classroom"."publisher" IS '作者';
COMMENT ON COLUMN "public"."tb_classroom"."title" IS '标题';
COMMENT ON COLUMN "public"."tb_classroom"."pic" IS '封面图';
COMMENT ON COLUMN "public"."tb_classroom"."avatar" IS '封面图';
COMMENT ON COLUMN "public"."tb_classroom"."top" IS '是否置顶0:否 1:是';
COMMENT ON COLUMN "public"."tb_classroom"."status" IS '状态1:待发布 2:已发布 3:已下架';
COMMENT ON COLUMN "public"."tb_classroom"."category_id" IS '关联类目ID';
COMMENT ON COLUMN "public"."tb_classroom"."push_time" IS '发布时间';
COMMENT ON COLUMN "public"."tb_classroom"."tisid" IS '机构号';
COMMENT ON COLUMN "public"."tb_classroom"."tisname" IS '机构名称';
COMMENT ON COLUMN "public"."tb_classroom"."creator" IS '创建人id';
COMMENT ON COLUMN "public"."tb_classroom"."create_name" IS '创建名称';
COMMENT ON COLUMN "public"."tb_classroom"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_classroom"."updater" IS '更新人id';
COMMENT ON COLUMN "public"."tb_classroom"."update_name" IS '更新人名称';
COMMENT ON COLUMN "public"."tb_classroom"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_classroom" IS '数币课堂表';

DROP TABLE IF EXISTS "public"."tb_classroom_content";
CREATE TABLE "public"."tb_classroom_content"(
    "id" int8 NOT NULL PRIMARY KEY,
    "content" text NOT NULL,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "public"."tb_classroom_content"."id" IS '主键ID';
COMMENT ON COLUMN "public"."tb_classroom_content"."content" IS '内容';
COMMENT ON COLUMN "public"."tb_classroom_content"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_classroom_content"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_classroom_content" IS '数币课堂内容表';

DROP TABLE IF EXISTS "public"."tb_mine";
CREATE TABLE "public"."tb_mine" (
    "id" serial NOT NULL PRIMARY KEY,
    "topbox" varchar(100),
    "status" int2 NOT NULL DEFAULT 0,
    "problem" int2 NOT NULL DEFAULT 1,
    "privilege_code" int2 NOT NULL DEFAULT 1,
    "about_us" int2 NOT NULL DEFAULT 1,
    "agreement" int2 NOT NULL DEFAULT 1,
    "feedback" int2 NOT NULL DEFAULT 1,
    "member" int2 NOT NULL DEFAULT 1,
    "customer" int2 NOT NULL DEFAULT 1,
    "digital" int2 NOT NULL DEFAULT 1,
    "password" int2 NOT NULL DEFAULT 1,
    "tisid" int8 NOT NULL,
    "tisname" varchar(20) NOT NULL,
    "creator" int8,
    "create_name" varchar(20),
    "created" timestamp(6) DEFAULT now(),
    "updater" int8,
    "update_name" varchar(20),
    "updated" timestamp(6) DEFAULT now()
);
COMMENT ON COLUMN "public"."tb_mine"."id" IS '主键id';
COMMENT ON COLUMN "public"."tb_mine"."topbox" IS '顶部背景图片';
COMMENT ON COLUMN "public"."tb_mine"."status" IS '状态是否删除 1:删除 0:正常';
COMMENT ON COLUMN "public"."tb_mine"."problem" IS '常见问题是否展示(1是 2否)';
COMMENT ON COLUMN "public"."tb_mine"."privilege_code" IS '我的特权码是否展示(1是 2否)';
COMMENT ON COLUMN "public"."tb_mine"."about_us" IS '关于我们是否展示(1是 2否)';
COMMENT ON COLUMN "public"."tb_mine"."agreement" IS '用户协议是否展示(1是 2否)';
COMMENT ON COLUMN "public"."tb_mine"."feedback" IS '建议反馈是否展示(1是 2否)';
COMMENT ON COLUMN "public"."tb_mine"."member" IS '会员中心是否展示(1是 2否)';
COMMENT ON COLUMN "public"."tb_mine"."customer" IS '客服微信是否展示(1是 2否)';
COMMENT ON COLUMN "public"."tb_mine"."digital" IS '数字人民币服务是否展示(1是 2否)';
COMMENT ON COLUMN "public"."tb_mine"."password" IS '密码管理是否展示(1是 2否)';
COMMENT ON COLUMN "public"."tb_mine"."tisid" IS '机构号';
COMMENT ON COLUMN "public"."tb_mine"."tisname" IS '机构名称';
COMMENT ON COLUMN "public"."tb_mine"."creator" IS '创建人id';
COMMENT ON COLUMN "public"."tb_mine"."create_name" IS '创建名称';
COMMENT ON COLUMN "public"."tb_mine"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_mine"."updater" IS '更新人id';
COMMENT ON COLUMN "public"."tb_mine"."update_name" IS '更新人名称';
COMMENT ON COLUMN "public"."tb_mine"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_mine" IS '我的页面配置表';

DROP TABLE IF EXISTS "public"."tb_mine_area";
CREATE TABLE "public"."tb_mine_area" (
    "id" bigserial NOT NULL PRIMARY KEY,
    "mine_id" int4 NOT NULL,
    "title" varchar(20),
    "status" int2 NOT NULL DEFAULT 0,
    "tisid" int8 NOT NULL,
    "tisname" varchar(20) NOT NULL,
    "creator" int8,
    "create_name" varchar(20),
    "created" timestamp(6) DEFAULT now(),
    "updater" int8,
    "update_name" varchar(20),
    "updated" timestamp(6) DEFAULT now()
);

CREATE INDEX tb_mine_area_mids_idx ON "public"."tb_mine_area"("mine_id", "status");

COMMENT ON COLUMN "public"."tb_mine_area"."id" IS '主键id';
COMMENT ON COLUMN "public"."tb_mine_area"."status" IS '是否失效 1:失效 0:正常';
COMMENT ON COLUMN "public"."tb_mine_area"."title" IS '区域标题';
COMMENT ON COLUMN "public"."tb_mine_area"."mine_id" IS '关联我的页面ID(tb_mine)';
COMMENT ON COLUMN "public"."tb_mine_area"."tisid" IS '机构号';
COMMENT ON COLUMN "public"."tb_mine_area"."tisname" IS '机构名称';
COMMENT ON COLUMN "public"."tb_mine_area"."creator" IS '创建人id';
COMMENT ON COLUMN "public"."tb_mine_area"."create_name" IS '创建名称';
COMMENT ON COLUMN "public"."tb_mine_area"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_mine_area"."updater" IS '更新人id';
COMMENT ON COLUMN "public"."tb_mine_area"."update_name" IS '更新人名称';
COMMENT ON COLUMN "public"."tb_mine_area"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_mine_area" IS '我的页面区域表';

DROP TABLE IF EXISTS "public"."tb_mine_module";
CREATE TABLE "public"."tb_mine_module" (
    "id" bigserial NOT NULL PRIMARY KEY,
    "name" varchar(20) NOT NULL,
    "icon" varchar(100) NOT NULL,
    "url" varchar(100) NOT NULL,
    "status" int2 NOT NULL DEFAULT 0,
    "tisid" int8 NOT NULL,
    "tisname" varchar(20) NOT NULL,
    "creator" int8,
    "create_name" varchar(20),
    "created" timestamp(6) DEFAULT now(),
    "updater" int8,
    "update_name" varchar(20),
    "updated" timestamp(6) DEFAULT now()
);
COMMENT ON COLUMN "public"."tb_mine_module"."id" IS '主键id';
COMMENT ON COLUMN "public"."tb_mine_module"."name" IS '模块名称';
COMMENT ON COLUMN "public"."tb_mine_module"."icon" IS '模块图标';
COMMENT ON COLUMN "public"."tb_mine_module"."url" IS '页面路径';
COMMENT ON COLUMN "public"."tb_mine_module"."status" IS '状态是否删除 1:删除 0:正常';
COMMENT ON COLUMN "public"."tb_mine_module"."tisid" IS '机构号';
COMMENT ON COLUMN "public"."tb_mine_module"."tisname" IS '机构名称';
COMMENT ON COLUMN "public"."tb_mine_module"."creator" IS '创建人id';
COMMENT ON COLUMN "public"."tb_mine_module"."create_name" IS '创建名称';
COMMENT ON COLUMN "public"."tb_mine_module"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_mine_module"."updater" IS '更新人id';
COMMENT ON COLUMN "public"."tb_mine_module"."update_name" IS '更新人名称';
COMMENT ON COLUMN "public"."tb_mine_module"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_mine_module" IS '我的页面模块表';

DROP TABLE IF EXISTS "public"."tb_mine_module_area";
CREATE TABLE "public"."tb_mine_module_area"(
    "id" bigserial NOT NULL PRIMARY KEY,
    "module_id" int8 NOT NULL,
    "area_id" int8 NOT NULL,
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);
COMMENT ON COLUMN "public"."tb_mine_module_area"."id" IS '主键id';
COMMENT ON COLUMN "public"."tb_mine_module_area"."module_id" IS '主键id';
COMMENT ON COLUMN "public"."tb_mine_module_area"."area_id" IS '主键id';
COMMENT ON COLUMN "public"."tb_mine_module_area"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_mine_module_area"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_mine_module_area" IS '我的页面模块与区域关联表';

DROP TABLE IF EXISTS "tb_notice";
CREATE TABLE "public"."tb_notice" (
    "id" bigserial NOT NULL ,
    "classify" int2,
    "title" VARCHAR (100) ,
    "content" TEXT ,
    "share_title" VARCHAR (16) ,
    "share_content" VARCHAR (100) ,
    "sorted" int2,
    "tisid" int8 NOT NULL,
    "jumplink" VARCHAR (120) ,
    "status"  int2 DEFAULT 0,
    "type" int2,
    "creator" int8,
    "create_name" varchar(20),
    "created" TIMESTAMP ( 0 ),
    "updater" int8,
    "update_name" varchar(20),
    "updated" TIMESTAMP ( 0 ),
    CONSTRAINT "tb_notice_pkey" PRIMARY KEY ( "id" )
);
ALTER TABLE "public"."tb_notice" OWNER TO "postgres";
COMMENT ON COLUMN "public"."tb_notice"."id" IS '主键id';
COMMENT ON COLUMN "public"."tb_notice"."classify" IS '分类(1系统更新 2活动通知 3新闻资讯 4小程序首页)';
COMMENT ON COLUMN "public"."tb_notice"."title" IS '标题';
COMMENT ON COLUMN "public"."tb_notice"."content" IS '公告内容';
COMMENT ON COLUMN "public"."tb_notice"."sorted" IS '排序';
COMMENT ON COLUMN "public"."tb_notice"."status" IS '0无效 1有效';
COMMENT ON COLUMN "public"."tb_notice"."jumplink" IS '跳转链接';
COMMENT ON COLUMN "public"."tb_notice"."tisid" IS '机构号';
COMMENT ON COLUMN "public"."tb_notice"."type" IS '类型（0 全部 1商家 2 用户 ）';
COMMENT ON COLUMN "public"."tb_notice"."share_content" IS '分享内容';
COMMENT ON COLUMN "public"."tb_notice"."share_title" IS '分享标题';
COMMENT ON COLUMN "public"."tb_notice"."creator" IS '创建人id';
COMMENT ON COLUMN "public"."tb_notice"."create_name" IS '创建名称';
COMMENT ON COLUMN "public"."tb_notice"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_notice"."updater" IS '更新人id';
COMMENT ON COLUMN "public"."tb_notice"."update_name" IS '更新人名称';
COMMENT ON COLUMN "public"."tb_notice"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_notice" IS '通知公告';

-- 小程序后台建议反馈表
DROP TABLE IF EXISTS "public"."tb_feedback";
CREATE TABLE "public"."tb_feedback" (
    "id" bigserial NOT NULL PRIMARY KEY ,
    "type" int4,
    "page" VARCHAR ( 100 ),
    "description" VARCHAR ( 255 ),
    "picture_url" VARCHAR ( 255 ),
    "submit_time" TIMESTAMP ( 6 ) NOT NULL,
    "status" smallint DEFAULT 0,
    "creator" int8,
    "create_name" VARCHAR ( 64 ) ,
    "handler_id" int8,
    "handler_name" VARCHAR ( 30 ),
    "handler_time" TIMESTAMP ( 6 ),
    "created" TIMESTAMP ( 6 ),
    "updated" TIMESTAMP ( 6 )
);
COMMENT ON COLUMN "public"."tb_feedback"."id" IS '自增id';
COMMENT ON COLUMN "public"."tb_feedback"."type" IS '问题类型  1系统问题  2优化问题  3活动建议 4问题投诉';
COMMENT ON COLUMN "public"."tb_feedback"."page" IS '问题相关页面（小程序）';
COMMENT ON COLUMN "public"."tb_feedback"."description" IS '问题描述';
COMMENT ON COLUMN "public"."tb_feedback"."submit_time" IS '提交时间';
COMMENT ON COLUMN "public"."tb_feedback"."status" IS '问题状态  1已处理 0未处理';
COMMENT ON COLUMN "public"."tb_feedback"."creator" IS '提交用户ID';
COMMENT ON COLUMN "public"."tb_feedback"."create_name" IS '提交人姓名';
COMMENT ON COLUMN "public"."tb_feedback"."handler_id" IS '处理人ID';
COMMENT ON COLUMN "public"."tb_feedback"."handler_name" IS '处理人名字';
COMMENT ON COLUMN "public"."tb_feedback"."handler_time" IS '处理时间';
COMMENT ON COLUMN "public"."tb_feedback"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_feedback"."updated" IS '修改时间';
COMMENT ON TABLE "public"."tb_feedback" IS '小程序建议反馈表';

DROP TABLE IF EXISTS "tb_card_config";
CREATE TABLE "public"."tb_card_config" (
    "id" bigserial NOT NULL ,
    "title" VARCHAR(15),
    "receive_start_time" TIMESTAMP ( 0 ),
    "receive_end_time" TIMESTAMP ( 0 ),
    "main_image" VARCHAR(100),
    "color" varchar(20) NOT NULL,
    "flow_image" VARCHAR(100),
    "rules" TEXT,
    "config_url" VARCHAR (100),
    "status"  int2 DEFAULT 0,
    "created" TIMESTAMP ( 0 ),
    "updated" TIMESTAMP ( 0 ),
    CONSTRAINT "tb_card_config_pkey" PRIMARY KEY ( "id" )
);
ALTER TABLE "public"."tb_card_config" OWNER TO "postgres";
COMMENT ON COLUMN "public"."tb_card_config"."id" IS '主键id';
COMMENT ON COLUMN "public"."tb_card_config"."title" IS '标题';
COMMENT ON COLUMN "public"."tb_card_config"."receive_start_time" IS '申领开始时间';
COMMENT ON COLUMN "public"."tb_card_config"."receive_end_time" IS '申领结束时间';
COMMENT ON COLUMN "public"."tb_card_config"."main_image" IS '头图';
COMMENT ON COLUMN "public"."tb_card_config"."color" IS '背景色 tab 上的文字默认颜色，仅支持十六进制颜色';
COMMENT ON COLUMN "public"."tb_card_config"."flow_image" IS '流程图图片';
COMMENT ON COLUMN "public"."tb_card_config"."rules" IS '规则';
COMMENT ON COLUMN "public"."tb_card_config"."config_url" IS '配置链接';
COMMENT ON COLUMN "public"."tb_card_config"."status" IS '状态（0 禁用 1 启用 ）';
COMMENT ON COLUMN "public"."tb_card_config"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_card_config"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_card_config" IS '数币通配置';

DROP TABLE IF EXISTS "public"."tb_content_cat";
CREATE TABLE "public"."tb_content_cat" (
    "id" bigserial NOT NULL PRIMARY KEY,
    "parent_id" int8 NOT NULL DEFAULT 0,
    "name" varchar(50) DEFAULT NULL,
    "status" int2 DEFAULT 1,
    "sorted" int2 DEFAULT 0,
    "path" varchar(255) NOT NULL DEFAULT '0',
    "terminal" VARCHAR(10) NOT NULL DEFAULT 'PC',
    "tenant_id" int8 NOT NULL,
    "tenant_name" varchar(20) NOT NULL,
    "creator" int8,
    "create_name" varchar(20),
    "created" TIMESTAMP DEFAULT NOW(),
    "updater" int8,
    "update_name" varchar(20),
    "updated" TIMESTAMP DEFAULT NOW()
);

COMMENT ON COLUMN "public"."tb_content_cat"."id" IS '主键ID';
COMMENT ON COLUMN "public"."tb_content_cat"."parent_id" IS '父类目ID=0时，代表的是一级的类目';
COMMENT ON COLUMN "public"."tb_content_cat"."name" IS '分类名称';
COMMENT ON COLUMN "public"."tb_content_cat"."status" IS '状态是否有效 0:否 1:是';
COMMENT ON COLUMN "public"."tb_content_cat"."sorted" IS '排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列';
COMMENT ON COLUMN "public"."tb_content_cat"."path" IS '所属类目，叶子类目';
COMMENT ON COLUMN "public"."tb_content_cat"."terminal" IS '终端PC:浏览器, APP:手机应用端, MINI:微信小程序';
COMMENT ON COLUMN "public"."tb_content_cat"."tenant_id" IS '租户ID';
COMMENT ON COLUMN "public"."tb_content_cat"."tenant_name" IS '租户名称';
COMMENT ON COLUMN "public"."tb_content_cat"."creator" IS '创建人id';
COMMENT ON COLUMN "public"."tb_content_cat"."create_name" IS '创建名称';
COMMENT ON COLUMN "public"."tb_content_cat"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_content_cat"."updater" IS '更新人id';
COMMENT ON COLUMN "public"."tb_content_cat"."update_name" IS '更新人名称';
COMMENT ON COLUMN "public"."tb_content_cat"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_content_cat" IS '内容分类表';


DROP TABLE IF EXISTS "public"."tb_content";
CREATE TABLE "public"."tb_content" (
    "id" bigserial NOT NULL PRIMARY KEY,
    "cid" int8 NOT NULL,
    "text1" varchar(30) DEFAULT NULL,
    "text2" varchar(80) DEFAULT NULL,
    "text3" varchar(125) DEFAULT NULL,
    "text4" varchar(255) DEFAULT NULL,
    "text5" text DEFAULT NULL,
    "url" varchar(255) DEFAULT NULL,
    "pic" varchar(255) DEFAULT NULL,
    "pic2" varchar(255) DEFAULT NULL ,
    "pic3" varchar(255) DEFAULT NULL ,
    "tenant_id" int8 NOT NULL,
    "tenant_name" varchar(20) NOT NULL,
    "creator" int8,
    "create_name" varchar(20),
    "created" TIMESTAMP DEFAULT NOW(),
    "updater" int8,
    "update_name" varchar(20),
    "updated" TIMESTAMP DEFAULT NOW()
);

COMMENT ON COLUMN "public"."tb_content"."id" IS '主键ID';
COMMENT ON COLUMN "public"."tb_content"."cid" IS '内容类目ID';
COMMENT ON COLUMN "public"."tb_content"."text1" IS '内容1';
COMMENT ON COLUMN "public"."tb_content"."text2" IS '内容2';
COMMENT ON COLUMN "public"."tb_content"."text3" IS '内容3';
COMMENT ON COLUMN "public"."tb_content"."text4" IS '内容4';
COMMENT ON COLUMN "public"."tb_content"."text5" IS '内容5';
COMMENT ON COLUMN "public"."tb_content"."url" IS '链接';
COMMENT ON COLUMN "public"."tb_content"."pic" IS '图片绝对路径';
COMMENT ON COLUMN "public"."tb_content"."pic2" IS '图片2';
COMMENT ON COLUMN "public"."tb_content"."pic3" IS '图片3';
COMMENT ON COLUMN "public"."tb_content"."tenant_id" IS '租户ID';
COMMENT ON COLUMN "public"."tb_content"."tenant_name" IS '租户名称';
COMMENT ON COLUMN "public"."tb_content"."creator" IS '创建人id';
COMMENT ON COLUMN "public"."tb_content"."create_name" IS '创建名称';
COMMENT ON COLUMN "public"."tb_content"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_content"."updater" IS '更新人id';
COMMENT ON COLUMN "public"."tb_content"."update_name" IS '更新人名称';
COMMENT ON COLUMN "public"."tb_content"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_content" IS '内容表';

DROP TABLE IF EXISTS "public"."tb_message_channel";
CREATE TABLE "public"."tb_message_channel"(
    "id" serial NOT NULL PRIMARY KEY,
    "name" varchar(20) NOT NULL,
    "status" smallint NOT NULL DEFAULT 1,
    "api_key" varchar(64) NOT NULL,
    "api_secret" varchar(64) NOT NULL,
    "url" varchar(255),
    "remark" varchar(255),
    "creator" int8,
    "create_name" varchar(20),
    "updater" int8,
    "update_name" varchar(20),
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
);

COMMENT ON COLUMN "public"."tb_message_channel"."id" IS '主键ID';
COMMENT ON COLUMN "public"."tb_message_channel"."name" IS '渠道名称';
COMMENT ON COLUMN "public"."tb_message_channel"."status" IS '是否启用 0:禁用 1:启用';
COMMENT ON COLUMN "public"."tb_message_channel"."api_key" IS 'API账号';
COMMENT ON COLUMN "public"."tb_message_channel"."api_secret" IS ' API秘钥';
COMMENT ON COLUMN "public"."tb_message_channel"."url" IS '回调URL';
COMMENT ON COLUMN "public"."tb_message_channel"."remark" IS '备注';
COMMENT ON COLUMN "public"."tb_message_channel"."creator" IS '创建人id';
COMMENT ON COLUMN "public"."tb_message_channel"."create_name" IS '创建名称';
COMMENT ON COLUMN "public"."tb_message_channel"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_message_channel"."updater" IS '更新人id';
COMMENT ON COLUMN "public"."tb_message_channel"."update_name" IS '更新人名称';
COMMENT ON COLUMN "public"."tb_message_channel"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_message_channel" IS '消息渠道表';

DROP TABLE IF EXISTS "public"."tb_message_template";
CREATE TABLE "public"."tb_message_template"(
    "id" serial NOT NULL PRIMARY KEY,
    "name" varchar(20) NOT NULL,
    "code" varchar(20) NOT NULL,
    "template_id" varchar(32) NOT NULL DEFAULT '-1',
    "content" text NOT NULL,
    "bean_name" varchar(32) NOT NULL,
    "status" smallint NOT NULL DEFAULT 1,
    "channel_id" int8 NOT NULL,
    "channel_name" varchar(20),
    "remark" varchar(255),
    "creator" int8,
    "create_name" varchar(20),
    "updater" int8,
    "update_name" varchar(20),
    "created" timestamp(6) DEFAULT now(),
    "updated" timestamp(6) DEFAULT now()
)

COMMENT ON COLUMN "public"."tb_message_template"."id" IS '主键ID';
COMMENT ON COLUMN "public"."tb_message_template"."name" IS '模板名称';
COMMENT ON COLUMN "public"."tb_message_template"."code" IS '模板编码';
COMMENT ON COLUMN "public"."tb_message_template"."template_id" IS '模板ID(-1:不要模板ID仅用于占位)';
COMMENT ON COLUMN "public"."tb_message_template"."content" IS '模板内容';
COMMENT ON COLUMN "public"."tb_message_template"."bean_name" IS '模板解析器';
COMMENT ON COLUMN "public"."tb_message_template"."status" IS '是否启用 0:禁用 1:启用';
COMMENT ON COLUMN "public"."tb_message_template"."channel_id" IS '渠道ID';
COMMENT ON COLUMN "public"."tb_message_template"."channel_name" IS '渠道名称(冗余字段)';
COMMENT ON COLUMN "public"."tb_message_template"."remark" IS '备注';
COMMENT ON COLUMN "public"."tb_message_template"."creator" IS '创建人id';
COMMENT ON COLUMN "public"."tb_message_template"."create_name" IS '创建名称';
COMMENT ON COLUMN "public"."tb_message_template"."created" IS '创建时间';
COMMENT ON COLUMN "public"."tb_message_template"."updater" IS '更新人id';
COMMENT ON COLUMN "public"."tb_message_template"."update_name" IS '更新人名称';
COMMENT ON COLUMN "public"."tb_message_template"."updated" IS '更新时间';
COMMENT ON TABLE "public"."tb_message_template" IS '消息模板表';