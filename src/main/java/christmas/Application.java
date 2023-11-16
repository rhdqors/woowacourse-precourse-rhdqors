package christmas;

public class Application {

    private final OutputView output;

    public Application(OutputView output) {
        this.output = output;
    }

    private void run() {
        MenuPrice.saveAllMenus();
        output.excuteOrder();
    }

    public static void main(String[] args) {
        InputView input = new InputView();
        Order order = new Order(input);
        Event event = new Event();
        OutputView output = new OutputView(input, order, event);
        Application app = new Application(output);

        try {
            app.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}
