interface BankInterface {
    double getBalance();
    double getInterestRate();
}

class BankA implements BankInterface {
    private double balance = 10000;
    
    public double getBalance() {
        return balance;
    }
    
    public double getInterestRate() {
        return 7.0;
    }
}

class BankB implements BankInterface {
    private double balance = 150000;
    
    public double getBalance() {
        return balance;
    }
    
    public double getInterestRate() {
        return 7.4;
    }
}

class BankC implements BankInterface {
    private double balance = 200000;
    
    public double getBalance() {
        return balance;
    }
    
    public double getInterestRate() {
        return 7.9;
    }
}

public class Main {
    public static void main(String[] args) {
        BankA bankA = new BankA();
        BankB bankB = new BankB();
        BankC bankC = new BankC();
        
        System.out.println("Bank A - Balance: $" + bankA.getBalance() + ", Interest Rate: " + bankA.getInterestRate() + "%");
        System.out.println("Bank B - Balance: $" + bankB.getBalance() + ", Interest Rate: " + bankB.getInterestRate() + "%");
        System.out.println("Bank C - Balance: $" + bankC.getBalance() + ", Interest Rate: " + bankC.getInterestRate() + "%");
    }
}
