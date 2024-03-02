package kone.bouchra.morpion;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.NumberExpression;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class TicTacToeModel {
 /**
 * Taille du plateau de jeu (pour être extensible).
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
private TicTacToeModel() { 
	//Tableau multidimentionnel representant le plateau de jeu
	  board = new ObjectProperty[BOARD_HEIGHT][BOARD_WIDTH];
	  //Tableau indiquant quelles cases ont été gagnées.
      winningBoard = new BooleanProperty[BOARD_HEIGHT][BOARD_WIDTH];
//Parcours lignes et colonnes
      for (int i = 0; i < BOARD_HEIGHT; i++) {
          for (int j = 0; j < BOARD_WIDTH; j++) {
              board[i][j] = new SimpleObjectProperty<>(Owner.NONE);
              winningBoard[i][j] = new SimpleBooleanProperty(false);
          }
      }
}

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
 private static final TicTacToeModel INSTANCE =new TicTacToeModel();
 }

 public void restart() { ... }

 public final ObjectProperty<Owner> turnProperty() { ... }

 public final ObjectProperty<Owner> getSquare(int row, int column)
 { ... }

 public final BooleanProperty getWinningSquare(int row, int column)
 { ... }

/**
 * Cette fonction ne doit donner le bon résultat que si le jeu
 * est terminé. L’affichage peut être caché avant la fin du jeu.
 *
 * @return résultat du jeu sous forme de texte */
 public final StringExpression getEndOfGameMessage() { ... }

 public void setWinner(Owner winner) { ... }

 public boolean validSquare(int row, int column) { ... }

public void nextPlayer() { ... }

 /**
 * Jouer dans la case (row, column) quand c’est possible.
 */
 public void play(int row, int column) { ... }

 /**
 * @return true s’il est possible de jouer dans la case
 * c’est-à-dire la case est libre et le jeu n’est pas terminé
 */
 public BooleanBinding legalMove(int row, int column) { ... }
public NumberExpression getScore(Owner owner) { ... }

 /**
 * @return true si le jeu est terminé
 * (soit un joueur a gagné, soit il n’y a plus de cases à jouer)
 */
 public BooleanBinding gameOver() { ... }
 }

