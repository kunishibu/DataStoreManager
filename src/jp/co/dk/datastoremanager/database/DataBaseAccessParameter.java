package jp.co.dk.datastoremanager.database;

import jp.co.dk.datastoremanager.DataStoreParameter;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

/**
 * DataBaseAccessParameterは、データベースに接続する際に必要なパラメータを保持するクラスです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class DataBaseAccessParameter extends DataStoreParameter{
	
	/** データベースドライバー */
	private DataBaseDriverConstants driver;
	
	/** 接続先URL */
	private String url;
	
	/** 接続先SID */
	private String sid;
	
	/** ユーザ */
	private String user;
	
	/** パスワード */
	private String password;
	
	/**
	 * コンストラクタ<p/>
	 * 指定のドライバー、接続先URL、SID、ユーザ名、パスワードからデータベースアクセスに必要なパラメータを生成します。<br/>
	 * いづれかの値にnullまたは空文字が設定されていた場合、例外を送出する。
	 * 
	 * @param driver   データベースドライバー
	 * @param url      接続先URL
	 * @param sid      接続先SID
	 * @param user     ユーザ
	 * @param password パスワード
	 */
	public DataBaseAccessParameter(DataBaseDriverConstants driver, String url, String sid, String user, String password) throws DataStoreManagerException {
		if (driver   == null) throw new DataStoreManaerException();
		if (url      == null || url.equals("")) throw new DataStoreManaerException();
		if (sid      == null || sid.equals("")) throw new DataStoreManaerException();
		if (user     == null || user.equals("")) throw new DataStoreManaerException();
		if (password == null || password.equals("")) throw new DataStoreManaerException();
		this.driver   = driver;
		this.url      = url;
		this.sid      = sid;
		this.user     = user;
		this.password = password;
	}
	
	/**
	 * データベースアクセスドライバーを取得します。
	 * @return データベースアクセスドライバー
	 */
	public DataBaseDriverConstants getDriver() {
		return driver;
	}
	
	/**
	 * データベース接続先のURLを取得します。
	 * 
	 * @return データベース接続先のURL
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * データベース接続先のSIDを取得します。
	 * 
	 * @return データベース接続先のSID
	 */
	public String getSid() {
		return sid;
	}
	
	/**
	 * データベース接続先のユーザを取得します。
	 * 
	 * @return データベース接続先のユーザ
	 */
	public String getUser() {
		return user;
	}
	
	/**
	 * データベース接続先のパスワードを取得します。
	 * 
	 * @return データベース接続先のパスワード
	 */
	public String getPassword() {
		return password;
	}
	
	
}
