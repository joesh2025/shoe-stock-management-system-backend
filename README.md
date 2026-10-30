# Shoe Stock Management System — Backend

**Student:** Shema Joel  
**Student ID:** 27407  
**Course:** INSY 8322 — Web Technology and Internet  
**Institution:** Adventist University of Central Africa (AUCA)  
**Project:** Shoe Stock Management System (`shoe-stock-management-system`)

## About the Project

This project is a backend system for managing a shoe shop. It manages shoe lines, sales counters, shop attendants, customers, shoe sales, and stock movements.

When a shoe sale reaches the **SOLD** status, stock movements can be recorded for that sale. The system also includes search features, business rules, reports, a dashboard, and pagination.

## Technologies Used

| Technology | Purpose |
|---|---|
| Java 17+ | Programming language |
| Spring Boot | Building the API |
| Spring Data JPA | Working with database records |
| PostgreSQL | Storing the data |
| Maven Wrapper | Building and running the project |

## Steps to Run the API

### 1. Start PostgreSQL

Make sure PostgreSQL is running. Create a database named `shoe_stock_db`:

```sql
CREATE DATABASE shoe_stock_db;
```

The application is configured to use the following database settings:

- **Database:** `shoe_stock_db`
- **Username:** `postgres`
- **Password:** `africa`
- **Host:** `localhost`
- **Port:** `5432`


### 2. Run the Application

Open a terminal in the project folder.

On **Windows**, run:

```bash
mvnw.cmd spring-boot:run
```

Wait for Spring Boot to finish starting.

Hibernate is configured to update the database tables automatically when the application runs.

### 3. Test the API

The API runs at:

`http://localhost:8090`

For example, you can retrieve all shoe sales using:

```bash
curl http://localhost:8090/api/shoe-sales/all
```

You can also use Postman to test the different endpoints.

## Main API Endpoints

The table below lists the main endpoints included in the project.

| Requirement | Method | Endpoint |
|---|---|---|
| A2 | POST | `/api/{path}/save` |
| A2 | GET | `/api/{path}/all` |
| A2 | GET | `/api/{path}/{id}` |
| A2 | PUT | `/api/{path}/update/{id}` |
| A2 | DELETE | `/api/{path}/delete/{id}` |
| B1 | GET | `/api/customers/by-last-name?lastName=shema` |
| B2 | GET | `/api/shoe-sales/by-status?status=QUOTED` |
| B3 | GET | `/api/shoe-sales/between?start=2026-10-01&end=2026-10-31` |
| C1 | GET | `/api/shop-attendants/by-shoe-line?name=sneakers` |
| C2 | GET | `/api/sales-counters/free` |
| C3 | GET | `/api/shoe-lines/unused` |
| C4 | GET | `/api/customers/of-shop-attendant/{shopAttendantId}` |
| D1 | POST | `/api/shoe-sales/save` |
| D2 | PATCH | `/api/shoe-sales/{id}/status?status=RESERVED` |
| D3 | PUT | `/api/shop-attendants/{shopAttendantId}/shoe-lines/{shoeLineId}` |
| D4 | PATCH | `/api/shop-attendants/{shopAttendantId}/sales-counter?counterNumber=4` |
| E1 | POST | `/api/stock-movements/save?shoeSaleId=...` |
| E2 | GET | `/api/stock-movements/customer/{customerCode}` |
| E3 | DELETE | `/api/stock-movements/delete/{id}` |
| F1 | GET | `/api/shoe-sales/stats/by-status` |
| F2 | PATCH | `/api/shoe-sales/cancel-day?shopAttendantId=...&date=2026-10-20` |
| Bonus | GET | `/api/dashboard/summary` |
| Bonus | GET | `/api/shoe-sales/page?page=0&size=5&sort=saleDate,desc` |

For the CRUD endpoints under A2, `{path}` represents the relevant resource: `shoe-lines`, `sales-counters`, `shop-attendants`, `customers`, or `shoe-sales`.

## Postman Collection

The Postman collection is included in the project root:

`shoe-stock-management-system-midterm.postman_collection.json`

Import this file into Postman to test the API. The requests are organized by requirement ID, making it easier to find and demonstrate each feature.

## Sample Data

The project includes sample records for shoe lines, sales counters, shop attendants, customers, and shoe sales. The records were added through the API's save endpoints to help test the queries and business rules.

The sample data includes different sale statuses, including `QUOTED`, `RESERVED`, `SOLD`, and `CANCELLED`.

## Commit History

The project was developed in parts, with commits for each major stage:

- **Part A:** Domain model and CRUD operations
- **Part B:** Derived queries
- **Part C:** JPQL queries and joins
- **Part D:** Business rules
- **Part E:** Stock movements
- **Part F:** Reports and bulk updates
- **Bonus:** Dashboard and pagination

This commit history shows how the project was built step by step.
