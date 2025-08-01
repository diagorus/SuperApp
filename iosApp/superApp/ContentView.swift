//
//  ContentView.swift
//  superApp
//
//  Created by Nikita Bilous on 31.07.25.
//

import SwiftUI
import sharedKit

struct ContentView: View {
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)
            Text("Hello, world!" + Platform_iosKt.platform())
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
