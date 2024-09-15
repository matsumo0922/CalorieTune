import androidx.compose.ui.window.ComposeUIViewController
import me.matsumo.translator.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
