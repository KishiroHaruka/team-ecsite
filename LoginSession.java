package jp.co.internous.team2504.model.session;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * ログインセッション
 * @author インターノウス
 *
 */
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class LoginSession implements Serializable {
	private static final long serialVersionUID = -4505629762363906244L;

	private int userId;
	private int tmpUserId;
	private String userName;
	private String password;
	private boolean IsLoggedIn;
	
	/*
	 * 以下にGetter / Setter メソッドを作成
	 */
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTmpUserId() {
		return tmpUserId;
	}
	public void setTmpUserId(int tmpUserId) {
		this.tmpUserId = tmpUserId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getIsLoggedIn() {
		return IsLoggedIn;
	}
	public void setIsLoggedIn(boolean IsLoggedIn) {
		this.IsLoggedIn = IsLoggedIn;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
