# Quarkus example

##### Description

* Java 21
* QUARKUS 3.22.1
* Gradle 8.13

### Java code style

Java code style refers to the conventions and guidelines that developers follow when writing Java code to ensure
consistency and readability.

project: google-java-format,
link: https://github.com/google/google-java-format/blob/master/README.md#intellij-jre-config

### Gradle

#### Gradle Versions Plugin

Displays a report of the project dependencies that are up-to-date, exceed the latest version found, have upgrades, or
failed to be resolved:

command:

```
gradle dependencyUpdates
```

#### Gradle wrapper

The recommended way to execute any Gradle build is with the help of the Gradle Wrapper (referred to as "Wrapper")

```
./gradlew wrapper --gradle-version latest
```

#### Gradle ignore test

To skip any task from the Gradle build, we can use the -x or –exclude-task option. In this case, we’ll use “-x test” to
skip tests from the build.

To see it in action, let’s run the build command with -x option:

```
gradle build -x test
```