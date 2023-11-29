public class ThreadProcuradora extends Thread
{
    

    private boolean fim = false;
    private Vector<Integer> armz;
    private int i;
    private int f;
    private int valor;
    private int posicao = -1;

    
    public ThreadProcuradora (Vector<Integer> armz, int i, int f, int valor)
    {
        this.armz = armz;
        this.i = i;
        this.f = f;
        this.valor = valor;
    }
    
    

    public void morra ()
    {
        this.fim=true;
    }

    public void run ()
    {
        for (int k = i;k<=f;k++){
            if (this.valor == this.armz.get(k)) {
                this.posicao = k;
                break;
            }
        }
    }

    public int getPosicao(){
        return this.posicao;
    }
    
    public int getValor(){
        return this.valor;
    }
}
