
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        
        //declarando variaveis
        String[] ips = {"127.0.1.1"}; // lista de ips!
        int porta = 9001;
        int tamanhoVetor = 10000;
        Vector<Integer> vetorGigante = new Vector<Integer>();
        int numASerProcurado = new Random().nextInt(tamanhoVetor); // numero aleatorio a ser procurado
        //populando vetor
        for (int i=0;i<tamanhoVetor;i++){
            vetorGigante.add(i);
        }
        //vetorGigante.embaralhaVetor(); podemos embaralhar o vetor
        
        //determinando range de busca por servidor
        int range = (int) tamanhoVetor/ips.length;
        int inicio = 0;
        int fim = range;
        ConnectionThread[] conexoes = new ConnectionThread[ips.length];
        
        //dividindo tarefas
        for (int i = 0;i < ips.length;i++){
            Vector<Integer> vetorDividido = vetorGigante.divideVetor(inicio, fim);
            ConnectionThread conexao = new ConnectionThread(ips[i], porta, vetorDividido, numASerProcurado, i);
            inicio = fim + 1;
            fim = fim + range;
            conexoes[i] = conexao;
            conexao.start();
           
        }

        //pegando resultados.
        for (int i = 0;i < ips.length;i++){
            try {
                conexoes[i].join();
                if (conexoes[i].getResposta() != null) {
                    System.out.println("Endereço: " + ips[i] + " encontrou o número " + numASerProcurado + " na posicao: " + conexoes[i].getResposta());
                }
                else {
                    System.out.println("Endereço: " + ips[i] + " não encontrou o número " + numASerProcurado);
                }

            } catch (InterruptedException e) {
                
                e.printStackTrace();
            }
        }

    

    }
}