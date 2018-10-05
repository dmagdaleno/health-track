CREATE TABLE T_HTK_ALIMENTO
  (
    id_alimento       NUMBER NOT NULL ,
    fk_id_usuario     NUMBER NOT NULL ,
    fk_id_tp_alimento NUMBER NOT NULL ,
    vl_caloria        REAL NOT NULL ,
    dt_consumo        DATE NOT NULL ,
    ds_alimento       VARCHAR2 (256)
  ) ;
ALTER TABLE T_HTK_ALIMENTO ADD CONSTRAINT T_HTK_ALIMENTO_PK PRIMARY KEY ( id_alimento ) ;


CREATE TABLE T_HTK_ATV_FISICA
  (
    id_atv_fisica       NUMBER NOT NULL ,
    fk_id_usuario       NUMBER NOT NULL ,
    fk_id_tp_atv_fisica NUMBER NOT NULL ,
    vl_caloria          REAL NOT NULL ,
    dt_atv_fisica       DATE NOT NULL ,
    ds_atv_fisica       VARCHAR2 (256)
  ) ;
ALTER TABLE T_HTK_ATV_FISICA ADD CONSTRAINT T_HTK_ATV_FISICA_PK PRIMARY KEY ( id_atv_fisica ) ;


CREATE TABLE T_HTK_PESO
  (
    id_peso       NUMBER NOT NULL ,
    fk_id_usuario NUMBER NOT NULL ,
    vl_peso       REAL NOT NULL ,
    dt_medida     DATE NOT NULL
  ) ;
ALTER TABLE T_HTK_PESO ADD CONSTRAINT T_HTK_PESO_PK PRIMARY KEY ( id_peso ) ;


CREATE TABLE T_HTK_PRESSAO
  (
    id_pressao     NUMBER NOT NULL ,
    fk_id_usuario  NUMBER NOT NULL ,
    vl_pressao_max NUMBER NOT NULL ,
    vl_pressao_min NUMBER NOT NULL ,
    dt_medida      DATE NOT NULL
  ) ;
ALTER TABLE T_HTK_PRESSAO ADD CONSTRAINT T_HTK_PRESSAO_PK PRIMARY KEY ( id_pressao ) ;


CREATE TABLE T_HTK_TP_ALIMENTO
  (
    id_tp_alimento NUMBER NOT NULL ,
    ds_tp_alimento VARCHAR2 (128) NOT NULL
  ) ;
ALTER TABLE T_HTK_TP_ALIMENTO ADD CONSTRAINT T_HTK_TP_ALIMENTO_PK PRIMARY KEY ( id_tp_alimento ) ;


CREATE TABLE T_HTK_TP_ATV_FISICA
  (
    id_tp_atv_fisica NUMBER NOT NULL ,
    ds_tp_atv_fisica VARCHAR2 (128) NOT NULL
  ) ;
ALTER TABLE T_HTK_TP_ATV_FISICA ADD CONSTRAINT T_HTK_TP_ATV_FISICA_PK PRIMARY KEY ( id_tp_atv_fisica ) ;


CREATE TABLE T_HTK_USUARIO
  (
    id_usuario        NUMBER NOT NULL ,
    ds_email          VARCHAR2 (256) NOT NULL ,
    cd_senha          VARCHAR2 (256) NOT NULL ,
    nm_usuario        VARCHAR2 (128) NOT NULL ,
    dt_nascimento     DATE NOT NULL ,
    vl_altura         REAL NOT NULL ,
    cd_genero         VARCHAR2 (1) NOT NULL ,
    vl_limite_caloria REAL ,
    dt_ult_login      DATE
  ) ;
ALTER TABLE T_HTK_USUARIO ADD CONSTRAINT T_HTK_USUARIO_PK PRIMARY KEY ( id_usuario ) ;
ALTER TABLE T_HTK_USUARIO ADD CONSTRAINT T_HTK_USUARIO_UN UNIQUE ( ds_email ) ;

ALTER TABLE T_HTK_ALIMENTO ADD CONSTRAINT T_HTK_ALIMENTO_TP_ALIMENTO_FK FOREIGN KEY ( fk_id_tp_alimento ) REFERENCES T_HTK_TP_ALIMENTO ( id_tp_alimento ) ;
ALTER TABLE T_HTK_ALIMENTO ADD CONSTRAINT T_HTK_ALIMENTO_USUARIO_FK FOREIGN KEY ( fk_id_usuario ) REFERENCES T_HTK_USUARIO ( id_usuario ) ;
ALTER TABLE T_HTK_ATV_FISICA ADD CONSTRAINT T_HTK_ATV_TP_ATV_FK FOREIGN KEY ( fk_id_tp_atv_fisica ) REFERENCES T_HTK_TP_ATV_FISICA ( id_tp_atv_fisica ) ;
ALTER TABLE T_HTK_ATV_FISICA ADD CONSTRAINT T_HTK_ATV_USUARIO_FK FOREIGN KEY ( fk_id_usuario ) REFERENCES T_HTK_USUARIO ( id_usuario ) ;
ALTER TABLE T_HTK_PESO ADD CONSTRAINT T_HTK_PESO_USUARIO_FK FOREIGN KEY ( fk_id_usuario ) REFERENCES T_HTK_USUARIO ( id_usuario ) ;
ALTER TABLE T_HTK_PRESSAO ADD CONSTRAINT T_HTK_PRESSAO_USUARIO_FK FOREIGN KEY ( fk_id_usuario ) REFERENCES T_HTK_USUARIO ( id_usuario ) ;
