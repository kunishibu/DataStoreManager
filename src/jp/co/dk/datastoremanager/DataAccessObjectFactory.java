package jp.co.dk.datastoremanager;

import jp.co.dk.datastoremanager.database.DataBaseDataStore;

/**
 * DataStoreObjectFactoryは、データアクセスオブジェクトを生成するデータアクセスオブジェクトファクトリクラスが実装するインターフェースです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public interface DataAccessObjectFactory {
	
	/**
	 * 指定のデータストア種別、データベースデータストアからデータアクセスオブジェクトを取得します。
	 * 
	 * @param dataStoreKind     データストア種別
	 * @param dataBaseDataStore データベースデータストア
	 * @return データアクセスオブジェクト
	 */
	public DataAccessObject getDataAccessObject(DataStoreKind dataStoreKind, DataBaseDataStore dataBaseDataStore);
}
