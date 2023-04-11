import {describe, expect, test} from '@jest/globals';
import { Employee } from '../source/employee';

describe('check the data',()=>{

test ('check for dev role',()=>{

    let emp=new Employee();
    expect(emp.displayDetails('Developer',6)).toBe("Congrats. You have been nominated for Team Lead role" )
})


})