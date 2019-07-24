package io.github.v7lin.fake_proxy;

import android.text.TextUtils;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * FakeProxyPlugin
 */
public class FakeProxyPlugin implements MethodCallHandler {
    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "v7lin.github.io/fake_proxy");
        channel.setMethodCallHandler(new FakeProxyPlugin());
    }

    private static final String METHOD_GETPROXY = "getProxy";

    @Override
    public void onMethodCall(MethodCall call, Result result) {
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
