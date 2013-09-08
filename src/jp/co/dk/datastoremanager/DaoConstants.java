package jp.co.dk.datastoremanager;

public interface DaoConstants {
	
	/**
	 * このDAOのファクトリクラスを取得します。
	 * @return DAOファクトリクラスオブジェクト
	 */
	public DataAccessObjectFactory getDataAccessObjectFactory();
	
	/**
	 * このデータアクセスオブジェクトの名称を取得します。
	 * 
	 * @return データアクセスオブジェクトの名称
	 */
	public String getName();
}
