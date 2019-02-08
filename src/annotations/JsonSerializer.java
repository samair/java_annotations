package annotations;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class JsonSerializer {
	
	public String serialize(Object object) throws IllegalAccessException {
		
		Class<?> objectClass = (object).getClass();
		Map<String,String> jsonElements = new HashMap<String,String>();

		//get all the fields using reflection
		for (Field field : objectClass.getDeclaredFields()) {
			field.setAccessible(true);
			
			//if annotation is present on a field
			if (field.isAnnotationPresent(JsonField.class)) {
				
				
			    jsonElements.put(getName(field), (String)field.get(object));

				
			}
		}
		return toJsonString(jsonElements);

	}
	
	private String toJsonString(Map<String,String> jsonElements) {
		
		String jsonString = jsonElements.entrySet().stream().map(entry-> "\""+entry.getKey()+"\":\""+entry.getValue() +"\"").collect(joining(","));
		
		return "{ "+ jsonString + "}";
	}
	
	private String getName(Field field) {
		
		String name = field.getAnnotation(JsonField.class).value();
		
		if (name.isEmpty()) {
			name = field.getName();
		}
		
		return name;
		
	}

}
