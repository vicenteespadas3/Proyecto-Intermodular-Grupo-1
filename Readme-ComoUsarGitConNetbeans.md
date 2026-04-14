**COMO TRABAJAR DESDE NETBEANS**

**1. AL EMPEZAR A TRABAJAR: Descargar cambios (PULL)**
Antes de escribir una sola línea de código, hay que asegurarse de tener lo último que hayan hecho los demás.

Cómo: Clic derecho en el proyecto > Git > Remote > Pull.

¿Por qué? Si alguien modificó el código mientras tú dormías, esto descarga esos cambios a tu computadora.

**2. AL TERMINAR UNA TAREA: Guardar localmente (COMMIT)**
Hicieron una función nueva o arreglaron un error y funciona bien. Es hora de empaquetar ese cambio.

Cómo: Clic derecho en el proyecto > Git > Commit.

Escriben qué hicieron (Ej: "Añadido el botón de inicio de sesión") y dan a Commit.

**3. PARA COMPARTIR LA TAREA: Subir a GitHub (PUSH)**
El paso final para que el resto del equipo vea los cambios.

Cómo: Clic derecho en el proyecto > Git > Remote > Push.

Nota: Si al hacer Push da un error, el 99% de las veces es porque alguien más subió cambios antes que ellos. La solución es hacer un Pull (paso 1), resolver si hay algún código mezclado, hacer un Commit de esa mezcla y volver a hacer Push.


**Para copiar el repositorio existente debeis hacer esto:**

-En NetBeans, ir al menú superior y seleccionar Team (Equipo) > Git > Clone... (Clonar...).

-En Repository URL, deben pegar el enlace de tu repositorio (https://github.com/vicenteespadas3/Proyecto-Intermodular-Grupo-B.git).

-En Username, ponen su propio usuario de GitHub.

-En Password, pegan su propio Personal Access Token.

-Hacen clic en Next. Seleccionan la rama (usualmente main o master), dan a Next otra vez, eligen en qué carpeta de su PC se guardará el proyecto y le dan a Finish.

-NetBeans les preguntará si quieren abrir el proyecto clonado. Le dan a Open Project. ¡Y listo! Ya tienen el código.


## 🔑 Cómo generar tu Token de Acceso en GitHub (Personal Access Token)

Para poder clonar este proyecto en NetBeans y poder subir o descargar actualizaciones, GitHub requiere que utilices un **Personal Access Token** en lugar de tu contraseña habitual de la cuenta. 

Por favor, sigue estos pasos para generar tu propio token antes de descargar el proyecto:


1. Inicia sesión en tu cuenta de [GitHub](https://github.com/).
2. Haz clic en tu foto de perfil (en la esquina superior derecha) y selecciona **Settings** (Configuración).
3. En el menú lateral izquierdo, desplázate hasta el final de la página y haz clic en **Developer settings** (Opciones de desarrollador).
4. En el nuevo menú de la izquierda, haz clic en **Personal access tokens** y luego selecciona **Tokens (classic)**.
5. Haz clic en el botón desplegable que dice **Generate new token** y elige la opción **Generate new token (classic)**.
6. Rellena los datos de la siguiente manera:
   * **Note:** Escribe un nombre para que sepas para qué es este token (Ejemplo: "Token para NetBeans").
   * **Expiration:** Elige los días que quieres que dure (30 o 90 días está bien por seguridad). *Nota: Cuando expire, tendrás que repetir este proceso.*
   * **Select scopes (Permisos):** Busca en la lista y **marca la casilla principal que dice `repo`**. Esto es obligatorio para que NetBeans pueda leer y modificar el código del proyecto.
7. Desplázate hasta el final de la página y haz clic en el botón verde **Generate token**.
8. ⚠️ **¡Paso crítico!** GitHub te mostrará una pantalla con tu nuevo token (una mezcla larga de letras y números). **Cópialo y guárdalo inmediatamente** en un lugar seguro (un bloc de notas privado o un gestor de contraseñas). Por seguridad, GitHub jamás te lo volverá a mostrar si cierras esa pestaña.

> **💡 ¿Cómo lo uso en NetBeans?**
> Cuando intentes hacer *Clone*, *Push* o *Pull* en NetBeans, te pedirá unas credenciales. 
> * En **User**: Escribe tu nombre de usuario de GitHub.
> * En **Password**: Pega el Token que acabas de guardar (NO tu contraseña normal de GitHub).
