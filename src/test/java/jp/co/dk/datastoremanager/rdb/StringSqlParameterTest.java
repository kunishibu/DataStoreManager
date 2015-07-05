package jp.co.dk.datastoremanager.rdb;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.text.ParseException;

import jp.co.dk.datastoremanager.DataStoreManagerTestFoundation;
import jp.co.dk.datastoremanager.rdb.StringSqlParameter;
import mockit.Expectations;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static jp.co.dk.datastoremanager.message.DataStoreManagerMessage.*;

@RunWith(Enclosed.class)
public class StringSqlParameterTest {

	public static class コンストラクタ extends DataStoreManagerTestFoundation{
		@Test
		public void constractor() throws ParseException {
			
		}
	}
	
	public static class 引数がＮＵＬＬの場合 extends DataStoreManagerTestFoundation{
		
		protected StringSqlParameter sut;
		
		@Before
		public void init() {
			this.sut = new StringSqlParameter(null);
			assertThat(this.sut.parameter, nullValue());
		}
		
		@Test
		public void set() {
			
		}
		@Test
		public void equals() {
			
		}
		@Test
		public void hashCodeTest() {
			
		}
		@Test
		public void toStringTest() {
			
		}
	}
	
	public static class 引数が文字列の場合 extends DataStoreManagerTestFoundation{
		@Test
		public void constractor() {
			
		}
	}
	
	public static class 引数が空文字の場合 extends DataStoreManagerTestFoundation{
		@Test
		public void constractor() {
			
		}
	}
	
}
