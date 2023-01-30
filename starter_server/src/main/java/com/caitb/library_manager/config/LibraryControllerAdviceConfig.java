package com.caitb.library_manager.config;

import com.caitb.library_manager.dto.R;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.net.BindException;
import java.util.stream.Collectors;

@ControllerAdvice
public class LibraryControllerAdviceConfig {

	/**
	 * 处理一些@NotBlank和@NotEmpty等出现的异常
	 *
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public R exceptionHandler(MethodArgumentNotValidException e) {
		String collect = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","));
		return R.error(collect);
	}

	/**
	 * 处理get请求中, @valid验证路径中请求实体校验失败后抛出的异常
	 *
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(BindException.class)
	public R bindException(BindException e) {
		return R.error(e.getMessage());
	}

	/**
	 * 处理请求参数格式错误 @RequestParam上Validate失败后抛出的异常
	 *
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(ConstraintViolationException.class)
	public R constrainViolationExceptionHandler(ConstraintViolationException e) {
		String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
		return R.error(message);
	}

	@ResponseBody
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public R httpMessageNotReadableException(HttpMessageNotReadableException e) {
		return R.error(e.getMessage());
	}


}
