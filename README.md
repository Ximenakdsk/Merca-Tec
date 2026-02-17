# 🛒 Merca-Tec  
### Sistema de Compras en Línea  

## 📌 Descripción del Proyecto

**Merca-Tec** es un sistema de compras por Internet desarrollado como proyecto académico para la materia de Fundamentos de Bases de Datos.

La empresa "Merca-Tec" requiere una plataforma que permita la venta de productos en línea organizados por categorías, gestionando clientes, compras, pagos y proveedores, utilizando una base de datos Oracle y un backend desarrollado en Java.

El sistema permite registrar ventas, controlar productos, administrar categorías y validar el esquema físico de la base de datos mediante un prototipo funcional conectado a Oracle.

---

## 🎯 Objetivos del Proyecto

- Diseñar un modelo Entidad-Relación (E-R)
- Transformar el modelo E-R a modelo relacional
- Implementar el esquema físico en Oracle
- Crear sentencias SQL de creación e inserción
- Desarrollar un prototipo en Java conectado a la base de datos
- Validar la correcta implementación del esquema físico

---

## 🏗️ Arquitectura del Sistema

El sistema está compuesto por:

- 🗄️ **Base de Datos:** Oracle Database
- ☕ **Backend:** Java
- 💾 **Lenguaje de consultas:** SQL
- 🧩 **Modelo de datos:** Modelo Relacional

---

## 🗃️ Modelo de Datos

El sistema contempla las siguientes entidades principales:

- **Categoría**
- **Producto**
- **Proveedor**
- **Cliente**
- **Compra**
- **Detalle_Compra**
- **Pago**

### Reglas del Negocio

- Cada producto pertenece a una sola categoría.
- Cada producto tiene un proveedor.
- Un cliente puede realizar una o múltiples compras.
- Cada compra puede incluir uno o más productos.
- El pago solo puede realizarse con tarjeta de crédito o débito.
- Cada compra registra:
  - Fecha
  - Cantidad de artículos
  - Total pagado
- Cada producto incluye una imagen representativa.
- El cliente recibe el detalle de su compra vía correo electrónico.

---

## 🧮 Estructura General del Modelo Relacional

Ejemplo simplificado:

- CATEGORIA (id_categoria, nombre)
- PROVEEDOR (id_proveedor, nombre, contacto)
- PRODUCTO (id_producto, nombre, precio, descripcion, id_categoria, id_proveedor, imagen)
- CLIENTE (id_cliente, nombre, correo)
- COMPRA (id_compra, fecha, total, id_cliente)
- DETALLE_COMPRA (id_detalle, id_compra, id_producto, cantidad, subtotal)
- PAGO (id_pago, id_compra, tipo_pago, fecha_pago)

---

## 📜 Scripts SQL Incluidos

El proyecto incluye:

- ✔️ Script de creación de tablas
- ✔️ Definición de claves primarias y foráneas
- ✔️ Restricciones (constraints)
- ✔️ Inserts de prueba
- ✔️ Consultas de validación

---

## 🔐 Restricciones Implementadas

- Claves primarias
- Claves foráneas
- Restricciones NOT NULL
- Integridad referencial
- Validación de tipo de pago (Crédito/Débito)
- Relaciones 1:N correctamente normalizadas

---

## 💻 Prototipo en Java

El backend desarrollado en Java permite:

- Conexión a la base de datos Oracle
- Validación del esquema físico
- Inserción de registros
- Consulta de productos
- Registro de compras
- Cálculo automático del total
- Validación de pago

---

## 📂 Estructura del Proyecto

