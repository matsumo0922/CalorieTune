import androidx.compose.ui.window.ComposeUIViewController
import me.matsumo.calorie.tune.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
