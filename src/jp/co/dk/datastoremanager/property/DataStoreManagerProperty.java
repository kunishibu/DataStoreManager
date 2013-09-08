package jp.co.dk.datastoremanager.property;

import java.io.File;
import jp.co.dk.datastoremanager.DaoConstants;
import jp.co.dk.property.AbstractProperty;
import jp.co.dk.property.exception.PropertyException;

/**
 * データストアに関するプロパティを定義するクラスです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class DataStoreManagerProperty extends AbstractProperty {
	
	/** 接続先データストア種別 */
	public final static DataStoreManagerProperty DATASTORE_TYPE = new DataStoreManagerProperty("datastore.type");
	
	/** ORACLEの接続先URL */
	public final static DataStoreManagerProperty DATASTORE_ORACLE_URL = new DataStoreManagerProperty("datastore.oracle.url");
	/** ORACLEの接続先SID */
	public final static DataStoreManagerProperty DATASTORE_ORACLE_SID = new DataStoreManagerProperty("datastore.oracle.sid");
	/** ORACLEの接続時のユーザ名 */
	public final static DataStoreManagerProperty DATASTORE_ORACLE_USER = new DataStoreManagerProperty("datastore.oracle.user");
	/** ORACLEの接続時のパスワード */
	public final static DataStoreManagerProperty DATASTORE_ORACLE_PASSWORD = new DataStoreManagerProperty("datastore.oracle.password");
	
	/** MYSQLの接続先URL */
	public final static DataStoreManagerProperty DATASTORE_MYSQL_URL = new DataStoreManagerProperty("datastore.mysql.url");
	/** MYSQLの接続先SID */
	public final static DataStoreManagerProperty DATASTORE_MYSQL_SID = new DataStoreManagerProperty("datastore.mysql.sid");
	/** MYSQLの接続時のユーザ名 */
	public final static DataStoreManagerProperty DATASTORE_MYSQL_USER = new DataStoreManagerProperty("datastore.mysql.user");
	/** MYSQLの接続時のパスワード */
	public final static DataStoreManagerProperty DATASTORE_MYSQL_PASSWORD = new DataStoreManagerProperty("datastore.mysql.password");
	
	/** POSTGRESQLの接続先URL */
	public final static DataStoreManagerProperty DATASTORE_POSTGRESQL_URL = new DataStoreManagerProperty("datastore.postgresql.url");
	/** POSTGRESQLの接続先SID */
	public final static DataStoreManagerProperty DATASTORE_POSTGRESQL_SID = new DataStoreManagerProperty("datastore.postgresql.sid");
	/** POSTGRESQLの接続時のユーザ名 */
	public final static DataStoreManagerProperty DATASTORE_POSTGRESQL_USER = new DataStoreManagerProperty("datastore.postgresql.user");
	/** POSTGRESQLの接続時のパスワード */
	public final static DataStoreManagerProperty DATASTORE_POSTGRESQL_PASSWORD = new DataStoreManagerProperty("datastore.postgresql.password");
	
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたプロパティキーをもとにプロパティ定数クラスを生成します。
	 * 
	 * @param key プロパティキー
	 */
	protected DataStoreManagerProperty (String key) throws PropertyException {
		super(new File("properties/DataStoreManager.properties"), key);
	}
	
	/**
	 * このプロパティキーをDAO定義オブジェクトの名称で補完した値を返却します。<p/>
	 * 例えば、"datastore.type"、DaoConstants.getNameの値が"USERS"とした場合、<br/>
	 * プロパティファイルから"datastore.type.USERS"のキーに設定されている値を取得して返却します。<br/>
	 * <br/>
	 * 取得出来なかった場合、通常のプロパティ値が取得される。
	 * 
	 * @param daoConstants DAO定義オブジェクト 
	 * @return プロパティ設定値
	 */
	public String getString(DaoConstants daoConstants) {
		String key = this.getKey();
		String value = this.properties.getString(new StringBuilder(key).append('.').append(daoConstants.getName()).toString());
		if (value == null ) value = this.properties.getString(key);
		return value;
	}
	
}
