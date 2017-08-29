# Realm
# A ProGuard configuration is provided as part of the Realm library.
# This means that you don’t need to add any Realm specific rules to your ProGuard configuration.

-dontwarn io.realm.**
-keep class io.realm.** {*;}