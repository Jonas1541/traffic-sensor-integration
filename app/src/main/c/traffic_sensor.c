// traffic_sensor.c
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// Simula a leitura de velocidade de um veículo em km/h
// Retorna um valor aleatório entre 40 e 120
int get_vehicle_speed(int sensor_id) {
    // Inicializa a semente randômica (apenas para simulação)
    srand(time(NULL) + sensor_id);
    int speed = (rand() % 80) + 40; 
    
    printf("[C Hardware] Sensor ID %d detectou velocidade: %d km/h\n", sensor_id, speed);
    return speed;
}