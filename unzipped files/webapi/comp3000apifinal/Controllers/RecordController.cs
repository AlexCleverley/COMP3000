using comp3000apifinal.Interfaces;
using comp3000apifinal.Models;
using Microsoft.AspNetCore.Mvc;

namespace comp3000apifinal.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class RecordController : ControllerBase
    {
        private readonly iRecordRepository _recordRepository;
        public RecordController(iRecordRepository recordRepository)
        {
            _recordRepository = recordRepository;
        }

        [HttpGet]
        [ProducesResponseType(200, Type = typeof(ICollection<Record>))]
        public IActionResult GetRecords()
        {
            var records = _recordRepository.GetRecords();

            if(!ModelState.IsValid)
                return BadRequest(ModelState);

            return Ok(records);
        }
    }
}
