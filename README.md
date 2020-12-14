# Serverless REST API in Java/Maven using DynamoDB


![image](https://user-images.githubusercontent.com/8188/38645675-ec708d0e-3db2-11e8-8f8b-a4a37ed612b9.png)


The sample serverless service will create a REST API for users. It will be deployed to AWS. The data will be stored in a DynamoDB table.

This is a companion app for the blog post [REST API in Java using DynamoDB and Serverless](https://serverless.com/blog/how-to-create-a-rest-api-in-java-using-dynamodb-and-serverless/).

## Install Pre-requisites

* `node` and `npm`
* Install the JDK and NOT the Java JRE from [Oracle JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html). And set the following:
`export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-10.jdk/Contents/Home`
* [Apache Maven](https://maven.apache.org/). After [downloading](https://maven.apache.org/download.html) and [installing](https://maven.apache.org/install.html) Apache Maven, please add the `apache-maven-x.x.x` folder to the `PATH` environment variable.

### Test Pre-requisites

Test Java installation:

```
$ java --version

java 10 2018-03-20
Java(TM) SE Runtime Environment 18.3 (build 10+46)
Java HotSpot(TM) 64-Bit Server VM 18.3 (build 10+46, mixed mode)
```

Test Maven installation:

```
$ mvn -v

Apache Maven 3.5.3 (3383c37e1f9e9b3bc3df5050c29c8aff9f295297; 2018-02-24T14:49:05-05:00)
Maven home: /usr/local/apache-maven-3.5.3
Java version: 10, vendor: Oracle Corporation
Java home: /Library/Java/JavaVirtualMachines/jdk-10.jdk/Contents/Home
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "10.13.3", arch: "x86_64", family: "mac"
```

## Build the Java project

Create the java artifact (jar) by:

```
$ cd megthink-mobile
$ mvn clean install

[INFO] Scanning for projects...
[INFO]
[INFO] --------------------< com.serverless:megthink-mobile >---------------------
[INFO] Building megthink-mobile dev
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ megthink-mobile ---
[INFO] Deleting /Users/rupakg/projects/svrless/apps/megthink-mobile/target

...
...

[INFO] --- maven-install-plugin:2.4:install (default-install) @ megthink-mobile ---
[INFO] Installing /Users/rupakg/projects/svrless/apps/megthink-mobile/target/megthink-mobile-dev.jar to /Users/rupakg/.m2/repository/com/serverless/megthink-mobile/dev/megthink-mobile-dev.jar
[INFO] Installing /Users/rupakg/projects/svrless/apps/megthink-mobile/pom.xml to /Users/rupakg/.m2/repository/com/serverless/megthink-mobile/dev/megthink-mobile-dev.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 2.790 s
[INFO] Finished at: 2018-04-08T19:58:15-04:00
[INFO] ------------------------------------------------------------------------
```

We can see that we have an artifact in the `target` folder named `megthink-mobile-dev.jar`.

## Deploy the serverless app

```
$ sls deploy

Serverless: Packaging service...
Serverless: Creating Stack...
Serverless: Checking Stack create progress...
.....
Serverless: Stack create finished...
Serverless: Uploading CloudFormation file to S3...
Serverless: Uploading artifacts...
Serverless: Validating template...
Serverless: Updating Stack...
Serverless: Checking Stack update progress...
..................................
Serverless: Stack update finished...
Service Information
service: megthink-mobile
stage: dev
region: us-east-1
stack: megthink-mobile-dev
api keys:
  None
endpoints:
  GET - https://xxxxxxxxx.execute-api.us-east-1.amazonaws.com/dev/users
  GET - https://xxxxxxxxx.execute-api.us-east-1.amazonaws.com/dev/users/{id}
  POST - https://xxxxxxxxx.execute-api.us-east-1.amazonaws.com/dev/users
  DELETE - https://xxxxxxxxx.execute-api.us-east-1.amazonaws.com/dev/users/{id}
functions:
  listUsers: megthink-mobile-dev-listUsers
  getUser: megthink-mobile-dev-getUser
  createUser: megthink-mobile-dev-createUser
  deleteuser: megthink-mobile-dev-deleteUser
```

