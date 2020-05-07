<!DOCTYPE html><html><head><meta charset="utf-8"></head><body id="preview">
<h1 class="code-line" data-line-start=0 data-line-end=1><a id="Clean_Architect_0"></a>Clean Architect</h1>
<p class="has-line-data" data-line-start="2" data-line-end="7">El desarrollo de este proyecto fue basado en clean architect, con el fin de no exterdeme mucho decidí manejar tres capas:<br>
- Presentation<br>
- Data<br>
- Domain</p>
<p class="has-line-data" data-line-start="8" data-line-end="9">Clean architect basicamente nos dice que tenemos capas exteriores e inferiores, y que las capas internas no deben saber nada de las externas, debido a eso para cumplir con esa regla el proyecto se maneja por módulo con eso evitamos podemos acceder a recursos que están en capas superiores.</p>
<p class="has-line-data" data-line-start="10" data-line-end="11">En la capa de presentación es la capa que interactua con la interfaz de usuario, en esta capa vamos a poder ver Activitys, Fragmets, ViewModels… etc, Para esta capa aplique el patron MVVM y observables.</p>
<p class="has-line-data" data-line-start="12" data-line-end="13">En la capa de data es donde se maneja las diferentes fuentes de datos, aquí podemos ver el patron repositorio, en este caso para este proyecto nosotros estamos consumiento tanto de base de datos locales como de una api.</p>
<p class="has-line-data" data-line-start="14" data-line-end="15">En la capa de dominio es donde se maneja las reglas de negocio, este capa debe ser un móduo puro de Kotlin o Java, sin ninguna dependencia del framework de Android.</p>
</body></html>
