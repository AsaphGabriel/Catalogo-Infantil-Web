/**
 * 
 */
document.addEventListener('DOMContentLoaded', function () {
    // Obtém a data de hoje no formato yyyy-mm-dd
    const hoje = new Date().toISOString().split('T')[0];
    
    // Define o atributo max do campo de data para a data de hoje
    document.getElementById('dataPublicacao').setAttribute('max', hoje);
});
//VALIDAÇÃO DO FORMULARIO
        document.getElementById('formCadastro').addEventListener('submit', function(event) {
            var form = event.target;
            var isValid = form.checkValidity();

            // Se o formulário não for válido, impede o envio e exibe a validação
            if (!isValid) {
                event.preventDefault();
                event.stopPropagation();
            }

            // Adiciona a classe de validação ao formulário
            form.classList.add('was-validated');
        });
		
//pesquisa forms
