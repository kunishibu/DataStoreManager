package jp.co.dk.datastoremanager;

import java.util.HashMap;
import java.util.Map;

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
	
	/** デフォルトのデータストア */
	protected DataStore defaultDataStore;
	
	/** 各種データストア */
	protected Map<String, DataStore> dataStores = new HashMap<String, DataStore>();
	
	/** データストアプロパティ */
	protected DataStoreManagerProperty dataStoreManagerProperty;
	
	/**
	 * コンストラクタ<p/>
	 * 
	 * 
	 * @param dataStoreManagerProperty データストアマネージャプロパティ
	 * @throws DataStoreManagerException インスタンスの生成に失敗した場合
	 */
	public DataStoreManager(DataStoreManagerProperty dataStoreManagerProperty) throws DataStoreManagerException{
		if (dataStoreManagerProperty == null) throw new DataStoreManagerException(PROPERTY_IS_NOT_SET);
		this.dataStoreManagerProperty               = dataStoreManagerProperty;
		this.defaultDataStore                       = dataStoreManagerProperty.getDefaultDataStoreParameter().createDataStore();
		Map<String, DataStoreParameter> parameterMap = dataStoreManagerProperty.getDataStoreParameters();
		for (String name : parameterMap.keySet()) {
			this.dataStores.put(name, parameterMap.get(name).createDataStore());
		}
	}
	
	/**
	 * このデータストア管理クラスが管理しているすべてのデータストアに対してトランザクションを開始します。<p/>
	 * トランザクション開始処理に失敗した場合、例外を送出します。
	 * 
	 * @throws DataStoreManagerException トランザクション開始に失敗した場合
	 */
	public void startTrunsaction() throws DataStoreManagerException {
		this.defaultDataStore.startTransaction();
		for (String name : this.dataStores.keySet()) {
			this.dataStores.get(name).startTransaction();
		}
	}
	
	/**
	 * 指定のDAO定義クラスオブジェクトを元にデータアクセスオブジェクトを生成し、返却します。
	 * @param daoConstants DAO定義クラス
	 * @return データアクセスオブジェクトのインスタンス
	 * @throws DataStoreManagerException データアクセスオブジェクトの生成、または取得に失敗した場合
	 */
	public DataAccessObject getDataAccessObject(DaoConstants daoConstants) throws DataStoreManagerException {
		DataStore dataStore = this.dataStores.get(daoConstants.getName());
		if (dataStore != null) { 
			return dataStore.getDataAccessObject(daoConstants);
		}
		return this.defaultDataStore.getDataAccessObject(daoConstants);
	}
	
	/**
	 * このデータストア管理クラスが管理しているすべてのデータストアに対してトランザクションを終了します。<p/>
	 * トランザクション終了処理に失敗した場合、例外を送出します。
	 * 
	 * @throws DataStoreManagerException トランザクション終了に失敗した場合
	 */
	public void finishTrunsaction() throws DataStoreManagerException {
		this.defaultDataStore.finishTransaction();
		for (String name : dataStores.keySet()) {
			this.dataStores.get(name).finishTransaction();
		}
	}
}
