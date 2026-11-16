// Pergunta antes de enviar formulários de exclusão.
// Uso no HTML: <form ... data-confirmar="Excluir este livro?">
document.querySelectorAll('form[data-confirmar]').forEach(function (form) {
  form.addEventListener('submit', function (evento) {
    if (!confirm(form.dataset.confirmar)) {
      evento.preventDefault(); // cancelou: não envia
    }
  });
});
