package model;

import java.util.Objects;

public class User {
	private String userId;
	private String name;
	
	public User(String userId, String name) {
		super();
		this.userId = userId;
		this.name = name;
	}
	
	public boolean equalsUser(User user) {
		return this.equals(user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, userId);
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
		return Objects.equals(name, other.name) && Objects.equals(userId, other.userId);
	}
	
	
	

}
