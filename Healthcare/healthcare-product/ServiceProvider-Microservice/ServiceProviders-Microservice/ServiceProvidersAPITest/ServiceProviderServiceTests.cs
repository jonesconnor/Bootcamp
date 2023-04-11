using Microsoft.Extensions.Options;
using MongoDB.Bson;
using MongoDB.Driver;
using Moq;
using NUnit.Framework;
using ServiceProvidersAPI.Models;
using ServiceProvidersAPI.Services;
using System.Reflection;

namespace ServiceProvidersAPITest
{
    [TestFixture]
    public class ServiceProviderServiceTests
    {
        private IMongoCollection<ServiceProvider> _mockCollection;
        private ServiceProviderService _service;
        private string providerId1, providerId2;

        [OneTimeSetUp]
        public void Setup()
        {
            var dbSettings = new DbSettings
            {
                ConnectionString = "mongodb://localhost:27017",
                DatabaseName = "HealthcareDB",
                CollectionName = "TestsCollection"
            };
            
            IOptions<DbSettings> options = Options.Create(dbSettings);
            _service = new ServiceProviderService(options);

            var client = new MongoClient(dbSettings.ConnectionString);
            var database = client.GetDatabase(dbSettings.DatabaseName);
            _mockCollection = database.GetCollection<ServiceProvider>(dbSettings.CollectionName);
            providerId1 = ObjectId.GenerateNewId().ToString();
            providerId2 = ObjectId.GenerateNewId().ToString();
        }


        // CreateAsync()
        [Test, Order(1)]
        public async Task CreateAsyncInsertsServiceProvider()
        {
            // Arrange
            var serviceProvider1 = new ServiceProvider
            {
                Id = providerId1,
                FirstName = "John",
                LastName = "Smith",
                Email = "j.smith@test.com",
                PhoneNumber = "1111111111"
            };
            var serviceProvider2 = new ServiceProvider
            {
                Id = providerId2,
                FirstName = "James",
                LastName = "Vaughan",
                Email = "j.vaughan@test.com",
                PhoneNumber = "2222222222",
                Bookings = new List<TimeSlot>()
                {
                    new TimeSlot { BookingId="1", BookingDate = DateTime.Parse("2022/04/01 08:00:00"), Duration=60 },
                    new TimeSlot { BookingId="2", BookingDate = DateTime.Parse("2022/04/01 09:30:00"), Duration=60 },
                    new TimeSlot { BookingId="3", BookingDate = DateTime.Parse("2022/04/02 10:00:00"), Duration=120 }
                },
                Notifications = new List<string>() { "Patient 1 has checked in", "Patient 2 has cancelled appointment" }
            };

            // Act
            await _service.CreateAsync(serviceProvider1);
            await _service.CreateAsync(serviceProvider2);

            var newProvider = await _mockCollection.Find(x => x.Id == providerId1).FirstOrDefaultAsync();

            // Assert
            Assert.That(newProvider.Id, Is.EqualTo(providerId1));
            Assert.That(newProvider.FirstName, Is.EqualTo("John"));
        }

        // GetAllAsync()
        [Test, Order(2)]
        public async Task GetAllShouldReturnAllProviders()
        {
            // Act
            var existingProviders = await _service.GetAllAsync();

            // Assert
            Assert.That(existingProviders.Count, Is.EqualTo(2));
        }

        // GetByIdAsync()
        [Test, Order(3)]
        public async Task GetByIdShouldReturnAProvider()
        {
            // Act
            var existingProvider = await _service.GetByIdAsync(providerId1);

            // Assert
            Assert.That(existingProvider.Id, Is.EqualTo(providerId1));
            Assert.That(existingProvider.FirstName, Is.EqualTo("John"));
        }

        // GetAllByFilterAsync()
        [Test, Order(4)]
        public async Task GetAllByFilterShouldReturnProvidersThatMatchTheFilter()
        {
            // Act
            var existingProviders = await _service.GetAllByFilterAsync(x => x.FirstName == "James");

            // Assert
            Assert.That(existingProviders.Count, Is.EqualTo(1));
            Assert.That(existingProviders[0].FirstName, Is.EqualTo("James"));
        }

        // UpdateAsync()
        [Test, Order(5)]
        public async Task UpdateShouldModifyTheProvider()
        {
            ServiceProvider provider = new() 
            {
                Id = providerId1,
                FirstName = "Sam",
                LastName = "Jackson",
                Email = "s.jackson@test.com",
                PhoneNumber = "1111111111"
            };

            // Act
            await _service.UpdateAsync(providerId1, provider);

            var existingProvider = await _service.GetByIdAsync(providerId1);

            // Assert
            Assert.That(existingProvider.FirstName, Is.EqualTo("Sam"));
            Assert.That(existingProvider.LastName, Is.EqualTo("Jackson"));
        }

        // RemoveAsync()
        [Test, Order(6)]
        public async Task RemoveShouldDeleteProvider()
        {
            var provider = new ServiceProvider
            {
                Id = providerId1,
                FirstName = "John",
                LastName = "Smith",
                Email = "j.smith@test.com",
                PhoneNumber = "1111111111"
            };

            // Act
            await _service.RemoveAsync(providerId1);

            var existingProviders = await _service.GetAllAsync();

            // Assert
            Assert.That(existingProviders, Does.Not.Contain(provider));
        }

        // GetBookingsByIdAsync()
        [Test, Order(7)]
        public async Task GetBookingsByIdShouldReturnBookingsForProvider()
        { 
            // Act
            var bookings = await _service.GetBookingsByIdAsync(providerId2);

            // Assert
            Assert.That(bookings, Is.Not.Empty);
            Assert.That(bookings.Count, Is.EqualTo(3));
        }

        // GetBookingsByIdAndDateAsync()
        [Test, Order(8)]
        public async Task GetBookingsByIdAndDateShouldReturnBookingsForProviderOnGivenDate()
        {
            // Act
            var bookings = await _service.GetBookingsByIdAndDateAsync(providerId2, DateTime.Parse("2022/04/01"));

            // Assert
            Assert.That(bookings, Is.Not.Empty);
            Assert.That(bookings.Count, Is.EqualTo(2));
        }

        // GetNotificationsByIdAsync()
        [Test, Order(9)]
        public async Task GetNotificationsByIdShouldReturnNotificationsForProvider()
        {
            // Act
            var notifications = await _service.GetNotificationsByIdAsync(providerId2);

            // Assert
            Assert.That(notifications, Is.Not.Empty);
            Assert.That(notifications.Count, Is.EqualTo(2));
        }

        // IsValidEmail
        [Test, Order(10)]
        public void IsValidEmailShouldReturnTrueIfEmailIsValid()
        {
            // Act
            bool result = _service.IsValidEmail("testemail@example.com");

            // Assert
            Assert.That(result, Is.True);
        }

        [Test, Order(11)]
        public void IsValidEmailShouldReturnFalseIfEmailIsInValid()
        {
            // Act
            bool result = _service.IsValidEmail("abc");

            // Assert
            Assert.That(result, Is.False);
        }

        // IsValidPhoneNumber
        [Test, Order(12)]
        public void IsValidPhoneNumberShouldReturnTrueIfPhoneNoIsValid()
        {
            // Act
            bool result = _service.IsValidPhoneNumber("7801212345");

            // Assert
            Assert.That(result, Is.True);
        }

        [Test, Order(13)]
        public void IsValidPhoneNumberShouldReturnFalseIfPhoneNoIsInValid()
        {
            // Act
            bool result = _service.IsValidPhoneNumber("123");

            // Assert
            Assert.That(result, Is.False);
        }

        // Remove the test data after all tests are completed
        [OneTimeTearDown]
        public void DisposeTestsCollection()
        {
            _mockCollection.DeleteMany(_ => true);
        }
    }
}
