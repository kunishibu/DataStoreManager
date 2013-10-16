package jp.co.dk.datastoremanager.testdbaccessobjects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import jp.co.dk.datastoremanager.TestDataStoreManagerFoundation;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.datastoremanager.testdbaccessobjects.record.UsersRecord;
import jp.co.dk.datastoremanager.testdbaccessobjects.table.mysql.UserDaoImpl;

import org.junit.Test;

public class TestDataBaseAccess extends TestDataStoreManagerFoundation{

	@Test
	public void access() throws DataStoreManagerException {
		UserDaoImpl UserDaoImpl = new UserDaoImpl(super.getAccessableDataBaseAccessParameter());
		try {
			UserDaoImpl.createTable();
			// =========================================================
			// INSERTデータ定義
			String stringData                  = "1234567890";
			int intData                        = 123;
			long longData                      = 1234567890L;
			Date dateData                      = new Date();
			Timestamp timestampData            = new Timestamp(new Date().getTime()) ;
			byte[] bytesData                   = {1,2,3};
			ArrayList<String> objectData       = new ArrayList<String>();
			objectData.add("objectData1");
			objectData.add("objectData2");
			objectData.add("objectData3");
			ArrayList<String> convertBytesData = new ArrayList<String>();
			objectData.add("convertBytesData1");
			objectData.add("convertBytesData2");
			objectData.add("convertBytesData3");
			// =========================================================
			
			// INSERT処理を実行
			UserDaoImpl.insert(stringData, intData, longData, dateData, timestampData, bytesData, objectData, convertBytesData);
			
			// SELECT処理を実行
			UsersRecord userRecord  = UserDaoImpl.select(stringData);
			assertEquals(userRecord.getStringData()   , stringData);
			assertEquals(userRecord.getIntData()      , intData);
			assertEquals(userRecord.getLongData()     , longData);
			assertEquals(super.getStringByDate_YYYYMMDD(userRecord.getDateData()), super.getStringByDate_YYYYMMDD(dateData));
			assertEquals(super.getStringByDate_YYYYMMDDHH24MMDD(userRecord.getTimestampData()), super.getStringByDate_YYYYMMDDHH24MMDD(timestampData));
			for (int i=0; i<bytesData.length; i++) {
				assertEquals(userRecord.getBytesData()[i], bytesData[i]);
			}
			Serializable object = (Serializable)userRecord.getObjectData();
			if (object instanceof ArrayList.class);
			 
			assertEquals(userRecord.getBytesData()    , convertBytesData);
		} finally {
			try {
				UserDaoImpl.dropTable();
			} catch (DataStoreManagerException e) {	}
		}
	}

}
