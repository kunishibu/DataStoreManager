package jp.co.dk.datastoremanager.database;

/**
 * DataBaseAccessParameterは、データベースに接続する際に必要なパラメータを保持するクラスです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class DataBaseAccessParameter {
	
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
	 * 指定のドライバー、接続先URL、SID、ユーザ名、パスワードからデータベースアクセスに必要なパラメータを生成します。
	 * 
	 * @param driver   データベースドライバー
	 * @param url      接続先URL
	 * @param sid      接続先SID
	 * @param user     ユーザ
	 * @param password パスワード
	 */
	public DataBaseAccessParameter(DataBaseDriverConstants driver, String url, String sid, String user, String password) {
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
