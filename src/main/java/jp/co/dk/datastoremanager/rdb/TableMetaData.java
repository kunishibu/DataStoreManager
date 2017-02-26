package jp.co.dk.datastoremanager.rdb;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableMetaData {
	
	protected Transaction transaction;
	
	protected String schemaName;
	
	protected String tableName;
	
	TableMetaData(Transaction transaction, String schemaName, String tableName) {
		this.transaction = transaction;
		this.schemaName = schemaName;
		this.tableName = tableName;
	}

	public List<ColumnMetaData> getColumns() {
		try {
			List<ColumnMetaData> columnMetaDataList = new ArrayList<>();
			DatabaseMetaData dbmd = this.transaction.connection.getMetaData();
			ResultSet rs = dbmd.getColumns(null, this.schemaName, this.tableName, "%");
			for (int i=0; rs.next(); i++) columnMetaDataList.add(new ColumnMetaData(rs, i));
			return columnMetaDataList;
		} catch (SQLException e) {
			return null;
		}

	}
	
	@Override
	public String toString() {
		return this.tableName;
	}
	
}
