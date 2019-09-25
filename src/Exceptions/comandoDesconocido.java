package Exceptions;

@SuppressWarnings("serial")
public class comandoDesconocido extends Excepciones {
	//Excepcion que se lanza cuando pones un comando inexistente
	public comandoDesconocido(String e) {
		super(e);
	}
}
