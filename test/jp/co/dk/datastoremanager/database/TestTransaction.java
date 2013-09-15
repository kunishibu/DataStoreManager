package jp.co.dk.datastoremanager.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import jp.co.dk.datastoremanager.TestDataStoreManagerFoundation;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

import mockit.Expectations;

import org.junit.Test;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

public class TestTransaction extends TestDataStoreManagerFoundation{
	
	@Test
	public void constractor() {
		// ==============================正常系==============================
		// 引数に設定された接続先に接続出来た場合、インスタンス生成に成功し、フィールドに各値を保持していること
		try {
			Transaction target = new Transaction(this.getAccessableDataBaseAccessParameter());
			assertNotNull(target.connection);
			assertNotNull(target.dataBaseAccessParameter);
		} catch (DataStoreManagerException e) {
			fail(e);
		};
		
		// ==============================異常系==============================
		// 引数にNULLを指定した場合、例外が発生すること
		try {
			Transaction target = new Transaction(null);
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), PARAMETER_IS_NOT_SET);
		};
		
		// 引数に設定されたドライバークラスの読み込み出来なかった場合、例外が発生すること
		try {
			final DataBaseAccessParameter param = this.getAccessFaileDataBaseAccessParameter();
        	new Expectations(param) {{
        		param.getDriver(); result = DataBaseDriverConstants.POSTGRESSQL;
            }};
            Transaction target = new Transaction(param);
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), FAILE_TO_READ_DRIVER);
		};
		
		// 引数に設定された接続先に接続出来なかった場合、例外が発生すること
		try {
			final DataBaseAccessParameter param = this.getAccessFaileDataBaseAccessParameter();
            Transaction target = new Transaction(param);
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), FAILE_TO_CREATE_CONNECTION);
		};
	}
	
	@Test
	public void execute() throws DataStoreManagerException, ParseException {
		
		Transaction target = new Transaction(this.getAccessableDataBaseAccessParameter());
		
		// ＝＝＝＝＝＝＝＝テーブル作成＝＝＝＝＝＝＝＝
		// テーブルを作成
		target.createTable(createTableSql());
		// 再度同じテーブルを作成しようとした場合、例外が発生すること。
		try {
			target.createTable(createTableSql());
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), FAILE_TO_EXECUTE_SQL);
		}
		
		// ＝＝＝＝＝＝＝＝レコードを追加＝＝＝＝＝＝＝＝
		// レコードを追加
		target.insert(insertSql());
		// 再度レコードを追加しようとした場合、例外が発生すること。
		try {
			target.insert(insertSql());
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), FAILE_TO_EXECUTE_SQL);
		}
		
		// ＝＝＝＝＝＝＝＝レコードを更新＝＝＝＝＝＝＝＝
		// レコードを更新
		int updateCount01 = target.update(updateSql());
		assertEquals(updateCount01 , 1);
		
		// 再度更新しようした場合、更新件数が０件で有ること
		int updateCount02 = target.update(updateSql());
		assertEquals(updateCount02 , 0);
		
		// 不正なSQLを実行した場合、例外が発生すること
		try {
			target.update(updateFaileSql());
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), FAILE_TO_EXECUTE_SQL);
		}
		
		// ＝＝＝＝＝＝＝＝レコードを取得＝＝＝＝＝＝＝＝
		// レコードを取得
		ResultSet resultSet = target.select(selectSql());
		
		// 不正なSQLを実行した場合、例外が発生すること
		try {
			target.select(selectFaileSql());
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), FAILE_TO_EXECUTE_SQL);
		}
		
		// ＝＝＝＝＝＝＝＝レコードを削除＝＝＝＝＝＝＝＝
		// レコードを削除
		int deleteResult01 = target.delete(deleteSql());
		assertEquals(deleteResult01, 1);
		
		// 再度レコードを削除した場合、更新件数が０件であること
		int deleteResult02 = target.delete(deleteSql());
		assertEquals(deleteResult02, 0);
		
		// 不正なSQLを実行した場合、例外が発生すること
		try {
			target.delete(deleteFaileSql());
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), FAILE_TO_EXECUTE_SQL);
		}
		
		// ＝＝＝＝＝＝＝＝テーブルを削除＝＝＝＝＝＝＝＝
		// テーブルを削除
		target.dropTable(dropTableSql());
		// 再度同じテーブルを削除しようとした場合、例外が発生すること。
		try {
			target.dropTable(dropTableSql());
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), FAILE_TO_EXECUTE_SQL);
		}
		
		target.close();
	}
	
	@Test
	public void commit() throws DataStoreManagerException, ParseException, SQLException {
		// ==============================正常系==============================
		DataBaseAccessParameter param = this.getAccessableDataBaseAccessParameter();
		Transaction target_01 = new Transaction(param);
		Transaction target_02 = new Transaction(param);
		Transaction target_03 = new Transaction(param);
		
		// トランザクション０１にてテーブルを作成
		target_01.createTable(createTableSql());
		
		// トランザクション０２でレコードを追加);
		target_02.insert(insertSql());
		
		// トランザクション０２でレコードを取得、同じトランザクションのため、取得できる。
		ResultSet resultSe02_01 = target_02.select(selectCountSql());
		int result02_01 = -1;
		while(resultSe02_01.next()) result02_01 = resultSe02_01.getInt("CNT");
		assertEquals(result02_01, 1);
		
		// トランザクション０３でレコードを取得、異なるトランザクションのため、取得できない。
		ResultSet resultSe03_01 = target_03.select(selectCountSql());
		int result03_01 = -1;
		while(resultSe03_01.next()) result03_01 = resultSe03_01.getInt("CNT");
		assertEquals(result03_01, 0);
		
		// トランザクション０２でコミットを実施
		target_02.commit();
		
		// トランザクション０３でレコードを取得、コミットされたため、異なるトランザクションのでも取得できる。
		ResultSet resultSe03_02 = target_03.select(selectCountSql());
		int result03_02 = -1;
		while(resultSe03_02.next()) result03_02 = resultSe03_02.getInt("CNT");
		assertEquals(result03_02, 1);
		target_02.close();
		target_03.close();
		
		// テーブルを削除
		target_01.dropTable(dropTableSql());
		target_01.close();
		
		// ==============================異常系==============================
		// トランザクション開始後、クローズ後、コミットした場合、例外が発生すること。
		try {
			Transaction target_04 = new Transaction(param);
			target_04.close();
			target_04.commit();
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), FAILE_TO_COMMIT);
		}
		
	}
	
	@Test
	public void rollback() throws DataStoreManagerException, ParseException, SQLException {
		// ==============================正常系==============================
		DataBaseAccessParameter param = this.getAccessableDataBaseAccessParameter();
		Transaction target_01 = new Transaction(param);
		Transaction target_02 = new Transaction(param);
		Transaction target_03 = new Transaction(param);
		
		// トランザクション０１にてテーブルを作成
		target_01.createTable(createTableSql());
		
		// トランザクション０２でレコードを追加);
		target_02.insert(insertSql());
		
		// トランザクション０２でレコードを取得、同じトランザクションのため、取得できる。
		ResultSet resultSe02_01 = target_02.select(selectCountSql());
		int result02_01 = -1;
		while(resultSe02_01.next()) result02_01 = resultSe02_01.getInt("CNT");
		assertEquals(result02_01, 1);
		
		// トランザクション０３でレコードを取得、異なるトランザクションのため、取得できない。
		ResultSet resultSe03_01 = target_03.select(selectCountSql());
		int result03_01 = -1;
		while(resultSe03_01.next()) result03_01 = resultSe03_01.getInt("CNT");
		assertEquals(result03_01, 0);
		
		// トランザクション０２でロールバックを実施
		target_02.rollback();
		
		// トランザクション０２でレコードを取得、ロールバックしたため、取得できないこと。
		ResultSet resultSe02_02 = target_02.select(selectCountSql());
		int result02_02 = -1;
		while(resultSe02_02.next()) result02_02 = resultSe02_02.getInt("CNT");
		assertEquals(result02_02, 0);
		
		// トランザクション０３でレコードを取得、ロールバックしたため、取得できないこと。
		ResultSet resultSe03_02 = target_03.select(selectCountSql());
		int result03_02 = -1;
		while(resultSe03_02.next()) result03_02 = resultSe03_02.getInt("CNT");
		assertEquals(result03_02, 0);
		target_02.close();
		target_03.close();
		
		// テーブルを削除
		target_01.dropTable(dropTableSql());
		target_01.close();
		
		// ==============================異常系==============================
		// トランザクション開始後、クローズ後、コミットした場合、例外が発生すること。
		try {
			Transaction target_04 = new Transaction(param);
			target_04.close();
			target_04.rollback();
			fail();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), FAILE_TO_ROLLBACK);
		}
	}
	
	@Test
	public void close() {
		// ==============================正常系==============================
		// 正常系はcommit、rollbackにて実施済み
		
		// ==============================異常系==============================
		try {
			DataBaseAccessParameter param = this.getAccessableDataBaseAccessParameter();
			Transaction target_01 = new Transaction(param);
			target_01.close();
			target_01.close();
		} catch (DataStoreManagerException e) {
			assertEquals(e.getMessageObj(), FAILE_TO_CLOSE);
		}
	}
	
	@Test
	public void test_equals() throws DataStoreManagerException {
		DataBaseAccessParameter param = this.getAccessableDataBaseAccessParameter();
		Transaction target_01 = new Transaction(param);
		
		List<Object> difflist = new ArrayList<Object>();
		Transaction target_02 = new Transaction(param);
		difflist.add(target_02);
		testEquals(target_01, target_01, target_01, difflist);
	}
	
}
