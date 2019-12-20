--------------------------------------------------------
--  Fichier créé - vendredi-décembre-20-2019   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table BDE
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."BDE" 
   (	"ID_BDE" NUMBER, 
	"IDCREATOR" VARCHAR2(20 BYTE), 
	"NAMEBDE" VARCHAR2(50 BYTE), 
	"SCHOOLBDE" VARCHAR2(200 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into SYSTEM.BDE
SET DEFINE OFF;

--------------------------------------------------------
--  Constraints for Table BDE
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."BDE" MODIFY ("IDCREATOR" NOT NULL ENABLE);



--------------------------------------------------------
--  Fichier créé - vendredi-décembre-20-2019   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table USERS
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."USERS" 
   (	"ID_USER" NUMBER, 
	"USERNAME" VARCHAR2(20 BYTE), 
	"EMAILUSER" VARCHAR2(50 BYTE), 
	"PASSWORDUSER" VARCHAR2(50 BYTE), 
	"FIRSTNAME" VARCHAR2(20 BYTE), 
	"LASTNAME" VARCHAR2(20 BYTE), 
	"PHONENUMBERUSER" VARCHAR2(20 BYTE), 
	"ID_BDE" NUMBER
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into SYSTEM.USERS
SET DEFINE OFF;
