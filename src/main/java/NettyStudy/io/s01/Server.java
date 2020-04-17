package NettyStudy.io.s01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

public class Server {

    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static void main(String[] args) {
        // 只负责连接
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 处理客户端连接上的客户事件
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            ChannelFuture future = bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new ServerChildHandler());
                        }
                    })
                    .bind(8888)
                    .sync();

            System.out.println("server started!");

            future.channel()
                    // 当close被调用时才会执行
                    .closeFuture()
                    .sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }
}

class ServerChildHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Server.clients.add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = null;
        try {
            buf = (ByteBuf) msg;

            byte[] bytes = new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(), bytes);
            String s = new String(bytes);
            System.out.println(s);

            if (s.equals("_close_")) {
                System.out.println("客户端要求退出");
                Server.clients.remove(ctx.channel());
                ctx.close();
            } else {
                Server.clients.writeAndFlush(msg);
            }

        } finally {
            // ReferenceCountUtil.release(buf); writeAndFlush会自动释放不用再手动释放
            // System.out.println("buf被引用数"+buf.refCnt());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}