import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;

public class HistogramGUI extends BorderPane {
  Text tMessage;
  TextField tfKey;
  HistogramPane hpPane;
  Button btStep;
  int stateCount = 0;

  public HistogramGUI() {
    drawGUI();
  }

  private void drawGUI() {
    tMessage = new Text();
    hpPane = new HistogramPane();
    Text tKey = new Text("Key:");
    tfKey = new TextField();
    btStep = new Button("Step");
    Button btReset = new Button("Reset");

    btStep.setOnAction(e -> step());
    btReset.setOnAction(e -> reset());

    HBox hbBottom = new HBox(10);
    hbBottom.getChildren().addAll(tKey, tfKey, btStep, btReset);
    hbBottom.setAlignment(Pos.CENTER);

    setTop(tMessage);
    setCenter(hpPane);
    setBottom(hbBottom);
    setAlignment(tMessage, Pos.CENTER);
  }

  private void step() {
    if (!inputIsValid(tfKey.getText())) {
      tfKey.setText("");
      tMessage.setText("Invalid Input: enter an integer");
      return;
    }

    tfKey.setEditable(false);

    if (hpPane.getStates() == null) {
      hpPane.getSearchStates(Integer.parseInt(tfKey.getText()));
    }

    LinearSearchState s = hpPane.getStates()[stateCount];
    hpPane.paintBarsWhite();
    hpPane.getBars()[stateCount].setFill(Color.BLACK);

    if (s.getFound()) {
      tMessage.setText("The key is found in the array at index " + s.getIndex());
      btStep.setDisable(true);
    } else if (stateCount == hpPane.getStates().length - 1 && !s.getFound()) {
      tMessage.setText("The key is not in the array");
      btStep.setDisable(true);
    }

    stateCount++;
  }

  private void reset() {
    hpPane.setUp();
    stateCount = 0;
    tMessage.setText("");
    tfKey.setEditable(true);
    btStep.setDisable(false);
  }

  private boolean inputIsValid(String input) {
    if (input.length() < 1) { return false; }
    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);
      if (i == 0 && c == '-') { continue; }
      if (!Character.isDigit(c)) { return false; }
    }
    return true;
  }
}
