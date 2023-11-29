import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionThread extends Thread
{
    private String ip;
    private int port;
    private Vector<Integer> vet;
    private int num;
    private int ordem;
    private Socket conexao;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Integer resposta;


    
    public ConnectionThread (String ip, int port, Vector<Integer> vet, int num, int ordem)
    {
        this.ip = ip;
        this.port = port;
        this.vet = vet;
        this.num = num;
        this.ordem = ordem;
    }
    
    

    public void run ()
    {
        try {
            abrePedido();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao conseguir resposta do ip " + ip + " Error: " + e);
        }
    }

    private void abrePedido() throws UnknownHostException, IOException, ClassNotFoundException{
        this.conexao = new Socket(ip, port);
        this.ois = new ObjectInputStream(conexao.getInputStream());
        this.oos = new ObjectOutputStream(conexao.getOutputStream());
        Object[] obj = {this.ordem, this.num, this.vet.size(), this.vet};
        oos.writeObject(obj);
        this.resposta = (Integer) ois.readObject();
    }

    public Integer getResposta(){
        return this.resposta;
    }


}
