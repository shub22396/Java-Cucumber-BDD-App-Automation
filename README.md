# Cucumber Java Lambdatest 


[Cucumber JVM](https://cucumber.io/docs/reference/jvm) Integration with Lambdatest for App Automation.

## Setup
* Clone the repo
* Install dependencies `mvn install`
* Set environment variables with your [

* Update `*.conf.js` files inside the `src/test/resources/conf/` directory to update desired capabilities.

## Running your tests
* To run a single test, run `mvn test -P single`
* To run parallel tests, run `mvn test -P parallel` 
* To run local tests, run `mvn test -P local`



## Notes

  ```
  export LT_USERNAME=<lambdatest-username> &&
  export LT_ACCESS_KEY=<lambdatest-access-key>
  ```

