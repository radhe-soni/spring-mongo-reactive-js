# spring-mongo-reactive-js
Example for end to end reactive support
<br/>

- Employee is a mongo-db document attached to employee collection.
- EmployeeRepository is a reactive mongo-db repository used to reactively fetch and update data in mongo db.
- EmployeeController is a spring rest controller creates/fetches employee details from mongo-db.
- CreateEmployee.html has a form to submit employee data.
- DisplayEmployees.html displays employee details currently available in db.

## employee collection :
It has to be capped collection, if we need to make tailable see [@Tailable](https://docs.mongodb.com/manual/core/tailable-cursors/).
