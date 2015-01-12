package jp.co.dk.datastoremanager.database;

import jp.co.dk.datastoremanager.DataStore;
import jp.co.dk.datastoremanager.DataStoreKind;
import jp.co.dk.datastoremanager.DataStoreParameter;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

/**
 * DataBaseAccessParameterは、データベースに接続する際に必要なパラメータを保持するクラスです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class DataBaseAccessParameter extends DataStoreParameter{
	
	/** データベースドライバー */
	protected DataBaseDriverConstants driver;
	
	/** 接続先URL */
	protected String url;
	
	/** 接続先SID */
	protected String sid;
	
	/** ユーザ */
	protected String user;
	
	/** パスワード */
	protected String password;
	
	/**
	 * コンストラクタ<p/>
	 * 指定のドライバー、接続先URL、SID、ユーザ名、パスワードからデータベースアクセスに必要なパラメータを生成します。<br/>
	 * いづれかの値にnullまたは空文字が設定されていた場合、例外を送出する。
	 * 
	 * @param dataStoreKind データストア種別
	 * @param driver        データベースドライバー
	 * @param url           接続先URL
	 * @param sid           接続先SID
	 * @param user          ユーザ
	 * @param password      パスワード
	 */
	public DataBaseAccessParameter(DataStoreKind dataStoreKind, DataBaseDriverConstants driver, String url, String sid, String user, String password) throws DataStoreManagerException {
		super(dataStoreKind);
		if (driver   == null) throw new DataStoreManagerException(DRIVER_IS_NOT_SET);
		if (url      == null || url.equals("")) throw new DataStoreManagerException(URL_IS_NOT_SET);
		if (sid      == null || sid.equals("")) throw new DataStoreManagerException(SID_IS_NOT_SET);
		if (user     == null || user.equals("")) throw new DataStoreManagerException(USER_IS_NOT_SET);
		if (password == null || password.equals("")) throw new DataStoreManagerException(PASSWORD_IS_NOT_SET);
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

	@Override
	protected DataStore createDataStore() {
		return new DataBaseDataStore(this);
	}
	
	@Override
	public int hashCode() {
		int hashcode = super.hashCode() ;
		hashcode *= this.driver.hashCode() ;
		hashcode *= this.url.hashCode() ;
		hashcode *= this.sid.hashCode() ;
		hashcode *= this.user.hashCode() ;
		hashcode *= this.password.hashCode() ;
		hashcode *= 17;
		return hashcode; 
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("DATASTOREKIND=[").append(this.dataStoreKind.toString()).append(']').append(',');
		sb.append("DRIVER=[").append(this.driver.getDriverClass()).append(']').append(',');
		sb.append("URL=[").append(this.driver.getUrl(this.url, this.sid)).append(']').append(',');
		sb.append("USER=[").append(this.user).append(']').append(',');
		sb.append("PASSWORD=[").append(this.password).append(']');
		return sb.toString();
	}
	
}
