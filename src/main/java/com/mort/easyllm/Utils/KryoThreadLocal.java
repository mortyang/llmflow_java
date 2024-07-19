package com.mort.easyllm.Utils;

import com.esotericsoftware.kryo.Kryo;
import com.mort.easyllm.Node.InfoNode.InfoNode;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.util.concurrent.ConcurrentHashMap;

public class KryoThreadLocal {

    private static final ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);
        kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
        return kryo;
    });



    public static Kryo getKryo() {
        return kryoThreadLocal.get();
    }

}
