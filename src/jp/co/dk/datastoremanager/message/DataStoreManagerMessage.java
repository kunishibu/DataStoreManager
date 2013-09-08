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
	public static final DataStoreManagerMessage FAILE_TO_READ_DRIVER = new DataStoreManagerMessage("E012");
	
	/** SQLに対してパラメータを設定を行った際に例外が発生しました。 */
	public static final DataStoreManagerMessage AN_EXCEPTION_OCCURRED_WHEN_PERFORMING_THE_SET_PARAMETERS_TO_SQL = new DataStoreManagerMessage("E012");
	
	/** 対応していないデータストア種別が設定されています。 */
	public static final DataStoreManagerMessage NO_SUPPORT_DATA_STORE_KIND = new DataStoreManagerMessage("E013");
	
	/** 対応していないデータストア種別が設定されています。 */
	public static final DataStoreManagerMessage NO_SUPPORT_DATA_STORE_KIND = new DataStoreManagerMessage("E013");
	
	/** 対応していないデータストア種別が設定されています。 */
	public static final DataStoreManagerMessage NO_SUPPORT_DATA_STORE_KIND = new DataStoreManagerMessage("E013");
	
	/** 対応していないデータストア種別が設定されています。 */
	public static final DataStoreManagerMessage NO_SUPPORT_DATA_STORE_KIND = new DataStoreManagerMessage("E013");
	
	/** 対応していないデータストア種別が設定されています。 */
	public static final DataStoreManagerMessage NO_SUPPORT_DATA_STORE_KIND = new DataStoreManagerMessage("E013");
	
	protected DataStoreManagerMessage(String messageId) {
		super(messageId);
	}

}
