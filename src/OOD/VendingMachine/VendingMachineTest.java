package OOD.VendingMachine;

public class VendingMachineTest {
}


// For each vending machine has few inventory's, which has the different drink

// classes:
// VendingMachine:
// Inventory:
// Drink:

// APIs:
// VendingMachine: 01: List<String + number> return all available drinks
//                 02: boolean available(Drink d)
//                 03: Output(Drink d, int changeMoney) purchase(Drink d, int moneyInput)
//                 04: boolean offer(Drink d, int number)
//                 05: return boolean resetMoney()

// Inventory: 01: List<String + number> return all available drinks
//            02: boolean available(Drink d)
//            03: Output(Drink d) purchase(Drink d)
//            04: boolean offer(Drink d, int number)

// Drink: enum Type: water, coffee, juice
//        int price