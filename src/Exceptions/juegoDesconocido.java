package Exceptions;

@SuppressWarnings("serial")
public class juegoDesconocido extends Excepciones{
	//Excepcion que se lanza cuando pones Play seguido de un juego inexistente
	public juegoDesconocido(String e) {
		super(e);
	}
}
