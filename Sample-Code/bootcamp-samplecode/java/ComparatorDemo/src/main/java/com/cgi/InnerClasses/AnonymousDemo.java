package com.cgi.InnerClasses;

public class AnonymousDemo {
    public void show(){
        System.out.println("In the main Class show method");
    }
}

//class B extends  AnonymousDemo{
//    @Override
//    public void show() {
//        System.out.println("In Class B  show method");
//    }
//}

class Main{
    public static void main(String[] args) {
//        B b = new B();
//        b.show();

        AnonymousDemo demo = new AnonymousDemo() {
            @Override
            public void show() {
                System.out.println("This is a Anonymous class method");
            }
        };
        demo.show();

    }
}

