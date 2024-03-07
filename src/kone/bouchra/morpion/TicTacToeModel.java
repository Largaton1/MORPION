package kone.bouchra.morpion;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.NumberExpression;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.NumberExpression;
import javafx.beans.binding.StringBinding;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.Node;



import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;


public class TicTacToeModel {
	
	
	/**
	 * Taille du plateau de jeu (pour être extensible).
	 */
	public static final int BOARD_WIDTH = 3;
	public static final int BOARD_HEIGHT = 3;

	/**
	 * Nombre de pièces alignés pour gagner (idem).
	 */
	public static final int WINNING_COUNT = 3;

	/**
	 * Joueur courant.
	 */
	public final ObjectProperty<Owner> turn = new SimpleObjectProperty<>(Owner.FIRST);
	
	/*
	 * Nombre de tour joué
	 */
	public int countturn = 0;
	
	/**
	 * Vainqueur du jeu, NONE si pas de vainqueur.
	 */
	public final ObjectProperty<Owner> winner = new SimpleObjectProperty<>(Owner.NONE);
	
	/**
	 * Plateau de jeu.
	 */
	public final ObjectProperty<Owner>[][] board;
	
	/**
	 * Positions gagnantes.
	 */
	public final BooleanProperty[][] winningBoard;

	/**
	 * Constructeur privé.
	 */
	public TicTacToeModel() {
		
	
        board = new SimpleObjectProperty[BOARD_WIDTH][BOARD_HEIGHT];
		this.winningBoard =new BooleanProperty[BOARD_HEIGHT][BOARD_WIDTH]; 
		
		// Initialisation du plateau de jeu et du tableau des positions gagnantes
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
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
			private static final TicTacToeModel INSTANCE = new TicTacToeModel();
		}

		
		
		public void restart() { 
			for (int i = 0; i < BOARD_WIDTH; i++) {
	            for (int j = 0; j < BOARD_HEIGHT; j++) {
	                board[i][j] = new SimpleObjectProperty<>(Owner.NONE);
	                winningBoard[i][j] = new SimpleBooleanProperty(false);
	            }
	        }
			
			winner.set(Owner.NONE);
			turn.set(Owner.FIRST);
			countturn = 0;
			
			
			
		}
	
		
		
		public final ObjectProperty<Owner> turnProperty() {
			
			return turn;
			
		}

		public final ObjectProperty<Owner> getSquare(int row, int column) { 
			return board[row][column];
			
		}

		public final BooleanProperty getWinningSquare(int row, int column) { 
			
			
			return winningBoard[row][column];
			
			 }

		
		
		/**
		 * Cette fonction ne doit donner le bon résultat que si le jeu
		 * est terminé. L’affichage peut être caché avant la fin du jeu.
		 *
		 * @return résultat du jeu sous forme de texte
		 */
		public final StringExpression getEndOfGameMessage() {
			
			StringExpression  gameOverMessage = null;
			
			if (gameOver() != null) {
				
				
				
				if (winner.get()== Owner.FIRST) {
					
		            gameOverMessage = new SimpleStringProperty("Le gagnant est le joueur 1");

				}
				
				else if (winner.get()== Owner.SECOND) {
					
					 gameOverMessage = new SimpleStringProperty( "Le joueur 2 a gagné");
				}
				
				else  gameOverMessage = new SimpleStringProperty("Egalité");
			}
			
			return gameOverMessage;
		}
		
		

		public void setWinner(Owner winner) { 
			
			
			this.winner.set(winner) ;
			
			
		}


		public boolean validSquare(int row, int column) { 
			
			return board[row][column].get() == Owner.NONE;
			
		}
		
		

		public void nextPlayer() { 

			if (turn.get() == Owner.FIRST) {
				turn.set(Owner.SECOND);
			}
			
			else turn.set(Owner.FIRST);
		}

	
		

		/**
		 *Jouer dans la case (row, column) quand c’est possible.
		 */
		public void play(int row, int column) { 
			if (countturn%2==1) {
				board[row][column].set(Owner.SECOND);
				
				
			}
			else board[row][column].set(Owner.FIRST);
			countturn=countturn+1;
			nextPlayer();
		}
		
		
		

		/**
		 * @return true s’il est possible de jouer dans la case
		 * c’est-à-dire la case est libre et le jeu n’est pas terminé
		 */
		public BooleanBinding legalMove(int row, int column) { 
			
			return (board[row][column].isEqualTo(Owner.NONE).not()).or(gameOver());

			
		}
		
		

		public NumberExpression getScore(Owner owner) {
			
			
			NumberExpression tour = null ;
			
			
			if (countturn % 2 == 0) {
				
				if (owner == Owner.FIRST) {
					tour = new  SimpleIntegerProperty(countturn/2);
				}
				else { tour = new  SimpleIntegerProperty((countturn/2)+1);}
				
			}
			
			
			else 
				tour = new  SimpleIntegerProperty((countturn+1)/2);
			
			
			
		return tour;
		}

	/**
	 * @return true si le jeu est terminé
	 * (soit un joueur a gagné, soit il n’y a plus de cases à jouer)
	 */
	
	public BooleanBinding gameOver() {
		
		
		for (int i = 0; i< 2;i++) {
			if ((board[i][0].get()==board[i][1].get())&&(board[i][0].get()==board[i][2].get())&&(board[i][0].get()!=Owner.NONE)) {
				winner.set(board[i][0].get());
				winningBoard[i][0].set(true); winningBoard[i][1].set(true);winningBoard[i][2].set(true);

			}
			if ((board[0][i].get()==board[1][i].get())&&(board[0][i].get()==board[2][i].get())&&(board[0][i].get()!=Owner.NONE)) {
				winner.set(board[0][i].get());
				winningBoard[0][i].set(true);winningBoard[1][i].set(true);winningBoard[2][i].set(true);

			}
			
			

		}
		if ((board[0][0].get()==board[1][1].get())&&(board[2][2].get()==board[1][1].get())&&(board[1][1].get()!=Owner.NONE)) {
			winner.set(board[1][1].get());
			winningBoard[0][0].set(true);winningBoard[1][1].set(true);winningBoard[2][2].set(true);

		}
		if ((board[0][2].get()==board[1][1].get())&&(board[2][0].get()==board[1][1].get())&&(board[1][1].get()!=Owner.NONE)) {
			winner.set(board[1][1].get());
			winningBoard[0][2].set(true);winningBoard[1][1].set(true);winningBoard[2][0].set(true);

		}
		
				
	    return winner.isNotEqualTo(Owner.NONE).or(jeuPlein());

		
	}
	
	/*
	 * On bind board et jeu plein, puis on verifie si toutes les cases sont pleines
	 * Si elles le sont, on aura jeuPlein remplie de true
	 */
	private BooleanBinding jeuPlein() { 
		
		 List<Observable> observables = new ArrayList<>(); // On creer un objet observable pour pouvoir utiliser le binding
		    for (int i = 0; i < board.length; i++) {
		        for (int j = 0; j < board[i].length; j++) {
		            observables.add(board[i][j]);
		        }
		    }
		
		return new BooleanBinding() {
	        {bind(observables.toArray(new Observable[0])); }// Vu sur internet
	     
	        @Override
	        protected boolean computeValue() {
	            for (int i = 0; i < board.length; i++) {
	                for (int j = 0; j < board[i].length; j++) {
	                    if (board[i][j].get() == Owner.NONE) {
	                        return false;
	                    }
	                }
	            }
	            return true;
	        }
	    };
	}


	public static int getWinningCount() {
		return WINNING_COUNT;
	}



}