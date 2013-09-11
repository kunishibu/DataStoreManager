package jp.co.dk.datastoremanager.testdbaccessobjects.table;

import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.datastoremanager.testdbaccessobjects.record.UsersRecord;

/**
 * ユーザテーブルへのDAOインターフェース
 * 
 * @version 1.0
 * @author D.Kanno
 */
public interface UsersDao {
	
	/**
	 * ユーザIDを元に、ユーザIDレコードを取得する。
	 * 
	 * @param userid ユーザID
	 * @return ユーザIDレコード
	 * @throws レコードの取得に失敗した場合
	 */
	public UsersRecord selectUser(String userid) throws DataStoreManagerException ;
}
