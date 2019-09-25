package Exceptions;

@SuppressWarnings("serial")
public class tableroCargadoNoValido extends Excepciones{
	//Excepcion que se lanza cuando al ejecutar load el tablero del archivo es incorrecto
	public tableroCargadoNoValido(String e) {
		super(e);
	}

}
