package com.stackroute;

public class Person {

    private  String userName;
    private String password;
    private int age;

    public Person(String userName, String password, int age) {
        this.userName = userName;
        this.password = password;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean checkPassword(){
        if(password.length()<6){
            return  false;
        }
        else {
            return  true;
        }
    }

    public String validate(String userName, String password) {
        if(userName==null || password==null){
            return "UserName and Password cannot be null";
        }
        if(userName.equals("Admin")&& password.equals("Password")){
            return "Admin User";
        }
        else {
            return "Normal User";
        }
    }

    public void checkAge() throws NegativeAgeException {
        if(age<0){
            throw new NegativeAgeException("Age cannot be -ve");
        }
    }
}
