
package sourceinformes;

/**
 *
 * @author Oak
 */
public class Guardar {

    public void saveObjetivosGlobales() {
        Conexion conexion = new Conexion();
        String fuente = System.getProperty("user.dir") + "/src/java/informes/EstrategiasGlobales.jasper";
        conexion.resportesPDF(fuente, "EstrategiasGlobales");
    }

    public void saveBSC() {
        Conexion conexion = new Conexion();
        String fuente = System.getProperty("user.dir") + "/src/java/informes/BSC.jasper";
        conexion.resportesPDF(fuente, "BSC");
    }

    public void saveColaboradores() {
        Conexion conexion = new Conexion();
        String fuente = System.getProperty("user.dir") + "/src/java/informes/Colaboradores.jasper";
        conexion.resportesPDF(fuente, "Colaboradores");
    }

    public void saveObjetivosEstrategicos() {
        Conexion conexion = new Conexion();
        String fuente = System.getProperty("user.dir") + "/src/java/informes/ObjetivosEstrategicos.jasper";
        conexion.resportesPDF(fuente, "ObjetivosEstrategicos");
    }

    public void saveActividades() {
        Conexion conexion = new Conexion();
        String fuente = System.getProperty("user.dir") + "/src/java/informes/Actividades.jasper";
        conexion.resportesPDF(fuente, "Actividades");
    }
//    public static void main(String[] args) {
//        Guardar guardar = new Guardar();
//        guardar.saveObjetivosEstrategicos();
//    }
}
