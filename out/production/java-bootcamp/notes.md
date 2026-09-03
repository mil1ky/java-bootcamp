

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

