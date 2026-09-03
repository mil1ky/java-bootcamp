public class InheritanceDemo {
 //   public static void main(String[] args) {
 //    // TODO: base-type array holding SavingsAccount(100) and CurrentAccount(100)
 //    Account[] accounts = {new SavingsAccount(100), new CurrentAccount(100)};

 //    for (Account account : accounts) {
 //        // Runtime type chooses the overridden method.
 //        account.withdraw(20.00);
 //        System.out.printf("%s balance: %.2f%n",
 //                account.getAccountType(),
 //                account.getBalance());
 //    }
 //}
 public static void main(String[] args) {
     // TODO: add FrozenAccount(100) — same loop, no special-casing
     Account[] accounts = {
             new SavingsAccount(100.00),
             new CurrentAccount(100.00),
             new FrozenAccount(100.00)
     };

     for (Account account : accounts) {
         // TODO: capture withdraw result; print type, ok flag, and balance
         boolean ok = account.withdraw(20.00);
         System.out.printf("%s withdraw=%s balance=%.2f%n",
                 account.getAccountType(),ok, account.getBalance());
     }
 }
}
