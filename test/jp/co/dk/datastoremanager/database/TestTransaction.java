package jp.co.dk.datastoremanager.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import jp.co.dk.datastoremanager.TestDataStoreManagerFoundation;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

import mockit.Expectations;

import org.junit.Test;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;
import static org.junit.Assert.*;

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
		
		// SQLはMYSQL
		Sql createSql  = new Sql("CREATE TABLE TEST_USERS( USERID VARCHAR(10), AGE INT(3), BIRTHDAY DATE );");
		target.createTable(createSql);
		
		Sql insertSql  = new Sql("INSERT INTO TEST_USERS( USERID, AGE, BIRTHDAY ) VALUES (?, ?, ?)");
		insertSql.setParameter("1234567890");
		insertSql.setParameter(20);
		insertSql.setParameter(super.createDateByString("20130101000000"));
		target.insert(insertSql);
		
		Sql uptedaSql  = new Sql("UPDATE TEST_USERS SET USERID=?, AGE=?, BIRTHDAY=? WHERE USERID=?");
		uptedaSql.setParameter("0987654321");
		uptedaSql.setParameter(21);
		uptedaSql.setParameter(super.createDateByString("20130102000000"));
		uptedaSql.setParameter("1234567890");
		int updateCount = target.update(uptedaSql);
		
		Sql selectSql  = new Sql("SELECT * FROM TEST_USERS WHERE USERID=?");
		selectSql.setParameter("0987654321");
		ResultSet resultSet = target.select(selectSql);
		
		Sql deleteSql  = new Sql("DELETE FROM TEST_USERS WHERE USERID=?");
		deleteSql.setParameter("0987654321");
		int       deleteResult      = target.delete(deleteSql);
		
		Sql dropTblSql = new Sql("DROP TABLE TEST_USERS");
		target.dropTable(dropTblSql);
		
		assertEquals(updateCount , 1);
		assertEquals(deleteResult, 1);
		
	}
	
	@Test
	public void commit() throws DataStoreManagerException, ParseException, SQLException {
		DataBaseAccessParameter param = this.getAccessableDataBaseAccessParameter();
		Transaction target_01 = new Transaction(param);
		Transaction target_02 = new Transaction(param);
		Transaction target_03 = new Transaction(param);
		// テーブルを作成
		Sql createSql  = new Sql("CREATE TABLE TEST_USERS( USERID VARCHAR(10), AGE INT(3), BIRTHDAY DATE );");
		target_01.createTable(createSql);
		
		// トランザクション０２でレコードを追加
		Sql insertSql  = new Sql("INSERT INTO TEST_USERS( USERID, AGE, BIRTHDAY ) VALUES (?, ?, ?)");
		insertSql.setParameter("1234567890");
		insertSql.setParameter(20);
		insertSql.setParameter(super.createDateByString("20130101000000"));
		target_02.insert(insertSql);
		
		// トランザクション０２でレコードを取得
		Sql selectSql02_01  = new Sql("SELECT COUNT(*) AS CNT FROM TEST_USERS WHERE USERID=?");
		selectSql02_01.setParameter("1234567890");
		ResultSet resultSe02_01 = target_02.select(selectSql02_01);
		int result02_01 = -1;
		while(resultSe02_01.next()) result02_01 = resultSe02_01.getInt("CNT");
		assertEquals(result02_01, 1);
		
		// トランザクション０３でレコードを取得
		Sql selectSql03_01  = new Sql("SELECT COUNT(*) AS CNT FROM TEST_USERS WHERE USERID=?");
		selectSql03_01.setParameter("1234567890");
		ResultSet resultSe03_01 = target_03.select(selectSql03_01);
		int result03_01 = -1;
		while(resultSe03_01.next()) result03_01 = resultSe03_01.getInt("CNT");
		assertEquals(result03_01, 0);
		
		// トランザクション０２でコミットを実施
		target_02.commit();
		
		// トランザクション０３でレコードを取得
		Sql selectSql03_02  = new Sql("SELECT COUNT(*) AS CNT FROM TEST_USERS WHERE USERID=?");
		selectSql03_02.setParameter("1234567890");
		ResultSet resultSe03_02 = target_03.select(selectSql03_02);
		int result03_02 = -1;
		while(resultSe03_02.next()) result03_02 = resultSe03_02.getInt("CNT");
		assertEquals(result03_02, 1);
		
		// テーブルを削除
		Sql dropTblSql = new Sql("DROP TABLE TEST_USERS");
		target_01.dropTable(dropTblSql);
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
