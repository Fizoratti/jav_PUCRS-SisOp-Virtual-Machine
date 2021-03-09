

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

# Maquina Virtual

Nossa máquina virtual (MV) tem CPU e Memória.

## Trabalho Prático

###### Enunciado do trabalho
Em grupos de até 4 alunos. Construir uma máquina virtual conforme definido neste documento, em linguagem de alto nível de sua escolha. [Link para o pdf](https://moodle.pucrs.br/pluginfile.php/3524730/mod_folder/content/0/TrabalhoSO2021-1-Fase1.pdf) que está no Moodle com o enunciado completo.

###### Primeira parte

##### CPU

 A máquina virtual deve poder executar programas descritos com o conjunto de instruções da CPU (seção 1.1). 
 Como parte do trabalho voce deve construir alguns programas (seção 1.4), veja o programa exemplo. 
 Em cada teste da máquina virtual, um destes programas é carregado a partir da posição 0 da memória (seção 1.2), e a CPU então é liberada para executar fazendo seu ciclo (seção 1.3).

###### Segunda parte

##### Gerência de Memória

Construir gerente de memória para memória paginada. Deve-se demonstrar que o sistema pode carregar vários processos em memória. E depois executar cada um
sequencialmente, do início ao fim. Deve-se provocar que processos utilizem frames não vizinhos na memória para testar
adequadamente a carga e o endereçamento dos processos.

## Pré Requisitos

Antes de começar, você vai precisar ter instalado o [Java](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html) e o [Git](https://git-scm.com) localmente no seu computador. 
Além disto é bom ter um editor para trabalhar com o código como [VSCode](https://code.visualstudio.com/).
Usando a IDE de browser [GitPod](https://gitpod.io/) não é preciso instalar nada localmente no seu computador.

## 🏃ㅤGetting Started

> **Não é preciso instalar o gradle para rodar o código.**

```bash
# Clone este repositório
$ git clone https://github.com/Fizoratti/jav_PUCRS-SisOp-Virtual-Machine/

# Acesse a pasta do projeto no terminal/cmd
$ cd jav_PUCRS-SisOp-Virtual-Machine

# Execute a aplicação
$ gradle run
```

## 🌿ㅤBranches

- ```master```: Branch com a última build do projeto em que o código que executa sem erros.
- ```develop```: Branch para desenvolvimento de features.

## 🚀ㅤFeatures

### OPCODES

Conjunto de instruções.

#### Instruções JUMP

- [ ] JMP
- [ ] JMPI
- [ ] JMPIG
- [ ] JMPIL
- [ ] JMPIE
- [ ] JMPIM
- [ ] JMPIGM
- [ ] JMPILM
- [ ] JMPIEM

#### Instruções Aritméticas

- [ ] ADDI
- [ ] SUBI
- [ ] ADD
- [ ] SUB
- [ ] MULT

#### Instruções de Movimentação

- [ ] LDI
- [ ] LDD
- [ ] STD
- [ ] LDX
- [ ] STX
- [ ] SWAP

#### Instruções Genréricas

- [ ] STOP

### Gerência de Memória

- [ ] Valores Básicos
- [ ] Testes

#### Funcionalidades do Gerênte

- [ ] Alocação
- [ ] Desalocação
- [ ] Interface
- [ ] Estruturas internas

#### Paginação

- [ ] Alocação do programa
- [ ] Execução do processo

## 🛠ㅤTecnologias

As seguintes ferramentas foram usadas na construção do projeto:

- [Gradle](https://gradle.org/install/)


