using comp3000apifinal.Data;
using comp3000apifinal.Interfaces;
using comp3000apifinal.Repository;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Options;
using MySqlConnector;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
builder.Services.AddScoped<iRecordRepository, RecordRepository>();
builder.Services.AddDbContext<DataContext>(options =>
{
    var connection = new MySqlConnection(builder.Configuration.GetConnectionString("RecordDB"));
    var serverVersion = new MySqlServerVersion(new Version(10, 4, 27));
    options.UseMySql(connection, serverVersion);

});

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseAuthorization();

app.MapControllers();

app.Run();
