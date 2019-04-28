package com.wangzhixuan.commons.result;

public enum ErrorCode {
	Success("SF0000","success","成功"),
	KnownFail("SF0010","未知异常","请求异常，请联系管理员"),
	SystemFial("SF0011","系统异常","请求异常，请联系管理员"),
	LoginUserIsNull("SF1001","用户名或者密码不能为空","用户名或者密码不能为空"),
	LoginFail("SF1002","用户名或者密码错误"),
	
	ReqIsNull("SF2001","请求对象为空","传入参数不能为空"),
	ReqIdIsNull("SF2002","Id不能为空"),
	ReqParentCodeIsNull("SF2003","父Code不能为空"),
	ReqTMXXIsNull("SF3101","题目选项不能为空","至少要有一个选项"),
	ReqTMBTIsNull("SF3102","题目标题不能为空"),
	ReqTMFLIsNull("SF3103","题目分类不能为空"),
	ReqTMFLDisable("SF3104","题目类型无效"),
	ReqWJTMLessThanOne("SF3201","问卷至少要有1个题目"),
	ReqWJSL001("SF3301","文件执行方式不能为空"),
	ReqWJSL002("SF3302","问卷ID不能为空"),
	ReqWjisNull("SF3303","文件不存在"),
	;
	
	ErrorCode(String code,String msg){
		this.code=code;
		this.msg=msg;
		this.info=msg;
	}
	ErrorCode(String code,String msg,String info){
		this.code=code;
		this.msg=msg;
		this.info=info;
	}
	private String code;
	private String msg;
	private String info;
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
