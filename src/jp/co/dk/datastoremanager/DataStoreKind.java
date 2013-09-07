package jp.co.dk.datastoremanager;

import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

/**
 * 接続先のデータストアの種別を定義します。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public enum DataStoreKind {
	
	/** ORACLE */
	ORACLE("oracle"),
	
	/** MYSQL */
	MYSQL("mysql"),
	
	/** POSTGRESSQL */
	POSTGRESSQL("postgressql"),
	
	/** CSV */
	CSV("csv"),
	;
	
	/** データベース種別 */
	private String kind;
	
	/**
	 * コンストラクタ<p/>
	 * データベース種別名称を元にインスタンスを生成します。
	 * 
	 * @param kind データベース種別名称
	 */
	private DataStoreKind(String kind) {
		this.kind = kind;
	}
	
	public static DataStoreKind convert(String kind) throws DataStoreManagerException {
		for (DataStoreKind kindObj : DataStoreKind.values()) {
			if (kindObj.kind.equals(kind)) return kindObj;
		}
		if (kind == null) {
			throw new DataStoreManagerException(DATASTORE_KIND_VALUE_IS_FAILE, "null");
		} else {
			throw new DataStoreManagerException(DATASTORE_KIND_VALUE_IS_FAILE, kind);
		}
	}
}
