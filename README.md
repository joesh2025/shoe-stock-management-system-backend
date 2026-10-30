# Shoe Stock Management System — Backend

**Student:** Shema Joel  ·  **Student ID:** 27407
**Course:** INSY 8322 — Web Technology and Internet
**Project:** Shoe Stock Management System (`shoe-stock-management-system`)

Spring Boot + Spring Data JPA + PostgreSQL backend for a shoe shop: shoe lines,
sales counters, shop attendants, customers, shoe sales and the stock movements
written once a sale is SOLD.

## Stack

| Piece | Version |
|---|---|
| Java | 17+ (built with Amazon Corretto 24) |
| Spring Boot | 3.x (Web, Data JPA) |
| PostgreSQL driver | latest |
| Build | Maven wrapper (`./mvnw`, no install needed) |

## Steps to run the API

1. **Start PostgreSQL** and create the database (the project expects
   `postgres` / `africa` on `localhost:5432`):

   ```bash
   psql -U postgres -c "CREATE DATABASE shoe_stock_db;"
   ```

   If your credentials differ, change them in
   `src/main/resources/application.properties` (`spring.datasource.*`).

2. **Run the application** from the project root:

   ```bash
   ./mvnw spring-boot:run        # macOS / Linux
   mvnw.cmd spring-boot:run      # Windows
   ```

   Hibernate creates the tables automatically
   (`spring.jpa.hibernate.ddl-auto=update`): `shoe_line`, `sales_counter`,
   `shop_attendant`, `customer`, `shoe_sale`, `stock_movement` and the
   many-to-many table `shop_attendant_shoe_line`.

3. **The API answers on** `http://localhost:8090`. Check it:

   ```bash
   curl http://localhost:8090/api/shoe-sales/all
   ```

4. **Import the Postman collection** from the repository root —
   `shoe-stock-management-system-midterm.postman_collection.json` — it holds
   one request per requirement, named by its ID (A2, B1, C4, D2, ...).

## Endpoints

| ID | Method and path |
|---|---|
| A2 | `POST /api/{path}/save` · `GET /api/{path}/all` · `GET /api/{path}/{id}` · `PUT /api/{path}/update/{id}` · `DELETE /api/{path}/delete/{id}` with `{path}` = `shoe-lines`, `sales-counters`, `shop-attendants`, `customers`, `shoe-sales` |
| B1 | `GET /api/customers/by-last-name?lastName=shema` |
| B2 | `GET /api/shoe-sales/by-status?status=QUOTED` |
| B3 | `GET /api/shoe-sales/between?start=2026-10-01&end=2026-10-31` |
| C1 | `GET /api/shop-attendants/by-shoe-line?name=sneakers` |
| C2 | `GET /api/sales-counters/free` |
| C3 | `GET /api/shoe-lines/unused` |
| C4 | `GET /api/customers/of-shop-attendant/{shopAttendantId}` |
| D1 | `POST /api/shoe-sales/save` (safe saving, always starts QUOTED) |
| D2 | `PATCH /api/shoe-sales/{id}/status?status=RESERVED` |
| D3 | `PUT /api/shop-attendants/{shopAttendantId}/shoe-lines/{shoeLineId}` |
| D4 | `PATCH /api/shop-attendants/{shopAttendantId}/sales-counter?counterNumber=4` |
| E1 | `POST /api/stock-movements/save?shoeSaleId=...` |
| E2 | `GET /api/stock-movements/customer/{customerCode}` |
| E3 | `DELETE /api/stock-movements/delete/{id}` |
| F1 | `GET /api/shoe-sales/stats/by-status` |
| F2 | `PATCH /api/shoe-sales/cancel-day?shopAttendantId=...&date=2026-10-20` |
| +5 | `GET /api/dashboard/summary` |
| +5 | `GET /api/shoe-sales/page?page=0&size=5&sort=saleDate,desc` |

## Seed data

Step 0 data (4 shoe lines, 4 sales counters, 3 shop attendants, 5 customers,
10 shoe sales spread over October and November 2026 with all four statuses)
was entered through the `save` endpoints themselves, so every query of the
project returns at least one row: `Formal Shoes` is held by nobody, sales
counter 4 is free, and at least two sales are SOLD for the stock movements.

## Commit history

One commit per part — `Part A` domain model and CRUD, `Part B` derived
queries, `Part C` JPQL joins, `Part D` business rules, `Part E` stock
movements, `Part F` reports and bulk update, then the bonus dashboard and
pagination — so the history shows the project growing part by part.
