package sai_cas.VOTABLEFile;
import sai_cas.db.*;
import sai_cas.VOTABLEFile.*;

import javax.xml.transform.stream.StreamSource;
import javax.xml.bind.*;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.sql.*;

public class Votable
{
	static Logger logger = Logger.getLogger("sai_cas.Votable");
	private VOTABLE vot;
	
	public Votable()
	{}
	
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
			vot = (VOTABLE) um.unmarshal(new StreamSource ( new StringReader(catalogString)));
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
	
	public static Votable getVOTableFromCSV(File file) throws VotableException
	{
		try
		{
			Votable vot = new Votable();
			VOTABLE vot0 = new VOTABLE();
			vot.vot = vot0;
			BufferedReader br; // to fetch file data
			br = new BufferedReader(new FileReader(file));
			
			String data;
			/* Getting the columnNames */

			data=br.readLine();
			List<String> columns =  Arrays.asList(data.split(","));
			int ncols = columns.size();

			vot0.resource= new ArrayList<sai_cas.VOTABLEFile.RESOURCE>();
			RESOURCE res = new RESOURCE();
			res.setName("crossmatch");
			vot0.resource.add(res);
			res.table = new ArrayList<TABLE>();
			TABLE tab = new TABLE();
			tab.setName("table");
			res.table.add(tab);
			tab.fieldOrPARAMOrGROUP = new ArrayList<Object>();
			List<Object> fieldList = tab.fieldOrPARAMOrGROUP;
			for (String column: columns)
			{
				FIELD f= new FIELD();
				f.setName(column);
				fieldList.add(f);
				f.setDatatype(sai_cas.VOTABLEFile.DataType.DOUBLE);
				f.setUnit("");
				f.setUcd("");
			}
			tab.data = new DATA();
			tab.data.tabledata = new TABLEDATA();
			ArrayList<TR> trList = new ArrayList<TR>();
			tab.data.tabledata.tr = trList;

			while((data = br.readLine()) != null)
			{
				String[] dataArray = data.split(",",ncols+1);
				int nrecs = dataArray.length;

				if (nrecs != ncols)
				{
					throw new VotableException("Number of records in the data is not equal to the number of columns");
				}
				
				TR tr = new TR();
				tr.td = new ArrayList<TD>();
				for (int i = 0; i < nrecs; i++)
				{
					TD td = new TD();
					//String tok = stoken.nextToken();
					//td.value = (tok == null) ? "" : tok;
					td.value = dataArray[i];
					tr.td.add(td);
				}
				trList.add(tr);
			}
			return vot;
		}
		catch(Exception e)
		{
			throw new VotableException(e.getMessage());
		}
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
			vot = (VOTABLE) um.unmarshal(file);
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
	
	public String insertDataToDB(DBInterface dbi, String catalogName0) throws SQLException, DBException, VotableException
	{
		/*  !!!!!!!!!!!  IMPORTANT !!!!!!!!!!
		 *  I do the convertion to lower case.
		 */
		logger.debug("Beginning of DB work in inserting the catalogue... ");

		RESOURCE res = vot.getRESOURCE().get(0);
		/* Just to get the first resource */
		
		String catalogName;
		try
		{
			catalogName = res.getName().toLowerCase();
		}
		catch(NullPointerException e)
		{
			catalogName = null;
		}
			
		catalogName = (catalogName0 == null)?catalogName:catalogName0;
		catalogName.replace('.','_');

		String catalogInfo=null;

		for (Object obj : res.getINFOOrCOOSYSOrPARAM())
		{
			if (obj instanceof INFO)
			{
				catalogInfo = ((INFO) obj).getValue();
			}
		}
		
		String catalogDescription ;
		try 
		{
			catalogDescription = vot.getDESCRIPTION().getContent().get(0).toString();
		}
		catch (NullPointerException e)
		{
			catalogDescription = null;
		}
		if (catalogDescription == null) {catalogDescription ="";}
		
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
//		dbi.insertCatalog(catalogName);
//		dbi.setCatalogInfo(catalogName, catalogInfo);

		//dbi.setCatalogDescription(catalogName, catalogDescription);
		
		List<TABLE> tableList = res.getTABLE();
		List<String> tableNameList = new ArrayList<String>();

		logger.debug("Looping through tables in the catalogue... ");				
		for(TABLE table : tableList)
		{
			/*  !!!!!!!!!!!  IMPORTANT !!!!!!!!!!
			 * I do the convertion to lower case. And I also convert slashes to 
			 * subscript
			 */
			
			String tableName = table.getName().toLowerCase().replace('/','_').replace('.','_');
			tableNameList.add(tableName);
			logger.debug("Inserting the table: "+tableName);				
			String tableDescription;
			
			try
			{
				tableDescription = table.getDESCRIPTION().getContent().get(0).toString();
			}
			catch (NullPointerException e)
			{
				tableDescription = null;
			}
			if (tableDescription == null) {tableDescription = "";}
//			String tableInfo = table.getInfo();
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
				 *   Also I replace '-','(',')' and '.' to '_'
				 */
				columnName = field.getName().toLowerCase().replace('-','_')
					.replace('.','_').replace('(','_').replace(')','_');

				unit =  field.getUnit();
				if (unit == null) {unit ="";}
				ucd = field.getUcd();
				if (ucd == null) {ucd="";}
				try {
					columnDescription = field.getDESCRIPTION().getContent().get(0).toString();
				} catch (NullPointerException e)
				{
					columnDescription = null;
				}
				if (columnDescription == null) {columnDescription="";}
					
//				columnInfo = column.getInfo();
				/* TODO 
				 * I should write the handling of the column properties too
				 * Now I don't do that since in that loop I should kind of 
				 * create the list of lists of properties ... 
				 */
//				List<Property> columnProperties = table.getPropertyList().getProperty();
				try
				{
					datatypeList.add(datatype.value());
				}
				catch (NullPointerException e)
				{
					throw new VotableException ("The datatype attribute must be specified for each field in the table");
				}
				columnNameList.add(columnName);
				unitList.add(unit);
				ucdList.add(ucd);
				columnDescriptionList.add(columnDescription);
				columnInfoList.add(columnInfo);
			}
			logger.debug("Inserting the columns, table metadata... ");				
			dbi.insertTable(catalogName, tableName, columnNameList, datatypeList, unitList, columnInfoList, columnDescriptionList);
			logger.debug("Setting UCDs... ");	
			dbi.setUcds(catalogName, tableName, columnNameList, ucdList);
//			dbi.setTableInfo(catalogName, tableName, tableInfo);
			logger.debug("Setting tableDescs... ");	
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
			String[] datatypeArray = new String[datatypeList.size()];

			dbi.prepareInsertingData(catalogName, tableName, datatypeList.toArray(datatypeArray));

			for (TR tr: trList)
			{
				int i=0;
				for (TD td: tr.getTD())
				{
					values[i++]=td.getValue();
				}
				dbi.insertData(values);
			}
			dbi.flushData();
			
		}  
		return tableNameList.get(0);
	}
	public boolean checkColumnExistance(String column)
	{
		for(Object o: vot.resource.get(0).table.get(0).fieldOrPARAMOrGROUP)
		{
			if (o instanceof FIELD)
			{
				FIELD f = (FIELD) o;
				if (f.name.equals(column))
				{
					return true;
				}
			}
		}
		return false;
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
			dbi.close();
//			dbcon.commit();

			xx = new Date();
			System.out.println(xx.getTime() - date);

//			stmt = dbcon.createStatement(); 
//			stmt.execute("analyze;");        
//			dbcon.commit();
		}
//		dbcon.close();
	}
	

}
