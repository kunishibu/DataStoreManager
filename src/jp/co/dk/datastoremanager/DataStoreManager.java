package jp.co.dk.datastoremanager;

import java.util.HashMap;
import java.util.Map;

import jp.co.dk.datastoremanager.database.DataBaseAccessParameter;
import jp.co.dk.datastoremanager.database.DataBaseDriverConstants;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.datastoremanager.property.DataStoreManagerProperty;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

/**
 * DataStoreManagerは、Oracle、Mysql、PostgreSqlなどのデータストアに対する管理を行うクラスです。<p/>
 * <br/>
 * このクラスは指定のデータストアへのトランザクション制御、各種テーブルへのDAOの取得などデータストアへの操作を管理する制御クラスとなります。<br/>
 * <br/>
 * 対応しているデータストアは以下の 通りとなります。<br/>
 * 
 * ===RDB===<br/>
 * ・Oracle<br/>
 * ・MySql<br/>
 * ・PostgreSql<br/>
 * <br/>
 * ===ファイル===<br/>
 * ・csvファイル<br/>
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class DataStoreManager {
	
	/** データストアプール */
	protected Map<DataStoreParameter, DataStore> dataStorePool = new HashMap<DataStoreParameter, DataStore>();
	
	/** データストアプロパティ */
	DataStoreManagerProperty dataStoreManagerProperty;
	
	/**
	 * コンストラクタ<p/>
	 * 指定のデータストアマネージャプロパティからデータストアマネージャのインスタンスを生成します。<br/>
	 * 指定されたプロパティがnullだった場合、またはプロパティに不正な値が設定されていた場合、例外が送出される。
	 * 
	 * @param dataStoreManagerProperty データストアマネージャプロパティ
	 * @throws DataStoreManagerException インスタンスの生成に失敗した場合
	 */
	public DataStoreManager(DataStoreManagerProperty dataStoreManagerProperty) throws DataStoreManagerException{
		if (dataStoreManagerProperty == null) throw new DataStoreManagerException(PROPERTY_IS_NOT_SET);
		this.dataStoreManagerProperty = dataStoreManagerProperty;
		
	}
	
	/**
	 * このデータストア管理クラスが管理しているすべてのデータストアに対してトランザクションを開始します。<p/>
	 * トランザクション開始処理に失敗した場合、例外を送出します。
	 * 
	 * @throws DataStoreManagerException トランザクション開始に失敗した場合
	 */
	public void startTrunsaction() throws DataStoreManagerException {
		
	}
	
	
	/**
	 * 指定のDAO定義クラスオブジェクトを元にデータアクセスオブジェクトを生成し、返却します。
	 * @param daoConstants DAO定義クラス
	 * @return データアクセスオブジェクトのインスタンス
	 */
	public DataAccessObject getDataAccessObject(DaoConstants daoConstants) {
		DataStoreParameter dataStoreParameter = this.getDataStoreParameter(daoConstants);
		DataStore dataStore = this.dataStorePool.get(dataStoreParameter);
		if (dataStore == null) { 
			
		}
		return daoConstants.getDataAccessObjectFactory().getDataAccessObject(dataStoreKind, dataBaseDataStore);
	}
	
	public void finishTrunsaction() throws DataStoreManagerException {
		
	}
	
	@SuppressWarnings("static-access")
	protected DataStoreParameter getDataStoreParameter(DaoConstants daoConstants) throws DataStoreManagerException {
		DataStoreKind dataStoreKind = DataStoreKind.convert(this.dataStoreManagerProperty.DATASTORE_TYPE.getString());
		switch (dataStoreKind) {
			case ORACLE:
				String oracleurl      = this.dataStoreManagerProperty.DATASTORE_ORACLE_URL.getString(daoConstants);
				String oraclesid      = this.dataStoreManagerProperty.DATASTORE_ORACLE_SID.getString(daoConstants);
				String oracleuser     = this.dataStoreManagerProperty.DATASTORE_ORACLE_USER.getString(daoConstants);
				String oraclepassword = this.dataStoreManagerProperty.DATASTORE_ORACLE_PASSWORD.getString(daoConstants);
				return new DataBaseAccessParameter(DataBaseDriverConstants.ORACLE, oracleurl, oraclesid, oracleuser, oraclepassword);
				
			case MYSQL:
				String mysqlurl      = this.dataStoreManagerProperty.DATASTORE_MYSQL_URL.getString(daoConstants);
				String mysqlsid      = this.dataStoreManagerProperty.DATASTORE_MYSQL_SID.getString(daoConstants);
				String mysqluser     = this.dataStoreManagerProperty.DATASTORE_MYSQL_USER.getString(daoConstants);
				String mysqlpassword = this.dataStoreManagerProperty.DATASTORE_MYSQL_PASSWORD.getString(daoConstants);
				return new DataBaseAccessParameter(DataBaseDriverConstants.MYSQL, mysqlurl, mysqlsid, mysqluser, mysqlpassword);
				
			case POSTGRESSQL:
				String postgresqlurl      = this.dataStoreManagerProperty.DATASTORE_POSTGRESQL_URL.getString(daoConstants);
				String postgresqlsid      = this.dataStoreManagerProperty.DATASTORE_POSTGRESQL_SID.getString(daoConstants);
				String postgresqluser     = this.dataStoreManagerProperty.DATASTORE_POSTGRESQL_USER.getString(daoConstants);
				String postgresqlpassword = this.dataStoreManagerProperty.DATASTORE_POSTGRESQL_PASSWORD.getString(daoConstants);
				return new DataBaseAccessParameter(DataBaseDriverConstants.POSTGRESSQL, postgresqlurl, postgresqlsid, postgresqluser, postgresqlpassword);
				
			default :
				throw new DataStoreManagerException(NO_SUPPORT_DATA_STORE_KIND);
		}
	}
}
