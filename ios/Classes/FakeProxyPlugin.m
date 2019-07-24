#import "FakeProxyPlugin.h"

@implementation FakeProxyPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
    FlutterMethodChannel* channel = [FlutterMethodChannel
                                     methodChannelWithName:@"v7lin.github.io/fake_proxy"
                                     binaryMessenger:[registrar messenger]];
    FakeProxyPlugin* instance = [[FakeProxyPlugin alloc] init];
    [registrar addMethodCallDelegate:instance channel:channel];
}

static NSString * const METHOD_GETPROXY = @"getProxy";

- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
    if ([METHOD_GETPROXY isEqualToString:call.method]) {
        CFDictionaryRef proxySettings = CFNetworkCopySystemProxySettings();
        NSDictionary *dictProxy = (__bridge_transfer id)proxySettings;
        //是否开启了http代理
        if ([[dictProxy objectForKey:@"HTTPEnable"] boolValue]) {
            NSString * proxyHost = [dictProxy objectForKey:@"HTTPProxy"]; //代理地址
            int proxyPort = [[dictProxy objectForKey:@"HTTPPort"] intValue];  //代理端口号
            result([NSString stringWithFormat:@"%@:%d", proxyHost, proxyPort]);
        } else {
            result(nil);
        }
    } else {
        result(FlutterMethodNotImplemented);
    }
}

@end
