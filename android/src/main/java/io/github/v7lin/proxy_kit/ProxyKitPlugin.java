package io.github.v7lin.proxy_kit;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * ProxyKitPlugin
 */
public class ProxyKitPlugin implements FlutterPlugin, MethodCallHandler {
    // This static function is optional and equivalent to onAttachedToEngine. It supports the old
    // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
    // plugin registration via this function while apps migrate to use the new Android APIs
    // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
    //
    // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
    // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
    // depending on the user's project. onAttachedToEngine or registerWith must both be defined
    // in the same class.
    public static void registerWith(Registrar registrar) {
        final ProxyKitPlugin instance = new ProxyKitPlugin();
        instance.onAttachedToEngine(registrar.context(), registrar.messenger());
    }

    private static final String METHOD_GETPROXY = "getProxy";

    private Context applicationContext;
    private MethodChannel channel;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
        onAttachedToEngine(binding.getApplicationContext(), binding.getBinaryMessenger());
    }

    private void onAttachedToEngine(Context applicationContext, BinaryMessenger messenger) {
        this.applicationContext = applicationContext;
        channel = new MethodChannel(messenger, "v7lin.github.io/proxy_kit");
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        applicationContext = null;
        channel.setMethodCallHandler(null);
        channel = null;
    }

    // --- MethodCallHandler

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (METHOD_GETPROXY.equals(call.method)) {
            String proxyHost = System.getProperty("http.proxyHost");
            String proxyPort = System.getProperty("http.proxyPort");
            if (!TextUtils.isEmpty(proxyHost)
                    && !TextUtils.isEmpty(proxyPort) && TextUtils.isDigitsOnly(proxyPort) && Integer.parseInt(proxyPort) > -1) {
                result.success(String.format("%1$s:%2$d", proxyHost, Integer.parseInt(proxyPort)));
            } else {
                result.success(null);
            }
        } else {
            result.notImplemented();
        }
    }
}
