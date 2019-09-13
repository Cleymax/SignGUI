<p align="center">
    <a href="https://github.com/Cleymax/SignGUI" rel="noopener">
        <img width=200px height=200px src="https://i.imgur.com/05lY09v.png" alt="Project logo">
    </a>
</p>

<h3 align="center">Sign GUI</h3>

<div align="center">

  [![Status](https://img.shields.io/badge/status-active-success.svg)]() 
  [![GitHub Issues](https://img.shields.io/github/issues/Cleymax/SignGUI.svg?style=flat-square)](https://github.com/Cleymax/SignGUI/issues)
  [![GitHub Pull Requests](https://img.shields.io/github/issues-pr/Cleymax/SignGUI.svg?style=flat-square)](https://github.com/Cleymax/SignGUI/pulls)
  [![GitHub](https://img.shields.io/github/license/Cleymax/SignGUI)](/LICENSE)

</div>

<p align="center"> Create a sign with a predefined text with the Spigot api, packet and nms !
    <br> 
    Only in <strong>1.14.X</strong> for the moment
</p>

## 📝 Table of Contents
- [About](#about)
- [Getting Started](#getting_started)
- [Usage](#usage)
- [Built Using](#built_using)
- [Authors](#authors)

## 🧐 About <a name = "about"></a>
You want the player to be able to write a course a text. Open a panel to display information. This simple API is made for you!

## 🏁 Getting Started <a name = "getting_started"></a>

### Usage
To use the SignGUI API, either:
- Put it in the `plugins` folder of your server, add it to your dependencies in your plugin.yml (e.g. `depend: [SignGUI]`) and add it to the dependencies in your IDE.
- Put it inside your plugin jar, initialize an `SignManager` in your plugin (don't forget to call the `init()` method).

You can download the latest version on the [Releases page](https://github.com/Cleymax/SignGUI/releases) on Github.

You can also use a build system: **SOON**

#### Gradle
```gradle
repositories {
    mavenCentral()
}

dependencies {
    compile 'fr.cleymax:signgui:1.0.0'
}
```

#### Maven
```xml
<dependency>
  <groupId>fr.cleymax</groupId>
  <artifactId>signgui</artifactId>
  <version>1.0.0</version>
</dependency>
```

## ⛏️ Built Using <a name = "built_using"></a>
- [Gradle](https://gradle.org) - Gradle is a production engine running on the Java platform.

## ✍️ Authors <a name = "authors"></a>
- [@Cleymax](https://github.com/Cleymax) - Idea & Initial work

See also the list of [contributors](https://github.com/Cleymax/SignGUI/contributors) who participated in this project.