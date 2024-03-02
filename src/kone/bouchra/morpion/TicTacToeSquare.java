package kone.bouchra.morpion;

public class TicTacToeSquare {

	/**
	 * 3 * Taille du plateau de jeu (pour être extensible). 4
	 */
	private final static int BOARD_WIDTH = 3;
	private final static int BOARD_HEIGHT = 3;
	/**
	 * Nombre de pièces alignés pour gagner (idem).
	 */
	private final static int WINNING_COUNT = 3;
	/**
	 * Joueur courant.
	 */
	private final ObjectProperty<Owner> turn = new SimpleObjectProperty<>(Owner.FIRST);
	/**
	 * Vainqueur du jeu, NONE si pas de vainqueur.
	 */
	private final ObjectProperty<Owner> winner = new SimpleObjectProperty<>(Owner.NONE);
	/**
	 * Plateau de jeu.
	 */
	private final ObjectProperty<Owner>[][] board;
	/**
	 * Positions gagnantes.
	 */
	private final BooleanProperty[][] winningBoard;

	/**
	 * Constructeur privé.
	 */
	 private TicTacToeModel() { ... }

	/**
	 * @return la seule instance possible du jeu.
	 */
	 public static TicTacToeModel getInstance() {

}
