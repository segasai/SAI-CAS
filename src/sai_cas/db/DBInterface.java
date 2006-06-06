package sai_cas.db;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

import org.apache.log4j.Logger;
import org.postgresql.*;


public class DBInterface  extends Object
{
	static Logger logger = Logger.getLogger("sai_cas.DBInterface");
	private PreparedStatement pstmtBuffered;
	private StatementSetter[] ss;
	public QueryResults qr;
	private Connection conn;
	Statement stmt;
	final int maxBatchStatement = 1000;
	int curNBatchStatements;

	
	public DBInterface(Connection conn) throws java.sql.SQLException
	{
		this.conn = conn;
		
		String query = "SET search_path TO cas_metadata,public;";
		stmt = conn.createStatement(); 
		stmt.execute(query);
		logger.info("The DB interface is successfully created...");
		curNBatchStatements = 0 ;
	}

	/* TODO need to be refactored (concerning user_schema) */
	public DBInterface(Connection conn, String user_schema) throws java.sql.SQLException
	{
		this.conn = conn;
		
		String query = "SET search_path TO "+user_schema+",cas_metadata,public;";
		stmt = conn.createStatement(); 
		stmt.execute(query);
		logger.info("The DB interface is successfully created...");
		curNBatchStatements = 0 ;
	}


	public void close()
	{
		close(true);
	}

	public void close(boolean commit_flag)
	{
		try
		{
			if (stmt != null)
			{
				stmt.close();
			}
			if (pstmtBuffered != null)
			{
				pstmtBuffered.executeBatch();
				pstmtBuffered.close();
			}
			if (qr != null)
			{
				qr.close();
			}
			if (commit_flag)
			{
				conn.commit();
			}
			else 
			{
				conn.rollback();
			}
			conn.close();
		}
		catch (SQLException e)
		{
			logger.error("Exception during DBInterface closing ... " + e);
		}
	}

	public static void close(DBInterface dbi, Connection conn)
	{
		close(dbi,conn,true);	
	}
	
	public static void close(DBInterface dbi, Connection conn, boolean commit_flag)
	{
		try 
		{
			if (dbi != null)
			{
				dbi.close(commit_flag);
			}
			else if (conn != null)
			{
				if (commit_flag)
				{
					conn.commit();
				}
				else
				{
					conn.rollback();
				}
				conn.close();
			}
		}
		catch(SQLException e)
		{			
		}
	}

	public void insertCatalog(String catalog) throws SQLException
	{
		String query = "INSERT INTO catalog_list (name) VALUES ('" + catalog + "')";
		stmt.execute(query);     
		query = "CREATE SCHEMA "+catalog;
		stmt.execute(query);            
	}

	public void prepareInsertingData(String catalog, String table, String[] datatypeArray) throws SQLException, DBException
	{
		String[] internalDatatypeArray = new String[datatypeArray.length];

		for (int i = 0; i < datatypeArray.length; i++)
		{	
			internalDatatypeArray[i] = getInternalDatatype(datatypeArray[i]);
		}
		
		StringBuffer query =  new StringBuffer(1000);
		query.append("INSERT INTO " + catalog + "." + table + " VALUES (");
		
		ss = new StatementSetter[internalDatatypeArray.length];
		for(int i = 0, len = internalDatatypeArray.length; i < len; i++)
		{
			if (internalDatatypeArray[i].equals( "varchar"))
				ss[i] = new StatementSetterVarchar();
			else if (internalDatatypeArray[i].equals( "integer"))
				ss[i] = new StatementSetterInt();
			else if (internalDatatypeArray[i].equals( "smallint"))
				ss[i] = new StatementSetterInt();
			else if (internalDatatypeArray[i].equals( "double precision"))
				ss[i] = new StatementSetterDouble();
			else if (internalDatatypeArray[i].equals( "real"))
				ss[i] = new StatementSetterFloat();
			else if (internalDatatypeArray[i].equals( "bigint"))
				ss[i] = new StatementSetterLong();
			else 
				ss[i] = new StatementSetter(internalDatatypeArray[i]);
			query.append(ss[i].getInsert());
		}
		query.setCharAt(query.length() - 1, ')');		
		System.out.println(query);		
		pstmtBuffered = conn.prepareStatement(query.toString());
		//org.postgresql.PGStatement pgstmt = 
//		((org.postgresql.PGStatement)pstmtBuffered).setPrepareThreshold(3);
		for(int i = 0, len = internalDatatypeArray.length; i < len; i++)
		{
			ss[i].setStatement(pstmtBuffered);
		}
	}	

	private class StatementSetter
	{
		String datatype;
		public StatementSetter (String datatype)
		{
			this.datatype = datatype;
		}
		public StatementSetter ()
		{
		}
		public void setStatement (PreparedStatement pstmt)
		{
			this.pstmt = pstmt;
		}		
		public void set(int i, String value) throws java.sql.SQLException
		{
			pstmt.setString(i,value);
		}
		public String getInsert()
		{
			return "CAST(? AS " + datatype + "),";
		}
		protected PreparedStatement pstmt;	
	}
	
	private class StatementSetterInt extends StatementSetter
	{
		public void set(int i, String value) throws java.sql.SQLException
		{
			String value1 = value.trim();
			if (value1.length() == 0)
			{
				pstmt.setNull(i,Types.INTEGER);
				return;
			}
			int offset = 0;
			if (value1.charAt(0) == '+')
			{
					offset = 1;
			}			
			pstmt.setInt(i, Integer.parseInt(value1.substring(offset)));
		}
		public String getInsert()
		{
			return "?,";
		}		
	}

	private class StatementSetterLong extends StatementSetter
	{
		public void set(int i, String value) throws java.sql.SQLException
		{
			String value1 = value.trim();
			int offset = 0;
			if (value1.length() == 0)
			{
				pstmt.setNull(i, Types.BIGINT);
				return;
			}

			if (value1.charAt(0) == '+')
			{
					offset = 1;
			}
			
			pstmt.setLong(i, Long.parseLong(value1.substring(offset)));
		}
		public String getInsert()
		{
			return "?,";
		}		
	}	
	private class StatementSetterDouble extends StatementSetter
	{
/*		public StatementSetterDouble (String datatype)
		{
			super(datatype);
		}*/
		public void set(int i, String value) throws java.sql.SQLException
		{
			String value1 = value.trim();
			if (value1.length() == 0)
			{
				pstmt.setNull(i, Types.DOUBLE);
				return;
			}			
			pstmt.setDouble(i, Double.parseDouble(value1));
		}		
		public String getInsert()
		{
			return "?,";
		}
	}

	
	private class StatementSetterFloat extends StatementSetter
	{
		public void set(int i, String value) throws java.sql.SQLException
		{
			String value1 = value.trim();
			if (value1.length() == 0)
			{
				pstmt.setNull(i, Types.FLOAT);
				return;
			}			
			pstmt.setFloat(i, Float.parseFloat(value1));
		}		
		public String getInsert()
		{
			return "?,";
		}
	}

	private class StatementSetterVarchar extends StatementSetter
	{
		/* !!!!!!!!!! IMPORTANT !!!!!!!!!!!!!!!!!!
		 * Currently I trim all the char[] fields
		 */
		
		public void set(int i, String value) throws java.sql.SQLException
		{
			pstmt.setString(i, value.trim());
		}
		public String getInsert()
		{
			return "?,";
		}		
	}
	public void insertData(String[] stringArray) throws SQLException
	{
//		boolean usingServerPrepare = ((org.postgresql.PGStatement)pstmtBuffered).isUseServerPrepare();
//		System.out.println(usingServerPrepare);
		for(int i=0, len = stringArray.length; i < len; i++)
		{
			ss[i].set(i + 1, stringArray[i]);
		}
		//pstmtBuffered.executeUpdate();
		pstmtBuffered.addBatch();
		if (++curNBatchStatements == maxBatchStatement)
		{
			//logger.debug("Executing batch ....");
			pstmtBuffered.executeBatch();
			curNBatchStatements = 0;
		}
	}
	
	public void insertData(String catalog, String table, String[] stringArray) throws SQLException
	{
		StringBuffer query =  new StringBuffer(1000);
		query.append("INSERT INTO "+catalog+"."+table+" VALUES (");
		for(int i=0, len = stringArray.length; i < len; i++)
		{
			query.append("'" + stringArray[i] + "',");
		}
		query.setCharAt(query.length() - 1, ')');
		stmt.execute(query.toString());
	}

	public void insertCatalog(String catalog, String catalogInfo, String catalogDescription) throws SQLException
	{
		String query = "INSERT INTO catalog_list (name, info, description) VALUES ('" +
			catalog + "','" + catalogInfo + "','" + catalogDescription + "')";
		stmt.execute(query);     
		query = "CREATE SCHEMA " + catalog;
		stmt.execute(query);
	}


	public boolean checkCatalogExists(String catalog) throws SQLException
	{
		String query="SELECT cas_catalog_exists('" + catalog + "')";
		stmt.executeQuery(query);            
		ResultSet rs = stmt.getResultSet();
		rs.next();
		boolean result = rs.getBoolean(1);
		rs.close();
		return result;
	}

	public boolean checkCatalogPropertyExists(String property) throws SQLException
	{
		String query="SELECT cas_catalog_property_exists('" + property + "')";
		stmt.executeQuery(query);
		ResultSet rs = stmt.getResultSet();
		rs.next();
		boolean result = rs.getBoolean(1);
		rs.close();
		return result;
	}
	/**
	 * 
	 * @param catalog -- the catalog to which the properties will be set
	 * @param property -- the property name 
	 * @param value -- the property value
	 * @throws SQLException
	 */
	public void setCatalogProperty(String catalog, String property, String value) throws SQLException
	{
		String query="INSERT INTO catalog_property_map"+
		"(property_id, catalog_id, value) VALUES"+
		"(cas_get_catalog_property_id( '" + property + "' )," +
		" cas_get_catalog_id ( '" + catalog + "' ), '" + value + "' )";
		stmt.execute(query);
	}
	/**
	 * This function just do the bulk set of properties for the catalogue
	 * @param catalog -- the name of the catalogue
	 * @param propertyList -- the list of two element arrays of property name/value pairs
	 * @throws SQLException
	 */
	public void setCatalogProperties(String catalog, List<String[]> propertyList) throws SQLException
	{
		logger.debug("Inserting catalog properties...");
		for (String[] propertyPair: propertyList)
		{
			if (checkCatalogPropertyExists(propertyPair[0]))
			{
				setCatalogProperty(catalog,propertyPair[0],propertyPair[1]);
			}
			else
			{
				/* TODO 
				 * I should handle somehow the case when the property do not
				 * exist
				 */
			}
		}
	}
	
	public void setCatalogInfo(String catalog, String info) throws SQLException
	{
		String query="UPDATE catalog_list SET info = ? WHERE" +
		" id = cas_get_catalog_id ( ? )";
		PreparedStatement pstmt = conn.prepareStatement(query); 
		pstmt.setString(1, info);
		pstmt.setString(2, catalog);
		pstmt.executeUpdate();
		pstmt.close();

	}

	public void setCatalogDescription(String catalog, String description) throws SQLException
	{
		String query="UPDATE catalog_list SET description = ? WHERE " +
		" id = cas_get_catalog_id ( ? )";
		PreparedStatement pstmt = conn.prepareStatement(query); 
		pstmt.setString(1, description);
		pstmt.setString(2, catalog);
		pstmt.executeUpdate();
		pstmt.close();
	}


	public void insertTable(String catalog, String table, List<String> columns, List<String> columnTypes, List<String> columnUnits, List<String> infoList, List<String> descriptionList) throws SQLException, DBException
	{
		String query = "INSERT INTO table_list (name, catalog_id) VALUES ( '"+table+"',cas_get_catalog_id('"+catalog+"') )";

		stmt.execute(query);            
		ListIterator<String> cit = columns.listIterator();
		ListIterator<String> ctit = columnTypes.listIterator();
		ListIterator<String> cuit = columnUnits.listIterator();
		ListIterator<String> ciit = infoList.listIterator();
		ListIterator<String> cdit = descriptionList.listIterator();

//		List columnInternalTypes = new ArrayList();
		StringBuffer sb = new StringBuffer();
		String column, columnType, columnInternalType, columnUnit, columnDescription, columnInfo;
		sb.append("CREATE TABLE "+catalog + "." + table + " ( ");

		query = "INSERT INTO attribute_list " +
		"(table_id, name ,datatype_id, unit, info, description) " +
		" VALUES (cas_get_table_id(?,?),? ,cas_get_datatype_id(?),?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(query);      

		while (cit.hasNext())
		{
			if (!ctit.hasNext())
			{
				throw new DBException("Column and ColumnType lists have different lengths");
			}
			column = cit.next();
			columnType = ctit.next();
			columnUnit = cuit.next();
			columnInfo = ciit.next();
			columnDescription = cdit.next();

			//System.out.println(columnType);
			columnInternalType = getInternalDatatype(columnType);
			//System.out.println(columnInternalType);
			
			sb.append(column + " " + columnInternalType+",");
			
			pstmt.setString(1,catalog);      
			pstmt.setString(2,table);      
			pstmt.setString(3,column);      
			pstmt.setString(4,columnType);      
			pstmt.setString(5,columnUnit);      
			pstmt.setString(6,columnInfo);      
			pstmt.setString(7,columnDescription);      
			pstmt.execute();
		}
		pstmt.close();
		sb.deleteCharAt(sb.length()-1);
		sb.append(")");

		stmt.execute(new String(sb)); 
	}

	private String getInternalDatatype(String columnType) throws SQLException, DBException {
		String query;
		ResultSet rs;
		String columnInternalType;
		query = "select cas_get_internal_datatype('" + columnType + "')";
		//stmt = conn.createStatement(); 
		rs = stmt.executeQuery(query);
		if (!rs.next()) 
		{
			throw new DBException("Unknown datatype");
		}
		
		columnInternalType = rs.getString(1);
		rs.close();
		return columnInternalType;
	}

	public boolean checkTableExists(String catalog, String table) throws SQLException
	{
		String query="SELECT cas_table_exists('"+catalog+"','"+table+"')";
		stmt.executeQuery(query);
		ResultSet rs = stmt.getResultSet();
		rs.next();
		boolean result = rs.getBoolean(1);
		rs.close();
		return result;
	}

	public boolean checkTablePropertyExists(String property) throws SQLException
	{
		String query="SELECT cas_table_property_exists('"+property+"')";
		stmt.executeQuery(query);            
		ResultSet rs = stmt.getResultSet();
		rs.next();
		boolean result = rs.getBoolean(1);
		rs.close();
		return result;
	}


	public void setTableProperty(String catalog, String table, String property, String value) throws SQLException
	{
		String query="INSERT INTO table_property_map"+
		"(property_id, table_id, value) VALUES"+
		"(cas_get_table_property_id( '" +property+"' ),"+
		" cas_get_table_id ( '"+catalog+"', '"+table+"' ), '"+value+"' )";
		stmt.execute(query);
	}

	/**
	 * This function just do the bulk set of properties for the table
	 * @param catalog -- the name of the catalogue
	 * @param table -- the name of the tabl
	 * @param propertyList -- the list of two element arrays of property name/value pairs
	 * @throws SQLException
	 */
	public void setTableProperties(String catalog, String table, List<String[]> propertyList) throws SQLException
	{
		logger.debug("Inserting table properties...");
		for (String[] propertyPair: propertyList)
		{
			if (checkTablePropertyExists(propertyPair[0]))
			{
				setTableProperty(catalog,table,propertyPair[0],propertyPair[1]);
			}
			else
			{
				/* TODO 
				 * I should handle somehow the case when the property do not
				 * exist
				 */
			}
		}
	}

	
	public void setTableInfo(String catalog, String table, String info) throws SQLException
	{
		String query="UPDATE table_list SET info = ? WHERE " +
		" id = cas_get_table_id (?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(query); 
		pstmt.setString(1,info);
		pstmt.setString(2,catalog);
		pstmt.setString(3,table);
		pstmt.executeUpdate(); 
		pstmt.close();
	}

	public void setTableDescription(String catalog, String table, String description) throws SQLException
	{
		String query="UPDATE table_list SET description = ? WHERE " +
		" id = cas_get_table_id ( ?,? )";
		PreparedStatement pstmt = conn.prepareStatement(query); 
		pstmt.setString(1,description);
		pstmt.setString(2,catalog);
		pstmt.setString(3,table);
		pstmt.executeUpdate();
		pstmt.close();
	}



	public boolean checkAttributeExists(String catalog, String table, String attribute) throws SQLException
	{
		String query="SELECT cas_attribute_exists('"+catalog+"','"+table+"','"+attribute+"')";
		stmt.executeQuery(query);            
		ResultSet rs = stmt.getResultSet();
		rs.first();
		boolean result = rs.getBoolean(1);
		rs.close();
		return result;
	}

	public boolean checkAttributePropertyExists(String property) throws SQLException
	{
		String query="SELECT cas_attribute_property_exists('"+property+"')";
		stmt.executeQuery(query);            
		ResultSet rs = stmt.getResultSet();
		rs.next();
		boolean result = rs.getBoolean(1);
		rs.close();
		return result;
	}

	public void setAttributeProperty(String catalog, String table, String attribute, String property, String value) throws SQLException
	{
		String query="INSERT INTO attribute_property_map"+
		"(property_id, attribute_id, value) VALUES"+
		"(cas_get_attribute_property_id( '" +property+"' ),"+
		" cas_get_attribute_id ( '"+catalog+"', '"+table+"', '"+attribute+"' ), '"+value+"' )";
		stmt.execute(query);
	}

	public void setAttributeInfo(String catalog, String table, String attribute, String info) throws SQLException
	{
		String query="UPDATE attribute_list SET info = '"+ info + "'WHERE" +
		" id = cas_get_attribute_id ( '"+catalog+"','"+ table+ "','"+attribute+"')";
		stmt.executeUpdate(query);
	}

	public void setAttributeDescription(String catalog, String table, String attribute, String description) throws SQLException
	{
		String query="UPDATE attribute_list SET description = '"+ description + "'WHERE" +
		" id = cas_get_table_id ( '"+catalog+"','"+table+"','"+attribute+"' )";
		PreparedStatement pstmt = conn.prepareStatement(query); 
		pstmt.setString(1,description);
		pstmt.setString(2,catalog);
		pstmt.setString(3,table);
		pstmt.setString(4,attribute);
		pstmt.executeUpdate();
		pstmt.close();
	}



	public void setUnit (String catalog, String table, String column, String unit) throws SQLException
	{
		
		String query = "UPDATE attribute_list SET unit = ? where"+
		"(cas_get_attribute_id(? ,? ,?)";
		PreparedStatement pstmt = conn.prepareStatement(query); 
		pstmt.setString(1,unit);
		pstmt.setString(2,catalog);
		pstmt.setString(3,table);
		pstmt.setString(4,column);
		pstmt.execute();
		pstmt.close();
	}

	public boolean checkUcdExists(String ucd) throws SQLException
	{
		String query="SELECT cas_ucd_exists('"+ucd+"')";
		stmt.executeQuery(query);            
		ResultSet rs = stmt.getResultSet();
		rs.next();
		boolean result = rs.getBoolean(1);
		rs.close();
		return result;
		
	}
	
	public void setUcds (String catalog, String table, List<String> columnList, List<String> ucdList) throws SQLException
	{
		
		String query = "UPDATE attribute_list SET ucd_id = cas_get_ucd_id(?)" +
				"WHERE id = cas_get_attribute_id('" + catalog + "','" +
				table + "',?)";
		PreparedStatement pstmt = conn.prepareStatement(query); 
		ListIterator<String> columnListIterator = columnList.listIterator();
		ListIterator<String> ucdListIterator = ucdList.listIterator();
		String ucd, column;
		while(columnListIterator.hasNext())
		{
			column = columnListIterator.next(); 
			ucd = ucdListIterator.next();
			if (ucd == null)
			{
				continue;
			}
			if (!checkUcdExists(ucd))
			{
				String query1 = "INSERT INTO ucd_list (name) VALUES ('" + ucd+"')";
				stmt.execute(query1);
			}
			pstmt.setString(1,ucd);
			pstmt.setString(2,column);
			pstmt.execute();				
			
		}
		pstmt.close();
		
	}

	
	public String getCatalogDescription(String catalog) throws SQLException
	{
		String query="SELECT cas_get_catalog_description('"+catalog+"');";
		stmt.executeQuery(query);
		ResultSet rs = stmt.getResultSet();
		rs.next();
		String result = rs.getString(1);
		rs.close();
		return result;
	}

	public String getCatalogInfo(String catalog) throws SQLException
	{
		String query="SELECT cas_get_catalog_info('"+catalog+"');";
		stmt.executeQuery(query);
		ResultSet rs = stmt.getResultSet();
		rs.next();
		String result = rs.getString(1);
		rs.close();
		return result;
	}

	public String[] getCatalogNames() throws SQLException
	{
		String query="SELECT cas_get_catalog_names();";
		stmt.executeQuery(query);
		ResultSet rs = stmt.getResultSet();
		ArrayList<String> als = new ArrayList<String>();
		while(rs.next())
		{
			als.add(rs.getString(1));
		}
		String[] result = new String[1];
		rs.close();
		return als.toArray(result);
	}

	public String[] getTableNames(String catalogName) throws SQLException
	{
		String query="SELECT cas_get_table_names('"+catalogName+"');";
		stmt.executeQuery(query);
		ResultSet rs = stmt.getResultSet();
		ArrayList<String> als = new ArrayList<String>();
		while(rs.next())
		{
			als.add(rs.getString(1));
		}
		String[] result = new String[1];
		rs.close();
		return als.toArray(result);
	}

	public String[] getColumnNames(String catalogName,String tableName) throws SQLException
	{
		String query="SELECT cas_get_attribute_names('"+catalogName+"','"+tableName+"');";
		stmt.executeQuery(query);
		ResultSet rs = stmt.getResultSet();
		ArrayList<String> als = new ArrayList<String>();
		while(rs.next())
		{
			als.add(rs.getString(1));
		}
		String[] result = new String[1];
		rs.close();
		return als.toArray(result);
	}

	public String[] getColumnDescriptions(String catalogName,String tableName) throws SQLException
	{
		String query="SELECT cas_get_column_descriptions('"+catalogName+"','"+tableName+"');";
		stmt.executeQuery(query);
		ResultSet rs = stmt.getResultSet();
		ArrayList<String> als = new ArrayList<String>();
		while(rs.next())
		{
			als.add(rs.getString(1));
		}
		String[] result = new String[1];
		rs.close();
		return als.toArray(result);
	}

	public String[] getColumnInfos(String catalogName,String tableName) throws SQLException
	{
		String query="SELECT cas_get_column_infos('"+catalogName+"','"+tableName+"');";
		stmt.executeQuery(query);
		ResultSet rs = stmt.getResultSet();
		ArrayList<String> als = new ArrayList<String>();
		while(rs.next())
		{
			als.add(rs.getString(1));
		}
		String[] result = new String[1];
		rs.close();
		return als.toArray(result);
	}


	public String[] getColumnUnits(String catalogName,String tableName) throws SQLException
	{
		String query="SELECT cas_get_column_units('"+catalogName+"','"+tableName+"');";
		stmt.executeQuery(query);
		ResultSet rs = stmt.getResultSet();
		ArrayList<String> als = new ArrayList<String>();
		while(rs.next())
		{
			als.add(rs.getString(1));
		}
		String[] result = new String[1];
		rs.close();
		return als.toArray(result);
	}


	public String[] getColumnUCDs(String catalogName,String tableName) throws SQLException
	{
		String query="SELECT cas_get_column_ucds('"+catalogName+"','"+tableName+"');";
		stmt.executeQuery(query);
		ResultSet rs = stmt.getResultSet();
		ArrayList<String> als = new ArrayList<String>();
		while(rs.next())
		{
			als.add(rs.getString(1));
		}
		String[] result = new String[1];
		rs.close();
		return als.toArray(result);
	}

	
	public String[][] getIndexes(String catalogName, String tableName) throws SQLException
	{
		String query="SELECT * FROM cas_get_table_indexes('" + catalogName +
			"','" + tableName + "') AS (a varchar, b varchar);";
		logger.debug("Running query: "+query);
		stmt.executeQuery(query);
		ResultSet rs = stmt.getResultSet();
		ArrayList<String[]> als = new ArrayList<String[]>();
		String[] row = new String[2];
		while(rs.next())
		{
			row[0]=rs.getString(1);
			row[1]=rs.getString(2);			
			als.add(row);
		}
		String[][] result = new String[1][1];
		rs.close();
		return als.toArray(result);
	}
	
	public String[] getRaDecColumns(String catalogName, String tableName) throws SQLException
	{
//		String query="?=CALL cas_get_table_ra_dec(?,?)";
		String query = "SELECT * from cas_get_table_ra_dec('"+catalogName+"','"+tableName+"') as (a varchar, b varchar)";
		logger.debug("Running query: "+query);
/*		Statement stmt = conn.prepareCall(query);
		stmt.setString(2,catalogName);
		stmt.setString(3,tableName);
		stmt.registerOutParameter(1,Types.VARCHAR);
		stmt.registerOutParameter(4,Types.VARCHAR);*/
		ResultSet rs = stmt.executeQuery(query);
		String [] res = new String[2];
		rs.next();
		res[0] = rs.getString(1);
		res[1] = rs.getString(2);
		rs.close();
		return res;
	}

	public String getColumn_ID_MAIN_UCD(String catalogName, String tableName) throws SQLException
	{
		String query="SELECT cas_get_table_id_main_ucd('" + catalogName +
			"','" + tableName + "');";
		logger.debug("Running query: "+query);
		stmt.executeQuery(query);
		ResultSet rs = stmt.getResultSet();
		rs.next();
		String result = rs.getString(1);
		rs.close();
		return result;
	}

	
	/**
	 * 
	 * @param catalogName -- The name of catalogue
	 * @return the table name if the catalogue contains only one table
	 * and null, if the catalogue has more than one table
	 */
	public String getSingleTableFromCatalog(String catalogName) throws SQLException
	{
		String tableArray[] = getTableNames(catalogName);
		if (tableArray.length>1)
		{
			return null;
		}
		else
		{
			return tableArray[0];
		}
	}
	
	public void executeQuery(String query) throws java.sql.SQLException
	{
		conn.setAutoCommit(false);    
		/* Ensure that the autocommit is turned off, since only in that case the
		 * cursor based ResultSet is working
		 */ 
		logger.debug("Executing Query: "+ query);
		PreparedStatement stmt = conn.prepareStatement(query,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
		stmt.setFetchSize(100);
		this.qr = new QueryResults(stmt.executeQuery());
	}

	

/*
	public static void main(String args[]) throws Exception
	{
		Class.forName("org.postgresql.Driver");
		Connection dbcon =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/cas","math","");
		Statement stmt = dbcon.createStatement(); 
		stmt.execute("set search_path to cas_metadata, public");        
		DBInterface dbi = new DBInterface(dbcon);
		dbi.insertCatalog("catxxx");
		List<String> col =new ArrayList<String> ();
		List<String> colt =new ArrayList<String> ();
		col.add("col1");
		col.add("col2");
		col.add("col3");
		colt.add("int");
		colt.add("double");
		colt.add("long");

		dbi.insertTable("catxxx","tabxxx",col,colt);
		dbi.setCatalogProperty("catxxx","Author","XXXX");
		dbi.setTableProperty("catxxx","tabxxx","Author","XXXX");
		dbi.setAttributeProperty("catxxx","tabxxx","col1","Author","XXXX");

	}
*/
	public class QueryResults
	{
		public QueryResults(ResultSet rs) throws SQLException
		{
			this.rs = rs;
			this.rsmd = this.rs.getMetaData();
			ncols = rsmd.getColumnCount();
			baseCatalogNameArray = new String[ncols];
			baseTableNameArray = new String[ncols];
			baseColumnNameArray = new String[ncols];
			columnNameArray = new String[ncols];      
			ucdArray = new String[ncols];
			unitArray = new String[ncols];
			datatypeArray = new String[ncols];
		}

		public void close() throws SQLException
		{
			rs.close();
		}    
 
		public boolean next()  throws SQLException
		{
			return rs.next();
		}

		public int getColumnCount() throws SQLException
		{
			return ncols;
		}

		public String getString(String s) throws SQLException
		{
			return rs.getString(s);
		}

		public String getString(int n)  throws SQLException
		{
			return rs.getString(n);
		}

		public String[] getStringArray()  throws SQLException
		{
			String[] arr = new String[ncols];
			for (int i = 1; i<=ncols; i++)
			{
				arr[i - 1] = rs.getString(i);
			}
			return arr;
		}

		public String getColumnName(int n)  throws SQLException
		{
			if (columnNameArray[n-1] == null)
			{
				columnNameArray[n-1] = rsmd.getColumnName(n);
			}
			return columnNameArray[n-1];
		}


		public String getBaseColumnName(int n)  throws SQLException
		{
			if (baseColumnNameArray[n-1]==null)
			{
				baseColumnNameArray[n-1]=((org.postgresql.PGResultSetMetaData)rsmd).getBaseColumnName(n);
			}
			return baseColumnNameArray[n-1];
		}

		public String[] getBaseColumnNameArray()  throws SQLException
		{
			for (int n = 1; n < ncols; n++)
			{
				if (baseColumnNameArray[n - 1] == null)
				{
					baseColumnNameArray[n - 1] = ((org.postgresql.PGResultSetMetaData)rsmd).getBaseColumnName(n);
				}
			}
			return baseColumnNameArray;
		}

		public String[] getColumnNameArray()  throws SQLException
		{
			for (int n = 1; n < ncols; n++)
			{
				if (columnNameArray[n - 1] == null)
				{
					columnNameArray[n - 1] = rsmd.getColumnName(n);
				}
			}
			return columnNameArray;
		}

		public String getBaseTableName(int n)  throws SQLException
		{
			if (baseTableNameArray[n-1]==null)
			{
				baseTableNameArray[n-1] = ((org.postgresql.PGResultSetMetaData)rsmd).getBaseTableName(n);
			}
			return baseTableNameArray[n-1];
		}

		public String getBaseCatalogName(int n)  throws SQLException
		{
			if (baseCatalogNameArray[n-1]==null)
			{
				baseCatalogNameArray[n-1] = ((org.postgresql.PGResultSetMetaData)rsmd).getBaseSchemaName(n);
			}
			return  baseCatalogNameArray[n-1];
		}

		public String getUcd(int n)  throws SQLException
		{
			if (ucdArray[n-1]==null)
			{
				PreparedStatement stmt = conn.prepareStatement("select cas_get_column_ucd(?, ?, ?)");
				stmt.setString(1, getBaseCatalogName(n));
				stmt.setString(2, getBaseTableName(n));
				stmt.setString(3, getBaseColumnName(n));
				ResultSet rs = stmt.executeQuery(); 
				rs.next();
				ucdArray[n - 1] = rs.getString(1);
				if (ucdArray[n-1]==null) ucdArray[n-1]="";        
				rs.close();
				stmt.close();
			}
			return ucdArray[n-1];
		}

		public String getDatatype(int n)  throws SQLException
		{
			if (datatypeArray[n - 1] == null)
			{
				PreparedStatement stmt = conn.prepareStatement("select cas_get_external_datatype(?, ?, ?)");
				stmt.setString(1, getBaseCatalogName(n));
				stmt.setString(2, getBaseTableName(n));
				stmt.setString(3, getBaseColumnName(n));
				ResultSet rs = stmt.executeQuery(); 
				rs.next();
				datatypeArray[n - 1] = rs.getString(1);
				rs.close();
				stmt.close();
			}
			return datatypeArray[n - 1];
		}


		public String getUnit(int n)  throws SQLException
		{
			if (unitArray[n-1]==null)
			{
				PreparedStatement stmt = conn.prepareStatement("select cas_get_column_unit(?, ?, ?)");
				stmt.setString(1, getBaseCatalogName(n));
				stmt.setString(2, getBaseTableName(n));
				stmt.setString(3, getBaseColumnName(n));
				ResultSet rs = stmt.executeQuery(); 
				rs.next();
				unitArray[n - 1] = rs.getString(1);
				if (unitArray[n - 1] == null) 
				{
					unitArray[n - 1] = "";
				}
				rs.close();
				stmt.close();
			}
			return unitArray[n-1];
		}

		public String getColumnInfo(int n)  throws SQLException
		{
			PreparedStatement pstmt = conn.prepareStatement("select cas_get_column_info(?, ?, ?)");
			pstmt.setString(1, getBaseCatalogName(n));
			pstmt.setString(2, getBaseTableName(n));
			pstmt.setString(3, getBaseColumnName(n));
			ResultSet rs = pstmt.executeQuery(); 
			rs.next();
			String columnInfo = rs.getString(1);
			if (columnInfo == null) 
			{
				columnInfo = "";
			}
			rs.close();
			pstmt.close();
			return columnInfo;
		}


		public String getColumnDescription(int n)  throws SQLException
		{
			PreparedStatement stmt = conn.prepareStatement("select cas_get_column_description(?, ?, ?)");
			stmt.setString(1, getBaseCatalogName(n));
			stmt.setString(2, getBaseTableName(n));
			stmt.setString(3, getBaseColumnName(n));
			ResultSet rs = stmt.executeQuery(); 
			rs.next();
			String columnDescription = rs.getString(1);
			if (columnDescription == null)
			{
				columnDescription = "";
			}
			rs.close();
			stmt.close();      
			return columnDescription;
		}

		private String[] baseCatalogNameArray;
		private String[] baseTableNameArray;
		private String[] baseColumnNameArray;
		private String[] columnNameArray;
		private String[] ucdArray;
		private String[] unitArray;
		private String[] datatypeArray;    

		private ResultSet rs;
		private ResultSetMetaData rsmd;
		private int ncols;
	};
	
}
