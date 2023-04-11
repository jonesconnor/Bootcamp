package com.cgi.InnerClasses;
//Login is outer class
public class Login {
    private  String userName;
    private String password;

    class  ValidateCredentails {
        public void validate(String name, String pwsd){
            userName = name;
            password = pwsd;
            if(userName.equalsIgnoreCase("sachin")&& password.equals("abc123")){
                System.out.println("login successfully");
            }
            else {
                System.out.println("invalid credentials");
            }
        }

    }
    public static void main(String[] args) {
          Login login = new Login();
          Login.ValidateCredentails credentails = login.new ValidateCredentails();
          credentails.validate("sachind","abc123");

    }
}
