package eu.smoothcloudservices.smoothcloud.node.util.interfaces;

import io.netty.channel.Channel;

public interface ChannelUser {

    Channel getChannel();

    void setChannel(Channel channel);

}
