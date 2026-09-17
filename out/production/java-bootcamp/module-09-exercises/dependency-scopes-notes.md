````text

| Dependency need | Scope |

| ---------------

&#x20;| ----- |

| JUnit Jupiter used only in `src/test/java` | `test` |

| Spring Context API called from production sources (Lab 9 learning placeholder) | `compile` |

| JDBC driver you never import in Java source but need at runtime later | `runtime` |

| API the application server will provide in production | `provided` |



Bad:





<dependency>

&#x20; <groupId>org.junit.jupiter</groupId>

&#x20; <artifactId>junit-jupiter</artifactId>

&#x20; <version>5.11.4</version>

&#x20; <!-- no scope — defaults to compile -->

</dependency>



Why this is wrong:



JUnit becomes a production dependency even though it is only needed for testing. This adds unnecessary files to the production application and puts JUnit on the production classpath.



Team rule:



Use the smallest Maven dependency scope that matches how the dependency is actually needed.





