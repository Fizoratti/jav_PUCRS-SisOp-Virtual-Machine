package tasks;

import util.Console;

public class TestTask implements Task {

    public void run() {                             Console.debug(" > TestTask.run()");
        Console.log("Works!");
    }

}
