package jp.co.dk.datastoremanager.database;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import jp.co.dk.datastoremanager.DataStoreManagerTestFoundation;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

import org.junit.Test;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

public class DateSqlParameterTest extends DataStoreManagerTestFoundation{

	@Test
	public void constractor() throws ParseException {
		// ==============================正常系==============================
		// コンストラクタに日付オブジェクトを設定した場合、正常に値が設定できること。
		try {
			DateSqlParameter parameter = new DateSqlParameter(super.createDateByString("20130101000000"));
			assertEquals(parameter.parameter.getTime(), super.createDateByString("20130101000000").getTime());
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// ==============================異常系==============================
		// コンストラクタに日付オブジェクトを設定した場合、正常に値が設定できること。
		try {
			new DateSqlParameter(null);
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), SQL_PARAMETER_IS_NOT_SET);
		}
	}
	
	@Test
	public void test_equals() throws ParseException {
		// コンストラクタに日付オブジェクトを設定した場合、正常に値が設定できること。
		try {
			DateSqlParameter parameter1 = new DateSqlParameter(super.createDateByString("20130101000000"));
			DateSqlParameter parameter2 = new DateSqlParameter(super.createDateByString("20130101000000"));
			DateSqlParameter parameter3 = new DateSqlParameter(super.createDateByString("20130101000000"));
			
			List<Object> faileList = new ArrayList<Object>();
			DateSqlParameter faileParam01 = new DateSqlParameter(super.createDateByString("20130101000001"));
			DateSqlParameter faileParam02 = new DateSqlParameter(super.createDateByString("20130102000000"));
			faileList.add(faileParam01);
			faileList.add(faileParam02);
			testEquals(parameter1, parameter2, parameter3, faileList);
			
		} catch (DataStoreManagerException e) {
			fail(e);
		}
	}
	
	@Test
	public void test_toString() throws ParseException {
		try {
			DateSqlParameter parameter1 = new DateSqlParameter(super.createDateByString("20130101000000"));
			assertEquals(parameter1.toString(), "20130101000000(date)");
		} catch (DataStoreManagerException e) {
			fail(e);
		}
	}
}
