$().ready(function(){
  $("#perfilForm").validate({
    rules: {
      nome: {
        required: true,
        minlength: 2
      },
      data: {
        required: true,
        date: true,
        min: "1900-01-01",
        max: hoje()
      },
      altura: {
        required: true,
        number: true,
        min: 0.1,
        max: 3
      },
      genero: {
        required: true
      },
      email: {
        required: true,
        email: true
      },
      senha: {
        required: true,
        minlength: 6
      },
      confirmaSenha: {
        required: true,
        minlength: 6,
        equalTo: '#senha'
      }
    },
    messages: {
      nome: {
        required: "O nome é obrigatório",
        minlength: "Insira um nome válido"
      },
      data: {
        required: "A data é obrigatória",
        date: "Insira uma data válida",
        min: "A data mínima é 01/01/1900",
        max: "Não é possível inserir datas futuras"
      },
      altura: {
        required: "A altura é obrigatória",
        number: "Insira um valor numérico",
        min: "A altura deve ser pelo menos {0} metros",
        max: "A altura não pode ultrapassar {0} metros"
      },
      genero: {
        required: "O gênero é obrigatório para o cálculo do IMC"
      },
      email: {
        required: "O email é obrigatório para o cadastro",
        email: "Forneça um email válido"
      },
      senha: {
        required: "A senha é obrigatória para o cadastro",
        minlength: "A senha precisa ter pelo menos {0} dígitos"
      },
      confirmaSenha: {
        required: "A confirmação de senha é obrigatória para o cadastro",
        minlength: "A senha precisa ter pelo menos {0} dígitos",
        equalTo: 'As senhas estão diferentes'
      }
    }
  });
});
