// provided.js - generated by gradle-jmh-report, 2021-01-17 12:58:57.848

var providedBenchmarks = ['results'];

var providedBenchmarkStore = {
'results': 
[
    {
        "jmhVersion" : "1.27",
        "benchmark" : "com.github.skjolber.jwt.JwtClaimBenchmark.auth0_claim",
        "mode" : "thrpt",
        "threads" : 1,
        "forks" : 1,
        "jvm" : "/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.275.b01-6.fc33.x86_64/jre/bin/java",
        "jvmArgs" : [
            "-XX:+UseG1GC"
        ],
        "jdkVersion" : "1.8.0_275",
        "vmName" : "OpenJDK 64-Bit Server VM",
        "vmVersion" : "25.275-b01",
        "warmupIterations" : 10,
        "warmupTime" : "1 s",
        "warmupBatchSize" : 1,
        "measurementIterations" : 5,
        "measurementTime" : "5 s",
        "measurementBatchSize" : 1,
        "primaryMetric" : {
            "score" : 29911.33934446535,
            "scoreError" : 64.83595516894678,
            "scoreConfidence" : [
                29846.503389296402,
                29976.175299634295
            ],
            "scorePercentiles" : {
                "0.0" : 29898.33082373041,
                "50.0" : 29907.940751867227,
                "90.0" : 29939.49949032005,
                "95.0" : 29939.49949032005,
                "99.0" : 29939.49949032005,
                "99.9" : 29939.49949032005,
                "99.99" : 29939.49949032005,
                "99.999" : 29939.49949032005,
                "99.9999" : 29939.49949032005,
                "100.0" : 29939.49949032005
            },
            "scoreUnit" : "ops/s",
            "rawData" : [
                [
                    29898.710099223943,
                    29912.215557185114,
                    29939.49949032005,
                    29907.940751867227,
                    29898.33082373041
                ]
            ]
        },
        "secondaryMetrics" : {
        }
    },
    {
        "jmhVersion" : "1.27",
        "benchmark" : "com.github.skjolber.jwt.JwtClaimBenchmark.fusionauth_claim",
        "mode" : "thrpt",
        "threads" : 1,
        "forks" : 1,
        "jvm" : "/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.275.b01-6.fc33.x86_64/jre/bin/java",
        "jvmArgs" : [
            "-XX:+UseG1GC"
        ],
        "jdkVersion" : "1.8.0_275",
        "vmName" : "OpenJDK 64-Bit Server VM",
        "vmVersion" : "25.275-b01",
        "warmupIterations" : 10,
        "warmupTime" : "1 s",
        "warmupBatchSize" : 1,
        "measurementIterations" : 5,
        "measurementTime" : "5 s",
        "measurementBatchSize" : 1,
        "primaryMetric" : {
            "score" : 32445.23945524598,
            "scoreError" : 91.37609026175564,
            "scoreConfidence" : [
                32353.863364984227,
                32536.615545507735
            ],
            "scorePercentiles" : {
                "0.0" : 32414.619503140497,
                "50.0" : 32442.64067043029,
                "90.0" : 32479.740111872434,
                "95.0" : 32479.740111872434,
                "99.0" : 32479.740111872434,
                "99.9" : 32479.740111872434,
                "99.99" : 32479.740111872434,
                "99.999" : 32479.740111872434,
                "99.9999" : 32479.740111872434,
                "100.0" : 32479.740111872434
            },
            "scoreUnit" : "ops/s",
            "rawData" : [
                [
                    32436.94948372237,
                    32414.619503140497,
                    32442.64067043029,
                    32452.24750706432,
                    32479.740111872434
                ]
            ]
        },
        "secondaryMetrics" : {
        }
    },
    {
        "jmhVersion" : "1.27",
        "benchmark" : "com.github.skjolber.jwt.JwtClaimBenchmark.jjwt_claim",
        "mode" : "thrpt",
        "threads" : 1,
        "forks" : 1,
        "jvm" : "/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.275.b01-6.fc33.x86_64/jre/bin/java",
        "jvmArgs" : [
            "-XX:+UseG1GC"
        ],
        "jdkVersion" : "1.8.0_275",
        "vmName" : "OpenJDK 64-Bit Server VM",
        "vmVersion" : "25.275-b01",
        "warmupIterations" : 10,
        "warmupTime" : "1 s",
        "warmupBatchSize" : 1,
        "measurementIterations" : 5,
        "measurementTime" : "5 s",
        "measurementBatchSize" : 1,
        "primaryMetric" : {
            "score" : 30753.60454289777,
            "scoreError" : 64.34651390753189,
            "scoreConfidence" : [
                30689.25802899024,
                30817.9510568053
            ],
            "scorePercentiles" : {
                "0.0" : 30731.02727813703,
                "50.0" : 30754.909656844895,
                "90.0" : 30777.277623590537,
                "95.0" : 30777.277623590537,
                "99.0" : 30777.277623590537,
                "99.9" : 30777.277623590537,
                "99.99" : 30777.277623590537,
                "99.999" : 30777.277623590537,
                "99.9999" : 30777.277623590537,
                "100.0" : 30777.277623590537
            },
            "scoreUnit" : "ops/s",
            "rawData" : [
                [
                    30754.909656844895,
                    30747.808348477574,
                    30777.277623590537,
                    30756.99980743881,
                    30731.02727813703
                ]
            ]
        },
        "secondaryMetrics" : {
        }
    },
    {
        "jmhVersion" : "1.27",
        "benchmark" : "com.github.skjolber.jwt.JwtClaimBenchmark.nimbus_claim",
        "mode" : "thrpt",
        "threads" : 1,
        "forks" : 1,
        "jvm" : "/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.275.b01-6.fc33.x86_64/jre/bin/java",
        "jvmArgs" : [
            "-XX:+UseG1GC"
        ],
        "jdkVersion" : "1.8.0_275",
        "vmName" : "OpenJDK 64-Bit Server VM",
        "vmVersion" : "25.275-b01",
        "warmupIterations" : 10,
        "warmupTime" : "1 s",
        "warmupBatchSize" : 1,
        "measurementIterations" : 5,
        "measurementTime" : "5 s",
        "measurementBatchSize" : 1,
        "primaryMetric" : {
            "score" : 18948.23032154543,
            "scoreError" : 38.86311350587496,
            "scoreConfidence" : [
                18909.367208039555,
                18987.093435051305
            ],
            "scorePercentiles" : {
                "0.0" : 18932.399454797694,
                "50.0" : 18953.051184078082,
                "90.0" : 18957.460044836545,
                "95.0" : 18957.460044836545,
                "99.0" : 18957.460044836545,
                "99.9" : 18957.460044836545,
                "99.99" : 18957.460044836545,
                "99.999" : 18957.460044836545,
                "99.9999" : 18957.460044836545,
                "100.0" : 18957.460044836545
            },
            "scoreUnit" : "ops/s",
            "rawData" : [
                [
                    18957.460044836545,
                    18953.958588840007,
                    18944.282335174827,
                    18953.051184078082,
                    18932.399454797694
                ]
            ]
        },
        "secondaryMetrics" : {
        }
    },
    {
        "jmhVersion" : "1.27",
        "benchmark" : "com.github.skjolber.jwt.JwtClaimBenchmark.okta_claim",
        "mode" : "thrpt",
        "threads" : 1,
        "forks" : 1,
        "jvm" : "/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.275.b01-6.fc33.x86_64/jre/bin/java",
        "jvmArgs" : [
            "-XX:+UseG1GC"
        ],
        "jdkVersion" : "1.8.0_275",
        "vmName" : "OpenJDK 64-Bit Server VM",
        "vmVersion" : "25.275-b01",
        "warmupIterations" : 10,
        "warmupTime" : "1 s",
        "warmupBatchSize" : 1,
        "measurementIterations" : 5,
        "measurementTime" : "5 s",
        "measurementBatchSize" : 1,
        "primaryMetric" : {
            "score" : 1327.593136127652,
            "scoreError" : 2.3847275827943024,
            "scoreConfidence" : [
                1325.2084085448578,
                1329.9778637104464
            ],
            "scorePercentiles" : {
                "0.0" : 1327.1098948120039,
                "50.0" : 1327.1748630994882,
                "90.0" : 1328.3483320319378,
                "95.0" : 1328.3483320319378,
                "99.0" : 1328.3483320319378,
                "99.9" : 1328.3483320319378,
                "99.99" : 1328.3483320319378,
                "99.999" : 1328.3483320319378,
                "99.9999" : 1328.3483320319378,
                "100.0" : 1328.3483320319378
            },
            "scoreUnit" : "ops/s",
            "rawData" : [
                [
                    1327.1748630994882,
                    1328.1881481463429,
                    1327.1444425484883,
                    1327.1098948120039,
                    1328.3483320319378
                ]
            ]
        },
        "secondaryMetrics" : {
        }
    },
    {
        "jmhVersion" : "1.27",
        "benchmark" : "com.github.skjolber.jwt.JwtParseBenchmark.auth0_parse",
        "mode" : "thrpt",
        "threads" : 1,
        "forks" : 1,
        "jvm" : "/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.275.b01-6.fc33.x86_64/jre/bin/java",
        "jvmArgs" : [
            "-XX:+UseG1GC"
        ],
        "jdkVersion" : "1.8.0_275",
        "vmName" : "OpenJDK 64-Bit Server VM",
        "vmVersion" : "25.275-b01",
        "warmupIterations" : 10,
        "warmupTime" : "1 s",
        "warmupBatchSize" : 1,
        "measurementIterations" : 5,
        "measurementTime" : "5 s",
        "measurementBatchSize" : 1,
        "primaryMetric" : {
            "score" : 387059.5421067922,
            "scoreError" : 1882.2112977421066,
            "scoreConfidence" : [
                385177.3308090501,
                388941.7534045343
            ],
            "scorePercentiles" : {
                "0.0" : 386516.5154659559,
                "50.0" : 387156.55301325105,
                "90.0" : 387753.7947351364,
                "95.0" : 387753.7947351364,
                "99.0" : 387753.7947351364,
                "99.9" : 387753.7947351364,
                "99.99" : 387753.7947351364,
                "99.999" : 387753.7947351364,
                "99.9999" : 387753.7947351364,
                "100.0" : 387753.7947351364
            },
            "scoreUnit" : "ops/s",
            "rawData" : [
                [
                    387753.7947351364,
                    387198.7030019024,
                    386516.5154659559,
                    386672.14431771555,
                    387156.55301325105
                ]
            ]
        },
        "secondaryMetrics" : {
        }
    },
    {
        "jmhVersion" : "1.27",
        "benchmark" : "com.github.skjolber.jwt.JwtParseBenchmark.fusionauth_parse",
        "mode" : "thrpt",
        "threads" : 1,
        "forks" : 1,
        "jvm" : "/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.275.b01-6.fc33.x86_64/jre/bin/java",
        "jvmArgs" : [
            "-XX:+UseG1GC"
        ],
        "jdkVersion" : "1.8.0_275",
        "vmName" : "OpenJDK 64-Bit Server VM",
        "vmVersion" : "25.275-b01",
        "warmupIterations" : 10,
        "warmupTime" : "1 s",
        "warmupBatchSize" : 1,
        "measurementIterations" : 5,
        "measurementTime" : "5 s",
        "measurementBatchSize" : 1,
        "primaryMetric" : {
            "score" : 1196033.7973455377,
            "scoreError" : 3618.9730682612567,
            "scoreConfidence" : [
                1192414.8242772764,
                1199652.770413799
            ],
            "scorePercentiles" : {
                "0.0" : 1194670.6033722782,
                "50.0" : 1195877.3383478073,
                "90.0" : 1197096.9073768815,
                "95.0" : 1197096.9073768815,
                "99.0" : 1197096.9073768815,
                "99.9" : 1197096.9073768815,
                "99.99" : 1197096.9073768815,
                "99.999" : 1197096.9073768815,
                "99.9999" : 1197096.9073768815,
                "100.0" : 1197096.9073768815
            },
            "scoreUnit" : "ops/s",
            "rawData" : [
                [
                    1196718.122282853,
                    1197096.9073768815,
                    1195877.3383478073,
                    1194670.6033722782,
                    1195806.0153478677
                ]
            ]
        },
        "secondaryMetrics" : {
        }
    },
    {
        "jmhVersion" : "1.27",
        "benchmark" : "com.github.skjolber.jwt.JwtParseBenchmark.nimbus_parse",
        "mode" : "thrpt",
        "threads" : 1,
        "forks" : 1,
        "jvm" : "/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.275.b01-6.fc33.x86_64/jre/bin/java",
        "jvmArgs" : [
            "-XX:+UseG1GC"
        ],
        "jdkVersion" : "1.8.0_275",
        "vmName" : "OpenJDK 64-Bit Server VM",
        "vmVersion" : "25.275-b01",
        "warmupIterations" : 10,
        "warmupTime" : "1 s",
        "warmupBatchSize" : 1,
        "measurementIterations" : 5,
        "measurementTime" : "5 s",
        "measurementBatchSize" : 1,
        "primaryMetric" : {
            "score" : 945390.2198561875,
            "scoreError" : 63470.69872510698,
            "scoreConfidence" : [
                881919.5211310805,
                1008860.9185812945
            ],
            "scorePercentiles" : {
                "0.0" : 920418.1269389259,
                "50.0" : 944774.985006797,
                "90.0" : 960698.79957823,
                "95.0" : 960698.79957823,
                "99.0" : 960698.79957823,
                "99.9" : 960698.79957823,
                "99.99" : 960698.79957823,
                "99.999" : 960698.79957823,
                "99.9999" : 960698.79957823,
                "100.0" : 960698.79957823
            },
            "scoreUnit" : "ops/s",
            "rawData" : [
                [
                    960698.79957823,
                    941159.0925715361,
                    944774.985006797,
                    959900.0951854482,
                    920418.1269389259
                ]
            ]
        },
        "secondaryMetrics" : {
        }
    },
    {
        "jmhVersion" : "1.27",
        "benchmark" : "com.github.skjolber.jwt.JwtVerifyBenchmark.auth0_verify",
        "mode" : "thrpt",
        "threads" : 1,
        "forks" : 1,
        "jvm" : "/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.275.b01-6.fc33.x86_64/jre/bin/java",
        "jvmArgs" : [
            "-XX:+UseG1GC"
        ],
        "jdkVersion" : "1.8.0_275",
        "vmName" : "OpenJDK 64-Bit Server VM",
        "vmVersion" : "25.275-b01",
        "warmupIterations" : 10,
        "warmupTime" : "1 s",
        "warmupBatchSize" : 1,
        "measurementIterations" : 5,
        "measurementTime" : "5 s",
        "measurementBatchSize" : 1,
        "primaryMetric" : {
            "score" : 30147.821502235754,
            "scoreError" : 32.36110508862117,
            "scoreConfidence" : [
                30115.460397147133,
                30180.182607324376
            ],
            "scorePercentiles" : {
                "0.0" : 30134.133258194357,
                "50.0" : 30148.404335372143,
                "90.0" : 30155.291173975053,
                "95.0" : 30155.291173975053,
                "99.0" : 30155.291173975053,
                "99.9" : 30155.291173975053,
                "99.99" : 30155.291173975053,
                "99.999" : 30155.291173975053,
                "99.9999" : 30155.291173975053,
                "100.0" : 30155.291173975053
            },
            "scoreUnit" : "ops/s",
            "rawData" : [
                [
                    30154.040069943774,
                    30134.133258194357,
                    30155.291173975053,
                    30147.23867369343,
                    30148.404335372143
                ]
            ]
        },
        "secondaryMetrics" : {
        }
    },
    {
        "jmhVersion" : "1.27",
        "benchmark" : "com.github.skjolber.jwt.JwtVerifyBenchmark.fusionauth_verify",
        "mode" : "thrpt",
        "threads" : 1,
        "forks" : 1,
        "jvm" : "/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.275.b01-6.fc33.x86_64/jre/bin/java",
        "jvmArgs" : [
            "-XX:+UseG1GC"
        ],
        "jdkVersion" : "1.8.0_275",
        "vmName" : "OpenJDK 64-Bit Server VM",
        "vmVersion" : "25.275-b01",
        "warmupIterations" : 10,
        "warmupTime" : "1 s",
        "warmupBatchSize" : 1,
        "measurementIterations" : 5,
        "measurementTime" : "5 s",
        "measurementBatchSize" : 1,
        "primaryMetric" : {
            "score" : 32647.184907415434,
            "scoreError" : 91.14499406800361,
            "scoreConfidence" : [
                32556.03991334743,
                32738.329901483437
            ],
            "scorePercentiles" : {
                "0.0" : 32612.64394077529,
                "50.0" : 32650.25609415642,
                "90.0" : 32673.073233228228,
                "95.0" : 32673.073233228228,
                "99.0" : 32673.073233228228,
                "99.9" : 32673.073233228228,
                "99.99" : 32673.073233228228,
                "99.999" : 32673.073233228228,
                "99.9999" : 32673.073233228228,
                "100.0" : 32673.073233228228
            },
            "scoreUnit" : "ops/s",
            "rawData" : [
                [
                    32612.64394077529,
                    32650.25609415642,
                    32663.257119945367,
                    32673.073233228228,
                    32636.694148971877
                ]
            ]
        },
        "secondaryMetrics" : {
        }
    },
    {
        "jmhVersion" : "1.27",
        "benchmark" : "com.github.skjolber.jwt.JwtVerifyBenchmark.jjwt_verify",
        "mode" : "thrpt",
        "threads" : 1,
        "forks" : 1,
        "jvm" : "/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.275.b01-6.fc33.x86_64/jre/bin/java",
        "jvmArgs" : [
            "-XX:+UseG1GC"
        ],
        "jdkVersion" : "1.8.0_275",
        "vmName" : "OpenJDK 64-Bit Server VM",
        "vmVersion" : "25.275-b01",
        "warmupIterations" : 10,
        "warmupTime" : "1 s",
        "warmupBatchSize" : 1,
        "measurementIterations" : 5,
        "measurementTime" : "5 s",
        "measurementBatchSize" : 1,
        "primaryMetric" : {
            "score" : 31452.539304040132,
            "scoreError" : 188.90804747577656,
            "scoreConfidence" : [
                31263.631256564357,
                31641.447351515908
            ],
            "scorePercentiles" : {
                "0.0" : 31367.67647944786,
                "50.0" : 31465.57410419714,
                "90.0" : 31492.97840626341,
                "95.0" : 31492.97840626341,
                "99.0" : 31492.97840626341,
                "99.9" : 31492.97840626341,
                "99.99" : 31492.97840626341,
                "99.999" : 31492.97840626341,
                "99.9999" : 31492.97840626341,
                "100.0" : 31492.97840626341
            },
            "scoreUnit" : "ops/s",
            "rawData" : [
                [
                    31367.67647944786,
                    31476.21853405695,
                    31465.57410419714,
                    31492.97840626341,
                    31460.248996235307
                ]
            ]
        },
        "secondaryMetrics" : {
        }
    },
    {
        "jmhVersion" : "1.27",
        "benchmark" : "com.github.skjolber.jwt.JwtVerifyBenchmark.nimbus_verify",
        "mode" : "thrpt",
        "threads" : 1,
        "forks" : 1,
        "jvm" : "/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.275.b01-6.fc33.x86_64/jre/bin/java",
        "jvmArgs" : [
            "-XX:+UseG1GC"
        ],
        "jdkVersion" : "1.8.0_275",
        "vmName" : "OpenJDK 64-Bit Server VM",
        "vmVersion" : "25.275-b01",
        "warmupIterations" : 10,
        "warmupTime" : "1 s",
        "warmupBatchSize" : 1,
        "measurementIterations" : 5,
        "measurementTime" : "5 s",
        "measurementBatchSize" : 1,
        "primaryMetric" : {
            "score" : 19744.224544910037,
            "scoreError" : 37.915819039919576,
            "scoreConfidence" : [
                19706.308725870116,
                19782.14036394996
            ],
            "scorePercentiles" : {
                "0.0" : 19734.899605832576,
                "50.0" : 19740.29566941897,
                "90.0" : 19754.88792169725,
                "95.0" : 19754.88792169725,
                "99.0" : 19754.88792169725,
                "99.9" : 19754.88792169725,
                "99.99" : 19754.88792169725,
                "99.999" : 19754.88792169725,
                "99.9999" : 19754.88792169725,
                "100.0" : 19754.88792169725
            },
            "scoreUnit" : "ops/s",
            "rawData" : [
                [
                    19734.899605832576,
                    19754.69500734917,
                    19740.29566941897,
                    19736.34452025223,
                    19754.88792169725
                ]
            ]
        },
        "secondaryMetrics" : {
        }
    },
    {
        "jmhVersion" : "1.27",
        "benchmark" : "com.github.skjolber.jwt.JwtVerifyBenchmark.okta_verify",
        "mode" : "thrpt",
        "threads" : 1,
        "forks" : 1,
        "jvm" : "/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.275.b01-6.fc33.x86_64/jre/bin/java",
        "jvmArgs" : [
            "-XX:+UseG1GC"
        ],
        "jdkVersion" : "1.8.0_275",
        "vmName" : "OpenJDK 64-Bit Server VM",
        "vmVersion" : "25.275-b01",
        "warmupIterations" : 10,
        "warmupTime" : "1 s",
        "warmupBatchSize" : 1,
        "measurementIterations" : 5,
        "measurementTime" : "5 s",
        "measurementBatchSize" : 1,
        "primaryMetric" : {
            "score" : 1297.924565401163,
            "scoreError" : 2.7435027646374714,
            "scoreConfidence" : [
                1295.1810626365254,
                1300.6680681658004
            ],
            "scorePercentiles" : {
                "0.0" : 1297.277707706139,
                "50.0" : 1297.5373722076858,
                "90.0" : 1298.8788164248579,
                "95.0" : 1298.8788164248579,
                "99.0" : 1298.8788164248579,
                "99.9" : 1298.8788164248579,
                "99.99" : 1298.8788164248579,
                "99.999" : 1298.8788164248579,
                "99.9999" : 1298.8788164248579,
                "100.0" : 1298.8788164248579
            },
            "scoreUnit" : "ops/s",
            "rawData" : [
                [
                    1298.8788164248579,
                    1297.5373722076858,
                    1297.277707706139,
                    1297.440832553544,
                    1298.488098113588
                ]
            ]
        },
        "secondaryMetrics" : {
        }
    }
]


};
