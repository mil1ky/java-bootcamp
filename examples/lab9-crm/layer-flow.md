1\. Client sends create request (correlation ID `lab-request-001`)

2\. `CustomerController` accepts `CustomerRequest` (validation at this boundary later)

3\. `CustomerService` applies business rules (unique ID, status defaults → `ACTIVE`)

4\. `CustomerRepository` stores `Customer` entity (in-memory list first; PostgreSQL later)

5\. Response DTO returns `CUS-1001` / `ACTIVE` without leaking internal storage type



Include a text or Mermaid diagram. Explicitly mark Angular, Kafka, and PostgreSQL as \*\*FUTURE / out of scope for Lab 8\*\*.

