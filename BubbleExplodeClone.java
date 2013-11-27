import pucrs.alpro.*;
/**
 * 
 * @author marco.mangan@pucrs.br
 * @author marco.gonzales@pucrs.br
 */
public class BubbleExplodeClone {
    
    /** main
     * 
     * @param args
     */
    public static void main(String[] args) {
        Terminal.println("Bubble Explode Clone");
        Tabuleiro b = new Tabuleiro();
        int r;
        int c;
        do {
            Terminal.println(b);
            Terminal.print("Row:");
            r = Terminal.readInt();
            Terminal.print("Column:");
            c = Terminal.readInt();
            if (!b.validarLocal(r, c)) {
                Terminal.printf("Invalid position: %d %d", r, c);
            } else {
                b.explodirBolha(r, c);
            }
        } while (!b.verificarFim());
    }        
}
