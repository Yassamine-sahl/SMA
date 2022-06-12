package Containers;

import Agents.Player2;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Player2Container extends Application {

    static Player2 p2;
    public ObservableList<String> observableList = FXCollections.observableArrayList();

    public void setAgent(Player2 player2) {
        this.p2 = player2;
    }

    public Player2 getPlayer2() {
        return p2;
    }

    public static void main(String[] args) throws ControllerException {
        Player2Container container = new Player2Container();
        container.startContainer();
        launch(args);
    }

    public void startContainer() throws ControllerException {
        Runtime run = Runtime.instance();
        ProfileImpl pr = new ProfileImpl();
        pr.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        AgentContainer runAgentContainer = run.createAgentContainer(pr);
        AgentController agentController = runAgentContainer.createNewAgent("Player2", "Agents.Player2", new Object[]{this});
        agentController.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        HBox hBox = new HBox();
        hBox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        Label label = new Label("send message");
        label.setPadding(new javafx.geometry.Insets(0, 10, 0, 10));
        TextField textField = new TextField();
        Button button = new Button("send");

        hBox.getChildren().addAll(label, textField, button);
        root.setTop(hBox);

        ListView<String> listView = new ListView<>();
        listView.setItems(observableList);

        VBox vBox = new VBox();
        vBox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(listView);
        root.setCenter(vBox);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Player2");
        primaryStage.show();

        button.setOnAction(event -> {
            String message = textField.getText();
            GuiEvent guiEvent = new GuiEvent(event, 0);
            guiEvent.addParameter(message);
            guiEvent.addParameter("Server");
            guiEvent.addParameter(listView);
            p2.onGuiEvent(guiEvent);
        });
    }
}
