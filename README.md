# Traffic Sensor Integration POC 🚦

Prova de Conceito (POC) desenvolvida para demonstrar estratégias de integração de alta performance entre **Java (High Level)** e **Drivers de Hardware (Low Level/C)** em sistemas embarcados de monitoramento de tráfego.

Este projeto simula a leitura de um sensor de velocidade (radar) e compara diferentes abordagens de FFI (Foreign Function Interface).

## 🚀 Tecnologias e Arquitetura

* **Linguagem Principal:** Java 25 (OpenJDK)
* **Hardware Simulado:** C (GCC Compiler)
* **Build System:** Gradle (Kotlin DSL) com orquestração de Makefile nativo.
* **Integração Nativa (FFI):**
    * **JNA (Java Native Access):** Abordagem legada/estável.
    * **Project Panama (FFM API):** Abordagem moderna (Java 22+) para baixa latência e segurança de memória.

### Destaques da Implementação
* **Design Pattern Strategy:** O sistema utiliza a interface `TrafficSensor` para permitir a troca dinâmica de drivers (JNA ou Panama) sem afetar a regra de negócio.
* **Automação de Build:** O Gradle gerencia o ciclo de vida completo: compila o código C (`.so`), linka as bibliotecas e executa a aplicação Java.
* **Segurança de Memória:** Uso de `Arena` (Project Panama) para gerenciamento seguro de memória off-heap.

## 📋 Pré-requisitos

O projeto foi desenvolvido e validado em ambiente **Linux (Fedora)**.

* **Java 25** (ou superior) instalado e configurado no PATH.
* **GCC** e **Make** (para compilação do driver simulado).
    * *Fedora:* `sudo dnf install gcc make`

## 🛠️ Como Executar

Não é necessário compilar o código C manualmente. O script do Gradle detecta mudanças no código nativo e recompila automaticamente via Makefile.

No terminal, na raiz do projeto:

```bash
./gradlew run