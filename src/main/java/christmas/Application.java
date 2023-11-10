package christmas;

public class Application {

    private final OutputView output;

    public Application(OutputView output) {
        this.output = output;
    }

    private void run() {
        output.excuteOrder();
    }

    public static void main(String[] args) {
        InputView input = new InputView();
        Menu menu = new Menu(input);
        OutputView output = new OutputView(input, menu);
        Application app = new Application(output);

        app.run();
    }
}
