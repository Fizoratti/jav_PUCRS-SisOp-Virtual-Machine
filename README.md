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

**Enunciado do trabalho**: Construir uma máquina virtual conforme definido no enunciado do trabalho. [Link para o pdf](https://moodle.pucrs.br/pluginfile.php/3524730/mod_folder/content/0/TrabalhoSO2021-1-Fase1.pdf) que está no Moodle com o enunciado da primeira parte do trabalho.

### Integrantes
Henrique Pugens Ramires, Gustavo Kunde Silveira, Robson Felipe Bittencourt e Ruan Flesch Pereira.

## 🚀ㅤFeatures

- [x] CPU
- [x] Memória
- [x] Interrupções

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
$ gradle -q --console plain run
```

###### O comando padrão é ```gradle run```. Para executar em ambiente Windows use o comando ```gradlew run```.

## Como selecionar o programa para executar
### Após mostrar as opções, devera inserir o valor correspondente ao programa, comforme a imagem abaixo:

<div align="center"><img src="https://tva1.sinaimg.cn/large/008eGmZEly1gp9u4slymrj30ga072aco.jpg" /></div>

## Programas

### 2. Fibonacci

**Algoritmo em linguagem de alto nível**

```java
public void fibonacci(int quantidadeNumerosFibonacci) {
    int r1;
    int r2;
    int p;
    r1 = 0;
    r2 = 1;

    // 1 - 1 - 2 - 3 - 5 - 8

    for (int i = 0; i < quantidadeNumerosFibonacci; i++) {  // linha 10 a 20
        p = r1 + r2;
        r1 = r2;
        r2 = p;

        System.out.println(r2);
    }
}
```

**Saída**
<div align="center"><img src="https://tva1.sinaimg.cn/large/008eGmZEly1gp9u0tz7mhj30p40wy7fl.jpg" /></div>

### 3. Fatorial

**Algoritmo em linguagem de alto nível**

```java
public void fatorial(int n) {
    int fatorial = 1;

    for (int i = 1; i <= n; i++) {
        fatorial = fatorial * i;
    }

    System.out.println(fatorial);
}
```

**Saída**
<div align="center"><img src="https://tva1.sinaimg.cn/large/008eGmZEly1gp9tzsyya6j30p40wytjl.jpg" /></div>

### 4. Bubble Sort

**Algoritmo em linguagem de alto nível**

```java
private int[] bubblesort(int[] _array) {
    int[] array = _array;
    int temp;
    int length = array.length;
    int swapped;

    for (int j = 0; j < length - 1; j++) {
        swapped = 0;                            // false

        for (int i = 0; i < length - j - 1; i++) {
            if (array[i] > array[i + 1]) {
                temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                swapped = 1;                    // true
            }
        }

        if (swapped == 0) {
            break;
        }
    }

    return array;
}
```

**Saída**
<div align="center"><img src="https://tva1.sinaimg.cn/large/008eGmZEly1gp9tx4s8nhj30p41d67ks.jpg" /></div>

### 5. TRAP IN
**Saída**
<div align="center"><img src="https://tva1.sinaimg.cn/large/008eGmZEly1gp9tswkmfij30p40hgteg.jpg" /></div>

### 6. TRAP OUT
**Saída**
<div align="center"><img src="https://tva1.sinaimg.cn/large/008eGmZEly1gp9to2nzaaj30p40f2af1.jpg" /></div>

## 📦ㅤReleases

- ```VM0``` : Instruções da CPU implementadas. Todos os programas implementados. **(Fase 1)**
- ```VM1``` : Interrupções. **(Fase 2)**
- ```VM2``` : I/O. **(Fase 3)**
- ```VM3``` : Gerente de Memória e Gerente de Processo. **(Fase 4)**
- ```VM4``` : Escalonamento de processos e Concorrência. **(Fase 5)**
