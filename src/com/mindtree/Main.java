package com.mindtree;

import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.mindtree.dao.EmployeeEventDAO;
import com.mindtree.daoImpl.EmployeeEventDAOImpl;

public class Main {
	
	private static final Logger log = Logger.getLogger(Main.class);
	
	public static void main(String[] args) throws IOException {

		EmployeeEventDAO employeeEventDAO = new EmployeeEventDAOImpl();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("          Welcome        ");
			System.out.println("Choose Option: (ex. 1,2 or 0)");
			System.out.println("  1.Register Employee For Event.");
			System.out.println("  2.Display All Employees");
			System.out.println("  0.Bye");
			int option = 0;
			try {
				option = sc.nextInt();
				sc.nextLine();
			} catch (Exception ee) {
				System.out.println("Input Should Be An Integer");
				log.error(ee+ ": Input Should Be An Integer");
				sc.nextLine();
				continue;
				
			}
			if (option != 0) {
				switch (option) {

				case 1:

					System.out.println("Enter MID of the Employee.(ex. MXXXXXXX): ");
					String MID = sc.nextLine();
					System.out.println(MID);
					System.out.println("Enter Event title: ");
					String EVENT_TITLE = sc.nextLine();
					System.out.println(EVENT_TITLE);
					System.out.println(employeeEventDAO.registerEmployeeForEvent(MID, EVENT_TITLE));
					break;

				case 2:
					employeeEventDAO.getAllEmployees();
					break;

				default:
					System.out.println("Please enter 1, 2 or 0");
					break;
				}
			}
			if (option == 0) {
				System.out.println("Bye bye");
				sc.close();
				break;
			}
		}
	}
}
