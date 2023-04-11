using Microsoft.AspNetCore.Mvc;
using MongoDB.Bson;
using MongoDB.Driver;
using MongoDB.Driver.Linq;
using Moq;
using ServiceProvidersAPI.Common.Exceptions;
using ServiceProvidersAPI.Controllers;
using ServiceProvidersAPI.Models;
using ServiceProvidersAPI.Services;
using System.Linq.Expressions;

namespace ServiceProvidersAPITest
{
    [TestFixture]
    public class ServiceProvidersControllerTests
    {
        List<ServiceProvider> serviceProvidersList = new List<ServiceProvider>
            {
                new ServiceProvider
                {
                    Id = "6419e97b8353f8de604e0b08",
                    FirstName = "Sam",
                    LastName = "Smith",
                    Location = "111 St NW, Edmonton, AB",
                    Email = "s.smith@gmail.com",
                    PhoneNumber = "7801231111",
                    Specialization = "caretaker",
                    Fee = 200,
                    AverageRating = 4.6,
                    Bookings = new List<TimeSlot>
                        {
                            new TimeSlot { BookingId = "1", BookingDate = DateTime.Parse("2023/03/24 09:30:00"), Duration = 30 },
                            new TimeSlot { BookingId = "2", BookingDate = DateTime.Parse("2023/03/24 10:00:00"), Duration = 30 },
                            new TimeSlot { BookingId = "3", BookingDate = DateTime.Parse("2023/03/24 10:30:00"), Duration = 60 }
                        },
                    Notifications = new List<string>()
                },
                new ServiceProvider
                {
                    Id = "6419d3e7e02e6c36fb7b1784",
                    FirstName = "Will",
                    LastName = "Smith",
                    Location = "222 St NW, Edmonton, AB",
                    Email = "w.smith@gmail.com",
                    PhoneNumber = "7801232222",
                    Specialization = "surgery",
                    Fee = 300,
                    AverageRating = 4,
                    Bookings = new List<TimeSlot>
                        {
                            new TimeSlot { BookingId = "1", BookingDate = DateTime.Parse("2023/03/24 09:00:00"), Duration = 120 },
                            new TimeSlot { BookingId = "2", BookingDate = DateTime.Parse("2023/03/25 10:00:00"), Duration = 30 },
                            new TimeSlot { BookingId = "3", BookingDate = DateTime.Parse("2023/03/25 10:30:00"), Duration = 60 }
                        },
                    Notifications = new List<string>()
                },
                new ServiceProvider
                {
                    Id = "6419d32ce02e6c36fb7b1783",
                    FirstName = "Will",
                    LastName = "Pharell",
                    Location = "123 St NW, Calgary, AB",
                    Email = "w.pharell@gmail.com",
                    PhoneNumber = "5871231111",
                    Specialization = "pediatrics",
                    Fee = 250,
                    AverageRating = 4.8,
                    Bookings = new List<TimeSlot>
                        {
                            new TimeSlot { BookingId = "1", BookingDate = DateTime.Parse("2023/03/26 09:00:00"), Duration = 120 },
                            new TimeSlot { BookingId = "2", BookingDate = DateTime.Parse("2023/03/26 11:30:00"), Duration = 60 },
                            new TimeSlot { BookingId = "3", BookingDate = DateTime.Parse("2023/03/27 10:00:00"), Duration = 30 },
                            new TimeSlot { BookingId = "4", BookingDate = DateTime.Parse("2023/03/27 10:30:00"), Duration = 60 }
                        },
                    Notifications = new List<string>()
                }
            };

        #region Positive Test Cases

        [Test(Description = "GetAllDoctors() should return list of doctors")]
        public async Task GetAllDoctorsReturnsListOfDoctors()
        {
            Mock<IServiceProviderService> serviceProviderMock = new();
            ServiceProvidersController controller = new(serviceProviderMock.Object);

            serviceProviderMock
                .Setup(x => x.GetAllByFilterAsync(It.IsAny<Expression<Func<ServiceProvider, bool>>>()))
                .ReturnsAsync(serviceProvidersList.Where(x => x.Specialization!.ToLower() != "caretaker").ToList());

            var result = await controller.GetAllDoctors();

            Assert.That(result, Is.InstanceOf<OkObjectResult>());
            var okResult = result as OkObjectResult;
            var serviceProviders = okResult!.Value as List<ServiceProvider>;
            Assert.That(serviceProviders, Has.Count.EqualTo(2));
            Assert.That(serviceProviders.Any(x => x.Specialization!.ToLower() == "caretaker"), Is.False);
        }


        [Test(Description = "GetAllCareTakers() should return list of caretakers")]
        public async Task GetAllCareTakersReturnsListOfCaretakers()
        {
            Mock<IServiceProviderService> serviceProviderMock = new();
            ServiceProvidersController controller = new(serviceProviderMock.Object);

            serviceProviderMock
                .Setup(x => x.GetAllByFilterAsync(It.IsAny<Expression<Func<ServiceProvider, bool>>>()))
                .ReturnsAsync(serviceProvidersList.Where(x => x.Specialization!.ToLower() == "caretaker").ToList());

            var result = await controller.GetAllCareTakers();

            Assert.That(result, Is.InstanceOf<OkObjectResult>());
            var okResult = result as OkObjectResult;
            var serviceProviders = okResult!.Value as List<ServiceProvider>;
            Assert.That(serviceProviders, Has.Count.EqualTo(1));
            Assert.That(serviceProviders.Any(x => x.Specialization!.ToLower() != "caretaker"), Is.False);
        }

        [Test(Description = "GetByIdShouldReturnTheProvider() should return the caretaker with the provided id")]
        public async Task GetByIdShouldReturnTheProvider()
        {
            string providerId = "6419d32ce02e6c36fb7b1783";
            Mock<IServiceProviderService> serviceProviderMock = new();
            ServiceProvidersController controller = new(serviceProviderMock.Object);

            serviceProviderMock
                .Setup(x => x.GetByIdAsync(It.IsAny<string>()))
                .ReturnsAsync(serviceProvidersList.FirstOrDefault(x => x.Id == providerId)!);


            var result = await controller.GetById(providerId);

            Assert.That(result, Is.InstanceOf<OkObjectResult>());
            var okResult = result as OkObjectResult;

            var provider = okResult!.Value as ServiceProvider;
            Assert.That(provider, Is.Not.Null);
            Assert.Multiple(() =>
            {
                Assert.That(provider.FirstName, Is.EqualTo("Will"));
                Assert.That(provider.Specialization, Is.EqualTo("pediatrics"));
            });
        }

        [Test(Description = "GetDoctorsByLocation() should return the doctors from a location")]
        public async Task GetDoctorsByLocationShouldReturnListOfDoctors()
        {
            string location = "Edmonton";
            Mock<IServiceProviderService> serviceProviderMock = new();
            ServiceProvidersController controller = new(serviceProviderMock.Object);

            serviceProviderMock
                .Setup(x => x.GetAllByFilterAsync(It.IsAny<Expression<Func<ServiceProvider, bool>>>()))
                .ReturnsAsync(serviceProvidersList
                    .Where(x => x.Specialization!.ToLower() != "caretaker" && x.Location.Contains(location)).ToList());

            var result = await controller.GetDoctorsByLocation(location);

            Assert.That(result, Is.InstanceOf<OkObjectResult>());
            var okResult = result as OkObjectResult;
            var serviceProviders = okResult!.Value as List<ServiceProvider>;
            Assert.That(serviceProviders, Has.Count.EqualTo(1));
            Assert.That(serviceProviders.Any(x => x.Specialization!.ToLower() == "caretaker"), Is.False);
        }

        [Test(Description = "GetCareTakersByLocation() should return the doctors from a location")]
        public async Task GetCareTakersByLocationShouldReturnListOfCareTakers()
        {
            string location1 = "Edmonton";
            Mock<IServiceProviderService> serviceProviderMock = new();
            ServiceProvidersController controller = new(serviceProviderMock.Object);

            serviceProviderMock
                .Setup(x => x.GetAllByFilterAsync(It.IsAny<Expression<Func<ServiceProvider, bool>>>()))
                .ReturnsAsync(serviceProvidersList
                    .Where(x => x.Specialization!.ToLower() == "caretaker" && x.Location.Contains(location1)).ToList());

            var result = await controller.GetCareTakersByLocation(location1);

            Assert.That(result, Is.InstanceOf<OkObjectResult>());
            var okResult = result as OkObjectResult;
            var serviceProviders = okResult!.Value as List<ServiceProvider>;
            Assert.That(serviceProviders, Has.Count.EqualTo(1));
            Assert.That(serviceProviders.Any(x => x.Specialization!.ToLower() != "caretaker"), Is.False);

            string location2 = "Calgary";

            serviceProviderMock
                .Setup(x => x.GetAllByFilterAsync(It.IsAny<Expression<Func<ServiceProvider, bool>>>()))
                .ReturnsAsync(serviceProvidersList
                    .Where(x => x.Specialization!.ToLower() == "caretaker" && x.Location.Contains(location2)).ToList());

            result = await controller.GetCareTakersByLocation(location2);

            Assert.That(result, Is.InstanceOf<OkObjectResult>());
            okResult = result as OkObjectResult;
            serviceProviders = okResult!.Value as List<ServiceProvider>;
            Assert.That(serviceProviders, Has.Count.EqualTo(0));
            Assert.That(serviceProviders.Any(x => x.Specialization!.ToLower() != "caretaker"), Is.False);
        }

        [Test(Description = "GetBookingsById() should return the bookings for a provider")]
        public async Task GetBookingsByIdShouldReturnListOfBookingsForProvider()
        {
            string providerId = "6419d32ce02e6c36fb7b1783";
            Mock<IServiceProviderService> serviceProviderMock = new();
            ServiceProvidersController controller = new(serviceProviderMock.Object);

            serviceProviderMock
                .Setup(x => x.GetByIdAsync(It.IsAny<string>()))
                .ReturnsAsync(serviceProvidersList.FirstOrDefault(x => x.Id == providerId)!);

            serviceProviderMock
                .Setup(x => x.GetBookingsByIdAsync(It.IsAny<string>()))
                .ReturnsAsync(serviceProvidersList.FirstOrDefault(x => x.Id == providerId)!.Bookings!);

            var result = await controller.GetBookingsById(providerId);

            Assert.That(result, Is.InstanceOf<OkObjectResult>());
            var okResult = result as OkObjectResult;

            var bookings = okResult!.Value as List<TimeSlot>;
            Assert.That(bookings, Is.Not.Null);
            Assert.That(bookings, Has.Count.EqualTo(4));
            Assert.Multiple(() =>
            {
                Assert.That(bookings[0].BookingDate, Is.EqualTo(DateTime.Parse("2023/03/26 09:00:00")));
                Assert.That(bookings[bookings.Count - 1].BookingDate, Is.EqualTo(DateTime.Parse("2023/03/27 10:30:00")));
            });
        }

        [Test(Description = "GetBookingsByIdAndDate() should return all bookings for a provider for the given date")]
        public async Task GetBookingsByIdAndDateReturnsBookingsForProviderOnADate()
        {
            string providerId = "6419d32ce02e6c36fb7b1783";
            DateTime date = DateTime.Parse("2023/03/26");

            Mock<IServiceProviderService> serviceProviderMock = new();
            ServiceProvidersController controller = new(serviceProviderMock.Object);

            serviceProviderMock
                .Setup(x => x.GetByIdAsync(It.IsAny<string>()))
                .ReturnsAsync(serviceProvidersList.FirstOrDefault(x => x.Id == providerId)!);

            serviceProviderMock
                .Setup(x => x.GetBookingsByIdAndDateAsync(It.IsAny<string>(), It.IsAny<DateTime>()))
                .ReturnsAsync(serviceProvidersList
                .FirstOrDefault(x => x.Id == providerId)!.Bookings!
                .Where(x => x.BookingDate.Date == date)!
                .ToList());

            var result = await controller.GetBookingsByIdAndDate(providerId, date);

            Assert.That(result, Is.InstanceOf<OkObjectResult>());
            var okResult = result as OkObjectResult;

            var bookings = okResult!.Value as List<TimeSlot>;
            Assert.That(bookings, Is.Not.Null);
            Assert.That(bookings, Has.Count.EqualTo(2));
            Assert.Multiple(() =>
            {
                Assert.That(bookings[0].BookingDate, Is.EqualTo(DateTime.Parse("2023/03/26 09:00:00")));
                Assert.That(bookings[bookings.Count - 1].BookingDate, Is.EqualTo(DateTime.Parse("2023/03/26 11:30:00")));
            });
        }

        [Test(Description = "Create() should create a new Service Provider")]
        public async Task CreateShouldCreateANewServiceProvider()
        {
            ObjectId newObjectId = ObjectId.GenerateNewId();
            var newProvider = new ServiceProvider
            {
                Id = newObjectId.ToString(),
                FirstName = "Tom",
                LastName = "Hardy",
                Location = "786 St NW, Leduc, AB",
                Email = "tom.hardy@yahoo.com",
                PhoneNumber = "7801237777",
                Specialization = "dermatology",
                Fee = 200,
                AverageRating = 4.9,
            };

            Mock<IServiceProviderService> serviceProviderMock = new();
            ServiceProvidersController controller = new(serviceProviderMock.Object);

            var result = await controller.Create(newProvider);

            Assert.That(result, Is.InstanceOf<OkObjectResult>());
            var okResult = result as OkObjectResult;
            Assert.That(okResult!.Value, Is.EqualTo(newProvider));
        }

        [Test(Description = "Update() should update a Service Provider")]
        public async Task UpdateShouldUpdateServiceProvider()
        {
            string providerId = "6419d32ce02e6c36fb7b1783";
            ServiceProvider provider = new ServiceProvider
            {
                Id = providerId,
                FirstName = "Jimmy",
                LastName = "Fallon",
                Location = "123 St NW, Calgary, AB",
                Email = "j.fallon@gmail.com",
                PhoneNumber = "5871232222",
                Specialization = "pediatrics",
                Fee = 250,
                AverageRating = 5.0,
                Bookings = new List<TimeSlot>(),
                Notifications = new List<string>()
            };


            Mock<IServiceProviderService> serviceProviderMock = new();
            ServiceProvidersController controller = new(serviceProviderMock.Object);

            serviceProviderMock
                .Setup(x => x.GetByIdAsync(It.IsAny<string>()))
                .ReturnsAsync(serviceProvidersList.FirstOrDefault(x => x.Id == providerId)!);

            var result = await controller.Update(providerId, provider);

            Assert.That(result, Is.InstanceOf<NoContentResult>());
            serviceProviderMock.Verify(s => s.UpdateAsync(providerId, provider), Times.Once);
        }

        [Test(Description = "Delete() should delete a Service Provider")]
        public async Task DeleteShouldDeleteAServiceProvider()
        {
            string providerId = "6419d32ce02e6c36fb7b1783";
            Mock<IServiceProviderService> serviceProviderMock = new();
            ServiceProvidersController controller = new(serviceProviderMock.Object);

            serviceProviderMock
                .Setup(x => x.GetByIdAsync(It.IsAny<string>()))
                .ReturnsAsync(serviceProvidersList.FirstOrDefault(x => x.Id == providerId)!);

            var result = await controller.Delete(providerId);

            Assert.That(result, Is.InstanceOf<NoContentResult>());
            serviceProviderMock.Verify(s => s.RemoveAsync(providerId), Times.Once);
        }

        // AddNewBookingForProvider
        [Test(Description = "AddNewBookingForProvider() should create a new booking for Service Provider")]
        public async Task AddNewBookingForProviderShouldCreateANewBooking()
        {
            string providerId = "1";
            TimeSlot newBooking = new();
            ServiceProvidersAPI.Models.ServiceProvider serviceProvider = new() { Bookings = new List<TimeSlot>() };

            Mock<IServiceProviderService> serviceProviderMock = new();
            ServiceProvidersController controller = new(serviceProviderMock.Object);

            serviceProviderMock
                .Setup(x => x.GetByIdAsync(It.IsAny<string>()))
                .ReturnsAsync(serviceProvider);

            var result = await controller.AddNewBookingForProvider(providerId, newBooking);


            Assert.That(result, Is.InstanceOf<NoContentResult>());
            Assert.That(serviceProvider.Bookings, Has.Member(newBooking));
            serviceProviderMock.Verify(s => s.UpdateAsync(providerId, serviceProvider), Times.Once);
        }

        // UpdateBookingForProvider
        [Test]
        public async Task UpdateBookingForProviderShouldUpdateBookings()
        {
            string providerId = "1";
            string bookingId = "1";
            TimeSlot updatedBooking = new TimeSlot { BookingDate = DateTime.Now.AddDays(1), Duration = 30 };
            ServiceProvider serviceProvider = new ServiceProvider 
            { 
                Id = providerId, 
                Bookings = new List<TimeSlot> { new TimeSlot { BookingId = bookingId } } 
            };

            Mock<IServiceProviderService> serviceProviderMock = new();
            ServiceProvidersController controller = new(serviceProviderMock.Object);

            serviceProviderMock
                .Setup(x => x.GetByIdAsync(It.IsAny<string>()))
                .ReturnsAsync(serviceProvider);

            var result = await controller.UpdateBookingForProvider(providerId, bookingId, updatedBooking) as NoContentResult;

            // Assert
            serviceProviderMock.Verify(s => s.GetByIdAsync(providerId), Times.Once);
            serviceProviderMock.Verify(s => s.UpdateAsync(providerId, serviceProvider), Times.Once);
            Assert.That(result, Is.TypeOf<NoContentResult>());
        }

        // DeleteBookingForProvider
        [Test]
        public async Task DeleteBookingForProviderShouldDeleteBooking()
        {
            string providerId = "1";
            
            ServiceProvider serviceProvider = new ServiceProvider
            {
                Id = providerId,
                Bookings = new List<TimeSlot>
                {
                    new TimeSlot {BookingId="1", BookingDate = DateTime.Now.AddDays(1), Duration = 30 },
                    new TimeSlot {BookingId="2", BookingDate = DateTime.Now.AddDays(2), Duration = 60 }
                }
            };

            Mock<IServiceProviderService> serviceProviderMock = new();
            ServiceProvidersController controller = new(serviceProviderMock.Object);

            serviceProviderMock
                .Setup(x => x.GetByIdAsync(It.IsAny<string>()))
                .ReturnsAsync(serviceProvider);

            var result = await controller.DeleteBookingForProvider(providerId, "1") as NoContentResult;

            // Assert
            serviceProviderMock.Verify(s => s.GetByIdAsync(providerId), Times.Once);
            serviceProviderMock.Verify(s => s.UpdateAsync(providerId, serviceProvider), Times.Once);
            Assert.That(result, Is.TypeOf<NoContentResult>());
            Assert.That(serviceProvider.Bookings, Has.Count.EqualTo(1));
        }

        [Test]
        public async Task GetNotificationsForProviderByIdShouldReturnNotifications()
        {
            string providerId = "1";
            List<string> notifications = new();
            ServiceProvider serviceProvider = new() { Notifications = notifications };

            Mock<IServiceProviderService> serviceProviderMock = new();
            ServiceProvidersController controller = new(serviceProviderMock.Object);

            serviceProviderMock
                .Setup(x => x.GetByIdAsync(It.IsAny<string>()))
                .ReturnsAsync(serviceProvider);

            serviceProviderMock
                .Setup(s => s.GetNotificationsByIdAsync(providerId)).ReturnsAsync(notifications);

            var result = await controller.GetNotificationsForProviderById(providerId);

            Assert.That(result, Is.TypeOf<OkObjectResult>());
            var okResult = (OkObjectResult)result;
            Assert.That(okResult.Value, Is.EqualTo(notifications));
            serviceProviderMock.Verify(s => s.GetByIdAsync(providerId), Times.Once);
            serviceProviderMock.Verify(s => s.GetNotificationsByIdAsync(providerId), Times.Once);
        }

        
        [Test]
        public async Task AddNewNotificationForProviderShouldCreateNotification()
        {
            string providerId = "1";
            string newNotification = "notification 5";
            var notifications = new List<string> { "notification 1", "notification 2", "notification 3", "notification 4" };
            ServiceProvider existingServiceProvider = new() { Notifications = notifications };

            Mock<IServiceProviderService> serviceProviderMock = new();
            ServiceProvidersController controller = new(serviceProviderMock.Object);

            serviceProviderMock.Setup(x => x.GetByIdAsync(providerId)).ReturnsAsync(existingServiceProvider);
            serviceProviderMock.Setup(x => x.UpdateAsync(providerId, existingServiceProvider)).Returns(Task.CompletedTask);

            var result = await controller.AddNewNotificationForProvider(providerId, newNotification);

            Assert.That(existingServiceProvider.Notifications.Count, Is.EqualTo(5));
            Assert.That(existingServiceProvider.Notifications, Does.Contain("notification 5"));
        }

        
        [Test]
        public async Task DeleteNotificationForProviderShouldDeleteANotification()
        {
            string providerId = "1";
            string removingNotification = "notification 3";
            var notifications = new List<string> { "notification 1", "notification 2", "notification 3", "notification 4" };
            ServiceProvider existingServiceProvider = new() { Notifications = notifications };

            Mock<IServiceProviderService> serviceProviderMock = new();
            ServiceProvidersController controller = new(serviceProviderMock.Object);

            serviceProviderMock.Setup(x => x.GetByIdAsync(providerId)).ReturnsAsync(existingServiceProvider);
            serviceProviderMock.Setup(x => x.UpdateAsync(providerId, existingServiceProvider)).Returns(Task.CompletedTask);

            var result = await controller.DeleteNotificationForProvider(providerId, removingNotification);


            Assert.That(existingServiceProvider.Notifications, Has.Count.EqualTo(3));
            Assert.That(existingServiceProvider.Notifications, Does.Not.Contain(removingNotification));
        }      

        #endregion

        #region Negative Test Cases
        [Test]
        public async Task CreateReturnsBadRequestForInvalidEmail()
        {
            ObjectId newObjectId = ObjectId.GenerateNewId();
            var newProvider = new ServiceProvider
            {
                Id = newObjectId.ToString(),
                FirstName = "Tom",
                LastName = "Hardy",
                Location = "786 St NW, Leduc, AB",
                Email = "tom",
                PhoneNumber = "7801237777",
                Specialization = "dermatology",
                Fee = 200,
                AverageRating = 4.9,
            };

            Mock<IServiceProviderService> serviceProviderMock = new();
            ServiceProvidersController controller = new(serviceProviderMock.Object);

            serviceProviderMock
                .Setup(x => x.CreateAsync(It.IsAny<ServiceProvider>()))
                .ThrowsAsync(new InvalidEmailException("Invalid email format"));


            var result = await controller.Create(newProvider);

            Assert.That(result, Is.InstanceOf<BadRequestObjectResult>());
            var badRequestResult = result as BadRequestObjectResult;
            Assert.That(badRequestResult!.Value, Is.EqualTo("Invalid email format"));
        }

        [Test]
        public async Task CreateReturnsBadRequestForInvalidPhoneNo()
        {
            ObjectId newObjectId = ObjectId.GenerateNewId();
            var newProvider = new ServiceProvider
            {
                Id = newObjectId.ToString(),
                FirstName = "Tom",
                LastName = "Hardy",
                Location = "786 St NW, Leduc, AB",
                Email = "tom.hardy@yahoo.com",
                PhoneNumber = "abc",
                Specialization = "dermatology",
                Fee = 200,
                AverageRating = 4.9,
            };

            Mock<IServiceProviderService> serviceProviderMock = new();
            ServiceProvidersController controller = new(serviceProviderMock.Object);

            serviceProviderMock
                .Setup(x => x.CreateAsync(It.IsAny<ServiceProvider>()))
                .ThrowsAsync(new InvalidEmailException("Invalid phone number format"));


            var result = await controller.Create(newProvider);

            Assert.That(result, Is.InstanceOf<BadRequestObjectResult>());
            var badRequestResult = result as BadRequestObjectResult;
            Assert.That(badRequestResult!.Value, Is.EqualTo("Invalid phone number format"));
        }
        #endregion
    }
}