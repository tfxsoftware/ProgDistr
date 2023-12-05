import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
        //static ServerSocket
        private static ServerSocket server;
        //socket server port 
        private static int port = 9001;
    public static void main(String[] args)  {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        while(true){
            System.out.println("Aguardando vetor do cliente!");
            try (Socket socket = server.accept()) {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                
                // Lendo o vetor e o desconstruindo/revelando tipos.
                Object[] message = (Object[]) ois.readObject();
                int ordem = (int) message[0];
                Integer num = (Integer) message[1];
                int tamanhoVetor = (int) message[2];
                Vector<Integer> vet = (Vector<Integer>) message[3];
                System.out.println("Requisiçao recebida! Procurando numero: " + num.toString());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                Integer resposta = vet.procuraPosicao(num);
                if (resposta != null){
                    //aqui temos que calcular aonde esse item está no vetor principal, já que estamos lidando com apenas uma parte do vetor
                    int posicao = tamanhoVetor * ordem + resposta;
                    oos.writeObject(posicao);
                    System.out.println("Este servidor achou o número na posicao " + posicao);
                }
                else{
                    oos.writeObject(null);
                    System.out.println("Esteve servidor não achou o número! ");
                }
                oos.flush();
            } catch (ClassNotFoundException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }



}
