package com.mvc.footprints.constant;

public class Constant {
	
	/**
	 * 操作成功
	 */
	public static final int SUCCESS = 1;
	/**
	 * 操作失败
	 */
	public static final int FAILURE = 0;
	

	/**
	 * 短信验证码内容
	 */
	public static final String SMS_MSG = "Thank you for signing up for My Footprints =) Your verification code is %s.";
	
	/**
	 * 登录成功
	 */
	public static final int USER_LOGIN_SC = 1;
	/**
	 * 用户名不存在
	 */
	public static final int USER_LOGIN_NOT_FOUND = 2;
	/**
	 * 用户名密码不匹配
	 */
	public static final int USER_LOGIN_NOT_MATCH = 3;
	
	/**
	 * 注册失败
	 */
	public static final int USER_REGIST_FAIL = 0;
	/**
	 * 注册成功
	 */
	public static final int USER_REGIST_SC = 1;
	/**
	 * 用户名已存在
	 */
	public static final int USER_REGIST_IS_EXIST = 2;
	/**
	 * 验证码错误
	 */
	public static final int USER_REGIST_CODE_FIRULE = 3;
	/**
	 * 手机号码已被注册
	 */
	public static final int MOBILE_PHONE_IS_EXISTS = 4;
	
	/**
	 * 商铺列表每次请求条数
	 */
	public static final int SHOP_LIST_ROWS = 10;
	
	/**
	 * 用户签到
	 */
	public static final String COMMENT_SIGN_YES = "1";
	
	/**
	 * 用户不签到
	 */
	public static final String COMMENT_SIGN_NO = "0";
	
	/**
	 * 日期格式
	 */
	public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 日期格式
	 */
	public static final String DATE = "yyyy-MM-dd";
	/**
	 * 当月已经点赞了，本月不能点赞
	 */
	public static final int LIKED = 2;
	/**
	 * 排行榜数
	 */
	public static final int RANKING_ROWS = 50;
	/**
	 * 排行榜查询类型 - 按年
	 */
	public static final String RANKING_TYPE_YEAR = "year";
	/**
	 * 排行榜查询类型 - 按月
	 */
	public static final String RANKING_TYPE_MONTH = "month";
	
	/**
	 * 文件服务器图片保存路径
	 */
	public static String FILE_PATH="/filedocument/temp/";
}
