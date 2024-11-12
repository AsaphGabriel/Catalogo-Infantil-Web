package DAO;

import model.Livro;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/biblioteca_infantil";
    private String jdbcUsername = "root";  // Ajuste o usuário
    private String jdbcPassword = "";      // Ajuste a senha

    private static final String INSERT_LIVRO_SQL = "INSERT INTO livro (titulo, autor, genero, ano_publicacao, editora, sinopse) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_LIVRO_BY_ID = "SELECT id, titulo, autor, genero, ano_publicacao, editora, sinopse FROM livro WHERE id = ?";
    private static final String SELECT_ALL_LIVROS = "SELECT * FROM livro";
    private static final String DELETE_LIVRO_SQL = "DELETE FROM livro WHERE id = ?";
    private static final String UPDATE_LIVRO_SQL = "UPDATE livro SET titulo = ?, autor = ?, genero = ?, ano_publicacao = ?, editora = ?, sinopse = ? WHERE id = ?";

    // Método para obter a conexão com o banco de dados
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Método para inserir um livro
    public void insertLivro(Livro livro) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LIVRO_SQL)) {
            preparedStatement.setString(1, livro.getTitulo());
            preparedStatement.setString(2, livro.getAutor());
            preparedStatement.setString(3, livro.getGenero());
            
            // Alteração: Usar LocalDate para a data de publicação
            preparedStatement.setDate(4, java.sql.Date.valueOf(livro.getDataPublicacao())); // Ajustado para LocalDate
            
            preparedStatement.setString(5, livro.getEditora());
            preparedStatement.setString(6, livro.getSinopse());
            preparedStatement.executeUpdate();
        }
    }

    // Método para selecionar um livro pelo ID
    public Livro selectLivro(int id) {
        Livro livro = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIVRO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String genero = rs.getString("genero");
                
                // Alteração: Converter o tipo de dados para LocalDate
                Date dataPublicacao = rs.getDate("ano_publicacao");
                LocalDate localDate = dataPublicacao.toLocalDate(); // Converte para LocalDate

                String editora = rs.getString("editora");
                String sinopse = rs.getString("sinopse");
                livro = new Livro(id, titulo, autor, genero, localDate, editora, sinopse); // Passa LocalDate no construtor
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livro;
    }

    // Método para selecionar todos os livros
    public List<Livro> selectAllLivros() {
        List<Livro> livros = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LIVROS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String genero = rs.getString("genero");
                
                // Alteração: Converter o tipo de dados para LocalDate
                Date dataPublicacao = rs.getDate("ano_publicacao");
                LocalDate localDate = dataPublicacao.toLocalDate(); // Converte para LocalDate

                String editora = rs.getString("editora");
                String sinopse = rs.getString("sinopse");
                Livro livro = new Livro(id, titulo, autor, genero, localDate, editora, sinopse); // Passa LocalDate no construtor
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    // Método para atualizar um livro
    public boolean updateLivro(Livro livro) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_LIVRO_SQL)) {
            statement.setString(1, livro.getTitulo());
            statement.setString(2, livro.getAutor());
            statement.setString(3, livro.getGenero());
            
            // Alteração: Usar LocalDate para a data de publicação
            statement.setDate(4, java.sql.Date.valueOf(livro.getDataPublicacao())); // Ajustado para LocalDate
            
            statement.setString(5, livro.getEditora());
            statement.setString(6, livro.getSinopse());
            statement.setInt(7, livro.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    // Método para deletar um livro
    public boolean deleteLivro(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_LIVRO_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}
