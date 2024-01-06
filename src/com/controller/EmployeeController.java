package com.controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.enums.RoleType;
import com.exception.InvalidIdException;
import com.model.Department;
import com.model.Employee;
import com.model.Manager;
import com.model.User;
import com.service.DepartmentService;
import com.service.EmployeeService;
import com.service.ManagerService;
import com.service.UserService;

public class EmployeeController {
	public static void main(String args[]) {
		//reaching out to persistence unit
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("mydbunit");
		// creating entitymanager using entityManagerFactory
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		//creating transaction using entityManager reference and used for sql operations
		EntityTransaction entityTransaction=entityManager.getTransaction();
		Scanner sc=new Scanner(System.in);
		UserService userService=new UserService(entityManager);
		ManagerService managerService=new ManagerService(entityManager);
		DepartmentService departmentService=new DepartmentService(entityManager);
		EmployeeService employeeService=new EmployeeService(entityManager);
		entityTransaction.begin();
		while(true) {
			System.out.println("********Employee Management System********");
			System.out.println("1.Add Manager");
			System.out.println("2.Add Department");
			System.out.println("3.Add Employee");
			System.out.println("4.Show all employees");
			System.out.println("5.Employee Login");
			System.out.println("6.Update Employee");
			System.out.println("7.Delete Employee");
			System.out.println("0.Exit");
			System.out.println("*******************************************");
			int input =sc.nextInt();
			sc.nextLine();
			if(input==0) {
				break;
			}
			switch (input) {
			case 1:
				System.out.println("Enter name");
				String name=sc.nextLine();
				System.out.println("Enter empCode");
				String empCode=sc.nextLine();
				Manager manager=new Manager();
				manager.setName(name);
				manager.setEmpcode(empCode);
				System.out.println("Enter username");
				String username=sc.nextLine();
				System.out.println("Enter password");
				String password=sc.nextLine();
				System.out.println("Reenter your password");
				String repassword=sc.nextLine();
				if(!(password.equals(repassword))) {
					System.out.println("Password wrong try again");
					break;
				}
				//creted user object and set my attributes
				User user=new User();
				user.setUsername(username);
				user.setPassword(password);
				user.setRole(RoleType.MANAGER);
				//now save the user in db
				user=userService.insertUser(user);
				manager.setUser(user);
				managerService.insertManager(manager);
				System.out.println("manager inserted successfully");
				entityTransaction.commit();
				break;
			case 2:
					 System.out.println("enter name");
					 name=sc.nextLine();
					 System.out.println("enter description");
					 String description=sc.nextLine();
					 Department department=new Department();
					 department.setName(name);
					 department.setDescription(description);
					 departmentService.insert(department);
					 entityTransaction.commit();
				break;
			case 3:
				System.out.println("enter name");
				name=sc.nextLine();
				System.out.println("enter contact");
				String contact=sc.nextLine();
				System.out.println("enter designation");
				String designation=sc.nextLine();
				System.out.println("enter salary");
				Double salary=sc.nextDouble();
				sc.nextLine();
				Employee employee=new Employee();
				employee.setName(name);
				employee.setContact(contact);
				employee.setDesignation(designation);
				System.out.println("enter username");
				username=sc.nextLine();
				System.out.println("enter password");
				password=sc.nextLine();
				System.out.println("enter password again");
				repassword=sc.nextLine();
				if(!(password.equals(repassword))) {
					System.out.println("Password wrong try again");
					break;
				}
				user=new User();
				user.setUsername(username);
				user.setPassword(password);
				user.setRole(RoleType.EMPLOYEE);
				user=userService.insertUser(user);
				employee.setUser(user);
				System.out.println("enter deparment id");
				int id=sc.nextInt();
				 department = null;
//				try {
//					department = departmentService.getDepartment(id);
//					employee.setDepartment(department);
//				} catch (InvalidIdException e) {
//					// TODO Auto-generated catch block
//					System.out.println(e.getMessage());
//				}
//				System.out.println(department);
				System.out.println("enter manager id");
				id=sc.nextInt();
				manager=null;
				try {
					department = departmentService.getDepartment(id);
					employee.setDepartment(department);
					manager=managerService.getManager(id);
					employee.setManager(manager);
					employeeService.insertEmployee(employee);
					System.out.println("Employee inserted in db");
				} catch (InvalidIdException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				entityTransaction.commit();
				break;
			case 4:
				System.out.println("Enter username");
				 username=sc.nextLine();
				System.out.println("Enter password");
				 password=sc.nextLine();
				String role=RoleType.MANAGER.toString();
				boolean status=userService.login(username,password,role);
				 if(status==true) {
					 System.out.println("Show all employees");
					 user=userService.getIdByUsername(username);
					 int userid=user.getId();
					 int managerid=managerService.getManagerById(userid);
					 List<Employee> list=employeeService.getAllEmployees(managerid);
					 if(list.isEmpty()) {
						 System.out.println("No employees allocated till now");
					 }
					 list.stream().forEach(e->System.out.println(e));
				 }
				 else {
					 System.out.println("Invalid credentails");
				 }
				 
				 break;
			case 5:
				 System.out.println("Enter the username");
				 username=sc.next();
				 System.out.println("Enter password");
				 password = sc.next();
				 role="EMPLOYEE";
				 status=userService.login(username,password,role);
				 if(status==true) {
					 System.out.println("Welcome "+username);
				 }
				 else {
					 System.out.println("Invalid username, please try again !");
				 }
				 
				 break;
			case 6:
				System.out.println("Enter employee Id to update");
				id=sc.nextInt();
				sc.nextLine();
				try {
					Employee updateEmployee=employeeService.getEmployeeById(id);
					System.out.println("Enter name");
					String newname=sc.nextLine();
					if(!newname.isEmpty()) {
						updateEmployee.setName(newname);
					}
					System.out.println("Enter contact");
					String newcontact=sc.nextLine();
					if(!newcontact.isEmpty()) {
						updateEmployee.setName(newcontact);
					}
					System.out.println("Enter designation");
					String newdesignation=sc.nextLine();
					if(!newdesignation.isEmpty()) {
						updateEmployee.setName(newdesignation);
					}
					System.out.println("Enter salary");
					String newsalary = sc.nextLine();
				    if (!newsalary.isEmpty()) {
				        // Assuming salary is a numeric value, you might need to parse it to the appropriate type (e.g., Double)
				        updateEmployee.setSalary(Double.parseDouble(newsalary));
				    }
					employeeService.updateEmployee(updateEmployee);
					System.out.println("Employee updated successfully");
				} catch (InvalidIdException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				entityTransaction.commit();
				break;
			case 7:
				System.out.println("Enter employee Id to delete");
				id=sc.nextInt();
				sc.nextLine();
				try {
					employee=employeeService.getEmployeeById(id);
					if(employee!=null) {
						employeeService.deleteById(employee);
						System.out.println("Deleted Succesfully");
					}
				} catch (InvalidIdException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				entityTransaction.commit();
				break;
			default:
				System.out.println("Invalid input given, try again!!");	
			
			}
			
		}
//		entityTransaction.commit();
		sc.close();
		entityManager.close();
	    entityManagerFactory.close();
	}
}
