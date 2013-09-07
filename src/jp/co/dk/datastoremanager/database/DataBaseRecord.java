package jp.co.dk.datastoremanager.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import jp.co.dk.datastoremanager.Record;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

/**
 * DataBaseRecordは、単一のデータベースレコードを表すオブジェクトが実装する抽象クラスです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class DataBaseRecord implements Record {
	
	/** レコードオブジェクト */
	private ResultSet resultSet;
	
	/**
	 * コンストラクタ<p/>
	 * 指定のレコードオブジェクトを基にデータベースレコードオブジェクトを生成します。
	 * 
	 * @param resultSet
	 */
	DataBaseRecord(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	
	/**
	 * このレコードから指定のカラム名の文字列を取得します。
	 * 
	 * @param Strung カラム名
	 * @return 文字列
	 * @throws DataStoreManagerException 値の取得に失敗した場合
	 */
	public String getString(String column) throws DataStoreManagerException {
		try {
			return this.resultSet.getString(column);
		} catch (SQLException e) {
			throw new DataStoreManagerException(GET_COLUMN_IS_FAILE_BY_NAME);
		}
	}
	
	/**
	 * このレコードから指定のカラム名の文字列を取得します。
	 * 
	 * @param Strung カラム名
	 * @return 数値
	 * @throws DataStoreManagerException 値の取得に失敗した場合
	 */
	public int getInt(String column) throws DataStoreManagerException {
		try {
			return this.resultSet.getInt(column);
		} catch (SQLException e) {
			throw new DataStoreManagerException(GET_COLUMN_IS_FAILE_BY_NAME);
		}
	}
	
	/**
	 * このレコードから指定のカラム名の文字列を取得します。
	 * 
	 * @param Strung カラム名
	 * @return 日付
	 * @throws DataStoreManagerException 値の取得に失敗した場合
	 */
	public Date getDate(String column) throws DataStoreManagerException {
		try {
			return this.resultSet.getDate(column);
		} catch (SQLException e) {
			throw new DataStoreManagerException(GET_COLUMN_IS_FAILE_BY_NAME);
		}
	}
	
	@Override
	public String getString(int index) throws DataStoreManagerException {
		try {
			return this.resultSet.getString(index);
		} catch (SQLException e) {
			throw new DataStoreManagerException(GET_COLUMN_IS_FAILE_BY_INDEX);
		}
	}
	
	@Override
	public int getInt(int index) throws DataStoreManagerException {
		try {
			return this.resultSet.getInt(index);
		} catch (SQLException e) {
			throw new DataStoreManagerException(GET_COLUMN_IS_FAILE_BY_INDEX);
		}
	}
	
	@Override
	public Date getDate(int index) throws DataStoreManagerException {
		try {
			return this.resultSet.getDate(index);
		} catch (SQLException e) {
			throw new DataStoreManagerException(GET_COLUMN_IS_FAILE_BY_INDEX);
		}
	};

}
