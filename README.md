## REST

```sh
method GET
/api/v1/companies
Get all companies, you can modify result by using params: startPage; pageSize; sortBy, sortMethod(asc, desc)
```
example  localhost:8080/api/v1/companies?sort=id,desc&page=0&size=20

```sh
method GET
/{companyId}
Get single company by company id
```
example  localhost:8080/api/v1/companies/{companyId}
```sh
method POST
Create company
```
example  localhost:8080/api/v1/companies

```sh
method PUT
/{companyId}
Update company by id
```
example  localhost:8080/api/v1/companies/{companyId}
```sh
method DELETE
/{companyId}
Delete company by id
```
example  localhost:8080/api/v1/companies/{companyId}

# Installation
> mvn clean install
```sh
 application.ymp located at src/main/resources/application.yml
 You can change the default database properties yourself
```
```sh
 test data execute automatically from
 src/main/resources/db/changelog/db.changelog-master.yaml
```
# Swagger documentation
> http://localhost:8080/swagger-ui/