package jp.co.dk.datastoremanager.database;

import static org.junit.Assert.*;

import java.text.ParseException;

import jp.co.dk.datastoremanager.TestDataStoreManagerFoundation;

import org.junit.Test;

public class TestSql extends TestDataStoreManagerFoundation{
	
	@Test
	public void test_toString() throws ParseException {
		// SQLにパラメータを指定しなかった場合、toStringは指定のStringの値を返却すること
		Sql sql1 = new Sql("SELECT * FROM USERS");
		assertEquals(sql1.toString(), "SQL=[SELECT * FROM USERS] PARAMETER=[NOTHING]");
		
		// SQLにパラメータを指定した場合、toStringは指定のStringの値を返却すること
		Sql sql2 = new Sql("SELECT * FROM USERS WHERE USERID=? AND AGE=? AND BIRTHDAY=?");
		sql2.setParameter("1234567890");
		sql2.setParameter(20);
		sql2.setParameter(createDateByString("19800101000000"));
		assertEquals(sql2.toString(), "SQL=[SELECT * FROM USERS WHERE USERID=? AND AGE=? AND BIRTHDAY=?] PARAMETER=[1234567890(string), 20(int), 19800101000000(date)]");
	}

}
