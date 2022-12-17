package com.moge10086.website.common.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @Title: JsonResult.java
 * @Package com.moge10086.website.common.utils;
 * @Description: 自定义响应数据结构
 * 				200：表示成功
 * 				500：表示错误，错误信息在msg字段中
 * 				501：bean验证错误，不管多少个错误都以map形式返回
 * 				502：拦截器拦截到用户token出错
 * 				555：异常抛出信息
 * 				556: 用户qq校验异常
 * @Copyright: Copyright (c) 2020
 * @author sq
 * @version V1.0
 */
@Schema(description = "通用返回对象")
public class JsonResult<T> {

    /**
     * 响应业务状态
     */
    @Schema(description = "业务状态码")
    private Integer status;

    /**
     * 响应消息
     */
    @Schema(description = "业务信息")
    private String msg;

    /**
     * 响应中的数据
     */
    @Schema(description = "业务数据")
    private T data;

    @JsonIgnore
    private String ok;

    public static<T> JsonResult<T> ok(T data) {
        return new JsonResult<>(data);
    }

    public static JsonResult<Object> ok() {
        return new JsonResult<Object>(null);
    }

    public static JsonResult<Object> errorMsg(String msg) {
        return new JsonResult<Object>(500, msg, null);
    }
    public static<T> JsonResult<T> errorMsg(Integer status, String msg) {
        return new JsonResult<>(status, msg, null);
    }

    public static<T> JsonResult<T>  errorMap(T data) {
        return new JsonResult<>(501, "error", data);
    }

    public static JsonResult<Object> errorTokenMsg(String msg) {
        return new JsonResult<>(502, msg, null);
    }

    public JsonResult() {

    }

    public JsonResult(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public JsonResult(Integer status, String msg, T data, String ok) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.ok = ok;
    }

    public JsonResult(T data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", ok='" + ok + '\'' +
                '}';
    }
}
