+ use resources path: https://stackoverflow.com/questions/2195445/eclipse-getresourceasstream-returning-null

+ table user

```SQL
create table user(
email nvarchar(125) not null unique primary key,
password nvarchar(125) not null,
name nvarchar(255) not null,
avatar nvarchar(255) not null
);
```
Demo web login
with:
+ Model: JDBC + MySQL
+ Controller: java servlet
+ View: JSP

+ Auth: bcrypt+jwt


*GET /login => form login => submit 
=> *POST /login => 
validate => [true] => set cookie => redirect to GET /welcome
	=> [false] => redirect to GET /login

*GET /welcome
check cookie => [false] => redirect login
	     => [true] => verify user => [true] => show data
				      => [false] => redirect to GET /login
[sign out] *POST /welcome => clear cookie => redirect GET /login

*GET /signup => form signup => submit
=> *POST /signup =>
validate => [true] => save to database => redirect to GET /login
		=> [false] => redirect to GET /signup
