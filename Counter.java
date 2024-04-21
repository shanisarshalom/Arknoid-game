//209583038 Shani Sar Shalom

/**
 *
 */
public class Counter {
    private int counter;

    /**
     * constructor of counter.
     * @param counter - the init num.
     */
    public Counter(int counter) {
        this.counter = counter;
    }

    /**
     * add number to current count.
     * @param number - the number to add.
     */
    void increase(int number) {
        counter += number;
    }

    /**
     * subtract number from current count.
     * @param number - the number to subtract.
     */
    void decrease(int number) {
        counter -= number;
    }

    /**
     * get current count.
     * @return the current count.
     */
    int getValue() {
        return this.counter;
    }
}