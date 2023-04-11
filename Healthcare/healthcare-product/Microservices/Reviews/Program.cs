using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using ProductService.Middleware;
using Reviews.Models;
using Reviews.Repository;
using Steeltoe.Discovery.Client;

var builder = WebApplication.CreateBuilder(args);
IConfiguration configuration = new ConfigurationBuilder()
     .AddJsonFile("appsettings.json")
     .Build();

//builder.Services.AddDbContext<ReviewsContext>(options =>
//    options.UseSqlServer(builder.Configuration.GetConnectionString("ReviewsContext") ?? throw new InvalidOperationException("Connection string 'ReviewsContext' not found.")));
builder.Services.AddScoped<IReviewRepository, ReviewRepository>();
// Add services to the container.
builder.Services.AddDiscoveryClient(builder.Configuration);
builder.Services.AddControllers();

// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddCors(options =>
{
    options.AddPolicy("ReviewPolicy",
        policy =>
        {
            policy.WithOrigins("https://localhost:7271/api/Reviews")
            .AllowAnyMethod()
            .AllowAnyHeader()
            .WithMethods("PUT", "DELETE", "GET", "POST");
        });
});

var connectionstring = configuration["mysqlconnection:connectionString"];
builder.Services.AddDbContext<ReviewsContext>
(option => option.UseMySql(
    connectionstring,
    ServerVersion.Parse("8.0.32-mysql"),
    option =>
    {
        option.EnableRetryOnFailure(
            maxRetryCount: 5,
            maxRetryDelay: System.TimeSpan.FromSeconds(5),
            errorNumbersToAdd: null);
    }
    ));

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseMiddleware<ReviewMiddleware>();
app.UseCors("ReviewPolicy");

app.UseHttpsRedirection();

//app.UseAuthorization();

app.MapControllers();


app.Run();
