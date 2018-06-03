[![Build Status](https://travis-ci.org/skjolber/java-jwt-benchmark.svg?branch=master)](https://travis-ci.org/skjolber/java-jwt-benchmark)

# java-jwt-benchmark
Project for benchmarking popular Json Web Token (JWT) frameworks for Java using [JMH].

Supported frameworks: 
  * [java-jwt] from [Auth0]
  * [jjwt] from jwtk

## License
[Apache 2.0]

# Obtain
The project is based on [Gradle].

# Usage
Modify the build version to your current snapshot, then run 

```
./gradlew --stop && ./gradlew clean jmhClasses jmh --refresh-dependencies --info
```

The JMH plugin seems to have trouble refreshing the project, so restart the Gradle deamon before running.
## Benchmarks

  * Parse token
  * Verify token
  * Verify token, get claim.

# History

 - 1.0.0: Initial version

[Apache 2.0]:          	http://www.apache.org/licenses/LICENSE-2.0.html
[issue-tracker]:       	https://github.com/skjolber/java-jwt-benchmark/issues
[Gradle]:               https://gradle.org/
[java-jwt]:				https://github.com/auth0/java-jwt
[Auth0]:				https://auth0.com
[JMH]:					http://openjdk.java.net/projects/code-tools/jmh/
[jjwt]:					https://github.com/jwtk/jjwt
