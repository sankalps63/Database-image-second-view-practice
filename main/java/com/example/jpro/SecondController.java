package com.example.jpro;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;

import com.mysql.cj.jdbc.Driver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ConnectException;

public class SecondController {
    @FXML
    private ImageView gv;
    private Stage stage;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    public void switch1() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        HelloController cont = fxmlloader.getController();
        cont.setStage(stage);
        stage.setScene(scene);
    }

    @FXML
    public void addImage(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/practice";
            String user = "root";
            String pass = "*************";
            Connection conn = DriverManager.getConnection(url,user,pass);

            FileChooser fc = new FileChooser();
            File img = fc.showOpenDialog(null);
            Image mg = new Image(img.toURI().toString());
            gv.setImage(mg);
            FileInputStream fis = new FileInputStream(img);
            PreparedStatement pstmt = conn.prepareStatement("insert into imageData (Image) values (?)");
            pstmt.setBinaryStream(1,fis);
            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected>0){
                System.out.println("Image added");
            }else {
                System.out.println("Some error occured");
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
