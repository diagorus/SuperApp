import SwiftUI
import sharedKit

@main
struct superApp: App {

    init() {
        InitKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
