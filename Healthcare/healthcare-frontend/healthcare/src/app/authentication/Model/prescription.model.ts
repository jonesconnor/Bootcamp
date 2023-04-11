export class Prescription
{
    id:string = "";
    dosage:string = "";
    refills:number = 0;
    doctorID:string = "";
    expiration:Date = new Date();
}