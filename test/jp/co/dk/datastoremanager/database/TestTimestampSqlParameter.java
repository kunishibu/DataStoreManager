package jp.co.dk.datastoremanager.database;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import jp.co.dk.datastoremanager.TestDataStoreManagerFoundation;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

import org.junit.Test;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

public class TestTimestampSqlParameter extends TestDataStoreManagerFoundation{

	@Test
	public void constractor() throws ParseException {
		Timestamp timestamp = new Timestamp(super.createDateByString("20130101000000").getTime());
		// ==============================正常系==============================
		// コンストラクタに文字列オブジェクトを設定した場合、正常に値が設定できること。
		try {
			TimestampSqlParameter parameter = new TimestampSqlParameter(timestamp);
			assertEquals(parameter.parameter, timestamp);
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// ==============================異常系==============================
		// コンストラクタに文字列オブジェクトを設定した場合、正常に値が設定できること。
		try {
			TimestampSqlParameter parameter = new TimestampSqlParameter(null);
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), SQL_PARAMETER_IS_NOT_SET);
		}
		
	}
	
	@Test
	public void test_equals() throws ParseException {
		Timestamp timestamp1 = new Timestamp(super.createDateByString("20130101000000").getTime());
		Timestamp timestamp2 = new Timestamp(super.createDateByString("20130101000000").getTime());
		Timestamp timestamp3 = new Timestamp(super.createDateByString("20130101000000").getTime());
		
		Timestamp timestamp4 = new Timestamp(super.createDateByString("20130101000001").getTime());
		Timestamp timestamp5 = new Timestamp(super.createDateByString("20130102000000").getTime());
		
		// コンストラクタに文字列オブジェクトを設定した場合、正常に値が設定できること。
		try {
			TimestampSqlParameter parameter1 = new TimestampSqlParameter(timestamp1);
			TimestampSqlParameter parameter2 = new TimestampSqlParameter(timestamp2);
			TimestampSqlParameter parameter3 = new TimestampSqlParameter(timestamp3);
			
			List<Object> faileList = new ArrayList<Object>();
			TimestampSqlParameter faileParam01 = new TimestampSqlParameter(timestamp4);
			TimestampSqlParameter faileParam02 = new TimestampSqlParameter(timestamp5);
			faileList.add(faileParam01);
			faileList.add(faileParam02);
			testEquals(parameter1, parameter2, parameter3, faileList);
			
		} catch (DataStoreManagerException e) {
			fail(e);
		}
	}
	
	@Test
	public void test_toString() throws ParseException {
		Timestamp timestamp1 = new Timestamp(super.createDateByString("20130101000000").getTime());
		try {
			TimestampSqlParameter parameter1 = new TimestampSqlParameter(timestamp1);
			assertEquals(parameter1.toString(), "2013-01-01 00:00:00.0(timestamp)");
		} catch (DataStoreManagerException e) {
			fail(e);
		}
	}
}
