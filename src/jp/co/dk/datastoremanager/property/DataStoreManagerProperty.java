package jp.co.dk.datastoremanager.property;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.NO_SUPPORT_DATA_STORE_KIND;

import java.io.File;
import jp.co.dk.datastoremanager.DaoConstants;
import jp.co.dk.datastoremanager.DataStoreKind;
import jp.co.dk.datastoremanager.DataStoreParameter;
import jp.co.dk.datastoremanager.database.DataBaseAccessParameter;
import jp.co.dk.datastoremanager.database.DataBaseDriverConstants;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.property.AbstractProperty;
import jp.co.dk.property.PropertiesFile;
import jp.co.dk.property.exception.PropertyException;

/**
 * データストアに関するプロパティを定義するクラスです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class DataStoreManagerProperty extends PropertiesFile {
	
//	/** 接続先データストア種別 */
//	public final static DataStoreManagerProperty DATASTORE_TYPE = new DataStoreManagerProperty("datastore.type");
//	
//	/** ORACLEの接続先URL */
//	public final static DataStoreManagerProperty DATASTORE_ORACLE_URL = new DataStoreManagerProperty("datastore.oracle.url");
//	/** ORACLEの接続先SID */
//	public final static DataStoreManagerProperty DATASTORE_ORACLE_SID = new DataStoreManagerProperty("datastore.oracle.sid");
//	/** ORACLEの接続時のユーザ名 */
//	public final static DataStoreManagerProperty DATASTORE_ORACLE_USER = new DataStoreManagerProperty("datastore.oracle.user");
//	/** ORACLEの接続時のパスワード */
//	public final static DataStoreManagerProperty DATASTORE_ORACLE_PASSWORD = new DataStoreManagerProperty("datastore.oracle.password");
//	
//	/** MYSQLの接続先URL */
//	public final static DataStoreManagerProperty DATASTORE_MYSQL_URL = new DataStoreManagerProperty("datastore.mysql.url");
//	/** MYSQLの接続先SID */
//	public final static DataStoreManagerProperty DATASTORE_MYSQL_SID = new DataStoreManagerProperty("datastore.mysql.sid");
//	/** MYSQLの接続時のユーザ名 */
//	public final static DataStoreManagerProperty DATASTORE_MYSQL_USER = new DataStoreManagerProperty("datastore.mysql.user");
//	/** MYSQLの接続時のパスワード */
//	public final static DataStoreManagerProperty DATASTORE_MYSQL_PASSWORD = new DataStoreManagerProperty("datastore.mysql.password");
//	
//	/** POSTGRESQLの接続先URL */
//	public final static DataStoreManagerProperty DATASTORE_POSTGRESQL_URL = new DataStoreManagerProperty("datastore.postgresql.url");
//	/** POSTGRESQLの接続先SID */
//	public final static DataStoreManagerProperty DATASTORE_POSTGRESQL_SID = new DataStoreManagerProperty("datastore.postgresql.sid");
//	/** POSTGRESQLの接続時のユーザ名 */
//	public final static DataStoreManagerProperty DATASTORE_POSTGRESQL_USER = new DataStoreManagerProperty("datastore.postgresql.user");
//	/** POSTGRESQLの接続時のパスワード */
//	public final static DataStoreManagerProperty DATASTORE_POSTGRESQL_PASSWORD = new DataStoreManagerProperty("datastore.postgresql.password");
//	
	
	/**
	 * コンストラクタ<p>
	 * デフォルトのプロパティファイルを元にプロパティファイルオブジェクトのインスタンスを生成する。<br/>
	 * デフォルトのプロパティファイルは"properties/DataStoreManager.properties"を参照します。<br/>
	 * <br/>
	 * プロパティファイルが存在しない場合、例外が発生します。
	 * 
	 * @throws PropertyException プロパティファイルオブジェクトのインスタンス作成に失敗した場合
	 */
	public DataStoreManagerProperty () throws PropertyException {
		this("properties/DataStoreManager.properties");
	}
	
	/**
	 * コンストラクタ<p>
	 * 指定のプロパティファイルを元にプロパティファイルオブジェクトのインスタンスを生成する。<br/>
	 * <br/>
	 * プロパティファイルが存在しない場合、例外が発生します。
	 * 
	 * @param file プロパティファイル
	 * @throws PropertyException プロパティファイルオブジェクトのインスタンス作成に失敗した場合
	 */
	public DataStoreManagerProperty (String file) throws PropertyException {
		super(file);
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
	public String getString(String key, DaoConstants daoConstants) {
		if (daoConstants == null) return this.getString(key);
		String value = this.getString(new StringBuilder(key).append('.').append(daoConstants.getName()).toString());
		if (value == null ) value = this.getString(key);
		return value;
	}
	
	/**
	 * このプロパティファイルからデータストアパラメータオブジェクトを生成し、返却する。<p/>
	 * プロパティファイルに値が設定されていないなど、プロパティファイルからデータストアパラメータクラスの生成に失敗した場合、例外が送出される。
	 * 
	 * @return データストアパラメータ
	 * @throws DataStoreManagerException データストアパラメータの生成に失敗した場合
	 */
	public DataStoreParameter getDataStoreParameter() throws DataStoreManagerException {
		return this.getDataStoreParameter(null);
	}
	
	/**
	 * このプロパティファイルからデータストアパラメータオブジェクトを生成し、返却する。<p/>
	 * プロパティファイルに値が設定されていないなど、プロパティファイルからデータストアパラメータクラスの生成に失敗した場合、例外が送出される。
	 * 
	 * @param daoConstants DAO定数クラス
	 * @return データストアパラメータ
	 * @throws DataStoreManagerException データストアパラメータの生成に失敗した場合
	 */
	public DataStoreParameter getDataStoreParameter(DaoConstants daoConstants) throws DataStoreManagerException {
		DataStoreKind dataStoreKind = DataStoreKind.convert(this.getString("datastore.type", daoConstants));
		switch (dataStoreKind) {
			case ORACLE:
				String oracleurl      = this.getString("datastore.oracle.url"     , daoConstants);
				String oraclesid      = this.getString("datastore.oracle.sid"     , daoConstants);
				String oracleuser     = this.getString("datastore.oracle.user"    , daoConstants);
				String oraclepassword = this.getString("datastore.oracle.password", daoConstants);
				return new DataBaseAccessParameter(DataStoreKind.ORACLE, DataBaseDriverConstants.ORACLE, oracleurl, oraclesid, oracleuser, oraclepassword);
				
			case MYSQL:
				String mysqlurl      = this.getString("datastore.mysql.url"     , daoConstants);
				String mysqlsid      = this.getString("datastore.mysql.sid"     , daoConstants);
				String mysqluser     = this.getString("datastore.mysql.user"    , daoConstants);
				String mysqlpassword = this.getString("datastore.mysql.password", daoConstants);
				return new DataBaseAccessParameter(DataStoreKind.MYSQL, DataBaseDriverConstants.MYSQL, mysqlurl, mysqlsid, mysqluser, mysqlpassword);
				
			case POSTGRESQL:
				String postgresqlurl      = this.getString("datastore.postgresql.url"     , daoConstants);
				String postgresqlsid      = this.getString("datastore.postgresql.sid"     , daoConstants);
				String postgresqluser     = this.getString("datastore.postgresql.user"    , daoConstants);
				String postgresqlpassword = this.getString("datastore.postgresql.password", daoConstants);
				return new DataBaseAccessParameter(DataStoreKind.POSTGRESQL, DataBaseDriverConstants.POSTGRESSQL, postgresqlurl, postgresqlsid, postgresqluser, postgresqlpassword);
				
			default :
				throw new DataStoreManagerException(NO_SUPPORT_DATA_STORE_KIND);
		}
	}
	
}
