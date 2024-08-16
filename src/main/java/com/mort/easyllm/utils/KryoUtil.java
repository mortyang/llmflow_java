package com.mort.easyllm.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class KryoUtil {


    private static final ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.setReferences(true);
        kryo.setRegistrationRequired(false);
        ((DefaultInstantiatorStrategy) kryo.getInstantiatorStrategy())
                .setFallbackInstantiatorStrategy(new StdInstantiatorStrategy());
        return kryo;
    });

    public static Kryo getKryo() {
        return kryoThreadLocal.get();
    }

    public static void removeKryo() {
        kryoThreadLocal.remove();
    }


    public static byte[] serialize(Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        KryoUtil.getKryo().writeObject(output, object);
        output.flush();
        return baos.toByteArray();

    }

    public static <T> T deserialize(byte[] data, Class<T> clazz) {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        Input input = new Input(bais);
        return KryoUtil.getKryo().readObject(input, clazz);

    }

}
