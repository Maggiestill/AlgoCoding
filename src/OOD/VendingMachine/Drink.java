package OOD.VendingMachine;

public class Drink {
    private DrinkType drinkType;
    private int price;

    public Drink(DrinkType drinkType, int price) {
        this.drinkType = drinkType;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }
}
