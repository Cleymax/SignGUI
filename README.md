<p align="center">
    <a href="https://github.com/Cleymax/SignGUI" rel="noopener">
        <img width=350px height=175px src="https://i.imgur.com/05lY09v.png" alt="Project logo">
    </a>
</p>

<h3 align="center">Sign GUI</h3>

<div align="center">

  [![Status](https://img.shields.io/badge/status-active-success.svg)]() 
  [![GitHub Issues](https://img.shields.io/github/issues/Cleymax/SignGUI.svg?style=flat-square)](https://github.com/Cleymax/SignGUI/issues)
  [![GitHub Pull Requests](https://img.shields.io/github/issues-pr/Cleymax/SignGUI.svg?style=flat-square)](https://github.com/Cleymax/SignGUI/pulls)
  [![GitHub](https://img.shields.io/github/license/Cleymax/SignGUI)](/LICENSE)
  [!![GitHub Workflow Status (branch)](https://img.shields.io/github/workflow/status/Cleymax/SignGUI/Java%20CI/mc-1.19)](https://github.com/Cleymax/SignGUI/actions)

</div>

<p align="center"> Create a sign with a predefined text with the Spigot api, packet and nms !
    <br> 
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

### Use

Latest version: [v1.2.1](https://github.com/Cleymax/SignGUI/packages/24279?version=1.2.1).

Please see [Github Documentation](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry) to add Github project repository.

#### Gradle

```groovy
repositories {
  maven {
    name = "GitHubPackages"
    url = uri("https://maven.pkg.github.com/Cleymax/SignGUI")
  }
}

dependencies {
    compile 'fr.cleymax:SignGUI:1.2.1'
}
```

#### Maven
```xml
<repository>
  <id>github</id>
  <url>https://maven.pkg.github.com/Cleymax/SignGUI</url>
</repository>

<dependency>
  <groupId>fr.cleymax</groupId>
  <artifactId>SignGUI</artifactId>
  <version>1.2.1</version>
</dependency>
```


## ⛏️ Built Using <a name = "built_using"></a>
- [Gradle](https://gradle.org) - Gradle is a production engine running on the Java platform.

## ✍️ Authors <a name = "authors"></a>
- [@Cleymax](https://github.com/Cleymax) - Idea & Initial work

See also the list of [contributors](https://github.com/Cleymax/SignGUI/contributors) who participated in this project.