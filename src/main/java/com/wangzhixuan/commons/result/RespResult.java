package com.wangzhixuan.commons.result;

public class RespResult<T> {
	private long st;
	private long et;
	private long taking;
	private String code;
	private String msg;
	private T data;
	public RespResult(){
		this.st=System.currentTimeMillis();
	}
	public void getSuccess(T data,String msg){
		this.data=data;
		this.code=ErrorCode.Success.getCode();
		this.msg=msg;
		this.et=System.currentTimeMillis();
	}
	public void getSuccess(T data){
		this.data=data;
		this.code=ErrorCode.Success.getCode();
		this.msg=ErrorCode.Success.getMsg();
		this.et=System.currentTimeMillis();
	}
	public void getSuccess(){
		this.code=ErrorCode.Success.getCode();
		this.msg=ErrorCode.Success.getMsg();
		this.et=System.currentTimeMillis();
	}
	public static void main(String[] args) {
		new RespResult<Integer>().getSuccess();
	}
	public void getFail(ErrorCode errorCode){
		this.data=null;
		this.code=errorCode.getCode();
		this.msg=errorCode.getMsg();
		this.et=System.currentTimeMillis();
	}
	public void getFail(String code,String msg){
		this.data=null;
		this.code=code;
		this.msg=msg;
		this.et=System.currentTimeMillis();
	}
	public void getFail(String msg){
		this.data=null;
		this.code="SF7777";
		this.msg=msg;
		this.et=System.currentTimeMillis();
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
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public long getSt() {
		return st;
	}
	public void setSt(long st) {
		this.st = st;
	}
	public long getEt() {
		return et;
	}
	public void setEt(long et) {
		this.et = et;
	}
	public long getTaking() {
		this.taking=this.et-this.st;
		if(this.taking<0){
			taking=0l;
		}
		return this.taking;
	}
}
