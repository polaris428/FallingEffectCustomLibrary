# FallingEffectView

**FallingEffectView** is a custom Android View library designed to add dynamic falling effects, such as snowflakes, to your UI. It supports customizable animations, random effects, and various attributes to create smooth and visually appealing effects. The library can be configured both in XML and programmatically in Kotlin, giving you flexibility in your implementation.

---

## 📦 Features

- **Snowflake Animation**: Customizable size, speed, transparency, and angle.
- **Customization**: Support for custom images and animation properties.
- **Flexible Configuration**: Configure properties using XML or programmatically in Kotlin.
- **Resource Efficiency**: Uses `HandlerThread` for asynchronous updates.
- **Fade Effect**: Gradual fading for falling elements.
- **Visibility Handling**: Manages states based on view visibility changes.

---

## 🛠️ Installation

### Gradle

1. Add the JitPack repository to your `build.gradle` file.

   ```gradle
   allprojects {
       repositories {
           ...
           maven { url 'https://jitpack.io' }
       }
   }
2. Add the dependency.

   ```gradle
   dependencies {
       implementation 'com.github.<YourGitHubUsername>:FallingEffectView:1.0.0'
   }


## 🚀 Usage

### 1. Using in XML

```xml
<com.polaris.fallingeffectcustomlibrary.FallingEffectView
    android:id="@+id/fallingEffectView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:creationResourcesNum="100"
    app:snowflakeImage="@drawable/snowflake"
    app:snowflakeAlphaMin="150"
    app:snowflakeAlphaMax="255"
    app:snowflakeSpeedMin="2"
    app:snowflakeSpeedMax="8"
    app:snowflakesFadingEnabled="false"
    app:snowflakesAlreadyFalling="false" />

```
### 2. Configuring Programmatically

You can configure `FallingEffectView` entirely through code for more dynamic use cases.

```kotlin
val fallingEffectView = FallingEffectView(context, null)

// Set properties programmatically
fallingEffectView.creationResourcesNum = 100
fallingEffectView.snowflakeAlphaMin = 150
fallingEffectView.snowflakeAlphaMax = 255
fallingEffectView.snowflakeSpeedMin = 2
fallingEffectView.snowflakeSpeedMax = 8
fallingEffectView.snowflakesFadingEnabled = true
fallingEffectView.snowflakesAlreadyFalling = false



// Stop snowflake animation
fallingEffectView.stopFalling()

// ReStart snowflake animation
fallingEffectView.restartFalling()
```

## 🎛️ Custom Attributes

You can configure the animation effect using these XML attributes or set them programmatically using their corresponding properties in Kotlin.

| Attribute                  | Kotlin Property                | Description                                   | Default     |
|----------------------------|---------------------------------|-----------------------------------------------|-------------|
| `creationResourcesNum`     | `creationResourcesNum`         | Number of falling elements                   | 200         |
| `snowflakeImage`           | `snowflakeImage`               | Drawable resource for snowflake image         | None        |
| `snowflakeAlphaMin`        | `snowflakeAlphaMin`            | Minimum transparency for snowflakes           | 150         |
| `snowflakeAlphaMax`        | `snowflakeAlphaMax`            | Maximum transparency for snowflakes           | 255         |
| `snowflakeSpeedMin`        | `snowflakeSpeedMin`            | Minimum speed of falling elements             | 2           |
| `snowflakeSpeedMax`        | `snowflakeSpeedMax`            | Maximum speed of falling elements             | 8           |
| `snowflakesFadingEnabled`  | `snowflakesFadingEnabled`      | Enable fade effect for falling elements       | `false`     |
| `snowflakesAlreadyFalling` | `snowflakesAlreadyFalling`     | If true, elements will already be falling     | `false`     |


## 📄 License

This project is licensed under the [MIT License](LICENSE). Feel free to use it in your projects!

---

## 🧑‍💻 Contributing

1. Fork the repository.
2. Create a new branch. (`git checkout -b feature/new-feature`)
3. Commit your changes. (`git commit -am 'Add new feature'`)
4. Push to the branch. (`git push origin feature/new-feature`)
5. Open a Pull Request.

---

## 🛠️ Development Environment

- **Kotlin** 2.0.0+
- **Android** SDK 28+
- **Gradle** 8.11+

---

Feel free to update this README to reflect additional features or requirements as the library evolves. If you need further assistance, let me know!

