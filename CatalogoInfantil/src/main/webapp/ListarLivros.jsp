<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catálogo de Livros Infantis</title>
    <!-- Link para o Bootstrap -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=JetBrains+Mono:ital,wght@0,100..800;1,100..800&family=Parisienne&family=Roboto+Serif:ital,opsz,wght@0,8..144,100..900;1,8..144,100..900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">  
</head>

<!-- NavBar com formulário de pesquisa -->
    <nav class="navbar bg-body-tertiary">
 	 <div class="container">
    	<a class="navbar-brand" href="FormularioCadastro.jsp">
     	 <img src="imagens/logoCadastro.png" width="70" height="55">
    	</a>
  	</div>
	</nav>
    <div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <!-- Card com o título e a tabela -->
            <div class="card">
                <div class="card-header">
                    <h3 style="font-family: 'JetBrains Mono', monospace;">Histórico</h3>
                </div>
                <div class="card-body">
                    <a href="livro?action=new" class="btn btn-success mb-3">Novo Livro</a>

                    <!-- Barra de pesquisa -->
                    <input type="text" id="searchInput" class="form-control mb-3" placeholder="Pesquisar livros..." onkeyup="searchBooks()">

                    <!-- Tabela dentro de uma div com rolagem horizontal e vertical -->
                    <div class="table-responsive">
                        <table class="table table-bordered" id="listarLivross">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Título</th>
                                    <th>Autor</th>
                                    <th>Gênero</th>
                                    <th>Ano de Publicação</th>
                                    <th>Editora</th>
                                    <th>Sinopse</th>
                                    <th>Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="livro" items="${listLivro}">
                                    <tr>
                                        <td>${livro.id}</td>
                                        <td>${livro.titulo}</td>
                                        <td>${livro.autor}</td>
                                        <td>${livro.genero}</td>
                                        <td>${livro.dataPublicacao}</td>
                                        <td>${livro.editora}</td>
                                        <td>${livro.sinopse}</td>
                                        <td>
                                            <a href="livro?action=edit&id=${livro.id}" class="btn btn-warning btn-sm">Editar</a> 
                                            <button class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmDeleteModal" data-id="${livro.id}">Deletar</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


    <!-- Modal de confirmação de exclusão -->
    <div class="modal fade" id="confirmDeleteModal" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="confirmDeleteModalLabel">Confirmar Exclusão</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Você tem certeza que deseja excluir este livro?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                    <a id="confirmDeleteButton" href="#" class="btn btn-danger">Excluir</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Scripts do Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
    <script>
        // Função de pesquisa
        function searchBooks() {
            var input, filter, table, tr, td, i, txtValue;
            input = document.getElementById("searchInput");
            filter = input.value.toUpperCase();
            table = document.getElementById("listarLivross");
            tr = table.getElementsByTagName("tr");

            // Loop para percorrer todas as linhas da tabela e ocultar as que não correspondem à pesquisa
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td");
                if (td.length > 0) {
                    // Verifica se algum dos dados da linha contém o texto pesquisado
                    var match = false;
                    for (var j = 0; j < td.length; j++) {
                        txtValue = td[j].textContent || td[j].innerText;
                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                            match = true;
                            break;
                        }
                    }
                    // Exibe ou oculta a linha
                    if (match) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }

        // Atualiza o link do botão de exclusão com o ID correto
        $('#confirmDeleteModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget); // Botão que acionou o modal
            var livroId = button.data('id'); // Captura o ID do livro
            
            var modal = $(this);
            modal.find('#confirmDeleteButton').attr('href', 'livro?action=delete&id=' + livroId);
        });
    </script>
</body>
</html>
