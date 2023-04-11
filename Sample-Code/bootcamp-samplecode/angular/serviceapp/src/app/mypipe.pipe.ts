import { Pipe, PipeTransform } from '@angular/core';
import { Employee } from './model/emp';

@Pipe({
  name: 'mypipe'
})
export class MypipePipe implements PipeTransform {

  transform(employees : Employee[],stext : any ): any {

     if(stext==null)
     return employees;

     return employees.filter( empobj=>{
        return empobj.empName?.toLowerCase().includes(stext.toLowerCase()) ||
        empobj.role?.toLowerCase().includes(stext.toLowerCase())

     })
    
  }

}
