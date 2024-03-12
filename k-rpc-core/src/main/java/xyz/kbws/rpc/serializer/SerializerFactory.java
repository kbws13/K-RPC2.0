package xyz.kbws.rpc.serializer;

import xyz.kbws.rpc.spi.SpiLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kbws
 * @date 2024/3/11
 * @description: 序列化工厂（用于获取序列化器对象）
 */
public class SerializerFactory {

    /**
     * 序列化映射
     */
    //private static final Map<String, Serializer> KEY_SERIALIZER_MAP = new HashMap<String, Serializer>() {{
    //    put(SerializerKeys.JDK, new JdkSerializer());
    //    put(SerializerKeys.JSON, new JsonSerializer());
    //    put(SerializerKeys.KRYO, new KryoSerializer());
    //    put(SerializerKeys.HESSIAN, new HessianSerializer());
    //}};
    static {
        SpiLoader.loadAll();
    }

    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULE_SERIALIZER = new JdkSerializer();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static Serializer getInstance(String key) {
        return SpiLoader.getInstance(Serializer.class, key);
    }
}
