package jp.co.dk.datastoremanager.command.createhtab;

import java.io.IOException;

import jp.co.dk.datastoremanager.DataStoreManager;
import jp.co.dk.datastoremanager.command.AbtractCommandControler;
import jp.co.dk.datastoremanager.command.exporter.Parameters;
import jp.co.dk.datastoremanager.command.exporter.SqlFile;
import jp.co.dk.datastoremanager.command.exporter.html.HtmlDBData;
import jp.co.dk.datastoremanager.exception.DataStoreExporterException;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.datastoremanager.property.DataStoreManagerProperty;
import jp.co.dk.datastoremanager.rdb.AbstractDataBaseAccessObject;
import jp.co.dk.property.exception.PropertyException;

import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

public class CreateHistoryTableControler extends AbtractCommandControler {
	
	@Override
	public void execute() {

		try (DataStoreManager dataStoreManager = new DataStoreManager(new DataStoreManagerProperty())) {
			
			java.io.File sqlFile    = new java.io.File(this.cmd.getOptionValue("f"));
			java.io.File outputFile = new java.io.File(this.cmd.getOptionValue("o"));
			Parameters   parameter  = new Parameters(this.cmd.getOptionValues("p"));
			
			dataStoreManager.startTrunsaction();
			AbstractDataBaseAccessObject dao = (AbstractDataBaseAccessObject)dataStoreManager.getDataAccessObject("default");
			
			SqlFile    sqlfile  = new SqlFile(sqlFile);
			sqlfile.setParameter(parameter);
			
			HtmlDBData htmlDBData = HtmlDBData.auto(outputFile, dao);
			htmlDBData.write(sqlfile);
			htmlDBData.write();
		} catch (DataStoreExporterException | DataStoreManagerException | PropertyException | IOException e) {
			System.out.println(e.toString());
			System.exit(1);
		}
	}
	
	
	@Override
	protected String getCommandName() {
		return "histtab";
	}

	@Override
	protected void getOptions(Options options) {
		options.addOption(OptionBuilder.isRequired(true ).hasArg(true ).withDescription("接続先データベース")	.withLongOpt("database").create("db"));
		options.addOption(OptionBuilder.isRequired(true ).hasArg(true ).withDescription("接続先データベースURL").withLongOpt("url").create("u"));
		options.addOption(OptionBuilder.isRequired(true ).hasArg(true ).withDescription("接続先データベースユーザ").withLongOpt("user").create("ur"));
		options.addOption(OptionBuilder.isRequired(true ).hasArg(true ).withDescription("接続先データベースパスワード").withLongOpt("password").create("ps"));
		
		options.addOption(OptionBuilder.isRequired(false).hasArg(false).withDescription("ヒストリーテーブルの状態を確認する")	.withLongOpt("info" ).create("i"));
		options.addOption(OptionBuilder.isRequired(false).hasArg(false).withDescription("ヒストリーテーブルを作成する").withLongOpt("create").create("o"));
		options.addOption(OptionBuilder.isRequired(false).hasArg(false).withDescription("ヒストリーテーブルを削除する").withLongOpt("drop").create("d"));
		
		options.addOption(OptionBuilder.isRequired(false).hasArg(true ).withArgName("tablename").withDescription("対象のテーブル名").withLongOpt("tablename").create("t"));
	}
	
	public static void main(String[] args) {
		CreateHistoryTableControler controler = new CreateHistoryTableControler();
		controler.execute(args);
	}
}
