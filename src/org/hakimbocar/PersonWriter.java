package org.hakimbocar;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.List;

public class PersonWriter extends FileWriter{
	private BufferedWriter wr ;

 
	
	public PersonWriter(FileWriter writer)  {
		super(FileDescriptor.in);
		wr = new BufferedWriter(writer);
	}
	
	public void write(List<Object> beans) {
		
		 	String nameBean;
		try  {
			int num=1;
			wr.write("# Ceci est une ligne de commentaires2\n");
			 wr.flush();

			for (Object bean : beans) {
				nameBean="p"+num; num++;
				wr.write("bean.name="+ nameBean+"\n");
				 wr.flush();
				
				 Class<?> cls;
				    cls=bean.getClass();
				    wr.write(nameBean+".class=" + cls.getName()+"\n");
				    wr.flush();
					
				 do {		 	
				Field fields[] = cls.getDeclaredFields(); // On commence par recuperer tous les champs dans un tableau

				for (Field field : fields) {
								 
					field.setAccessible(true); // Permet d'acceder au champ prives
					try {
						 			
					    wr.write(nameBean+"."+ field.getName() +"=" + field.get(bean).toString()+"\n");	
					    wr.flush();
								
						 
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				 cls = cls.getSuperclass(); 
			} while (cls!=null && !cls.getName().endsWith("Object"));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}

}
