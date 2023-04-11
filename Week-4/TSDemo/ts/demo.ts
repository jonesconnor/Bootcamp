// console.log("Hello from typescript");
let username:string = 'apple';


// arrays

let arr1:Array<string> = ["hello", "world"];
let arr2:Array<string | number> = ["hello", 1, "world"];


// tuples

let valTuple:[string, number] = ['connor', 123];
console.log(valTuple[0]);


// for each that returns index

for(let val in arr1) {
    console.log(arr1[val]);
}


// normal for each

arr2.forEach(element => {
    console.log(element);
});


//functions

function addResult(num1:number, num2:number) {
    return num1 + num2;
}
console.log(addResult(1,1));


//lambdas

let sum = (num1:number, num2:number) => {
    return num1 + num2
}
console.log(sum(2,2));

// classes
class Student {
    private stdId:number;
    private stdName:string;
    private stdEmail:string;

    constructor(stdId:number, stdName:string, stdEmail:string) {
        this.stdId = stdId;
        this.stdName = stdName;
        this.stdEmail = stdEmail;
    }

    getId(): number {
        return this.stdId;
    }

}

let stu1 = new Student(1, "Connor", "connor@uvic.ca");
console.log(stu1.getId())


// Inheritance

// class User {
//     showData(): void {
//         console.log("This is a parent class");
//     }
// }

// class Employee extends User {
//     showData(): void {
//         console.log("This is an employee");
//     }
//     employeeFunctionality() {
//         console.log("I work here");
//     }
// }

// class Manager extends Employee {
//     employeeFunctionality(): void {
//         console.log("I manage");
//     }
// }

// let employee = new Employee();
// employee.showData();
// employee.employeeFunctionality();
// let manager = new Manager();
// manager.employeeFunctionality();


// Interfaces

interface User {
    name:string;
    age:number;
    occupation:string;
}

const users:User[] = [
    {
        name:'Mike',
        age:25,
        occupation:'Software Dev'
    },
    {
        name:'Angela',
        age:32,
        occupation:'Software Engineer'
    },
    {
        name:'Paul',
        age:45,
        occupation:'Senior Manager'
    }
];

function logUser(user:User) {
    console.log(`-${user.name}, ${user.age}`);
}

console.log('User Info');
users.forEach(logUser);


interface Person {
    displayData();
}

class LumonEmployee implements Person {
    displayData() {
        console.log("Interface demo");
    }
}

let emp = new LumonEmployee();
emp.displayData();