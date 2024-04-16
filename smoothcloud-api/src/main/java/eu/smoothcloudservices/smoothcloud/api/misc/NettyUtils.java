package eu.smoothcloudservices.smoothcloud.api.misc;

import io.netty5.channel.*;
import io.netty5.channel.epoll.Epoll;
import io.netty5.channel.epoll.EpollHandler;
import io.netty5.channel.epoll.EpollServerSocketChannel;
import io.netty5.channel.epoll.EpollSocketChannel;
import io.netty5.channel.nio.NioHandler;
import io.netty5.channel.socket.nio.NioServerSocketChannel;
import io.netty5.channel.socket.nio.NioSocketChannel;

public class NettyUtils {

    public static IoHandlerFactory getFactory() {
        return Epoll.isAvailable() ? EpollHandler.newFactory() : NioHandler.newFactory();
    }

    public static ServerChannelFactory<? extends ServerChannel> getChannelFactory() {
        return Epoll.isAvailable() ? EpollServerSocketChannel::new : NioServerSocketChannel::new;
    }

    public static ChannelFactory<? extends Channel> getSocketChannelFactory() {
        return Epoll.isAvailable() ? EpollSocketChannel::new : NioSocketChannel::new;
    }
}
