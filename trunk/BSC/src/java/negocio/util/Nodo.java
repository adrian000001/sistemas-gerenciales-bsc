package negocio.util;

/**
 * Genera un Nodo del árbol de expresión
 * @author José Farfán
 * @author Jorge Merchán
 */
public class Nodo {
    public String valor;
    Nodo Sguiente;
    Nodo Derecha;
    Nodo Izquierda;
/**
 * Constructor de la clase
 */
    public Nodo() {
    }
/**
 * Constructor de la clase
 * @param valor Valor inicial del nodo
 */
    public Nodo(String valor) {
        this.valor = valor;
        this.Sguiente=null;
        this.Derecha=null;
        this.Izquierda=null;
    }

}
