package conan.weiax.security.dto;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3585517686705545175L;
	private Long userId;
	private String username;
	private String password;
	private String description;
	public User() {
		super();
	}
	public User(Long userId, String username, String password,
			String description) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.description = description;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	 @Override
	    public boolean equals(Object o){
	        if(o.toString().equals(this.username))
	            return true;
	        return false;
	    }

	    @Override
	    public int hashCode(){
	        return username.hashCode();
	    }


	    @Override
	    public String toString() {
	        return this.username;
	    }
	
}
