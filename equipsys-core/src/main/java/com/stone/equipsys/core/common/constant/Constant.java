package com.stone.equipsys.core.common.constant;

/**
 * 	所有静态常量汇总
 * @author Administrator
 *
 */
public class Constant {
	//
	public static final String ROLEID = "roleId";
	
	public static final String SESSION_OPERATOR = "session_operator";
	
	public static final String SESSION_PROPERTY_USER="user";

	public static final String RESPONSE_CODE = "code";

	public static final String RESPONSE_CODE_MSG = "msg";

	public static final String RESPONSE_DATA = "data";

	public static final String RESPONSE_DATA_TOTAL = "total";

	public static final String RESPONSE_DATA_TOTALCOUNT = "totalCount";

	public static final String RESPONSE_DATA_CURRENTPAGE = "currentPage";

	public static final String RESPONSE_DATA_PAGE = "page";
	
	public static final String OPERATION_SUCCESS = "操作成功";

	public static final String OPERATION_FAIL = "操作失败";
	
	public static final int SUCCEED_CODE_VALUE = 200; // 成功 插入 、删除 更新 修改

	public static final int FAIL_CODE_PARAM_INSUFFICIENT = 300; // 参数列表不完整

	public static final int FAIL_CODE_VALUE = 400; // 失败 插入 、删除 更新 修改

	public static final int FAIL_CODE_PWD = 401; // 交易密码错误

	public static final int PERM_CODE_VALUE = 403; // 无权限访问

	public static final int OTHER_CODE_VALUE = 500; // 其他异常

	public static final int NOSESSION_CODE_VALUE = 800; // session失效

	public static final int CLIENT_EXCEPTION_CODE_VALUE = 998; // 连接异常（除请求超时）

	public static final int TIMEOUT_CODE_VALUE = 999; // 请求超时

	public static final String TRANSFER_CHARSET = "UTF-8";

	public static final String initPassword="65faolo247ixyprl4nd3sigjclba35v322gqq36kgpe3tqwiygv22kmixfjvhlkukxtupwzfxkg4u";
}
