package jp.co.dk.datastoremanager.database;

import static org.junit.Assert.*;

import java.text.ParseException;

import jp.co.dk.datastoremanager.TestDataStoreManagerFoundation;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;

import org.junit.Test;

public class TestSql extends TestDataStoreManagerFoundation{
	
	@Test
	public void test_toString() throws ParseException {
		// SQLにパラメータを指定しなかった場合、toStringは指定のStringの値を返却すること
		try {
			Sql sql1 = new Sql("SELECT * FROM USERS");
			assertEquals(sql1.toString(), "SQL=[SELECT * FROM USERS] PARAMETER=[NOTHING]");
		} catch (DataStoreManagerException e) {
			fail(e);
		}
		
		// SQLにパラメータを指定した場合、toStringは指定のStringの値を返却すること
		try {
			Sql sql2 = new Sql("SELECT * FROM USERS WHERE USERID=? AND AGE=? AND BIRTHDAY=?");
			sql2.setParameter("1234567890");
			sql2.setParameter(20);
			sql2.setParameter(createDateByString("19800101000000"));
			assertEquals(sql2.toString(), "SQL=[SELECT * FROM USERS WHERE USERID=? AND AGE=? AND BIRTHDAY=?] PARAMETER=[1234567890(string), 20(int), 19800101000000(date)]");
		} catch (DataStoreManagerException e) {
			fail(e);
		}
	}
	
	@Test
	public void setParameter_String() {
		// SQLにパラメータを設定した際に、正常にパラメータリストに追加されること
		try {
			Sql sql1 = new Sql("SELECT * FROM USERS WHERE USERID=?");
			
			assertEquals(sql1.toString(), "SQL=[SELECT * FROM USERS] PARAMETER=[NOTHING]");
		} catch (DataStoreManagerException e) {
			fail(e);
		}
	}
}
