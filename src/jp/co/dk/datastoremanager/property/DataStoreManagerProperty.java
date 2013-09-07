package jp.co.dk.datastoremanager.property;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;

import jp.co.dk.property.AbstractProperty;
import jp.co.dk.property.exception.PropertyException;

/**
 * データストアに関するプロパティを定義するクラスです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class DataStoreManagerProperty extends AbstractProperty {
	
	public static DataStoreManagerProperty REQUEST_HEADER = new DataStoreManagerProperty("header.request.");
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたプロパティキーをもとにプロパティ定数クラスを生成します。
	 * 
	 * @param key プロパティキー
	 */
	protected DataStoreManagerProperty (String key) throws PropertyException {
		super(key);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたプロパティキー、デフォルト値をもとにプロパティ定数クラスを生成します。
	 * 
	 * @param key プロパティキー
	 * @param defaltValue デフォルト値
	 */
	protected DataStoreManagerProperty (String key, boolean defaltValue) throws PropertyException {
		super(key, defaltValue);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたプロパティキー、デフォルト値をもとにプロパティ定数クラスを生成します。
	 * 
	 * @param key プロパティキー
	 * @param defaltValue デフォルト値
	 */
	protected DataStoreManagerProperty (String key, byte defaltValue) throws PropertyException {
		super(key, defaltValue);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたプロパティキー、デフォルト値をもとにプロパティ定数クラスを生成します。
	 * 
	 * @param key プロパティキー
	 * @param defaltValue デフォルト値
	 */
	protected DataStoreManagerProperty (String key, double defaltValue) throws PropertyException {
		super(key, defaltValue);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたプロパティキー、デフォルト値をもとにプロパティ定数クラスを生成します。
	 * 
	 * @param key プロパティキー
	 * @param defaltValue デフォルト値
	 */
	protected DataStoreManagerProperty (String key, float defaltValue) throws PropertyException {
		super(key, defaltValue);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたプロパティキー、デフォルト値をもとにプロパティ定数クラスを生成します。
	 * 
	 * @param key プロパティキー
	 * @param defaltValue デフォルト値
	 */
	protected DataStoreManagerProperty (String key, int defaltValue) throws PropertyException {
		super(key, defaltValue);
	}

	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたプロパティキー、デフォルト値をもとにプロパティ定数クラスを生成します。
	 * 
	 * @param key プロパティキー
	 * @param defaltValue デフォルト値
	 */
	protected DataStoreManagerProperty (String key, long defaltValue) throws PropertyException {
		super(key, defaltValue);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたプロパティキー、デフォルト値をもとにプロパティ定数クラスを生成します。
	 * 
	 * @param key プロパティキー
	 * @param defaltValue デフォルト値
	 */
	protected DataStoreManagerProperty (String key, short defaltValue) throws PropertyException {
		super(key, defaltValue);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたプロパティキー、デフォルト値をもとにプロパティ定数クラスを生成します。
	 * 
	 * @param key プロパティキー
	 * @param defaltValue デフォルト値
	 */
	protected DataStoreManagerProperty (String key, BigDecimal defaltValue) throws PropertyException {
		super(key, defaltValue);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたプロパティキー、デフォルト値をもとにプロパティ定数クラスを生成します。
	 * 
	 * @param key プロパティキー
	 * @param defaltValue デフォルト値
	 */
	protected DataStoreManagerProperty (String key, String defaltValue) throws PropertyException {
		super(key, defaltValue);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたプロパティファイルパス、プロパティキーをもとにプロパティ定数クラスを生成します。
	 * 
	 * @param file プロパティファイルパス
	 * @param key  プロパティキー
	 */
	protected DataStoreManagerProperty (File file, String key) throws PropertyException {
		super(file, key);
	}
	

	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたプロパティファイルパス、プロパティキー、デフォルト値をもとにプロパティ定数クラスを生成します。
	 * 
	 * @param file プロパティファイルパス
	 * @param key  プロパティキー
	 * @param defaltValue デフォルト値
	 */
	protected DataStoreManagerProperty (File file, String key, boolean defaltValue) throws PropertyException {
		super(file, key, defaltValue);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたプロパティファイルパス、プロパティキー、デフォルト値をもとにプロパティ定数クラスを生成します。
	 * 
	 * @param file プロパティファイルパス
	 * @param key  プロパティキー
	 * @param defaltValue デフォルト値
	 */
	protected DataStoreManagerProperty (File file, String key, byte defaltValue) throws PropertyException {
		super(file, key, defaltValue);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたプロパティファイルパス、プロパティキー、デフォルト値をもとにプロパティ定数クラスを生成します。
	 * 
	 * @param file プロパティファイルパス
	 * @param key  プロパティキー
	 * @param defaltValue デフォルト値
	 */
	protected DataStoreManagerProperty (File file, String key, double defaltValue) throws PropertyException {
		super(file, key, defaltValue);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたプロパティファイルパス、プロパティキー、デフォルト値をもとにプロパティ定数クラスを生成します。
	 * 
	 * @param file プロパティファイルパス
	 * @param key  プロパティキー
	 * @param defaltValue デフォルト値
	 */
	protected DataStoreManagerProperty (File file, String key, float defaltValue) throws PropertyException {
		super(file, key, defaltValue);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたプロパティファイルパス、プロパティキー、デフォルト値をもとにプロパティ定数クラスを生成します。
	 * 
	 * @param file プロパティファイルパス
	 * @param key  プロパティキー
	 * @param defaltValue デフォルト値
	 */
	protected DataStoreManagerProperty (File file, String key, int defaltValue) throws PropertyException {
		super(file, key, defaltValue);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたプロパティファイルパス、プロパティキー、デフォルト値をもとにプロパティ定数クラスを生成します。
	 * 
	 * @param file プロパティファイルパス
	 * @param key  プロパティキー
	 * @param defaltValue デフォルト値
	 */
	protected DataStoreManagerProperty (File file, String key, long defaltValue) throws PropertyException {
		super(file, key, defaltValue);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたプロパティファイルパス、プロパティキー、デフォルト値をもとにプロパティ定数クラスを生成します。
	 * 
	 * @param file プロパティファイルパス
	 * @param key  プロパティキー
	 * @param defaltValue デフォルト値
	 */
	protected DataStoreManagerProperty (File file, String key, short defaltValue) throws PropertyException {
		super(file, key, defaltValue);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたプロパティファイルパス、プロパティキー、デフォルト値をもとにプロパティ定数クラスを生成します。
	 * 
	 * @param file プロパティファイルパス
	 * @param key  プロパティキー
	 * @param defaltValue デフォルト値
	 */
	protected DataStoreManagerProperty (File file, String key, BigDecimal defaltValue) throws PropertyException {
		super(file, key, defaltValue);
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたプロパティファイルパス、プロパティキー、デフォルト値をもとにプロパティ定数クラスを生成します。
	 * 
	 * @param file プロパティファイルパス
	 * @param key  プロパティキー
	 * @param defaltValue デフォルト値
	 */
	protected DataStoreManagerProperty (File file, String key, String defaltValue) throws PropertyException {
		super(file, key, defaltValue);
	}
	
	
	public List<DataStoreParameter> getDataStoreParameters() {
		List</> dataStoreParmaterList = new ArrayList<DataStoreParameter>();
		return dataStoreParmaterList;
	}
	
	
	
}
