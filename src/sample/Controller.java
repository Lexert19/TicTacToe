package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Game game = new Game();
    @FXML
    private Button field11;
    @FXML
    private Button field21;
    @FXML
    private Button field31;
    @FXML
    private Button field12;
    @FXML
    private Button field22;
    @FXML
    private Button field32;
    @FXML
    private Button field13;
    @FXML
    private Button field23;
    @FXML
    private Button field33;
    @FXML
    private Label rounds;
    @FXML
    private Label x;
    @FXML
    private Label o;
    private Button[][] fields = new Button[3][3];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fields[0][0] = field11;
        fields[1][0] = field21;
        fields[2][0] = field31;
        fields[0][1] = field12;
        fields[1][1] = field22;
        fields[2][1] = field32;
        fields[0][2] = field13;
        fields[1][2] = field23;
        fields[2][2] = field33;
    }

    public void pressedField11(MouseEvent mouseEvent) {
        pressedField(1, 1);
    }

    public void pressedField21(MouseEvent mouseEvent) {
        pressedField(2, 1);
    }

    public void pressedField31(MouseEvent mouseEvent) {
        pressedField(3, 1);
    }

    public void pressedField12(MouseEvent mouseEvent) {
        pressedField(1, 2);
    }

    public void pressedField22(MouseEvent mouseEvent) {
        pressedField(2, 2);
    }

    public void pressedField32(MouseEvent mouseEvent) {
        pressedField(3, 2);
    }

    public void pressedField13(MouseEvent mouseEvent) {
        pressedField(1, 3);
    }

    public void pressedField23(MouseEvent mouseEvent) {
        pressedField(2, 3);
    }

    public void pressedField33(MouseEvent mouseEvent) {
        pressedField(3, 3);
    }

    public void pressedField(int i, int j) {
        game.move(i,j);
        if(game.isBot())
            game.moveBot();


        update();
        game.check();
        if(game.getEnd()){
           clear();
        }
    }
    public void moveBot(){
        game.moveBot();

        update();
        game.check();
        if(game.getEnd()){
            clear();
        }
    }

    private void update(){
        for(int i=0; i<fields[0].length; i++){
            for(int j=0; j<fields[1].length; j++){
                if(game.getFields()[j][i] != '\u0000' && !fields[i][j].getText().equals("❌") && !fields[i][j].getText().equals("◯")){
                    fields[i][j].setText(String.valueOf(game.getFields()[j][i]));
                    fields[i][j].getStyleClass().add("disableHover");
                    if(game.getFields()[j][i] == '❌')
                        fields[i][j].getStyleClass().add("fieldX");
                    if(game.getFields()[j][i] == '◯')
                        fields[i][j].getStyleClass().add("fieldO");
                }
            }
        }
        rounds.setText("Rounds: "+game.getGamesCount());
        x.setText(": "+game.getX());
        o.setText(": "+game.getO());
    }

    public void clear(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                fields[i][j].setText(String.valueOf('\u0000'));
                fields[i][j].getStyleClass().remove("fieldX");
                fields[i][j].getStyleClass().remove("fieldO");
                fields[i][j].getStyleClass().remove("disableHover");
            }
        }
        game.setEnd(false);
        update();
        if(game.isBot())
            moveBot();
    }


    public void turnOnBot(ActionEvent actionEvent) {
        game.setBot(true);
        restart();
    }

    public void turnOffBot(ActionEvent actionEvent) {
        game.setBot(false);
        restart();
    }

    public void restart(){
        game.restart();
        clear();
    }

    public void restartMenu(ActionEvent actionEvent) {
        restart();
    }
}
