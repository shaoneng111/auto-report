<html>
    <head>
        <title>
            WelcomeQ
        </title>
    </head>
<body>
    <h1>
        Welcome ${user}!
    </h1>
    <p>
        Our latest Product:
        <a href="${latestProduct.url}">${latestProduct.name}</a>!
    </p>

    <div>
        countries selection:
        <#--<#assign x = "something">-->
        ${indexOf("met","something")}
        ${indexOf("foo","something")}
    </div>
</body>
</html>