package traffic.sensor.integration;

import traffic.sensor.integration.contract.TrafficSensor;
import traffic.sensor.integration.impl.JnaTrafficSensor;
import traffic.sensor.integration.impl.PanamaTrafficSensor;

import java.util.List;

public class App {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println(" VELSIS POC: Integração Java <-> Sistemas Embarcados");
        System.out.println("==================================================\n");

        // Polimorfismo na veia: Lista de estratégias diferentes
        List<TrafficSensor> sensors = List.of(
            new JnaTrafficSensor(),
            new PanamaTrafficSensor()
        );

        int sensorId = 2024;

        // Itera sobre as implementações sem saber qual é qual
        for (TrafficSensor sensor : sensors) {
            testSensorDriver(sensor, sensorId);
        }
    }

    // Método genérico que aceita qualquer implementação de sensor
    private static void testSensorDriver(TrafficSensor sensor, int id) {
        System.out.println(">>> Testando Driver: " + sensor.getTechnologyName());
        try {
            long start = System.nanoTime();
            
            int speed = sensor.readSpeed(id);
            
            long duration = System.nanoTime() - start;
            System.out.printf("[SUCESSO] Velocidade: %d km/h (Latência: %d ns)\n\n", speed, duration);
            
        } catch (Exception e) {
            System.err.println("[ERRO] Falha no driver: " + e.getMessage());
            e.printStackTrace();
        }
    }
}