package jp.co.dk.datastoremanager.database.rdb;

import static org.junit.Assert.*;
import jp.co.dk.datastoremanager.DataStoreKind;
import jp.co.dk.datastoremanager.database.rdb.RelationalDataBaseDataStoreParameter;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

import org.junit.Test;

public class TestRelationalDataBaseDataStoreParameter {
	
	/**
	 * 引数にすべて値を設定した場合、正常にインスタンスを生成できること。
	 */
	@Test
	public void constractor_success01() throws DataStoreManagerException {
		RelationalDataBaseDataStoreParameter target = new RelationalDataBaseDataStoreParameter(DataStoreKind.ORACLE, "server", "sid", "user", "password");
		assertEquals(target.getDataStoreKind(), DataStoreKind.ORACLE);
		assertEquals(target.getServer(), "server");
		assertEquals(target.getSid(), "sid");
		assertEquals(target.getUser(), "user");
		assertEquals(target.getPassword(), "password");
	}
	
	/**
	 * コンストラクタの引数にサーバ名にnullが設定されていた場合、例外が送出されること。
	 */
	@Test
	public void constractor_error01() {
		try {
			new RelationalDataBaseDataStoreParameter(DataStoreKind.ORACLE, null, "sid", "user", "password");
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DATASTORE_PARAMETER_CREATE_FAILE_SERVER_IS_NOT_SET);
			assertEquals(e.getMessage(), "サーバ名が設定されていません。");
		}
	}
	
	/**
	 * コンストラクタの引数にサーバ名に空文字が設定されていた場合、例外が送出されること。
	 */
	@Test
	public void constractor_error02() {
		try {
			new RelationalDataBaseDataStoreParameter(DataStoreKind.ORACLE, "", "sid", "user", "password");
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DATASTORE_PARAMETER_CREATE_FAILE_SERVER_IS_NOT_SET);
			assertEquals(e.getMessage(), "サーバ名が設定されていません。");
		}
	}
	
	/**
	 * コンストラクタの引数にSIDにnullが設定されていた場合、例外が送出されること。
	 */
	@Test
	public void constractor_error03() {
		try {
			new RelationalDataBaseDataStoreParameter(DataStoreKind.ORACLE, "server", null, "user", "password");
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DATASTORE_PARAMETER_CREATE_FAILE_SID_IS_NOT_SET);
			assertEquals(e.getMessage(), "SIDが設定されていません。");
		}
	}
	
	/**
	 * コンストラクタの引数にSIDに空文字が設定されていた場合、例外が送出されること。
	 */
	@Test
	public void constractor_error04() {
		try {
			new RelationalDataBaseDataStoreParameter(DataStoreKind.ORACLE, "server", "", "user", "password");
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DATASTORE_PARAMETER_CREATE_FAILE_SID_IS_NOT_SET);
			assertEquals(e.getMessage(), "SIDが設定されていません。");
		}
	}
	
	/**
	 * コンストラクタの引数にユーザにnullが設定されていた場合、例外が送出されること。
	 */
	@Test
	public void constractor_error05() {
		try {
			new RelationalDataBaseDataStoreParameter(DataStoreKind.ORACLE, "server", "sid", null, "password");
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DATASTORE_PARAMETER_CREATE_FAILE_USER_IS_NOT_SET);
			assertEquals(e.getMessage(), "ユーザIDが設定されていません。");
		}
	}
	
	/**
	 * コンストラクタの引数にユーザに空文字が設定されていた場合、例外が送出されること。
	 */
	@Test
	public void constractor_error06() {
		try {
			new RelationalDataBaseDataStoreParameter(DataStoreKind.ORACLE, "server", "sid", "", "password");
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DATASTORE_PARAMETER_CREATE_FAILE_USER_IS_NOT_SET);
			assertEquals(e.getMessage(), "ユーザIDが設定されていません。");
		}
	}
	
	/**
	 * コンストラクタの引数にパスワードにnullが設定されていた場合、例外が送出されること。
	 */
	@Test
	public void constractor_error07() {
		try {
			new RelationalDataBaseDataStoreParameter(DataStoreKind.ORACLE, "server", "sid", "user", null);
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DATASTORE_PARAMETER_CREATE_FAILE_PASSWORD_IS_NOT_SET);
			assertEquals(e.getMessage(), "パスワードが設定されていません。");
		}
	}
	
	/**
	 * コンストラクタの引数にパスワードに空文字が設定されていた場合、例外が送出されること。
	 */
	@Test
	public void constractor_error08() {
		try {
			new RelationalDataBaseDataStoreParameter(DataStoreKind.ORACLE, "server", "sid", "user", "");
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), DATASTORE_PARAMETER_CREATE_FAILE_PASSWORD_IS_NOT_SET);
			assertEquals(e.getMessage(), "パスワードが設定されていません。");
		}
	}
	
}
