<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${livro != null ? 'Editar Livro' : 'Novo Livro'}</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">  
  <link href="https://fonts.googleapis.com/css2?family=Parisienne&family=Roboto+Serif:ital,opsz,wght@0,8..144,100..900;1,8..144,100..900&display=swap" rel="stylesheet">
</head>
<body>
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
                <!-- Card com o Formulário de Cadastro e Edição -->
                <div class="card">
                    <div class="card-header">
                        <h3>${livro != null ? 'Editar Livro' : 'Cadastro de Livro'}</h3>
                    </div>
                    <div class="card-body">
                        <form id="formCadastro" action="livro?action=${livro != null ? 'update' : 'insert'}" method="post" novalidate>
                            <input type="hidden" name="id" value="${livro != null ? livro.id : ''}" />
                            
                            <!-- Título -->
                            <div class="form-group">
                                <label for="titulo"><strong>Título</strong></label>
                                <input type="text" class="form-control" id="titulo" name="titulo" value="${livro != null ? livro.titulo : ''}" required>
                                <div class="invalid-feedback">
                                    O título é obrigatório.
                                </div>
                            </div>

                            <!-- Autor -->
                            <div class="form-group">
                                <label for="autor"><strong>Autor</strong></label>
                                <input type="text" class="form-control" id="autor" name="autor" value="${livro != null ? livro.autor : ''}" required>
                                <div class="invalid-feedback">
                                    O autor é obrigatório.
                                </div>
                            </div>

                            <!-- Gênero -->
                            <div class="form-group">
                                <label for="genero"><strong>Gênero</strong></label>
                                <input type="text" class="form-control" id="genero" name="genero" value="${livro != null ? livro.genero : ''}" required>
                                <div class="invalid-feedback">
                                    O gênero é obrigatório.
                                </div>
                            </div>

                            <!-- Data de Publicação -->
                            <div class="form-group">
                                <label for="dataPublicacao"><strong>Data de Publicação</strong></label>
                                <input type="date" class="form-control" id="dataPublicacao" name="dataPublicacao" value="${livro != null ? livro.dataPublicacao : ''}" required>
                                <div class="invalid-feedback">
                                    A data de publicação é obrigatória.
                                </div>
                            </div>

                            <!-- Editora -->
                            <div class="form-group">
                                <label for="editora"><strong>Editora</strong></label>
                                <input type="text" class="form-control" id="editora" name="editora" value="${livro != null ? livro.editora : ''}" required>
                                <div class="invalid-feedback">
                                    A editora é obrigatória.
                                </div>
                            </div>

                            <!-- Sinopse -->
                            <div class="form-group">
                                <label for="sinopse"><strong>Sinopse</strong></label>
                                <textarea class="form-control" id="sinopse" name="sinopse" required>${livro != null ? livro.sinopse : ''}</textarea>
                                <div class="invalid-feedback">
                                    A sinopse é obrigatória.
                                </div>
                            </div>

                            <button type="submit" class="btn btn-primary">${livro != null ? 'Salvar Alterações' : 'Cadastrar'}</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Scripts do Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script src="js/script.js"></script>
</body>
</html>
