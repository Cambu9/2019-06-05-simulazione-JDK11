/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.crimes;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.crimes.model.District;
import it.polito.tdp.crimes.model.Model;
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

    @FXML // fx:id="boxAnno"
    private ComboBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxMese"
    private ComboBox<Integer> boxMese; // Value injected by FXMLLoader

    @FXML // fx:id="boxGiorno"
    private ComboBox<Integer> boxGiorno; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaReteCittadina"
    private Button btnCreaReteCittadina; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaReteCittadina(ActionEvent event) {

    	txtResult.clear();
    	Object anno;
    	anno = boxAnno.getValue();
    	if(anno == null) {
    		txtResult.appendText("Devi selezionare un anno");
    		return;
    	}
    	txtResult.appendText(model.creaGrafo((int) anno) + "\n");
    	List<District> distretti = new ArrayList<District>();
    	distretti = model.getVertici();
    	for(District d: distretti) {
    		txtResult.appendText("Distanza da " + d + ": " + model.distrettiAdiacenti(d).toString() + "\n");
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	Object anno;
    	anno = boxAnno.getValue();
    	Object mese;
    	mese = boxMese.getValue();
    	Object giorno;
    	giorno = boxGiorno.getValue();
    	int agenti;
    	if(mese == null) {
    		txtResult.appendText("Devi selezionare un mese");
    		return;
    	}
    	if(giorno== null) {
    		txtResult.appendText("Devi selezionare un giorno");
    		return;
    	}
    	boolean controllo;
    	controllo = controllo((int)mese, (int)giorno);
    	if(controllo = false) {
    		return;
    	}
    	try {
    		agenti = Integer.parseInt(txtN.getText());
    	}catch(NumberFormatException e) {
    		txtResult.appendText("Devi inserire un numero N intero");
    		return;
    	}
    	
    }

    private boolean controllo(int mese, int giorno) {
    	if(mese == 4 || mese == 6 || mese == 9 || mese == 11) {
    		if(giorno == 31) {
    			txtResult.appendText("Questo mese ha 30 giorni!");
    			return false;
    		}		
    	}else if(mese == 2) {
    		if(boxAnno.getValue() == 2016) {
    			if(giorno == 30 || giorno == 31) {
    				txtResult.appendText("Il 2016 era un anno bisestile, quindi febbraio ha al masimo 29 giorni");
    				return false;
    			}
    		}else if(giorno == 29 || giorno == 30 || giorno == 31) {
    			txtResult.appendText("Febbraio ha solo 28 giorni");
    			return false;
    		}
    	}
		return true;
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxGiorno != null : "fx:id=\"boxGiorno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaReteCittadina != null : "fx:id=\"btnCreaReteCittadina\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	boxAnno.getItems().addAll(model.getAnni());
    	for(int i=0; i<12; i++) {
    		boxMese.getItems().add(i+1);
    	}
    	for(int i=0; i<31; i++) {
    		boxGiorno.getItems().add(i+1);
    	}
    }
}
