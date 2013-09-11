package jp.co.dk.datastoremanager.database;

import jp.co.dk.datastoremanager.DataStoreKind;

/**
 * DataBaseDriverConstantsは、各種データベースに接続する際のデータベースドライバー、接続先URLを定義する定数クラスです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public enum DataBaseDriverConstants {
	
	/** ORACLE */
	ORACLE("jdbc:oracle:thin:@servername:databasename", "oracle.jdbc.driver.OracleDriver", DataStoreKind.ORACLE),
	/** MYSQL */
	MYSQL("jdbc:mysql://servername/databasename", "com.mysql.jdbc.Driver", DataStoreKind.MYSQL),
	/** POSTGRESSQL */
	POSTGRESSQL("jdbc:postgresql://servername/databasename", "org.postgresql.Driver", DataStoreKind.POSTGRESQL),
	;
	
	/** URL */
	private String url;
	
	/** ドライバークラス */
	private String driverClass;
	
	/** データストア種別 */
	private DataStoreKind dataStoreKind;
	
	/**
	 * コンストラクタ<p/>
	 * 
	 * 
	 * @param url           データベース接続時のURL固定部
	 * @param driverClass   ドライバークラス名
	 * @param dataStoreKind データストア種別
	 */
	private DataBaseDriverConstants(String url, String driverClass, DataStoreKind dataStoreKind) {
		this.url           = url;
		this.driverClass   = driverClass;
		this.dataStoreKind = dataStoreKind;
	}
	
	/**
	 * 指定のサーバ名、データベース名を基に接続先URLを取得する。
	 * 
	 * @param servername   サーバ名
	 * @param databasename データベース名称
	 * @return 接続先URL
	 */
	String getUrl(String servername, String databasename) {
		String result1 = this.url.replaceAll("servername", servername);
		String result2 = result1.replaceAll("databasename", databasename);
		return result2;
	}
	
	/**
	 * ドライバークラス名を取得する。
	 * 
	 * @return ドライバークラス名
	 */
	String getDriverClass() {
		return this.driverClass;
	}
	/**
	 * データストア種別を取得する。
	 * 
	 * @return データストア種別
	 */
	DataStoreKind getDataStoreKind() {
		return this.dataStoreKind;
	}
	
}
