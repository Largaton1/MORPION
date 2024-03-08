package kone.bouchra.morpion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.net.URL;
import java.util.ResourceBundle;

public class MorpionController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label message;

    @FXML
    private Label nbrLabelX;

    @FXML
    private Label nbrLabelO;
    @FXML
    private Label nbrLabel;

    private GridPane grid;
    private final int BOARD_HEIGHT = TicTacToeModel.getBoardHeight();
    private final int BOARD_WIDTH = TicTacToeModel.getBoardHeight();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grid = new GridPane();
        grid.setPrefHeight(BOARD_HEIGHT);
        grid.setPrefWidth(BOARD_WIDTH);

        for (int i = 0; i < BOARD_HEIGHT; i++) {
            grid.getRowConstraints().add(new RowConstraints(90));
        }

        for (int j = 0; j < BOARD_WIDTH; j++) {
            grid.getColumnConstraints().add(new ColumnConstraints(90));
        }

        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                grid.add(new TicTacToeSquare(i,j), j, i);
            }
        }
        grid.setGridLinesVisible(true);
 
        AnchorPane.setTopAnchor(grid, 30.0);
        AnchorPane.setBottomAnchor(grid, 50.0);
        AnchorPane.setLeftAnchor(grid, 110.0);
        AnchorPane.setRightAnchor(grid, 100.0);
        anchorPane.getChildren().add(grid);

        TicTacToeModel ticTacToeModel = TicTacToeModel.getInstance();
        maj(ticTacToeModel);
      

    }
    
    private void maj(TicTacToeModel ticTacToeModel) {
        message.textProperty().bind(ticTacToeModel.getEndOfGameMessage());
        nbrLabelX.textProperty().bind(ticTacToeModel.getNbrLabelX().asString("%d cases pour X "));
        nbrLabelO.textProperty().bind(ticTacToeModel.getNbrLabelO().asString("%d cases pour O "));
        nbrLabel.textProperty().bind(ticTacToeModel.getNbrLabel().asString("%d cases libres "));
    	
    }

    public void restart(ActionEvent e) {
        anchorPane.getChildren().remove(grid);
        TicTacToeModel.getInstance().restart();
        this.initialize(null, null);
    }
}