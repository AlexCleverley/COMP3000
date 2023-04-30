using comp3000apifinal.Models;
using Microsoft.EntityFrameworkCore;

namespace comp3000apifinal.Data
{
    public class DataContext : DbContext
    {
        public DataContext(DbContextOptions<DataContext> options) : base(options)
        {
          
        }

        public DbSet<Record> imagerecordtable { get; set; }
    }
}
