package com.baobaotao.domain;

import java.io.Serializable;
import java.util.Date;

public class SysLog implements Serializable{

	private static final long serialVersionUID = 9206857539156778071L;
	
	private String uId;  //用户id
	private String uName; //用户姓名
	private Date operateTime; //操作时间
	private String operateDes; //操作顺序
	private String uIp; //当前ip
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public String getOperateDes() {
		return operateDes;
	}
	public void setOperateDes(String operateDes) {
		this.operateDes = operateDes;
	}
	public String getuIp() {
		return uIp;
	}
	public void setuIp(String uIp) {
		this.uIp = uIp;
	}
	public SysLog(String uId, String uName, Date operateTime,
			String operateDes, String uIp) {
		super();
		this.uId = uId;
		this.uName = uName;
		this.operateTime = operateTime;
		this.operateDes = operateDes;
		this.uIp = uIp;
	}
	public SysLog() {
		super();
	}
	
	

}
