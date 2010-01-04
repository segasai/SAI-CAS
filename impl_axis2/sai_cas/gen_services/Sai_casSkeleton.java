
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
        
    public class Sai_casSkeleton{
        
         
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
                                     * @param getCatalogNames
             * @throws RemoteExceptionException : 
         */
        
                 public sai_cas.gen_services.GetCatalogNamesResponse getCatalogNames
                  (
                  sai_cas.gen_services.GetCatalogNames getCatalogNames
                  )
            throws RemoteExceptionException{
                //TODO : fill this with the necessary business logic
                sai_cas.gen_services.GetCatalogNamesResponse ret =
                new sai_cas.gen_services.GetCatalogNamesResponse();
                try{
                ret.set_return(MainAxisServices.getCatalogNames());
                } catch(RemoteException e)
                {}
                return ret;
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getCatalogNames");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getColumnUnits
             * @throws ExceptionException : 
         */
        
                 public sai_cas.gen_services.GetColumnUnitsResponse getColumnUnits
                  (
                  sai_cas.gen_services.GetColumnUnits getColumnUnits
                  )
            throws ExceptionException{
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnUnits");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getIndexes
             * @throws ExceptionException : 
         */
        
                 public sai_cas.gen_services.GetIndexesResponse getIndexes
                  (
                  sai_cas.gen_services.GetIndexes getIndexes
                  )
            throws ExceptionException{
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getIndexes");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getColumnDescriptions
             * @throws ExceptionException : 
         */
        
                 public sai_cas.gen_services.GetColumnDescriptionsResponse getColumnDescriptions
                  (
                  sai_cas.gen_services.GetColumnDescriptions getColumnDescriptions
                  )
            throws ExceptionException{
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
             * @throws ExceptionException : 
         */
        
                 public sai_cas.gen_services.GetColumnInfosResponse getColumnInfos
                  (
                  sai_cas.gen_services.GetColumnInfos getColumnInfos
                  )
            throws ExceptionException{
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
             * @throws ExceptionException : 
         */
        
                 public sai_cas.gen_services.GetColumnNamesResponse getColumnNames
                  (
                  sai_cas.gen_services.GetColumnNames getColumnNames
                  )
            throws ExceptionException{
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
                                     * @param getColumnUCDs
             * @throws ExceptionException : 
         */
        
                 public sai_cas.gen_services.GetColumnUCDsResponse getColumnUCDs
                  (
                  sai_cas.gen_services.GetColumnUCDs getColumnUCDs
                  )
            throws ExceptionException{
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
             * @throws ExceptionException : 
         */
        
                 public sai_cas.gen_services.GetColumnDatatypesResponse getColumnDatatypes
                  (
                  sai_cas.gen_services.GetColumnDatatypes getColumnDatatypes
                  )
            throws ExceptionException{
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnDatatypes");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getTableNames
             * @throws ExceptionException : 
         */
        
                 public sai_cas.gen_services.GetTableNamesResponse getTableNames
                  (
                  sai_cas.gen_services.GetTableNames getTableNames
                  )
            throws ExceptionException{
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getTableNames");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getTableDescription
             * @throws ExceptionException : 
         */
        
                 public sai_cas.gen_services.GetTableDescriptionResponse getTableDescription
                  (
                  sai_cas.gen_services.GetTableDescription getTableDescription
                  )
            throws ExceptionException{
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getTableDescription");
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
     
    }
    