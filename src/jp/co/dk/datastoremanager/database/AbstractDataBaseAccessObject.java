package jp.co.dk.datastoremanager.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.dk.datastoremanager.DataAccessObject;
import jp.co.dk.datastoremanager.DataConvertable;
import jp.co.dk.datastoremanager.DataStore;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

/**
 * AbstractDataBaseAccessObjectは、データベースにアクセスを行うデータアクセスオブジェクトが実装する基底クラスです。<p/>
 * 
 * このクラスはデータストアオブジェクトを内包し、そのデータストアに対してselect、insert、update、deleteを行います。<br/>
 * 例えば、"ORACLE"の"USERS"テーブルにアクセスする場合、"USERS"テーブルにアクセスするクラスを作成し、本クラスを継承し、その中でSQLオブジェクトを作成、<br/>
 * このメソッドのアクセスメソッドを使用することでSQLを実行、結果の取得をおこなうことができる。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public abstract class AbstractDataBaseAccessObject implements DataAccessObject{
	
	/** データストアオブジェクト */
	protected DataBaseDataStore dataStore;
	
	/**
	 * コンストラクタ<p/>
	 * 以下のパラメータを元に、データベースアクセスオブジェクトを生成、トランザクションを開始する。<br/>
	 * <br/>
	 * パラメータが不足している場合、またはトランザクション開始に失敗した場合、例外が送出される。<br/>
	 * <br/>
	 * ・データベースドライバー<br/>
	 * ・データベース接続先URLまたは、IPアドレス<br/>
	 * ・接続先データべース名称（ORACLEの場合、SID）<br/>
	 * ・データベース接続先ユーザ<br/>
	 * ・データベース接続先パスワード<br/>
	 * 
	 * @param driver   データベースドライバー
	 * @param url      データベース接続先URLまたは、IPアドレス
	 * @param sid      接続先データべース名称（ORACLEの場合、SID）
	 * @param user     データベース接続先ユーザ
	 * @param password データベース接続先パスワード
	 * @throws DataStoreManagerException パラメータが不足している場合、またはトランザクション開始に失敗した場合
	 */
	protected AbstractDataBaseAccessObject(DataBaseDriverConstants driver, String url, String sid, String user, String password) throws DataStoreManagerException {
		this(new DataBaseAccessParameter(driver.getDataStoreKind(), driver, url, sid, user, password));
	}
	
	/**
	 * コンストラクタ<p/>
	 * 以下のデータベースアクセスパラメータを元に、データベースアクセスオブジェクトを生成、トランザクションを開始する。<br/>
	 * <br/>
	 * パラメータが不足している場合、またはトランザクション開始に失敗した場合、例外が送出される。<br/>
	 * 
	 * @param driver   データベースドライバー
	 * @param url      データベース接続先URLまたは、IPアドレス
	 * @param sid      接続先データべース名称（ORACLEの場合、SID）
	 * @param user     データベース接続先ユーザ
	 * @param password データベース接続先パスワード
	 * @throws DataStoreManagerException パラメータが不足している場合、またはトランザクション開始に失敗した場合
	 */
	protected AbstractDataBaseAccessObject(DataBaseAccessParameter dataBaseAccessParameter) throws DataStoreManagerException {
		if (dataBaseAccessParameter == null) throw new DataStoreManagerException(DATA_ACCESS_OBJECT_IS_NOT_SET);
		this.dataStore = new DataBaseDataStore(dataBaseAccessParameter);
		this.dataStore.startTransaction();
	}
	
	/**
	 * コンストラクタ<p/>
	 * 指定のデータストアオブジェクトを元に、データベースアクセスオブジェクトを生成する。<br/>
	 * データストアがデータベース用データストアでなかった場合、例外が送出される。<br/>
	 * <br/>
	 * @param dataStore データストアオブジェクト
	 * @throws DataStoreManagerException データストアがデータベース用データストアでなかった場合
	 */
	protected AbstractDataBaseAccessObject(DataStore dataStore) throws DataStoreManagerException {
		if (!(dataStore instanceof DataBaseDataStore)) throw new DataStoreManagerException(FAILE_TO_CAST_DATA_STORE_OBJECT); 
		this.dataStore = (DataBaseDataStore)dataStore;
	}
	
	/**
	 * 指定のSQLを実行し、単一のレコードを取得する。<p/>
	 * 引数に指定されたSQLを実行し、指定のレコード変換オブジェクトを使用してレコードを変換、返却する。<br/>
	 * レコードが取得出来なかった場合はnullが返却される。<br/>
	 * レコードが複数取得された、SQLの実行に失敗した場合、例外が送出される。
	 * 
	 * @param sql 実行対象のSQLオブジェクト
	 * @param convertable レコード変換オブジェクト
	 * @return 取得したレコードオブジェクト
	 * @throws DataStoreManagerException レコードが複数取得された、SQLの実行に失敗した場合
	 */
	public <E extends DataConvertable> E selectSingle(Sql sql, E convertable) throws DataStoreManagerException{
		List<E> resultList = this.selectMulti(sql, convertable);
		if (resultList.size() == 0) return null;
		if (resultList.size() >  1) throw new DataStoreManagerException(GET_RECORD_IS_FAILE);
		return resultList.get(0);
	}
	
	/**
	 * 指定のSQLを実行し、複数のレコードを取得する。<p/>
	 * 引数に指定されたSQLを実行し、指定のレコード変換オブジェクトを使用してレコードを変換、返却する。<br/>
	 * レコードが取得出来なかった場合は空のリストが返却される。SQLの実行に失敗した場合、例外が送出される。<br/>
	 * 
	 * @param sql 実行対象のSQLオブジェクト
	 * @param convertable レコード変換オブジェクト
	 * @return 取得したレコードオブジェクトのリスト
	 * @throws DataStoreManagerException SQLの実行に失敗した場合
	 */
	public <E extends DataConvertable> List<E> selectMulti(Sql sql, E convertable) throws DataStoreManagerException{
		List<E> resultList = new ArrayList<E>();
		ResultSet result = this.dataStore.execute(sql);
		try {
			while (result.next()) {
				resultList.add((E)convertable.convert(new DataBaseRecord(result)));
			}
		} catch (SQLException e) {
			throw new DataStoreManagerException(GET_RECORD_IS_FAILE);
		}
		return resultList;
	}
}
