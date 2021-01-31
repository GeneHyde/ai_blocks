package com.github.genehyde.ai_blocks.network.datasync;

import com.github.genehyde.ai_blocks.entity.SimpleProgrammableEntityData;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.IDataSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class ModDataSerializers extends DataSerializers {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final IDataSerializer<SimpleProgrammableEntityData> GOAL_DATA =
            new IDataSerializer<SimpleProgrammableEntityData>() {
                public void write(PacketBuffer buf, SimpleProgrammableEntityData value) {
                    try {
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(bos);
                        oos.writeObject(value);
                        oos.flush();
                        byte[] data = bos.toByteArray();
                        buf.writeBytes(data);
                    } catch (IOException ex) {
                        LOGGER.error(ex);
                    }
                }

                public SimpleProgrammableEntityData read(PacketBuffer buf) {
                    try {
                        ByteArrayInputStream bis = new ByteArrayInputStream(buf.readByteArray());
                        ObjectInputStream ois = new ObjectInputStream(bis);
                        return (SimpleProgrammableEntityData) ois.readObject();
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                    return null;
                }

                public SimpleProgrammableEntityData copyValue(SimpleProgrammableEntityData value) {
                    return value;
                }
            };

    static {
        registerSerializer(GOAL_DATA);
    }
}
