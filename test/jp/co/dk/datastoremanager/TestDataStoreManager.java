package jp.co.dk.datastoremanager;

import org.junit.Test;

import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.test.template.TestCaseTemplate;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

public class TestDataStoreManager extends TestDataStoreManagerFoundation{
	
	/**
	 * コンストラクタにnullを指定した場合、例外が送出されること。
	 */
	@Test
	public void constractor_success01() {
		try {
			DataStoreManager target = new DataStoreManager(null);
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), PROPERTY_IS_NOT_SET);
		}
	}
	
	/**
	 * コンストラクタにnullを指定した場合、例外が送出されること。
	 */
	@Test
	public void constractor_error01() {
		try {
			DataStoreManager target = new DataStoreManager(null);
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), PROPERTY_IS_NOT_SET);
		}
	}
}
