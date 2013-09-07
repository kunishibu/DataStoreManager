package jp.co.dk.datastoremanager;

import jp.co.dk.datastoremanager.database.DataBaseRecord;

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
	 */
	public DataConvertable convert(DataBaseRecord dataBaseRecord);
	
	/**
	 * その他のレコードオブエジェクトから取得した単一のレコードを自身のクラスへ設定します。
	 * 
	 * @param dataBaseRecord 単一のデータベースレコード
	 */
	public DataConvertable convert(Record record);
}
