package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import com.sun.glass.events.MouseEvent;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {
	
	Dictionary d;

	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbox;

    @FXML
    private TextArea txtTesto;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtWrong;

    @FXML
    private Label lblErrors;

    @FXML
    private Button btnClear;

    @FXML
    private Label lblSeconds;

    @FXML
    void doClear(ActionEvent event) {
    	txtWrong.clear();
    	txtTesto.clear();
    	lblErrors.setVisible(false);
    	lblSeconds.setVisible(false);
    }

    @FXML
    void doSpellCheck(ActionEvent event){
    	int errate = 0;
    	d.loadDictionary(cmbox.getValue());
    	
    	//????????????? va bene ?????????????????????????????????????????????? 
    	
    	double temp1 = System.nanoTime();
    	
    	List<String> myList = new ArrayList<String>(Arrays.asList(txtTesto.getText().split(" ")));
    	List<RichWord> temp = d.spellCheckText(myList);
    	
    	for(RichWord r : temp){
    		if (r.isCorretta() == false){
    			txtWrong.appendText(r.getParolaInserita() + "\n");
    			errate ++;
    		}		
    	}
    	
    	double temp2 = System.nanoTime();
    	lblSeconds.setText("Spell check completed in " + (temp2 - temp1) / 1e9 + " seconds");
    	
    	
    	if(errate > 0)
    		lblErrors.setText("The text contains " + errate + " errors");
    	else
    		lblErrors.setText("The text doesn't contain any errors");
    	

    }

    @FXML
    void initialize() {
        assert cmbox != null : "fx:id=\"cmbox\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtWrong != null : "fx:id=\"txtWrong\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblErrors != null : "fx:id=\"lblErrors\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblSeconds != null : "fx:id=\"lblSeconds\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        
        cmbox.getItems().addAll("English", "Italian");
        d = new Dictionary();
    }
}
