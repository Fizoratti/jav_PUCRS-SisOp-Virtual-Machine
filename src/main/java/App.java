import virtualmachine.Sistema;

public class App {
    public String getGreeting() {
        return "Hello world! \uD83D\uDC7D \n";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        Sistema.main();
    }
}
