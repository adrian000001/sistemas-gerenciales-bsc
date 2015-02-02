package negocio.util;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import modelo.Detallehistorial;

/**
 * Genera un árbol de expresión a partir de una cadena de caracteres y la resuelve
 * @author José Farfán
 * @author Jorge Merchán
 */
public class Arbol {
    private Nodo Raiz;
    private String res;
    private int N_Datos;
    private String Lista[][];
    private int n;
    private List<String>terminos;
    private List<Detallehistorial>valores;
    //private JFrame ventana=new JFrame();
//Contructor
    public Arbol() {
        this.Raiz=null;
    }
/**
 * Contructor
 * @param valor- valor inicial del arbol
 */
    public Arbol(String valor) {
        Raiz=new Nodo(valor);
    }
/**
 * Forma el arbol con la cadena ingresada
 */
    public void Formar_Arbol(){
        Raiz=Terminos(Raiz.valor);
        Armar(Raiz);
    }
/**
 * Forma un subarbol con los nodo hoja y existe una expresion
 * @param a Nodo que se formara en un subarbol
 */
    private void Armar (Nodo a){
        if (a!=null) {
            if (a.Derecha!=null)
                if (a.Derecha.Derecha==null)a.Derecha=Terminos(a.Derecha.valor);
            if (a.Izquierda!=null)
                if (a.Izquierda.Izquierda==null)a.Izquierda=Terminos(a.Izquierda.valor);
            Armar(a.Izquierda);
            Armar(a.Derecha);
        }
    }
/**
 * Divide la expresion en terminos y signos y los almacena en forma de lista
 * @param cadena    La cadena que sera dividida
 * @return  La lista de terminos y signos
 */
    private Nodo Terminos(String cadena){
        cadena=Cambio(cadena);//Elimina algun signo que se encuentre frente a un parentesis
        Nodo subarbol=null;
        Nodo temp;
        int i=0;//Contador de posicionnes
        String termino="";
        int parentesis=0;//Contador de Parentesis
        while(i<cadena.length()){
////////////////vuelve un nodo independiente todo valor entre parentesis
            if(cadena.charAt(i)=='('){
                do{
                    if(cadena.charAt(i)=='(')parentesis++;
                    if(cadena.charAt(i)==')')parentesis--;
                    termino=termino+cadena.charAt(i);
                    i++;
                }while(parentesis!=0);
/////////////////////////Guarda el Termino y el signo que continua si existe
                if (subarbol==null){
                    subarbol=new Nodo(termino.substring(1, termino.length()-1));
                    if (i!=cadena.length())subarbol.Sguiente=new Nodo (String.valueOf(cadena.charAt(i)));
                }else{
                    temp=subarbol;
                    while(temp.Sguiente!=null)temp=temp.Sguiente;
                    temp.Sguiente=new Nodo(termino.substring(1, termino.length()-1));
                    if (i!=cadena.length()){temp=temp.Sguiente;temp.Sguiente=new Nodo (String.valueOf(cadena.charAt(i)));}
                }
                termino="";
            }else{//Guarda terminos que no se encuentren entre parentasis y los signos que lo separan
                termino=termino+cadena.charAt(i);
                if(i==cadena.length()-1){
                if (subarbol==null){
                    subarbol=new Nodo(termino);termino="";
                }else{
                    temp=subarbol;
                    while(temp.Sguiente!=null)temp=temp.Sguiente;
                    temp.Sguiente=new Nodo(termino);termino="";
                }}else{
                    if(cadena.charAt(i+1)=='+'||cadena.charAt(i+1)=='-'||cadena.charAt(i+1)=='*'||cadena.charAt(i+1)=='/'){
                    i++;
                    if (subarbol==null){
                        subarbol=new Nodo(termino);termino="";
                        subarbol.Sguiente=new Nodo (String.valueOf(cadena.charAt(i)));
                    }else{
                        temp=subarbol;
                        while(temp.Sguiente!=null)temp=temp.Sguiente;
                        temp.Sguiente=new Nodo(termino);termino="";
                        temp=temp.Sguiente;temp.Sguiente=new Nodo (String.valueOf(cadena.charAt(i)));
                    }
                    }
                }
                }
            i++;
            }
        subarbol=SubArbol(subarbol);//Transforma la lista a un arbol
        return subarbol;
        }
/**
 * Forma un arbol a partir de una lista
 * @param Arbol La lista que se transformara en arbol
 * @return  El arbol resultante
 */
    private Nodo SubArbol(Nodo Arbol){
        Nodo temp=Arbol;
        Nodo anterior=null;
            while (temp.Sguiente!=null){//Forma subarboles con multiplicaciones y diviciones de la lista
                if(temp.Sguiente.valor.equals("*")||temp.Sguiente.valor.equals("/")){
                    Nodo aux=temp.Sguiente;
                    aux.Izquierda=temp;
                    aux.Derecha=aux.Sguiente;
                    aux.Sguiente=aux.Derecha.Sguiente;
                    aux.Izquierda.Sguiente=null;
                    aux.Derecha.Sguiente=null;
                    if (anterior!=null)anterior.Sguiente=aux;
                    if (anterior==null)Arbol=aux;
                    if(aux.Sguiente!=null)temp=aux.Sguiente;else temp=aux;
                    anterior=aux;
                }else {anterior=temp;temp=temp.Sguiente;}
            }
//Forma subarboles con sumas y restas de la lista
                temp=Arbol;
                while(Tam_Lista(temp)>1){
                    Nodo aux=temp.Sguiente;
                    aux.Izquierda=temp;
                    aux.Derecha=aux.Sguiente;
                    aux.Sguiente=aux.Derecha.Sguiente;
                    aux.Izquierda.Sguiente=null;
                    aux.Derecha.Sguiente=null;
                    Arbol=aux;
                    temp=Arbol;
                }
        return temp;
    }
/**
 * Calcula el resultado del arbol
 * @return La respuesta en Forma de String
 */
    public String Calcular(){
        N_Datos=0;
        N_Datos(Raiz);
        while (N_Datos>1){
            Resolver_Siguiente(Raiz);
            N_Datos=0;
            N_Datos(Raiz);
        }
        return Raiz.valor;
    }
/**
 * Resulve recursivamente las operaciones de la parte inferior del arbol
 * @param a Arbol que se desea resolver
 */
    private void Resolver_Siguiente(Nodo a){
        if (a!=null) {
            if (a.Derecha!=null && a.Izquierda!=null)
                if (a.Derecha.Derecha==null && a.Izquierda.Izquierda==null){//Si cumple con esta condiciones es un subarbol de la parte inferior que se debe resolver
                    a.valor=Resolver (a.Izquierda.valor,a.valor,a.Derecha.valor);
                    a.Derecha=null;a.Izquierda=null;
                }
            if (a.valor.equals("Error")){//Si el valor del nodo es Error, hubo una division para cero y no existe valor de respuesta
                Raiz.valor="Error";//El valor de Raiz es Error y termina el proceso
                Raiz.Derecha=null;Raiz.Izquierda=null;
                a=Raiz;
            }
            Resolver_Siguiente(a.Izquierda);
            Resolver_Siguiente(a.Derecha);
        }
    }
/**
 * Resuelve una operacion sencilla de dos terminos y retorna la respuesta
 * @param v1 El primer término
 * @param s El signo de la operacion
 * @param v2 El segundo Termino
 * @return La respuesta en forma de String
 */
    private String Resolver (String v1,String s,String v2){
        Double r=0.0;
        if (s.equals("+"))r=Double.parseDouble(v1)+Double.parseDouble(v2);
        if (s.equals("-"))r=Double.parseDouble(v1)-Double.parseDouble(v2);
        if (s.equals("*"))r=Double.parseDouble(v1)*Double.parseDouble(v2);
        if (s.equals("/") && Double.parseDouble(v2)!=0.0)r=Double.parseDouble(v1)/Double.parseDouble(v2);
        if (s.equals("/") && Double.parseDouble(v2)==0.0)
            return "Error";
        else return r.toString();
    }
    /**
     * Retorna el numero de nodos de una lista de acuerdo al campo siguiente
     * @param lista La lista a contar
     * @return el numero de elementos
     */
    private int Tam_Lista(Nodo lista){
        int r=0;
        Nodo temp=lista;
        while(temp!=null){
            temp=temp.Sguiente;
            r++;
        }
        return r;
    }

    /**
     * Elimina o cambia el primer signo de la cadena por su equivalente si esta al inicio de la cadena
     */
    private String Cambio(String cadena){
        if (cadena.charAt(0)=='+')cadena=cadena.substring(1);
        if (cadena.charAt(0)=='-' && cadena.charAt(1)=='('){
            cadena=cadena.substring(1);
            cadena="-1*"+cadena;
        }
        return cadena;
    }
    /**
 * Guarda los datos del arbol al recorrido InOrden
 * @return Una cadena con los datos del arbol
 */
    public String InOrden(){
        res="";
        Orden(Raiz);
        return res;
    }
    /**
     * Guarda los datos recurcivamente "InOrden"
     * @param a recibe el arbol a que va a ser recorrido
     */
    private void Orden (Nodo a){
        if (a!=null) {
        Orden(a.Izquierda);
        res=res+a.valor;
        Orden(a.Derecha);
        }
    }
    /**
 * Guarda los datos del arbol al recorrido PreOrden
 * @return Una cadena con los datos del arbol
 */
    public String PreOrden(){
        res="";
        Pre(Raiz);
        return res;
    }
    
    public void reemplazarValores(List<Detallehistorial> valores){
        System.out.println("Reeplazo");
        this.valores=valores;
        this.reemplazar(Raiz);
    }
    
    public List listarTerminos(){
        terminos=new ArrayList<>();
        this.getTerminos(Raiz);
        return terminos;
    }
    /**
     * Guarda los datos recurcivamente "PreOrden"
     * @param a recibe el arbol a que va a ser recorrido
     */
    private void Pre (Nodo a){
        if (a!=null) {
        res=res+a.valor;
        Pre(a.Izquierda);
        Pre(a.Derecha);
        }
    }
    
    private void reemplazar (Nodo a){
        if (a!=null) {
            for (int i=0;i<valores.size();i++){
                if (a.valor.equals(valores.get(i).getIdcomponenteformula().getDescripcion())){
                    a.valor=String.valueOf(valores.get(i).getValor().doubleValue());
                }
            }
        reemplazar(a.Izquierda);
        reemplazar(a.Derecha);
        }
    }
    
    private void getTerminos (Nodo a){
        if (a!=null) {
            if (!(a.valor.trim().equals("+")||a.valor.trim().equals("-")||a.valor.trim().equals("*")||a.valor.trim().equals("/")))
                terminos.add(a.valor);
        getTerminos(a.Izquierda);
        getTerminos(a.Derecha);
        }
    }
    /**
 * Guarda los datos del arbol al recorrido PostOrden
 * @return Una cadena con los datos del arbol
 */
    public String PostOrden(){
        res="";
        Post(Raiz);
        return res;
    }
    /**
     * Guarda los datos recurcivamente "PostOrden"
     * @param a recibe el arbol a que va a ser recorrido
     */
    private void Post (Nodo a){
        if (a!=null) {
        Post(a.Izquierda);
        Post(a.Derecha);
        res=res+a.valor;
        }
    }
    /**
     * Muestra la representacion grafica del arbol formado
     */
    public void Grafica(){
//        Grafica g=new Grafica();
//        N_Datos=0;
//        N_Datos(Raiz);
//        n=0;
//        Lista=new String[N_Datos][5];
//        Listar(Raiz,550,20,256);
//        g.setLista(Lista);
//        g.setN(N_Datos);
//        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        ventana.add(g);
//        ventana.setSize(1150, 700);
//        ventana.setLocation(210, 1);
//        ventana.setVisible(true);
    }

    private void N_Datos(Nodo a){
        if (a!=null) {
            N_Datos(a.Izquierda);
            N_Datos++;
            N_Datos(a.Derecha);
        }
    }
    private void Listar(Nodo a,int x,int y,int esp){
        if (a!=null) {
            String hoja="f";//indica si el nodo es hoja 1 verdadero 0 falso
            if (a.Derecha==null)hoja="v";
            Lista[n][0]=String.valueOf(x);Lista[n][1]=String.valueOf(y);Lista[n][2]=a.valor;Lista[n][3]=hoja;Lista[n][4]=String.valueOf(esp);n++;
            Listar(a.Izquierda,x-(esp),y+60,esp/2);
            Listar(a.Derecha,x+(esp),y+60,esp/2);
        }
    }
    /**
     * Oculta la grafica del árbol
     */
    public void Ocultar(){
//        ventana.setVisible(false);
    }
}
