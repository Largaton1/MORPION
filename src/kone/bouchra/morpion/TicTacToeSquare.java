package kone.bouchra.morpion;
import kone.bouchra.morpion.TicTacToeModel;
import kone.bouchra.morpion.Owner;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TextField;

public class TicTacToeSquare  extends TextField  {
	
	private static TicTacToeModel model = TicTacToeModel.getInstance();
	
	private ObjectProperty<Owner> ownerProperty =
	new SimpleObjectProperty<>(Owner.NONE);
	
	private BooleanProperty winnerProperty =
	 new SimpleBooleanProperty(false);
	
	 public ObjectProperty<Owner> ownerProperty() {
		return null;
	}
	
	public BooleanProperty WinnerProperty() {
		return null;
	}
	
	 public TicTacToeSquare(final int row, final int column) { 
		 
		 model.play(row,column);
		 
		 
	 }

}
