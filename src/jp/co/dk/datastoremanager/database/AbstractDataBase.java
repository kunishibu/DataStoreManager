package jp.co.dk.datastoremanager.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.dk.datastoremanager.DataAccessObject;
import jp.co.dk.datastoremanager.DataConvertable;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

public abstract class AbstractDataBase implements DataAccessObject{
	
	/** トランザクションオブジェクト */
	protected Transaction transaction;
	
	/** データベースアクセスパラメータ */
	protected DataBaseAccessParameter parameter;
	
	protected AbstractDataBase(DataBaseDriverConstants driver, String url, String sid, String user, String password) throws DataStoreManagerException {
		this(new DataBaseAccessParameter(driver.getDataStoreKind(), driver, url, sid, user, password));
	}
	
	protected AbstractDataBase(DataBaseAccessParameter parameter) throws DataStoreManagerException {
		this.parameter   = parameter;
		this.transaction = Transaction.getTransaction(parameter);
	}
	
	
	public <E extends DataConvertable> E selectSingle(Sql sql, E convertable) throws DataStoreManagerException{
		List<E> resultList = this.selectMulti(sql, convertable);
		if (resultList.size() > 1) {
			throw new DataStoreManagerException(GET_RECORD_IS_FAILE);
		}
		return resultList.get(0);
	}
	
	
	public <E extends DataConvertable> List<E> selectMulti(Sql sql, E convertable) throws DataStoreManagerException{
		List<E> resultList = new ArrayList<E>();
		ResultSet result = this.transaction.execute(sql);
		try {
			while (result.next()) {
				resultList.add((E)convertable.convert(new DataBaseRecord(result)));
			}
		} catch (SQLException e) {
			throw new DataStoreManagerException(GET_RECORD_IS_FAILE);
		}
		return resultList;
	}
}
