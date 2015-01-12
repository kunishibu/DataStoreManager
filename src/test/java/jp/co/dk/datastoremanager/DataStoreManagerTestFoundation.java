package jp.co.dk.datastoremanager;

import java.text.ParseException;

import jp.co.dk.datastoremanager.database.DataBaseAccessParameter;
import jp.co.dk.datastoremanager.database.DataBaseDriverConstants;
import jp.co.dk.datastoremanager.database.Sql;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.test.template.TestCaseTemplate;

public class DataStoreManagerTestFoundation extends TestCaseTemplate{
	
	/**
	 * アクセス可能なDBアクセスパラメータを設定したDataBaseAccessParameterを返却します。
	 * 
	 * @return DBアクセスパラメータ
	 * @throws DataStoreManagerException 引数が不足していた場合
	 */
	
	protected DataBaseAccessParameter getAccessableDataBaseAccessParameter() throws DataStoreManagerException {
		return new DataBaseAccessParameter(DataStoreKind.MYSQL, DataBaseDriverConstants.MYSQL, "192.168.11.104:3306", "test_db", "test_user", "123456");
	}
	
	/**
	 * アクセス不可能なDBアクセスパラメータを設定したDataBaseAccessParameterを返却します。
	 * 
	 * @return DBアクセスパラメータ
	 * @throws DataStoreManagerException 引数が不足していた場合
	 */
	protected DataBaseAccessParameter getAccessFaileDataBaseAccessParameter() throws DataStoreManagerException {
		return new DataBaseAccessParameter(DataStoreKind.MYSQL, DataBaseDriverConstants.MYSQL, "255.255.255.255:3306", "test_db", "test_user", "123456");
	}
	
	
	public Sql createTableSql() throws DataStoreManagerException {
		return new Sql("CREATE TABLE TEST_USERS( USERID VARCHAR(10) NOT NULL PRIMARY KEY, AGE INT(3), BIRTHDAY DATE );");
	}
	
	public Sql insertSql_1234567890() throws DataStoreManagerException, ParseException {
		Sql insertSql  = new Sql("INSERT INTO TEST_USERS( USERID, AGE, BIRTHDAY ) VALUES (?, ?, ?)");
		insertSql.setParameter("1234567890");
		insertSql.setParameter(20);
		insertSql.setParameter(super.createDateByString("20130101000000"));
		return insertSql;
	}
	
	public Sql updateSql_1234567890_to_0987654321() throws DataStoreManagerException, ParseException {
		Sql uptedaSql  = new Sql("UPDATE TEST_USERS SET USERID=?, AGE=?, BIRTHDAY=? WHERE USERID=?");
		uptedaSql.setParameter("0987654321");
		uptedaSql.setParameter(21);
		uptedaSql.setParameter(super.createDateByString("20130102000000"));
		uptedaSql.setParameter("1234567890");
		return uptedaSql;
	}
	
	public Sql updateFaileSql() throws DataStoreManagerException, ParseException {
		Sql uptedaSql  = new Sql("UPDATE TEST_USERS_ SET USERID=?, AGE=?, BIRTHDAY=? WHERE USERID=?");
		uptedaSql.setParameter("0987654321");
		uptedaSql.setParameter(21);
		uptedaSql.setParameter(super.createDateByString("20130102000000"));
		uptedaSql.setParameter("1234567890");
		return uptedaSql;
	}
	
	public Sql selectSql_1234567890() throws DataStoreManagerException {
		Sql selectSql  = new Sql("SELECT * FROM TEST_USERS WHERE USERID=?");
		selectSql.setParameter("1234567890");
		return selectSql;
	}
	
	public Sql selectCountSql_1234567890() throws DataStoreManagerException {
		Sql selectSql  = new Sql("SELECT COUNT(*) AS CNT FROM TEST_USERS WHERE USERID=?");
		selectSql.setParameter("1234567890");
		return selectSql;
	}
	
	public Sql selectSql_0987654321() throws DataStoreManagerException {
		Sql selectSql  = new Sql("SELECT * FROM TEST_USERS WHERE USERID=?");
		selectSql.setParameter("0987654321");
		return selectSql;
	}
	
	public Sql selectCountSql_0987654321() throws DataStoreManagerException {
		Sql selectSql  = new Sql("SELECT COUNT(*) AS CNT FROM TEST_USERS WHERE USERID=?");
		selectSql.setParameter("0987654321");
		return selectSql;
	}
	
	public Sql selectFaileSql() throws DataStoreManagerException {
		Sql selectSql  = new Sql("SELECT * FROM TEST_USERS_ WHERE USERID=?");
		selectSql.setParameter("1234567890");
		return selectSql;
	}
	
	public Sql deleteSql_1234567890() throws DataStoreManagerException {
		Sql deleteSql  = new Sql("DELETE FROM TEST_USERS WHERE USERID=?");
		deleteSql.setParameter("1234567890");
		return deleteSql;
	}
	
	public Sql deleteSql_0987654321() throws DataStoreManagerException {
		Sql deleteSql  = new Sql("DELETE FROM TEST_USERS WHERE USERID=?");
		deleteSql.setParameter("0987654321");
		return deleteSql;
	}
	
	public Sql deleteFaileSql() throws DataStoreManagerException {
		Sql deleteSql  = new Sql("DELETE FROM TEST_USERS_ WHERE USERID=?");
		deleteSql.setParameter("1234567890");
		return deleteSql;
	}
	
	public Sql dropTableSql() throws DataStoreManagerException {
		return new Sql("DROP TABLE TEST_USERS");
	}
}
