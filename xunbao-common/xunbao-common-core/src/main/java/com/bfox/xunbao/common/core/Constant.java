package com.bfox.xunbao.common.core;

public interface Constant {

	/**
	 * SQL常量 取一条
	 */
	String LIMIT_ONE = "LIMIT 1";

	/**
	 * 分隔符 .
	 */
	String DELIMITER_DOT = ".";

	/**
	 * 分隔符 -
	 */
	String DELIMITER_ROT = "-";

	/**
	 * 分隔符 :
	 */
	String DELIMITER_COLON = ":";

	/**
	 * 分隔符 ,
	 */
	String DELIMITER_COMMA = ",";

	/**
	 * 斜杠分隔符
	 */
	String DELIMITER_SLASH = "/";

	/**
	 * 总数
	 */
	String TOTAL = "TOTAL";

	/**
	 * 每月
	 */
	String MONTH = "MONTH";

	/**
	 * 每周
	 */
	String WEEK = "WEEK";

	/**
	 * 每天
	 */
	String DAY = "DAY";

	/**
	 * 十六进制补位
	 */
	String SM_HEX = "04";

	/**
	 * 经度
	 */
	String LNG = "lng";

	/**
	 * 维度
	 */
	String LAT = "lat";

	/**
	 * 系统
	 * @author eden
	 * @time 2022年7月22日 下午3:48:58
	 */
	public interface Sys {
		
		int SUPER_ADMIN = 1;
        
        String MENU_NAME = "一级菜单";
        
        String TOKEN = "token";

		String FACTOR_NAME = "isSend";
	}
	
	/**
	 * 菜单类型
	 * @author eden
	 * @time 2022年7月22日 下午3:48:53
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * 字典模板
     * @author eden
     * @date 2022年11月20日 下午6:31:29
     */
    public interface DictTemplate {
    	
    	String TABLE = "sys_dict";
    	
    	String TEST_KEY = "TEST_KEY";
    }

	/**
     * Redis Key接口统一进行管理
     * 常亮命名必须全部大写
     * @author eden
     * @date 2023年2月19日 上午9:05:17
     */
    public interface RedisKey {

		/**
		 * 管理系统Redis缓存前缀
		 */
		String SYS_PRIFIX_KEY = "TT:SYS:XUNBAO:";
    	
    	/**
    	 * 后台管理登录成功session id
    	 */
    	String SYS_SESSION_ID_KEY = SYS_PRIFIX_KEY.concat("SESSION:ID:");

		/**
		 * 后台管理双因子短信认证
		 */
		String SYS_SMS_ID_KEY = SYS_PRIFIX_KEY.concat("A2F:ID:");

		/**
		 * 短信code码
		 */
		String SYS_SMS_MOBILE_KEY = SYS_PRIFIX_KEY.concat("A2T:MOBILE:");

		/**
		 * 后台管系统登录锁定KEY
		 */
		String SYS_LOGIN_LOCKED_KEY = SYS_PRIFIX_KEY.concat("LOGIN:LOCKED:");

		/**
		 * 后台管理行政区
		 */
		String SYS_REGION_KEY = SYS_PRIFIX_KEY.concat("REGION:");
    	
    }

	/**
	 * 常用正则表达式
	 */
	interface RegExp {

		/**
		 * 邮箱
		 */
		String REX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

		/**
		 * 域名
		 */
		String REG_DOMAIN = "[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(/.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+/.?";

		/**
		 * 强密码(大小写、数字、特殊符号)
		 */
		String REX_PASSWORD = "^.*(?=.{8,})(?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).*$";

		/**
		 * 手机号
		 */
		String REG_MOBILE = "^1[356789]\\d{9}$";
	}
    
}
