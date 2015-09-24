package com.ilucky.aplay.core.consts;

/**
 * @author IluckySi
 * @since 20150721
 */
public class Consts {

	/**
	 * web
	 */
	public static final String WEB_ADDRESS = "http://101.200.89.4";
	public static final String USER_PHOTO = "/aplay-web/userphoto/";
	public static final String GET_USER_SCORE_RANK_BY_PAGE = WEB_ADDRESS + "/aplay-web/userController/getUserScoreRankByPage.mvc";
	public static final String  MODIFY_USER= WEB_ADDRESS + "/aplay-web/userController/modifyUser.mvc";
	public static final String REGISTER = WEB_ADDRESS + "/aplay-web/loginController/register.mvc";
	public static final String LOGIN = WEB_ADDRESS + "/aplay-web/loginController/login.mvc";
	public static final String LOGOUT = WEB_ADDRESS + "/aplay-web/loginController/logout.mvc";
	public static final String GET_LIST_BY_RANDOM= WEB_ADDRESS + "/aplay-web/playController/getPlayListByRandom.mvc";
	public static final String SUBMIT_SCORE = WEB_ADDRESS + "/aplay-web/userController/submitScore.mvc";
	public static final String SUBMIT_PK_RESULT = WEB_ADDRESS + "/aplay-web/userController/submitPkResult.mvc";
	public static final String GET_USER_BY_NAME = WEB_ADDRESS + "/aplay-web/userController/getUserByName.mvc";
	public static final String SUBMIT_ADVICE = WEB_ADDRESS + "/aplay-web/userController/submitAdvice.mvc";
	public static final String GET_LATEST_VERSION_INSTALLED_PACKAGE = WEB_ADDRESS + "/aplay-web/userController/getLatestVersionInstallPackage.mvc";
	
	/**
	 * server
	 */
	public static final String SERVER_ADDRESS = "101.200.89.4";
	public static final int SERVER_PORT = 9999;
	public static final String HEART_BEAT_FAILURE = "心跳连接异常";
	public static final String CONNECT_CREATE = "客户端创建连接";
	public static final String CONNECT_OPEN = "客户端打开连接";
	public static final String MESSAGE_SENT = "客户端发送消息";
	public static final String MESSAGE_RECEIVED = "客户端接收消息";
	public static final String CONNECT_IDLE = "客户端连接空闲";
	public static final String CONNECT_CLOSE = "客户端关闭连接";
	public static final String CONNECT_EXCEPTION = "客户端连接异常";
	public static final String CONNECT_FAILURE = "建立连接失败"; 
	public static final String CONNECTNG = "正在建立连接"; 
	public static final String CONNECTNG_1 = "正在建立连接."; 
	public static final String CONNECTNG_2 = "正在建立连接.."; 
	public static final String CONNECTNG_3 = "正在建立连接..."; 
	public static final int MESSAGE_NON_HEART_BEAT = 0;
	public static final int MESSAGE_HEART_BEAT = 1;
	public static final int MESSAGE_PK_INVITE = 21;
	public static final int MESSAGE_PK_INVITE_CANCEL = 211;
	public static final int MESSAGE_PK_INVITE_FAILURE = 212;
	public static final int MESSAGE_PK_AGREE = 22;
	public static final int MESSAGE_PK_REFUSE = 23;
	public static final int MESSAGE_PK_RESULT = 24;
	
	/**
	 * message
	 */
	public static final String RESULT = "result";
	public static final String SUCCESS = "200";
	public static final String FAILURE = "400";
	public static final String MESSAGE = "message";
	public static final String SERVER = "服务器繁忙,请稍后再试";
	public static final String PROMPT = "系统提示";
	public static final String OK = "确定";
	public static final String CANCEL = "取消";
	//RegisterActivity
	public static final String REGISTER_ACTIVITY_PHOTO_GALLERY = "本地图片";
	public static final String REGISTER_ACTIVITY_PHOTO_CAMERA = "照相";
	public static final String REGISTER_ACTIVITY_PHOTO_SET = "设置头像";
	public static final String REGISTER_ACTIVITY_SEX_MAN= "男";
	public static final String REGISTER_ACTIVITY_EMPTY_PHONE_NAME_PASSWORD = "手机号,昵称和密码不能为空";
	public static final String REGISTER_ACTIVITY_NOT_SAME_PASSWORD = "两次输入的密码不一样";
	public static final String REGISTER_ACTIVITY_PROMPT= "注册中";
	//LoginActivity
	public static final String LOGIN_ACTIVITY_EMPTY_PHONE_PASSWORD = "手机号和密码不能为空";
	public static final String LOGIN_ACTIVITY_PROMPT= "登陆中";
	//InvitedPkActivity
	public static final String INVITED_PK_ACTIVITY_PROMPT= "加载中";
	//InvitePkActivity
	public static final String INVITE_PK_ACTIVITY_PROMPT= "加载中";
	//StartPkActivity
	public static final String START_PK_ACTIVITY_RIGHT = "回答正确,获取1积分";
	public static final String START_PK_ACTIVITY_WRONG = "回答错误,正确答案是";
	public static final String START_PK_ACTIVITY_SUBMIT = "提交结果";
	public static final String START_PK_ACTIVITY_PROMPT= "等待pk结果";
	//HomeFragment
	public static final String HOME_FRAGMENT_PROMPT= "加载中";
	public static final String  HOME_FRAGMENT_URL = "url";
	//RankFragment
	public static final String RANK_FRAGMENT_EMPTY_SEARCH = "关键字不能为空";
	public static final String RANK_FRAGMENT_USER = "user";
	public static final String RANK_FRAGMENT_PROMPT = "数据拼命加载中";
	//AdviceActivity
	public static final String  ADVICE_ACTIVITY_VALIDATE_ADVICE = "建议不能少于12个字符,不能多于120个字符";
	public static final String  ADVICE_ACTIVITY_PROMPT = "提交中";
	//PlayActivity
	public static final String PLAY_ACTIVITY_RIGHT = "回答正确,获取1积分";
	public static final String PLAY_ACTIVITY_WRONG = "回答错误,正确答案是";
	public static final String PLAY_ACTIVITY_SUBMIT = "提交结果";
	//UserActivity
	public static final String USER_ACTIVITY_EMPTY_NAME = "昵称不能为空";
	public static final String USER_ACTIVITY_PROMPT= "修改中";
	//RankAdapter
	public static final String RANK_ADAPTER_CANNOT_PK_SELF = "不可以挑战自己";
	//SettingFragment
	public static final String SETTING_FRAGMENT_LOGIN = "登录";
	public static final String SETTING_FRAGMENT_LOGOUT = "退出";
	public static final String SETTING_FRAGMENT_LOGOUT_PROMPT = "是否确认退出系统";
	public static final String SETTING_FRAGMENT_PROMT = "已经是最新的软件包";
	/**
	 * receiver
	 */
	public static final String INVITE_PK_FAILURE_RECEIVER = "com.ilucky.aplay.android.invite.pk.failure";
	public static final String INVITE_PK_RESULT_RECEIVER = "com.ilucky.aplay.android.invite.pk.result";
	public static final String PK_RESULT_RECEIVER = "com.ilucky.aplay.android.pk.result";
	
	/**
	 * color
	 */
	public static final int white =0xFFFFFFFF;
	public static final int gray = 0xFF7597B3;
	public static final int blue = 0xFF40AA53;
	
	/**
	 * file
	 */
	public static final String USER = "user";
	
	/**
	 * other
	 */
	public static final String ZIP = ".zip";
	public static final String TXT = ".txt";
	public static final String MP3 = ".mp3";
	public static final String PNG = ".png";
	public static final String DEFAULT_PHOTO = "default.png";
	public static final String PHOTO = "photo.png";

}
