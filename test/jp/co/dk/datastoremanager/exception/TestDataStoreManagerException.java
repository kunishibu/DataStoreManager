package jp.co.dk.datastoremanager.exception;

import java.util.ArrayList;
import java.util.List;

import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Test;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

public class TestDataStoreManagerException extends TestCaseTemplate{

	@Test
	public void constractor() {
		// 指定のメッセージで、例外を生成されることを確認。
		DataStoreManagerException exception1 = new DataStoreManagerException(PROPERTY_IS_NOT_SET);
		
		// 指定のメッセージと置換文字列で、例外を生成されることを確認。
		DataStoreManagerException exception2 = new DataStoreManagerException(PROPERTY_IS_NOT_SET, "");
		
		// 指定のメッセージと置換文字列一覧で、例外を生成されることを確認。
		DataStoreManagerException exception3 = new DataStoreManagerException(PROPERTY_IS_NOT_SET, new ArrayList<String>());
		
		// 指定のメッセージと置換文字列一覧で、例外を生成されることを確認。
		String[] arrays1= {};
		DataStoreManagerException exception4 = new DataStoreManagerException(PROPERTY_IS_NOT_SET, arrays1);
		
		// 指定のメッセージ、例外で、例外を生成されることを確認。
		DataStoreManagerException exception5 = new DataStoreManagerException(PROPERTY_IS_NOT_SET, new Exception());
		
		// 指定のメッセージと置換文字列、例外で、例外を生成されることを確認。
		DataStoreManagerException exception6 = new DataStoreManagerException(PROPERTY_IS_NOT_SET, "", new Exception());
		
		// 指定のメッセージと置換文字列一覧、例外で、例外を生成されることを確認。
		List<String> list2 = new ArrayList<String>();
		DataStoreManagerException exception7 = new DataStoreManagerException(PROPERTY_IS_NOT_SET, list2, new Exception());
		
		// 指定のメッセージと置換文字列一覧、例外で、例外を生成されることを確認。
		String[] arrays2= {};
		DataStoreManagerException exception8 = new DataStoreManagerException(PROPERTY_IS_NOT_SET, arrays2, new Exception());
		
	}

}
