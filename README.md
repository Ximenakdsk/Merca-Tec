# 🛒 Merca-Tec: E-Commerce Database System

> A robust online shopping management system integrated with Oracle Database, featuring a Java Swing desktop interface for real-time transaction handling.

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk)
![Oracle](https://img.shields.io/badge/Oracle-Database-F80000?style=for-the-badge&logo=oracle)
![Swing](https://img.shields.io/badge/Frontend-Java%20Swing-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Completed-success?style=for-the-badge)

## 📖 About the Project

**Merca-Tec** is an academic software engineering project developed for the **Database Fundamentals** course. The platform simulates a complete e-commerce ecosystem, managing everything from product categorization and supplier logistics to customer transactions and payment validation.

The primary focus was the design and implementation of a highly normalized relational schema in **Oracle SQL**, validated through a functional Java prototype.

### ✨ Key Features

* **Inventory Management:** Dynamic product control organized by categories and suppliers.
* **Transaction Processing:** Automated sales registration with total calculation and stock validation.
* **Secure Payments:** Integrated validation for Credit and Debit card transactions.
* **Email Integration:** Automated delivery of purchase details to customers upon checkout.
* **Database Integrity:** Strict enforcement of referential integrity, constraints, and business rules.

---

## 🛠️ Tech Stack

* **Database:** Oracle Database (Physical Schema, Constraints, SQL Scripts).
* **Backend:** Java (JDBC for database connectivity).
* **Frontend:** Java Swing (Desktop GUI for administrative and customer views).
* **Design Tools:** Entity-Relationship (E-R) Modeling.

---

## 🏗️ System Architecture & Business Rules

The system follows a modular architecture where the **Java Swing** frontend communicates with the **Oracle** backend via JDBC.

### Core Business Logic:
1.  **Normalization:** 1:N relationships correctly normalized across all entities.
2.  **Payment Logic:** Strict validation restricting payments to specific card types (Credit/Debit).
3.  **Data Integrity:** Implementation of `NOT NULL`, `PRIMARY KEY`, and `FOREIGN KEY` constraints to ensure data consistency.
4.  **Automation:** Automatic calculation of subtotal and total amounts within the `DETALLE_COMPRA` module.

---

## 🗃️ Data Model (Relational Schema)

The core database consists of the following entities:

* **CATEGORIA / PRODUCTO / PROVEEDOR:** Logistics and inventory core.
* **CLIENTE / COMPRA:** User management and order tracking.
* **DETALLE_COMPRA / PAGO:** Transactional granularity and financial records.

---

## 📂 Project Structure

```text
SIstemaComprasLinea_BD/
├── src/
│   ├── connection/     # Oracle JDBC Connection logic
│   ├── view/           # Java Swing GUI Components (Frontend)
│   ├── controller/     # Event handling and business logic
│   ├── model/          # Data transfer objects (DTO)
│   └── main/           # Application entry point
├── sql/
│   ├── ddl_tables.sql  # Schema creation scripts
│   ├── dml_inserts.sql # Seed data and test records
│   └── queries.sql     # Validation and reporting queries
├── docs/               # E-R and Relational Diagrams
└── README.md
```
## 🚀 Installation & Setup

### 1. Database Configuration
* **Ensure** you have **Oracle Database** installed and running.
* **Execute** the scripts in `/sql/ddl_tables.sql` to generate the physical schema.
* **(Optional)** Run `/sql/dml_inserts.sql` to populate the system with test data.

### 2. Java Application
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/Ximenakdsk/SIstemaComprasLinea_BD.git
Configure Driver:

Add the ojdbc.jar driver to your project's build path to enable Oracle connectivity.

Update Connection:

Edit the connection string in src/connection/DatabaseConnection.java with your specific Oracle credentials:

User

Password

Host / SID

👩‍💻 Authors
Merca-Tec – Database Engineering Project

*   **[Ximenakdsk](https://github.com/Ximenakdsk)** - *Ximena Hernández*
