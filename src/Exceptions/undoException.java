package Exceptions;

@SuppressWarnings("serial")
public class undoException extends Excepciones{
	//Excepcion que se lanza cuando no se puede realizar el comando Undo
	public undoException(String e) {
		super(e);
	}

}
