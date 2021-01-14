import java.util.Random;
import java.util.Scanner;

/*
    Desafio Olimpíada Brasileira de Informática - 2019
    Tarefas Programação Nível Sênior - Fase 1 : Chuva
    url: https://olimpiada.ic.unicamp.br/pratique/pu/2019/f1/chuva/
 */

public class solucao {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int N, M;

        // Recebendo a quantidade de linhas da matriz (valor valido entre 3 e 500)
        do {
            System.out.println("Informe a quantidade de linhas: ");
            N = in.nextInt();
        } while (N < 3 || N > 500);

        // Recebendo a quantidade de colunas da matriz (valor valido entre 3 e 500)
        do {
            System.out.println("Informe a quantidade de colunas: ");
            M = in.nextInt();
        } while (M < 3 || M > 500);

        // Declaração da matriz com os valores recebidos
        String[][] chuva = new String[N][M];
        // Identificando a coluna na posição "meio" da matriz
        int posicaoVazamento = M / 2;
        // Array com as possíveis opções de caractere para o preenchimento da matriz
        String[] charPossiveis = {"#", "."};

        // Preenchimento da matriz
        for (int i = 0; i < chuva.length; i++) {
            for (int j = 0; j < chuva[0].length; j++) {
                // Verificando se a linha atual é a primeira
                if (i == 0) {
                    // Verificando se a coluna atual está na posição "meio", caso sim, é criado o ponto de inicio da chuva
                    if (j == posicaoVazamento) {
                        chuva[i][j] = "o";
                    } else {
                        chuva[i][j] = ".";
                    }
                    // Interrompendo e incrementando ciclo atual para evitar verificações desnecessárias
                    continue;
                }
                // Verificando se a linha atual é impar (Verificação invertida, já que o array começa em 0)
                if (i % 2 == 0 || i == chuva.length - 1) {
                    chuva[i][j] = ".";
                // Verificando se a coluna atual é a primeira ou a ultima
                } else if (j == 0 || j == chuva[0].length - 1) {
                    chuva[i][j] = ".";
                } else {
                // Preenchendo a linha atual com caracteres aleatórios (armazenados em charPossíveis)
                    int rnd = new Random().nextInt(charPossiveis.length);
                    chuva[i][j] = charPossiveis[rnd];
                }
            }
        }

        System.out.println("\nEntrada: \n");

        // Impressão da matriz já preenchida
        for (int i = 0; i < chuva.length; i++) {
            for (int j = 0; j < chuva[0].length; j++) {
                System.out.print(chuva[i][j]);
            }
            System.out.println();
        }

        // Variável contadora para identificar quantos caracteres quem podem ser alterados ( . ) existem
        int contador = 0;

        // Realizando contagem de caracteres que podem ser alterados de parede ( . ) para chuva ( o )
        for (int i = 0; i < chuva.length; i++) {
            for (int j = 0; j < chuva[0].length; j++) {
                if (chuva[i][j].equals(".")) {
                    contador++;
                }
            }
        }

        // Repetição para cada caractere valido para modificação
        for (int cont = 1; cont < contador; cont++) {
            // Transformando os caracteres de parede ( . ) em caracteres de chuva ( o )
            for (int i = 0; i < chuva.length; i++) {
                for (int j = 0; j < chuva[0].length; j++) {
                    // Verificando se o caractere atual é do tipo parede ( . )
                    if (chuva[i][j] == ".") {
                        // Verificando se a linha atual é a primeira
                        if (i == 0) {
                            // Verificando se a coluna atual é a primeira
                            if (j == 0) {
                                // Verificando se o caractere a direita é do tipo chuva ( o ) e se o embaixo dele é do tipo prateleira ( # )
                                if (chuva[i][j + 1] == "o" && chuva[i + 1][j + 1] == "#") {
                                    // Caso sim, o caractere do tipo parede ( . ) se transforma em um do tipo chuva ( o )
                                    chuva[i][j] = "o";
                                }
                            // Verificando se a coluna atual é a ultima
                            } else if (j == chuva[0].length - 1) {
                                // Verificando se o caractere a esquerda é do tipo chuva ( o ) e se o embaixo dele é do tipo prateleira ( # )
                                if (chuva[i][j - 1] == "o" && chuva[i + 1][j - 1] == "#") {
                                    // Caso sim, o caractere do tipo parede ( . ) se transforma em um do tipo chuva ( o )
                                    chuva[i][j] = "o";
                                }
                            // Caso a coluna não seja nem a primeira nem a ultima
                            } else {
                                // Verificando se o caractere a esquerda é do tipo chuva ( o ) e se o embaixo dele é do tipo prateleira ( # ) ou se
                                // o caractere a direita é do tipo chuva ( o ) e se o embaixo dele é do tipo prateleira ( # )
                                if (chuva[i][j-1] == "o" && chuva[i+1][j-1] == "#" || chuva[i][j+1] == "o" && chuva[i+1][j+1] == "#"){
                                    // Caso sim, o caractere do tipo parede ( . ) se transforma em um do tipo chuva ( o )
                                    chuva[i][j] = "o";
                                }
                            }
                        // Verificando se a linha atual é a ultima
                        } else if (i == chuva.length - 1) {
                            // Verificando se o caractere a cima deste é do tipo chuva ( o )
                            if (chuva[i - 1][j] == "o") {
                                // Caso sim, o caractere do tipo parede ( . ) se transforma em um do tipo chuva ( o )
                                chuva[i][j] = "o";
                            }
                        // Caso a linha atual não seja nem a primeira nem a ultima
                        } else {
                            // Verificando se a coluna atual é a primeira
                            if (j == 0) {
                                // Verificando se o caractere a cima deste é do tipo chuva ( o ) ou se
                                // o caractere a direita é do tipo chuva ( o ) e se o embaixo dele é do tipo prateleira ( # )
                                if (chuva[i - 1][j] == "o" || chuva[i][j + 1] == "o" && chuva[i + 1][j + 1] == "#") {
                                    chuva[i][j] = "o";
                                }
                            // Verificando se a coluna atual é a ultima
                            } else if (j == chuva[0].length - 1) {
                                // Verificando se o caractere a cima deste é do tipo chuva ( o ) ou se
                                // o caractere a esquerda é do tipo chuva ( o ) e se o embaixo dele é do tipo prateleira ( # )
                                if (chuva[i - 1][j] == "o" || chuva[i][j - 1] == "o" && chuva[i + 1][j - 1] == "#") {
                                    chuva[i][j] = "o";
                                }
                            // Caso a coluna não seja nem a primeira nem a ultima
                            } else {
                                // Verificando se o caractere a cima deste é do tipo chuva ( o ) ou se
                                // o caractere a direita é do tipo chuva ( o ) e se o embaixo dele é do tipo prateleira ( # ) ou se
                                // o caractere a esquerda é do tipo chuva ( o ) e se o embaixo dele é do tipo prateleira ( # )
                                if (chuva[i - 1][j] == "o" || chuva[i][j - 1] == "o" && chuva[i + 1][j - 1] == "#" || chuva[i][j + 1] == "o" && chuva[i + 1][j + 1] == "#") {
                                    chuva[i][j] = "o";
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("\nSaida: \n");

        // Imprimindo a matriz com os devidos caracteres de parede ( . ) já transformados em um do tipo chuva ( o )
        for (int i = 0; i < chuva.length; i++) {
            for (int j = 0; j < chuva[0].length; j++) {
                System.out.print(chuva[i][j]);
            }
            System.out.println();
        }
    }
}
