package org.hakimbocar;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class AnalyzeBean {

	// Question 1
	public String getClassName(Object o) {
		Class<?> classe = o.getClass();
		return classe.getName();
	}

	// Question 2
	public Object getInstance(String className) {
		try {
			Class<?> classe2 = Class.forName(className);
			Object newInstance = classe2.newInstance();
			return newInstance;

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	// Question 3 : Ce sont les getters qui definissent les property donc on va
	// devoir recuperer les méthodes commenceant par get ou par is

	public List<String> getProperties(Object o) {
		List<String> properties = new ArrayList<>(); 
		// getDeclaredMethods nous donne la garantie que les methodes que l'on recupere sont des methodes de la classe
		Method[] declaredMethods = o.getClass().getDeclaredMethods(); 
		// On commence par recuperer toutes les methodes dans un tableau
		for (Method method : declaredMethods) {
			String methodName = method.getName(); // On selectionne ensuite les getters : commencent par get ou is +
													// n'ont pas de parametre + methode est public
			if ((methodName.startsWith("get") || methodName.startsWith("is")) && method.getParameterCount() == 0
					&& Modifier.isPublic(method.getModifiers())) { 
				// Test que la methode est bien public 
				// getDeclaredMethods methodes publics et privees
				// Extraction nom de la propriete donc suppression des 2 premiers caracteres
				// si methode commence par is et des 3 premiers sinon (on aura
				// alors une methode qui commence par get)
				
				String propertyName = "";
				if (methodName.startsWith("is")) {
					propertyName = methodName.substring(2);
				} else {
					propertyName = methodName.substring(3);
				}
				// Finalement on prend le 1er caractere et on le met en minuscule
				propertyName = propertyName.substring(0, 1).toLowerCase() + propertyName.substring(1);
				properties.add(propertyName); // On ajoute le nom de propriete a la liste de String
			}
		}
		return properties;
	}

	// Question 4

	public Object get(Object bean, String property) {
		// List<String> properties2 = getProperties(bean);

		Field fields[] = bean.getClass().getDeclaredFields(); // On commence par recuperer tous les champs dans un tableau

		for (Field f : fields) {
			if (!f.getName().equals(property))
				continue;
			f.setAccessible(true); // Permet d'acceder au champ prives
			try {
				return f.get(bean);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			break;
		}

		return null;

	}

	// Question 5
	public void set(Object bean, String property, Object value) {
		// List<String> properties2 = getProperties(bean);

		Field fields2[] = bean.getClass().getDeclaredFields();

		for (Field f : fields2) {
			if (!f.getName().equals(property))
				continue;
			f.setAccessible(true); // Permet d'acceder au champ prives
			try {
				f.set(bean, value);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			break;
		}

	}

}
