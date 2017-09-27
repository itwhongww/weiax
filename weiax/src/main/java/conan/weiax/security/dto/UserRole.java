package conan.weiax.security.dto;

import java.io.Serializable;

public class UserRole implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8003572048755579213L;
	private Long userRoleId;
	private Long userId;
	private Long roleId;
	private String description;
	public UserRole() {
		super();
	}
	public UserRole(Long userRoleId, Long userId, Long roleId,
			String description) {
		super();
		this.userRoleId = userRoleId;
		this.userId = userId;
		this.roleId = roleId;
		this.description = description;
	}
	public Long getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "UserRole [userRoleId=" + userRoleId + ", userId=" + userId
				+ ", roleId=" + roleId + ", description=" + description + "]";
	}
	
}
