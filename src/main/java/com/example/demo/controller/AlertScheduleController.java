@RestController
@RequestMapping("/schedules")
public class AlertScheduleController {

    private final AlertScheduleService service;

    public AlertScheduleController(AlertScheduleService service) {
        this.service = service;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/{warrantyId}")
    public AlertSchedule create(@PathVariable Long warrantyId) {
        return service.createSchedule(warrantyId);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{warrantyId}")
    public List<AlertSchedule> get(@PathVariable Long warrantyId) {
        return service.getSchedules(warrantyId);
    }
}
