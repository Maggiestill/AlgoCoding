package OOD.VendingMachine;
import java.util.*;

// APIs:
// VendingMachine: 01: List<String + number> return all available drinks
//                 02: boolean available(Drink d)
//                 03: Output(Drink d, int changeMoney) purchase(Drink d, int moneyInput)
//                 04: boolean offer(Drink d, int number)
//                 05: return boolean resetMoney()


public class VendingMachine {
    private List<Inventory> inventoryList;
    private int moneyBox;

    public VendingMachine(List<Inventory> inventoryList, int initialMoney) {
        this.inventoryList = inventoryList;
        this.moneyBox = initialMoney;
    }

    public int getMoneyBox() {
        return moneyBox;
    }

    public boolean setMoneyBox(int money) {
        if (money < 0) {
            System.out.println("Money Should has a value larger or equals to 0.");
            return false;
        }
        this.moneyBox = money;
        return true;
    }

//    public VendingMachine(int waterNum, int coffeeNum, int juiceNum, int initialMoney) {
//        this.moneyBox = initialMoney;
//        inventoryList = new ArrayList<>();
//        Drink water = new Drink(DrinkType.Water, waterPrice);
//        Inventory inventoryWater = new Inventory()
//    }

    // APIs
    // VendingMachine: 01: List<String + number> return all available drinks
    //                 02: boolean available(Drink d)
    //                 03: Output(Drink d, int changeMoney) purchase(Drink d, int moneyInput)
    //                 04: boolean offer(Drink d, int number)
    //                 05: return boolean resetMoney()

    public List<String> printAllAvailableDrink() {
        List<String> result = new ArrayList<>();
        for (Inventory inventory: inventoryList) {
            result.add(inventory.printAvailableDrink());
        }
        return result;
    }

    public boolean canPurchase(Drink drink, int moneyInput) {
        for (Inventory inventory: inventoryList) {
            if (inventory.getDrink().getDrinkType() == drink.getDrinkType()) {
                if (inventory.availableWithMoney(drink, moneyInput)) {

                }
            }
        }

    }





}
