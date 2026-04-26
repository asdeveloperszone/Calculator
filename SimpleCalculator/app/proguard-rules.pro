# Add project specific ProGuard rules here.
# By default, the flags in this file are applied to all build variants.

# Keep Compose related classes
-keep class androidx.compose.** { *; }

# Keep the application entry point
-keep class com.asdroid.calculator.MainActivity { *; }
