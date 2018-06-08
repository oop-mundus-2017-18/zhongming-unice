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

import java.util.stream.Stream;

/**
 * @author ZHONG Ming
 * @author Xu Yijie
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
                if (model.field.get(row, col).states.contains(Infected)) {
                    if (model.field.get(row, col).virus.name.equals("H5N1"))
                        ((Shape) points[row][col]).setFill(Color.BURLYWOOD);
                    if (model.field.get(row, col).virus.name.equals("H1N1"))
                        ((Shape) points[row][col]).setFill(Color.MISTYROSE);
                }
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
        root.setPrefSize(1000, 800);
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
        // ajouter un bouton
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

        // ajouter les commentaires
        HBox commentaire = new HBox();
        commentaire.setAlignment(Pos.CENTER);
        VBox c1 = new VBox();
        VBox c2 = new VBox();

        Label lab0 = new Label(" : Infected by H1N1");
        Label lab1 = new Label(" : Infected by H5N1");
        Label lab2 = new Label(" : Healthy");
        Label lab3 = new Label(" : Dead");
        Label lab4 = new Label(" : Sapiens");
        Label lab5 = new Label(" : Chicken");
        Label lab6 = new Label(" : Duck");
        Label lab7 = new Label(" : Pig");

        Rectangle a0 = new Rectangle(15, 15);
        a0.setFill(Color.MISTYROSE);
        Rectangle a1 = new Rectangle(15, 15);
        a1.setFill(Color.BURLYWOOD);
        Rectangle a2 = new Rectangle(15, 15);
        a2.setFill(Color.CADETBLUE);
        Rectangle a3 = new Rectangle(15, 15);
        a3.setFill(Color.DARKGRAY);
        Arc a4 = new Arc();
        a4.setRadiusX(7.0f);
        a4.setRadiusY(7.0f);
        a4.setStartAngle(45.0f);
        a4.setLength(270.0f);
        a4.setType(ArcType.ROUND);
        QuadCurve a5 = new QuadCurve();
        a5.setStartX(7.5f);
        a5.setStartY(26.25f);
        a5.setEndX(26.25f);
        a5.setEndY(26.25f);
        a5.setControlX(15.0f);
        a5.setControlY(3.75f);
        CubicCurve a6 = new CubicCurve();
        a6.setStartX(2.25f);
        a6.setStartY(7.5f);
        a6.setControlX1(15.0f);
        a6.setControlY1(0.0f);
        a6.setControlX2(15.0f);
        a6.setControlY2(41.25f);
        a6.setEndX(26.25f);
        a6.setEndY(15.0f);
        Circle a7 = new Circle();
        a7.setRadius(6.75f);

        Stream.of(a4, a5, a6, a7).forEach(e -> e.setFill(Color.DARKRED));

        c1.getChildren().addAll(a0, a1, a2, a3, a4, a5, a6, a7);
        c1.setSpacing(10);
        c2.getChildren().addAll(lab0, lab1, lab2, lab3, lab4, lab5, lab6, lab7);
        c2.setSpacing(10);
        commentaire.getChildren().addAll(c1, c2);
        root.setRight(commentaire);
        BorderPane.setAlignment(root.getRight(), Pos.CENTER);
    }
}
