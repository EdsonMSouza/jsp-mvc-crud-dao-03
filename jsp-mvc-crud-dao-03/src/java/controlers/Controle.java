/**
 * Arquivo: Controle.java
 *
 */
package controlers;

import beans.Aluno;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.AlunoDAO;
import utils.GeraRA;

/**
 *
 * @author Edson Melo de Souza, Me. <prof.edson.melo@gmail.com>
 */
public class Controle extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    @SuppressWarnings("null")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        /**
         * Configuração do código de página para mostrar os caracteres
         * corretamente
         */
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Variável que receberá o valor da flag enviado pelo formulário
        String flag = request.getParameter("flag");

        try (PrintWriter out = response.getWriter()) {

            /**
             * Direciona para a página que está determinada no parâmetro do
             * método
             */
            if (flag == null) {
                request.getRequestDispatcher("index.html").
                        forward(request, response);
            }

            /**
             * Declaração das variáveis para os objetos e para receber os
             * valores enviados pelos formulários
             */
            // Objetos
            Aluno aluno = new Aluno();
            AlunoDAO alunoDAO = new AlunoDAO();
            ArrayList<Aluno> listaAluno = new ArrayList();
            List<Aluno> alunos;
            List<Aluno> pesquisa;
            List<Aluno> alunoAlteraDAO;
            Map<String, String> campos;

            // Variáveis dos formulários
            String nome;
            String curso;
            String disciplina;
            String email;

            // Variáveis para tratamento das mensagens de erro
            String tituloErro = "";
            String erro = "";

            // Verifica qual ação deve ser tomada
            switch (flag) {
                case "cadastrar":
                    //Recupera os valores enviados pelo formulário
                    nome = request.getParameter("nomeAluno");
                    curso = request.getParameter("cursoAluno");
                    disciplina = request.getParameter("disciplinaAluno");
                    email = request.getParameter("emailAluno");

                    // Cria o objeto e e atribui os dados recebidos
                    aluno = new Aluno();
                    aluno.setNome(nome);
                    aluno.setCurso(curso);
                    aluno.setDisciplina(disciplina);
                    aluno.setEmail(email);

                    // Cria um objeto para receber os campos
                    campos = new HashMap<>();

                    // Verifica o preenchimento dos campos
                    campos = aluno.verificaDados();

                    // Percorre a lista (objetos - campos) em busca dos erros
                    for (String key : campos.keySet()) {
                        if (campos.get(key).equals("")) {
                            // monta a mensagem de erro
                            tituloErro = "<h1>Campo (s) não preenchido (s)!</h1>";
                            erro = erro + "&rarr; " + String.valueOf(key) + "<br>";
                        }
                    }

                    // Se ocorreram erros, envia para página de erro
                    if (!erro.isEmpty()) {
                        request.setAttribute("mensagem", tituloErro + erro);
                        request.getRequestDispatcher("views/erro.jsp").
                                forward(request, response);
                        break;
                    }

                    // Se passou sem erros, gera o RA
                    aluno.setRa(new GeraRA().getRa());

                    /**
                     * Repassa os valores dos atributos para o objeto DAO que
                     * irá manipular os dados e gravar no banco
                     */
                    alunoDAO = new AlunoDAO();
                    alunoDAO.inserir(aluno);

                    /**
                     * Cria uma lista e coloca o objeto aluno para ser repassado
                     * para a views/mensagem.jsp
                     */
                    listaAluno = new ArrayList<>();
                    listaAluno.add(aluno);

                    // Cria um atributo com o aluno para ser utilizado na View
                    request.setAttribute("listaAluno", listaAluno);

                    // Redireciona para a View
                    request.getRequestDispatcher("views/mensagem.jsp").
                            forward(request, response);
                    break;

                case "listar":
                    // Busca no model os dados
                    alunoDAO = new AlunoDAO();

                    // Coloca todos os alunos em uma lista
                    alunos = alunoDAO.listar();

                    // Cria um atributo com o aluno para ser utilizado na View
                    request.setAttribute("listaAlunos", alunos);

                    // Redireciona para a View
                    request.getRequestDispatcher("views/lista_alunos.jsp").
                            forward(request, response);

                    break;

                case "pesquisar":
                    // Cria um novo aluno
                    aluno = new Aluno();

                    /**
                     * Atribui os valores recuperados do formulário O parâmetro
                     * utilizado "pesquisa" é igual para os três campos, pois
                     * está sendo utilizado o LIKE na instrução SQL do DAO
                     */
                    aluno.setNome(request.getParameter("pesquisa"));
                    aluno.setRa(request.getParameter("pesquisa"));
                    aluno.setCurso(request.getParameter("pesquisa"));

                    // Busca no model (DAO) os dados
                    alunoDAO = new AlunoDAO();

                    // Coloca todos os alunos em uma lista
                    pesquisa = alunoDAO.pesquisar(aluno);

                    // Cria um atributo com o aluno para ser utilizado na View
                    request.setAttribute("listaAlunos", pesquisa);

                    // Redireciona para a View
                    request.getRequestDispatcher("views/lista_alunos.jsp").
                            forward(request, response);

                    break;
                case "editar":

                    /**
                     * Cria o objeto aluno e atribui o RA para pesquisa
                     */
                    aluno = new Aluno();
                    aluno.setRa(request.getParameter("ra"));

                    // Busca no model os dados
                    alunoDAO = new AlunoDAO();

                    // Coloca todos os alunos em uma lista
                    alunoAlteraDAO = alunoDAO.pesquisar(aluno);
                    
                    // Cria um atributo com o aluno para ser utilizado na View
                    request.setAttribute("listaAlunos", alunoAlteraDAO);

                    // Redireciona para a View
                    request.getRequestDispatcher("views/editar.jsp").
                            forward(request, response);

                    break;

                case "salvar":
                    // Redireciona para a página de erro
                    tituloErro = "<h1>Aviso!</h1>";
                    erro = "Metodo [<strong>salvar</strong>] não implementado";
                    request.setAttribute("mensagem", tituloErro + erro);
                    request.getRequestDispatcher("views/erro.jsp").
                            forward(request, response);
                    break;

                case "excluir":
                    // Redireciona para a página de erro
                    tituloErro = "<h1>Aviso!</h1>";
                    erro = "Metodo [<strong>excluir</strong>] não implementado";
                    request.setAttribute("mensagem", tituloErro + erro);
                    request.getRequestDispatcher("views/erro.jsp").
                            forward(request, response);
                    break;
            }
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
