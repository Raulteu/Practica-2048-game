package Exceptions;

@SuppressWarnings("serial")
public class noSave extends Excepciones{
	//Excepcion que se lanza cuando intentas guardar un juego ya existente pero no deseas sobreescribirlo
	public noSave(String e) {
		super(e);
	}

}
