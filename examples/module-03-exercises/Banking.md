

classDiagram

&#x20;   class Printable {

&#x20;       <<interface>>

&#x20;       +printDetails() void

&#x20;   }



&#x20;   class Customer {

&#x20;       -String id

&#x20;       -String name

&#x20;       +printDetails() void

&#x20;   }



&#x20;   class Account {

&#x20;       -double balance

&#x20;       +deposit(double amount) void

&#x20;       +withdraw(double amount) boolean

&#x20;       +getBalance() double

&#x20;       +getAccountType() String

&#x20;   }



&#x20;   class SavingsAccount {

&#x20;       +getAccountType() String

&#x20;   }



&#x20;   class CurrentAccount {

&#x20;       -double WITHDRAWAL\_FEE

&#x20;       +withdraw(double amount) boolean

&#x20;       +getAccountType() String

&#x20;   }



&#x20;   class Transaction {

&#x20;       -String transactionId

&#x20;       -String type

&#x20;       -double amount

&#x20;   }



&#x20;   Printable <|.. Customer : implements

&#x20;   Account <|-- SavingsAccount : extends

&#x20;   Account <|-- CurrentAccount : extends

&#x20;   Customer "1" --> "0..\*" Account : owns

&#x20;   Account "1" --> "0..\*" Transaction : records

