package virtualmachine;

public class InterruptHandling {

    public InterruptHandling() {
    }

    public boolean checkOverflowMathOperation(int value) {
        return (value > -2147483648 && value < 2147483647);
    }

    public boolean checkAddressLimits(int value) {
        return (value >= 0 && value <= 1023);
    }
}
