package jp.co.dk.datastoremanager;

import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

/**
 * データストアに接続する際に使用するパラメータを定義するクラスです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public abstract class DataStoreParameter {
	
	/** データストア種別 */
	private DataStoreKind dataStoreKind;
	
	/**
	 * コンストラクタ<p/>
	 * 指定のデータストア種別を元にインスタンスを生成します。
	 * 
	 * @param dataStoreKind データストア種別
	 * @throws DataStoreManagerException インスタンスの生成に失敗した場合
	 */
	protected DataStoreParameter(DataStoreKind dataStoreKind) throws DataStoreManagerException {
		if (dataStoreKind == null) throw new DataStoreManagerException(DATASTORE_PARAMETER_CREATE_FAILE_DATASTOREKIND_IS_NOT_SET);
		this.dataStoreKind = dataStoreKind;
	}
	
	/**
	 * 設定されたデータストア種別を取得する。
	 * 
	 * @return データストア種別
	 */
	public DataStoreKind getDataStoreKind() {
		return this.dataStoreKind;
	}
}
