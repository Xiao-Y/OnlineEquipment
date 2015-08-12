
1.每个入口jsp中都要引入公用js和css

<jsp:include page="/resource/index.html"/>

----------------------------------------------------

2.使用font-awesome:

在app.js中：

//启用图标
Ext.setGlyphFontFamily('FontAwesome'); //Font Awesome图标

方式1：glyph : 0xf013,

方式2：glyph : "xf0c7@FontAwesome",

注意：两种方式不同。
图标：
http://fortawesome.github.io/Font-Awesome/cheatsheet/
http://fontawesome.dashgame.com/

------------------------------------------------------------