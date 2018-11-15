$().ready(function(){
  $("#pesoForm").validate({
    rules: {
      peso: {
        required: true,
        number: true,
        min: 1,
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
      peso: {
        required: "O peso é obrigatório",
        number: "Insira um valor numérico",
        min: "O peso deve ser maior que 0",
        max: "Uma pessoa não pode pesar mais de {0} kilogramas"
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
