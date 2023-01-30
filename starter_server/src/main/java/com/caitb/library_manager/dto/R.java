package com.caitb.library_manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class R<T> {

	@JsonProperty(value = "ErrorInfo")
	private ErrorInfo ErrorInfo;

	private int code;

	private String message;

	private T data;

	private boolean success;

	private boolean resSuccess;

	private static final long serialVersionUID = 1L;

	/**
	 * 请求id
	 */
	private String requestId;

	public R() {
		this.code = 0;
		this.message = "消息";
	}

	public static <T> R<T> error() {
		return error(1, "操作失败");
	}

	public static <T> R<T> error(String msg) {
		return error(500, msg);
	}

	public static <T> R<T> error(int code, String msg) {
		return new R<T>()
				.setCode(code).setMessage(msg)
				.setSuccess(true)
				.setErrorInfo(new ErrorInfo().setErrDec(msg));
	}

	/**
	 * 返回正确的数据
	 *
	 * @param msg
	 * @param data
	 * @return
	 */
	public static <T> R<T> ok(String msg, T t) {
		R<T> r = new R<>();
		r.setCode(0)
				.setMessage(msg)
				.setData(t)
				.setSuccess(true)
				.setResSuccess(true);
		return r;
	}

	public static <T> R<T> ok(int code, String msg) {
		R<T> r = new R<>();
		r.setCode(code);
		r.setMessage(msg).setSuccess(true)
				.setResSuccess(true);
		return r;
	}

	public static <T> R<T> ok(T t) {
		return new R<T>()
				.setCode(0)
				.setMessage("操作成功")
				.setData(t)
				.setSuccess(true)
				.setResSuccess(true);
	}

	public static <T> R<T> ok() {
		return new R<T>()
				.setSuccess(true)
				.setResSuccess(true)
				.setCode(0).setMessage("操作成功");
	}
}
