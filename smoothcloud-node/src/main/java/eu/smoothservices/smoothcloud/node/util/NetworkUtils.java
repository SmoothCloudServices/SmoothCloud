package eu.smoothservices.smoothcloud.node.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import eu.smoothservices.smoothcloud.node.SmoothCloudNode;
import eu.smoothservices.smoothcloud.client.lib.SmoothNetwork;
import eu.smoothservices.smoothcloud.client.protocol.codec.ProtocolInDecoder;
import eu.smoothservices.smoothcloud.client.protocol.codec.ProtocolLengthDeserializer;
import eu.smoothservices.smoothcloud.client.protocol.codec.ProtocolLengthSerializer;
import eu.smoothservices.smoothcloud.client.protocol.codec.ProtocolOutEncoder;
import eu.smoothservices.smoothcloud.node.util.document.Document;
import eu.smoothservices.smoothcloud.node.util.interfaces.Acceptable;
import eu.smoothservices.smoothcloud.node.util.interfaces.Catcher;
import io.netty.channel.AbstractChannel;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;

import com.sun.management.OperatingSystemMXBean;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.kqueue.KQueue;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueServerSocketChannel;
import io.netty.channel.kqueue.KQueueSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadLocalRandom;

public class NetworkUtils {

    public static final boolean EPOLL = Epoll.isAvailable();
    public static final Gson GSON = new Gson();
    public static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    public static final String DEV_PROPERTY = "_SMOOTHCLOUD_DEV_SERVICE_UNIQUEID_", EMPTY_STRING = "", SPACE_STRING = " ", SLASH_STRING = "/";
    private static final char[] VALUES = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private NetworkUtils() {}

    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception ex) {
            return "127.0.0.1";
        }
    }

    public static double cpuUsage() {
        return ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getCpuLoad() * 100;
    }

    public static double internalCpuUsage() {
        return ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getProcessCpuLoad() * 100;
    }

    public static long systemMemory() {
        return ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
    }

    public static OperatingSystemMXBean system() {
        return ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean());
    }

    public static TypeToken<SmoothCloudNode> smoothCloud() {
        return new TypeToken<>() {};
    }

    public static Class<? extends AbstractChannel> socketChannel() {
        return EPOLL ? EpollServerSocketChannel.class : KQueue.isAvailable() ? KQueueSocketChannel.class : NioSocketChannel.class;
    }

    public static Class<? extends ServerSocketChannel> serverSocketChannel() {
        return EPOLL ? EpollServerSocketChannel.class : KQueue.isAvailable() ? KQueueServerSocketChannel.class : NioServerSocketChannel.class;
    }

    public static EventLoopGroup eventLoopGroup() {
        return eventLoopGroup(Runtime.getRuntime().availableProcessors());
    }

    public static EventLoopGroup eventLoopGroup(int threads) {
        return EPOLL ? new EpollEventLoopGroup(threads) : KQueue.isAvailable() ? new KQueueEventLoopGroup(threads) : new NioEventLoopGroup(threads);
    }

    public static EventLoopGroup eventLoopGroup(ThreadFactory threadFactory) {
        return eventLoopGroup(0, threadFactory);
    }

    public static EventLoopGroup eventLoopGroup(int threads, ThreadFactory threadFactory) {
        return EPOLL ? new EpollEventLoopGroup(threads, threadFactory) : KQueue.isAvailable() ? new KQueueEventLoopGroup(threads, threadFactory) : new NioEventLoopGroup(threads, threadFactory);
    }

    public static <T> void addAll(Collection<T> key, Collection<T> value) {
        if(key == null || value == null) return;
        key.addAll(value);
    }

    public static <T, V> void addAll(Map<T, V> key, Map<T, V> value) {
        for(T key_ : value.keySet()) {
            key.put(key_, value.get(key_));
        }
    }

    public static void addAll(Document key, Document value) {
        for (String keys : value.keys()) {
            key.append(keys, value.get(keys));
        }
    }

    public static <T, V> void addAll(Map<T, V> map, List<V> list, Catcher<T, V> catcher) {
        for (V ke : list) {
            map.put(catcher.doCatch(ke), ke);
        }
    }

    public static <T, V> void addAll(Map<T, V> key, Map<T, V> value, Acceptable<V> handle) {
        for (T key_ : value.keySet()) {
            if (handle.isAccepted(value.get(key_))) {
                key.put(key_, value.get(key_));
            }
        }
    }

    public static Channel initChannel(Channel channel) {
        channel.pipeline().addLast(new ProtocolLengthDeserializer(),
                new ProtocolInDecoder(),
                new ProtocolLengthSerializer(),
                new ProtocolOutEncoder());
        return channel;
    }

    public static boolean checkIsNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static ConnectableAddress fromString(String input) {
        String[] x = input.split(":");
        return new ConnectableAddress(x[0], Integer.parseInt(x[1]));
    }

    public static String randomString(int size) {
        StringBuilder builder = new StringBuilder();
        for (short i = 0; i < size; i++) {
            builder.append(VALUES[RANDOM.nextInt(VALUES.length)]);
        }
        return builder.substring(0);
    }

    public static void writeWrapperKey() {
        Random random = new Random();

        Path path = Paths.get("WRP_KY.cnd");
        if(!Files.exists(path)) {
            StringBuilder builder = new StringBuilder();
            for(short i = 0; i < 4096; i++) {
                builder.append(VALUES[random.nextInt(VALUES.length)]);
            }

            try {
                Files.createFile(path);
                try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Files.newOutputStream(path), StandardCharsets.UTF_8)) {
                    outputStreamWriter.write(builder.substring(0) + "\n");
                    outputStreamWriter.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String readWrapperKey() {
        Path path = Paths.get("WRP_KY.cnd");
        if(!Files.exists(path)) {
            return null;
        }

        StringBuilder builder = new StringBuilder();

        try {
            for(String string : Files.readAllLines(path, StandardCharsets.UTF_8)) {
                builder.append(string);
            }
            return builder.substring(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.substring(0);
    }

    public static void sleepUninterruptedly(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static <K, V>ConcurrentHashMap<K, V> newConcurrentHashMap() {
        return new ConcurrentHashMap<>(0);
    }

    public static SmoothNetwork newSmoothNetwork() {
        SmoothNetwork network = new SmoothNetwork();
        return network;
    }
}
