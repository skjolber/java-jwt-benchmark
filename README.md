#java-jwt-benchmark

[![Build Status](https://travis-ci.org/skjolber/java-jwt-benchmark.svg?branch=master)](https://travis-ci.org/skjolber/java-jwt-benchmark)

# java-jwt-benchmark
Project for benchmarking popular Json Web Token (JWT) frameworks for Java using JMH.

Supported frameworks: 
  * java-jwt from Auth0

## License
[Apache 2.0]

# Obtain
The project is based on [Gradle].

# Usage
```
./gradlew clean jmhClasses jmh
```
## Benchmarks

  * Parse token
  * Verify token
  * Verify token, get claim.

# History

 - 1.0.0: Initial version

[Apache 2.0]:          	http://www.apache.org/licenses/LICENSE-2.0.html
[issue-tracker]:       	https://github.com/skjolber/java-jwt-benchmark/issues
[Gradle]:               https://gradle.org/
