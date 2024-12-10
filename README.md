# FallingEffectView




https://github.com/user-attachments/assets/f0115b52-f1af-47f3-a47e-e0d86d09a183




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
       implementation 'com.github.poalris428:FallingEffectView:0.0.2'
   }


## 🚀 Usage

### 1. Using in XML

```xml
  <com.polaris.fallingeffectcustomlibrary.FallingEffectView
        android:id="@+id/fallingEffectView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:creationResourcesNum="50"
        app:fallingImage="@drawable/ic_launcher_background"
        app:fallingImageAlphaMin="80"
        app:fallingImageAlphaMax="90"
        app:fallingImageSizeMin="15dp"
        app:fallingImageSizeMax="20dp"
        app:fallingImageSpeedMin="1"
        app:fallingImageSpeedMax="2"
        app:alreadyFallingImage="true"/>

```
### 2. Configuring Programmatically

You can configure `FallingEffectView` entirely through code for more dynamic use cases.

```kotlin
 val fallingEffectView = findViewById<FallingEffectView>(R.id.fallingEffectView)

        fallingEffectView.apply {
            creationResourcesNum = 50 // app:creationResourcesNum="50"
            val drawable = ContextCompat.getDrawable(context, R.drawable.ic_launcher_background)
            fallingImage = drawable?.let { drawableToBitmap(it) }
            fallingImageAlphaMin = 80 // app:fallingImageAlphaMin="80"
            fallingImageAlphaMax = 90 // app:fallingImageAlphaMax="90"
            fallingImageSizeMin = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                15f,
                resources.displayMetrics
            ).toInt() // app:fallingImageSizeMin="15dp"
            fallingImageSizeMax = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                20f,
                resources.displayMetrics
            ).toInt() // app:fallingImageSizeMax="20dp"
            fallingImageSpeedMin = 1 // app:fallingImageSpeedMin="1"
            fallingImageSpeedMax = 2 // app:fallingImageSpeedMax="2"
            alreadyFallingImage = true // app:alreadyFallingImage="true"
        }

    }

    fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }

        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }



// Stop snowflake animation
fallingEffectView.stopFalling()

// ReStart snowflake animation
fallingEffectView.restartFalling()
```

## 🎛️ Custom Attributes

You can configure the animation effect using these XML attributes or set them programmatically using their corresponding properties in Kotlin.

| Attribute                  | Kotlin Property                | Description                                       | Default     |
|----------------------------|--------------------------------|--------------------------------------------------|-------------|
| `creationResourcesNum`     | `creationResourcesNum`         | Number of falling elements                        | 200         |
| `fallingImage`             | `fallingImage`                 | Drawable resource for  falling image   elements   | None        |
| `fallingImageAlphaMin`     | `fallingImageAlphaMin`         | Minimum transparency for  falling image elements  | 150         |
| `fallingImageAlphaMax`     | `fallingImageAlphaMax`         | Maximum transparency for  falling image elements  | 250         |
| `fallingImageAngleMax`     | `fallingImageAngleMax`         | Maximum Angle of falling image elements           | 10          |
| `fallingImageSizeMin`        | `fallingImageSizeMin`        | Minimum imge size of  falling image elements      | 2 DP        |
| `fallingImageSizeMax`        | `fallingImageSizeMax`        | Maximum imge size of  falling image elements      | 8 DP        |
| `fallingImageSpeedMin`      | `fallingImageSpeedMin`        | Minimum speed of  falling image elements          | 2 DP        |
| `fallingImageSpeedMax`      | `fallingImageSpeedMax`        | Minimum speed of  falling image elements          | 8 DP        |
| `fallingImageFadingEnabled`  | `fallingImageFadingEnabled`  | Pause the effect                                  | `false`     |
| `snowflakesAlreadyFalling` | `snowflakesAlreadyFalling`     | If true, elements will already be falling         | `false`     |


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

