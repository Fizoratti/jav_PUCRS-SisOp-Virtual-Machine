

<h3 align="center">
  <img src="https://img.shields.io/badge/platform-windows%20%7C%20linux%20%7C%20macos-blue" />
  <img src="https://img.shields.io/badge/java-%3E%3D13.0.0-blue" />
  <img src="https://img.shields.io/badge/gradle-6.1.1-blue" />
  <a href="https://gitpod.io/#https://github.com/Fizoratti/jav_PUCRS-SisOp-Virtual-Machine">
    <img src="https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod" />
  </a>
  <p></p>
  <p align="center">PUCRS - Escola Politécnica - 2021/1</p>
  <p align="center">Disciplina: Sistemas Operacionais</p>
  <p align="center">Prof. Fernando Luís Dotti</p>
</h3>

# Máquina Virtual

Nossa máquina virtual tem CPU e Memória.

**Enunciado do trabalho**: Em grupos de até 3 alunos. Construir uma máquina virtual conforme definido no enunciado do trabalho. [Link para o pdf](https://moodle.pucrs.br/pluginfile.php/3524730/mod_folder/content/0/TrabalhoSO2021-1-Fase1.pdf) que está no Moodle com o enunciado da primeira parte do trabalho.


## 📝 To Do
**To Do**
- [ ] Finalizar instruções (opcodes)
- [ ] Finalizar Programas
  - [ ] Avaliar Programa 1
  - [ ] Escrever Programa 2 (Fibonacci)
  - [ ] Escrever Programa 3 (Fatorial) 
  - [ ] Escrever Programa 4 (Bubble sort)
- [ ] Interrupções
- [ ] Traps

**Done**
- [x] Café

## 🔦ㅤPré Requisitos

Antes de começar, você vai precisar ter instalado o [Java](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html) e o [Git](https://git-scm.com) localmente no seu computador. 
Além disto é bom ter um editor para trabalhar com o código como [VSCode](https://code.visualstudio.com/).
Usando a IDE de browser [GitPod](https://gitpod.io/) não é preciso instalar nada localmente no seu computador.

## 🏃ㅤGetting Started

> **Não é preciso ter o gradle instalado para executar o código.**

```bash
# Clone este repositório
$ git clone https://github.com/Fizoratti/jav_PUCRS-SisOp-Virtual-Machine/

# Acesse a pasta do projeto no terminal/cmd
$ cd jav_PUCRS-SisOp-Virtual-Machine

# Execute a aplicação
$ gradle run
```
###### Para executar em ambiente Windows use o comando ```gradlew run```.

## 🌿ㅤBranches

- ```main```: branch para desenvolvimento de features.
- ```stable```: branch com a última build do projeto em que o código que executa sem erros.

## 🚀ㅤFeatures

### OPCODES

Conjunto de instruções.

#### Instruções JUMP

- [x] JMP
- [x] JMPI
- [x] JMPIG
- [x] JMPIL
- [x] JMPIE
- [ ] JMPIM
- [ ] JMPIGM
- [ ] JMPILM
- [ ] JMPIEM

#### Instruções Aritméticas

- [x] ADD
- [x] ADDI
- [x] SUB
- [x] SUBI
- [x] MULT

#### Instruções de Movimentação

- [x] LDI
- [x] STD
- [x] STX
- [x] LDD
- [x] LDX
- [x] SWAP

#### Instruções Genréricas

- [x] STOP
- [x] DATA

## 🛠ㅤTecnologias

As seguintes ferramentas foram usadas na construção do projeto:

- [Gradle](https://gradle.org/install/)
