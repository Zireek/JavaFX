package com.example.juegojavafx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class HelloApplication extends Application {
    public static final int width = 800;
    public static final int height = 600;
    public static final int player_height = 100;
    public static final int player_width = 15;
    private static final double ball_r = 15;
    private int ballYSpeed = 1;
    private int ballXSpeed = 1;
    private double playerOneYPos = height / 2;
    private double playerTwoYPos = height / 2;
    private double ballXPos = width / 2;
    private double ballYPos = width / 2;
    private int scoreP1 = 0;
    private int scoreP2 = 0;
    private boolean gameStarted;
    private int playerOneXPos = 0;
    private double playerTwoXPos = width - player_width;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("PONG");
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);

        //Control con el mouse
        canvas.setOnMouseMoved(e -> playerOneYPos = e.getY());
        canvas.setOnMouseClicked(e -> gameStarted = true);
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
        tl.play();
    }

    private void run(GraphicsContext gc){

        //color fondo
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);

        //color letra
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));

        //Movimiento bola
        if (gameStarted) {
            ballXPos += ballXSpeed;
            ballYPos += ballYSpeed;

            //player2
            if (ballXPos < width - width / 4) {
                playerTwoYPos = ballYPos - player_height / 2;
            } else {
                playerTwoYPos = ballYPos > playerTwoYPos + player_height / 2 ? playerTwoYPos += ballYSpeed : playerTwoYPos - 5;
            }
           /* if (ballXPos < width - width / 4) {
                playerOneYPos = ballYPos - player_height / 2;
            } else {
                playerOneYPos = ballYPos > playerOneYPos + player_height / 2 ? playerOneYPos += ballYSpeed *2 : playerOneYPos - 5;
            }*/

            //dibujar bola
            gc.fillOval(ballXPos, ballYPos, ball_r, ball_r);

        }else {

            //texto iniciar
            gc.setStroke(Color.WHITE);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText("Haz clic aquí", width / 2, height / 2);

            //resetear posicion bola
            ballXPos = width / 2;
            ballYPos = height / 2;

            //resetear la velocidad y direccion

            ballXSpeed = new Random().nextInt(2) == 0 ? 1: -1;
            ballYSpeed = new Random().nextInt(2) == 0 ? 1: +1;

        }

        //pelota en el canvas
        if (ballYPos > height || ballYPos < 0) ballYSpeed *= -1;

        //player2 punto

        if (ballXPos < playerOneXPos - player_width) {
            scoreP2++;
            gameStarted = false;
        }

        //player1 punto
        if (ballXPos > playerTwoXPos + player_width){
            scoreP1++;
            gameStarted = false;
        }

        //incrementar la velocidad de la pelota
        if (((ballXPos + ball_r > playerTwoXPos) && ballYPos >= playerTwoYPos && ballYPos <= playerTwoYPos + player_height) ||
        ((ballXPos < playerOneXPos + player_width) && ballYPos >= playerOneYPos && ballYPos <= playerOneYPos + player_height)){
            ballYSpeed +=1 * Math.signum(ballYSpeed);
            ballXSpeed +=1 * Math.signum(ballXSpeed);
            ballXSpeed *= -1;
            ballYSpeed *= 1;
        }

        //mostrar score
        gc.fillText(scoreP1 + "\t\t\t\t\t\t\t\t\t\t" + scoreP2, width / 2, 100);

        //mostrar player1 y player2
        gc.fillRect(playerTwoXPos, playerTwoYPos,player_width,player_height);
        gc.fillRect(playerOneXPos, playerOneYPos,player_width,player_height);

    }
}






