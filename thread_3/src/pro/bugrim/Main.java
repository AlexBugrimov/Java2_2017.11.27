package pro.bugrim;

public class Main {

    public static void main(String[] args) {
        Data.value = 0;

        Counter tr1 = new Counter(0, 1000);
        Counter tr2 = new Counter(0, 1000);

        tr1.join();
        tr2.join();

        System.out.println("Global counter in main = " + Data.value);
        System.out.println("AtomicValue = " + Data.aValue.get());
    }
}
