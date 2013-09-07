package jp.co.dk.datastoremanager;

import java.util.Date;

import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

/**
 * Recordは、単一のレコードを表すオブジェクトが実装するインターフェースです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public interface Record {
	
	/**
	 * このレコードから指定のインデックスの文字列を取得します。
	 * 
	 * @param index インデックス
	 * @return 文字列
	 * @throws DataStoreManagerException 値の取得に失敗した場合
	 */
	public String getString(int index) throws DataStoreManagerException ;
	
	/**
	 * このレコードから指定のインデックスの文字列を取得します。
	 * 
	 * @param index インデックス
	 * @return 数値
	 * @throws DataStoreManagerException 値の取得に失敗した場合
	 */
	public int getInt(int index) throws DataStoreManagerException ;
	
	/**
	 * このレコードから指定のインデックスの文字列を取得します。
	 * 
	 * @param index インデックス
	 * @return 日付
	 * @throws DataStoreManagerException 値の取得に失敗した場合
	 */
	public Date getDate(int index) throws DataStoreManagerException ;
}
