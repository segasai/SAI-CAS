package sai_cas;
import sai_cas.db.*;
import sai_cas.VOTABLEFile.*;

import javax.xml.transform.stream.StreamSource;
import javax.xml.bind.*;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.Properties;
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

public class Votable
{
	static Logger logger = Logger.getLogger("sai_cas.Votable");
	private VOTABLE vot;

	public Votable(String catalogString) throws  VotableException
	{
		logger.info("The Votable constructor is running");
		Unmarshaller um = null;
		try
		{
			JAXBContext jc = JAXBContext.newInstance("sai_cas.VOTABLEFile");
			um = jc.createUnmarshaller();
		}
		catch (JAXBException e)
		{
			logger.error("Error in unmarshaller creation:", e);
			throw new VotableException("Error in creating unmarshaller:\n Message: " + e.getMessage());			
		}
		try
		{
			JAXBElement<?> votElement = (JAXBElement<?>)um.unmarshal(new StreamSource ( new StringReader( catalogString )));
			vot = (VOTABLE) votElement.getValue();
		}
		catch (UnmarshalException e) 
		{
			logger.error("Error during unmarshalling:", e);
			throw new VotableException("Error during unmarshalling:\n Message: " + e.getMessage() + e.getCause());
		}
		catch (JAXBException e)
		{
			logger.error("Error during unmarshalling:", e);
			throw new VotableException("Error during unmarshalling:\n Message: " + e.getMessage() + e.getCause());
		}
		logger.info("The catalog successfully unmarshalled");
	}
	
	public Votable(URI uri) throws  VotableException
	{
		Unmarshaller um;
		JAXBContext jc;
		try
		{
			jc = JAXBContext.newInstance("sai_cas.VOTABLEFile");
			um = jc.createUnmarshaller();
		}
		catch (JAXBException e)
		{
			logger.error("Error in unmarshaller creation:", e);
			throw new VotableException("Error in creating unmarshaller:\n Message: " + e.getMessage());			
		}

		try
		{
			vot = (VOTABLE) um.unmarshal(new File(uri));
		}
		catch (UnmarshalException e) 
		{
			logger.error("Error during unmarshalling:", e);
			throw new VotableException("Error during unmarshalling:\n Message: " + e.getMessage() + e.getCause());
		}
		catch (JAXBException e)
		{
			logger.error("Error during unmarshalling:", e);
			throw new VotableException("Error during unmarshalling:\n Message: " + e.getMessage() + e.getCause());
		}
	}
	
	public Votable(File file) throws VotableException
	{
		logger.info("The Votable constructor is running");
		Unmarshaller um = null;
		try
		{
			JAXBContext jc = JAXBContext.newInstance("sai_cas.VOTABLEFile");
			um = jc.createUnmarshaller();
		}
		catch (JAXBException e)
		{
			logger.error("Error in unmarshaller creation:", e);
			throw new VotableException("Error in creating unmarshaller:\n Message: " + e.getMessage());			
		}
		try
		{
			JAXBElement<?> votElement = (JAXBElement<?>) um.unmarshal(file);
			vot = (VOTABLE) votElement.getValue();
		}
		catch (UnmarshalException e) 
		{
			logger.error("Error during unmarshalling:", e);
			throw new VotableException("Error during unmarshalling:\n Message: " + e.getMessage() + e.getCause());
		}
		catch (JAXBException e)
		{
			logger.error("Error during unmarshalling:", e);
			throw new VotableException("Error during unmarshalling:\n Message: " + e.getMessage() + e.getCause());
		}
		logger.info("The catalog successfully unmarshalled");
		
	}

	public void insertDataToDB(DBInterface dbi) throws SQLException, DBException, VotableException
	{
		insertDataToDB(dbi,null);
	}
	
	public void insertDataToDB(DBInterface dbi, String catalogName0) throws SQLException, DBException, VotableException
	{
		/*  !!!!!!!!!!!  IMPORTANT !!!!!!!!!!
		 *  I do the convertion to lower case.
		 */
		logger.debug("Beginning of DB work in inserting the catalogue... ");

		RESOURCE res = vot.getRESOURCE().get(0);
		/* Just to get the first resource */

		String catalogName = res.getName().toLowerCase();

		catalogName = (catalogName0==null)?catalogName:catalogName0;

		String catalogInfo=null;

		for (Object obj : res.getINFOOrCOOSYSOrPARAM())
		{
			if (obj instanceof INFO)
			{
				catalogInfo = ((INFO) obj).getValue();
			}
		}

		StringBuffer buf=new StringBuffer();
		
		String catalogDescription ;
		catalogDescription=vot.getDESCRIPTION().getContent().get(0).toString();

/*		List<String[]> catalogProperties;	
		try
		{
			catalogProperties = convertProperties(cat.getPropertyList().getProperty());	
		}
		catch (NullPointerException e)
		{
			catalogProperties = new ArrayList<String[]>();
		}
		dbi.setCatalogProperties(catalogName, catalogProperties);
*/

		logger.debug("Inserting the catalogue metadata... ");		
		dbi.insertCatalog(catalogName);
//		dbi.setCatalogInfo(catalogName, catalogInfo);

		dbi.setCatalogDescription(catalogName, catalogDescription);
		
		List<TABLE> tableList = res.getTABLE();

		logger.debug("Looping through tables in the catalogue... ");				
		for(TABLE table : tableList)
		{
			/*  !!!!!!!!!!!  IMPORTANT !!!!!!!!!!
			 * I do the convertion to lower case.
			 */
			
			String tableName = table.getName().toLowerCase();
			logger.debug("Inserting the table: "+tableName);				
			
//			String tableInfo = table.getInfo();
			String tableDescription = table.getDESCRIPTION().getContent().get(0).toString();
/*			List<String[]> tableProperties;
			try
			{
				tableProperties = convertProperties(table.getPropertyList().getProperty());	
			}
			catch (NullPointerException e)
			{
				tableProperties = new ArrayList<String[]>();
			}
*/
			
			List<FIELD> fieldList =new ArrayList<FIELD>();
			for (Object obj : table.getFIELDOrPARAMOrGROUP())
			{
				if (obj instanceof FIELD)
				{
					fieldList.add((FIELD)obj);
				}
			}
			List<String> datatypeList = new ArrayList<String>();
			List<String> columnNameList = new ArrayList<String>();
			List<String> unitList = new ArrayList<String>();
			List<String> ucdList = new ArrayList<String>();

			List<String> columnDescriptionList = new ArrayList<String>();
			List<String> columnInfoList = new ArrayList<String>();
			String unit, ucd, columnName, columnInfo=null, columnDescription;
			DataType datatype;
			int ncols = fieldList.size();
			
			for (FIELD field : fieldList)
			{
				datatype = field.getDatatype();
				/*  !!!!!!!!!!!  IMPORTANT !!!!!!!!!!
				 *   I do the convertion to lower case.
				 */
				columnName = field.getName().toLowerCase();
				unit =  field.getUnit();
				ucd = field.getUcd();
				columnDescription = field.getDESCRIPTION().getContent().get(0).toString();
//				columnInfo = column.getInfo();
				/* TODO 
				 * I should write the handling of the column properties too
				 * Now I don't do that since in that loop I should kind of 
				 * create the list of lists of properties ... 
				 */
//				List<Property> columnProperties = table.getPropertyList().getProperty();
				datatypeList.add(datatype.value());
				columnNameList.add(columnName);
				unitList.add(unit);
				ucdList.add(ucd);
				columnDescriptionList.add(columnDescription);
				columnInfoList.add(columnInfo);
			}
			logger.debug("Inserting the columns, table metadata... ");				
			dbi.insertTable(catalogName, tableName, columnNameList, datatypeList, unitList, columnInfoList, columnDescriptionList);
			dbi.setUcds(catalogName, tableName, columnNameList, ucdList);
//			dbi.setTableInfo(catalogName, tableName, tableInfo);
			dbi.setTableDescription(catalogName, tableName, tableDescription);
//			dbi.setTableProperties(catalogName, tableName, tableProperties);
			logger.debug("Preparing to read the data... ");							
			/* Now we are handling the data in the table */			
			DATA d = table.getDATA();
			
			if (d == null)
			{
				continue;
				/* That means that there is no data, or data reference for that table */
			}
			
			TABLEDATA tdata = d.getTABLEDATA();
			List <TR> trList = tdata.getTR();
			String[] values = new String[ncols];
			for (TR tr: trList)
			{
				int i=0;
				for (TD td: tr.getTD())
				{
					values[i++]=td.getValue();
				}
				dbi.insertData(catalogName, tableName, values);
			}
			
		}  
	}
	
	public static void main(String args[]) throws Exception
	{
		Class.forName("org.postgresql.Driver");
		Connection dbcon =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/cas","cas_user","");
		dbcon.setAutoCommit(false);
		Statement stmt = dbcon.createStatement(); 
		stmt.execute("set search_path to cas_metadata, public");        
		DBInterface dbi = new DBInterface(dbcon);
		//for (String file : Arrays.asList((new File("tmp")).list()))
		{
			Date xx=new Date();
			long date = xx.getTime();
			
			Votable vot = new Votable(new File(args[0]));
			vot.insertDataToDB(dbi);
			dbcon.commit();

			xx = new Date();
			System.out.println(xx.getTime() - date);

			stmt = dbcon.createStatement(); 
			stmt.execute("analyze;");        
			dbcon.commit();
		}
		dbcon.close();
	}
	

}
