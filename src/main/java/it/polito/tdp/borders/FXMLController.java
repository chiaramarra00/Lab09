
package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="boxStati"
    private ComboBox<Country> boxStati; // Value injected by FXMLLoader

    @FXML // fx:id="doRicerca"
    private Button doRicerca; // Value injected by FXMLLoader

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	txtResult.clear();
    	String input = txtAnno.getText();
    	int anno = 0;
    	try {
    		anno = Integer.parseInt(input);
    	} catch (NumberFormatException e) {
    		e.printStackTrace();
    	}
    	if (anno<1816 || anno>2016) {
    		txtResult.setText("Inserire un anno compreso tra il 1816 e il 2016.");
    	}
    	model.creaGrafo(anno);
    	boxStati.getItems().setAll(model.getCountries());
    	List<String> elencoStati = model.elencoStati();
    	for (String s : elencoStati) {
    		txtResult.appendText(s+"\n");
    	}
    	txtResult.appendText("Numero componenti connesse: "+model.nComponentiConnesse());

    }
    
    @FXML
    void doRicerca(ActionEvent event) {
    	txtResult.clear();
    	Country stato = boxStati.getValue();
    	Map<Country,Country> alberoInverso = model.visitaGrafo(stato);
    	for (Country c : alberoInverso.keySet())
    		txtResult.appendText(c +"\n");
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
