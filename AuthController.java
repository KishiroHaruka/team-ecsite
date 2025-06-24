package jp.co.internous.team2504.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import jp.co.internous.team2504.model.domain.MstUser;
import jp.co.internous.team2504.model.form.UserForm;
import jp.co.internous.team2504.model.mapper.MstUserMapper;
import jp.co.internous.team2504.model.mapper.TblCartMapper;
import jp.co.internous.team2504.model.session.LoginSession;

/**
 * 認証に関する処理を行うコントローラー
 * @author インターノウス
 *
 */
@RestController
@RequestMapping("/team2504/auth")
public class AuthController {

	/*
	 * フィールド定義
	 */

	@Autowired
	private MstUserMapper userMapper;
	
	@Autowired
	private TblCartMapper cartMapper;
	
	@Autowired
	private LoginSession session;

	private Gson gson = new Gson();

	/**
	 * ログイン処理をおこなう
	 * @param f ユーザーフォーム
	 */
	@PostMapping("/login")
	public String login(@RequestBody UserForm f) {
		MstUser user = userMapper.findByUserNameAndPassword(f.getUserName(),f.getPassword());
		if(user == null) {
			return gson.toJson(user);
		}
		cartMapper.updateUserId(user.getId(),session.getTmpUserId());
		session.setUserId(user.getId());
		session.setTmpUserId(0);
		session.setUserName(user.getUserName());
		session.setPassword(user.getPassword());
		session.setIsLoggedIn(true);
		return gson.toJson(user);
	}

	/**
	 * ログアウト処理をおこなう
	 * @return 空文字
	 */
	@PostMapping("/logout")
	public String logout() {
		session.setUserId(0);
		session.setTmpUserId(0);
		session.setUserName(null);
		session.setPassword(null);
		session.setIsLoggedIn(false);
		return "";
	}

	/**
	 * パスワード再設定をおこなう
	 * @param f ユーザーフォーム
	 * @return 処理後のメッセージ
	 */
	@PostMapping("/resetPassword")
	public String resetPassword(@RequestBody UserForm f) {
		MstUser currentUser = userMapper.findByUserNameAndPassword(session.getUserName(), session.getPassword());
		if (f.getNewPassword().equals(currentUser.getPassword())) {
			return "現在のパスワードと同一文字列が入力されました。";
		}
		userMapper.updatePassword(f.getUserName(), f.getNewPassword());
		session.setPassword(f.getNewPassword());
		return "パスワードが再設定されました。";
	}
}
