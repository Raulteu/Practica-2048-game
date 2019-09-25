package control;

import Exceptions.juegoDesconocido;
import Rules.GameRules;
import Rules.Rules2048;
import Rules.RulesFib;
import Rules.RulesInverse;


public enum GameType {
	ORIG("2048, original version", "original", new Rules2048()),
	FIB("2048, Fibonacci version", "fib", new RulesFib()),
	INVERSE("2048, inverse version", "inverse", new RulesInverse());

	private String friendlyName;
	private String name;
	private GameRules rules;

	private GameType(String friendly, String name, GameRules rules) {
		this.friendlyName = friendly;
		this.name = name;
		this.rules = rules;
	}
	
	
	//Usar en PlayCommand en parse y en Game en load
	public static GameType parse(String param) throws juegoDesconocido {
		for (GameType gameType: GameType.values()) {
			if (gameType.name.equalsIgnoreCase(param))
				return gameType;
		}
		throw new juegoDesconocido("Juego desconocido. (Juegos validos: fib, inverse, original)");
	}
	
	//Usar en PlayCommand para construir el mensaje de ayuda y en parse para mensaje de exception
	public static String externaliseAll() {
		String s = "";
		for (GameType type: GameType.values())
			s = s + " " + type.name + ",";
		return s.substring(1, s.length() - 1);
	}

	
	//Usar en Game para construir un objeto y cuando ejecuta play command
	public GameRules getRules() {
		return rules;
	}
		
	//Usar en PlayCommand y LoadCommand en parse.
	public String toString() {
		return friendlyName;
	}

}
