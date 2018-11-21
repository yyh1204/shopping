package com.neuedu.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

//服务端返回到前端的高复用的相应对象
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> {

    private int status; //返回到前端的状态码
    private T date; //返回给前端的数据
    private String msg; //当 status!=0 时，封装了错误信息

    private ServerResponse() {

    }
    private ServerResponse(int status) {
        this.status = status;
    }
    private ServerResponse(int status, T date) {
        this.status = status;
        this.date = date;
    }
    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    private ServerResponse(int status, T date, String msg) {
        this.status = status;
        this.date = date;
        this.msg = msg;
    }

    //调用接口成功时回调
    public static ServerResponse serverResponsebBySuccess(){
        return new ServerResponse(ResponseCode.SUCESS);
    }
    public static <T> ServerResponse serverResponsebBySuccess(T date){
        return new ServerResponse(ResponseCode.SUCESS,date);
    }
    public static <T> ServerResponse serverResponsebBySuccess(T date,String msg){
        return new ServerResponse(ResponseCode.SUCESS,date,msg);
    }

    //接口调用失败时回调
    public static ServerResponse serverResponseByError(){
        return new ServerResponse(ResponseCode.ERROR);
    }
    public static ServerResponse serverResponseByError(String msg){
        return new ServerResponse(ResponseCode.ERROR,msg);
    }
    public static ServerResponse serverResponseByError(int status){
        return new ServerResponse(status);
    }
    public static ServerResponse serverResponseByError(int status,String msg){
        return new ServerResponse(status,msg);
    }

    //判断接口是否正确返回
    //status==0
    @JsonIgnore
    public boolean isScuess(){
        return this.status == ResponseCode.SUCESS;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /*
    @Override
    public String toString() {
        return "ServerResponse{" +
                "status=" + status +
                ", date=" + date +
                ", msg='" + msg + '\'' +
                '}';
    }

    public static void main(String[] args) {
        ServerResponse serverResponse = new ServerResponse(0,new Object());
        ServerResponse serverResponse1 = ServerResponse.serverResponsebBySuccess("hello",null);
        System.out.println(serverResponse);
        System.out.println(serverResponse1);
    }
*/
}
