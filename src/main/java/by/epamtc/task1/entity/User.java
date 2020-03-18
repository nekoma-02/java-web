package by.epamtc.task1.entity;

public class User {

	private String name;
	private String login;
	private String password;

	public User() {
		this.name = "name";
		this.login = "login";
		this.password = "password";
	}

	public User(String name, String login, String password) {
		this.name = name;
		this.login = login;
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		User other = (User) obj;

		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;

		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;

		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [name=" + name + ", login=" + login + ", password=" + password + "]";
	}

	public String getLogin() {
		return login;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
