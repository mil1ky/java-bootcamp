| Entity | Identity | Important attributes | Main responsibility |

| ------ | -------- | -------------------- | ------------------- |

| Customer | customerId | name, email, phone | Maintain customer profile |

| Account | accountNumber | owner, balance, accountType | Protect balance and perform deposits/withdrawals |

| Transaction | transactionId | account, type, amount, timestamp | Record one account operation |







\# Relationships



\- One Customer can own zero or more Accounts.

\- One Account belongs to exactly one Customer.

\- One Account can have many Transactions.

\- One Transaction belongs to exactly one Account.



\## Rules



\- An account balance cannot be changed directly from outside Account.

\- A deposit amount must be positive.

\- A withdrawal cannot exceed the allowed balance.



Account should decide if a withdrawal is allowed because it knows how much money is in the account. Main should just ask the account to withdraw money.





The original method could change because the formula changes or because

the output format changes. These are separate responsibilities.





An object is not collectible merely because one reference becomes null.

It becomes GC-eligible only when no live strong-reference path can reach it.

Eligibility does not guarantee immediate collection, and System.gc() is only

a request.



Command:

java -XX:+UseG1GC -Xms16m -Xmx64m -Xlog:gc GcObserve



Evidence:

The log began with "Using G1" and showed G1 evacuation pauses.

The collector flag selects G1; it does not guarantee a particular pause time.



Command:

java -XX:+UseZGC -Xms16m -Xmx64m -Xlog:gc GcObserve



Evidence:

The log began with "Using The Z Garbage Collector" instead of "Using G1".

Pause-related log lines look different — ZGC does most of its work concurrently,

so it does not report the same kind of stop-the-world "Evacuation Pause" G1 does.



loaded RetentionDemo class

&#x20; → static CACHE field

&#x20; → ArrayList entries

&#x20; → byte\[] objects

