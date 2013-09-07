package jp.co.dk.datastoremanager;

import java.util.HashMap;
import java.util.Map;

import jp.co.dk.datastoremanager.database.DataBaseAccessParameter;
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
	protected Map<DataBaseAccessParameter, DataStore> dataStorePool = new HashMap<DataBaseAccessParameter, DataStore>();
	
	/** データストアプロパティ */
	DataStoreManagerProperty dataStoreManagerProperty;
	
	public DataStoreManager(DataStoreManagerProperty dataStoreManagerProperty) throws DataStoreManagerException{
		if (dataStoreManagerProperty == null) throw new DataStoreManagerException(PROPERTY_IS_NOT_SET);
		this.dataStoreManagerProperty = dataStoreManagerProperty;
	}
	
	public void startTrunsaction() throws DataStoreManagerException {
		
	}
	
	public DataAccessObject getDataAccessObject(DaoConstants daoConstants) {
		return null;
	}
	
	public void finishTrunsaction() throws DataStoreManagerException {
		
	}
}
