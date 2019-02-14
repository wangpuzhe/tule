package tuletest;

import com.kiah.tule.model.TRoomRecord;
import com.kiah.tule.service.RoomRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring/spring.xml"})
public class TRoomRecordTest{
    @Resource
    private RoomRecordService roomRecordService;
    @Test
    public void test(){
        String no = "101";
        //TRoomRecord tRoomRecord = roomRecordService.findRecord(no);
        //System.out.println(tRoomRecord.toString());
    }
}
