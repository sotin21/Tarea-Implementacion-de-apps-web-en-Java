<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registro usuario</title>
    <link rel="stylesheet" type="text/css" href="estilos.css">
</head>
<body>
<h1 class="encabezado"> Registro usuario</h1>
<h2 class="subtitulo">Datos Incorrectos, favor comprobar nuevamente</h2>
<form action="registroUsuario" method="post">
    <div class="centrado">
        <label> Nombre:</label>
        <input name="nombre" type="text" class="campoTexto">
        <label> Rut:</label>
        <input name="rut" type="text" class="campoTexto">
        <label> Edad:</label>
        <input name="edad" type="number" class="campoTexto">
        <br><br>
        <input class="boton" type="submit" value="enviar">
    </div>
</form>
</body>
</html>