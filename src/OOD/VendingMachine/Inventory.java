package OOD.VendingMachine;

// Inventory: 01: List<String + number> return all available drinks
//            02: boolean available(Drink d)
//            03: Output(Drink d) purchase(Drink d)
//            04: boolean offer(Drink d, int number)

public class Inventory {
    private Drink drink;
    private final static int SIZE = 10;
    private int availableNumber;

    // constructor
    public Inventory(Drink drink) {
        this.drink = drink;
    }

    public Inventory(Drink drink, int availableNumber) {
        this.drink = drink;
        if (availableNumber > SIZE) {
            availableNumber = SIZE;
        }
        this.availableNumber = availableNumber;
    }

    // getter and setter
    public int getAvailableNumber() {
        return this.availableNumber;
    }

    public boolean setAvailableNumber(int number) {
        if (number > SIZE) {
            return false;
        }
        this.availableNumber = number;
        return true;
    }

    public Drink getDrink() {
        return this.drink;
    }

    // Inventory: 01: List<String + number> return all available drinks
    //            02: boolean available(Drink d)
    //            03: Output(Drink d) purchase(Drink d)
    //            04: boolean offer(Drink d, int number)

    public String printAvailableDrink() {
        return "We have " + this.drink.toString() + " with " + Integer.toString(availableNumber) + " bottles." + "/n";
    }

    public boolean available(Drink drink) {
        if (drink.getDrinkType() == this.drink.getDrinkType() && availableNumber > 0) {
            return true;
        }
        return false;
    }

    public boolean availableWithMoney(Drink drink, int inputMoney) {
        if (available(drink) && drink.getPrice() <= inputMoney) {
            return true;
        }
        return false;
    }

    public Drink purchase(Drink drink) {
        setAvailableNumber(availableNumber - 1);
        return drink;
    }

    public boolean offer(Drink drink, int number) {
        if (drink.getDrinkType() != this.drink.getDrinkType()) {
            System.out.println("Not a same type drink for this inventory." + "/n");
            return false;
        }
        if (availableNumber + number > SIZE) {
            System.out.println("Can not offer this much drink for this inventory." + "/n");
            return false;
        }
        setAvailableNumber(availableNumber + number);
        return true;
    }
}
