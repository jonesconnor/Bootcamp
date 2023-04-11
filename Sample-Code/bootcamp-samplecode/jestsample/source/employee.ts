
export class Employee{

     public displayDetails(role:string,year:number) : string
     {
if (role==='Developer' && year>=3)
return "Congrats. You have been nominated for Team Lead role";
else if (role==="Trainee" && year>=2)
return "Congratulations. You have been nomiated for Developer role";
else
return "All the best";

     }

}