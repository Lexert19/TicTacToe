package sample;


import javafx.scene.control.Alert;

public class Game {
    private char[][] fields = new char[3][3];
    private char round = '◯';
    private int roundCount = 0;
    private boolean end = false;
    private int x = 0;
    private int o = 0;
    private int gamesCount = 0;
    private boolean bot = true;
    private boolean botFirst = false;

    public char move(int i, int j){
        i--;
        j--;
        if(fields[j][i] == '\u0000'){
            fields[j][i] = round;
            roundCount += 1;

            check();

            if(round == '◯'){
                round = '❌';
                return  '◯';
            }
            else{
                round = '◯';
                return  '❌';
            }
        }else {
            return 'n';
        }
    }
    public void moveBot(){
        if(round == '◯')
            return;
        botPutWin();
        botPutBlock();

        if(roundCount == 0)
            botPutChar(2,0);
        if(roundCount == 1)
            botPutChar(1,1);
        if(fields[2][0]==round && fields[1][1] != '◯' && (fields[0][0] == '◯' || fields[0][2] == '◯' || fields[2][2] == '◯')){
            if(fields[1][0] == '◯')
                botPutChar(2,2);
            if(fields[2][1] == '◯')
                botPutChar(0,0);
            botPutChar(2,2);
            botPutChar(0,0);
            botPutChar(0,2);
        }
        if(fields[2][0]==round && (fields[0][1] == '◯' || fields[1][0] == '◯' ||fields[2][1] == '◯' ||fields[1][2] == '◯' )){
            botPutChar(1,1);
        }
        if(fields[1][1] == '◯' && fields[2][0]==round){
            botPutChar(0,2);
            botPutChar(2,0);
        }
        if(fields[1][1] == '◯' && fields[2][2] == '◯' && fields[0][0] == round){
            botPutChar(0,2);
        }
        if(fields[1][1] == round && ((fields[0][0] == '◯' && fields[2][2] == '◯') || (fields[0][2] == '◯' && fields[2][0] == '◯')))
            botPutChar(1,0);


        if(fields[2][2] == '◯' && fields[1][2] == '◯')
            botPutChar(0,2);

        botPutBlock();
        botPutSenseless();

        check();
    }

    private void botPutSenseless(){
        botPutChar(0,0);
        botPutChar(1,0);
        botPutChar(2,0);
        botPutChar(0,1);
        botPutChar(0,2);
        botPutChar(1,1);
        botPutChar(2,2);
        botPutChar(1,2);
        botPutChar(2,1);
    }
    private void botPutBlock(){
        if(fields[0][0] == '◯' && fields[1][0] == '◯')
            botPutChar(2,0);
        if(fields[1][0] == '◯' && fields[2][0] == '◯')
            botPutChar(0,0);
        if(fields[0][0] == '◯' && fields[2][0] == '◯')
            botPutChar(1,0);
        //
        if(fields[0][1] == '◯' && fields[1][1] == '◯')
            botPutChar(2,1);
        if(fields[1][1] == '◯' && fields[2][1] == '◯')
            botPutChar(0,1);
        if(fields[0][1] == '◯' && fields[2][1] == '◯')
            botPutChar(1,1);
        //
        if(fields[0][2] == '◯' && fields[1][2] == '◯')
            botPutChar(2,2);
        if(fields[1][2] == '◯' && fields[2][2] == '◯')
            botPutChar(0,2);
        if(fields[0][2] == '◯' && fields[2][2] == '◯')
            botPutChar(1,2);
        //
        if(fields[0][0] == '◯' && fields[0][1] == '◯')
            botPutChar(0,2);
        if(fields[0][0] == '◯' && fields[0][2] == '◯')
            botPutChar(0,1);
        if(fields[0][2] == '◯' && fields[0][1] == '◯')
            botPutChar(0,0);
        //
        if(fields[1][0] == '◯' && fields[1][1] == '◯')
            botPutChar(1,2);
        if(fields[1][0] == '◯' && fields[1][2] == '◯')
            botPutChar(1,1);
        if(fields[1][2] == '◯' && fields[1][1] == '◯')
            botPutChar(1,0);
        //
        if(fields[2][0] == '◯' && fields[2][1] == '◯')
            botPutChar(2,2);
        if(fields[2][0] == '◯' && fields[2][2] == '◯')
            botPutChar(2,1);
        if(fields[2][2] == '◯' && fields[2][1] == '◯')
            botPutChar(2,0);
        //
        if(fields[0][0]== '◯' && fields[1][1]== '◯')
            botPutChar(2,2);
        if(fields[2][2]== '◯' && fields[1][1]== '◯')
            botPutChar(0,0);
        if(fields[0][0]== '◯' && fields[2][2]== '◯')
            botPutChar(1,1);
        //
        if(fields[0][2]== '◯' && fields[1][1]== '◯')
            botPutChar(2,0);
        if(fields[2][0]== '◯' && fields[1][1]== '◯')
            botPutChar(0,2);
        if(fields[2][0]== '◯' && fields[0][2]== '◯')
            botPutChar(1,1);
    }
    private void botPutWin(){
        if(fields[0][0] == '❌' && fields[1][0] == '❌')
            botPutChar(2,0);
        if(fields[1][0] == '❌' && fields[2][0] == '❌')
            botPutChar(0,0);
        if(fields[0][0] == '❌' && fields[2][0] == '❌')
            botPutChar(1,0);
        //
        if(fields[0][1] == '❌' && fields[1][1] == '❌')
            botPutChar(2,1);
        if(fields[1][1] == '❌' && fields[2][1] == '❌')
            botPutChar(0,1);
        if(fields[0][1] == '❌' && fields[2][1] == '❌')
            botPutChar(1,1);
        //
        if(fields[0][2] == '❌' && fields[1][2] == '❌')
            botPutChar(2,2);
        if(fields[1][2] == '❌' && fields[2][2] == '❌')
            botPutChar(0,2);
        if(fields[0][2] == '❌' && fields[2][2] == '❌')
            botPutChar(1,2);
        //
        if(fields[0][0] == '❌' && fields[0][1] == '❌')
            botPutChar(0,2);
        if(fields[0][0] == '❌' && fields[0][2] == '❌')
            botPutChar(0,1);
        if(fields[0][2] == '❌' && fields[0][1] == '❌')
            botPutChar(0,0);
        //
        if(fields[1][0] == '❌' && fields[1][1] == '❌')
            botPutChar(1,2);
        if(fields[1][0] == '❌' && fields[1][2] == '❌')
            botPutChar(1,1);
        if(fields[1][2] == '❌' && fields[1][1] == '❌')
            botPutChar(1,0);
        //
        if(fields[2][0] == '❌' && fields[2][1] == '❌')
            botPutChar(2,2);
        if(fields[2][0] == '❌' && fields[2][2] == '❌')
            botPutChar(2,1);
        if(fields[2][2] == '❌' && fields[2][1] == '❌')
            botPutChar(2,0);
        //
        if(fields[0][0]== '❌' && fields[1][1]== '❌')
            botPutChar(2,2);
        if(fields[2][2]== '❌' && fields[1][1]== '❌')
            botPutChar(0,0);
        if(fields[0][0]== '❌' && fields[2][2]== '❌')
            botPutChar(1,1);
        //
        if(fields[0][2]== '❌' && fields[1][1]== '❌')
            botPutChar(2,0);
        if(fields[2][0]== '❌' && fields[1][1]== '❌')
            botPutChar(0,2);
        if(fields[2][0]== '❌' && fields[0][2]== '❌')
            botPutChar(1,1);
    }
    private void botPutChar(int i, int j){
        if(fields[i][j] == '\u0000' && round == '❌') {
            fields[i][j] = round;
            round = '◯';
            roundCount++;
        }
    }

    public void check(){
        if(fields[0][0] == fields[0][1] && fields[0][1] == fields[0][2]){
            showAlert(fields[0][0]);
        }if(fields[1][0] == fields[1][1] && fields[1][1] == fields[1][2]){
            showAlert(fields[1][0]);
        }if(fields[2][0] == fields[2][1] && fields[2][1] == fields[2][2]){
            showAlert(fields[2][0]);
        }if(fields[0][0] == fields[1][0] && fields[1][0] == fields[2][0]){
            showAlert(fields[0][0]);
        }if(fields[0][1] == fields[1][1] && fields[1][1] == fields[2][1]){
            showAlert(fields[0][1]);
        }if(fields[0][2] == fields[1][2] && fields[1][2] == fields[2][2]){
            showAlert(fields[0][2]);
        }if(fields[0][0] == fields[1][1] && fields[1][1] == fields[2][2]){
            showAlert(fields[0][0]);
        }if(fields[0][2] == fields[1][1] && fields[1][1] == fields[2][0]) {
            showAlert(fields[0][2]);
        }if(roundCount == 9){
            showRemisAlert();
        }
    }

    public void showAlert(char player){
        if(player == '❌')
            this.x += 1;
        if(player == '◯')
            this.o += 1;
        if(player == '❌' || player =='◯'){
            this.gamesCount += 1;
            end = true;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("End");
            alert.setHeaderText(null);
            alert.setContentText("Player: "+player+" won!");

            alert.showAndWait();

            clearFields();
        }
    }
    public void showRemisAlert(){
        this.gamesCount += 1;
        end = true;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End");
        alert.setHeaderText(null);
        alert.setContentText("Remis");

        alert.showAndWait();
        clearFields();
    }

    public void clearFields(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                fields[i][j] = '\u0000';
            }
        }
        roundCount = 0;
    }

    public void restart(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                fields[i][j] = '\u0000';
            }
        }
        round = '◯';
        roundCount = 0;
        gamesCount = 0;
        this.x = 0;
        this.o = 0;
    }

    public boolean getEnd(){
        return this.end;
    }

    public void setEnd(boolean end){
        this.end = end;
    }

    public int getGamesCount(){
        return this.gamesCount;
    }
    public int getX(){
        return this.x;
    }
    public int getO(){
        return this.o;
    }
    public char[][] getFields(){
        return this.fields;
    }

    public boolean isBot() {
        return bot;
    }

    public void setBot(boolean bot) {
        this.bot = bot;
    }
}
