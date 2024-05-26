package eu.smoothservices.smoothcloud.api.misc;

import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyUtils {

//    public static IoHandlerFactory getFactory() {
//        return Epoll.isAvailable() ?  : NioHandler.newFactory();
//    }
//
//    public static ServerChannelFactory<? extends ServerChannel> getChannelFactory() {
//        return Epoll.isAvailable() ? EpollServerSocketChannel::new : NioServerSocketChannel::new;
//    }
//
//    public static ChannelFactory<? extends Channel> getSocketChannelFactory() {
//        return Epoll.isAvailable() ? EpollSocketChannel::new : NioSocketChannel::new;
//    }
}
