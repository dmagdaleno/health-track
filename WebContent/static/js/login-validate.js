$().ready(function(){
  $("#loginForm").validate({
    rules: {
      email: {
        required: true,
        email: true
      },
      password: {
        required: true,
        minlength: 6
      }
    },
    messages: {
      email: {
        required: "O email é obrigatório para o login",
        email: "Forneça um email válido"
      },
      password: {
        required: "A senha é obrigatória para o login",
        minlength: "A senha precisa ter pelo menos {0} dígitos"
      }
    }
  });
});
