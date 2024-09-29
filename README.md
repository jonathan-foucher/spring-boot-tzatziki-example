## introduction
This project is an example of integration testing with tzatziki and Spring Boot.

## run the project
### Database
Install postgres locally or run it through docker with :
`docker run -p 5432:5432 -e POSTGRES_DB=my_database -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres postgres`

### Application
Once the postgres database is launched, you can start the Spring Boot project and try it out.

Get all jobs
```
curl --location 'http://localhost:8080/tzatziki-example/jobs'
```

Create a job
```
curl --location --request POST 'http://localhost:8080/tzatziki-example/jobs?name=SOME_JOB'
```

### Integration testing
The integration tests are written in `/test/resources/features` folder

Docker must be running to launch the integration tests since Testcontainers will start a postgres database for the tests.
