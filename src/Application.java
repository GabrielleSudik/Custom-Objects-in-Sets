import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

//how to use your own custom object as 
//objects in sets, or keys in maps
//so far we worked with standard objects (like strings, ints)

class Person{
	
	private int id;
	private String name;
	
	public Person(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String toString(){
		return "**ID is : " + id + " and Name is : " + name;
	}

	//rt click -- source -- get hashCodes: Give you the next two things:
	//you need this stuff so the sets and maps can eliminate duplicates.
	//they know to do that with primitive types.
	//but need to be told how with custom objects.
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
}

public class Application {

	public static void main (String[] args){
		
		Map<String, Integer> map1 = new LinkedHashMap<String, Integer>();
		//CTRL SHIFT O (as in oh!) will import the utils.
		//it seems to delete unneeded utils, too. groovy.
		
		map1.put("one", 1);
		map1.put("two",  2);
		map1.put("three",  3);
		map1.put("one", 4);
		
		for (String key: map1.keySet()) {
			System.out.println(key + " : " + map1.get(key));
		}
		
		Set<String> set1 = new LinkedHashSet<String>();
		
		set1.add("dog");
		set1.add("cat");
		set1.add("mouse");
		set1.add("cat");
		
		System.out.println(set1);
		
		Person p1 = new Person(0, "Bob");
		Person p2 = new Person(1, "Sue");
		Person p3 = new Person(2, "Mike");
		Person p4 = new Person(1, "Sue");
		
		Map<Person, Integer> map2 = new LinkedHashMap<Person, Integer>();
		
		Set<Person> set2 = new LinkedHashSet<Person>();
		
		map2.put(p1, 1);
		map2.put(p2, 2);
		map2.put(p3, 3);
		map2.put(p4, 1);
		
		for (Person key: map2.keySet()) {
			System.out.println(key + " : " + map2.get(key));
		}
		
		set2.add(p1);
		set2.add(p2);
		set2.add(p3);
		set2.add(p4);
		
		System.out.println(set2);
		
		//you actually get some weird output here
		//unless you add the get hashCodes and equals.
		//basically, without those you get duplicates.
	}
}
