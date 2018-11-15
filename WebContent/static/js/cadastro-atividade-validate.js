$().ready(function(){
  $("#atividadeForm").validate({
    rules: {
      tipo: {
        required: true
      },
      caloria: {
        required: true,
        number: true,
        min: 1,
        max: 10000
      },
      data: {
        required: true,
        date: true,
        min: "1970-01-01",
        max: amanha()
      }
    },
    messages: {
      tipo: {
        required: "O tipo de atividade é obrigatória"
      },
      caloria: {
        required: "A quantidade de calorias é obrigatória",
        number: "Insira um valor numérico",
        min: "A atividade deve ter consumido pelo menos {0} caloria",
        max: "A atividade não pode ter consumido mais que {0} calorias"
      },
      data: {
        required: "A data é obrigatória",
        date: "Insira uma data válida",
        min: "A data mínima é 01/01/1970",
        max: "Não é possível inserir datas futuras"
      }
    }
  });
});
