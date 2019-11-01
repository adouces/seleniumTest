
package seleniumtest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class metodos {
    public WebDriver driver = null;
    BufferedReader contenido;
    FileReader archivo;
    File errores;
    FileWriter escritura;
    String asercion, texto;
    String[] datosTest;
    
    public void cartel (String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    public void cartel (int mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    public void sleepy(int duration){
        try {
            Thread.sleep(duration*1000);
        } catch (InterruptedException ex) {
            cartel("Error en el Sleep.");
        }
    }
    public void loadDriver(String driverName){
        System.setProperty("webdriver.chrome.driver", driverName);
        driver = new ChromeDriver();
        
    }
    public void fileRead(String file){
        try {
            archivo = new FileReader(file);
            contenido = new BufferedReader(archivo);
        } catch (FileNotFoundException ex) {
            cartel("No se encontro: " + ex);
        }   
    }
    public void callWeb(String url){
        driver.get(url);
    }
    public void screen (String varSize){
        switch(varSize){
            case "full":
                driver.manage().window().fullscreen();
                break;
            case "max":
                driver.manage().window().maximize();
                break;
        }
    }
    public void closeDriver(){
        driver.close();
    }
    public void asercion(String estado){
        asercion = driver.getPageSource();
        if(asercion.indexOf(estado)>=0){
            
        }else{
            errores = new File("Error.txt");
            try {
                escritura = new FileWriter(errores,true);
                escritura.write("Error en"+"\n");
                escritura.close();
            } catch (IOException ex) {
                Logger.getLogger(metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    public void ejecutarTest(){
        
        for (int i = 0; i < 3; i++) {
            try {
                texto = contenido.readLine();
                datosTest = datosArray();
                driver.findElement(By.id("usuario")).sendKeys(datosTest[0]);
                driver.findElement(By.id("clave")).sendKeys(datosTest[1]);
            } catch (IOException ex) {
            }
            
            driver.findElement(By.id("Ingresar")).click();
            sleepy(3);
            asercion("Ingreso INCorecto");
            driver.findElement(By.id("Volver")).click();    
            }
        }
    public String[] datosArray(){
        String[] dataS = texto.split(",");
        return dataS;
    }
}
