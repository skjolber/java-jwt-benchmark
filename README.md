[![Build Status](https://travis-ci.org/skjolber/java-jwt-benchmark.svg?branch=master)](https://travis-ci.org/skjolber/java-jwt-benchmark)

# java-jwt-benchmark
Project for benchmarking popular Json Web Token (JWT) frameworks for Java using [JMH].

Supported frameworks: 
  * [java-jwt] from [Auth0]
  * [jjwt] from jwtk
  * [Okta JWT Verifier for Java] from Okta
  * [fusionauth-jwt] from [FusionAuth]

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

  * Parse token (if possible)
  * Verify token
  * Verify token, get claim.

## Results
Running for OpenJDK 1.8.0 build 275-b01, Fedora Linux 5.10.7-200, AMD 5950x.

A [visualization_v1.3.0] is available, summery:

| Framework | Version | Verify (op/s) | Claim (op/s) | Parse (op/s)
| --------- | ----- |----- | ----- | ----- |
|[java-jwt] | 3.12.0/0.15.0 | 30.1k | 29.9k | 387k |
|[fusionauth-jwt]| 4.0.1 | 32.6k | 32.4k | 1196k |
|[jjwt] | 0.9.1 | 31.4k | 30.7k | |
|[Nimbus-JOSE-JWT]| 9.4.1 | 19.7k | 18.9k | 945k |
|[Okta JWT Verifier for Java]| 0.5.0 | 1.3k | 1.3k | |

In short, [fusionauth-jwt] is the new fastest parser. Parsing without validation is now faster than before for java-jwt, otherwise no improvements.

## Results
Running for OpenJDK 1.8.0 build 232, Linux 5.3.7.

A [visualization_v1.2.0] is available, summery:

| Framework | Version | Verify (op/s) | Claim (op/s) | Parse (op/s)
| --------- | ----- |----- | ----- | ----- |
|[java-jwt] | 3.8.3/0.9.0 | 14.6k | 14.6k | 230.8k |
|[jjwt] | 0.9.1 | 14.9k | 14.5k | |
|[Okta JWT Verifier for Java]| 0.4.0 | 0.6k | 0.6k | |
|[fusionauth-jwt]| 3.1.6 | 15.9k | 15.7k | 581.5k |

In short, [fusionauth-jwt] is the new fastest parser. Parsing without validation is now faster than before for java-jwt, otherwise no improvements.

# Previous results
Note that **the relative number matters**, benchmarks are not necessarily run on the same machine, operating system or Java version.

## Version 1.1.0:
Running for OpenJDK 1.8.0 build 191.

A [visualization_v1.1.0] is available, summery:

| Framework | Version | Verify (op/s) | Claim (op/s) |
| --------- | ----- |----- | ----- |
|[java-jwt] | 3.7.0/0.7.0 | 14.5k | 14.6k |
|[jjwt] | 0.9.1 | 14.7k | 14.9k |
|[Okta JWT Verifier for Java]| 0.4.0 | 0.61k | 0.61k |

In short, **java-jwt did catch up with jjwt, they were essentially just as fast**. 
The Okta JWT verifiser regressed into a total disaster. 

Only [java-jwt] seems to parse without validation, at about 52.3k operations per second.

## Version 1.0.0:

Running for Oracle JDK 1.8 build unknown-

A [visualization_v1.0.0] is available, summery:

| Framework | Version | Verify (op/s) | Claim (op/s) |
| --------- | ----- |----- | ----- |
|[java-jwt] | 3.3.0/0.4.0 | 14.2k | 13.9k |
|[jjwt] | 0.9.0 | 19k | 18.3k |
|[Okta JWT Verifier for Java]| 0.3.0 | 17.1k | 17.1k |

Only [java-jwt] seems to parse without validation, at about 65.2k operations per second.

# History

 - 1.3.0: Updated dependencies, added Nimbus JOSE + JWT
 - 1.2.0: Added FusionAuth
 - 1.1.0: Bumped versions after accepted performance-enhancing [PR#255] for java-jwt.
 - 1.0.0: Initial version

[Apache 2.0]:          			http://www.apache.org/licenses/LICENSE-2.0.html
[issue-tracker]:       			https://github.com/skjolber/java-jwt-benchmark/issues
[Gradle]:              		 	https://gradle.org/
[java-jwt]:				https://github.com/auth0/java-jwt
[Auth0]:				https://auth0.com
[JMH]:					http://openjdk.java.net/projects/code-tools/jmh/
[jjwt]:					https://github.com/jwtk/jjwt
[Okta JWT Verifier for Java]: 		https://github.com/okta/okta-jwt-verifier-java
[visualization_v1.0.0]:			https://skjolber.github.io/java-jwt-benchmark/jmh_v1.0.0/index.html
[visualization_v1.1.0]:			https://skjolber.github.io/java-jwt-benchmark/jmh_v1.1.0/index.html
[visualization_v1.2.0]:			https://skjolber.github.io/java-jwt-benchmark/jmh_v1.2.0/index.html
[visualization_v1.3.0]:			https://skjolber.github.io/java-jwt-benchmark/jmh_v1.3.0/index.html
[PR#255]:				https://github.com/auth0/java-jwt/pull/255
[fusionauth-jwt]:			https://github.com/FusionAuth/fusionauth-jwt
[FusionAuth]:				https://fusionauth.io/
[Nimbus-JOSE-JWT]:				https://bitbucket.org/connect2id/nimbus-jose-jwt/src/master/
