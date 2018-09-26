
package parserfx;

import java.awt.Component;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author SABIT_KARAEV
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button button_open;
    @FXML
    private Button button_start;
    StringBuilder Text = new StringBuilder();
    FileChooser fileChooser = new FileChooser();
    private String FileName, DirName, FileData, Filter;
    @FXML
    private CheckBox CheckBox1;
    @FXML
    private CheckBox CheckBox2;
    @FXML
    private CheckBox CheckBox3;
    @FXML
    private CheckBox CheckBox4;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void button_openAction(ActionEvent event) {

         //Set title for FileChooser
      fileChooser.setTitle("Укажите файл для парсинга");
 
      // Set Initial Directory
      fileChooser.setInitialDirectory(new File("C:/Users/SABIT_KARAEV/Documents/NetBeansProjects/Instrumentalka/ParserFX"));
 
      // Add Extension Filters
      fileChooser.getExtensionFilters().addAll(//
              new FileChooser.ExtensionFilter("All Files", "*.*"), //
              new FileChooser.ExtensionFilter("TXT", "*.txt"));
      File file = fileChooser.showOpenDialog(button_open.getScene().getWindow());
       FileName = file.toString();
        DirName = fileChooser.getInitialDirectory()+ System.getProperty("file.separator");
          System.out.println(FileName);

        try {
            Scanner Input = new Scanner(new FileInputStream(FileName),"cp1251");
            while (Input.hasNextLine()){
                FileData += Input.nextLine();
            }
        }  catch (Exception ex) {
            button_start.setText("Ошибка!");
        }

    }

    @FXML
    private void button_startAction(ActionEvent event) {
        Filter = "";
        Filter += "[^";
        if (CheckBox1.isSelected()){
                Filter += "А-Яа-я";
        }

        if (CheckBox2.isSelected()){
                Filter += "\\s";
            }
        if (CheckBox3.isSelected()){
                Filter += "A-Za-z";
            }
        if (CheckBox4.isSelected()){
                Filter += "\\d";
            }

        Filter += "]";
        try {
         String result = FileData.replaceAll(Filter, "");
         PrintWriter Out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(DirName + "output.txt"),"cp1251"));
         Out.write(result);
         Out.flush(); 
         Out.close();
         button_start.setText("Готово!");
        } catch (Exception ex) {
         button_start.setText("Ошибка!!");
        }
    }
    
}
