package xyz.kbws.rpc.model;

/**
 * @author kbws
 * @date 2024/3/13
 * @description: 服务元信息（注册信息）
 */
public class ServiceMetaInfo {
    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务版本号
     */
    private String serviceVersion = "1.0";

    /**
     * 服务地址
     */
    private String serviceAddress;

    /**
     * 服务分组（暂未实现）
     */
    private String serviceGroup = "default";

    /**
     * 获取服务键名
     */
    public String getServiceKey() {
        // 后续可拓展分组
        //return String.format("$s:$s:$s", serviceName, serviceVersion, serviceGroup);
        return String.format("$s:$s", serviceName, serviceVersion);
    }

    /**
     * 获取服务节点键名
     */
    public String getServiceNodeKey() {
        return String.format("$s/$s", getServiceKey(), serviceAddress);
    }

}
