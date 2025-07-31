package homework.oop1;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

public class OOP1HomeworkTest {
    @Test
    public void query_after_all() {
        // given
        AppRunner appRunner = new AppRunner();

        // when
        List<String> queryResult = appRunner.queryAll();

        // then
        assertThat(queryResult.size()).isEqualTo(3);
        assertThat(queryResult.get(0)).contains("金普卡");
        assertThat(queryResult.get(0)).contains("9499.5");
        assertThat(queryResult.get(0)).contains("501");
        assertThat(queryResult.get(1)).contains("商务卡");
        assertThat(queryResult.get(1)).contains("9111.12");
        assertThat(queryResult.get(2)).contains("黑金无限卡");
        assertThat(queryResult.get(2)).contains("2147382536");
        assertThat(queryResult.get(2)).contains("10111100");
        assertThat(queryResult.get(2)).contains("机场贵宾厅");
        assertThat(queryResult.get(2)).contains("境内接送机");
    }
}