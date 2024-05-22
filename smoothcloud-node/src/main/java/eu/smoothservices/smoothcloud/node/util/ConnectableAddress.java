package eu.smoothservices.smoothcloud.node.util;

import lombok.Getter;

@Getter
public record ConnectableAddress(String hostName, int port) {

}
