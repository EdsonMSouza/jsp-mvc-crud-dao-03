/**
 * Arquivo: Aluno.java
 *
 */
package beans;

import java.util.HashMap;
import java.util.Map;

/**
 * Author : Edson Melo de Souza, MSc <souzaem@outlook.com>
 * Since : 03/03/2016, 11:33:13
 */
public class Aluno {

    private int id;
    private String ra;
    private String nome;
    private String curso;
    private String disciplina;
    private String email;

    /**
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return String
     */
    public String getRa() {
        return ra;
    }

    /**
     *
     * @param ra
     */
    public void setRa(String ra) {
        this.ra = ra;
    }

    /**
     *
     * @return String
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return String
     */
    public String getCurso() {
        return curso;
    }

    /**
     *
     * @param curso
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     *
     * @return String
     */
    public String getDisciplina() {
        return disciplina;
    }

    /**
     *
     * @param disciplina
     */
    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    /**
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Realiza a verificação de todos os campos da classe
     *
     * @return Campos
     */
    public Map<String, String> verificaDados() {

        Map<String, String> campos = new HashMap<>();

        if (nome.isEmpty()) {
            campos.put("nome", "");
        } else {
            campos.put("nome", nome);
        }

        if (curso.isEmpty()) {
            campos.put("curso", "");
        } else {
            campos.put("curso", curso);
        }

        if (disciplina.isEmpty()) {
            campos.put("disciplina", "");
        } else {
            campos.put("disciplina", disciplina);
        }

        if (email.isEmpty()) {
            campos.put("email", "");
        } else {
            campos.put("email", email);
        }

        return campos;
    }

}
