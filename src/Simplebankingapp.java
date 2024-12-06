import java.util.*;

class Account {
    String name;
    static int totalAccounts = 0;
    int accountNumber;
    long phoneNumber;
    String address;
    float balance;
    int pin;

    Account(){
        accountNumber=0;
    }

    void init() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        name = sc.nextLine();
        System.out.println("Enter your phone number");
        while (true) {
            try{
                long ph = sc.nextLong();
                int length = (int) Math.log10(ph);
                if(ph<0){
                    System.out.println("Phone number should be a positive value");
                }else if (length == 9) {
                    phoneNumber = ph;
                    break;
                } else {
                    System.out.println("Phone number should be 10 digits.\nPlease Enter again");
                }
            }catch(Exception e){
                System.out.println("Enter a valid phone number");
                sc.nextLine();
            }
        }
        sc.nextLine();
        System.out.println("Enter your address");
        address = sc.nextLine();
        System.out.println("Enter your pin(4 digits): ");
        while (true) {
            try{
                int tempPin = sc.nextInt();
                int length = (int) Math.log10(tempPin);
                if(tempPin<0){
                    System.out.println("Pin should be a positive number.\nPlease Enter again");
                }else if (length == 3) {
                    pin = tempPin;
                    break;
                }else {
                    System.out.println("Pin should be of 4 digits.\nPlease Enter again: ");
                }
            }catch(Exception e){
                System.out.println("Enter a valid pin(Integer)");
                sc.nextLine();
            }
        }
        Account.totalAccounts++;
        accountNumber = Account.totalAccounts;
        balance = 0f;
        System.out.println("\nAccount created successfully!");
        System.out.println("Your account number is: " + accountNumber);
        System.out.println("Your account balance is: " + balance);
    }

    void depositMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter amount to be deposited : ");
        try{
            float amount = sc.nextFloat();
            if(amount<0) System.out.println("Amount can't be negative.");
            else balance+=amount;
            System.out.println("Total account balance is: " + balance);
        }catch(Exception e){
            System.out.println("Enter a valid amount");
            sc.nextLine();
        }
    }

    void withdrawMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter amount to be withdrawn : ");
        try{

            float amount = sc.nextFloat();
            if (amount > balance) {
                System.out.println("Entered amount is more than the available balance");
            }else if(amount<0){
                System.out.println("Amount can not be negative.");
            }else {
                System.out.println("Amount withdrawn");
                balance -= amount;
                System.out.println("Available balance is: " + balance);
            }
        }catch(Exception e){
            System.out.println("Enter a valid amount");
            sc.nextLine();
        }
    }

    void checkBalance() {
        System.out.println("Available balance is : " + balance);
    }

}

class Banking {

    Account validate(ArrayList<Account> accountList) {
        if(accountList.size()==0){
            System.out.println("No account created.");
            return null;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your account number");
        try{
            int tempAccNum = sc.nextInt();
            if(tempAccNum>accountList.size() || tempAccNum<1){
                System.out.println("Invalid account number");
                return null;
            }
            System.out.println("Enter you pin");
            int tempPin = sc.nextInt();
            if (tempPin == accountList.get(tempAccNum - 1).pin) {
                return accountList.get(tempAccNum-1);
            }else{
                System.out.println("Invalid details");
            }
        }catch(Exception e){
            System.out.println("Invalid details!");
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Account> accountList = new ArrayList<>();
        Banking banking = new Banking();
        while (true) {
            System.out.println("\n1. Create Account\n2. Deposit Money\n3. Withdraw Money\n4. Check Balance\n5. Exit");
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    Account account = new Account();
                    account.init();
                    accountList.add(account);
                    break;
                case 2:
                    Account acc = banking.validate(accountList);
                    if(acc!=null){
                        acc.depositMoney();
                    }
                    break;
                case 3:
                    Account acc1 = banking.validate(accountList);
                    if(acc1!=null){
                        acc1.withdrawMoney();
                    }
                    break;
                case 4:
                    Account acc2 = banking.validate(accountList);
                    if(acc2!=null){
                        acc2.checkBalance();
                    }
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}