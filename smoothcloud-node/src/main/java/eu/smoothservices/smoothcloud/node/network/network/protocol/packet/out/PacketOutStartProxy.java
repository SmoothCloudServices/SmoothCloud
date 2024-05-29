package eu.smoothservices.smoothcloud.node.network.network.protocol.packet.out;

import eu.smoothservices.smoothcloud.api.group.ICloudGroup;
import eu.smoothservices.smoothcloud.node.network.protocol.packet.Packet;
import eu.smoothservices.smoothcloud.node.network.protocol.packet.PacketRC;
import eu.smoothservices.smoothcloud.node.util.document.Document;
import eu.smoothservices.smoothcloud.node.util.proxy.ProxyProcessMeta;
import eu.smoothservices.smoothcloud.node.util.service.plugin.ServiceInstallablePlugin;

import java.util.Collection;

public class PacketOutStartProxy extends Packet {

    public PacketOutStartProxy(ICloudGroup proxyGroup,
                               int memory,
                               String[] parameters,
                               String url,
                               Collection<ServiceInstallablePlugin> plugins,
                               Document document) {
        super(PacketRC.SERVER_HANDLE + 6, new Document("group", proxyGroup.getName()).append("memory", memory).append("url", url).append(
                "processParameters",
                parameters).append("plugins", plugins).append("properties", document));
    }

    public PacketOutStartProxy(String wrapper,
                               ICloudGroup proxyGroup,
                               int memory,
                               String[] parameters,
                               String url,
                               Collection<ServiceInstallablePlugin> plugins,
                               Document document) {
        super(PacketRC.SERVER_HANDLE + 6, new Document("group", proxyGroup.getName()).append("wrapper", wrapper)
                .append("memory", memory)
                .append("url", url)
                .append("processParameters", parameters)
                .append("plugins", plugins)
                .append("properties", document));
    }

    public PacketOutStartProxy(ProxyProcessMeta proxyProcessMeta, boolean b) {
        super(PacketRC.SERVER_HANDLE + 6, new Document("group", proxyProcessMeta.getGroup().getName()).append("memory", proxyProcessMeta.getMemory()).append("url", proxyProcessMeta.getUrl()).append(
                "processParameters",
                proxyProcessMeta.getProcessParameters()).append("plugins", proxyProcessMeta.getDownloadablePlugins()).append("properties", proxyProcessMeta.getProperties()));
    }

    public PacketOutStartProxy(ProxyProcessMeta proxyProcessMeta) {
        super(PacketRC.SERVER_HANDLE + 6, new Document("group", proxyProcessMeta.getGroup().getName()).append("memory", proxyProcessMeta.getMemory()).append("url", proxyProcessMeta.getUrl()).append(
                "processParameters",
                proxyProcessMeta.getProcessParameters()).append("plugins", proxyProcessMeta.getDownloadablePlugins()).append("properties", proxyProcessMeta.getProperties()));
    }
}
