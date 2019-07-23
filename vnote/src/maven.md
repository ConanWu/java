## java：maven-dependency中作用域scope含义

在编写pom.xml项目时需要填写依赖的包dependencies，通常需要填写依赖包的groupId、artifactId和version。但是有时候还有一个属性值：scope。

这个scope代表什么含义呢？该属性控制包的作用范围，它有5个可配置的值

|Scope|	作用|
|:----:|:----|
|compile|	compile 是默认值，如果没有指定 scope 值，该元素的默认值为 compile。被依赖项目需要参与到当前项目的编译，测试，打包，运行等阶段。打包的时候通常会包含被依赖项目。|
|provided|	被依赖项目理论上可以参与编译、测试、运行等阶段，相当于compile，但是再打包阶段做了exclude的动作。 适用场景：例如， 如果我们在开发一个web 应用，在编译时我们需要依赖 servlet-api.jar，但是在运行时我们不需要该 jar 包，因为这个 jar 包已由应用服务器提供，此时我们需要使用 provided 进行范围修饰。|
|runtime|	表示被依赖项目无需参与项目的编译，但是会参与到项目的测试和运行。与compile相比，被依赖项目无需参与项目的编译。 适用场景：例如，在编译的时候我们不需要 JDBC API 的 jar 包，而在运行的时候我们才需要 JDBC 驱动包。|
|test|	表示被依赖项目仅仅参与测试相关的工作，包括测试代码的编译，执行。 适用场景：例如，Junit 测试。|
|system|	system 元素与 provided 元素类似，但是被依赖项不会从 maven 仓库中查找，而是从本地系统中获取，systemPath 元素用于制定本地系统中 jar 文件的路径。也就是说，在编译时使用 systemPath 元素指定的 jar 包，在运行时由应用服务器提供 jar 包。 适用场景：项目中存在自定义的基础组件服务时使用。|
