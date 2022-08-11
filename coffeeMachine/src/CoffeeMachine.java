import java.util.Scanner;

public class CoffeeMachine {
    private final Integer EXPRESSO_WATER = 250;
    private final Integer EXPRESSO_COFFEEBEANS = 16;
    private final Integer EXPRESSO_PRICE = 4;

    private final Integer LATTE_WATER = 350;
    private final Integer LATTE_MILK = 75;
    private final Integer LATTE_COFFEEBEANS = 20;
    private final Integer LATTE_PRICE = 7;

    private final Integer CAPPUCCINO_WATER = 200;
    private final Integer CAPPUCCINO_MILK = 100;
    private final Integer CAPPUCCINO_COFFEEBEANS = 12;
    private final Integer CAPPUCCINO_PRICE = 6;
    Boolean isMachineOn = true;
    Scanner scanner = new Scanner(System.in);
    Integer totalWater = 400;
    Integer totalMilk = 540;
    Integer totalCoffeeBeans = 120;
    Integer totalDisposableCups = 9;
    Integer totalMoney = 550;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String answer;

        CoffeeMachine coffeeMachine = new CoffeeMachine();

        coffeeMachine.initCoffeeMachine();

    }
    public static boolean isInteger(String answer) {
        for (int i = 0; i < answer.length(); i++) {
            Character character = answer.charAt(i);
            if (!Character.isDigit(character)) {
                System.out.println("You must enter only numbers");
                return false;
            }
        }
        return true;
    }

    public void initCoffeeMachine(){
        String answer;
        do {
            System.out.println("");
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            do {
                answer = scanner.nextLine();
            }while (!answer.equalsIgnoreCase("buy") &&
                    !answer.equalsIgnoreCase("fill") &&
                    !answer.equalsIgnoreCase("take") &&
                    !answer.equalsIgnoreCase("remaining") &&
                    !answer.equalsIgnoreCase("exit"));

            answer.toLowerCase();

            switch (answer) {
                case "buy":
                    sellCoffee();
                    break;
                case "fill":
                    fillCoffeeMachine();
                    break;
                case "take":
                    gaveMoney();
                    break;
                case "remaining":
                    printTotal();
                    break;
                case "exit":
                    isMachineOn = false;
                    break;
            }
        } while (isMachineOn);
    }

    public void gaveMoney() {
        System.out.println("I gave you $" + totalMoney);
        totalMoney = 0;
    }
    public void fillCoffeeMachine() {
        String answer;
        System.out.println("Write how many ml of water you want to add:");
        do {
            answer = scanner.nextLine();
        }while (!isInteger(answer));
        totalWater += Integer.parseInt(answer);

        System.out.println("Write how many ml of milk you want to add: ");
        do {
            answer = scanner.nextLine();
        }while (!isInteger(answer));
        totalMilk += Integer.parseInt(answer);

        System.out.println("Write how many grams of coffee beans you want to add: ");
        do {
            answer = scanner.nextLine();
        }while (!isInteger(answer));
        totalCoffeeBeans += Integer.parseInt(answer);

        System.out.println("Write how many disposable cups of coffee you want to add:");
        do {
            answer = scanner.nextLine();
        }while (!isInteger(answer));
        totalDisposableCups += Integer.parseInt(answer);
    }

    public void canMakeCoffee(Integer typeOfCoffee){
        if (typeOfCoffee == 1) {
            if(totalWater >= EXPRESSO_WATER && totalCoffeeBeans >= EXPRESSO_COFFEEBEANS) {
                System.out.println("I have enough resources, making you a coffee!");
                updateTotalAfterSell(typeOfCoffee);
                return;
            }
            //Sorry, not enough water!
            if (totalWater < EXPRESSO_WATER)
                System.out.println("Sorry, not enough water!");
            if (totalCoffeeBeans < EXPRESSO_COFFEEBEANS)
                System.out.println("Sorry, not enough coffee beans!");

            return;
        }

        if (typeOfCoffee == 2) {
            if(totalWater >= LATTE_WATER && totalMilk >= LATTE_MILK && totalCoffeeBeans >= LATTE_COFFEEBEANS) {
                System.out.println("I have enough resources, making you a coffee!");
                updateTotalAfterSell(typeOfCoffee);
                return;
            }

            if (totalWater < LATTE_WATER)
                System.out.println("Sorry, not enough water!");
            if (totalCoffeeBeans < LATTE_COFFEEBEANS)
                System.out.println("Sorry, not enough coffee beans!");
            if (totalMilk < LATTE_MILK)
                System.out.println("Sorry, not enough milk");

            return;
        }

        if (typeOfCoffee == 3) {
            if(totalWater >= CAPPUCCINO_WATER && totalMilk >= CAPPUCCINO_MILK && totalCoffeeBeans >= CAPPUCCINO_COFFEEBEANS) {
                System.out.println("I have enough resources, making you a coffee!");
                updateTotalAfterSell(typeOfCoffee);
                return;
            }

            if (totalWater <= CAPPUCCINO_WATER)
                System.out.println("Sorry, not enough water!");
            if (totalCoffeeBeans < CAPPUCCINO_COFFEEBEANS)
                System.out.println("Sorry, not enough coffee beans!");
            if (totalMilk < CAPPUCCINO_MILK)
                System.out.println("Sorry, not enough milk");

            return;
        }
    }

    public void sellCoffee() {
        String answer;
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        do{
            answer = scanner.nextLine();
        } while (!answer.equals("1") && !answer.equals("2") && !answer.equals("3") && !answer.equalsIgnoreCase("back"));

        if (answer.equalsIgnoreCase("back")) {
            return;
        }

        Integer typeOfCoffee = Integer.parseInt(answer);

        canMakeCoffee(typeOfCoffee);
    }

    public void updateTotalAfterSell(Integer typeOfCoffee) {
        //1 - espresso, 2 - latte, 3 - cappuccino
        totalDisposableCups--;

        if(typeOfCoffee == 1) {
            this.totalWater -= EXPRESSO_WATER;
            this.totalCoffeeBeans -= EXPRESSO_COFFEEBEANS;
            this.totalMoney += EXPRESSO_PRICE;

            return;
        }
        if(typeOfCoffee == 2) {
            this.totalWater -= LATTE_WATER;
            this.totalMilk -= LATTE_MILK;
            this.totalCoffeeBeans -= LATTE_COFFEEBEANS;
            this.totalMoney += LATTE_PRICE;

            return;
        }

        this.totalWater -= CAPPUCCINO_WATER;
        this.totalMilk -= CAPPUCCINO_MILK;
        this.totalCoffeeBeans -= CAPPUCCINO_COFFEEBEANS;
        this.totalMoney += CAPPUCCINO_PRICE;
    }

    public void printTotal() {
        System.out.println("");
        System.out.println("The coffee machine has:");
        System.out.println(totalWater + " ml of water");
        System.out.println(totalMilk + " ml of milk");
        System.out.println(totalCoffeeBeans + " g of coffee beans");
        System.out.println(totalDisposableCups + " disposable cups");
        System.out.println("$" + totalMoney + " of money");
    }
}