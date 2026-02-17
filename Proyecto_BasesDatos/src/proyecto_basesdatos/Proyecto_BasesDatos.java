/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_basesdatos;
import conexiones.ConexionOracle;
import java.sql.Connection;//
import java.sql.ResultSet;//
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;//
import java.util.Scanner;


public class Proyecto_BasesDatos {

    
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String sSQL = "";
        ConexionOracle conection = new ConexionOracle();
        String jdbcURL = "jdbc:oracle:thin:@localhost:1521:XE"; // Cambiar según tu configuración
        String usuario = "SYSTEM"; // Cambiar según tu usuario
        String contraseña = "oracle"; // Cambiar según tu contraseña
        int op = 0;
        do{
        System.out.println("1. INSERTAR DIRECCION");
        System.out.println("2. Insertar cliente");
        System.out.println("3. Consultar cliente");
            System.out.println("0. salir");
        op = scanner.nextInt();
        // Consulta SQL para insertar datos
        switch (op) {
            case 1:
                String sql = "INSERT INTO DOMICILIO VALUES (?, ?, ?, ?)";

        try (Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña);
             PreparedStatement preparedStatement = conexion.prepareStatement(sql);
             ) {

            System.out.println("Conexión exitosa a la base de datos Oracle.");

            // Solicitar datos al usuario
            System.out.print("Ingrese el ID del domicilio: ");
            String id = scanner.nextLine();

            System.out.print("Ingrese la ciudad: ");
            String ciudad = scanner.nextLine();

            System.out.print("Ingrese el estado: ");
            String estado = scanner.nextLine();
            
            System.out.println("Ingrese el pais: ");
            String pais = scanner.nextLine();
            // Asignar valores al PreparedStatement
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, ciudad);
            preparedStatement.setString(3, estado);
            preparedStatement.setString(4, pais);

            // Ejecutar la inserción
            int filasAfectadas = preparedStatement.executeUpdate();
            System.out.println("Inserción exitosa. Filas afectadas: " + filasAfectadas);

        } catch (SQLException e) {
            System.err.println("Error al conectar o ejecutar la consulta: " + e.getMessage());
        }
        try (Connection con = conection.conectar();
             Statement cn = con.createStatement();
             ResultSet res = cn.executeQuery("SELECT * FROM DOMICILIO")) {

            System.out.println("\nDatos en la tabla DOMICILIO:");
            while (res.next()) {
                // Imprimir las columnas según el orden de tu tabla
                System.out.println("ID: " + res.getString(1) + 
                                   ", Ciudad: " + res.getString(2) +
                                   ", Estado: " + res.getString(3) +
                                   ", País: " + res.getString(4));
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar los datos: " + e.getMessage());
        }
                break;
            case 2:
                insertarCliente(jdbcURL, usuario, contraseña, scanner);
                break;
            case 3:
                consultarClientes(jdbcURL, usuario, contraseña);
                break;
            case 0:
                System.out.println("Saliendo");
                break;
            default:
                System.out.println("ERROR");
        }
        }while(op != 0);
    }
    
    private static void insertarCliente(String jdbcURL, String usuario, String contraseña, Scanner scanner) {
        String insertSQL = "INSERT INTO CLIENTE (ID_CTE, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO, TELEFONO, EMAIL, CONTRASEÑA, CODIGO_POSTAL, CALLE, NUMERO, CO_DOMICILIO) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña);
             PreparedStatement preparedStatement = conexion.prepareStatement(insertSQL)) {

            System.out.println("Inserción en la tabla CLIENTE.");
            System.out.print("Ingrese el ID del cliente (6 caracteres): ");
            String idCte = scanner.next();

            System.out.print("Ingrese el nombre del cliente: ");
            String nombre = scanner.next();

            System.out.print("Ingrese el apellido paterno: ");
            String apellidoPaterno = scanner.next();

            System.out.print("Ingrese el apellido materno (puede dejarlo vacío): ");
            String apellidoMaterno = scanner.next();

            System.out.print("Ingrese el teléfono (10 dígitos): ");
            String telefono = scanner.next();

            System.out.print("Ingrese el email: ");
            String email = scanner.next();

            System.out.print("Ingrese la contraseña: ");
            String contraseñaCliente = scanner.next();

            System.out.print("Ingrese el código postal: ");
            String codigoPostal = scanner.next();

            System.out.print("Ingrese la calle: ");
            String calle = scanner.next();

            System.out.print("Ingrese el número: ");
            String numero = scanner.next();

            System.out.print("Ingrese el ID de domicilio (4 caracteres max): ");
            String coDomicilio = scanner.next();

            // Asignar valores al PreparedStatement
            preparedStatement.setString(1, idCte);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, apellidoPaterno);
            preparedStatement.setString(4, apellidoMaterno.isEmpty() ? null : apellidoMaterno);
            preparedStatement.setString(5, telefono);
            preparedStatement.setString(6, email);
            preparedStatement.setString(7, contraseñaCliente);
            preparedStatement.setString(8, codigoPostal);
            preparedStatement.setString(9, calle);
            preparedStatement.setString(10, numero);
            preparedStatement.setString(11, coDomicilio);

            int filasAfectadas = preparedStatement.executeUpdate();
            System.out.println("Inserción exitosa en CLIENTE. Filas afectadas: " + filasAfectadas);

        } catch (SQLException e) {
            System.err.println("Error al insertar en CLIENTE: " + e.getMessage());
        }
    }
    
    private static void consultarClientes(String jdbcURL, String usuario, String contraseña) {
        String selectSQL = "SELECT * FROM CLIENTE";
        try (Connection conexion = DriverManager.getConnection(jdbcURL, usuario, contraseña);
             Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            System.out.println("\nDatos en la tabla CLIENTE:");
            while (resultSet.next()) {
                System.out.println("ID_CTE: " + resultSet.getString("ID_CTE") +
                                   ", NOMBRE: " + resultSet.getString("NOMBRE") +
                                   ", APELLIDO PATERNO: " + resultSet.getString("APELLIDO_PATERNO") +
                                   ", APELLIDO MATERNO: " + resultSet.getString("APELLIDO_MATERNO") +
                                   ", TELEFONO: " + resultSet.getString("TELEFONO") +
                                   ", EMAIL: " + resultSet.getString("EMAIL") +
                                   ", CONTRASEÑA: " + resultSet.getString("CONTRASEÑA") +
                                   ", CODIGO POSTAL: " + resultSet.getString("CODIGO_POSTAL") +
                                   ", CALLE: " + resultSet.getString("CALLE") +
                                   ", NUMERO: " + resultSet.getString("NUMERO") +
                                   ", CO_DOMICILIO: " + resultSet.getString("CO_DOMICILIO"));
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar CLIENTE: " + e.getMessage());
        }
    }
}
