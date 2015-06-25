package jp.co.dk.datastoremanager.testdbaccessobjects.record;

import java.io.Serializable;
import java.util.Date;

import jp.co.dk.datastoremanager.DataConvertable;
import jp.co.dk.datastoremanager.Record;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.datastoremanager.rdb.DataBaseRecord;

/**
 * ユーザテーブルのレコードオブジェクト
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class UsersRecord implements DataConvertable {
	
	protected String stringData;
	
	protected int intData;
	
	protected long longData;
	
	protected Date dateData;
	
	protected Date timestampData;
	
	protected byte[] bytesData;
	
	protected Object serializableData;
	
	protected Object objectData;
	

	public String getStringData() {
		return stringData;
	}

	public int getIntData() {
		return intData;
	}

	public long getLongData() {
		return longData;
	}

	public Date getDateData() {
		return dateData;
	}

	public Date getTimestampData() {
		return timestampData;
	}

	public byte[] getBytesData() {
		return bytesData;
	}

	public Object getSerializableData() {
		return serializableData;
	}

	public Object getObjectData() {
		return objectData;
	}
	
	@Override
	public DataConvertable convert(DataBaseRecord dataBaseRecord) throws DataStoreManagerException {
		UsersRecord usersRecord = new UsersRecord();
		usersRecord.stringData       = dataBaseRecord.getString("STRING_DATA");
		usersRecord.intData          = dataBaseRecord.getInt("INT_DATA");
		usersRecord.longData         = dataBaseRecord.getLong("LONG_DATA");
		usersRecord.dateData         = dataBaseRecord.getDate("DATE_DATA");
		usersRecord.timestampData    = dataBaseRecord.getTimestamp("TIMESTAMP_DATA");
		usersRecord.bytesData        = dataBaseRecord.getBytes("BYTES_DATA");
		usersRecord.serializableData = dataBaseRecord.getObject("OBJECT_DATA");
		usersRecord.objectData       = dataBaseRecord.getObject("CONVERTBYTES_DATA");
		return usersRecord;
	}

	@Override
	public DataConvertable convert(Record record) throws DataStoreManagerException {
		UsersRecord usersRecord = new UsersRecord();
		usersRecord.stringData       = record.getString(1);
		usersRecord.intData          = record.getInt(2);
		usersRecord.longData         = record.getLong(3);
		usersRecord.dateData         = record.getDate(4);
		usersRecord.timestampData    = record.getTimestamp(5);
		usersRecord.bytesData        = record.getBytes(6);
		usersRecord.serializableData = (Serializable)record.getObject(7);
		usersRecord.objectData       = record.getBytes(8);
		return usersRecord;
	}
}
