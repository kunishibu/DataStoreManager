package jp.co.dk.datastoremanager.testdbaccessobjects.record;

import jp.co.dk.datastoremanager.DataConvertable;
import jp.co.dk.datastoremanager.Record;
import jp.co.dk.datastoremanager.database.DataBaseRecord;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

/**
 * ユーザテーブルのレコードオブジェクト
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class UsersRecord implements DataConvertable {
	
	/** 名前 */
	private String name;
	
	/** 年齢 */
	private int age;
	
	/**
	 * 名前を取得する。
	 * 
	 * @return 名前
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 年齢を取得する。
	 * 
	 * @return 年齢
	 */
	public int getAge() {
		return age;
	}
	
	@Override
	public DataConvertable convert(DataBaseRecord dataBaseRecord) throws DataStoreManagerException {
		UsersRecord usersRecord = new UsersRecord();
		usersRecord.name   = dataBaseRecord.getString("NAME");
		usersRecord.age    = dataBaseRecord.getInt("AGE");
		return usersRecord;
	}

	@Override
	public DataConvertable convert(Record record) throws DataStoreManagerException {
		UsersRecord usersRecord = new UsersRecord();
		usersRecord.name   = record.getString(0);
		usersRecord.age    = record.getInt(1);
		return usersRecord;
	}
}
