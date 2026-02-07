package traffic.sensor.integration.impl;

import com.sun.jna.Library;
import com.sun.jna.Native;
import traffic.sensor.integration.contract.TrafficSensor;

public class JnaTrafficSensor implements TrafficSensor {

    // Ocultamos a interface nativa dentro da classe de implementação
    private interface TrafficLib extends Library {
        // Carrega a libtraffic.so
        TrafficLib INSTANCE = Native.load("traffic", TrafficLib.class);
        int get_vehicle_speed(int sensor_id);
    }

    @Override
    public int readSpeed(int sensorId) {
        // Chamada direta e limpa
        return TrafficLib.INSTANCE.get_vehicle_speed(sensorId);
    }

    @Override
    public String getTechnologyName() {
        return "JNA (Java Native Access)";
    }
}
