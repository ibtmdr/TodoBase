

package fr.todo.operation;
import fr.todo.module.*;

import java.sql.*;
import java.util.ArrayList;

public class CRUDTodo {
    private static Connection connection;
	public static void connection() {

		try{
			// Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/base", "root", "Ibtissam@12345");
		    connection.setAutoCommit(false);
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}

	}

	public static void disconnection(){
		try {

			connection.close();
		}catch (SQLException e){
			e.getMessage();
		}
	}

		public static void deleteTodo(int id) {

		try {


			PreparedStatement ps = connection.prepareStatement("DELETE FROM todo WHERE id = ? ");
			// pour ajouter un valur en enter de requete (premier ?)
			ps.setInt(1, id);
			// supprission de la base
			ps.executeUpdate();
			connection.commit();


		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}
		}

	}





	public static int insertionTodo(Todo todo){
        int i = 0;
		try {


			PreparedStatement ps = connection.prepareStatement("INSERT INTO todo (titre,description,urgence) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			// pour ajouter un valur en enter de requete (premier ?)
			ps.setString(1, todo.getTitre());
			// pour ajouter un valur en enter de requete (deuxieme ?)
			ps.setString(2, todo.getDescription());
			// pour ajouter un valur en enter de requete (troisieme ?)
			ps.setString(3, todo.getUrg().toString());
			// insertion a la base
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				i = rs.getInt(1);
			}
			connection.commit();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}
		}
        return i ;
	}





	public static ArrayList<Todo> selectTodo() {

		ArrayList<Todo> todos= new ArrayList<Todo>();

		try {

			// Porteur de requette sql
			Statement stmt = connection.createStatement();
			// Executer la requette sql , met le retour dans result state
			ResultSet resultats = stmt.executeQuery("SELECT * FROM todo");

			// lire le resultat ligne par ligne
			while(resultats.next()) {
				todos.add(new Todo(resultats.getInt(1), resultats.getString(2), resultats.getString(3), Urgence.valueOf(resultats.getString(4))));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return todos;

	}
	public static Todo selectParId(int id) {

		Todo todo = null;

		try {


			PreparedStatement ps = connection.prepareStatement("SELECT * FROM todo WHERE id  = ? ");
			// pour ajouter un valur en enter de requete (premier ?)
			ps.setInt(1, id);
			ResultSet resultats = ps.executeQuery();
			System.out.println("clients :");

			while(resultats.next())
				todo = new Todo(resultats.getInt(1),resultats.getString(2),resultats.getString(3),Urgence.valueOf(resultats.getString(4)));


		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}



		return todo;

	}


	public static void updateTodo(Todo todo)  {
					
					try {
			
						
						PreparedStatement ps = connection.prepareStatement("UPDATE todo SET titre = ? , description= ?, urgence = ? WHERE id = ? ");
						// pour ajouter un valur en enter de requete (premier ?)
						ps.setString(1, todo.getTitre());
						// pour ajouter un valur en enter de requete (deuxieme ?)
						ps.setString(2, todo.getDescription());
						// pour ajouter un valur en enter de requete (deuxieme ?)
						ps.setString(3, todo.getUrg().toString());
						// pour ajouter un valur en enter de requete (deuxieme ?)
						ps.setInt(4, todo.getId());
                        ps.executeUpdate();
						connection.commit();
					} catch (SQLException e) {
						System.out.println(e.getMessage());
						try {
							connection.rollback();
						} catch (SQLException ex) {
							throw new RuntimeException(ex);
						}
					}

				}

			

			

		}



