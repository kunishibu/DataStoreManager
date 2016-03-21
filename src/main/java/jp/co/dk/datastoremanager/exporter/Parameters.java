package jp.co.dk.datastoremanager.exporter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.co.dk.datastoremanager.exception.DataStoreExporterException;
import jp.co.dk.datastoremanager.message.DataStoreExporterMessage;

public class Parameters {
	
	/** バインド変数フォーマット */
	protected static Pattern variableFormat = Pattern.compile("^(.+?)=(.+?)$");
	
	protected String[] parameterstr;
	
	protected Map<String, String> parameter = new HashMap<>();
	
	public Parameters(String[] parameters) throws DataStoreExporterException {
		if (parameters != null) {
			this.parameterstr = parameters;
			for(String paramStr : parameters) {
				Matcher variableMatcher = variableFormat.matcher(paramStr);
				if (variableMatcher.find()) {
					String name  = variableMatcher.group(1);
					String value = variableMatcher.group(2);
					parameter.put(name, value);
				} else {
					throw new DataStoreExporterException(DataStoreExporterMessage.PARAMETER_FORMAT_IS_INVALID, paramStr);
				}
			}
		}
	}
	
	public Map<String, String> getParameter() {
		return new HashMap<>(this.parameter);
	}
	
	@Override
	public String toString() {
		return this.parameter.toString();
	}
}
