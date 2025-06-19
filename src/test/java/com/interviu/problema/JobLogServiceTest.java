import com.interviu.problema.model.JobDTO;
import com.interviu.problema.services.JobLogService;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JobLogServiceTest {

    private final JobLogService service = new JobLogService();

    @Test
    public void testParseLogsAndDuration() throws ParseException {
        List<String> logs = Arrays.asList(
                "11:00:00,task 001,START,1001",
                "11:06:00,task 001,END,1001",    // 6 minute
                "12:00:00,task 002,START,1002",
                "12:15:00,task 002,END,1002",    // 15 minute
                "13:00:00,task 003,START,1003",
                "13:03:00,task 003,END,1003"     // 3 minute
        );

        List<JobDTO> jobs = service.parseLogs(logs);

        // Verif sa avem 3 joburi parse-uite
        assertEquals(3, jobs.size());

        // Filtrare jobs > 5 min
        long over5min = jobs.stream()
                .filter(j -> j.getDurationMinutes() >= 5)
                .count();
        assertEquals(2, over5min);

        // Filtrare job > 10 min
        long over10min = jobs.stream()
                .filter(j -> j.getDurationMinutes() >= 10)
                .count();
        assertEquals(1, over10min);
    }
}
