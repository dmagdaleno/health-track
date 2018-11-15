$().ready(function(){
  $("#pressaoForm").validate({
    rules: {
      pressaoMax: {
        required: true,
        number: true,
        min: 10,
        max: 500
      },
      pressaoMin: {
        required: true,
        number: true,
        min: 10,
        max: 500
      },
      data: {
        required: true,
        date: true,
        min: "1970-01-01",
        max: hoje()
      }
    },
    messages: {
      pressaoMax: {
        required: "O pressão arterial sistólica é obrigatória",
        number: "Insira um valor numérico",
        min: "A pressão deve ser maior que {0}",
        max: "A pressão não deve ultrapassar de {0}"
      },
      pressaoMin: {
        required: "O pressão arterial diastólica é obrigatória",
        number: "Insira um valor numérico",
        min: "A pressão deve ser maior que {0}",
        max: "A pressão não deve ultrapassar de {0}"
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
