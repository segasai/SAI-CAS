/*
	   Copyright (C) 2005-2006 Sergey Koposov
   
    Author: Sergey Koposov
    Email: math@sai.msu.ru 
    http://lnfm1.sai.msu.ru/~math

    This file is part of SAI CAS

    SAI CAS is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    SAI CAS is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with SAI CAS; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/


package sai_cas;
import sai_cas.db.*;
import sai_cas.XMLCatalogFile.*;

import javax.xml.transform.stream.StreamSource;
import javax.xml.bind.*;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.Properties;
import java.util.Arrays;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URI;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.sql.*;

public class XMLCatalog
{
	static Logger logger = Logger.getLogger("sai_cas.XMLCatalog");

	private Catalog cat;

	public XMLCatalog(String catalogString) throws  XMLCatalogException
	{
		logger.info("The XMLCatalog constructor is running");
		Unmarshaller um = null;
		try
		{
			JAXBContext jc = JAXBContext.newInstance("sai_cas.XMLCatalogFile");
			um = jc.createUnmarshaller();
		}
		catch (JAXBException e)
		{
			logger.error("Error in unmarshaller creation:", e);
			throw new XMLCatalogException("Error in creating unmarshaller:\n Message: " + e.getMessage());			
		}
		try
		{
			cat = (Catalog) um.unmarshal(new StreamSource ( new StringReader( catalogString ))); 
		}
		catch (UnmarshalException e) 
		{
			logger.error("Error during unmarshalling:", e);
			throw new XMLCatalogException("Error during unmarshalling:\n Message: " + e.getMessage() + e.getCause());
		}
		catch (JAXBException e)
		{
			logger.error("Error during unmarshalling:", e);
			throw new XMLCatalogException("Error during unmarshalling:\n Message: " + e.getMessage() + e.getCause());
		}
		logger.info("The catalog successfully unmarshalled");
	}
	
	public XMLCatalog(URI uri) throws  XMLCatalogException
	{
		Unmarshaller um;
		JAXBContext jc;
		try
		{
			jc = JAXBContext.newInstance("sai_cas.XMLCatalogFile");
			um = jc.createUnmarshaller();
		}
		catch (JAXBException e)
		{
			logger.error("Error in unmarshaller creation:", e);
			throw new XMLCatalogException("Error in creating unmarshaller:\n Message: " + e.getMessage());			
		}

		try
		{
			cat=(Catalog)um.unmarshal(new File(uri));
		}
		catch (UnmarshalException e) 
		{
			logger.error("Error during unmarshalling:", e);
			throw new XMLCatalogException("Error during unmarshalling:\n Message: " + e.getMessage() + e.getCause());
		}
		catch (JAXBException e)
		{
			logger.error("Error during unmarshalling:", e);
			throw new XMLCatalogException("Error during unmarshalling:\n Message: " + e.getMessage() + e.getCause());
		}
	}
	
	public XMLCatalog(File file) throws XMLCatalogException
	{
		logger.info("The XMLCatalog constructor is running");
		Unmarshaller um = null;
		try
		{
			JAXBContext jc = JAXBContext.newInstance("sai_cas.XMLCatalogFile");
			um = jc.createUnmarshaller();
		}
		catch (JAXBException e)
		{
			logger.error("Error in unmarshaller creation:", e);
			throw new XMLCatalogException("Error in creating unmarshaller:\n Message: " + e.getMessage());			
		}
		try
		{
			cat = (Catalog) um.unmarshal(file);
		}
		catch (UnmarshalException e) 
		{
			logger.error("Error during unmarshalling:", e);
			throw new XMLCatalogException("Error during unmarshalling:\n Message: " + e.getMessage() + e.getCause());
		}
		catch (JAXBException e)
		{
			logger.error("Error during unmarshalling:", e);
			throw new XMLCatalogException("Error during unmarshalling:\n Message: " + e.getMessage() + e.getCause());
		}
		logger.info("The catalog successfully unmarshalled");
		
	}

	public XMLCatalog(DBInterface dbi, String catalog) throws SQLException, DBException, XMLCatalogException
	{
		if (!dbi.checkCatalogExists(catalog))
		{
			return;
		}
		cat = new Catalog();
		cat.setDescription(dbi.getCatalogDescription(catalog));
		cat.setInfo(dbi.getCatalogInfo(catalog));
		cat.setName(catalog);
		PropertyList pl = new PropertyList();
		Property p = new Property();
		List <Property> lpc = pl.getProperty();
		for (String s[]: dbi.getCatalogProperties(catalog))
		{
			p.setName(s[0]);
			p.setValue(s[1]);
			lpc.add(p);
		}
				
		List<Table> lt = cat.getTable();	
		for (String st: Arrays.asList(dbi.getTableNames(catalog)))
		{
			Table t = new Table();
			t.setName(st);
			t.setInfo(dbi.getTableInfo(catalog,st));
			t.setDescription(dbi.getTableInfo(catalog,st));
			pl = new PropertyList();
			List <Property> lpt = pl.getProperty();
			
			for (String s[]: dbi.getTableProperties(catalog, st))
			{
				p.setName(s[0]);
				p.setValue(s[1]);
				lpt.add(p);
			}
			t.setPropertyList(pl);

			List <Column> lc = t.getColumn();
			String [] units = dbi.getColumnUnits(catalog,st);
			String [] ucds = dbi.getColumnUCDs(catalog,st);
			String [] infos = dbi.getColumnInfos(catalog,st);
			String [] descriptions = dbi.getColumnDescriptions(catalog,st);
			String [] datatypes = dbi.getColumnDatatypes(catalog,st);

			int i = 0;

			for (String sc: Arrays.asList(dbi.getColumnNames(catalog, st)))
			{
				Column col = new Column();
				col.setName(sc);
				col.setInfo(infos[i]);
				col.setDescription(descriptions[i]);
				col.setUnit(units[i]);
				col.setUcd(ucds[i]);
				col.setDatatype(datatypes[i]);
				i++;
				lc.add(col);
			}
			lt.add(t);
		}

	}
	
	public String toString()
	{
		StringWriter sw = new StringWriter();
		try
		{
			JAXBContext context1 = JAXBContext.newInstance("sai_cas.XMLCatalogFile");        
			Marshaller m = context1.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(cat, sw);
		}
		catch (javax.xml.bind.JAXBException e)
		{
			/* Don't do anything right now */
			System.err.println(e.getMessage()+e.getCause());
		}
		return  sw.toString();
	}
	
	public void insertCatalogueToDB(DBInterface dbi) throws SQLException, DBException, XMLCatalogException
	{
		/*  !!!!!!!!!!!  IMPORTANT !!!!!!!!!!
		 *  I do the convertion to lower case.
		 */
		logger.debug("Beginning of DB work in inserting the catalogue... ");
		String catalogName = cat.getName().toLowerCase();
		String catalogInfo = cat.getInfo();
		String catalogDescription = cat.getDescription();
		List<String[]> catalogProperties;	
		try
		{
			catalogProperties = convertProperties(cat.getPropertyList().getProperty());	
		}
		catch (NullPointerException e)
		{
			catalogProperties = new ArrayList<String[]>();
		}
		dbi.setCatalogProperties(catalogName, catalogProperties);
		logger.debug("Inserting the catalogue metadata... ");		
		dbi.insertCatalog(catalogName);
		dbi.setCatalogInfo(catalogName, catalogInfo);
		dbi.setCatalogDescription(catalogName, catalogDescription);
		
		List<Table> tableList = cat.getTable();

		logger.debug("Looping through tables in the catalogue... ");				
		for(Table table : tableList)
		{
			/*  !!!!!!!!!!!  IMPORTANT !!!!!!!!!!
			 * I do the convertion to lower case.
			 */
			
			String tableName = table.getName().toLowerCase();
			logger.debug("Inserting the table: "+tableName);				
			
			String tableInfo = table.getInfo();
			String tableDescription = table.getDescription();
			List<String[]> tableProperties;
			try
			{
				tableProperties = convertProperties(table.getPropertyList().getProperty());	
			}
			catch (NullPointerException e)
			{
				tableProperties = new ArrayList<String[]>();
			}

			
			List<Column> columnList = table.getColumn();			
			List<String> datatypeList = new ArrayList<String>();
			List<String> columnNameList = new ArrayList<String>();
			List<String> unitList = new ArrayList<String>();
			List<String> ucdList = new ArrayList<String>();

			List<String> columnDescriptionList = new ArrayList<String>();
			List<String> columnInfoList = new ArrayList<String>();
			String unit, ucd, columnName, datatype, columnInfo, columnDescription;
			int ncols = columnList.size();
			
			for (Column column : columnList)
			{
				columnName = column.getName().toLowerCase();

				datatype = column.getDatatype();

				if (datatype == null) 
				{
					throw new XMLCatalogException("The datatype attribute MUST be specified for the column "+columnName);
				}

				/*  !!!!!!!!!!!  IMPORTANT !!!!!!!!!!
				 *   I do the convertion to lower case.
				 */
				unit =  column.getUnit();
				ucd = column.getUcd();
				columnDescription = column.getDescription();
				columnInfo = column.getInfo();
				/* TODO 
				 * I should write the handling of the column properties too
				 * Now I don't do that since in that loop I should kind of 
				 * create the list of lists of properties ... 
				 */
//				List<Property> columnProperties = table.getPropertyList().getProperty();
				datatypeList.add(datatype);
				columnNameList.add(columnName);
				unitList.add(unit);
				ucdList.add(ucd);
				columnDescriptionList.add(columnDescription);
				columnInfoList.add(columnInfo);
			}
			logger.debug("Inserting the columns, table metadata... ");				
			dbi.insertTable(catalogName, tableName, columnNameList, datatypeList, unitList, columnInfoList, columnDescriptionList);
			dbi.setUcds(catalogName, tableName, columnNameList, ucdList);
			dbi.setTableInfo(catalogName, tableName, tableInfo);
			dbi.setTableDescription(catalogName, tableName, tableDescription);
			dbi.setTableProperties(catalogName, tableName, tableProperties);
			logger.debug("Preparing to read the data... ");							
			/* Now we are handling the data in the table */			
			Data d = table.getData();
			
			if (d == null)
			{
				continue;
				/* That means that there is no data, or data reference for that table */
			}
			
			Tabledata td = d.getTabledata();
			
			if (td != null)
			
			{
				throw new XMLCatalogException("The data directly embedded (in <tabledata> tags) in the XML file  is not supported\nStore the data in the separate file (and use <externaldata> tag)");
			}
			
			Externaldata ed = d.getExternaldata();
			DataReader dr; 
			
			if (ed != null)
			{
				String format = ed.getFormat().value();
				
				if (format.equals("delimited"))
				{
					dr = new DelimitedDataReader(ed, ncols);
				}
				else if (format.equals("fixed-width"))
				{
					dr = new FixedWidthDataReader(ed, ncols);				
				}
				else
				{
					throw new XMLCatalogException("The format '"+format+"' of the data is not supported");
				}

				String[] datatypeArray = new String[datatypeList.size()];
				logger.debug("Preparing to insert the data into the DB... ");							
				dbi.prepareInsertingData(catalogName, tableName, datatypeList.toArray(datatypeArray));
				logger.debug("Inserting the data into the DB... ");							
				while (true)
				{
					String []sarr;
					try 
					{
						sarr = dr.getData();
					}
					catch (IOException e)
					{
						throw new XMLCatalogException("Problem with reading data from " + dr.currentURL.toString());
					}
					if (sarr == null)
					{
						break;
					}
					if (sarr.length!=ncols)
					{
						throw new XMLCatalogException("The number of columns in the data file ("+sarr.length+") is not equal to the number of the fields in the XML declaration("+ncols+")");
					}

					try
					{
						dbi.insertData(sarr);
					}
					catch (NumberFormatException e)
					{
						throw new XMLCatalogException("NumberFormatException in "+ dr.currentURL.toString()+ " "+e.getMessage());					
					}

				}
				logger.debug("The data seems to be ingested correctly");
			}
		}
		/* Set the permissions */
		dbi.allowCatalogueUse(catalogName);  
	}
	


	public void insertTableToDB(DBInterface dbi) throws SQLException, DBException, XMLCatalogException
	{
		/*  !!!!!!!!!!!  IMPORTANT !!!!!!!!!!
		 *  I do the convertion to lower case.
		 */
		logger.debug("Beginning of DB work in inserting the catalogue... ");
		String catalogName = cat.getName().toLowerCase();

		List<Table> tableList = cat.getTable();

		logger.debug("Looping through tables in the catalogue... ");				
		for(Table table : tableList)
		{
			/*  !!!!!!!!!!!  IMPORTANT !!!!!!!!!!
			 * I do the convertion to lower case.
			 */
			
			String tableName = table.getName().toLowerCase();
			logger.debug("Inserting the table: "+tableName);				
			
			String tableInfo = table.getInfo();
			String tableDescription = table.getDescription();
			List<String[]> tableProperties;
			try
			{
				tableProperties = convertProperties(table.getPropertyList().getProperty());	
			}
			catch (NullPointerException e)
			{
				tableProperties = new ArrayList<String[]>();
			}

			
			List<Column> columnList = table.getColumn();			
			List<String> datatypeList = new ArrayList<String>();
			List<String> columnNameList = new ArrayList<String>();
			List<String> unitList = new ArrayList<String>();
			List<String> ucdList = new ArrayList<String>();

			List<String> columnDescriptionList = new ArrayList<String>();
			List<String> columnInfoList = new ArrayList<String>();
			String unit, ucd, columnName, datatype, columnInfo, columnDescription;
			int ncols = columnList.size();
			
			for (Column column : columnList)
			{
				datatype = column.getDatatype();
				/*  !!!!!!!!!!!  IMPORTANT !!!!!!!!!!
				 *   I do the convertion to lower case.
				 */
				if (datatype == null)
				{
					throw new XMLCatalogException("Datatype attribute must be specified");
				}
				columnName = column.getName().toLowerCase();
				
				if (columnName == null)
				{
					throw new XMLCatalogException("The column name attribute must be specified");
				}
				unit =  column.getUnit();
				ucd = column.getUcd();
				columnDescription = column.getDescription();
				columnInfo = column.getInfo();
				/* TODO 
				 * I should write the handling of the column properties too
				 * Now I don't do that since in that loop I should kind of 
				 * create the list of lists of properties ... 
				 */
//				List<Property> columnProperties = table.getPropertyList().getProperty();
				datatypeList.add(datatype);
				columnNameList.add(columnName);
				unitList.add(unit);
				ucdList.add(ucd);
				columnDescriptionList.add(columnDescription);
				columnInfoList.add(columnInfo);
			}
			logger.debug("Inserting the columns, table metadata... ");				
			dbi.insertTable(catalogName, tableName, columnNameList, datatypeList, unitList, columnInfoList, columnDescriptionList);
			dbi.setUcds(catalogName, tableName, columnNameList, ucdList);
			dbi.setTableInfo(catalogName, tableName, tableInfo);
			dbi.setTableDescription(catalogName, tableName, tableDescription);
			dbi.setTableProperties(catalogName, tableName, tableProperties);
			logger.debug("Preparing to read the data... ");							
			/* Now we are handling the data in the table */			
			Data d = table.getData();
			
			if (d == null)
			{
				continue;
				/* That means that there is no data, or data reference for that table */
			}
			
			Tabledata td = d.getTabledata();
			
			if (td != null)
			
			{
				throw new XMLCatalogException("The data directly embedded (in <tabledata> tags) in the XML file  is not supported\nStore the data in the separate file (and use <externaldata> tag)");
			}
			
			Externaldata ed = d.getExternaldata();
			DataReader dr; 
			
			if (ed != null)
			{
				String format = ed.getFormat().value();
				
				if (format.equals("delimited"))
				{
					dr = new DelimitedDataReader(ed, ncols);
				}
				else if (format.equals("fixed-width"))
				{
					dr = new FixedWidthDataReader(ed, ncols);				
				}
				else
				{
					throw new XMLCatalogException("The format '"+format+"' of the data is not supported");
				}

				String[] datatypeArray = new String[datatypeList.size()];
				logger.debug("Preparing to insert the data into the DB... ");							
				dbi.prepareInsertingData(catalogName, tableName, datatypeList.toArray(datatypeArray));
				logger.debug("Inserting the data into the DB... ");							
				while (true)
				{
					String []sarr;
					try 
					{
						sarr = dr.getData();
					}
					catch (IOException e)
					{
						throw new XMLCatalogException("Problem with reading data from " + dr.currentURL.toString());
					}
					if (sarr == null)
					{
						break;
					}
					if (sarr.length!=ncols)
					{
						throw new XMLCatalogException("The number of columns in the data file ("+sarr.length+") is not equal to the number of the fields in the XML declaration("+ncols+")");
					}

					try
					{
						dbi.insertData(sarr);
					}
					catch (NumberFormatException e)
					{
						throw new XMLCatalogException("NumberFormatException in "+ dr.currentURL.toString()+ " "+e.getMessage());					
					}

				}
				logger.debug("The data seems to be ingested correctly");
				dbi.allowTableUse(catalogName,tableName);  

			}
		}
		/* Set the permissions */
	}
	



	public List<String[]> convertProperties(List<Property> propertyList)
	{
		List<String[]> result = new ArrayList<String[]>();
		//String[] propertyPair = new String[2];
		for (Property p: propertyList)
		{
			String[] propertyPair = {p.getName(),p.getValue()};
			result.add(propertyPair);
		}
		return result;
		
	}
	
	
	
	public static void main(String args[]) throws Exception
	{
		Class.forName("org.postgresql.Driver");
		Connection dbcon =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/cas","cas_user","");
		dbcon.setAutoCommit(false);
		Statement stmt = dbcon.createStatement(); 
		stmt.execute("set search_path to cas_metadata, public");        
		DBInterface dbi = new DBInterface(dbcon);
		{
			XMLCatalog xmlc = new XMLCatalog(dbi,args[0]);
			System.out.println(xmlc);
/*			Date xx=new Date();
			long date = xx.getTime();
			
			//XMLCatalog xmlc = new XMLCatalog("tmp/"+file);//"demo_cat.xml");
			XMLCatalog xmlc = new XMLCatalog(new File(args[0]));
			//"schema/xx.xml");
			xmlc.insertDataToDB(dbi);
			dbcon.commit();

			xx = new Date();
			System.out.println(xx.getTime() - date);

			stmt = dbcon.createStatement(); 
			stmt.execute("analyze;");        
			dbcon.commit();
*/
		}
		dbcon.close();
	}
	
	private abstract class DataReader
	{
		public DataReader(Externaldata ed, int ncols) throws XMLCatalogException
		{
			this.ncols = ncols;
			EncodingType et = ed.getEncoding();
			if (et != null)
			{
				encoding = ed.getEncoding().value();
			}
			else
			{
				encoding="none";
			}
			List<Property> propertyList = ed.getPropertyList().getProperty();	
			properties  = new Properties();
			for (Property property: propertyList)
			{
				properties.setProperty(property.getName(),property.getValue());
			}
			List<Datasource> sourceList = ed.getSource();
			if (sourceList == null)
			{
				throw new XMLCatalogException("If there is the externaldata field, you should put the link for the data source");				
			}

			ArrayList<URL> urlList = new ArrayList<URL>();
			for (Datasource source: sourceList)
			{
				try
				{
					urlList.add((new URI(source.getUri())).toURL());
				}
				catch(URISyntaxException e)
				{
					throw new XMLCatalogException("Invalid URI in the Catalog description: \n"+source);
				}
				catch(MalformedURLException e)
				{
					throw new XMLCatalogException("Malformed URL in the Catalog: "+source);
				}
			}
			urlIterator = urlList.listIterator();
		}
		
		public boolean getReader() throws XMLCatalogException
		{
			InputStream is;
			InputStream is1;
			
			logger.debug("Retrieving the reader ...");
			if (urlIterator.hasNext())
			{
				currentURL = urlIterator.next();
				logger.debug("Reading from the URL: "+currentURL);
			}
			else
			{
				return false;
			}
			
			try
			{
				is = currentURL.openStream();
			}
			catch (IOException e)
			{
				throw new XMLCatalogException("Problem with opening the URI of the data\n:"+e.getMessage());
			}
			if (encoding.equals("none"))
			{
				is1 = is;
			}
			else if (encoding.equals("base64"))
			{
				throw new XMLCatalogException("base64 encoding is not yet supported...");
			}
			else if (encoding.equals("gzip"))
			{
				try
				{
					is1 = new GZIPInputStream(is);
				}
				catch (IOException e)
				{
					throw new XMLCatalogException("Problems with opening the gzip encoded URI:\n" + e.getMessage());
				}
			}
			else 
			{
				throw new XMLCatalogException("'"+encoding+"' is not yet supported...");
			}
			try
			{
				br = new BufferedReader(new InputStreamReader(is1,"US-ASCII"));
			}
			catch (UnsupportedEncodingException e) 
			{
			}
			return true;
		}
		
		abstract String[] getData() throws IOException, XMLCatalogException;
		protected ListIterator<URL> urlIterator;
		protected BufferedReader br;		
		protected Properties properties;
		protected URL currentURL;
		protected int ncols;
		private String encoding;
	}

	private class FixedWidthDataReader extends DataReader
	{
		public FixedWidthDataReader(Externaldata ed, int ncols) throws XMLCatalogException 
		{
			super(ed,ncols);
			String widthsIn = properties.getProperty("widths");
			String fieldsIn = properties.getProperty("fields");
			String widthsArray[], fieldsArray[];
			
			if ((widthsIn == null)&&(fieldsIn==null))
			{
				throw new XMLCatalogException ("You should define the \"widths\" or \"fields\" property when you select the 'fixed-width' format");
			}
			if ((fieldsIn!=null)&&(widthsIn!=null))
			{
				throw new XMLCatalogException ("You must not define the \"widths\" and \"fields\" properties together when you select the 'fixed-width' format");				
			}
			if (widthsIn!=null)
			{
				withoutNewLines = (properties.getProperty("withoutNewLines")!=null);
				
				widthsArray = widthsIn.split(" ",0);
				int len = widthsArray.length;
				
				if (len != ncols)
				{
					throw new XMLCatalogException("The number of elements in the list of widths must be equal to the number of columns ("+ncols+")");
				}
				
				try 
				{
					this.fieldsLeft = new int[len];
					this.fieldsRight = new int[len];
					int cur_pos = 0;
					for (int i = 0; i < len; i++)
					{
						this.fieldsLeft[i]=cur_pos;
						this.fieldsRight[i]=(cur_pos+=Integer.parseInt(widthsArray[i]));
					}
				}
				catch (NumberFormatException e)
				{
					throw new XMLCatalogException("Invalid field list for the fixed-width format");
				}
			}
			else
			{
				fieldsArray = fieldsIn.split(" ",0);
				int len = fieldsArray.length;
				
				if (len != 2*ncols)
				{
					throw new XMLCatalogException("The number of elements in the list of fields must be twice the number of columns ("+ncols+")");
				}
				
				try 
				{
					this.fieldsLeft = new int[ncols];
					this.fieldsRight = new int[ncols];
					for (int i = 0; i < ncols; i++)
					{
						this.fieldsLeft[i]=Integer.parseInt(fieldsArray[i*2])-1;
						this.fieldsRight[i]=Integer.parseInt(fieldsArray[2*i+1]);
					}
				}
				catch (NumberFormatException e)
				{
					throw new XMLCatalogException("Invalid field list for the fixed-width format");
				}
				
			}
			stringBuffer = new ArrayList<String>();
			stringBuffer.ensureCapacity(bufLen);
			stringBufferIterator = stringBuffer.listIterator();
			getReader();

		}
		public String[] getData() throws IOException, XMLCatalogException
		{
			String s;
			String result[]=new String[ncols];
			while (!stringBufferIterator.hasNext())
			{
				stringBuffer.clear();
				if (!withoutNewLines)
				{
					int i;
					for(i = 1; i <= bufLen; i++)
					{
						String s1 = br.readLine();
						if (s1 == null) break; 
						stringBuffer.add(s1);	
					}
					
					if (i == 1)
					{
						if (!getReader())
						{
							return null;
						}
					}
				}
				else
				{
					char buf[]=new char[totalWidth*bufLen];
					int nChars = br.read(buf,0,totalWidth*bufLen);
					if (nChars == -1)
					{
						if (!getReader())
						{
							return null;
						}						
					}
					else
					{
						if  (nChars%totalWidth!=0)
						{
							throw new XMLCatalogException("Wrong datasource: the size of the file is not the multiplier of the total width of the fields");
						}
						for (int i = 0; i < nChars/totalWidth; i++)
						{
							stringBuffer.add(new String(buf,i*totalWidth,totalWidth));
						}
					}
				}
				stringBufferIterator = stringBuffer.listIterator();
			}
			s = stringBufferIterator.next();

			for(int i = 0; i < ncols; i++)
			{
				result[i] = s.substring(fieldsLeft[i],fieldsRight[i]);
			}
			return result;
		}

		int fieldsLeft[];
		int fieldsRight[];
		int totalWidth;
		boolean withoutNewLines;
		final int bufLen = 1000;
		ArrayList<String> stringBuffer;
		ListIterator<String> stringBufferIterator;
		
	}
	private class DelimitedDataReader extends DataReader
	{
		public DelimitedDataReader(Externaldata ed, int ncols) throws XMLCatalogException
		{
			super(ed, ncols);
			delimiter = properties.getProperty("delimiter");
			nullMarker = properties.getProperty("null");

			if (delimiter == null)
			{
				throw new XMLCatalogException ("You should define the delimiter when you select the 'delimited' format");
			}
			stringBuffer = new ArrayList<String>();
			stringBuffer.ensureCapacity(bufLen);
			stringBufferIterator = stringBuffer.listIterator();
			getReader();
		}
		public String[] getData() throws IOException, XMLCatalogException
		{
			String s;
			while (!stringBufferIterator.hasNext())
			{
				int i;
				stringBuffer.clear();
				for(i = 1; i <= bufLen; i++)
				{
					String s1 = br.readLine();
					if (s1 == null) break; 
					stringBuffer.add(s1);	
				}
				
				if (i == 1)
				{
					if (!getReader())
					{
						return null;
					}
				} 
				stringBufferIterator = stringBuffer.listIterator();
			}
			s = stringBufferIterator.next();
//			logger.debug("Read string: "+s);
//			logger.debug("Delim: "+delimiter);
			
			String sArr[] = s.split("\\"+delimiter);
//			logger.debug("Length: "+sArr.length);

			if (nullMarker != null)
			{
				int n = sArr.length;
				for (int i = 0; i < n; i++)
				{
					if (nullMarker.equals(sArr[i]))
					{
						sArr[i]="";
					} 
				}
			}
			return sArr;
		}
		private String delimiter, nullMarker;
		ArrayList<String> stringBuffer;
		ListIterator<String> stringBufferIterator;
		final int bufLen = 100;
	}
}
