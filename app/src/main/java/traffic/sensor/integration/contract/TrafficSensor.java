package traffic.sensor.integration.contract;

public interface TrafficSensor {
    /**
     * Lê a velocidade do hardware simulado.
     * @param sensorId O ID do sensor físico.
     * @return A velocidade em km/h.
     * @throws Exception Caso ocorra erro de comunicação nativa.
     */
    int readSpeed(int sensorId) throws Exception;
    
    /**
     * Retorna o nome da tecnologia usada (para logs).
     */
    String getTechnologyName();
}
