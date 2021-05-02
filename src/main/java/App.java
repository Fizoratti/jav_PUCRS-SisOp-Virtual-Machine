import util.Menu;
import util.Console;
import util.Emoji;

import virtualmachine.VM;

public class App {
    public static void main(String[] args) {
        Console.info("Boas vindas! "+ Emoji.DELIVERY_TRUCK);
        
        VM.init();
        Menu.get().showMenu();
    }
}

