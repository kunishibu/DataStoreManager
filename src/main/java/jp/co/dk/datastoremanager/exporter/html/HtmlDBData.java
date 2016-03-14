package jp.co.dk.datastoremanager.exporter.html;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import jp.co.dk.datastoremanager.exception.DataStoreExporterException;
import jp.co.dk.datastoremanager.exception.DataStoreExporterFatalException;
import jp.co.dk.datastoremanager.exception.DataStoreManagerException;
import jp.co.dk.datastoremanager.rdb.AbstractDataBaseAccessObject;
import jp.co.dk.datastoremanager.rdb.ColumnMetaData;
import jp.co.dk.datastoremanager.rdb.DataBaseRecord;
import jp.co.dk.datastoremanager.rdb.DataConvertable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import static jp.co.dk.datastoremanager.message.DataStoreExporterMessage.*;

public class HtmlDBData {
	
	protected AbstractDataBaseAccessObject dao;
	
	protected Document document;
	
	protected Element htmlElement;
	
	protected Element headElement;
	
	protected Element bodyElement;
	
	protected Element cssElement;
	
	public HtmlDBData(AbstractDataBaseAccessObject dao) throws DataStoreExporterFatalException {
		try {
			this.dao         = dao;
			
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			this.document    = docBuilder.newDocument();
			
			this.htmlElement = this.document.createElement("html");
			this.headElement = this.document.createElement("head");
			this.bodyElement = this.document.createElement("body");
			this.cssElement  = this.document.createElement("style");
			
			this.htmlElement.appendChild(this.headElement);
			this.htmlElement.appendChild(this.bodyElement);
			this.htmlElement.appendChild(this.cssElement);
			
			this.document.appendChild(this.htmlElement);
		} catch (ParserConfigurationException e) {
			throw new DataStoreExporterFatalException(FAILED_TO_GENERATE_HTML);
		}
	}
	
	public void write(jp.co.dk.datastoremanager.exporter.Sql sql) throws DataStoreManagerException {
		
		Element dataLabel = this.createNode("label");
		dataLabel.setTextContent(sql.toString());
		
		Element dataTable = this.createNode("table");
		dataTable.setAttribute("border", "1");
		Element headerTr  = this.createNode("tr");
		
		this.dao.selectMulti(sql, new DataConvertable() {
			public DataConvertable convert(DataBaseRecord dataBaseRecord) throws DataStoreManagerException {
				if (dataBaseRecord.getRowIndex() == 1) {
					for (ColumnMetaData columnMetaData : dataBaseRecord.getColumns()) {
						Element headerTd = createNode("td");
						headerTd.setTextContent(columnMetaData.getColumnname());
						headerTr.appendChild(headerTd);
						dataTable.appendChild(headerTr);
					}
				}
				Element dataTr = createNode("tr");
				for (ColumnMetaData columnMetaData : dataBaseRecord.getColumns()) {
					Element dataTd = createNode("td");
					dataTd.setTextContent(dataBaseRecord.getString(columnMetaData.getColumnname()));
					dataTr.appendChild(dataTd);
					dataTable.appendChild(dataTr);
				}
				return null;
			}
		});
		this.bodyElement.appendChild(dataLabel);
		this.bodyElement.appendChild(dataTable);
	}
	
	protected Element createNode(String tagName) {
		return this.document.createElement(tagName);
	}
	
	public void write(File outfile) throws DataStoreExporterException {
		try {
			TransformerFactory tfactory = TransformerFactory.newInstance(); 
	        Transformer transformer = tfactory.newTransformer();
	        transformer.transform(new DOMSource(this.document), new StreamResult(outfile));
		} catch (TransformerException e) {
			throw new DataStoreExporterException(FAILED_TO_CREATE_HTML, outfile.toString());
		}
	}
	
}
