
    /**
     * Sai_casSkeleton.java
     *
     * This file was auto-generated from WSDL
     * by the Apache Axis2 version: 1.1-RC2 Nov 02, 2006 (02:37:50 LKT)
     */
    package sai_cas.gen_services;
import sai_cas.services.MainAxisServices;
import org.apache.log4j.Logger;

    /**
     *  Sai_casSkeleton java skeleton for the axisService
     */
    public class Sai_casSkeleton{
	static Logger logger = Logger.getLogger("sai_cas.AXIS_SERVICES");
     
         
        /**
         * Auto generated method signature
         
          
         */
        public  sai_cas.gen_services.GetDBLastChangedDateResponse getDBLastChangedDate
                  (
          
          )
         
           {
                //Todo fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getDBLastChangedDate");
        }
     
         
        /**
         * Auto generated method signature
         
          
         */
        public  sai_cas.gen_services.GetCatalogNamesResponse getCatalogNames
                  (
          
          )
         
           {
                //Todo fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getCatalogNames");
        }
     
         
        /**
         * Auto generated method signature
         
          * @param param4
         
         */
        public  sai_cas.gen_services.GetCatalogDescriptionResponse getCatalogDescription
                  (
          sai_cas.gen_services.GetCatalogDescription param4
          )
         
           {
		try 
		{
			sai_cas.gen_services.GetCatalogDescriptionResponse gcdr = new 
			sai_cas.gen_services.GetCatalogDescriptionResponse();
			gcdr.set_return(MainAxisServices.getCatalogDescription(param4.getCatalog()));
			return gcdr;
		}
		catch (Exception e)
		{
			logger.error("Catched exception ",e);
			return null;
		}	


                //Todo fill this with the necessary business logic
   //             throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getCatalogDescription");
        }
     
         
        /**
         * Auto generated method signature
         
          * @param param6
         
         */
        public  sai_cas.gen_services.GetCatalogInfoResponse getCatalogInfo
                  (
          sai_cas.gen_services.GetCatalogInfo param6
          )
         
           {
                //Todo fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getCatalogInfo");
        }
     
         
        /**
         * Auto generated method signature
         
          * @param param8
         
         */
        public  sai_cas.gen_services.GetColumnDescriptionsResponse getColumnDescriptions
                  (
          sai_cas.gen_services.GetColumnDescriptions param8
          )
         
           {
                //Todo fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnDescriptions");
        }
     
         
        /**
         * Auto generated method signature
         
          * @param param10
         
         */
        public  sai_cas.gen_services.GetIndexesResponse getIndexes
                  (
          sai_cas.gen_services.GetIndexes param10
          )
         
           throws GetIndexesFaultException{
                //Todo fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getIndexes");
        }
     
         
        /**
         * Auto generated method signature
         
          * @param param12
         
         */
        public  sai_cas.gen_services.GetConeSearchAsString1Response getConeSearchAsString1
                  (
          sai_cas.gen_services.GetConeSearchAsString1 param12
          )
         
           {
                //Todo fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getConeSearchAsString1");
        }
     
         
        /**
         * Auto generated method signature
         
          * @param param14
         
         */
        public  sai_cas.gen_services.GetColumnUnitsResponse getColumnUnits
                  (
          sai_cas.gen_services.GetColumnUnits param14
          )
         
           {
                //Todo fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnUnits");
        }
     
         
        /**
         * Auto generated method signature
         
          * @param param16
         
         */
        public  sai_cas.gen_services.GetColumnNamesResponse getColumnNames
                  (
          sai_cas.gen_services.GetColumnNames param16
          )
         
           {
                //Todo fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnNames");
        }
     
         
        /**
         * Auto generated method signature
         
          * @param param18
         
         */
/*        public  sai_cas.gen_services.GetColumnUCDsResponse getColumnUCDs
                  (
          sai_cas.gen_services.GetColumnUCDs param18
          )
         
           {
                //Todo fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnUCDs");
        }
 */    
         
        /**
         * Auto generated method signature
         
          * @param param20
         
         */
        public  sai_cas.gen_services.GetConeSearchAsStringResponse getConeSearchAsString
                  (
          sai_cas.gen_services.GetConeSearchAsString param20
          )
         
           {
                //Todo fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getConeSearchAsString");
        }
     
         
        /**
         * Auto generated method signature
         
          * @param param22
         
         */
        public  sai_cas.gen_services.GetConeSearchAsString2Response getConeSearchAsString2
                  (
          sai_cas.gen_services.GetConeSearchAsString2 param22
          )
         
           {
                //Todo fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getConeSearchAsString2");
        }
     
         
        /**
         * Auto generated method signature
         
          * @param param24
         
         */
        public  sai_cas.gen_services.GetColumnInfosResponse getColumnInfos
                  (
          sai_cas.gen_services.GetColumnInfos param24
          )
         
           {
                //Todo fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getColumnInfos");
        }
     
         
        /**
         * Auto generated method signature
         
          * @param param26
         
         */
        public  sai_cas.gen_services.GetTableNamesResponse getTableNames
                  (
          sai_cas.gen_services.GetTableNames param26
          )
         
           {
                //Todo fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getTableNames");
        }
     
    }
    