import java.util.ArrayList;

/**
 * A classe <code>Estouro</code> representa uma explosão no tabuleiro, iniciando 
 * pela Bolha <code>iniciadora</code>.
 * 
 * Um Estouro falhará se a <code>iniciadora</code> não tiver a mesma cor da
 * Bolha adjacente. Um Estouro que falha não tem forma ("NONE)" e não gera pontos.
 * 
 * Um Estouro terá sucesso se a <code>iniciadora</code> tiver uma ou mais Bolhas
 * adjacentes da mesma cor. Um Estouro gera pontos dependendo do seu tamanho: 
 * quanto mais Bolhas, mais pontos
 * 
 * Algumas formas de Estouros geram pontos de bônus. Veja o Bolha Explode original
 * sobre a documentação a respeito.
 * 
 * @see http://www.spookyhousestudios.com/bubex-bonus-shapes.html
 * 
 * @author marco.mangan@pucrs.br
 * @author marco.gonzales@pucrs.br
 * 
 */
public class Estouro {

    /**
     * Bolha que inicia o Estouro.
     */
    private Bolha iniciadora;

    /**
     * Bolhas explodidas a partir da <code>iniciadora</code>.
     */
    private ArrayList<Bolha> explodidas;

    /**
     * multiplicador do escore relacionado às formas de Estouro.
     * 
     * @see http://www.spookyhousestudios.com/bubex-bonus-shapes.html
     */
    private int multiplicador;

    /**
     * Forma do Estouro, se nenhuma forma é identificada, 
     * forma é configurada como "NONE".
     * 
     * @see http://www.spookyhousestudios.com/bubex-bonus-shapes.html
     */
    private String forma;

    /**
     * escore de pontos do Estouro.
     */
    private int escore;

    /**
     * Cria um novo estouro (explosão) iniciando pela Bolha <code>iniciadora</code>.
     * 
     * Para cada estouro, inicialmente deve haver:
     * lista de explodidas vazia, 
     * escore zerodo, 
     * multiplicador igual a 1,
     * e forma "NONE".
     * 
     * Verifique: <code>iniciadora</code> não pode ser null.
     * 
     * @param iniciadora
     *            uma Bolha com no mínimo outra adjacente da mesma cor
     * @see #explodirIniciadora()
     */
    public Estouro(Bolha iniciadora) {
        this.escore = 0;
        this.multiplicador = 1;
        this.forma = "NONE";
        explodidas = new ArrayList<Bolha>();
        this.iniciadora = iniciadora;
    }

    /**
     * Inicia o estouro.
     * 
     * Se a <code>iniciadora</code> está sozinha, nada acontece.
     * 
     * Remove (pops) Bolhas adjacentes de mesma cor 
     * vizinhas da <code>iniciadora</code>,
     * computa escores
     * e verifica se o Estouro tem bônus por forma.
     */
    public void explodirIniciadora() {
        //verifica se a bolha tá isolada
        if(iniciadora.verificarIsolada() == false){
            //adiciona a bolha iniciadora dentro do array de explodidas
            explodidas.add(iniciadora);
            int posicao = 0;
            //laço para verificar bolhas da mesma cor adjacentes a iniciadora e adicionar no array das explodidas
            while(posicao <= explodidas.size()-1){
                Bolha atual = explodidas.get(posicao);
                //pega a esquerda da bolha atual e compara se a cor é igual
                if(atual.getEsquerda() != null && atual.getEsquerda().getCor() == atual.getCor()){
                    //verifica já não contem uma bolha igual no array, se não contém, adiciona no array das explodidas
                    if(explodidas.contains(atual.getEsquerda()) == false)
                        explodidas.add(atual.getEsquerda());
                }
                //pega a direita da bolha atual e compara se a cor é igual
                if(atual.getDireita() != null && atual.getDireita().getCor() == atual.getCor()){
                    //verifica já não contem uma bolha igual no array, se não contém, adiciona no array das explodidas
                    if(explodidas.contains(atual.getDireita()) == false)
                        explodidas.add(atual.getDireita());
                }
                //pega a abaixo da bolha atual e compara se a cor é igual
                if(atual.getAbaixo() != null && atual.getAbaixo().getCor() == atual.getCor()){
                    //verifica já não contem uma bolha igual no array, se não contém, adiciona no array das explodidas
                    if(explodidas.contains(atual.getAbaixo()) == false)
                        explodidas.add(atual.getAbaixo());
                }
                //pega a acima da bolha atual e compara se a cor é igual
                if(atual.getAcima() != null && atual.getAcima().getCor() == atual.getCor()){
                    //verifica já não contem uma bolha igual no array, se não contém, adiciona no array das explodidas
                    if(explodidas.contains(atual.getAcima()) == false)
                        explodidas.add(atual.getAcima());
                }   
                posicao++;
            }
            //chama o metodo para ordenar o array das explodidas por linha e coluna
            ordenaExplodidas(explodidas);
            //calcula o escore padrão das bolhas por uma f(x)=x^2+x
            int tempEscore = explodidas.size()*explodidas.size()+explodidas.size();
            //verifica se as bolhas explodidas sao de formato QUADRADO, se for, multiplica o escore por 8.
            if(verificarQuadrado()){
                forma = "SQUARE";
                tempEscore = tempEscore * 8;
            }
            //verifica se as bolhas explodidas sao de formato T, se for, multiplica o escore por 6.
            else if(verificarLetraT()){
                forma = "TEE";
                tempEscore = tempEscore * 6;
            } 
            //verifica se as bolhas explodidas sao de formato CRUZ, se for, multiplica o escore por 8.
            else if(verificarCruz()){
                forma = "CROSS";
                tempEscore = tempEscore * 8;
            } 
            //verifica se as bolhas explodidas sao de formato RETANGULO, se for, multiplica o escore por 8.
            else if(verificarRetangulo()){
                forma = "RECTANGLE";
                tempEscore = tempEscore * 8;
            } 
            //verifica se as bolhas explodidas sao de formato ESCADA, se for, multiplica o escore por 6.
            else if(verificarEscada()){
                forma = "STAIRS";
                tempEscore = tempEscore * 6;
            } 
            //verifica se as bolhas explodidas sao de formato GARFO, se for, multiplica o escore por 6.
            else if(verificarGarfo()){
                forma = "FORK";
                tempEscore = tempEscore * 6;
            }
            //verifica se as bolhas explodidas sao de formato LINHA, se for, multiplica o escore por 8.
            else if(verificarLinha()){
                forma = "LINE";
                tempEscore = tempEscore * 8;
            }
            //Se não for de nenhum dos formatos acima, forma = "NONE"
            else{
                forma = "NONE";
            }
            //escore final
            escore = tempEscore;
            //pega cada elemento do array de explodidas e remove do tabuleiro
            for(Bolha b: explodidas){
                b.remover();
            }  
        }        
        
    }
    
   /**
     * Ordena o array de explodidas por linha e coluna.
     * @param a
     *            ArrayList<Bolha> explodidas          
     */
    private void ordenaExplodidas(ArrayList<Bolha> explodidas)
    {
    boolean trocou = true;
    int pos;
    int tamanho = explodidas.size();
    Bolha b1, b2;
    while(trocou){
        trocou = false;
        for(pos=0;pos<tamanho-1;pos++){
            b1 = explodidas.get(pos);
            b2 = explodidas.get(pos+1);
            if(b1.getLinha() > b2.getLinha()){
                explodidas.set(pos,b2);
                explodidas.set(pos+1,b1);
                trocou = true;
            }
            if(b1.getLinha() == b2.getLinha() && b1.getColuna() > b2.getColuna()){
                explodidas.set(pos,b2);
                explodidas.set(pos+1,b1);
                trocou = true;
            }
        }
        tamanho--;
    }
     /* for(Bolha b : explodidas){
            System.out.println(b.getLinha() + " - "  + b.getColuna());
            }
     */
    }

    /**
     * Verifica se <code>inferior</code> é a Bolha que está logo abaixo
     * da <code>bolha</code>.
     * 
     * @param a
     *            Bolha bolha
     * @param a
     *            Bolha inferior
     * @return true se a inferior é realmente uma Bolha que está logo abaixo
     */
    private boolean verificarAbaixo(Bolha bolha, Bolha inferior) {
        return bolha.getColuna() == inferior.getColuna()
                && bolha.getLinha() == inferior.getLinha() - 1;
    }

    /**
     * Verifica se <code>direita</code> é a Bolha que está logo à direita
     * da <code>bolha</code>.
     * 
     * @param a
     *            Bolha bolha
     * @param a
     *            Bolha direita
     * @return true se direita é realmente uma Bolha que está logo à direita
     */
    private boolean verificarADireita(Bolha bolha, Bolha direita) {
        return bolha.getLinha() == direita.getLinha()
                && bolha.getColuna() == direita.getColuna() - 1;
    }

    /**
     * Retorna o multiplicador conforma a forma considerada, normalmente 6x or 8x.
     * 
     * @return int multiplicador
     */
    public int getMultiplicador() {
        return multiplicador;
    }

    /**
     * Retorna o nome da forma.
     * 
     * Lembre-se de usar nomes com letras maiúsculas.
     * 
     * @return String forma, que pode ser "NONE", se nenhuma forma for detectada.
     */
    public String getForma() {
        return forma;
    }

    /**
     * Retorna o escore atual.
     * 
     * @return int escore
     */
    public int getEscore() {
        return escore;
    }

    /**
     * Verifica se o Estouro tem forma quadrada independente de orientação (square).
     * 
     * @return true se Estouro tem forma quadrada
     */
    private boolean verificarQuadrado() {
        if (explodidas.size() != 4) {
            return false;
        }
        // 01
        // 23
        if (verificarADireita(explodidas.get(0), explodidas.get(1))
                && verificarAbaixo(explodidas.get(0), explodidas.get(2))
                && verificarADireita(explodidas.get(2), explodidas.get(3))) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se o Estouro tem forma da letra T independente de orientação (tee).
     * 
     * @return true se Estouro tem forma da letra T.
     */
    private boolean verificarLetraT() {
        if (explodidas.size() != 5) {
            return false;
        }
        // 012
        //  3
        //  4
        if (
                   verificarADireita(explodidas.get(0), explodidas.get(1))
                && verificarADireita(explodidas.get(1), explodidas.get(2))
                && verificarAbaixo(explodidas.get(1), explodidas.get(3))
                && verificarAbaixo(explodidas.get(3), explodidas.get(4))
                ) {
            return true;
        }
        //  0
        //  1
        // 234
        if (
                   verificarADireita(explodidas.get(2), explodidas.get(3))
                && verificarADireita(explodidas.get(3), explodidas.get(4))
                && verificarAbaixo(explodidas.get(0), explodidas.get(1))
                && verificarAbaixo(explodidas.get(1), explodidas.get(3))
                ) {
            return true;
        }
        // 0
        // 123
        // 4
        if (
                   verificarADireita(explodidas.get(1), explodidas.get(2))
                && verificarADireita(explodidas.get(2), explodidas.get(3))
                && verificarAbaixo(explodidas.get(0), explodidas.get(1))
                && verificarAbaixo(explodidas.get(1), explodidas.get(4))
                ) {
            return true;
        }
        //   0
        // 123
        //   4
        if (
                   verificarADireita(explodidas.get(1), explodidas.get(2))
                && verificarADireita(explodidas.get(2), explodidas.get(3))
                && verificarAbaixo(explodidas.get(0), explodidas.get(3))
                && verificarAbaixo(explodidas.get(3), explodidas.get(4))
                ) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se o Estouro tem forma de cruz independente de orientação (cross).
     * 
     * @return true se Estouro tem forma de cruz
     */
    private boolean verificarCruz() {
        if (explodidas.size() != 5) {
            return false;
        }
        //  0
        // 123
        //  4
        if (
                   verificarADireita(explodidas.get(1), explodidas.get(2))
                && verificarADireita(explodidas.get(2), explodidas.get(3))
                && verificarAbaixo(explodidas.get(0), explodidas.get(2))
                && verificarAbaixo(explodidas.get(2), explodidas.get(4))) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se o Estouro tem forma retangula independente de orientação (Rectangle).
     * 
     * @return true se Estouro tem forma retangula
     */
    private boolean verificarRetangulo() {
        if (explodidas.size() != 6) {
            return false;
        }
        // 01
        // 23
        // 45
        if (
                   verificarADireita(explodidas.get(0), explodidas.get(1))
                && verificarADireita(explodidas.get(2), explodidas.get(3))
                && verificarADireita(explodidas.get(4), explodidas.get(5))
                && verificarAbaixo(explodidas.get(0), explodidas.get(2))
                && verificarAbaixo(explodidas.get(2), explodidas.get(4))) {
            return true;
        }
        // 012
        // 345
        if (
                   verificarADireita(explodidas.get(0), explodidas.get(1))
                && verificarADireita(explodidas.get(1), explodidas.get(2))
                && verificarADireita(explodidas.get(3), explodidas.get(4))
                && verificarADireita(explodidas.get(4), explodidas.get(5))
                && verificarAbaixo(explodidas.get(0), explodidas.get(3))) {
            return true;
        }
        return false;
    }
    
    /**
     * Verifica se o Estouro tem forma escada independente de orientação (stairs).
     * 
     * @return true se Estouro tem forma escada
     */
    private boolean verificarEscada() {
        if (explodidas.size() != 4) {
            return false;
        }

        // 01
        //  23
        if (
                   verificarADireita(explodidas.get(0), explodidas.get(1))
                && verificarADireita(explodidas.get(2), explodidas.get(3))
                && verificarAbaixo(explodidas.get(1), explodidas.get(2))) {
            return true;
        }
        //  01
        // 23
        if (
                   verificarADireita(explodidas.get(0), explodidas.get(1))
                && verificarADireita(explodidas.get(2), explodidas.get(3))
                && verificarAbaixo(explodidas.get(0), explodidas.get(3))) {
            return true;
        }
        // 0
        // 12
        //  3
        if (
                   verificarADireita(explodidas.get(1), explodidas.get(2))
                && verificarAbaixo(explodidas.get(0), explodidas.get(1))
                && verificarAbaixo(explodidas.get(2), explodidas.get(3))) {
            return true;
        }
        //  0
        // 12
        // 3
        if (
                   verificarADireita(explodidas.get(1), explodidas.get(2))
                && verificarAbaixo(explodidas.get(0), explodidas.get(2))
                && verificarAbaixo(explodidas.get(1), explodidas.get(3))) {
            return true;
        }
        return false;
    }
    
        /**
     * Verifica se o Estouro tem forma de garfo independente de orientação (fork).
     * 
     * @return true se Estouro tem forma de garfo
     */
    private boolean verificarGarfo() {
        if (explodidas.size() != 6) {
            return false;
        }
        // 0 1
        // 234
        //  5
        if (
                   verificarADireita(explodidas.get(2), explodidas.get(3))
                && verificarADireita(explodidas.get(3), explodidas.get(4))
                && verificarAbaixo(explodidas.get(0), explodidas.get(2))
                && verificarAbaixo(explodidas.get(1), explodidas.get(4))
                && verificarAbaixo(explodidas.get(3), explodidas.get(5))) {
            return true;
        }
        
        //  0
        // 123
        // 4 5
        if (
                   verificarADireita(explodidas.get(1), explodidas.get(2))
                && verificarADireita(explodidas.get(2), explodidas.get(3))
                && verificarAbaixo(explodidas.get(0), explodidas.get(2))
                && verificarAbaixo(explodidas.get(1), explodidas.get(4))
                && verificarAbaixo(explodidas.get(3), explodidas.get(5))) {
            return true;
        }
        //  01
        // 23
        //  45
        if (
                   verificarADireita(explodidas.get(0), explodidas.get(1))
                && verificarADireita(explodidas.get(2), explodidas.get(3))
                && verificarADireita(explodidas.get(4), explodidas.get(5))
                && verificarAbaixo(explodidas.get(0), explodidas.get(3))
                && verificarAbaixo(explodidas.get(3), explodidas.get(4))) {
            return true;
        }
        // 01
        //  23
        // 45
        if (
                   verificarADireita(explodidas.get(0), explodidas.get(1))
                && verificarADireita(explodidas.get(2), explodidas.get(3))
                && verificarADireita(explodidas.get(4), explodidas.get(5))
                && verificarAbaixo(explodidas.get(1), explodidas.get(2))
                && verificarAbaixo(explodidas.get(2), explodidas.get(5))) {
            return true;
        }
        return false;
    }
    /**
     * Verifica se o Estouro tem forma de linha independente de orientação (line).
     * 
     * @return true se Estouro tem forma de linha
     */
    private boolean verificarLinha() {
        if (explodidas.size() != 4) {
            return false;
        }
        // 0123
        if (
                   verificarADireita(explodidas.get(0), explodidas.get(1))
                && verificarADireita(explodidas.get(1), explodidas.get(2))
                && verificarADireita(explodidas.get(2), explodidas.get(3))) {
            return true;
        }
        // 0
        // 1
        // 2
        // 3
        if (
                   verificarAbaixo(explodidas.get(0), explodidas.get(1))
                && verificarAbaixo(explodidas.get(1), explodidas.get(2))
                && verificarAbaixo(explodidas.get(2), explodidas.get(3))) {
            return true;
        }
        return false;
    }



}
