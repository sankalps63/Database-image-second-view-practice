package com.example.jpro;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HelloController {

    private Stage stage;

    public void setStage(Stage stage){
        this.stage = stage;
    }
    @FXML
    public ImageView imgv;

    @FXML
    protected void onButtonClick(){
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);
        Image img = new Image(file.toURI().toString());
        if(file != null){
            imgv.setImage(img);
        }
        else {
            System.out.println("Error");
        }
    }

    @FXML
    public void showFromDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/practice";
            String user = "root";
            String pass = "***********";
            Connection conn = DriverManager.getConnection(url,user,pass);
            PreparedStatement pstmt = conn.prepareStatement("select Image from imageData where id = ?");
            pstmt.setInt(1,1);
            ResultSet set = pstmt.executeQuery();
            while(set.next()){
                byte[] image = set.getBytes("Image");
                ByteArrayInputStream bis = new ByteArrayInputStream(image);
                Image mi = new Image(bis);
                imgv.setImage(mi);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void switch2() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("second.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        SecondController cont = fxmlloader.getController();
        cont.setStage(stage);
        stage.setScene(scene);
    }
}