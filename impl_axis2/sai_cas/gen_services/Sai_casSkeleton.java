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
                  getCatalogInfo_U.getArgs0(),getCatalogInfo_U.getArgs1(),
                  getCatalogInfo_U.getArgs2()));
                return ret;
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getCatalogInfo_U");
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
                  deleteTable.getArgs0(),deleteTable.getArgs1(),
                  deleteTable.getArgs2(),deleteTable.getArgs3());
              } catch( RemoteException e)
              {
                throw new RemoteExceptionException(e.getMessage(),e.getCause());
              }
                //TODO : fill this with the necessary business logic
                
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
						getCatalogDescription.getArgs0()));
                return ret;
				}
				catch (RemoteException e)
				{
					throw new RemoteExceptionException(e.getMessage(),e.getCause());
				}

                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getCatalogDescription");
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
                //TODO : fill this with the necessary business logic
                try
                {
                sai_cas.services.MainAxisServices.insertCatalog(
                  insertCatalog.getArgs0(),insertCatalog.getArgs1(),
                  insertCatalog.getArgs2());
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
                  getCatalogInfo.getArgs0()));
                return ret;

                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getCatalogInfo");
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
                  getColumnInfos_U.getArgs0(),getColumnInfos_U.getArgs1(),
                  getColumnInfos_U.getArgs2(),getColumnInfos_U.getArgs3()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;

                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnInfos_U");
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
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getCatalogNames");
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
                  getColumnUnits.getArgs0(),getColumnUnits.getArgs1()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;

                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnUnits");
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
                  getIndexes.getArgs0(),getIndexes.getArgs1());
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

                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getIndexes");
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
                  getTableCount_U.getArgs0(),getTableCount_U.getArgs1(),
                  getTableCount_U.getArgs2(),getTableCount_U.getArgs3()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;

                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getTableCount_U");
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
                  getColumnDescriptions.getArgs0(),
                  getColumnDescriptions.getArgs1()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;


                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnDescriptions");
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
                //TODO : fill this with the necessary business logic
                try {
                MainAxisServices.insertCatalogFromVotable(
                  insertCatalogFromVotable.getArgs0(),insertCatalogFromVotable.getArgs1(),
                  insertCatalogFromVotable.getArgs2());
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
                  getColumnInfos.getArgs0(),getColumnInfos.getArgs1()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnInfos");
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
                  deleteCatalog.getArgs0(),deleteCatalog.getArgs1(),
                  deleteCatalog.getArgs2());
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                //TODO : fill this with the necessary business logic
                
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
                  getColumnNames.getArgs0(),getColumnNames.getArgs1()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnNames");
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
                  getTableCount.getArgs0(),getTableCount.getArgs1()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getTableCount");
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
                  getColumnNames_U.getArgs0(),getColumnNames_U.getArgs1(),
                  getColumnNames_U.getArgs2(),getColumnNames_U.getArgs3()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;

                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnNames_U");
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
                  dumpCatalog.getArgs0(),dumpCatalog.getArgs1(),
                  dumpCatalog.getArgs2()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#dumpCatalog");
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
                  getTableNames_U.getArgs0(),getTableNames_U.getArgs1(),
                  getTableNames_U.getArgs2()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;

                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getTableNames_U");
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
                  getCatalogDescription_U.getArgs0(),getCatalogDescription_U.getArgs1(),
                  getCatalogDescription_U.getArgs2()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getCatalogDescription_U");
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
                  getColumnUCDs_U.getArgs0(),getColumnUCDs_U.getArgs1(),
                  getColumnUCDs_U.getArgs2(),getColumnUCDs_U.getArgs3()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnUCDs_U");
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
                //TODO : fill this with the necessary business logic
                try {
                MainAxisServices.insertCatalogFromURI(
                  insertCatalogFromURI.getArgs0(),insertCatalogFromURI.getArgs1(),
                  insertCatalogFromURI.getArgs2());
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
                  getTableDescription_U.getArgs0(),getTableDescription_U.getArgs1(),
                  getTableDescription_U.getArgs2(),getTableDescription_U.getArgs3()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;

                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getTableDescription_U");
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
                  getColumnUnits_U.getArgs0(),getColumnUnits_U.getArgs1(),
                  getColumnUnits_U.getArgs2(),getColumnUnits_U.getArgs3()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;

                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnUnits_U");
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
                  getColumnUCDs.getArgs0(),getColumnUCDs.getArgs1()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnUCDs");
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
                  getConeSearchAsString.getArgs0(),getConeSearchAsString.getArgs1(),
                  getConeSearchAsString.getArgs2(),getConeSearchAsString.getArgs3(),
                  getConeSearchAsString.getArgs4(),getConeSearchAsString.getArgs5()));
                }
				//catch (RemoteException e)
                {
                //  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getConeSearchAsString");
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
                  getConeSearchAsString_withColumns.getArgs0(),getConeSearchAsString_withColumns.getArgs1(),
                  getConeSearchAsString_withColumns.getArgs2(),getConeSearchAsString_withColumns.getArgs3(),
                  getConeSearchAsString_withColumns.getArgs4(),getConeSearchAsString_withColumns.getArgs5(),
					getConeSearchAsString_withColumns.getArgs6()));
                }
				//catch (RemoteException e)
                {
                  //throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getConeSearchAsString");
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
                  getColumnDatatypes.getArgs0(),getColumnDatatypes.getArgs1()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;

                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnDatatypes");
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
                  getColumnDescriptions_U.getArgs0(),getColumnDescriptions_U.getArgs1(),
                  getColumnDescriptions_U.getArgs2(),getColumnDescriptions_U.getArgs3()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;                
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnDescriptions_U");
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
                  getTableNames.getArgs0()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;                
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getTableNames");
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
                  getColumnDatatypes_U.getArgs0(),getColumnDatatypes_U.getArgs1(),
                  getColumnDatatypes_U.getArgs2(),getColumnDatatypes_U.getArgs3()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnDatatypes_U");
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
                  getTableDescription.getArgs0(),getTableDescription.getArgs1()));
                } catch (RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }
                return ret;
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getTableDescription");
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
                  insertTable.getArgs0(),insertTable.getArgs1(),
                  insertTable.getArgs2());
                } catch(RemoteException e)
                {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
                }                

                //TODO : fill this with the necessary business logic
                
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

                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getDBLastChangedDate");
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
              ret.set_return(MainAxisServices.getCatalogNames_U(getCatalogNames_U.getArgs0(),
				getCatalogNames_U.getArgs1()));
              } catch(RemoteException e)
              {
                  throw new RemoteExceptionException(e.getMessage(),e.getCause());
              }
              return ret;
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getCatalogNames_U");
        }
     
    }
    