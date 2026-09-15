&#x20;Lab 10 pre-lab prompts



\## Weak

Write a customer class.

List<Employee> sorted = list

Why weak: I does not have any specificities like the name of the class  or how the dev would like it sorted 



\## Strong

List<Employee> sorted = employees.stream()

.sorted(Comparator.comparing(Employee::getSalary).reversed())

.collect(Collectors.toList())



\## Three constraints

1\. Jdk 21 (version)

2\. domain fixtures

3\. staus: Active

```

