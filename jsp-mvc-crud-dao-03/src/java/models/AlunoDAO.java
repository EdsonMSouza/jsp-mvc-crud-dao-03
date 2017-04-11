/**
 * Arquivo: AlunoDAO.java
 *
 */
package models;

import beans.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnectionFactory;

/**
 * Author : Edson Melo de Souza, Me. <prof.edson.melo@gmail.com>
 * Since : 10/03/2016, 15:24:44
 */
public class AlunoDAO {

    // Declaração das variáveis globais
    private Connection conexao = null;
    private String status;

    /**
     * Método construtor da classe
     *
     * @throws SQLException
     */
    public AlunoDAO() throws SQLException {
        // Retorna a conexao no momento da chamada da classe
        this.conexao = ConnectionFactory.getInstance().getConnection();
    }

    /**
     * Realiza a inclusão de um novo registro no banco de dados
     *
     * @param aluno
     */
    @SuppressWarnings("empty-statement")
    public void inserir(Aluno aluno) {
        try {
            // Declaração da variável para a instrução SQL
            String sql = "insert INTO aluno (ra, nome, curso, disciplina, email) "
                    + "VALUES (?,?,?,?,?)";

            // Atribui os valores ao objeto ps
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                // seta os valores
                ps.setString(1, aluno.getRa());
                ps.setString(2, aluno.getNome());
                ps.setString(3, aluno.getCurso());
                ps.setString(4, aluno.getDisciplina());
                ps.setString(5, aluno.getEmail());

                // Executa o sql (execute)
                ps.execute();

                // Fecha o ps
                ps.close();
            }

            // Fecha a conexão
            conexao.close();

            // Retorna o status da inserção
            status = "Inserido com Sucesso!";

        } catch (SQLException ex) {
            // Lança um erro novo personalizado 
            throw new RuntimeException("Erro ao inserir aluno", ex);
        }
    }

    /**
     * Realiza a atualização de um registro específico
     *
     * @param aluno
     */
    public void salvar(Aluno aluno) {
        // Não implementado
    }

    /**
     * Realiza a exclusão de um registro específico
     *
     * @param aluno
     */
    public void excluir(Aluno aluno) {
        // Não implementado
    }

    /**
     * Realiza a pesquisa no banco de dados e retorna um ou mais registros
     *
     * @param aluno
     * @return Aluno
     */
    public List<Aluno> pesquisar(Aluno aluno) {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno WHERE ra like ? or nome like ? or curso like ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, '%' + aluno.getRa() + '%');
            ps.setString(2, '%' + aluno.getNome() + '%');
            ps.setString(3, '%' + aluno.getCurso() + '%');

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setRa(rs.getString("ra"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCurso(rs.getString("curso"));
                aluno.setDisciplina(rs.getString("disciplina"));
                aluno.setEmail(rs.getString("email"));
                alunos.add(aluno);
            }
            return alunos;

        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Falha ao listar os alunos em JDBCAluno", ex);
        }
    }

    /**
     * Realiza a listagem de TODOS os registros existentes no banco de dados
     *
     * @return Aluno
     */
    public List<Aluno> listar() {
        List<Aluno> alunos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM aluno ORDER BY nome";
            try (PreparedStatement ps = conexao.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Aluno aluno = new Aluno();
                    aluno.setId(rs.getInt("id"));
                    aluno.setRa(rs.getString("ra"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setCurso(rs.getString("curso"));
                    aluno.setDisciplina(rs.getString("disciplina"));
                    aluno.setEmail(rs.getString("email"));

                    alunos.add(aluno);
                }
            }
            return alunos;

        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Falha ao listar os alunos em JDBCAluno", ex);
        }
    }

    /**
     * Método que retorna o status da operação realizada
     *
     * @return String
     */
    @Override
    public String toString() {
        return status;
    }

}
