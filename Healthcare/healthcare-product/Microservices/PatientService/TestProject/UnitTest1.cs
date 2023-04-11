using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.DependencyInjection;
using Moq;
using NUnit.Framework;
using PatientService.Controllers;
using PatientService.Model;
using PatientService.Service;
using System.Collections.ObjectModel;
using System.Linq.Expressions;
//using Xunit;

namespace TestProject
{
    public class Tests
    {

        [Test(Description = "Get() should return list of patiens")] 
        public async Task GetAllDoctorsReturnsListOfDoctors() 
        { 
            Mock<IPatientsService> patientMock = new();
            PatientsController controller = new (patientMock.Object);
            patientMock.Setup(x => x.GetAllAsync()).ReturnsAsync(patients); 

            var result = await controller.Get(); 
            Assert.That(result, Is.InstanceOf<OkObjectResult>());
            var okResult = result as OkObjectResult; 
            var actual = okResult!.Value as List<Patient>;
            Assert.That(actual, Has.Count.EqualTo(2));
            Assert.That(actual[0].Id, Is.EqualTo("641cd11eda2f21bb9ba245re"));
            Assert.That(actual[1].Id, Is.EqualTo("641cd11ed32f21bb9ba245re"));
        }

        [Test(Description = "GetById() should return list of patiens")]
        public async Task GetByIdReturnsObjOfDoctors()
        {
            Mock<IPatientsService> patientMock = new();
            PatientsController controller = new(patientMock.Object);
            string id = "641cd11eda2f21bb9ba245re";
            patientMock.Setup( x => x.GetAllAsyncByFilter(It.IsAny<Expression<Func<Patient, bool>>>())).ReturnsAsync(patients);

            var result = await controller.GetById(id);
            Assert.That(result, Is.InstanceOf<OkObjectResult>());
            var okResult = result as OkObjectResult;
            var actual = okResult!.Value as Patient;
            Assert.That(actual.Id, Is.EqualTo(id));
        }

        //[Test(Description = "Post() should return true")]
        //public async Task PostReturnsTrue()
        //{
        //    Mock<IPatientsService> patientMock = new();
        //    PatientsController controller = new(patientMock.Object);
        //    var newPatient = new Patient
        //    {
        //        Id = "641cdn1eda2f21bb0ba245re",
        //        FirstName = "John",
        //        LastName = "Smith",
        //        Email = "john.smith@gmail.com",
        //        PhoneNumber = "7800000000",
        //        PhaemacyPhoneNumber = "7800000001",
        //        Phn = "32345678",
        //        Municipality = "Edmonton",
        //        Address = "123 45St NW",
        //        PostalCode = "T6W1K9",
        //        Prescriptions = new(){
        //            new Prescription { Id="641cd13fas9f21bc9ba245df", Dosage="3 pills per day", Refills=3, DoctorID=12,Expiration=DateTime.Now},
        //            new Prescription { Id="65acd13fda2f21bb9ba240df", Dosage="4 pills per day", Refills=4, DoctorID=12,Expiration=DateTime.Now},
        //        },
        //        Notifications = new() { "Note1", "Note2" },
        //    };
        //    patientMock.Setup(x => x.CreateAsync(It.IsAny<Patient>())).ReturnsAsync(true);

        //    var result = await controller.GetById(id);
        //    Assert.That(result, Is.InstanceOf<OkObjectResult>());
        //    var okResult = result as OkObjectResult;
        //    var actual = okResult!.Value as Patient;
        //    Assert.That(actual.Id, Is.EqualTo(id));
        //}




        private List<Patient> patients = new List<Patient>{

            new Patient {Id="641cd11eda2f21bb9ba245re", FirstName= "John", LastName="Smith", Email= "john.smith@gmail.com", PhoneNumber="7800000000", PhaemacyPhoneNumber="7800000001", Phn="12345678", Municipality="Edmonton", Address="123 45St NW", PostalCode="T6W1K9", Prescriptions=new(){
                    new Prescription { Id="641cd13fas4f21bb9ba245df", Dosage="3 pills per day", Refills=3, DoctorID=12,Expiration=DateTime.Now},
                    new Prescription { Id="641cd13fda2f21bb9ba240df", Dosage="4 pills per day", Refills=4, DoctorID=12,Expiration=DateTime.Now},
                },
                Notifications=new(){ "Note1", "Note2" },
            },

            new Patient {Id="641cd11ed32f21bb9ba245re", FirstName= "Bruce", LastName="Li", Email= "bruce.li@gmail.com", PhoneNumber="7800000000", PhaemacyPhoneNumber="7800000001", Phn="22345678", Municipality="Calgary", Address="123 45St NW", PostalCode="T5E1K9", Prescriptions=new(){
                    new Prescription { Id="641cd13fda2f21ab9ba245df", Dosage="3 pills per day", Refills=3, DoctorID=12,Expiration=DateTime.Now},
                    new Prescription { Id="641er13fda2f21bb9ba245df", Dosage="4 pills per day", Refills=4, DoctorID=12,Expiration=DateTime.Now},
                },
                Notifications=new(){ "Note1", "Note2" },
            }
        };

        Task<List<Patient>> Convert(List<Patient> patientsList)
        {
            return Task.FromResult(patientsList);
        }
    }
}