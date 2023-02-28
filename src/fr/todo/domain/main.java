package fr.todo.domain;

import java.util.Scanner;


import fr.todo.module.*;
import fr.todo.operation.*;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		 int cx ;
		CRUDTodo.connection();
    	 do {
    		 
    		    System.out.println( "Menu: \n"
    		    		+ "1) Ajouter un todo\r\n"
    		    		+ "2) voir les todos\r\n"
    		    		+ "3) retirer et afficher un todo par index\r\n"
    		    		+ "4) update et afficher le un todo par index\r\n"
    		    		+ "5) quitter \r\n");
    		    
    		    Scanner in = new Scanner(System.in);
    		    cx = in.nextInt();
    		    switch ( cx ) {
    		        case 1: {  
    		        	        Scanner s = new Scanner(System.in);
    		                    System.out.println(" insert titre :");
    		                    String a  = s.nextLine();
    		                    System.out.println(" insert description :");
    		                    String b  = s.nextLine();
    		                    int c=0;
    		                    do {
	    		                    System.out.println(" insert Urgence (0:HAUTE, 1:NORMALE, 2:FAIBLE) :");
				                    c  = s.nextInt();
    		                    } while (c<0 || c>2);
    		                    Todo t= new Todo(0, a, b,Urgence.values()[c]);
    		                    System.out.println(" todo : " + t.toString() + " id : " + CRUDTodo.insertionTodo(t));
    		                    break;
    		                  }
    		        case 2: {
    		        	        for(Todo todo : CRUDTodo.selectTodo())
			                    System.out.println("todo " + todo.getId() + " : " + todo.toString());
			                    break;
    		                  }
    		        case 3: {
		    		        	Scanner s = new Scanner(System.in);
			                    System.out.println(" insert un id a retirer :");
			                    int r  = s.nextInt();
			                    System.out.println(" deleted todo : " + CRUDTodo.selectParId(r).toString());
						        CRUDTodo.deleteTodo(r);
			                    break;
    		                  }
    		        case 4: {
								Scanner sx = new Scanner(System.in);
								System.out.println(" insert un id a modifier :");
								int r  = sx.nextInt();
								System.out.println(" update todo : " + CRUDTodo.selectParId(r).toString());
								Scanner s = new Scanner(System.in);
								System.out.println(" insert titre :");
								String a  = s.nextLine();
								System.out.println(" insert description :");
								String b  = s.nextLine();
								int c=0;
								do {
									System.out.println(" insert Urgence (0:HAUTE, 1:NORMALE, 2:FAIBLE) :");
									c  = s.nextInt();
								} while (c<0 || c>2);
						        Todo t= new Todo(r,a, b, Urgence.values()[c]);
						        CRUDTodo.updateTodo(t);
			                    break;
    		                  }
    		         case 5: {
    		        	        System.out.println(" thanks ");

    		                    break;
    		                  }
    		       default : System.out.println("please insert number in menu ");
    		    }

    		} while ( cx != 5 );
		CRUDTodo.disconnection();
    	 System.out.println(" bye bye ");
	}

}
