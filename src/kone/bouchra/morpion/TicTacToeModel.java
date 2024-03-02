package kone.bouchra.morpion;

public class TicTacToeModel {
 /**
 * Taille du plateau de jeu (pour �tre extensible).
 */
 private final static int BOARD_WIDTH = 3;
 private final static int BOARD_HEIGHT = 3;
 /**
 * Nombre de pi�ces align�s pour gagner (idem).
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
 * Constructeur priv�.
 */
private TicTacToeModel() { ... }

 /**
 * @return la seule instance possible du jeu.
 */
 public static TicTacToeModel getInstance() {
return TicTacToeModelHolder.INSTANCE;
 }

 /**
 * Classe interne selon le pattern singleton.
 */
 private static class TicTacToeModelHolder {
 private static final TicTacToeModel INSTANCE =
 new TicTacToeModel();
 }

 public void restart() { ... }

 public final ObjectProperty<Owner> turnProperty() { ... }

 public final ObjectProperty<Owner> getSquare(int row, int column)
 { ... }

 public final BooleanProperty getWinningSquare(int row, int column)
 { ... }

/**
 * Cette fonction ne doit donner le bon r�sultat que si le jeu
 * est termin�. L�affichage peut �tre cach� avant la fin du jeu.
 *
 * @return r�sultat du jeu sous forme de texte */
 public final StringExpression getEndOfGameMessage() { ... }

 public void setWinner(Owner winner) { ... }

 public boolean validSquare(int row, int column) { ... }

public void nextPlayer() { ... }

 /**
 * Jouer dans la case (row, column) quand c�est possible.
 */
 public void play(int row, int column) { ... }

 /**
 * @return true s�il est possible de jouer dans la case
 * c�est-�-dire la case est libre et le jeu n�est pas termin�
 */
 public BooleanBinding legalMove(int row, int column) { ... }
public NumberExpression getScore(Owner owner) { ... }

 /**
 * @return true si le jeu est termin�
 * (soit un joueur a gagn�, soit il n�y a plus de cases � jouer)
 */
 public BooleanBinding gameOver() { ... }
 }
