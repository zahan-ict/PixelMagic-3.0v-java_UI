package dashboard;



import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.*;
import java.net.*;

import java.util.ResourceBundle;



public class Controller implements Initializable {

    @FXML
    private BorderPane borderPane;
    private String ui;

    @FXML private HBox dataHbox;


    /*private final ObservableList<ImgTable> recordList = FXCollections.observableArrayList(

            new ImgTable("1","row_current","2M","View")


    );
*/


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }


    private void loadUI(String ui){
        Parent child = null;

        try {
            System.out.println(ui);

            child = FXMLLoader.load(getClass().getResource(ui+".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(child);


    }



    private void loadUIandSendhttpRequest(String ui){

        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(ui+".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }



        System.out.println(ui);

        // a button for adding a new person.



            TableView tableView = new TableView();
          // tableView.setEditable(false);

            TableColumn<String, ImgTable> column1 = new TableColumn<>("Index");
            column1.setCellValueFactory(new PropertyValueFactory<>("index"));
           // column1.prefWidthProperty().bind(tableView.widthProperty().multiply(0.22)); // w * 1/4


            TableColumn<String, ImgTable> column2 = new TableColumn<>("File name");
            column2.setCellValueFactory(new PropertyValueFactory<>("filename"));
           // column2.prefWidthProperty().bind(tableView.widthProperty().multiply(0.22)); // w * 1/4

            TableColumn<String, ImgTable> column3 = new TableColumn<>("File size");
            column3.setCellValueFactory(new PropertyValueFactory<>("filesize"));
           // column3.prefWidthProperty().bind(tableView.widthProperty().multiply(0.22)); // w * 1/4

        // tableView.setItems(recordList);

        tableView.getItems().add(new ImgTable("1", "Pano1","10M","Show image"));
        tableView.getItems().add(new ImgTable("2", "Pano2","20M","Show image"));
        tableView.getItems().add(new ImgTable("3", "Pano3","20M","Show image"));




           // column4.setSortable(false);

        //****************************** Resize Image ******************************

        TableColumn<ImgTable, ImgTable> resizeCol = new TableColumn<>("Resize Image");
        resizeCol.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );

        resizeCol.setCellFactory(param -> new TableCell<ImgTable, ImgTable>() {
            private final Button resizeButton = new Button("Resize");

            @Override
            protected void updateItem(ImgTable TbRow, boolean empty) {
                super.updateItem(TbRow, empty);

                if (TbRow == null) {
                    setGraphic(null);

                    return;

                }


                setGraphic(resizeButton);
                resizeButton.setOnAction(event -> getTableView().getItems());

                resizeButton.setOnAction(event -> {
                    String a = "Zahan";


                    System.out.println(a);
                });


            }
        });

        //*****************************************************************************



        //******************************Delete Image******************************

        TableColumn<ImgTable, ImgTable> removeCol = new TableColumn<>("Remove");
        removeCol.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );




        removeCol.setCellFactory(param -> new TableCell<ImgTable, ImgTable>() {
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(ImgTable person, boolean empty) {
                super.updateItem(person, empty);

                if (person == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(
                        event -> getTableView().getItems().remove(person)
                );
            }
        });
        //*****************************************************************************

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

           /* tableView.getColumns().add(column1);
            tableView.getColumns().add(column2);
            tableView.getColumns().add(column3);

            tableView.getColumns().add(column4);

            tableView.getColumns().add(unfriendCol);*/

             tableView.getColumns().addAll(column1, column2,column3,resizeCol,removeCol);






           VBox vbox = new VBox();

           Label lblImgList = new Label("Image List");

           lblImgList.setStyle("-fx-font-size: 30px; -fx-padding:20px;");

           vbox.getChildren().add(lblImgList);

           vbox.getChildren().add(tableView);


           root = vbox;

        borderPane.setCenter(root);

        sendHttpRequest();

    }

    public void sendHttpRequest(){

        String url = "http://192.168.6.87/readimages.php";
        String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
        String param1 = "value1";
        String param2 = "value2";




        try {
            String query = String.format("param1=%s&param2=%s",
                    URLEncoder.encode(param1, charset),
                    URLEncoder.encode(param2, charset));

            HttpURLConnection httpConnection = (HttpURLConnection) new URL(url + "?" + query).openConnection();
            httpConnection.setRequestProperty("Accept-Charset", charset);
            InputStream response = httpConnection.getInputStream();

            String contentType = httpConnection.getHeaderField("Content-Type");




            if (charset != null) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(response, charset))) {
                    for (String line; (line = reader.readLine()) != null;) {
                        System.out.println(line);
                    }
                }
            } else {
                // It's likely binary content, use InputStream/OutputStream.
            }







        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }




    public void imageList(MouseEvent mouseEvent) {
       // loadUI("imglist");
        loadUIandSendhttpRequest("imglist");

    }





    @FXML
    public void cropList(MouseEvent mouseEvent) {
        loadUI("croplist");

    }


    public void setting(MouseEvent mouseEvent) {
        loadUI("setting");
    }

    public void about(MouseEvent mouseEvent) {

        loadUI("about");

    }
}
