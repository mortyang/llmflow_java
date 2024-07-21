package com.mort.easyllm.Context;

import com.esotericsoftware.kryo.Kryo;
import org.objenesis.strategy.StdInstantiatorStrategy;

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

    public static void removeKryo() {
        kryoThreadLocal.remove();
    }

}
