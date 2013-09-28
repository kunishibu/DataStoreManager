package jp.co.dk.datastoremanager.database;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import jp.co.dk.datastoremanager.TestDataStoreManagerFoundation;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

import org.junit.Test;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

public class TestBytesSqlParameter extends TestDataStoreManagerFoundation{

	@Test
	public void constractor() throws ParseException {
		// ==============================正常系==============================
		// コンストラクタにバイト配列オブジェクトを設定した場合、正常に値が設定できること。
		try {
			byte[] bytes = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
			BytesSqlParameter parameter = new BytesSqlParameter(bytes);
			assertEquals(parameter.parameter, bytes);
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// コンストラクタにバイト配列オブジェクトを設定した場合、正常に値が設定できること。
		try {
			String byteString1 = "abc";
			String byteString2 = "ab";
			String byteString3 = "abcd";
			BytesSqlParameter parameter1 = new BytesSqlParameter(byteString1);
			BytesSqlParameter parameter2 = new BytesSqlParameter(byteString2);
			BytesSqlParameter parameter3 = new BytesSqlParameter(byteString3);
			// 固定部６バイト＋長さ１バイト＋データ部
			assertEquals(parameter1.parameter.length, 10);
			assertEquals(parameter2.parameter.length, 9);
			assertEquals(parameter3.parameter.length, 11);
			
			List<String> byteList = new ArrayList<String>();
			byteList.add("a");
			byteList.add("b");
			byteList.add("c");
			BytesSqlParameter parameter4 = new BytesSqlParameter(byteList);
			assertEquals(parameter4.parameter.length, 70);
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// ==============================異常系==============================
		// コンストラクタにバイト配列オブジェクトのNULLを設定した場合、例外が送出されること。
		try {
			byte[] bytes = null;
			BytesSqlParameter parameter = new BytesSqlParameter(bytes);
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), SQL_PARAMETER_IS_NOT_SET);
		}
		
		// コンストラクタに空のバイト配列オブジェクトを設定した場合、例外が送出されること。
		try {
			byte[] bytes = {};
			BytesSqlParameter parameter = new BytesSqlParameter(bytes);
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), SQL_PARAMETER_IS_NOT_SET);
		}
		
		// コンストラクタにnullのオブジェクトを設定した場合、例外が送出されること。
		try {
			Object bytes = null ;
			BytesSqlParameter parameter = new BytesSqlParameter(bytes);
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), SQL_PARAMETER_IS_NOT_SET);
		}
		
	}
	
	@Test
	public void test_equals() throws ParseException {
		// コンストラクタにバイト配列オブジェクトを設定した場合、正常に値が設定できること。
		try {
			byte[] bytes1 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
			byte[] bytes2 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
			byte[] bytes3 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
			BytesSqlParameter parameter1 = new BytesSqlParameter(bytes1);
			BytesSqlParameter parameter2 = new BytesSqlParameter(bytes2);
			BytesSqlParameter parameter3 = new BytesSqlParameter(bytes3);
			
			List<Object> faileList = new ArrayList<Object>();
			byte[] bytes4 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
			byte[] bytes5 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
			BytesSqlParameter faileParam01 = new BytesSqlParameter(bytes4);
			BytesSqlParameter faileParam02 = new BytesSqlParameter(bytes5);
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
			byte[] bytes1 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
			BytesSqlParameter parameter1 = new BytesSqlParameter(bytes1);
			assertEquals(parameter1.toString(), "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15(byte[])");
		} catch (DataStoreManagerException e) {
			fail(e);
		}
	}
}
