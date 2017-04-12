package jp.co.dk.datastoremanager.rdb.oracle;

import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.datastoremanager.rdb.ColumnMetaData;
import jp.co.dk.datastoremanager.rdb.DataBaseAccessParameter;
import jp.co.dk.datastoremanager.rdb.DataBaseDataStore;
import jp.co.dk.datastoremanager.rdb.Sql;
import jp.co.dk.datastoremanager.rdb.TableMetaData;
import jp.co.dk.datastoremanager.rdb.Transaction;

public class OracleDataBaseDataStore extends DataBaseDataStore {

	public OracleDataBaseDataStore(DataBaseAccessParameter dataBaseAccessParameter) {
		super(dataBaseAccessParameter);
	}
	
	@Override
	protected Transaction createTransaction(DataBaseAccessParameter dataBaseAccessParameter) throws DataStoreManagerException {
		return new OracleTransaction(dataBaseAccessParameter);
	}
}

class OracleTransaction extends Transaction {

	OracleTransaction(DataBaseAccessParameter dataBaseAccessParameter) throws DataStoreManagerException {
		super(dataBaseAccessParameter);
	}
	
	@Override
	protected TableMetaData createTableMetaData(Transaction transaction, String schma, String tableName) {
		return new OracleTableMetaData(transaction, schma, tableName);
	}
}

class OracleTableMetaData extends TableMetaData {

	OracleTableMetaData(Transaction transaction, String schemaName, String tableName) {
		super(transaction, schemaName, tableName);
	}

	@Override
	protected void createHistoryTable() throws DataStoreManagerException {
		Sql sql = new Sql("CREATE TABLE ");
		sql.add("H$").add(this.tableName).add(" AS ");
		sql.add("SELECT SYSDATE AS OPTM, '  ' AS OPTP, ");
		sql.add(this.tableName).add(".*").add(" FROM ").add(this.tableName);
		sql.add(" WHERE ").add("ROWNUM = 0");
		this.transaction.createTable(sql);
	}
	
	@Override
	protected void dropHistoryTable() throws DataStoreManagerException {
		Sql sql = new Sql("DROP TABLE ").add("H$").add(this.tableName);
		this.transaction.dropTable(sql);
	}
	
	@Override
	protected void createTriggerHistoryTable() throws DataStoreManagerException {
		this.createInsertTrigerForHistoryTable();
		this.createUpdateTrigerForHistoryTable();
		this.createDeleteTrigerForHistoryTable();
	}
	
	protected void createInsertTrigerForHistoryTable() throws DataStoreManagerException {
		// 通常どおりCREATE TRIGGERでトリガーを作成した場合、:NEWのコロン部でエラー発生
		// それを回避する為、CREATE TRIGGER文を文字列化し、その文をPLSQLにて実行することで回避する。
		Sql sql = new Sql("BEGIN EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER ").add("H$").add(this.tableName).add("_INS_TRG").add(" ");
		sql.add("AFTER INSERT ON ").add(this.tableName).add(" ").add("FOR EACH ROW ");
		sql.add("BEGIN ");
		sql.add("INSERT INTO ").add("H$").add(this.tableName).add(" VALUES( SYSDATE, ''IS''");
		for (ColumnMetaData column : this.getColumns()) sql.add(", ").add(":NEW.").add(column.getColumnname());
		sql.add(");");
		sql.add("END;'; END;");
		this.transaction.createTable(sql);
	}
	
	protected void createUpdateTrigerForHistoryTable() throws DataStoreManagerException {
		Sql sql = new Sql("BEGIN EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER ").add("H$").add(this.tableName).add("_UPD_TRG").add(" ");
		sql.add("AFTER UPDATE ON ").add(this.tableName).add(" ").add("FOR EACH ROW ");
		sql.add("BEGIN ");
		sql.add("INSERT INTO ").add("H$").add(this.tableName).add(" VALUES( SYSDATE, ''UO''");
		for (ColumnMetaData column : this.getColumns()) sql.add(", ").add(":OLD.").add(column.getColumnname());
		sql.add(");");
		sql.add("INSERT INTO ").add("H$").add(this.tableName).add(" VALUES( SYSDATE, ''UN''");
		for (ColumnMetaData column : this.getColumns()) sql.add(", ").add(":NEW.").add(column.getColumnname());
		sql.add(");");
		sql.add("END;'; END;");
		this.transaction.createTable(sql);
	}
	
	protected void createDeleteTrigerForHistoryTable() throws DataStoreManagerException {
		Sql sql = new Sql("BEGIN EXECUTE IMMEDIATE 'CREATE OR REPLACE TRIGGER ").add("H$").add(this.tableName).add("_DEL_TRG").add(" ");
		sql.add("AFTER DELETE ON ").add(this.tableName).add(" ").add("FOR EACH ROW ");
		sql.add("BEGIN ");
		sql.add("INSERT INTO ").add("H$").add(this.tableName).add(" VALUES( SYSDATE, ''DL''");
		for (ColumnMetaData column : this.getColumns()) sql.add(", ").add(":OLD.").add(column.getColumnname());
		sql.add(");");
		sql.add("END;'; END;");
		this.transaction.createTable(sql);
	}
	
	@Override
	protected void dropHistoryTrigger() throws DataStoreManagerException {
		this.dropInsertHistoryTrigger();
		this.dropUpdateHistoryTrigger();
		this.dropDeleteHistoryTrigger();
	}
	
	protected void dropInsertHistoryTrigger() throws DataStoreManagerException {
		Sql sql = new Sql("DROP TRIGGER ").add("H$").add(this.tableName).add("_INS_TRG");
		this.transaction.dropTable(sql);
	}
	
	protected void dropUpdateHistoryTrigger() throws DataStoreManagerException {
		Sql sql = new Sql("DROP TRIGGER ").add("H$").add(this.tableName).add("_UPD_TRG");
		this.transaction.dropTable(sql);
	}
	
	protected void dropDeleteHistoryTrigger() throws DataStoreManagerException {
		Sql sql = new Sql("DROP TRIGGER ").add("H$").add(this.tableName).add("_DEL_TRG");
		this.transaction.dropTable(sql);
	}
}

//  lk;++zxzzzzbvn yumlc                gf ,kn....... nm,. 