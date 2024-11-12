package Controle;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Livro;

@WebServlet("/livro")
public class LivroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DAO.LivroDAO livroDAO;

    public void init() {
        livroDAO = new DAO.LivroDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertLivro(request, response);
                    break;
                case "delete":
                    deleteLivro(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateLivro(request, response);
                    break;
                default:
                    listLivro(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listLivro(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Livro> listLivro = livroDAO.selectAllLivros();
        request.setAttribute("listLivro", listLivro);
        request.getRequestDispatcher("ListarLivros.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("FormularioCadastro.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Livro livroExistente = livroDAO.selectLivro(id);
        request.setAttribute("livro", livroExistente);
        request.getRequestDispatcher("FormularioCadastro.jsp").forward(request, response);
    }

    private void insertLivro(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String genero = request.getParameter("genero");

        // Usando o nome correto: dataPublicacao
        String dataPublicacaoStr = request.getParameter("dataPublicacao");
        LocalDate dataPublicacao = LocalDate.parse(dataPublicacaoStr); // Conversão para LocalDate

        String editora = request.getParameter("editora");
        String sinopse = request.getParameter("sinopse");

        Livro novoLivro = new Livro(titulo, autor, genero, dataPublicacao, editora, sinopse);
        livroDAO.insertLivro(novoLivro);
        response.sendRedirect("livro?action=list");
    }

    private void updateLivro(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String genero = request.getParameter("genero");

        // Usando o nome correto: dataPublicacao
        String dataPublicacaoStr = request.getParameter("dataPublicacao");
        LocalDate dataPublicacao = LocalDate.parse(dataPublicacaoStr); // Conversão para LocalDate

        String editora = request.getParameter("editora");
        String sinopse = request.getParameter("sinopse");

        Livro livroAtualizado = new Livro(id, titulo, autor, genero, dataPublicacao, editora, sinopse);
        livroDAO.updateLivro(livroAtualizado);
        response.sendRedirect("livro?action=list");
    }

    private void deleteLivro(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        livroDAO.deleteLivro(id);
        response.sendRedirect("livro?action=list");
    }
    
    // Pesquisa
    
}

