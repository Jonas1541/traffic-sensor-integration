# Makefile dentro da pasta 'app'
CC = gcc
# Flags importantes: -fPIC para posição independente, -shared para biblioteca dinâmica
CFLAGS = -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux
# O alvo é a pasta resources do próprio módulo app
TARGET_DIR = src/main/resources
LIB_NAME = libtraffic.so

all: $(TARGET_DIR)/$(LIB_NAME)

$(TARGET_DIR)/$(LIB_NAME): src/main/c/traffic_sensor.c
	mkdir -p $(TARGET_DIR)
	$(CC) -shared -o $@ $< $(CFLAGS)
	@echo "--- Hardware (Lib C) Compilado com Sucesso ---"

clean:
	rm -f $(TARGET_DIR)/$(LIB_NAME)