package eg.edu.alexu.csd.datastructure.mailServer;

public class IContact {
	String name;
	String email;
	String password;

	public IContact(String name, String email, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

}
