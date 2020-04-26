import com.jiangxb.netty.io.s02.TankMsg;
import com.jiangxb.netty.io.s02.TankMsgDecoder;
import com.jiangxb.netty.io.s02.TankMsgEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TankMsgCodecTest {

    @Test
    public void testTankMsgEncoder(){
        TankMsg msg = new TankMsg(1, 1);
        EmbeddedChannel channel = new EmbeddedChannel(new TankMsgEncoder());

        channel.writeOutbound(msg);

        ByteBuf buf = channel.readOutbound(); // 编码器将对象转为ByteBuf
        int x = buf.readInt();
        int y = buf.readInt();

        Assertions.assertTrue(x == 1 && y == 1);

        buf.release();
    }

    @Test
    public void testTankMsgEncoder2(){
        ByteBuf buf = Unpooled.buffer();
        TankMsg msg = new TankMsg(10, 10);
        buf.writeInt(msg.x);
        buf.writeInt(msg.y);

        EmbeddedChannel channel = new EmbeddedChannel(new TankMsgEncoder(), new TankMsgDecoder());
        channel.writeInbound(buf.duplicate());

        TankMsg tankMsg = channel.readInbound();

        Assertions.assertTrue(tankMsg.x == 10 && tankMsg.y == 10);

    }

}
