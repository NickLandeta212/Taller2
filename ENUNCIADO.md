# Evaluación POO - Carrera de Ciberseguridad

## Sistema de Gestión de Activos Digitales con ArrayList

Desarrollar un sistema básico en Java para registrar y administrar activos digitales de una organización. El sistema debe aplicar conceptos de Programación Orientada a Objetos y manejo de colecciones mediante `ArrayList`.

## Temas evaluados

- Constructores
- Métodos accesores `get` y `set`
- Métodos funcionales
- `ArrayList` de objetos
- Herencia
- Encapsulamiento

## Restricción importante

No utilizar atributos ni métodos `static` para resolver la lógica del gestor.

## Clases requeridas

### Clase `ActivoDigital`

Debe contener:

- `codigo` de tipo `String`
- `nombre` de tipo `String`
- `nivelRiesgo` de tipo `int`
- `parcheAplicado` de tipo `boolean`

Debe implementar:

- Constructor con todos los atributos
- Getters y setters
- Encapsulamiento correcto

### Clase `Servidor`

Debe heredar de `ActivoDigital`.

Atributo propio:

- `sistemaOperativo` de tipo `String`

Debe implementar constructor, getter y setter de `sistemaOperativo`.

### Clase `Firewall`

Debe heredar de `ActivoDigital`.

Atributo propio:

- `reglasActivas` de tipo `int`

Debe implementar constructor, getter y setter de `reglasActivas`.

### Clase `GestorActivos`

Debe administrar una colección de objetos usando `ArrayList`:

```java
private ArrayList<ActivoDigital> activos;
```

Métodos obligatorios:

```java
public boolean registrarActivo(ActivoDigital activo)
public ActivoDigital buscarPorCodigo(String codigo)
public int contarActivosCriticos()
public double calcularPromedioRiesgo()
public boolean aplicarParcheActivo(String codigo)
public int obtenerCantidadActivos()
public ArrayList<ActivoDigital> obtenerActivos()
public void reiniciar()
```

## Reglas funcionales

- No se deben registrar activos duplicados por código.
- Se debe utilizar un `ArrayList<ActivoDigital>` para almacenar los activos.
- Se considera activo crítico aquel con nivel de riesgo mayor o igual a 8.
- El promedio de riesgo debe calcularse usando los activos registrados.
- Si no hay activos registrados, el promedio debe ser 0.
- Aplicar parche debe cambiar `parcheAplicado` a `true`.
- Si el activo no existe, aplicar parche debe retornar `false`.
- El método `reiniciar()` debe dejar la lista vacía.

## Rúbrica automática sobre 10 puntos

| Requisito | Puntaje |
|---|---:|
| Constructor, getters y setters de `ActivoDigital` | 1 |
| Herencia en `Servidor` | 1 |
| Herencia en `Firewall` | 1 |
| Uso correcto de `ArrayList` no estático | 1.5 |
| Registro de activos sin duplicados | 1.5 |
| Búsqueda por código | 1 |
| Conteo de activos críticos | 1 |
| Promedio de riesgo | 1 |
| Aplicación de parche y reinicio | 1 |

Total: **10 puntos**

## Ejecución local

```bash
mvn test
```
