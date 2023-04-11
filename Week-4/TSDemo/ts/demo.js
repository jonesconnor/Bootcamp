// console.log("Hello from typescript");
var username = 'apple';
// arrays
var arr1 = ["hello", "world"];
var arr2 = ["hello", 1, "world"];
// tuples
var valTuple = ['connor', 123];
console.log(valTuple[0]);
// for each that returns index
for (var val in arr1) {
    console.log(arr1[val]);
}
// normal for each
arr2.forEach(function (element) {
    console.log(element);
});
//functions
function addResult(num1, num2) {
    return num1 + num2;
}
console.log(addResult(1, 1));
//lambdas
var sum = function (num1, num2) {
    return num1 + num2;
};
console.log(sum(2, 2));
// classes
var Student = /** @class */ (function () {
    function Student(stdId, stdName, stdEmail) {
        this.stdId = stdId;
        this.stdName = stdName;
        this.stdEmail = stdEmail;
    }
    Student.prototype.getId = function () {
        return this.stdId;
    };
    return Student;
}());
var stu1 = new Student(1, "Connor", "connor@uvic.ca");
console.log(stu1.getId());
var users = [
    {
        name: 'Mike',
        age: 25,
        occupation: 'Software Dev'
    },
    {
        name: 'Angela',
        age: 32,
        occupation: 'Software Engineer'
    },
    {
        name: 'Paul',
        age: 45,
        occupation: 'Senior Manager'
    }
];
function logUser(user) {
    console.log("-".concat(user.name, ", ").concat(user.age));
}
console.log('User Info');
users.forEach(logUser);
var LumonEmployee = /** @class */ (function () {
    function LumonEmployee() {
    }
    LumonEmployee.prototype.displayData = function () {
        console.log("Interface demo");
    };
    return LumonEmployee;
}());
var emp = new LumonEmployee();
emp.displayData();
