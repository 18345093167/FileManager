package com.example.uploadingfiles.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "users")
@Table(name="pub_users")
public class UserEntity {
    //用户Id
    @Column(name="user_id")
    private String userId ;
    //用户名
    @Column(name="user_name")
    private String userName;
    //密码
    @Column(name="user_password")
    private String userPassword;
    //登录时间
    @Column(name="login_time")
    private Date loginTime;
    //登录Ip
    @Column(name="login_ip")
    private String loginIp;
    //登出时间
    @Column(name="login_out_time")
    private Date loginOutTime;
    //用户名
    @Column(name="user_email")
	private String userEmail;
	//是否登录 不是登录就是注册
	@Column(name="isLogin")
	private boolean isLogin;


	public boolean getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}


    public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPasswd() {
		return userPassword;
	}
	public void setUserPasswd(String userPasswd) {
		this.userPassword = userPasswd;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Date getLoginOutTime() {
		return this.loginOutTime;
	}
	public void setLoginOutTime(Date loginOutTime) {
		this.loginOutTime = loginOutTime;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", userName=" + userName + ", userPasswd=" + userPassword + ", loginTime="
				+ loginTime + ", loginIp=" + loginIp + ", loginOutTime=" + loginOutTime + ", userEmail=" + userEmail
				+ "]";
	}

}
