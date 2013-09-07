package jp.co.dk.datastoremanager.database.rdb;

import jp.co.dk.datastoremanager.DataStoreKind;
import jp.co.dk.datastoremanager.DataStoreParameter;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

/**
 * リレーショナルデータベースのデータストアに接続する際に使用するパラメータを定義するクラスです
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class RelationalDataBaseDataStoreParameter extends DataStoreParameter{
	
	/** サーバ名 */
	private String server;
	
	/** SID */
	private String sid;
	
	/** ユーザID */
	private String user;
	
	/** パスワード */
	private String password;
	
	/**
	 * コンストラクタ<p/>
	 * 指定のデータストア種別、サーバ名、SID、ユーザ、パスワードを基にパラメータを取得する。<br/>
	 * いずれかの値にnullまたは、空文字が設定されていた場合、例外を送出します。
	 * 
	 * @param dataStoreKind データストア種別
	 * @param server        サーバ名
	 * @param sid           SID
	 * @param userid        ユーザ
	 * @param password      パスワード
	 * @throws DataStoreManagerException いずれかの値にnullまたは、空文字が設定されていた場合、DataStoreManagerExceptionを送出します。
	 */
	public RelationalDataBaseDataStoreParameter(DataStoreKind dataStoreKind, String server, String sid, String userid, String password) throws DataStoreManagerException {
		super(dataStoreKind);
		if ( server   == null || server.equals("")  ) throw new DataStoreManagerException(DATASTORE_PARAMETER_CREATE_FAILE_SERVER_IS_NOT_SET);
		if ( sid      == null || sid.equals("")     ) throw new DataStoreManagerException(DATASTORE_PARAMETER_CREATE_FAILE_SID_IS_NOT_SET);
		if ( userid   == null || userid.equals("")  ) throw new DataStoreManagerException(DATASTORE_PARAMETER_CREATE_FAILE_USER_IS_NOT_SET);
		if ( password == null || password.equals("")) throw new DataStoreManagerException(DATASTORE_PARAMETER_CREATE_FAILE_PASSWORD_IS_NOT_SET);
		this.server        = server;
		this.sid           = sid;
		this.user          = userid;
		this.password      = password;
		
	}
	
	/**
	 * サーバ名を取得する。
	 * 
	 * @return サーバ名
	 */
	String getServer() {
		return server;
	}
	
	/**
	 * SIDを取得する。
	 * 
	 * @return SID
	 */
	String getSid() {
		return sid;
	}

	/**
	 * ユーザ名を取得する。
	 * 
	 * @return ユーザ名
	 */
	String getUser() {
		return user;
	}
	
	/**
	 * パスワードを取得する。
	 * 
	 * @return パスワード
	 */
	String getPassword() {
		return password;
	}

}
