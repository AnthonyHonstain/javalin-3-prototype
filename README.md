# Overview
This was an experimental project for me to play with Javalin and Kotlin.

**WARNING** - this was my first attempt and I did not construct any tests as I more than had my hand fulls getting
Maven and Kotlin building and packaged into a Jar (I have historically always had trouble getting my Jar's constructed
the way I like). 

## References
* Kotlin https://kotlinlang.org/docs/home.html
* Javalin https://javalin.io/
    * A simple web framework for Java and Kotlin, inspired by Sinatra, uses Jetty

* Junit 5 https://junit.org/junit5/docs/current/user-guide/
    * https://github.com/tipsy/javalin-testing-example
* I referenced this blog post pretty extensively https://phauer.com/2018/best-practices-unit-testing-kotlin/

### Monitoring
* Micrometer https://javalin.io/plugins/micrometer
    * https://micrometer.io/docs

### Compile and Package References
* This guide was super important and the key to me getting a Jar constructed that I could use: https://kotlinlang.org/docs/maven.html#self-contained-jar-file
    * Prior to find this, I went down a lot of dead ends
    * Important for creating the self-contained Jar https://maven.apache.org/plugins/maven-assembly-plugin/usage.html
* Nice reference on the difference between some of the maven plugin options https://medium.com/@randilfernando/when-to-use-maven-jar-maven-assembly-or-maven-shade-ffc3f76ba7a6
* Maven + Javalin https://javalin.io/tutorials/maven-setup
    * https://javalin.io/tutorials/docker

* Inspecting the Jar
```text
jar tf target/my-app-1.0-SNAPSHOT.jar
unzip -l target/my-app-1.0-SNAPSHOT.jar
```

# Run Locally

## Build
```text
mvn clean package
```

Outcome:
```text
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by com.google.inject.internal.cglib.core.$ReflectUtils$1 (file:/usr/share/maven/lib/guice.jar) to method java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain)
WARNING: Please consider reporting this to the maintainers of com.google.inject.internal.cglib.core.$ReflectUtils$1
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
[INFO] Scanning for projects...
[INFO] 
[INFO] ----------------------< com.mycompany.app:my-app >----------------------
[INFO] Building my-app 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:3.1.0:clean (default-clean) @ my-app ---
[INFO] Deleting /home/dev/Desktop/my-app/target
[INFO] 
[INFO] --- maven-resources-plugin:3.0.2:resources (default-resources) @ my-app ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/dev/Desktop/my-app/src/main/resources
[INFO] 
[INFO] --- kotlin-maven-plugin:1.4.32:compile (compile) @ my-app ---
[WARNING] Runtime JAR files in the classpath should have the same version. These files were found in the classpath:
    /home/dev/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib/1.4.32/kotlin-stdlib-1.4.32.jar (version 1.4)
    /home/dev/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib-common/1.4.32/kotlin-stdlib-common-1.4.32.jar (version 1.4)
    /home/dev/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib-jdk8/1.3.71/kotlin-stdlib-jdk8-1.3.71.jar (version 1.3)
    /home/dev/.m2/repository/org/jetbrains/kotlin/kotlin-stdlib-jdk7/1.3.71/kotlin-stdlib-jdk7-1.3.71.jar (version 1.3)
[WARNING] Some runtime JAR files in the classpath have an incompatible version. Consider removing them from the classpath
[WARNING] /home/dev/Desktop/my-app/src/main/kotlin/com/mycompany/app/App.kt: (18, 10) Parameter 'args' is never used
[WARNING] /home/dev/Desktop/my-app/src/main/kotlin/com/mycompany/app/App.kt: (67, 18) Parameter 'ctx' is never used, could be renamed to _
[INFO] 
[INFO] --- maven-compiler-plugin:3.5.1:compile (java-compile) @ my-app ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:3.0.2:testResources (default-testResources) @ my-app ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/dev/Desktop/my-app/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.5.1:testCompile (java-test-compile) @ my-app ---
[INFO] No sources to compile
[INFO] 
[INFO] --- maven-surefire-plugin:2.22.1:test (default-test) @ my-app ---
[INFO] No tests to run.
[INFO] 
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ my-app ---
[INFO] Building jar: /home/dev/Desktop/my-app/target/my-app-1.0-SNAPSHOT.jar
[INFO] 
[INFO] --- maven-assembly-plugin:2.6:single (make-assembly) @ my-app ---
[INFO] Building jar: /home/dev/Desktop/my-app/target/my-app-1.0-SNAPSHOT-jar-with-dependencies.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  9.985 s
[INFO] Finished at: 2021-05-14T09:48:19-07:00
[INFO] ------------------------------------------------------------------------
```

## Run Javalin
```text
java -jar target/my-app-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Outcome:
```text
[main] INFO org.eclipse.jetty.util.log - Logging initialized @243ms to org.eclipse.jetty.util.log.Slf4jLog
[main] INFO io.javalin.Javalin - 
           __                      __ _
          / /____ _ _   __ ____ _ / /(_)____
     __  / // __ `/| | / // __ `// // // __ \
    / /_/ // /_/ / | |/ // /_/ // // // / / /
    \____/ \__,_/  |___/ \__,_//_//_//_/ /_/

        https://javalin.io/documentation

[main] INFO io.javalin.Javalin - Starting Javalin ...
[main] INFO org.eclipse.jetty.server.Server - jetty-9.4.39.v20210325; built: 2021-03-25T14:42:11.471Z; git: 9fc7ca5a922f2a37b84ec9dbc26a5168cee7e667; jvm 11.0.11+9-Ubuntu-0ubuntu2.20.04
[main] INFO org.eclipse.jetty.server.Server - Started @500ms
[main] INFO io.javalin.Javalin - Listening on http://localhost:7000/
[main] INFO io.javalin.Javalin - Javalin started in 164ms \o/
```

