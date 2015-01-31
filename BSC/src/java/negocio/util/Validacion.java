package negocio.util;
/**
 * Comprueba la valides de una expresión matemática requerida
 * @author José Farfán
 * @author Jorge Merchán
 */
public class Validacion {
    private String Cadena;

    public Validacion(String Cadena) {
        this.Cadena = Cadena;
    }
    /**
     * Verifica si la cadena es válida para realizar el calculo
     * @return  true par cadenas validas
     *          valse para cadenas erroneas
     */
    public boolean Exp_Valida(){
        boolean resp=true;//indica si la cadena es valida o no
        char actual;//guarda el caracter que se esta revisando
        char anterior;//guarda el caracter anterior
        char siguiente;//guarda el siguiente caracter
        int ascii;//guarda el valor ascii del caracter
        int con_par=0;//lleva la cuenta de los parentesis
        int i=0;
        while(i<Cadena.length() && resp){
            //guarda los caracteres en su respectiva variable
            actual=Cadena.charAt(i);
            ascii=(int)actual;
            if (i!=0)anterior=Cadena.charAt(i-1);else anterior=' ';
            if (i!=Cadena.length()-1)siguiente=Cadena.charAt(i+1);else siguiente=' ';
            //controla que los caracteres sean los permtidos
            //if(!((ascii>39 && ascii<58)&&ascii!=44))resp=false;
            //controla la cantidad de parentesis
            //controla que la apertura del parentesis sea correcta
            if(ascii==40){
                con_par++;
                if (i==1)if ((int)anterior==42 ||(int)anterior==47)resp=false;
                if (i!=0)if (!(Signo(anterior)||(int)anterior==40))resp=false;
                if (i!=Cadena.length()-1)if (!((int)siguiente==40||(int)siguiente==43||(int)siguiente==45||(int)siguiente==46||Numero(siguiente)))resp=false;
            }
            //controla que el cierre del parentesis sea correcto
            if(ascii==41){
                con_par--;
                if (i!=0)if (!(Numero(anterior)||(int)anterior==46||(int)anterior==41))resp=false;
                if (i!=Cadena.length()-1)if (!((int)siguiente==41||Signo(siguiente)))resp=false;
            }
            //controla que los signos * y / esten bien ubicado
            if(ascii==42 ||ascii==47){
                if (i!=0)if (!(Numero(anterior)||(int)anterior==46||(int)anterior==41))resp=false;
                if (i!=Cadena.length()-1)if (!((int)siguiente==40||Numero(siguiente)||(int)siguiente==46||(int)siguiente==43||(int)siguiente==45))resp=false;
            }
            //controla que los signos + y - esten bien ubicado
            if(ascii==43 ||ascii==45){
                if (i!=0)if (!(Numero(anterior)||(int)anterior==41||(int)anterior==46||(int)anterior==42||(int)anterior==47))resp=false;
                if (i!=Cadena.length()-1)if (!((int)siguiente==40||Numero(siguiente)||(int)siguiente==46))resp=false;
            }
            //controla que el punto este bien ubicado
            if(ascii==46){
                if (i!=0)if (!(Numero(anterior)||Numero(siguiente)))resp=false;
            }
            i++;
        }
        //verifica la cantidad de parentesis
        if(con_par!=0 && i==Cadena.length())resp=false;
        return resp;
    }
    /**
     * Verifica si un caracter es un signo permitido en a cadena
     * @param c-caracter que será comparado
     * @return  true para un signo valido
     *          false para otros caracteres
     */
    private boolean Signo(char c){
        if ((int)c==42 ||(int)c==43 ||(int)c==45 ||(int)c==47)return true;
        else return false;
    }
    /**
     * Verifica si un caracter es un signo digito
     * @param c-caracter que será comparado
     * @return  true cuando es un digito
     *          false para otros caracteres
     */
    private boolean Numero(char c){
        return true;
    }

}
