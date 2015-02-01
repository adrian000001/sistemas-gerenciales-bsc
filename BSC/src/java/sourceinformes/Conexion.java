package sourceinformes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Oak
 */
public class Conexion {

    Connection conexion;
    Statement instrucion;

    public Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost/bsc";
            String usuarioDB = "root";
            String passwordDB = "ucuenca";
            conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB);
            instrucion = conexion.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
  
    public void resportesPDF(String ruta, String nombre) {
        try {
            String rutaInforme = ruta;
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, null, conexion);
            JasperExportManager.exportReportToPdfFile(informe, "../temp/" + nombre + ".pdf");
            //JasperViewer ventanavisor = new JasperViewer(informe, false);
            //ventanavisor.setTitle("INFORME");
            //ventanavisor.setVisible(false);
        } catch (JRException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
