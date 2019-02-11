package com.plusmall.commons;

import java.io.Serializable;

/**
 * @Description:
 */
public class ActionResult implements Serializable {
	private Boolean success;
	private String msg;

	public ActionResult(Boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
