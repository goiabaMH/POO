def soma(a, b):
    return a + b

def subtracao(a, b):
    return a - b

def multiplicacao(a, b):
    return a * b

def divisao(a, b):
    if b == 0:
        raise ValueError("Divisão por zero!")
    return a / b

def potencia(base, expoente):
    return base ** expoente

def raiz_quadrada(numero):
    if x < 0:
        raise ValueError("Número negativo!")
    return x ** 0.5