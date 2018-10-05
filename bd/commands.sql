INSERT INTO T_HTK_USUARIO (id_usuario, ds_email, cd_senha, nm_usuario, 
   dt_nascimento, vl_altura, cd_genero, vl_limite_caloria, dt_ult_login) 
 VALUES (1, 'teste@teste.com', 'senha123', 'Nome de teste', 
   TO_DATE('01/01/1990','DD/MM/YYYY'), 1.7, 'M', 2000, NULL);

UPDATE T_HTK_USUARIO 
 SET
  ds_email = 'joao@gmail.com', 
  cd_senha = 'grandesertaoveredas', 
  nm_usuario = 'João Guimarães Rosa', 
  dt_nascimento = TO_DATE('27/06/1998','DD/MM/YYYY'), 
  vl_altura = 1.7, 
  cd_genero = 'M', 
  vl_limite_caloria = 2000, 
  dt_ult_login = TO_DATE('18/09/2018','DD/MM/YYYY')
 WHERE id_usuario = 1;
 
SELECT U.* FROM T_HTK_USUARIO U;


INSERT INTO T_HTK_PESO (id_peso, fk_id_usuario, vl_peso, dt_medida) 
 VALUES (1, 1, 80, TO_DATE('17/09/2018','DD/MM/YYYY'));

UPDATE T_HTK_PESO 
 SET
  fk_id_usuario = 1,
  vl_peso = 81, 
  dt_medida = TO_DATE('18/09/2018','DD/MM/YYYY')
 WHERE id_peso = 1;
 
SELECT P.* FROM T_HTK_PESO P;


INSERT INTO T_HTK_PRESSAO (id_pressao, fk_id_usuario, vl_pressao_max, vl_pressao_min, dt_medida) 
 VALUES (1, 1, 140, 90, TO_DATE('17/09/2018','DD/MM/YYYY'));

UPDATE T_HTK_PRESSAO 
 SET 
  fk_id_usuario = 1,
  vl_pressao_max = 130,
  vl_pressao_min = 80, 
  dt_medida = TO_DATE('18/09/2018','DD/MM/YYYY')
 WHERE id_pressao = 1;

SELECT PR.* FROM T_HTK_PRESSAO PR;


INSERT INTO T_HTK_TP_ATV_FISICA (id_tp_atv_fisica, ds_tp_atv_fisica)
 VALUES (1, 'CAFE_DA_MANHA');
INSERT INTO T_HTK_TP_ATV_FISICA (id_tp_atv_fisica, ds_tp_atv_fisica)
 VALUES (2, 'ALMOCO');
INSERT INTO T_HTK_TP_ATV_FISICA (id_tp_atv_fisica, ds_tp_atv_fisica)
 VALUES (3, 'JANTAR');
INSERT INTO T_HTK_TP_ATV_FISICA (id_tp_atv_fisica, ds_tp_atv_fisica)
 VALUES (4, 'LANCHE_LEVE');
INSERT INTO T_HTK_TP_ATV_FISICA (id_tp_atv_fisica, ds_tp_atv_fisica)
 VALUES (5, 'FRUTA');

INSERT INTO T_HTK_ATV_FISICA (id_atv_fisica, fk_id_usuario, fk_id_tp_atv_fisica, vl_caloria, dt_atv_fisica, ds_atv_fisica)
 VALUES (1, 1, 1, 500, TO_DATE('17/09/2018','DD/MM/YYYY'), 'corrida pela manhã');

UPDATE T_HTK_ATV_FISICA 
 SET 
  fk_id_usuario = 1,
  fk_id_tp_atv_fisica = 2, 
  vl_caloria = 200, 
  dt_atv_fisica = TO_DATE('17/09/2018','DD/MM/YYYY'), 
  ds_atv_fisica = 'treino de membros inferiores'
 WHERE id_atv_fisica = 1;

SELECT AF.* FROM T_HTK_ATV_FISICA AF;


INSERT INTO T_HTK_ALIMENTO (id_alimento, fk_id_usuario, fk_id_tp_alimento, vl_caloria, dt_consumo, ds_alimento)
 VALUES (1, 1, 1, 800, TO_DATE('17/09/2018','DD/MM/YYYY'), 'feijoada');

UPDATE T_HTK_ALIMENTO
 SET 
  fk_id_usuario = 1,
  fk_id_tp_alimento = 1,
  vl_caloria = 600,
  dt_consumo = TO_DATE('17/09/2018','DD/MM/YYYY'),
  ds_alimento = 'almoço leve'
 WHERE id_alimento = 1;


-- Consultar os dados de um determinado usuário
SELECT * FROM T_HTK_USUARIO WHERE id_usuario = 1;

-- Consultar todos os dados de todos os registros de peso de um determinado usuário, ordenando-os dos registros mais recentes para os mais antigos
SELECT * FROM T_HTK_PESO WHERE fk_id_usuario = 1 ORDER BY dt_medida DESC;

-- Consultar todos os dados de um único registro de peso de um determinado usuário
SELECT * FROM T_HTK_PESO WHERE id_peso = 1 AND fk_id_usuario = 1;

-- Consultar todos os dados de todos os registros de pressão arterial de um determinado usuário, ordenando-os dos registros mais recentes para os mais antigos
SELECT * FROM T_HTK_PRESSAO WHERE fk_id_usuario = 1 ORDER BY dt_medida DESC;

-- Consultar todos os dados de um único registro de pressão arterial de um determinado usuário
SELECT * FROM T_HTK_PRESSAO WHERE id_pressao = 1 AND fk_id_usuario = 1;

-- Consultar todos os dados de todos os registros de atividade física de um determinado usuário, ordenando-os dos registros mais recentes para os mais antigos 
SELECT * FROM T_HTK_ATV_FISICA WHERE fk_id_usuario = 1 ORDER BY dt_atv_fisica DESC;

-- Consultar todos os dados de um único registro de atividade física de um determinado usuário
SELECT * FROM T_HTK_ATV_FISICA WHERE id_atv_fisica = 1 AND fk_id_usuario = 1;

-- Consultar todos os dados de todos os registros de alimentos ingeridos de um determinado usuário, ordenando-os dos registros mais recentes para os mais antigos
SELECT * FROM T_HTK_ALIMENTO WHERE fk_id_usuario = 1 ORDER BY dt_consumo DESC;

-- Consultar todos os dados de um único registro de alimento ingerido de um determinado usuário 
SELECT * FROM T_HTK_ALIMENTO WHERE id_alimento = 1 AND fk_id_usuario = 1;

-- Consultar os dados básicos de um determinado usuário, o último peso registrado e a última pressão arterial registrada
SELECT U.*, P.vl_peso, PA.vl_pressao_max, PA.vl_pressao_min FROM T_HTK_USUARIO U 
INNER JOIN T_HTK_PESO P, T_HTK_PRESSAO PA 
ON U.id_usuario = P.fk_id_usuario AND P.dt_medida = (SELECT MAX(dt_medida) FROM T_HTK_PESO) 
AND U.id_usuario = PA.fk_id_usuario AND PA.dt_medida = (SELECT MAX(dt_medida) FROM T_HTK_PRESSAO) 


-- Consultar os dados básicos de um determinado usuário, o último peso registrado e a última pressão arterial registrada
SELECT
 U.*, P.vl_peso, PA.vl_pressao_max, PA.vl_pressao_min 
FROM 
 T_HTK_USUARIO U, T_HTK_PESO P, T_HTK_PRESSAO PA 
WHERE
 U.id_usuario = P.fk_id_usuario AND P.dt_medida = (SELECT MAX(dt_medida) FROM T_HTK_PESO) 
 AND U.id_usuario = PA.fk_id_usuario AND PA.dt_medida = (SELECT MAX(dt_medida) FROM T_HTK_PRESSAO);
