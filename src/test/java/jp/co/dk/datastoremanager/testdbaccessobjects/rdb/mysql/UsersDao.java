package jp.co.dk.datastoremanager.testdbaccessobjects.rdb.mysql;

import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

/**
 * ユーザテーブルへのDAOインターフェース
 * 
 * @version 1.0
 * @author D.Kanno
 */
public interface UsersDao extends jp.co.dk.datastoremanager.testdbaccessobjects.UsersDao {
	
	public void createTable() throws DataStoreManagerException;
	
	public void dropTable() throws DataStoreManagerException;
	
}
