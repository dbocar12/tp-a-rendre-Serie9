package org.hakimbocar;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PersonReader extends FileReader {
 
	private BufferedReader br ;



	public PersonReader(FileReader fr)  {
		super(FileDescriptor.in);
 
		br= new BufferedReader(fr);
	}

	
	
	private Field myGetDeclaredField( Class<?> cls, String filedName) throws NoSuchFieldException { 
		//Permet de recuperer tous les champs d'une classe qui etend une autre classe
	do {
 	try {
			Field nameField = cls.getDeclaredField(filedName);
			return nameField;

		} catch (NoSuchFieldException e) {
			 cls = cls.getSuperclass(); 
			 if (cls==null)  
			     throw (e);
		}
			 
		} while (true);
	
	 
	}
	
	public List<Object> readPeople() { 
		Set<String> beanKeys = new HashSet<>();
		Map<String, Object> beanRegistry = new HashMap<>();
      try {
			String line = br.readLine();
			while (line != null) {
				if (!line.startsWith("#")) {

					String[] element = line.split("=");
					if (element[0].equals("bean.name")) {
						beanKeys.add(element[1]);

					} else if (element[0].endsWith(".class")) {
						String beanKey = element[0].substring(0, element[0].indexOf('.'));
						String className = element[1];
						Class<?> beanClass = Class.forName(className);
						Constructor<?> beanEmptyConstructor = beanClass.getConstructor();
						Object bean = beanEmptyConstructor.newInstance();
						beanRegistry.put(beanKey, bean);

					} else if (element[0].endsWith(".lastName")) {
						String beanKey = element[0].substring(0, element[0].indexOf('.'));
						String lastName = element[1];

						Object bean = beanRegistry.get(beanKey);
						Field nameField = myGetDeclaredField ( bean.getClass(), "lastName");
						nameField.setAccessible(true);
						nameField.set(bean, lastName);
					} else if (element[0].endsWith(".firstName")) {
						String beanKey = element[0].substring(0, element[0].indexOf('.'));
						String firstName = element[1];

						Object bean = beanRegistry.get(beanKey);
						Field nameField = myGetDeclaredField ( bean.getClass(), "firstName");
						nameField.setAccessible(true);
						nameField.set(bean, firstName);

					} else if (element[0].endsWith(".age")) {
						String beanKey = element[0].substring(0, element[0].indexOf('.'));
						int age = Integer.parseInt(element[1]);

						Object bean = beanRegistry.get(beanKey);
						Field ageField = myGetDeclaredField ( bean.getClass(), "age");
						ageField.setAccessible(true);
						ageField.set(bean, age);
					} else if (element[0].endsWith(".salary")) {
						String beanKey = element[0].substring(0, element[0].indexOf('.'));

						double salaire = Double.parseDouble(element[1]);

						Object bean = beanRegistry.get(beanKey);
						Field salaryField =myGetDeclaredField ( bean.getClass(), "salary");
						salaryField.setAccessible(true);
						salaryField.set(bean, salaire);
					}

				}
				try {
					line = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Object> listObject = new ArrayList<>();

		System.out.println("Analyzed keys");
		beanKeys.forEach(System.out::println);

		System.out.println("\nCreated beans");
		beanRegistry.forEach((k, v) -> listObject.add(v));

		return listObject;

	}

}
