package jp.co.dk.datastoremanager.rdb;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * カラムに付随するメタデータを表すクラスです。
 * カラムが所属するデータベース名、カラム名、カラムサイズ、カラムタイプなどを保持します。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class ColumnMetaData {
	
	/** メタデータオブジェクト */
	protected ResultSetMetaData metadata;
	
	/** インデックス */
	protected int index;
	
	/** データベース名 */
	protected String databaseName;
	
	/** テーブル名 */
	protected String tableName;

	/** カラム名 */
	protected String columnname;
	
	/** カラムサイズ */
	protected int size;
	
	/** カラムタイプ */
	protected String columnType;
	
	/**
	 * <p>コンストラクタ</p>
	 * ResultSetMetaDataと、インデックスを基にカラムメタデータオブジェクトを生成します。
	 * 
	 * @param metaData メタデータオブジェト
	 * @param index インデックス 
	 * @throws SQLException データベースアクセスエラーが発生した場合
	 */
	protected ColumnMetaData(ResultSetMetaData metaData, int index) throws SQLException {
		this.metadata     = metaData;
		this.index        = index;
		this.databaseName = metaData.getCatalogName(index);
		this.tableName    = metaData.getTableName(index);
		this.columnname   = metaData.getColumnName(index);
		this.size         = metaData.getColumnDisplaySize(index);
		this.columnType   = metaData.getColumnTypeName(index);
	}
	
	/**
	 * データベース名を取得します。
	 * @return データベース名
	 */
	public String getDatabaseName() {
		return databaseName;
	}
	
	/**
	 * テーブル名を取得します。
	 * @return テーブル名
	 */
	public String getTableName() {
		return tableName;
	}
	
	/**
	 * カラム名を取得します。
	 * @return カラム名
	 */
	public String getColumnname() {
		return columnname;
	}
	
	/**
	 * カラムサイズを取得します。
	 * @return カラムサイズ
	 */
	public int getSize() {
		return size;
	}

	/**
	 * カラムのＳＱＬ型を取得します。
	 * @return カラムのＳＱＬ型
	 */
	public String getColumnType() {
		return columnType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		result = prime * result
				+ ((metadata == null) ? 0 : metadata.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColumnMetaData other = (ColumnMetaData) obj;
		if (index != other.index)
			return false;
		if (metadata == null) {
			if (other.metadata != null)
				return false;
		} else if (!metadata.equals(other.metadata))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ColumnMetaData [databaseName=");
		builder.append(databaseName);
		builder.append(", tableName=");
		builder.append(tableName);
		builder.append(", columnname=");
		builder.append(columnname);
		builder.append(", size=");
		builder.append(size);
		builder.append(", columnType=");
		builder.append(columnType);
		builder.append("]");
		return builder.toString();
	}
}