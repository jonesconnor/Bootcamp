using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Builder;
using Microsoft.IdentityModel.Tokens;
using PatientService.Middleware;
using PatientService.Model;
using PatientService.Service;
using Steeltoe.Discovery.Client;
using System.Text;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
builder.Services.AddControllers();

builder.Services.AddDiscoveryClient();
builder.Services.Configure<PatientDbDatabaseSettings>(
        builder.Configuration.GetSection("PatientDbDatabase")
    );
builder.Services.AddSingleton<IPatientsService, PatientsService>();

builder.Services.AddCors(options =>
{
    options.AddPolicy("MyPolicy",
        policy =>
        {
            policy.WithOrigins("http://localhost:4200")
            .AllowAnyMethod()
            .AllowAnyHeader()
            .WithMethods("PUT", "DELETE", "GET", "POST");
        });
});

builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme).AddJwtBearer(options => 
{
    options.SaveToken = true;
    options.TokenValidationParameters = new TokenValidationParameters 
    { 
        ValidateIssuer = true, 
        ValidateAudience = true, 
        ValidateLifetime = true, 
        ValidateIssuerSigningKey = true,
        RoleClaimType = "Role",
        ValidIssuer = builder.Configuration["Jwt:Issuer"], 
        ValidAudience = builder.Configuration["Jwt:Audience"], 
        IssuerSigningKey = new SymmetricSecurityKey(Encoding.ASCII.GetBytes(builder.Configuration["Jwt:Key"]!)) 
    }; 
}); 
builder.Services.AddAuthorization();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseMiddleware<CorsMiddleWare>();
app.UseCors("MyPolicy");
app.UseAuthentication();
app.UseAuthorization();

//app.UseDiscoveryClient();
app.MapControllers();


app.Run();

