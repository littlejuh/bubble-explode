import java.util.Random;

/**
 * Class <code>Bolha</code> representa uma simples bolha.
 * 
 * @author marco.mangan@pucrs.br 
 * @author marco.gonzales@pucrs.br
 */
public class Bolha 
{
	
	/**
	 * caracteres de cores válidas.
	 */
	private String cores = "YGBR";
	
	/**
	 * tabuleiro.
	 */
    private Tabuleiro tabuleiro;
    
    /**
     * cor da Bolha.
     */
    private char cor;
    
    /**
     * linha da Bolha.
     */
    private int linha;
    
    /**
     * coluna da Bolha.
     */
    private int coluna;

    /**
     * semente randômica.
     */
    private static Random r = new Random();

    /** 
     * Cria uma Bolha.
     * 
     * @param tabuleiro
     * @param linha
     * @param coluna
     */
    public Bolha(Tabuleiro tabuleiro, int linha, int coluna) {
        // poderia haver exceção: tabuleiro null ou posição não válida
        this.tabuleiro = tabuleiro;
        this.linha = linha;
        this.coluna = coluna;
        this.cor = cores.charAt(r.nextInt(4));
    }

   /**
    * Cria uma Bolha apenas para teste!
    */
    public Bolha(Tabuleiro tabuleiro, int linha, int coluna, char cor) {
        // Poderia haver exceção: tabuleiro null, posição inválida ou cor vazia
        this.tabuleiro = tabuleiro;
        this.linha = linha;
        this.coluna = coluna;
        this.cor = cor;
    }

    /** 
     * Retorna um Estouro, this é a bolha iniciadora.
     * Um Estouro pode falhar.
     * 
     * @return Estouro estouro com as Bolhas explodidas
     */
    public Estouro explodir() {
        Estouro estouro = new Estouro(this);
        estouro.explodirIniciadora();
        return estouro;
    }

    /** 
     * Move Bolha para baixo, se possível.
     * 
     * @return      true se a bolha foi movida.
     */
    public boolean moverParaBaixo() {
        if (tabuleiro.moverParaBaixo(this)) {
            linha++;
            return true;
        }
        return false;
    }

    /** 
     * Retorna a linha da Bolha.
     * 
     * @return      int linha
     */
    public int getLinha() {
        return linha;
    }

    /** 
     * Retorna a coluna da Bolha.
     * 
     * @return      int coluna
     */
    public int getColuna() {
        return coluna;
    }

    /** 
     * Retorna a cor da Bolha.
     * 
     * @return      char cor
     */
    public char getCor() {
        return cor;
    }

    /** 
     * Remove tabuleiro this.
     * Na mensagem não use remove(this), use remove()!
     */
    public void remover() {
        tabuleiro.remover(this);
    }
    
    /** 
     * Retorna a célula acima.
     * 
     * @return  uma Bolha
     */
    public Bolha getAcima() {
        return tabuleiro.getLocalAcima(this);
    }

    /** 
     * Retorna a célula abaixo.
     * 
     * @return  uma Bolha
     */
    public Bolha getAbaixo() {
        return tabuleiro.getLocalAbaixo(this);
    }

    /** 
     * Retorna a célula à direita.
     * 
     * @return  uma Bolha
     */
    public Bolha getDireita() {
        return tabuleiro.getLocalADireita(this);
    }

    /** 
     * Retorna a célula à esquerda.
     * 
     * @return  uma Bolha
     */
    public Bolha getEsquerda() {
        return tabuleiro.getLocalAEsquerda(this);
    }

    /** 
     * Verifica se a Bolha está sozinha.
     * 
     * @return  true se está sozinha
     */
    public boolean verificarIsolada() {
        //verifica se a bolha a esquerda é da mesma cor
        if(getEsquerda() != null && getEsquerda().getCor() == cor ){
        return false;
        }
        //verifica se a bolha a direita é da mesma cor
        if(getDireita() != null && getDireita().getCor() == cor ){
        return false;
        }
        //verifica se a bolha a abaixo é da mesma cor
        if(getAbaixo() != null && getAbaixo().getCor() == cor ){
        return false;
        }
        //verifica se a bolha a acima é da mesma cor
        if(getAcima() != null && getAcima().getCor() == cor ){
        return false;
        }
        //retorna true se a bolha estiver isolada
        return true;
    }
    
    //@Override
    public String toString() {
        return String.format("%s", cor);
    }   

}
