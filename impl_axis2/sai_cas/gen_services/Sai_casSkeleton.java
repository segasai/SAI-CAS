
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getCatalogInfo_U");
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
                  )
            {
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getCatalogDescription");
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getCatalogInfo");
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnInfos_U");
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
              {}
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnUnits");
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getIndexes");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getTableCount_U
         */
        
                 public sai_cas.gen_services.GetTableCount_UResponse getTableCount_U
                  (
                  sai_cas.gen_services.GetTableCount_U getTableCount_U
                  )
            {
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getTableCount_U");
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnDescriptions");
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnInfos");
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnNames");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getTableCount
         */
        
                 public sai_cas.gen_services.GetTableCountResponse getTableCount
                  (
                  sai_cas.gen_services.GetTableCount getTableCount
                  )
            {
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getTableCount");
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnNames_U");
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#dumpCatalog");
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getTableNames_U");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getCatalogDescription_U
         */
        
                 public sai_cas.gen_services.GetCatalogDescription_UResponse getCatalogDescription_U
                  (
                  sai_cas.gen_services.GetCatalogDescription_U getCatalogDescription_U
                  )
            {
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getCatalogDescription_U");
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnUCDs_U");
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getTableDescription_U");
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnUnits_U");
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnUCDs");
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getConeSearchAsString");
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnDatatypes");
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnDescriptions_U");
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getTableNames");
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnDatatypes_U");
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getTableDescription");
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getDBLastChangedDate");
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
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getCatalogNames_U");
        }
     
    }
    