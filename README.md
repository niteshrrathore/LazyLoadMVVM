# LazyLoad With RecyclerView - Modern Android Architecture

LazyLoadMVVM is a sample project that presents a modern approach to Android app development through MVVM pattern.

The project tries to combine popular Android tools and to demonstrate best development practices by utilizing up to date tech-stack like Kotlin, State Flow and Hilt.

## Description

* Tech/Tools
    * [Kotlin](https://kotlinlang.org/) 100% coverage
    * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) and [Flow](https://developer.android.com/kotlin/flow) for async operations
    * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection
    * [Jetpack](https://developer.android.com/jetpack)
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) that stores, exposes and manages UI state
    * [Retrofit](https://square.github.io/retrofit/) for networking
    
* Modern Architecture
    * Single activity architecture (with [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)) that defines navigation graphs
    * MVVM for presentation layer
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))
    * [Android KTX](https://developer.android.com/kotlin/ktx) - Jetpack Kotlin extensions

## Presentation patterns layers
* ViewModel - [AAC ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) that manages and set the state of the corresponding screen. Additionally, it intercepts UI events as callbacks and produces side-effects. The ViewModel is scoped to the lifetime of the corresponding screen composable in the backstack.
* Model - Data source classes that retrieve content. In a Clean architecture context, one could use UseCases or Interactors that tap into repositories or data sources directly.


As the presentation layer is defined with MVVM, there are a two core components described:
* **State** - data class that holds the state content of the corresponding screen e.g. list of `Products`. The state is exposed as a Compose runtime `MutableState` object from that perfectly matches the use-case of receiving continuous updates with initial value.

### Dependency injection
[Hilt](https://developer.android.com/training/dependency-injection/hilt-android) is used for Dependency Injection as a wrapper on top of [Dagger](https://github.com/google/dagger). 

Most of the dependencies are injected with `@Singleton` scope and are provided within the `NetworkModule` module.

For ViewModels, we use the out-of-the-box `@HiltViewModel` annotation that injects them with the scope of the navigation graph composables that represent the screens.

### Decoupling Compose
Since Compose is a standalone declarative UI framework, one must try to decouple it from the Android framework as much as possible. In order to achieve this, the project uses an `EntryPointActivity` that defines a navigation graph where every screen is a composable.

The `EntryPointActivity` also collects `state` objects and passes them together with the `Effect` flows to each Screen composable. This way, the Activity is coupled with the navigation component and only screen (root level) composables. This causes the screen composables to only receive and interact with plain objects and Kotlin Flows, therefore trying to be platform agnostic as much as possible.
