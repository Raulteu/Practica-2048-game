package Exceptions;

@SuppressWarnings("serial")
public class fileNameInvalid extends Excepciones {
	//Excepcion que se lanza cuando se pone Load seguido de un archivo inexistente
	public fileNameInvalid(String e) {
		super (e);
	}
}
