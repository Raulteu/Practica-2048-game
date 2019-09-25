package Exceptions;

@SuppressWarnings("serial")
public class direccionDesconocida  extends Excepciones{
	//Excepcion que se lanza cuando pones move seguido de alguna direccion inexistente
	public direccionDesconocida(String e) {
		super(e);
	}

}