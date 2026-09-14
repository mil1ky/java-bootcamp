| Intent | Command |

| ------ | ------- |

| Confirm POM parses before coding further | Validate|

| Compile production Java only (stops before tests if you use the phase carefully) | Compile|

| Run unit tests | Tests|

| Produce `target/customer-service.jar` |package  |

| Run package plus verification checks CI cares about | Verify|

| Put the JAR into your local Maven cache |Install |





\- `package` : 4 

\- `validate`: 1  

\- `install`: 6 

\- `compile`: 2 

\- `verify`: 5 

\- `test`: 3 





Write:



> Continuous Integration usually runs `mvn -B verify` so the build is batch/non-interactive and stops after verification without casually installing or deploying from every laptop.

