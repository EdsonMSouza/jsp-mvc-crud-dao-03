/**
 * Arquivo: GeraRA.java
 *
 */
package utils;

import java.util.Random;

/**
 * Author : Edson Melo de Souza, MSc <souzaem@outlook.com>
 * Since : 03/03/2016, 15:16:49
 */
public class GeraRA {

    // Variável para armazenar o novo RA
    private final String ra;

    /**
     * Instancia um objeto da classe Random usando o construtor genérico
     */
    public GeraRA() {
        // Cria um objeto para gerar os número randômicos
        Random gerador = new Random();

        // Cria a variável para armazenar o RA criado
        String retorno = "";

        // Concatena os números aleatórios entre 1 e 9
        for (int i = 1; i < 10; i++) {
            retorno += String.valueOf(gerador.nextInt(10));
        }
        this.ra = retorno;
    }

    /**
     *
     * @return String
     */
    public String getRa() {
        return ra;
    }

}
