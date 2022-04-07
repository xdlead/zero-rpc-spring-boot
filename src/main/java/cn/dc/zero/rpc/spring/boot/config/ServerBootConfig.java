package cn.dc.zero.rpc.spring.boot.config;


import cn.dc.zero.rpc.core.config.ServerConfig;
import cn.dc.zero.rpc.core.node.NodeInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerBootConfig {

    @Value("${zero.rpc.server.netty.host:127.0.0.1}")
    private String host;

    @Value("${zero.rpc.server.netty.port:9527}")
    private Integer port;

    @Value("${zero.rpc.server.netty.serialization:jdk}")
    private String serialization;

    @Value("${zero.rpc.server.serverType:netty}")
    private String serverType;

    @Value("${zero.rpc.server.nodeId:zero-node-id}")
    private String nodeId;

    @Value("${zero.rpc.server.nodeIdentify:zero-node-identify}")
    private String nodeIdentify;

    @Value("${zero.rpc.server.nodeGroup:zero-node-group}")
    private String nodeGroup;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeIdentify() {
        return nodeIdentify;
    }

    public void setNodeIdentify(String nodeIdentify) {
        this.nodeIdentify = nodeIdentify;
    }

    public String getNodeGroup() {
        return nodeGroup;
    }

    public void setNodeGroup(String nodeGroup) {
        this.nodeGroup = nodeGroup;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getSerialization() {
        return serialization;
    }

    public void setSerialization(String serialization) {
        this.serialization = serialization;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public ServerConfig buildServerConfig(){
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setHost(host);
        serverConfig.setPort(port);
        serverConfig.setSerializer(serialization);
        serverConfig.setServerType(serverType);

        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo.setNodeId(nodeId);
        nodeInfo.setNodeGroup(nodeGroup);
        nodeInfo.setNodeIdentify(nodeIdentify);
        return serverConfig;
    }
}
