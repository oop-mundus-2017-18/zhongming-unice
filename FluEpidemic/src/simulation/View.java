package simulation;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;

import static beings.State.*;

/**
 * @author ZHONG Ming
 */

public class View extends Application {
    static final int ROW = 15;
    static final int COL = 15;
    static final int SIZE = 40;
    private Model model = new Model();
    private Node[][] points = new Node[ROW][COL];

    void update() {
        model.nextDay();
        for (int row = 0; row < model.field.getHeight(); row++) {
            for (int col = 0; col < model.field.getWidth(); col++) {
                switch (model.field.get(row, col).toString()) {
                case "Person":
                    points[row][col] = new Arc();
                    ((Arc) points[row][col]).setCenterX(20.0f);
                    ((Arc) points[row][col]).setCenterY(20.0f);
                    ((Arc) points[row][col]).setRadiusX(10.0f);
                    ((Arc) points[row][col]).setRadiusY(10.0f);
                    ((Arc) points[row][col]).setStartAngle(45.0f);
                    ((Arc) points[row][col]).setLength(270.0f);
                    ((Arc) points[row][col]).setType(ArcType.ROUND);
                    break;
                case "Chicken":
                    points[row][col] = new QuadCurve();
                    ((QuadCurve) points[row][col]).setStartX(10.0f);
                    ((QuadCurve) points[row][col]).setStartY(35.0f);
                    ((QuadCurve) points[row][col]).setEndX(35.0f);
                    ((QuadCurve) points[row][col]).setEndY(35.0f);
                    ((QuadCurve) points[row][col]).setControlX(20.0f);
                    ((QuadCurve) points[row][col]).setControlY(5.0f);
                    break;
                case "Duck":
                    points[row][col] = new CubicCurve();
                    ((CubicCurve) points[row][col]).setStartX(3.0f);
                    ((CubicCurve) points[row][col]).setStartY(10.0f);
                    ((CubicCurve) points[row][col]).setControlX1(20.0f);
                    ((CubicCurve) points[row][col]).setControlY1(0.0f);
                    ((CubicCurve) points[row][col]).setControlX2(20.0f);
                    ((CubicCurve) points[row][col]).setControlY2(45.0f);
                    ((CubicCurve) points[row][col]).setEndX(35.0f);
                    ((CubicCurve) points[row][col]).setEndY(20.0f);
                    break;
                case "Pig":
                    points[row][col] = new Circle();
                    ((Circle) points[row][col]).setCenterX(20.0f);
                    ((Circle) points[row][col]).setCenterY(20.0f);
                    ((Circle) points[row][col]).setRadius(9.0f);
                    break;
                }
                if (model.field.get(row, col).states.contains(Infected))
                    ((Shape) points[row][col]).setFill(Color.BURLYWOOD);
                if (model.field.get(row, col).states.contains(Dead))
                    ((Shape) points[row][col]).setFill(Color.DARKGRAY);
                if (model.field.get(row, col).states.contains(Healthy))
                    ((Shape) points[row][col]).setFill(Color.CADETBLUE);
            }
        }
    }

    @Override
    public void init() {
        model.init();
        update();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        for (int i = 0; i < COL; i++) {
            ColumnConstraints column = new ColumnConstraints(SIZE);
            grid.getColumnConstraints().add(column);
        }

        for (int i = 0; i < ROW; i++) {
            RowConstraints row = new RowConstraints(SIZE);
            grid.getRowConstraints().add(row);
        }

        grid.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");
        for (int i = 0; i < COL; i++) {
            for (int j = 0; j < ROW; j++) {
                Pane pane = new Pane();
                pane.getChildren().add(points[i][j]);
                grid.add(pane, i, j);
            }
        }
        BorderPane root = new BorderPane();
        root.setPrefSize(1000,800);
        Label dayLabel = new Label();
        dayLabel.setText("day" + model.day);
        dayLabel.setAlignment(Pos.CENTER);
        root.setTop(dayLabel);
        BorderPane.setAlignment(root.getTop(), Pos.CENTER);

        root.setCenter(grid);
        grid.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(root.getCenter(), Pos.CENTER);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        Button btn = new Button("next day");
        btn.setOnAction(event -> {
            update();
            dayLabel.setText("day " + model.day);
            for (int i = 0; i < COL; i++) {
                for (int j = 0; j < ROW; j++) {
                    Pane pane = new Pane();
                    pane.getChildren().add(points[i][j]);
                    grid.add(pane, i, j);
                }
            }
        });
        root.setBottom(btn);
        btn.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(root.getBottom(), Pos.CENTER);
        
        VBox commentaire = new VBox();
        commentaire.setAlignment(Pos.CENTER);
        
        Label lab1 = new Label("BURLYWOOD : Infected");
        commentaire.getChildren().add(lab1);
        Label lab2 = new Label("CADETBLUE : Healthy");
        commentaire.getChildren().add(lab2);
        Label lab3 = new Label("DARKGRAY : Dead");
        commentaire.getChildren().add(lab3);
        
        
        
        root.setRight(commentaire);
        BorderPane.setAlignment(root.getRight(), Pos.CENTER);
    }
}
