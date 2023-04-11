import { Prescription } from "./prescription.model";

export class Patient
{
    id:string = "";
    firstName:string = "";
    lastName:string = "";
    email:string = "";
    phn:string = "";
    phoneNumber:string = "";
    phaemacyPhoneNumber:string = "";
    address:string = "";
    municipality:string = "";
    postalCode:string = "";
    prescriptions:Prescription[] = [];
    notifications:string[] = [];
}