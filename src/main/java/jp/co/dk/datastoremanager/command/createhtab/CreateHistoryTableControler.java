package jp.co.dk.datastoremanager.command.createhtab;

import java.util.List;

import jp.co.dk.datastoremanager.DataBaseDriverConstants;
import jp.co.dk.datastoremanager.command.AbtractCommandControler;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.datastoremanager.rdb.DataBaseAccessParameter;
import jp.co.dk.datastoremanager.rdb.DataBaseDataStore;
import jp.co.dk.datastoremanager.rdb.TableMetaData;

import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

public class CreateHistoryTableControler extends AbtractCommandControler {
	
	@Override
	public void execute() {

		try {
			DataBaseDriverConstants driver = DataBaseDriverConstants.getDataBaseDriverConstants(this.cmd.getOptionValue("db"));
			String url = this.cmd.getOptionValue("url");
			String user = this.cmd.getOptionValue("user");
			String pass = this.cmd.getOptionValue("pass");
			DataBaseAccessParameter dataBaseAccessParameter = new DataBaseAccessParameter(driver.getDataStoreKind(), driver, url, user, pass);
			DataBaseDataStore dataStore = (DataBaseDataStore)dataBaseAccessParameter.createDataStore();
			dataStore.startTransaction();
			List<TableMetaData> tableMetaDataList = dataStore.getTable();
			
			if (this.cmd.hasOption("i")) {
				for (TableMetaData tableMetaData : tableMetaDataList) {
					System.out.print(tableMetaData);
					if (tableMetaData.isExistsHistoryTable()) {
						System.out.println(" is exists.");
					} else {
						System.out.println(" is not exists.");
					}
				}
			}
			
		} catch (DataStoreManagerException e) {
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
		options.addOption(OptionBuilder.isRequired(true ).hasArg(true ).withDescription("接続先データベースURL").withLongOpt("url").create("url"));
		options.addOption(OptionBuilder.isRequired(true ).hasArg(true ).withDescription("接続先データベースユーザ").withLongOpt("user").create("user"));
		options.addOption(OptionBuilder.isRequired(true ).hasArg(true ).withDescription("接続先データベースパスワード").withLongOpt("password").create("pass"));
		
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
