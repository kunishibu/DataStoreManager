package jp.co.dk.datastoremanager.message;

import jp.co.dk.message.AbstractMessage;

/**
 * DataStoreManagerMessageは、データストアの操作にて使用するメッセージを定義する定数クラスです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class DataStoreManagerMessage extends AbstractMessage{
	
	/** データストアが生成できませんでした。プロパティが設定されていません。 */
	public static final DataStoreManagerMessage PROPERTY_IS_NOT_SET = new DataStoreManagerMessage("E001");
	
	/** データストア種別に設定されている値が不正です。デタストア種別設定値=[{0}] */
	public static final DataStoreManagerMessage DATASTORE_KIND_VALUE_IS_FAILE = new DataStoreManagerMessage("E002");
	
	/** データストア種別が設定されていません。 */
	public static final DataStoreManagerMessage DATASTORE_PARAMETER_CREATE_FAILE_DATASTOREKIND_IS_NOT_SET = new DataStoreManagerMessage("E003");
	
	/** サーバ名が設定されていません。 */
	public static final DataStoreManagerMessage DATASTORE_PARAMETER_CREATE_FAILE_SERVER_IS_NOT_SET = new DataStoreManagerMessage("E004");
	
	/** SIDが設定されていません。 */
	public static final DataStoreManagerMessage DATASTORE_PARAMETER_CREATE_FAILE_SID_IS_NOT_SET = new DataStoreManagerMessage("E005");
	
	/** ユーザIDが設定されていません。 */
	public static final DataStoreManagerMessage DATASTORE_PARAMETER_CREATE_FAILE_USER_IS_NOT_SET = new DataStoreManagerMessage("E006");
	
	/** パスワードが設定されていません。 */
	public static final DataStoreManagerMessage DATASTORE_PARAMETER_CREATE_FAILE_PASSWORD_IS_NOT_SET = new DataStoreManagerMessage("E007");
	
	/** カラムの値の取得に失敗しました。COLUMN=[{0}] */
	public static final DataStoreManagerMessage GET_COLUMN_IS_FAILE_BY_NAME = new DataStoreManagerMessage("E008");
	
	/** カラムの値の取得に失敗しました。INDEX=[{0}] */
	public static final DataStoreManagerMessage GET_COLUMN_IS_FAILE_BY_INDEX = new DataStoreManagerMessage("E009");
	
	/** レコードの取得に失敗しました。 */
	public static final DataStoreManagerMessage GET_RECORD_IS_FAILE = new DataStoreManagerMessage("E010");
	
	/** 単一のレコード取得を行いましたが、複数レコード取得されました。 */
	public static final DataStoreManagerMessage GET_MORE_THAN_ONE_RECORD = new DataStoreManagerMessage("E011");
	
	/** SQLの実行に失敗しました。 */
	public static final DataStoreManagerMessage FAILE_TO_EXECUTE_SQL = new DataStoreManagerMessage("E012");
	
	/** ドライバーの読み込みに失敗しました。 */
	public static final DataStoreManagerMessage FAILE_TO_READ_DRIVER = new DataStoreManagerMessage("E013");
	
	/** SQLに対してパラメータを設定を行った際に例外が発生しました。 */
	public static final DataStoreManagerMessage AN_EXCEPTION_OCCURRED_WHEN_PERFORMING_THE_SET_PARAMETERS_TO_SQL = new DataStoreManagerMessage("E014");
	
	/** 対応していないデータストア種別が設定されています。 */
	public static final DataStoreManagerMessage NO_SUPPORT_DATA_STORE_KIND = new DataStoreManagerMessage("E015");
	
	/** URLが設定されていません。 */
	public static final DataStoreManagerMessage URL_IS_NOT_SET = new DataStoreManagerMessage("E016");
	
	/** SIDが設定されていません。 */
	public static final DataStoreManagerMessage SID_IS_NOT_SET = new DataStoreManagerMessage("E017");
	
	/** ユーザが設定されていません。 */
	public static final DataStoreManagerMessage USER_IS_NOT_SET = new DataStoreManagerMessage("E018");
	
	/** パスワードが設定されていません。 */
	public static final DataStoreManagerMessage PASSWORD_IS_NOT_SET = new DataStoreManagerMessage("E019");
	
	/** ドライバーが設定されていません。 */
	public static final DataStoreManagerMessage DRIVER_IS_NOT_SET = new DataStoreManagerMessage("E020");
	
	/** データアクセスオブジェクトの生成に失敗しました。 */
	public static final DataStoreManagerMessage FAILE_TO_CREATE_DATA_ACCESS_OBJECT = new DataStoreManagerMessage("E021");
	
	/** コネクションの生成に失敗しました。 */
	public static final DataStoreManagerMessage FAILE_TO_CREATE_CONNECTION = new DataStoreManagerMessage("E022");
	
	/** データストア種別が設定されていません。 */
	public static final DataStoreManagerMessage DATA_STORE_KIND_IS_NOT_SET = new DataStoreManagerMessage("E023");
	
	/** データストアオブジェクトの変換に失敗しました。 */
	public static final DataStoreManagerMessage FAILE_TO_CAST_DATA_STORE_OBJECT = new DataStoreManagerMessage("E024");
	
	/** データアクセスオブジェクトが設定されていません。 */
	public static final DataStoreManagerMessage DATA_ACCESS_OBJECT_IS_NOT_SET = new DataStoreManagerMessage("E025");
	
	/** トランザクションが開始していません。 */
	public static final DataStoreManagerMessage TRANSACTION_IS_NOT_START = new DataStoreManagerMessage("E026");
	
	/** コミットに失敗しました。 */
	public static final DataStoreManagerMessage FAILE_TO_COMMIT = new DataStoreManagerMessage("E027");
	
	/** ロールバックに失敗しました。 */
	public static final DataStoreManagerMessage FAILE_TO_ROLLBACK = new DataStoreManagerMessage("E028");
	
	/** SQLオブジェクトの生成に失敗しました。SQL本文が設定されていません。 */
	public static final DataStoreManagerMessage FAILE_TO_CREATE_SQL_OBJECT = new DataStoreManagerMessage("E029");
	
	/** SQLパラメータの値が設定されていません。 */
	public static final DataStoreManagerMessage SQL_PARAMETER_IS_NOT_SET = new DataStoreManagerMessage("E030");
	
	/** トランザクションが生成できませんでした。データストアパラメータオブジェクトが設定されていません。 */
	public static final DataStoreManagerMessage PARAMETER_IS_NOT_SET = new DataStoreManagerMessage("E031");
	
	protected DataStoreManagerMessage(String messageId) {
		super(messageId);
	}

}
