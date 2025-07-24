package com.corejava.concepts.designpattern.behavioural;

class UserRequest {
	private String username;
	private String email;
	private String password;

	public UserRequest(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

/**
 * This class also represent a Template which is base of Template Design Pattern
 */
abstract class UserSignUpHandler {
	// this reference works like the pointer to next handler
	private UserSignUpHandler signUpHandler;

	public void setNext(UserSignUpHandler userSignUpHandler) {
		this.signUpHandler = userSignUpHandler;
	}

	public void handle(UserRequest request) {
		validate(request);
		if (signUpHandler != null) {
			signUpHandler.handle(request);
		}
	}

	abstract public void validate(UserRequest request);
}

class UsernameHandler extends UserSignUpHandler {

	@Override
	public void validate(UserRequest request) {
		if (request.getUsername() == null || request.getUsername().equals("")) {
			throw new IllegalArgumentException("Username shouldn't be empty!");
		}
		System.out.println(request.getUsername() + " username is validated successfully");
	}

}

class EmailHandler extends UserSignUpHandler {

	@Override
	public void validate(UserRequest request) {
		if (request.getEmail() == null || request.getEmail().equals("")) {
			throw new IllegalArgumentException(request.getUsername() + " Email shouldn't be empty!");
		} else if (!request.getEmail().endsWith("@gmail.com")) {
			throw new IllegalArgumentException(request.getUsername() + " Email should end with @gmail.com!");
		}

		System.out.println(request.getUsername() + " email " + request.getEmail() + " is validate successfully");
	}

}

class PasswordHandler extends UserSignUpHandler {

	@Override
	public void validate(UserRequest request) {
		if (request.getPassword() == null || request.getPassword().equals("")) {
			throw new IllegalArgumentException("Password shouldn't be empty!");
		} else if (request.getEmail().length() < 8) {
			throw new IllegalArgumentException("Password should contains atleast 8 characters");
		}

		System.out.println(request.getUsername() + " password is validated successfully");
	}

}

public class ChainOfResponsibilityPattern1 {

	public static void main(String[] args) {
		UserRequest request1 = new UserRequest("Sourabh", "sourabh@gmail.com", "7777777777");
		UserRequest request2 = new UserRequest("Abhishek", "abhishek@email.com", "3333");
		UserSignUpHandler usernameHandler = new UsernameHandler();
		UserSignUpHandler emailHandler = new EmailHandler();
		usernameHandler.setNext(emailHandler);
		emailHandler.setNext(new PasswordHandler());
		usernameHandler.handle(request1);
		usernameHandler.handle(request2);
	}

}
