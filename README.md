REST Countries Boot
=====================

This project is a complete port http://restcountries.eu (https://github.com/fayder/restcountries)
from a `JBoss RestEasy` to `Spring Boot`

-------------------------

# Build

    $./mvnw package
    
An uber-jar is produced is then produced in the `target` directory

# Significant improvements over original

* Less verbose controller code
* All tests run during build and cover more functionality 