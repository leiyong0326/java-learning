package com.cykj.base.common.system;

/**
 * 系统配置项
 * @author LeiYong
 *
 */
public class SystemConfig {
	//系统开关
	public static final boolean CAPTCHA_ENABLE= false;//验证码
	public static final boolean SYSTEM_FLAG_SENCURITY = true;//安全验证
	public static final boolean SYSTEM_FLAG_SESSION_FILTER = true;//session过滤器
	public static final boolean SYSTEM_FLAG_SHIRO = true;//shiro开启
	public static final boolean CACHE_ENABLED = true;//AOP缓存开启
	public static final boolean LOG_ENABLE = false;//AOP日志开启
	public static final boolean LOG_IP_ENABLE= true;//记录ip所在地等信息
	//参数配置
	public static final int REDIS_INDEX_DB = 4;//索引库
	public static final int REDIS_CACHE_DB = 5;//缓存库
	public static final int REDIS_WX_TOKEN_DB = 10;//微信token库
	public static final String SPLIT_CHARPTER = "_";//分隔符
	public static final String CHARSET = "utf-8";//编码格式
	public static final int LOGIN_COUNT = 5;//登录错误次数限制
	public static final int LOGIN_COUNT_TIME = 5;//错误N次后冻结时间N分钟
	public static final int LOG_CONTENT_LENGTH = 81;//多主键时保留日志主键位数
	public static final int REMEMBER_REDIS_CACHE_TIME = 2592000;//缓存时间,秒
	public static final int REMEMBER_REDIS_CACHE_RANDOM = 6;//缓存key混淆长度
	public static final int TRANSACTIONAL_TIMEOUT = 6;//缓存key混淆长度
	//cacheKey
	public static final String SHIRO_REDIS_CACHE_KEY = "SHIRO_CACHE";//Shiro的缓存主键
	public static final String REMEMBER_REDIS_CACHE_KEY = "REMEMBER_CACHE";//Shiro的缓存主键
	public static final String COOKIE_KEY = "randomToken";//记住我功能的CookieKey
	public static final String MENU_MENU_REDIS_CACHE_KEY = "MENU_MENU_CACHE";//菜单
	public static final String MENU_FUNC_REDIS_CACHE_KEY = "MENU_FUNC_CACHE";//菜单功能
}
