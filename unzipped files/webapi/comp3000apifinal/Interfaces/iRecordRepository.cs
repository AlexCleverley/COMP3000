using comp3000apifinal.Models;

namespace comp3000apifinal.Interfaces
{
    public interface iRecordRepository
    {
        ICollection<Record> GetRecords();
    }
}
