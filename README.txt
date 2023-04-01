# bm-irr-demo

Please refer to BM-Task.pdf for more info


Running The Demo
================

Step 1:
=======
Create a postgress sql databse and adjust the properties in the properties file(dbname and access)

spring.datasource.url=jdbc:postgresql://localhost:5432/bmirrigation
spring.datasource.username=postgres
spring.datasource.password=0000

Step 2 :
========
Compile and Launch the server

Step 3:
=======
You can add some schedules and see the logic executing.
The databse will be pre-loaded with 2 Plots and 2 Sensors
http://localhost:8080/api/scheduler/schedule
{
    "taskId": 1000,
    "cronExpression": "*/10 * * * * *",
    "waterAmount": 1,
    "duration": 1,
    "plotId" : 1
}
{
    "taskId": 1001,
    "cronExpression": "*/5 * * * * *",
    "waterAmount": 1,
    "duration": 1,
    "plotId" : 2
}