/**
 * A classe <code>Tabuleiro</code> representa a área do jogo.
 * 
 * O Tabuleiro tem 13 linhas e 10 colunas. 
 * Cada célula pode estar vazia ou
 * pode estar ocupada por uma Bolha.
 * 
 * @author marco.mangan@pucrs.br
 * @author marco.gonzales@pucrs.br
 */
public class Tabuleiro {
    /**
     * Escore acumulado de um jogo.
     */
    private int escore;

    /**
     * Área do jogo.
     */
    private Bolha[][] campo;

    /**
     * Estouro anterior.
     */
    private Estouro estouroAnterior;

    /**
     * contador de Combo.
     */
    private int combo;

    /**
     * Cria um tabuleiro gerado randomicamente.
     */
    public Tabuleiro() {
        super();
        escore = 0;
        campo = new Bolha[13][10];
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo[0].length; j++) {
                campo[i][j] = new Bolha(this, i, j);
            }
        }
        estouroAnterior = null;
        combo = 1;
    }

    /**
     * Atualiza o Tabuleiro após uma Jogada.
     * 
     * Tenta empurrar para baixo todas as Bolhas,
     * exceto aquelas localizadas na última linha inferior.
     */
    private void atualizar() {
        boolean movida = true;
        while(movida) {
            movida = false;
            for (int i = 0; i < campo.length - 1; i++) {
                for (int j = 0; j < campo[0].length; j++) {
                    Bolha b = campo[i][j];
                    if (b != null) {
                        if (b.moverParaBaixo()) {
                            movida = true;
                        }
                    }
                }
            }
        }
    }

    /**
     * Analisa Bolhas a partir de uma String,
     * substituindo o conteúdo do Tabuleiro.
     * 
     * @param String s
     */
    public void analisarString(String s) {
        // pode haver exceção
        escore = 0;
        campo = new Bolha[13][10];
        int p = 0;
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo[0].length; j++) {
                if (p >= s.length()) {
                    return;
                }
                char cor = s.charAt(p++);
                if (cor != '.') {
                    campo[i][j] = new Bolha(this, i, j, cor);
                }
            }
        }
        estouroAnterior = null;
        combo = 1;
    }

    /**
     * Retorna uma <code>Bolha</code> do Tabuleiro.
     * 
     * @param int linha
     * @param int coluna
     * @return null se não há Bolha aqui
     */
    public Bolha getLocal(int linha, int coluna) {
        if (!validarLocal(linha, coluna)) {
            return null;
        }
        return campo[linha][coluna];
    }

    /**
     * Retorna uma Bolha acima da <code>Bolha</code> atual.
     * 
     * @param Bolha bolha
     * @return null if there is no bolha above
     */
    public Bolha getLocalAcima(Bolha bolha) {
        if (!validarLocal(bolha.getLinha() - 1, bolha.getColuna())) {
            return null;
        }
        return campo[bolha.getLinha() - 1][bolha.getColuna()];
    }

    /**
     * Retorna a Bolha abaixo da <code>Bolha</code> atual.
     * 
     * @param Bolha bolha
     * @return null, se não ha Bolha abaixo
     */
    public Bolha getLocalAbaixo(Bolha bolha) {
        if (!validarLocal(bolha.getLinha() + 1, bolha.getColuna())) {
            return null;
        }
        return campo[bolha.getLinha() + 1][bolha.getColuna()];
    }

    /**
     * Retorna a Bolha à esquerda da <code>Bolha</code> atual.
     * 
     * @param Bolha bolha
     * @return null, se não há bolha à esquerda
     */
    public Bolha getLocalAEsquerda(Bolha bolha) {
        if (!validarLocal(bolha.getLinha(), bolha.getColuna() - 1)) {
            return null;
        }
        return campo[bolha.getLinha()][bolha.getColuna() - 1];
    }

    /**
     * Retorna a Bolha à direita da <code>Bolha</code> atual.
     * 
     * @param Bolha bolha
     * @return null, se não há Bolha à direita
     */
    public Bolha getLocalADireita(Bolha bolha) {
        if (!validarLocal(bolha.getLinha(), bolha.getColuna() + 1)) {
            return null;
        }
        return campo[bolha.getLinha()][bolha.getColuna() + 1];
    }

    /**
     * Verifica se linha e coluna são válidas no Tabuleiro
     * 
     * @param int linha
     * @param int coluna
     * @return true se linha e coluna são coordenadas válidas.
     */
    public boolean validarLocal(int linha, int coluna) {
        return linha >= 0 && linha < campo.length && coluna >= 0
                    && coluna < campo[0].length;
    }

    /**
     * Remove uma Bolha do Tabuleiro.
     * 
     * @param Bolha bolha
     */
    public void remover(Bolha bolha) {
        // Pode haver exceção: bolha null
        campo[bolha.getLinha()][bolha.getColuna()] = null;
    }

    /**
     * Retorna o escore acumulado.
     * 
     * @return escore acumulado
     */
    public int getEscore() {
        return escore;
    }

    /**
     * Explode uma Bolha na linha i, coluna j.
     * 
     * @param i
     * @param j
     */
    public void explodirBolha(int i, int j) {
        Bolha b = campo[i][j];
        if (b == null) {
            return;
        }

        Estouro estouro = b.explodir();
        estouroAnterior = estouro;
        escore = escore + estouro.getEscore();
        
        // TODO: K L M
        /* K. (1,0 pt) realizar a contagem de combo, conforme o jogo original;
         * L. (1,0 pt) manter placar com nome e pontuação dos cinco melhores jogadores.
         * M. (1,0 pt) adicionar novas linhas de bolhas durante o jogo, conforme o jogo original*/
        
        atualizar();
    }
    
    
    /**
     * Retorna multiplicador referente a combo.
     * 
     * @return combo
     */
    public int getCombo() {
        return combo;
    }

    /**
     * Retorna nome da forma anterior.
     * 
     * @return String
     */
    public String getFormaAnterior() {
        if (estouroAnterior == null) {
            return "NONE";
        } else {
            return estouroAnterior.getForma();
        }
    }

    /**
     * Move a Bolha inferior, no Tabuleiro.
     * 
     * @param Bolha bolha
     * @return
     */
    public boolean moverParaBaixo(Bolha bolha) {
        // Pode haver exceção: bolha null
        Bolha inferior = getLocalAbaixo(bolha);
        if (inferior != null) {
            return false;
        }
        if (!validarLocal(bolha.getLinha() + 1, bolha.getColuna())) {
            return false;
        }
        campo[bolha.getLinha()][bolha.getColuna()] = null;
        campo[bolha.getLinha() + 1][bolha.getColuna()] = bolha;
        return true;
    }

    /**
     * Verifica se finalizou o jogo.
     * 
     * O jogo finaliza quando o Tabuleiro ficar vazio ou
     * quando só houver Bolhas isoladas (sem adjacentes).
     * 
     * @return true, se o jogo finalizou.
     */
    public boolean verificarFim() {
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo[0].length; j++) {
                Bolha b = campo[i][j];
                if (b != null) {
                    if (!b.verificarIsolada()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String s = String.format("Escore: %d\n", escore);
        s += String.format("Forma anterior: %s\n", getFormaAnterior());
        s += "\n    ";
        for (int j = 0; j < campo[0].length; j++) {
            s += String.format("%d", j);
        }
        s += "\n\n";
        for (int i = 0; i < campo.length; i++) {
            s += String.format(" %2d ", i);

            for (int j = 0; j < campo[0].length; j++) {
                s += String.format("%s", campo[i][j] == null ? "."
                : campo[i][j]);
            }
            s += String.format(" %-2d\n", i);
        }

        s += "\n    ";
        for (int j = 0; j < campo[0].length; j++) {
            s += String.format("%d", j);
        }
        s += "\n";
        return s;
    }    
    
}
