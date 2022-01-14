
/**
 * Interfaccia per le classi che si occupano dei Players "Forza 4"
 * Usando questa interfaccia si è sicuri che qualsiasi Handler sia in grado di usare il programma e
 * interfacciarsi con i salvataggi dei giocatori e la loro modifica.
 * @author Argento Simone
 * @version 1.00 11 Jan 2022
 */
public interface IPlayerable {
	public boolean checkPlayer(String playerUsername);
}
