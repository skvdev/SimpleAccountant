# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\cora32\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-dontwarn java.nio.**
-dontwarn java.lang.**
-dontwarn org.codehaus.**

-keep class ru.fors.remsmed3.model.** { *; }

#For zlibscanner
-keep class net.sourceforge.zbar.** { *; }
#For SearchView
-keep class android.support.v7.widget.SearchView { *; }

#Using for retrofit & gson
-keep class com.google.gson.** { *; }
-keep class com.google.inject.** { *; }
-keep class org.apache.http.** { *; }
-keep class org.apache.james.mime4j.* { *; }
-keep class javax.inject.** { *; }
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

-allowaccessmodification
-dontpreverify
-dontskipnonpubliclibraryclasses
-repackageclasses ''
-optimizationpasses 5
#For debugging in case of crush
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable