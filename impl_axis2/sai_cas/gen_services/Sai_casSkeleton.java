/*
	   Copyright (C) 2005-2009 Sergey Koposov
   
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


/**
 * Sai_casSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */
    package sai_cas.gen_services;
    /**
     *  Sai_casSkeleton java skeleton for the axisService
     */
    import sai_cas.services.MainAxisServices;
    import java.rmi.RemoteException;
    import org.apache.log4j.Logger;
    
    public class Sai_casSkeleton{
      static Logger logger = Logger.getLogger("sai_cas.AXIS_SERVICES");
        
         
        /**
         * Auto generated method signature
         * 
                                     * @param getCatalogInfo_U
         */
        
                 public sai_cas.gen_services.GetCatalogInfo_UResponse getCatalogInfo_U
                  (
                  sai_cas.gen_services.GetCatalogInfo_U getCatalogInfo_U
                  )
            {
                sai_cas.gen_services.GetCatalogInfo_UResponse ret = new
                sai_cas.gen_services.GetCatalogInfo_UResponse();
                ret.set_return(MainAxisServices.getCatalogInfo_U(
                  getCatalogInfo_U.getCatalog(),getCatalogInfo_U.getUser(),
                  getCatalogInfo_U.getPassword()));
                return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param deleteTable
             * @throws RemoteExceptionException : 
         */
        
                 public void deleteTable
                  (
                  sai_cas.gen_services.DeleteTable deleteTable
                  )
            throws RemoteExceptionException{
              try
              {
                MainAxisServices.deleteTable(
                  deleteTable.getCatalog(),deleteTable.getTable(),
                  deleteTable.getUser(),deleteTable.getPassword());
              } catch( RemoteException e)
              {
                throw new RemoteExceptionException(e.getMessage(),e.getCause());
              }
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getCatalogDescription
         */
        
                 public sai_cas.gen_services.GetCatalogDescriptionResponse getCatalogDescription
                  (
                  sai_cas.gen_services.GetCatalogDescription getCatalogDescription
                  ) throws RemoteExceptionException
            {
                try
                {
					sai_cas.gen_services.GetCatalogDescriptionResponse ret = new
					sai_cas.gen_services.GetCatalogDescriptionResponse();
					ret.set_return(MainAxisServices.getCatalogDescription(
						getCatalogDescription.getCatalog()));
                return ret;
				}
				catch (RemoteException e)
				{
					throw new RemoteExceptionException(e.getMessage(),e.getCause());
				}
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param insertCatalog
             * @throws RemoteExceptionException : 
         */
        
                 public void insertCatalog
                  (
                  sai_cas.gen_services.InsertCatalog insertCatalog
                  )
            throws RemoteExceptionException{
                try
                {
                sai_cas.services.MainAxisServices.insertCatalog(
                  insertCatalog.getCatalogString(),insertCatalog.getAdminUser(),
                  insertCatalog.getAdminPassword());
                } catch(RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }                
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getCatalogInfo
         */
        
                 public sai_cas.gen_services.GetCatalogInfoResponse getCatalogInfo
                  (
                  sai_cas.gen_services.GetCatalogInfo getCatalogInfo
                  )
            {
                sai_cas.gen_services.GetCatalogInfoResponse ret = new
                sai_cas.gen_services.GetCatalogInfoResponse();
                ret.set_return(MainAxisServices.getCatalogInfo(
                  getCatalogInfo.getCatalog()));
                return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getColumnInfos_U
             * @throws RemoteExceptionException : 
         */
        
                 public sai_cas.gen_services.GetColumnInfos_UResponse getColumnInfos_U
                  (
                  sai_cas.gen_services.GetColumnInfos_U getColumnInfos_U
                  )
            throws RemoteExceptionException{
                sai_cas.gen_services.GetColumnInfos_UResponse ret = new
                sai_cas.gen_services.GetColumnInfos_UResponse();
                try {
                ret.set_return(MainAxisServices.getColumnInfos_U(
                  getColumnInfos_U.getCatalogName(),getColumnInfos_U.getTableName(),
                  getColumnInfos_U.getUser(),getColumnInfos_U.getPassword()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
             * @throws RemoteExceptionException : 
         */
        
                 public sai_cas.gen_services.GetCatalogNamesResponse getCatalogNames
                  (
                  
                  )
            throws RemoteExceptionException{
              sai_cas.gen_services.GetCatalogNamesResponse ret =
              new sai_cas.gen_services.GetCatalogNamesResponse();
              try{
              ret.set_return(MainAxisServices.getCatalogNames());
              } catch(RemoteException e)
              {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
              }
              return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getColumnUnits
             * @throws RemoteExceptionException : 
         */
        
                 public sai_cas.gen_services.GetColumnUnitsResponse getColumnUnits
                  (
                  sai_cas.gen_services.GetColumnUnits getColumnUnits
                  )
            throws RemoteExceptionException{
                sai_cas.gen_services.GetColumnUnitsResponse ret = new
                sai_cas.gen_services.GetColumnUnitsResponse();
                try {
                ret.set_return(MainAxisServices.getColumnUnits(
                  getColumnUnits.getCatalogName(),getColumnUnits.getTableName()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getIndexes
             * @throws RemoteExceptionException : 
         */
        
                 public sai_cas.gen_services.GetIndexesResponse getIndexes
                  (
                  sai_cas.gen_services.GetIndexes getIndexes
                  )
            throws RemoteExceptionException{
                sai_cas.gen_services.GetIndexesResponse ret = new
                sai_cas.gen_services.GetIndexesResponse();
                try {
                  String [][] idx =MainAxisServices.getIndexes(
                  getIndexes.getCatalogName(),getIndexes.getTableName());
                  ArrayOfString[] arr = new ArrayOfString[idx.length];
                  int tmp=0;
                  for (String[] s: idx)
                  {
                    arr[tmp]=new ArrayOfString();
                    arr[tmp++].setArray(s);
                  }
                ret.set_return(arr);
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getTableCount_U
         */
        
                 public sai_cas.gen_services.GetTableCount_UResponse getTableCount_U
                  (
                  sai_cas.gen_services.GetTableCount_U getTableCount_U
                  ) throws RemoteExceptionException
            {
                sai_cas.gen_services.GetTableCount_UResponse ret = new
                sai_cas.gen_services.GetTableCount_UResponse();
                try {
                ret.set_return(MainAxisServices.getTableCount_U(
                  getTableCount_U.getCatalogName(),getTableCount_U.getTableName(),
                  getTableCount_U.getUser(),getTableCount_U.getPassword()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getColumnDescriptions
             * @throws RemoteExceptionException : 
         */
        
                 public sai_cas.gen_services.GetColumnDescriptionsResponse getColumnDescriptions
                  (
                  sai_cas.gen_services.GetColumnDescriptions getColumnDescriptions
                  )
            throws RemoteExceptionException{
                sai_cas.gen_services.GetColumnDescriptionsResponse ret = new
                sai_cas.gen_services.GetColumnDescriptionsResponse();
                try {
                ret.set_return(MainAxisServices.getColumnDescriptions(
                  getColumnDescriptions.getCatalogName(),
                  getColumnDescriptions.getTableName()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param insertCatalogFromVotable
             * @throws RemoteExceptionException : 
         */
        
                 public void insertCatalogFromVotable
                  (
                  sai_cas.gen_services.InsertCatalogFromVotable insertCatalogFromVotable
                  )
            throws RemoteExceptionException{
                try {
                MainAxisServices.insertCatalogFromVotable(
                  insertCatalogFromVotable.getCatalogString(),
                  insertCatalogFromVotable.getUser(),
                  insertCatalogFromVotable.getPassword());
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getColumnInfos
             * @throws RemoteExceptionException : 
         */
        
                 public sai_cas.gen_services.GetColumnInfosResponse getColumnInfos
                  (
                  sai_cas.gen_services.GetColumnInfos getColumnInfos
                  )
            throws RemoteExceptionException{
                sai_cas.gen_services.GetColumnInfosResponse ret = new
                sai_cas.gen_services.GetColumnInfosResponse();
                try {
                ret.set_return(MainAxisServices.getColumnInfos(
                  getColumnInfos.getCatalogName(),getColumnInfos.getTableName()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param deleteCatalog
             * @throws RemoteExceptionException : 
         */
        
                 public void deleteCatalog
                  (
                  sai_cas.gen_services.DeleteCatalog deleteCatalog
                  )
            throws RemoteExceptionException{
                try {
                MainAxisServices.deleteCatalog(
                  deleteCatalog.getCatalog(),deleteCatalog.getAdminUser(),
                  deleteCatalog.getAdminPassword());
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }                
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getColumnNames
             * @throws RemoteExceptionException : 
         */
        
                 public sai_cas.gen_services.GetColumnNamesResponse getColumnNames
                  (
                  sai_cas.gen_services.GetColumnNames getColumnNames
                  )
            throws RemoteExceptionException{
                sai_cas.gen_services.GetColumnNamesResponse ret = new
                sai_cas.gen_services.GetColumnNamesResponse();
                try {
                ret.set_return(MainAxisServices.getColumnNames(
                  getColumnNames.getCatalogName(),getColumnNames.getTableName()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getTableCount
         */
        
                 public sai_cas.gen_services.GetTableCountResponse getTableCount
                  (
                  sai_cas.gen_services.GetTableCount getTableCount
                  ) throws RemoteExceptionException
            {
                sai_cas.gen_services.GetTableCountResponse ret = new
                sai_cas.gen_services.GetTableCountResponse();
                try {
                ret.set_return(MainAxisServices.getTableCount(
                  getTableCount.getCatalogName(),getTableCount.getTableName()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getColumnNames_U
             * @throws RemoteExceptionException : 
         */
        
                 public sai_cas.gen_services.GetColumnNames_UResponse getColumnNames_U
                  (
                  sai_cas.gen_services.GetColumnNames_U getColumnNames_U
                  )
            throws RemoteExceptionException{
                sai_cas.gen_services.GetColumnNames_UResponse ret = new
                sai_cas.gen_services.GetColumnNames_UResponse();
                try {
                ret.set_return(MainAxisServices.getColumnNames_U(
                  getColumnNames_U.getCatalogName(),getColumnNames_U.getTableName(),
                  getColumnNames_U.getUser(),getColumnNames_U.getPassword()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param dumpCatalog
             * @throws RemoteExceptionException : 
         */
        
                 public sai_cas.gen_services.DumpCatalogResponse dumpCatalog
                  (
                  sai_cas.gen_services.DumpCatalog dumpCatalog
                  )
            throws RemoteExceptionException{
                sai_cas.gen_services.DumpCatalogResponse ret = new
                sai_cas.gen_services.DumpCatalogResponse();
                try {
                ret.set_return(MainAxisServices.dumpCatalog(
                  dumpCatalog.getCatalogName(),dumpCatalog.getAdminUser(),
                  dumpCatalog.getAdminPassword()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getTableNames_U
             * @throws RemoteExceptionException : 
         */
        
                 public sai_cas.gen_services.GetTableNames_UResponse getTableNames_U
                  (
                  sai_cas.gen_services.GetTableNames_U getTableNames_U
                  )
            throws RemoteExceptionException{
                sai_cas.gen_services.GetTableNames_UResponse ret = new
                sai_cas.gen_services.GetTableNames_UResponse();
                try {
                ret.set_return(MainAxisServices.getTableNames_U(
                  getTableNames_U.getCatalogName(),getTableNames_U.getUser(),
                  getTableNames_U.getPassword()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getCatalogDescription_U
         */
        
                 public sai_cas.gen_services.GetCatalogDescription_UResponse getCatalogDescription_U
                  (
                  sai_cas.gen_services.GetCatalogDescription_U getCatalogDescription_U
                  ) throws RemoteExceptionException
            {
                sai_cas.gen_services.GetCatalogDescription_UResponse ret = new
                sai_cas.gen_services.GetCatalogDescription_UResponse();
                try {
                ret.set_return(MainAxisServices.getCatalogDescription_U(
                  getCatalogDescription_U.getCatalog(),
                  getCatalogDescription_U.getUser(),
                  getCatalogDescription_U.getPassword()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getColumnUCDs_U
             * @throws RemoteExceptionException : 
         */
        
                 public sai_cas.gen_services.GetColumnUCDs_UResponse getColumnUCDs_U
                  (
                  sai_cas.gen_services.GetColumnUCDs_U getColumnUCDs_U
                  )
            throws RemoteExceptionException{
                sai_cas.gen_services.GetColumnUCDs_UResponse ret = new
                sai_cas.gen_services.GetColumnUCDs_UResponse();
                try {
                ret.set_return(MainAxisServices.getColumnUCDs_U(
                  getColumnUCDs_U.getCatalogName(),getColumnUCDs_U.getTableName(),
                  getColumnUCDs_U.getUser(),getColumnUCDs_U.getPassword()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param insertCatalogFromURI
             * @throws RemoteExceptionException : 
         */
        
                 public void insertCatalogFromURI
                  (
                  sai_cas.gen_services.InsertCatalogFromURI insertCatalogFromURI
                  )
            throws RemoteExceptionException{
                try {
                MainAxisServices.insertCatalogFromURI(
                  insertCatalogFromURI.getUriCatalog(),
                  insertCatalogFromURI.getAdminUser(),
                  insertCatalogFromURI.getAdminPassword());
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }                
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getTableDescription_U
             * @throws RemoteExceptionException : 
         */
        
                 public sai_cas.gen_services.GetTableDescription_UResponse getTableDescription_U
                  (
                  sai_cas.gen_services.GetTableDescription_U getTableDescription_U
                  )
            throws RemoteExceptionException{
                sai_cas.gen_services.GetTableDescription_UResponse ret = new
                sai_cas.gen_services.GetTableDescription_UResponse();
                try {
                ret.set_return(MainAxisServices.getTableDescription_U(
                  getTableDescription_U.getCatalogName(),
                  getTableDescription_U.getTableName(),
                  getTableDescription_U.getUser(),getTableDescription_U.getPassword()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getColumnUnits_U
             * @throws RemoteExceptionException : 
         */
        
                 public sai_cas.gen_services.GetColumnUnits_UResponse getColumnUnits_U
                  (
                  sai_cas.gen_services.GetColumnUnits_U getColumnUnits_U
                  )
            throws RemoteExceptionException{
                sai_cas.gen_services.GetColumnUnits_UResponse ret = new
                sai_cas.gen_services.GetColumnUnits_UResponse();
                try {
                ret.set_return(MainAxisServices.getColumnUnits_U(
                  getColumnUnits_U.getCatalogName(),getColumnUnits_U.getTableName(),
                  getColumnUnits_U.getUser(),getColumnUnits_U.getPassword()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getColumnUCDs
             * @throws RemoteExceptionException : 
         */
        
                 public sai_cas.gen_services.GetColumnUCDsResponse getColumnUCDs
                  (
                  sai_cas.gen_services.GetColumnUCDs getColumnUCDs
                  )
            throws RemoteExceptionException{
                sai_cas.gen_services.GetColumnUCDsResponse ret = new
                sai_cas.gen_services.GetColumnUCDsResponse();
                try {
                ret.set_return(MainAxisServices.getColumnUCDs(
                  getColumnUCDs.getCatalogName(),getColumnUCDs.getTableName()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getConeSearchAsString
         */
        
                 public sai_cas.gen_services.GetConeSearchAsStringResponse getConeSearchAsString
                  (
                  sai_cas.gen_services.GetConeSearchAsString getConeSearchAsString
                  )
            {
                sai_cas.gen_services.GetConeSearchAsStringResponse ret = new
                sai_cas.gen_services.GetConeSearchAsStringResponse();
                //try
				{
                ret.set_return(MainAxisServices.getConeSearchAsString(
                  getConeSearchAsString.getCat(),getConeSearchAsString.getTab(),
                  getConeSearchAsString.getRa(),getConeSearchAsString.getDec(),
                  getConeSearchAsString.getSr(),getConeSearchAsString.getFormat()));
                }
				//catch (RemoteException e)
                {
                //  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
        }
     
        /**
         * Auto generated method signature
         * 
                                     * @param getConeSearchAsString
         */
        
                 public sai_cas.gen_services.GetConeSearchAsString_withColumnsResponse getConeSearchAsString_withColumns
                  (
                  sai_cas.gen_services.GetConeSearchAsString_withColumns getConeSearchAsString_withColumns
                  )
            {
                sai_cas.gen_services.GetConeSearchAsString_withColumnsResponse ret = new
                sai_cas.gen_services.GetConeSearchAsString_withColumnsResponse();
                //try
				{
                ret.set_return(MainAxisServices.getConeSearchAsString_withColumns(
                  getConeSearchAsString_withColumns.getCat(),
                  getConeSearchAsString_withColumns.getTab(),
                  getConeSearchAsString_withColumns.getRa(),
                  getConeSearchAsString_withColumns.getDec(),
                  getConeSearchAsString_withColumns.getSr(),
                  getConeSearchAsString_withColumns.getFormat(),
				getConeSearchAsString_withColumns.getColumnList()));
                }
				//catch (RemoteException e)
                {
                  //throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
        }


         
        /**
         * Auto generated method signature
         * 
                                     * @param getColumnDatatypes
             * @throws RemoteExceptionException : 
         */
        
                 public sai_cas.gen_services.GetColumnDatatypesResponse getColumnDatatypes
                  (
                  sai_cas.gen_services.GetColumnDatatypes getColumnDatatypes
                  )
            throws RemoteExceptionException{
                sai_cas.gen_services.GetColumnDatatypesResponse ret = new
                sai_cas.gen_services.GetColumnDatatypesResponse();
                try {
                ret.set_return(MainAxisServices.getColumnDatatypes(
                  getColumnDatatypes.getCatalogName(),getColumnDatatypes.getTableName()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getColumnDescriptions_U
             * @throws RemoteExceptionException : 
         */
        
                 public sai_cas.gen_services.GetColumnDescriptions_UResponse getColumnDescriptions_U
                  (
                  sai_cas.gen_services.GetColumnDescriptions_U getColumnDescriptions_U
                  )
            throws RemoteExceptionException{
                sai_cas.gen_services.GetColumnDescriptions_UResponse ret = new
                sai_cas.gen_services.GetColumnDescriptions_UResponse();
                try {
                ret.set_return(MainAxisServices.getColumnDescriptions_U(
                  getColumnDescriptions_U.getCatalogName(),
                  getColumnDescriptions_U.getTableName(),
                  getColumnDescriptions_U.getUser(),
                  getColumnDescriptions_U.getPassword()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;                
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getTableNames
             * @throws RemoteExceptionException : 
         */
        
                 public sai_cas.gen_services.GetTableNamesResponse getTableNames
                  (
                  sai_cas.gen_services.GetTableNames getTableNames
                  )
            throws RemoteExceptionException{
                sai_cas.gen_services.GetTableNamesResponse ret = new
                sai_cas.gen_services.GetTableNamesResponse();
                try {
                ret.set_return(MainAxisServices.getTableNames(
                  getTableNames.getCatalogName()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;                
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getColumnDatatypes_U
             * @throws RemoteExceptionException : 
         */
        
                 public sai_cas.gen_services.GetColumnDatatypes_UResponse getColumnDatatypes_U
                  (
                  sai_cas.gen_services.GetColumnDatatypes_U getColumnDatatypes_U
                  )
            throws RemoteExceptionException{
                sai_cas.gen_services.GetColumnDatatypes_UResponse ret = new
                sai_cas.gen_services.GetColumnDatatypes_UResponse();
                try {
                ret.set_return(MainAxisServices.getColumnDatatypes_U(
                  getColumnDatatypes_U.getCatalogName(),
                  getColumnDatatypes_U.getTableName(),
                  getColumnDatatypes_U.getUser(),
                  getColumnDatatypes_U.getPassword()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getTableDescription
             * @throws RemoteExceptionException : 
         */
        
                 public sai_cas.gen_services.GetTableDescriptionResponse getTableDescription
                  (
                  sai_cas.gen_services.GetTableDescription getTableDescription
                  )
            throws RemoteExceptionException{
                sai_cas.gen_services.GetTableDescriptionResponse ret = new
                sai_cas.gen_services.GetTableDescriptionResponse();
                try {
                ret.set_return(MainAxisServices.getTableDescription(
                  getTableDescription.getCatalogName(),getTableDescription.getTableName()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param insertTable
             * @throws RemoteExceptionException : 
         */
        
                 public void insertTable
                  (
                  sai_cas.gen_services.InsertTable insertTable
                  )
            throws RemoteExceptionException{
                try
                {
                sai_cas.services.MainAxisServices.insertTable(
                  insertTable.getCatalogString(),insertTable.getAdminUser(),
                  insertTable.getAdminPassword());
                } catch(RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }                
        }
     
         
        /**
         * Auto generated method signature
         * 
         */
        
                 public sai_cas.gen_services.GetDBLastChangedDateResponse getDBLastChangedDate
                  (
                  
                  )
            {
              sai_cas.gen_services.GetDBLastChangedDateResponse ret =
              new sai_cas.gen_services.GetDBLastChangedDateResponse();
              //try{
              ret.set_return(MainAxisServices.getDBLastChangedDate());
              //} catch(RemoteException e)
              {
              //    throw new RemoteExceptionException(e.getMessage(),e.getCause());
              }
              return ret;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getCatalogNames_U
             * @throws RemoteExceptionException : 
         */
        
                 public sai_cas.gen_services.GetCatalogNames_UResponse getCatalogNames_U
                  (
                  sai_cas.gen_services.GetCatalogNames_U getCatalogNames_U
                  )
            throws RemoteExceptionException{
              sai_cas.gen_services.GetCatalogNames_UResponse ret =
              new sai_cas.gen_services.GetCatalogNames_UResponse();
              try{
              ret.set_return(MainAxisServices.getCatalogNames_U(
            		  getCatalogNames_U.getUser(),
				getCatalogNames_U.getPassword()));
              } catch(RemoteException e)
              {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
              }
              return ret;
        }
     
    }
    