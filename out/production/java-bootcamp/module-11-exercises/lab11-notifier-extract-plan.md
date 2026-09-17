public interface CustomerNotifier {

&#x20;   void notifyStatusChange(String customerId,

&#x20;                           CustomerStatus from,

&#x20;                           CustomerStatus to);

}

```



\*\*3. Why this helps Copilot (one sentence)\*\*  

Stronger prompts name `CustomerNotifier` so AI does not bury I/O inside `CustomerService`.

