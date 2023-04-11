using System.Linq.Expressions;

namespace ServiceProvidersAPI.Services
{
    public interface IServiceProviderService
    {
        Task CreateAsync(Models.ServiceProvider obj);
        Task<List<Models.ServiceProvider>> GetAllAsync();
        Task<Models.ServiceProvider> GetByIdAsync(string id);
        Task<List<Models.ServiceProvider>> GetAllByFilterAsync(Expression<Func<Models.ServiceProvider, bool>>? filter = null);
        Task<List<Models.TimeSlot>> GetBookingsByIdAsync(string id);
        Task<List<Models.TimeSlot>> GetBookingsByIdAndDateAsync(string id, DateTime date);
        Task<List<string>> GetNotificationsByIdAsync(string id);
        Task RemoveAsync(string id);
        Task UpdateAsync(string id, Models.ServiceProvider obj);        
    }
}
