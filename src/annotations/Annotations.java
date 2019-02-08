package annotations;

import annotations.model.User;

public class Annotations {
	public static void main(String[] args) throws IllegalAccessException {
		System.out.println("Main application");
		
		User user = new User ("samair", "holahola");
		
		JsonSerializer jsonSer = new JsonSerializer();
		
		String json = jsonSer.serialize(user);
		System.out.println(json);
	} 

}
