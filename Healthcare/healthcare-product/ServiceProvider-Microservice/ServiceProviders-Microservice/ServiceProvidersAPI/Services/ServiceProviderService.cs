using Microsoft.Extensions.Options;
using MongoDB.Bson;
using MongoDB.Driver;
using ServiceProvidersAPI.Common.Exceptions;
using ServiceProvidersAPI.Models;
using System.Linq.Expressions;
using System.Text.RegularExpressions;

namespace ServiceProvidersAPI.Services
{
    public class ServiceProviderService : IServiceProviderService
    {
        #region Constructor DI

        private readonly IMongoCollection<Models.ServiceProvider> _collection;
        public ServiceProviderService(IOptions<DbSettings> dbSettings)
        {
            var mongoClient = new MongoClient(dbSettings.Value.ConnectionString);
            var mongoDatabase = mongoClient.GetDatabase(dbSettings.Value.DatabaseName);
            _collection = mongoDatabase.GetCollection<Models.ServiceProvider>(dbSettings.Value.CollectionName);
        }

        #endregion

        // Create a new ServiceProvider
        public async Task CreateAsync(Models.ServiceProvider obj)
        {
            if (!IsValidEmail(obj.Email))
            {
                throw new InvalidEmailException("Invalid email format");
            }
            if (!IsValidPhoneNumber(obj.PhoneNumber))
            {
                throw new InvalidPhoneNumberException("Invalid phone number format");
            }
            await _collection.InsertOneAsync(obj);
        }

        // Get the list of all Service Providers
        public async Task<List<Models.ServiceProvider>> GetAllAsync() =>
            await _collection.Find(_ => true).ToListAsync();

        // Get the list of all Service Providers that match a given 'filter'
        public async Task<List<Models.ServiceProvider>> GetAllByFilterAsync(Expression<Func<Models.ServiceProvider, bool>>? filter = null)
        {
            if (filter == null)
            {
                return await _collection.Find(_ => true).ToListAsync();
            }
            return await _collection.Find(filter).ToListAsync();
        }

        // Gets all bookings for the Service Provider whose Id is given
        public async Task<List<TimeSlot>> GetBookingsByIdAsync(string id)
        {
            if (!ObjectId.TryParse(id, out _))
            {
                throw new InvalidObjectIdException("The id was not a valid ObjectId");
            }
            var existingProvider = await GetByIdAsync(id);

            if (existingProvider.Bookings != null)
            {
                return existingProvider.Bookings;
            }
            return new List<TimeSlot>();
        }

        // Gets all bookings for the Service Provider whose Id is given for a specific date
        public async Task<List<TimeSlot>> GetBookingsByIdAndDateAsync(string id, DateTime date)
        {
            if (!ObjectId.TryParse(id, out _))
            {
                throw new InvalidObjectIdException("The id was not a valid ObjectId");
            }
            var existingProvider = await GetByIdAsync(id);

            if (existingProvider.Bookings != null)
            {
                return existingProvider.Bookings.Where(b => b.BookingDate.Date == date).ToList();
            }
            return new List<TimeSlot>();
        }

        // Get the ServiceProvider with the given Id
        public async Task<Models.ServiceProvider> GetByIdAsync(string id)
        {
            if (!ObjectId.TryParse(id, out _))
            {
                throw new InvalidObjectIdException("The id provided was not a valid ObjectId");
            }
            return await _collection.Find(x => x.Id == id).FirstOrDefaultAsync();
        }

        // Get all notifications for the ServiceProvider with the given Id
        public async Task<List<string>> GetNotificationsByIdAsync(string id)
        {
            if (!ObjectId.TryParse(id, out _))
            {
                throw new InvalidObjectIdException("The id was not a valid ObjectId");
            }

            var existingProvider = await GetByIdAsync(id);

            if (existingProvider.Notifications != null)
            {
                return existingProvider.Notifications;
            }
            return new List<string>();
        }

        // Remove the ServiceProvider with the given Id
        public async Task RemoveAsync(string id)
        {
            if (!ObjectId.TryParse(id, out _))
            {
                throw new InvalidObjectIdException("The id was not a valid ObjectId");
            }
            await _collection.DeleteOneAsync(x => x.Id == id);
        }
              

        // Replace the ServiceProvider with the given Id to the ServiceProvider passed as argument
        public async Task UpdateAsync(string id, Models.ServiceProvider obj)
        {
            if (!ObjectId.TryParse(id, out _))
            {
                throw new InvalidObjectIdException("The id was not a valid ObjectId");
            }
            if (!IsValidEmail(obj.Email))
            {
                throw new InvalidEmailException("Invalid email format");
            }
            if (!IsValidPhoneNumber(obj.PhoneNumber))
            {
                throw new InvalidPhoneNumberException("Invalid phone number format");
            }
            await _collection.ReplaceOneAsync(x => x.Id == id, obj);
        }

        // Checks if a given string is a valid email address
        public bool IsValidEmail(string email)
        {
            string regex = @"^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$";
            var match = Regex.Match(email, regex, RegexOptions.IgnoreCase);
            return match.Success;
        }

        // Checks if a given string is a valid phone number (Ex: 7801231111)
        public bool IsValidPhoneNumber(string phoneNo)
        {
            string regex = @"^[1-9]\d{9}$";
            var match = Regex.Match(phoneNo, regex);
            return match.Success;
        }
    }
}
