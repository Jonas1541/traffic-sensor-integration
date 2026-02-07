package traffic.sensor.integration.impl;

import traffic.sensor.integration.contract.TrafficSensor;
import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.nio.file.Path;
import java.util.Objects;

public class PanamaTrafficSensor implements TrafficSensor {

    private final MethodHandle getSpeedHandle;

    public PanamaTrafficSensor() {
        try {
            // 1. Localizar o arquivo .so no classpath (resources)
            String libPath = Objects.requireNonNull(
                getClass().getClassLoader().getResource("libtraffic.so")
            ).getPath();

            // 2. Configurar o Linker e Lookup
            Linker linker = Linker.nativeLinker();
            SymbolLookup fileLookup = SymbolLookup.libraryLookup(Path.of(libPath), Arena.global());

            // 3. Buscar o endereço da função (uma única vez)
            MemorySegment functionAddress = fileLookup.find("get_vehicle_speed")
                    .orElseThrow(() -> new RuntimeException("Função nativa não encontrada"));

            // 4. Definir assinatura (int -> int)
            FunctionDescriptor descriptor = FunctionDescriptor.of(
                    ValueLayout.JAVA_INT, 
                    ValueLayout.JAVA_INT
            );

            // 5. Criar o Handle
            this.getSpeedHandle = linker.downcallHandle(functionAddress, descriptor);

        } catch (Exception e) {
            throw new RuntimeException("Falha ao inicializar driver Panama", e);
        }
    }

    @Override
    public int readSpeed(int sensorId) throws Exception {
        try {
            // invokeExact lança Throwable (pode ser Error ou Exception)
            return (int) getSpeedHandle.invokeExact(sensorId);
        } catch (Throwable t) {
            // Tratamento Sênior:
            // Se for uma Exception (verificada ou runtime), repassa conforme o contrato da interface.
            if (t instanceof Exception) {
                throw (Exception) t;
            }
            // Se for um Error (ex: OutOfMemoryError, StackOverflow), 
            // empacota numa RuntimeException porque Errors não devem ser engolidos,
            // mas a interface não permite lançar Throwable puro.
            throw new RuntimeException("Erro crítico nativo no Panama", t);
        }
    }

    @Override
    public String getTechnologyName() {
        return "Project Panama (FFM API - Java 25)";
    }
}