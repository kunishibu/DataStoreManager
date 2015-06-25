package jp.co.dk.datastoremanager.gdb;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import jp.co.dk.datastoremanager.DataStoreManagerTestFoundation;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

import org.junit.Test;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

public class StringCypherParameterTest extends DataStoreManagerTestFoundation{

	@Test
	public void constractor() throws ParseException {
		// ==============================正常系==============================
		// コンストラクタに文字列オブジェクトを設定した場合、正常に値が設定できること。
		try {
			StringCypherParameter parameter = new StringCypherParameter("1234567890");
			assertEquals(parameter.parameter, "1234567890");
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// ==============================異常系==============================
		// コンストラクタに文字列オブジェクトを設定した場合、正常に値が設定できること。
		try {
			new StringCypherParameter(null);
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), SQL_PARAMETER_IS_NOT_SET);
		}
		
		// コンストラクタに文字列オブジェクトを設定した場合、正常に値が設定できること。
		try {
			new StringCypherParameter("");
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), SQL_PARAMETER_IS_NOT_SET);
		}
	}
	
	@Test
	public void test_equals() throws ParseException {
		// コンストラクタに文字列オブジェクトを設定した場合、正常に値が設定できること。
		try {
			StringCypherParameter parameter1 = new StringCypherParameter("1234567890");
			StringCypherParameter parameter2 = new StringCypherParameter("1234567890");
			StringCypherParameter parameter3 = new StringCypherParameter("1234567890");
			
			List<Object> faileList = new ArrayList<Object>();
			StringCypherParameter faileParam01 = new StringCypherParameter("12345678900");
			StringCypherParameter faileParam02 = new StringCypherParameter("123456789");
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
			StringCypherParameter parameter1 = new StringCypherParameter("1234567890");
			assertEquals(parameter1.toString(), "1234567890(string" +
					")");
		} catch (DataStoreManagerException e) {
			fail(e);
		}
	}
}
