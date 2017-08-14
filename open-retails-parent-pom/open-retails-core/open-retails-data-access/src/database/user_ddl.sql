CREATE SEQUENCE USER_TYPE_SEQ INCREMENT BY 1 CACHE 1;
/*Date should be in GMT always in database*/
CREATE TABLE T_USER_TYPE(
   ENTITY_ID LONG PRIMARY KEY,
   NAME VARCHAR(255) NOT NULL,
   OBSOLETE BOOLEAN  NOT NULL,
   CREATED_DATE TIMESTAMP NOT NULL,
   COMMENT VARCHAR(255)
);
/*
 * 1. Technical Admin
 * 2. Application
 * 3. Social Media
 * 4. Normal User
 * 
 */

CREATE SEQUENCE USER_SEQ INCREMENT BY 1 CACHE 1;
/*Date should be in GMT always in database*/
CREATE TABLE T_USER(
   ENTITY_ID LONG PRIMARY KEY,
   TITLE VARCHAR(10),
   FIRST_NAME VARCHAR(255) NOT NULL,
   MIDDLE_NAME VARCHAR(255),
   LAST_NAME VARCHAR(255),
   IS_MOBILE_VERIFIED BOOLEAN,
   IS_EMAIL_VERIFIED BOOLEAN,
   OBSOLETE BOOLEAN  NOT NULL,
   USER_TYPE_ID LONG,
   PERMANENT_ADDRESS_ID LONG,
   CURRENT_ADDRESS_ID LONG,
   PRIMARY_EMAIL_ID VARCHAR(254),
   SECONDARY_EMAIL_ID VARCHAR(254),
   PRIMARY_MOBILE_NUMBER LONG,
   SECONDARY_MOBILE_NUMBER LONG,
   CREATED_DATE TIMESTAMP NOT NULL,
   MODIFIED_DATE TIMESTAMP NOT NULL,
   CREATED_BY LONG,
   MODIFIED_BY LONG,
   PASSWORD VARCHAR(255),
   COMMENT VARCHAR(255)
);

CREATE SEQUENCE ADDRESS_SEQ INCREMENT BY 1 CACHE 1;
/*Date should be in GMT always in database*/
CREATE TABLE T_ADDRESS(
   ENTITY_ID LONG PRIMARY KEY,
   ADDRESS VARCHAR(255) NOT NULL,
   PIN_CODE LONG,
   COUNTRY_NAME VARCHAR(55),
   COUNTRY_CODE VARCHAR(10),
   CITY_NAME VARCHAR(255),
   STATE_NAME VARCHAR(255),
   OBSOLETE BOOLEAN  NOT NULL,
   CREATED_DATE TIMESTAMP NOT NULL,
   MODIFIED_DATE TIMESTAMP NOT NULL,
   CREATED_BY LONG,
   MODIFIED_BY LONG,
   COMMENT VARCHAR(255)
);





