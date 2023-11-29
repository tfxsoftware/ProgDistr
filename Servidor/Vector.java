import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class Vector <X> implements Cloneable, Serializable
{
    private static final int TAMANHO_INICIAL = 10;
    private static final long serialVersionUID = 6529685098267757690L;
    private Object[] elem; //private X[] elem;
    private int qtd;

    public Vector ()
    {
      //this.elem = new X [Vector.TAMANHO_INICIAL]; <- nao compila
        this.elem = new Object [Vector.TAMANHO_INICIAL];
        this.qtd  = 0;
    }

    public Vector(Object[] elem){
        this.elem = elem;
        this.qtd = elem.length;
    }

    public int size ()
    {
        return this.qtd;
    }

    private void redimensioneSe (float taxaDeRedim)
    {
        Object[] novo;
        
      //novo = new X [Math.round(this.elem.length*taxaDeRedim)]; <- nao compila
        novo = new Object [Math.round(this.elem.length*taxaDeRedim)];

        for (int i=0; i<this.qtd; i++)
            novo[i] = this.elem[i];

        this.elem = novo;
    }

    public void add (X x)
    {
        if (this.qtd==this.elem.length)
            this.redimensioneSe (2.0F);

        this.elem[this.qtd] = x;
        this.qtd++;
    }

    public X get (int posicao) // posicao vai de 0 a this.qtd-1
    {
        if (posicao<0 || posicao>this.qtd-1)
            throw new java.lang.ArrayIndexOutOfBoundsException (posicao);
            
        return (X)this.elem[posicao];
    }

    public void remove (int posicao) throws ArrayIndexOutOfBoundsException
    {
        if (posicao<0 || posicao>this.qtd-1)
            throw new java.lang.ArrayIndexOutOfBoundsException (posicao);

        for (int i=posicao+1; i<this.qtd; i++)
            this.elem[i-1] = this.elem[i];

        this.qtd--;
        this.elem[this.qtd] = null;

        if (this.elem.length>Vector.TAMANHO_INICIAL &&
            this.qtd<=Math.round(this.elem.length*0.25F))
            this.redimensioneSe (0.5F);
    }

    public Integer procuraPosicao(X item){
        for (int i = 0; i<qtd;i++){
            if (item.equals(elem[i])) return (Integer) i;
        }
        return null;
    }

        public void embaralhaVetor(){
        // instancia o random
        Random rand = new Random();
		
        // loop para embaralhar o Vector
		for (int i = 0; i < this.qtd; i++) {
            // gera um número aleatorio para trocar de lugar
			int randomIndexToSwap = rand.nextInt(this.qtd);
            // salva o valor que está presente no index do número gerado (randomIndexToSwap)
			int temp = (int) this.elem[randomIndexToSwap];
            // coloca o elemento presente no posição i no index gerado (randomIndexToSwap)
			this.elem[randomIndexToSwap] = this.elem[i];
            // coloca o elemento presente no index gerado (randomIndexToSwap) no index i
			this.elem[i] = temp;
		}
        //System.out.println(this);
    }

    public Vector<X> divideVetor(int inicio, int fim){
       return new Vector<X>(Arrays.copyOfRange(this.elem, inicio, fim));
    }
    public String toString ()
    {
        String ret="[";

        for (int i=0; i<this.qtd-1; i++)
            ret = ret+this.elem[i]+", ";

        if (this.qtd>0)
            ret = ret+this.elem[this.qtd-1];

        return ret+"]";
    }

    @Override
    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;
            
        if (obj==null)
            return false;
            
        if (this.getClass()!=obj.getClass())
            return false;

        Vector<X> vec = (Vector<X>)obj;

        for (int i=0; i<this.qtd; i++)
            if (!this.elem[i].equals(vec.elem[i]))
                return false;
                
        return true;
    }

    @Override
    public int hashCode ()
    {
        int ret=1;

        ret = 13*ret + Integer.valueOf(this.qtd).hashCode();

        for (int i=0; i<this.qtd; i++)
            ret = 13*ret + this.elem[i].hashCode();

        if (ret<0) ret=-ret;

        return ret;
    }

    public Vector (Vector<X> modelo) throws Exception
    {
        if (modelo==null) throw new Exception ("modelo ausente");

        this.qtd=modelo.qtd;

        // this.elem = new X[modelo.elem.length];
        this.elem = new Object [modelo.elem.length];

        for (int i=0; i<this.qtd; i++)
            this.elem[i]=modelo.elem[i];
    }

    @Override
    public Object clone ()
    {
        Vector<X> ret=null;

        try
        {
            ret = new Vector<X> (this);
        }
        catch (Exception erro)
        {} // ignoro, pq sei que no try nao ocorrera excecoes

        return ret;
    }



}
