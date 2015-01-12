package jp.co.dk.datastoremanager;

import jp.co.dk.datastoremanager.database.DataBaseRecord;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

/**
 * DataConvertableは、レコードの変換を行うクラスが実装するインターフェースです。<p/>
 * データベースから取得したレコード、CSVファイルではCSVファイル内の１行から値を取り出し、自身のクラス内に設定するクラスが実装するインターフェースです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public interface DataConvertable {
	
	/**
	 * データベースから取得した単一のレコードを自身のクラスへ設定します。
	 * 
	 * @param dataBaseRecord 単一のデータベースレコード
	 * @throws DataStoreManagerException 変換に失敗した場合
	 */
	public DataConvertable convert(DataBaseRecord dataBaseRecord) throws DataStoreManagerException ;
	
	/**
	 * その他のレコードオブエジェクトから取得した単一のレコードを自身のクラスへ設定します。
	 * 
	 * @param record 単一のデータベースレコード
	 * @throws DataStoreManagerException 変換に失敗した場合
	 */
	public DataConvertable convert(Record record) throws DataStoreManagerException ;
}
