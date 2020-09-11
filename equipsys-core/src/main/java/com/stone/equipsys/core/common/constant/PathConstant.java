package com.stone.equipsys.core.common.constant;

public class PathConstant {

	/**登录后进入工作页面**/
	public static final String FrameMainPage="forward:/WEB-INF/jsp/Frame/Main.jsp";
	/**工作台**/
	public static final String FrameWorkbenchPage="forward:/WEB-INF/jsp/Frame/Workbench.jsp";								//首页
	
	/***设备管理*/
	public static final String EquipInfoManage="forward:/WEB-INF/jsp/EquipManage/InfoManage/Manage.jsp";					//设备信息管理
	public static final String EquipPartsList="forward:/WEB-INF/jsp/EquipManage/InfoManage/PartsList.jsp";					//
	public static final String EquipServicingApplicationManage="forward:/WEB-INF/jsp/EquipManage/ServicingManage/ApplicationManage.jsp";//设备维修申请管理页面
	//库存进销存管理
	/**仓位名称管理**/
	public static final String StoreInfo="forward:/WEB-INF/jsp/StoreManage/StoreInfo/Manage.jsp";							
	/**物料类型管理**/
	public static final String GoodsType="forward:/WEB-INF/jsp/StoreManage/GoodsType/Manage.jsp";
	public static final String GoodsTypeList="forward:/WEB-INF/jsp/StoreManage/GoodsType/List.jsp";
	/**物料库存信息管理**/
	public static final String StoreStockGoodsInfo="forward:/WEB-INF/jsp/StoreManage/StoreStockGoodsInfo/Manage.jsp";
	public static final String ModelNumberList="forward:/WEB-INF/jsp/StoreManage/StoreStockGoodsInfo/subList.jsp";
	//组织机构管理
	/**部门管理**/
	public static final String DeptManage="forward:/WEB-INF/jsp/OrganizationManage/DeptManage/Manage.jsp" ;					
	public static final String PostManage="forward:/WEB-INF/jsp/OrganizationManage/PostManage/Manage.jsp" ;					//岗位管理
	public static final String EmployeeManage="forward:/WEB-INF/jsp/OrganizationManage/EmployeeManage/Manage.jsp" ;			//员工管理
	public static final String EmployeeList="forward:/WEB-INF/jsp/OrganizationManage/EmployeeManage/List.jsp";
	/*** 个人中心*/
	public static final String MemberDetailInfo="forward:/WEB-INF/jsp/MemberCenter/MemberDetailInfo/MemberDetailInfo.jsp"; 	//用户详细信息
	public static final String ResetPassword="forward:/WEB-INF/jsp/MemberCenter/ResetPassword/ResetPassword.jsp"; 			//找回密码
	
	/***系统管理*/
	public static final String UserManage="forward:/WEB-INF/jsp/SystemManage/UserManage/Manage.jsp";						//用户管理
	public static final String UserList="forward:/WEB-INF/jsp/SystemManage/UserManage/List.jsp";							//用户列表
	public static final String RoleManage="forward:/WEB-INF/jsp/SystemManage/RoleManage/Manage.jsp";						//角色管理
	public static final String MenuManage="forward:/WEB-INF/jsp/SystemManage/MenuManage/Manage.jsp";						//菜单资源管理
	
	/***错误请求后的跳转页面**/
	public static final String ErrorPage="forward:/404.jsp";
}
