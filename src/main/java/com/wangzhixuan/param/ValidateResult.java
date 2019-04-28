package com.wangzhixuan.param;

public class ValidateResult {
	private boolean validate;
	private String msg;
	public ValidateResult(){
		
	}
	public ValidateResult(boolean validate, String msg) {
		this.validate = validate;
		this.msg = msg;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
