package org.hbrs.se1.ws25.exercises.uebung1.view;

import org.hbrs.se1.ws24.exercises.uebung1.control.Translator;

public class Client {

	/**
		 * Methode zur Ausgabe einer Zahl auf der Console
		 * (auch bezeichnet als CLI, Terminal)
		 *
		 */
		 void display( int aNumber ){
			// In dieser Methode soll die Methode translateNumber
			// mit dem übergegebenen Wert der Variable aNumber
			// aufgerufen werden.
			//
			// Strenge Implementierung (nur) gegen das Interface Translator gewuenscht!

			 // Translator translator = TranslatorFactory.createGermanTranslator();
			 String result = this.translator.translateNumber(1);

			 System.out.println("Das Ergebnis der Berechnung: " +
					"[das Ergebnis an dieser Stelle]" + result );
		 }
}





