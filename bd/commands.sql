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


INSERT INTO T_HTK_PESO (id_peso, fk_id_usuario, vl_peso, dt_medida) 
 VALUES (1, 1, 80, TO_DATE('17/09/2018','DD/MM/YYYY'));

UPDATE T_HTK_PESO 
 SET
  vl_peso = 81, 
  dt_medida = TO_DATE('18/09/2018','DD/MM/YYYY')
 WHERE id_peso = 1;


INSERT INTO T_HTK_PRESSAO (id_pressao, fk_id_usuario, vl_pressao_max, vl_pressao_min, dt_medida) 
 VALUES (1, 1, 140, 90, TO_DATE('17/09/2018','DD/MM/YYYY'));

UPDATE T_HTK_PRESSAO 
 SET
  vl_pressao_max = 130,
  vl_pressao_min = 80, 
  dt_medida = TO_DATE('18/09/2018','DD/MM/YYYY')
 WHERE id_pressao = 1;


INSERT INTO T_HTK_PRESSAO (id_pressao, fk_id_usuario, vl_pressao_max, vl_pressao_min, dt_medida) 
 VALUES (1, 1, 140, 90, TO_DATE('17/09/2018','DD/MM/YYYY'));

UPDATE T_HTK_PRESSAO 
 SET
  vl_pressao_max = 130,
  vl_pressao_min = 80, 
  dt_medida = TO_DATE('18/09/2018','DD/MM/YYYY')
 WHERE id_pressao = 1;


INSERT INTO T_HTK_ATV_FISICA (id_atv_fisica, fk_id_usuario, fk_id_tp_atv_fisica, vl_caloria, dt_atv_fisica, ds_atv_fisica)
 VALUES (1, 1, 1, 500, TO_DATE('17/09/2018','DD/MM/YYYY'), 'corrida pela manhã');

UPDATE T_HTK_ATV_FISICA 
 SET 
  fk_id_tp_atv_fisica = 2, 
  vl_caloria = 200, 
  dt_atv_fisica = TO_DATE('17/09/2018','DD/MM/YYYY'), 
  ds_atv_fisica = 'treino de membros inferiores')
 WHERE id_atv_fisica = 1;