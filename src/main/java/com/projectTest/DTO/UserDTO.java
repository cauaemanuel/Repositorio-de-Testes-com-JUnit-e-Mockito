package com.projectTest.DTO;

public class UserDTO{
	
		private Integer id;
		private String name;
		private String email;
		private String password;
		
		public UserDTO() {
		}
		
		public UserDTO(Integer id, String name, String email, String password) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.password = password;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		
}
