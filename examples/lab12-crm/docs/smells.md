# Lab 12 — Code Smells

## Baseline

The intentionally messy baseline is:

`src/main/java/com/northstar/crm/service/CustomerService.java`

The primary CRM scenario used for impact analysis is customer `CUS-1001` (Amina Khan).

## 1. Poor Naming

**Example:** `doStuff`, `data`, and parameters `a`, `b`, `c`, `d`, and `e`.

**Location:** `CustomerService.java`, `doStuff(...)`.

**Impact:** The method's purpose and parameter meanings are unclear. A developer supporting customer `CUS-1001` has to inspect comments and implementation details to determine which value represents the customer ID, name, email, phone, and status.

**Refactoring direction:** Replace the generic method and parameter names with intention-revealing names such as `createCustomer`, `customerId`, `fullName`, `email`, `phone`, and `status`.

---

## 2. Raw Types

**Example:** `List data = new ArrayList();`

**Location:** `CustomerService.java`, field declaration.

**Impact:** The service loses compile-time type safety and requires casts such as `(Customer) data.get(i)`. Invalid objects could be inserted into the collection and cause runtime failures while supporting customer records such as `CUS-1001`.

**Refactoring direction:** Use a typed collection such as `Map<String, Customer>` or `List<Customer>`.

---

## 3. Long Method / Mixed Responsibilities

**Example:** `doStuff(...)` creates a customer, validates input, checks duplicates, assigns status, logs output, and contains a separate update path.

**Location:** `CustomerService.java`, `doStuff(...)`.

**Impact:** Multiple responsibilities are combined into one method, making changes risky. A developer trying to modify creation behavior for `CUS-1001` could unintentionally affect the embedded update behavior.

**Refactoring direction:** Separate creation, lookup, validation, and status-update responsibilities into focused methods.

---

## 4. Stringly-Typed Status

**Example:** `e.equals("ACTIVE")`, `e.equals("PROSPECT")`, `e.equals("SUSPENDED")`, and `e.equals("CLOSED")`.

**Location:** `CustomerService.java`, status handling inside `doStuff(...)`.

**Impact:** Status values are represented as strings and compared manually. A typo or unsupported string can silently produce incorrect behavior. This makes customer status handling less reliable for records such as `CUS-1001`.

**Refactoring direction:** Accept `CustomerStatus` directly in the public service API and avoid repeated string comparisons.

---

## 5. Incorrect Equality

**Example:**

`if (x.getCustomerId() == id)`

**Location:** `CustomerService.java`, `get(...)`.

**Impact:** `==` compares String object references rather than String contents. A lookup for `CUS-1001` can therefore fail when the ID has the same characters but is stored in a different String object. This can cause an existing customer to be incorrectly treated as not found.

**Refactoring direction:** Use content equality such as `equals`, `Objects.equals`, or a typed map lookup by customer ID.

---

## 6. Null as Control Flow

**Example:** `return null` when validation fails, a duplicate is found, or a customer cannot be found.

**Location:** `CustomerService.java`, `doStuff(...)` and `get(...)`.

**Impact:** Callers cannot distinguish normal absence from an error without additional null checks. A caller retrieving `CUS-1001` could encounter a null result and potentially fail later with a NullPointerException.

**Refactoring direction:** Throw meaningful exceptions for invalid, duplicate, or unknown customer operations.

---

## 7. Side-Effect Logging

**Example:** `System.out.println("bad")`, `System.out.println("dup")`, `System.out.println("ok " + a)`, and `System.out.println("upd")`.

**Location:** `CustomerService.java`.

**Impact:** Business logic is directly coupled to console output. These messages are difficult to manage or correlate in a real CRM application and do not provide structured support information for a customer such as `CUS-1001`.

**Refactoring direction:** Remove direct console logging from the service or use an appropriate logging mechanism with correlation information where required.

---

## 8. Magic Behavior Based on Customer Name

**Example:**

`if (b != null && b.contains("UPDATE"))`

**Location:** `CustomerService.java`, update path inside `doStuff(...)`.

**Impact:** A customer's name controls whether update behavior occurs. This creates hidden behavior: a value containing `"UPDATE"` can cause a status modification while creating a customer. This is especially risky because unrelated customer data could unexpectedly trigger an update.

**Refactoring direction:** Remove the magic `"UPDATE"` branch and provide an explicit `updateStatus(customerId, newStatus)` operation.

---

## Summary

The baseline combines unclear naming, weak typing, mixed responsibilities, string-based status handling, incorrect String equality, null-based error handling, console side effects, and hidden behavior.

The main CRM impact is that ordinary operations involving customers such as `CUS-1001` are harder to understand, test, support, and modify safely.
