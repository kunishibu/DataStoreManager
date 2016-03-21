package jp.co.dk.datastoremanager.exporter.html;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static jp.co.dk.datastoremanager.message.DataStoreExporterMessage.*;

public class HtmlDBData {
	
	protected File file;
	
	protected AbstractDataBaseAccessObject dao;
	
	protected Document document;
	
	protected Element htmlElement;
	
	protected Element headElement;
	
	protected Element metaElement;
	
	protected Element bodyElement;
	
	protected NodeList dataList;
	
	protected Element cssElement;

	public static HtmlDBData create(File outfile, AbstractDataBaseAccessObject dao) {
		return new HtmlDBData(FileType.CREATE, outfile, dao);
	}
	
	public static HtmlDBData read(File outfile, AbstractDataBaseAccessObject dao) {
		return new HtmlDBData(FileType.READ  , outfile, dao);
	}
	
	protected HtmlDBData(FileType type, File outfile, AbstractDataBaseAccessObject dao) throws DataStoreExporterFatalException {
		try {
			this.file        = outfile;
			this.dao         = dao;
			
			if (type == FileType.CREATE) {
				DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				this.document    = docBuilder.newDocument();
				this.htmlElement = this.document.createElement("html");
				this.headElement = this.document.createElement("head");
				this.bodyElement = this.document.createElement("body");
				this.cssElement  = this.document.createElement("style");
				this.dataList    = this.bodyElement.getChildNodes();
				
				this.htmlElement.appendChild(this.headElement);
				this.htmlElement.appendChild(this.bodyElement);
				this.htmlElement.appendChild(this.cssElement);
				this.document.appendChild(this.htmlElement);
				
				this.cssElement.setTextContent(".header{background-color: #00bfff;}");
				
			} else {
				DocumentBuilderFactory docBuilderFactory= DocumentBuilderFactory.newInstance();
				docBuilderFactory.setValidating(false);
				DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
				
				this.document    = docBuilder.parse(this.file);
				this.htmlElement = (Element) this.document.getDocumentElement();
				this.headElement = (Element) this.htmlElement.getElementsByTagName("HEAD").item(0);
				this.bodyElement = (Element) this.htmlElement.getElementsByTagName("body").item(0);
				this.cssElement  = (Element) this.htmlElement.getElementsByTagName("style").item(0);
				this.dataList    = this.bodyElement.getChildNodes();
			}
		} catch (ParserConfigurationException e) {
			throw new DataStoreExporterFatalException(FAILED_TO_GENERATE_HTML);
		} catch (SAXException e) {
			throw new DataStoreExporterFatalException(FAILED_TO_GENERATE_HTML);
		} catch (IOException e) {
			throw new DataStoreExporterFatalException(FAILED_TO_GENERATE_HTML);
		}
	}
	
	public void write(jp.co.dk.datastoremanager.exporter.Sql sql) throws DataStoreManagerException {
		
		Element dataDiv  = this.createNode("div");
		
		Element dataLabel = this.createNode("label");
		dataLabel.setTextContent(sql.toString());
		
		Element dataTable = this.createNode("table");
		dataTable.setAttribute("border", "1");
		Element headerTr      = this.createNode("tr");
		Element headerTypeTr  = this.createNode("tr");
		headerTr.setAttribute("class", "header");
		headerTypeTr.setAttribute("class", "header");
		
		this.dao.selectMulti(sql, new DataConvertable() {
			public DataConvertable convert(DataBaseRecord dataBaseRecord) throws DataStoreManagerException {
				if (dataBaseRecord.getRowIndex() == 1) {
					for (ColumnMetaData columnMetaData : dataBaseRecord.getColumns()) {
						Element headerTd     = createNode("td");
						Element headerTypeTd = createNode("td");
						
						headerTd.setTextContent(columnMetaData.getColumnname());
						headerTypeTd.setTextContent(columnMetaData.getColumnType());
						
						headerTr.appendChild(headerTd);
						headerTypeTr.appendChild(headerTypeTd);
					}
					dataTable.appendChild(headerTr);
					dataTable.appendChild(headerTypeTr);
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
		dataDiv.appendChild(dataLabel);
		dataDiv.appendChild(dataTable);
		this.bodyElement.appendChild(dataDiv);		
	}
	
	protected Element createNode(String tagName) {
		return this.document.createElement(tagName);
	}
	
	public void write() throws DataStoreExporterException {
		try {
			TransformerFactory tfactory = TransformerFactory.newInstance(); 
	        Transformer transformer = tfactory.newTransformer();
	        transformer.transform(new DOMSource(this.document), new StreamResult(this.file));
		} catch (TransformerException e) {
			throw new DataStoreExporterException(FAILED_TO_CREATE_HTML, this.file.toString());
		}
	}
}

enum FileType {
	CREATE,
	READ,
	;
}
