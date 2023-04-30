using comp3000apifinal.Data;
using comp3000apifinal.Interfaces;
using comp3000apifinal.Models;

namespace comp3000apifinal.Repository
{
    public class RecordRepository : iRecordRepository
    {
        private readonly DataContext _context;
        public RecordRepository(DataContext context) 
        {
           this._context = context;
        }

        public ICollection<Record> GetRecords() 
        {
            return _context.imagerecordtable.OrderBy(p => p.recordID).ToList();
        }
    }
}
