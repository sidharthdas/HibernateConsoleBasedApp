package com.mindtree.daoImpl;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mindtree.dao.EmployeeEventDAO;
import com.mindtree.exception.DAOException;
import com.mindtree.model.Employees;
import com.mindtree.model.Events;
import com.mindtree.util.HibernateConfig;

public class EmployeeEventDAOImpl implements EmployeeEventDAO {
	
	private static final Logger log = Logger.getLogger(EmployeeEventDAOImpl.class);
	
	HibernateConfig config = new HibernateConfig();

	public String employeeMIDFromDB(String MID) {
		config.getSession().beginTransaction();
		String empMID = config.getSession().createQuery("SELECT MID FROM EMPLOYEES WHERE MID = ?").setParameter(0, MID)
				.list().toString();
		return empMID;
	}

	public String eventTileFromDB(String eventTitle) {
		config.getSession().beginTransaction();
		String eveTitle = config.getSession().createQuery("SELECT EVENT_TITLE FROM EVENTS WHERE EVENT_TITLE = ?")
				.setParameter(0, eventTitle).list().toString();
		return eveTitle;

	}

	public Boolean checkEmployee(String MID) throws DAOException {
		String empName = employeeMIDFromDB(MID);
		if (empName.equals("[]")) {
			throw new DAOException("MID not found");
		}
		return true;

	}

	public Boolean checkEvent(String EVENT_TITLE) throws DAOException {
		String eveTitle = eventTileFromDB(EVENT_TITLE);
		if (eveTitle.equals("[]")) {
			throw new DAOException("Event not found");
		}
		return true;

	}

	public Boolean checkEmployeesEvent(String MID, String EVENT_TITLE) {
		config.getSession().beginTransaction();
		String EVENT_ID = config.getSession().createQuery("SELECT EVENT_ID FROM EVENTS WHERE EVENT_TITLE = ?")
				.setParameter(0, EVENT_TITLE).list().toString();
		int eveID = Integer.parseInt(EVENT_ID.substring(1, EVENT_ID.length() - 1));
		String employee_events = config.getSession()
				.createSQLQuery(
						"SELECT employees_MID FROM EMPLOYEES_EVENTS WHERE employees_MID = ? AND events_EVENT_ID = ?")
				.setParameter(0, MID).setParameter(1, eveID).list().toString();
		if (employee_events.equals("[]")) {
			return false;
		}
		return true;
	}
	@Override
	public String registerEmployeeForEvent(String MID, String EVENT_TITLE) {
		try {
			Boolean name = checkEmployee(MID);
			Boolean event = checkEvent(EVENT_TITLE);

			if (name.equals(true) && event.equals(true)) {
				Boolean employeesEvents = checkEmployeesEvent(MID, EVENT_TITLE);
				if (employeesEvents) {
					return "Event Already Associated";
				}
				config.getSession().beginTransaction();
				ArrayList<Employees> emp = (ArrayList<Employees>) config.getSession()
						.createQuery("FROM EMPLOYEES WHERE MID = ?").setParameter(0, MID).list();

				ArrayList<Events> eve = (ArrayList<Events>) config.getSession()
						.createQuery("FROM EVENTS WHERE EVENT_TITLE = ?").setParameter(0, EVENT_TITLE).list();
				Employees empp = emp.get(0);
				System.out.println(empp.getEMAIL_ID());
				System.out.println(empp.getMID());
				Events evee = eve.get(0);
				empp.getEvents().add(evee);
				evee.getEmployees().add(empp);
				config.getSession().update(empp);
				config.getSession().update(evee);
				config.getSession().getTransaction().commit();
				return "Event tagged to Employee with MID: " + MID + " sucessfully.";
			}
		} catch (DAOException ee) {
			log.error(ee.getMessage()+": "+ee);
		}

		return "Could not add event due to above reason!!!";

	}
	@Override
	public void getAllEmployees() {
		config.getSession().beginTransaction();
		ArrayList<Employees> employees = (ArrayList<Employees>) config.getSession().createQuery("FROM EMPLOYEES")
				.list();

		for (Employees ed : employees) {
			System.out.println(ed.getMID());
			System.out.println(ed.getNAME());
			System.out.println(ed.getJOIN_DATE());
			System.out.println(ed.getEMAIL_ID());
			if (ed.getEvents().isEmpty()) {
				System.out.println("----No event associated.---");
			} else {
				System.out.println(" > Event associated: ");
				for (Events event : ed.getEvents()) {
					System.out.println(event.getEVENT_TITLE());
				}
			}
			System.out.println("_______________________________________");
		}


}
}
