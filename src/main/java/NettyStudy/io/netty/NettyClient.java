package NettyStudy.io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

public class NettyClient {
    public static void main(String[] args) {
        // 事件处理的线程池
        EventLoopGroup group = new NioEventLoopGroup(1);
        // 辅助启动类
        Bootstrap bootstrap = new Bootstrap();

        try {
            ChannelFuture future = bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer())
                    // 调用connect后handler才会执行
                    .connect("localhost", 8888);
                    // connect 方法是异步的，如果要确认connect连上再往下执行需要用sync方法
                    //.sync();

            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (!channelFuture.isSuccess()) {
                        // 连接失败
                        System.out.println("not connected!");
                    } else {
                        // 已经连上了
                        System.out.println("connected!");
                    }
                }
            });

            future.sync();
            System.out.println("客户端已连接");

            future.channel()
                    // 当close被调用时才会执行
                    .closeFuture()
                    .sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}

class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new ClientHandler());
    }
}

class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = null;
        try {
            buf = (ByteBuf) msg;

            byte[] bytes = new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(), bytes);
            System.out.println(new String(bytes));

            // ctx.writeAndFlush(msg);
        } finally {
            if (buf != null) {
                // ReferenceCountUtil.release(buf);  writeAndFlush会自动释放不用再手动释放
                // System.out.println("buf被引用数"+buf.refCnt());
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // channel 第一次连上可用，写出一个字符串
        // netty的ByteBuf 直接访问内存 效率高 Direct Memory
        // 直接访问内存 不参与java的垃圾回收
        // 这个buf直接指向操作系统内存，不是jvm的虚拟内存，需要手动释放
        ByteBuf buf = Unpooled.copiedBuffer("[NettyClient]--->hello".getBytes());

        // 该方法会自动释放内存
        ctx.writeAndFlush(buf);
    }
}