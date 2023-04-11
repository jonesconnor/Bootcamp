class Student
{
    sid=0;
    sname="";
    score=0;

    constructor(psid,psname,pscore) {
        this.sid=psid;
        this.sname=psname
        this.score=pscore
    }
    displayDetails()
    {
        console.log( " name " + this.sname  + " scroe " + this.score)
    }
}

let stud1=new Student(10,"Varun",599)
let stud2=new Student(20,"Sofia",800)
let stud3=new Student(30,"Dan",700)
stud1.displayDetails()

let students=[stud1,stud2,stud3]


students.forEach ( studobj => studobj.displayDetails() )


students.filter( studobj=> studobj.score>600 )
        .map( studobj=>studobj.sname)
        .forEach(n => console.log(n))


