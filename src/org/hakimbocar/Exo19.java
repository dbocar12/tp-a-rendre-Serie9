package org.hakimbocar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Exo19 {

	public static void main(String[] args) throws FileNotFoundException {

		// Test question 1

		AnalyzeBean beanAnalyzer = new AnalyzeBean();
		Person person = new Person("Deschamps", "Didier", 54);
		Employee employe = new Employee(2500);
		List<Person> people = new ArrayList<>();
		Set<Employee> workers = new HashSet<>();

		System.out.println(beanAnalyzer.getClassName(beanAnalyzer));
		System.out.println(beanAnalyzer.getClassName(people));
		System.out.println(beanAnalyzer.getClassName(workers));
		System.out.println("==================================================================");

		// Test question 2
		Person p = (Person) beanAnalyzer.getInstance("org.hakimbocar.Person");
		Employee e = (Employee) beanAnalyzer.getInstance("org.hakimbocar.Employee");
		System.out.println("p = " + p);
		System.out.println("e = " + e);
		System.out.println("==================================================================");

		// AAAAAAAAAAAAA ???? À quelle condition cette methode pourra-t-elle instancier
		// la classe passée en paramètre ???

		// Test question 3

		List<String> propertiesPerson = beanAnalyzer.getProperties(p);
		List<String> propertiesEmployee = beanAnalyzer.getProperties(e);
		System.out.println("properties = " + propertiesPerson);
		System.out.println("properties = " + propertiesEmployee);
		System.out.println("==================================================================");

		// Test question 4
		String nom = (String) beanAnalyzer.get(person, "lastName");
		System.out.println("lastName = " + nom);
		System.out.println("==================================================================");

		// Test question 5
		beanAnalyzer.set(person, "lastName", "Zidane");
		System.out.println("lastName = " + person.getLastName());

		// Test question 6
		String fileName = "files/people.txt";
		List<Object> objs = null;
		File f = new File(fileName);

		try (PersonReader reader = new PersonReader(new FileReader(fileName));) {

			objs = reader.readPeople();
			reader.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Test question 7
		String fileNameOut = "files/people2.txt";

		if (objs != null) {
			try (PersonWriter writer = new PersonWriter(new FileWriter(fileNameOut));) {

				writer.write(objs);
				writer.close(); 

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
		}

	}

}
