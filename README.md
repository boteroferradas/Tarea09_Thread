# Tarea 09 - Hilos

### Niveles realizados: 1

![nivel1](capturas/nivel1.png)


| Ejecución | Descarga mas lenta | Tiempo real (ms) | Suma(ms) |
| ---------- | ---------- | ---------- | ---------- |
| 1 | 3167 ms | 3173 ms | 11905 ms |
| 2 | 3464 ms | 3481 ms | 13301 ms |
| 3 | 4116 ms | 4127 ms | 13555 ms |

#### ¿Por qué el tiempo real es mucho menor que la suma?
Porque en la suma se supone que utilizaria un solo hilo para hacer las descargas una detras de otra, y en 
el tiempo real se usan 4 hilos independientes para gestionar cada descarga, por lo que es mucho mas eficiente
al realizarse en paralelo.

#### ¿Qué pasa si hacéis start() y join() dentro del mismo bucle? Probadlo y
poned el tiempo real que os sale.

![pregunta2](capturas/nivel1_1.png)

El programa ejecuta los hilos de forma totalmente secuencial, por lo que destruye la concurrencia y 
el tiempo real se aproxima a la suma.
